Protokolli ülesehituse ja teostuse ideed
========================================

### Praegune olukord

Protokolli sõnumivahetus koosneb teadetest. Üks teade kujutab endast mingit konkreetset infot, mis saadetakse ühest masinast teise. Hetkel on meie teade järgmise ülesehitusega.

```java
int messageType;
int messageLen;
byte[] data;
```

Kusjuures data võib olla mingi JSON objekt, aga pole teada milline. Praegu on olemas kaks ``Raw*`` klassi mida võiks põhimõtteliselt data kaudu saata.


### Rando plaan info selgemaks esituseks

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
   CREATE_TASK(0),
   ERROR(1);
   // jne

   private final int type;

   MessageType(int type) {
      this.type = type;
   }

   public int getAsInt() {
      return type;
   }

   MessageClass parseMessageClass(Gson gson, byte[] data){
      switch (this){
           case CREATE_TASK:
               return gson.fromJson(String.valueOf(data), CreateTask.class);
           case ERROR:
               return gson.fromJson(String.valueOf(data), Error.class);
           // jne
      }
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

### Konkreetne ettepanek missuguseid teateid on võimalik saata ja mis info nendega kaasa käib.

COMING SOON
