Protokolli ülesehituse ja teostuse ideed
========================================

## Praegune olukord

Protokolli sõnumivahetus koosneb teadetest. Üks teade kujutab endast mingit konkreetset infot, mis saadetakse ühest masinast teise. Hetkel on meie teade järgmise ülesehitusega.

```java
int messageType;
int messageLen;
byte[] data;
```

Kusjuures data võib olla mingi JSON objekt, aga pole teada milline. Praegu on olemas kaks ``Raw*`` klassi mida võiks põhimõtteliselt data kaudu saata.


## Rando plaan info selgemaks esituseks

Mina, Rando käisin välja mõtte saata teateid kujul

```java
int messageLen;
byte[] data;
```

kus data on serialiseeritud JSON objekt, mis on klassist ``MessageClass``. Kogu asi hakkaks välja nägema niimoodi

```java
abstract class MessageClass {}

// Ütle serverile "tee uus task".
class CreateTask extends MessageClass{
   RawTask rawTask;
}

// Ütle kliendile, et Server ei saanud millegagi hakkama.
class Error extends MessageClass {
   String description;
}

// jne
```

Selle lähenemise probleem on selles, et minu teada Gson ei oska abstraktseid klasse deserialiseerida, kuna ta ei tea, missuguse konkreetse klassi isend tuleks tagastada.

Selle probleemi saab lahendada GSON-i trikkidega või ühe teise meetodiga, mida kohe demonstreerin.

```java
enum MessageType {
   CREATE_TASK(0, CreateTask.class),
   ERROR(1, Error.class);
   // jne

   private final int type;
   private final Class class;

   MessageType(int type, Class class) {
      this.type = type;
      this.class = class;
   }

   public int getAsInt() {
      return type;
   }

   MessageClass parseMessageClass(Gson gson, byte[] data){
      return gson.fromJson(String.valueOf(data), class);
      /*
      switch (this){
           case CREATE_TASK:
               return gson.fromJson(String.valueOf(data), CreateTask.class);
           case ERROR:
               return gson.fromJson(String.valueOf(data), Error.class);
           // jne
      }
      */
   }
}

abstract class MessageClass {
   abstract MessageType getMessageType();

}

// Ütle serverile "tee uus task".
class CreateTask extends MessageClass{
   RawTask rawTask;
   MessageType getMessageType(){
      return MessageType.CREATE_TASK;
   }
}

// Ütle kliendile, et Server ei saanud millegagi hakkama.
class Error extends MessageClass {
   String description;
   MessageType getMessageType(){
      return MessageType.ERROR;
   }
}
// jne
```

Paistab, et ma ei sa ikka veel päris hästi aru kuidas enum-id töötavad, aga maa
loodan et mu idee on põhimõtteliselt teostatav. Ehk siis igale MessageType-i
variandile vastab üks MessageClass-i alamklass ja meetodid garanteerivad, et alati
(de)serialiseeritakse õige asi. Teadete jaoks kasutame ikka vana struktuuri

```java
int messageType;
int messageLen;
byte[] data;
```

aga nüüd ei pea muretsema, kas deserialiseeritakse ikka õige asi.

Kui tekkib tahtmine teha protokoll JSON-i teegist sõltumatu, siis võib
``parseMessageClass`` meetodi panna ka kuhugile mujale staatiliseks meetodiks.

Aga kui juba teha protokoll JSON-i teegist sõltumatu, siis äkki on olemas parem
JSON-i teek, mis oskab abstraktseid klasse deserialiseerida.

## Konkreetne ettepanek missuguseid teateid on võimalik saata ja mis info nendega kaasa käib.

### Seansid

Ilmselt on kõike seda lihtsam ette kujutada näite baasil. Ütleme, et kasutaja
soovib teha järgmist:
1. Logib sisse
2. Vaatab, mis ülesanded tal on.
3. Loob uue ülesande.
4. Avastas, et ta on uuele ülesandele pannud kogemata vale kirjelduse/tähtaja
   ja muudab seda.
5. Märgib ühe oma ülesannetest tehtuks.
6. Logib välja.

See on nn kasutaja seanss, mis on kõik sisselogimise ja väljalogimise vahel.
Seanssi võib teostada protokollis kolmel viisil.
1. Ühele seansile vastab üks Socket. See on halb, kuna seansid võivad kesta kaua
   ja server peab neid kõiki Socketeid alles hoidma. Samuti pole sell juhul
   võimalik teha CLI kliente, mida seansi jooksul korduvalt käivitatakse.
2. Seansi jooksul luuakse mitu korda Socketiga ühendus. Kasutaja parool/ID
   saadetakse kõige esimese Socketiga. Server peab meeles kes on kust sisse logitud.
3. Seansi jooksul saadetakse iga kord kasutaja parool/ID. Server peab meeles
   ainult seda infot, mille talletamine on vajalik.

Paneme tähele, et CLI või TUI klient võib kasutaja parooli/ID ka kettale
talletada, umbes nagu mõned git-i GUI-d ja siis ei pruugi esimene ja viimane
samm kasutajale nähtavad olla.

Protokolli teostus oleneb sellest, kas valida 2. või 3. variant. Järgnevalt oletan,
et valime 2. variandi, kuigi 3. tundub parem.

### TODO
