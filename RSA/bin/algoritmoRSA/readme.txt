
   Copyright (c) ottobre 2001

   Luca Bariani LucaBariani@ferrara.linux.it
                LucaBariani@yahoo.com

   membro FLUG: Ferrara Linux User Group  http://www.ferrara.linux.it

   HomePage: http://members.ferrara.linux.it/lucabariani



   This program is free software; you can redistribute it and/or
   modify it under the terms of the GNU General Public License
   version 2 as published by the Free Software Foundation.

   This program is distributed in the hope that it will be useful,
   but WITHOUT ANY WARRANTY; without even the implied warranty of
   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
   See the GNU General Public License for more details.

   You should have received a copy of the GNU General Public
   License along with this program; see the file COPYING.
   If not, write to the Free Software Foundation, 675 Mass Ave,
   Cambridge, MA 02139, USA.




  Questo programma è un esempio di implementazione dell'algoritmo di codifica/decodifica RSA

  In questo programma è possibile generare una chiave RSA con il numero di bit desiderato, codificare un
  qualsiasi messaggio di testo, vedere e modificare il messaggio codificato, infine decodificarlo .

  Le possibilità del programma sono:

   - si può generare una coppia di chiavi RSA a partire da numeri primi aventi numero di bit arbitrario
     (questo numero di bit deve essere > 9, per avere comodamente numeri primi distinti)

   - si può immettere un testo e codificarlo con la chiave di cifratura

   - si visualizza il messaggio codificato, lo si può decofificare con la chiave di decifratura

   - le chiavi di cifratura e decifratura sono arbitrariamente modificabili

   - il messaggio codificato è arbitrariamente modificabile


  Le prove cosigliate sono le seguenti:

   - generare chiavi con dimensione diverse

   - fissata una coppia di chiavi, codicifare e decodificare un messaggio immesso: si ottiene il messaggio
     di partenza

   - fissata una coppia di chiavi, codificare un messaggio immesso, alterare il messaggio codificato (anche
     solo di qualche bit, come cambiare un 1 in 0 e così via) e decodificare: si ottiene un messaggio diverso
     da quello di partenza

   - generare una coppia di chiavi, alterare la chiave di cifratura e/o qualla di decifratura (in modo tale
     che il prodotto delle due chiavi modulo phiN sia diverso da 1); codificare e decodificare un messaggio
     immesso: si ottiene un messaggio che non ha nulla a che fare con quello di partenza



  Bug noti:

   - la codifica/decodifica di messaggi con caratteri particolari tipo "à","è", "ù"... spesso non genera un
     messaggio finale uguale a quello iniziale



  LISTA FILE

  - readme.txt          : questo file di introduzione
  - copying             : licenza GPL per la distribuzione di questo programma
  - InterfacciaRSA.java : contiene tutto ciò che concerne le specifiche per la interfaccia grafica, contiene
                          il main del programma
			  locazione: directory locale (.)
  - InterfacciaRSA.form : contiene il file generato dall'ambiente Forte versione 3 di Sun, questo file non è
                          necessario per la compilazione nè per la esecuzione del programma, ma è
			  fondamentale per alterare mediante gli strumenti visuali di Forte l'interfaccia
			  grafica
			  locazione: accanto al file InterfacciaRSA.java

  - Primes.java         : contiene la porzione di codice che manipola i numeri primi

  - Euclide.java        : contiene la porzione di codice che contiene l'algoritmo euclideo esteso

  - RSA.java            : contiene la porzione di codice che implementa l'algoritmo RSA

  i file Primes.java Euclide.java e RSA.java vanno nella directory ./LucaBariani/Crittografia/
  se si mettono in locazioni diverse occorre cambiare le specifice "package" e "import" nei sorgenti


  COMPILAZIONE

  per compilare basta digitare:

  javac ./LucaBariani/Crittografia/Primes.java
        ./LucaBariani/Crittografia/Euclide.java
	./LucaBariani/Crittografia/RSA.java
	./InterfacciaRSA.java

  (si può fare il tutto con una unica operazione o con 4 operazione distinte; è molto importante mantenere
  l'ordine dei sorgenti da compilare: i cso contrario si possono avere errori di dipendenze)


  ESECUZIONE

  per eseguire il tutto è sufficiente digitare "java InterfacciaRSA" (senza alcuna estensione)


  NOTE

  i sorgenti sono stati da me testati con piattaforma java 1.3 e java 1.3.1 sia in ambiente linux che in
  ambiente windows; in base le specifiche API non dovrebbero esserci problemi con piattaforme java 1.2.x;
  per le piattaforme java 1.1.x dovrebbero mancare alcune funzioni di libreria utilizzate. 