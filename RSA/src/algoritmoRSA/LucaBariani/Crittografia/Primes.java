/*******************************************************************

� �Primes.java

� �Copyright (c) ottobre 2001

   Luca Bariani LucaBariani@ferrara.linux.it
                LucaBariani@yahoo.com

   membro FLUG: Ferrara Linux User Group  http://www.ferrara.linux.it

   HomePage: http://members.ferrara.linux.it/lucabariani



� �This program is free software; you can redistribute it and/or
� �modify it under the terms of the GNU General Public License
� �version 2 as published by the Free Software Foundation.

� �This program is distributed in the hope that it will be useful,
� �but WITHOUT ANY WARRANTY; without even the implied warranty of
� �MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
� �See the GNU General Public License for more details.

� �You should have received a copy of the GNU General Public
� �License along with this program; see the file COPYING.
� �If not, write to the Free Software Foundation, 675 Mass Ave,
� �Cambridge, MA 02139, USA.

********************************************************************/


package algoritmoRSA.LucaBariani.Crittografia;

import java.math.BigInteger;
import java.util.*;
/**
 * Questa classe fornisce � una libreria con le principali funzioni necessarie per la
 * manipolazione dei numeri primi
 *
 * @author Luca Bariani  (LucaBariani@yahoo.com)
 * @version 1.0      ottobre 2001
 * @see <a href="http://www.cacr.math.uwaterloo.ca/hac/"> Handbook of applied cryptograpy </a>
 */

public final class Primes

/* classe che fornisce solo una libreria, quindi non ha senso ereditare da Primes*/

{
    private Primes()
    {}
  /* costruttore vuoto e privato, in questo modo non viene creato il costruttore
     predefinito di default; inoltre essendo il costruttore privato si nega a chiunque
     la possibilit� di creare oggetti Primes (infatti non ha senso un oggetto Primes))*/
    
/**
 * Test probabilistico di primalit� (test di Miller Rabin). Se il test fallisce n �
 * sicuramente un numero composto (cio� non � primo),  se il test ha successo allora n �
 * primo con una probabilit� di errore inferiore a tol; la velocit� del test dipende da
 * n e, a parit� di n, dalla tolleranza tol; per convenzione 0 e 1 non sono primi.
 *
 * @param n numero intero su cui fare il test, n pu� essere anche negativo
 * (per convenzione -n � primo se � primo n)
 * @param tol probababilit� massima di errore nel caso in cui il risultato sia true
 * @return false se il numero � composto, true se il numero � primo con probabilit� di
 * errore al pi� tol
 */
    public static boolean isPrime (BigInteger n, double tol)
    {
        if (n.equals(BigInteger.ZERO))
        {
            return false;
            
            // n=0, 0 non � primo per convenzione
        }
        
        BigInteger nMeno1=n.abs().subtract(BigInteger.ONE);
        // nMeno1 = |n|-1
        
        if (nMeno1.equals(BigInteger.ZERO))
        {
            return false;
            
                /* nMeno1 = 0, quindi n=+1 o n=-1
                   per convenzione 1 e -1 non sono primi */
        }
        
        if (nMeno1.equals(BigInteger.ONE))
        {
            return true;
                /* nMeno1 = 1, quindi n=2 numero primo */
        }
        
        
        /* controlli non obbligatori: non sono richiesti dall'algoritmo di Miller-Rabin
           per� se n � divisibile per 3,5,7,11,13,17 allora n non � primo, questi sono i
           numeri primi pi� piccoli, quindi sono i potenziali divisori pi� comuni   */
        
        if (n.remainder(new BigInteger("3")).equals(BigInteger.ZERO))
        {
            /* se n � divisibile per 3, allora n non � primo */
            
            return false;
        }
        if (n.remainder(new BigInteger("5")).equals(BigInteger.ZERO))
        {
            /* se n � divisibile per 5, allora n non � primo */
            
            return false;
        }
        if (n.remainder(new BigInteger("7")).equals(BigInteger.ZERO))
        {
            /* se n � divisibile per 7, allora n non � primo */
            
            return false;
        }
        if (n.remainder(new BigInteger("11")).equals(BigInteger.ZERO))
        {
            /* se n � divisibile per 11, allora n non � primo */
            
            return false;
        }
        if (n.remainder(new BigInteger("13")).equals(BigInteger.ZERO))
        {
            /* se n � divisibile per 13, allora n non � primo */
            
            return false;
        }
        if (n.remainder(new BigInteger("17")).equals(BigInteger.ZERO))
        {
            /* se n � divisibile per 17, allora n non � primo */
            
            return false;
        }
        
        /* fine dei controlli banali */
        
        
        int esp2=nMeno1.getLowestSetBit();
        
 /* affinch� |n| sia primo e > 2, |n| deve essere dispari, quindi nMeno1= |n|-1 deve
    essere pari; utilizzando il metodo getLowestSetBit si ottiene il primo bit meno
    significativo con valore 1 nella rappresentazione binaria di nMeno1, quindi in nMeno1
    si hanno tanti fattori 2 quanto il valore di esp2, quindi nMeno1 � divisibile per
    2^esp2	*/
        
        if (esp2 == 0)
        {
            return false;
            
    /* se esp2 = 0 allora nMeno1 � dispari, quindi n � un
           numero pari diverso da 2, quindi n non � primo      */
        }
/*
    Test di Miller Rabin
 
    a= base casuale
 
    n-1 = pari = (2^r)*s, s = dispari
 
    si costruisce la sequenza (ogni termine � preso modulo n)
 
    a^s, a^2s, a^4s, .. , a^(2^r)*s=a^(n-1)
 
    n passa il test con base a se
 
    - l'ultimo termine della sequanza � 1
    - la prima occorrenza di 1 � o il primo termine o � preceduto da -1 (in modulo
      n -1=n-1)
 
    (entrambe le condizioni sono da soddisfare)
 
    se n non passa il test allora n � composto
    se n � composto ha una probabilit� del 25% di passare il test
 */
/* la probabilit� che un numero non primo passi il test di Miller Rabin � al pi� 1/4, la
   probabilit� che un numero non primo passi k volte il test di Miller Rabin con base
   casuale � 1/(4^k)
 
   fissata la probabilit� massima di errore tol per il test di primalit�, si risolve la
   equazione seguente:
 
    1/(4^k) = tol
    4^k=1/tol
    ln (4^k)= ln (1/tol)
    2k ln(2)=-ln (tol)
    k = -ln (tol)/(2 ln (2))
 
    k � il numero di test di Miller Rabin che un numero deve passare per avere la
    probabilit� di errore richiesta
 
    come k si prende l'intero superiore del rapporto calcolato (per comodit� si arrotonda
    e si aggiunge 1)
 */
        
        int maxCicli = (int) Math.round(-Math.log(tol)/(2*Math.log(2)))+1,
        contCicli = 1, // contatore ciclo esterno
        i;             // contatore ciclo interno
        
/*  dispari � nMeno1 diviso per la massima potenza di 2 possibile (2^esp2), questa
    divisione � molto rapida ed efficiente usando lo shift a destra dei bit       */
        
        BigInteger dispari=nMeno1.shiftRight(esp2),
        due = new BigInteger("2");  /* due � usato come costante */
        
        Random rnd=new Random(); // inizializzazione numeri casuali
        
        BigInteger base, // base del test di Miller Rabin
        last, // variabili di appoggio nei cicli
        prec; //
        
        boolean ok = true, // ok = false se n � composto
        primo1;    /* flag che indica se � gi� stato trovato nel ciclo un valore 1 */
        
 /* si cicla per al massimo maxCicli volte, ma ci si interrompe se si � gi� dimostrato
    che n � composto       */
        
        while ((contCicli <= maxCicli) && ok)
        {
            do
            {
                base = new BigInteger(n.bitLength(),rnd);
            }
            while ((base.compareTo(BigInteger.ZERO) == 0) ||
            (base.compareTo(n) >= 0));
            
      /* si genera base come numero casuale con un numero di bit pari al numero di bit di
         n, per esigenze teoriche base deve essere diverso da 0 e < n   */
            
            contCicli++; // incremento contatore di ciclo
            
            last=base.modPow (dispari,n); // last=(base^dispari) mod n
            
      /* l'utilizzo del metodo modPow � pi� efficiente della coppia di metodi pow e
         remainder usati in cascata*/
            
            primo1 = last.equals(BigInteger.ONE);
            
      /* primo1 indica se � stato trovato il primo valore pari ad 1 */
            
      /* un ciclo per ogni volta che n � divisibile per 2 */
            
            for (i=1; i <= esp2; i++)
            {
                prec = last;
                last = last.modPow(due,n); // last = (last^2) mod n
                
        /* !primo1 � vero se non � stato ancora incontrato il primo 1 nella sequanza dei
           last  */
                
                if ( !primo1  && last.equals(BigInteger.ONE))
                {
                    primo1 = true; // � stato trovati il primo 1
                    
                    ok = prec.equals (nMeno1);
       /* se prec=nMeno1=|n|-1 allora n ha passato questo test, altrimenti no */
                }
            }
            ok = ok && (last.equals(BigInteger.ONE));
       /* condizione necessaria affinch� n passi il test � che l'ultimo last della
          sequenza sia 1 */
        }
        return ok;
    }
    
/**
 * Metodo che fornisce il numero primo successivo ad n. Per certificare la primalit� della
 * soluzione trovata si usa il test isPrime con tolleranza di errore tol.
 *
 * @param n   intero positivo
 * @param tol tolleranza all'errore ammessa
 *
 * @return il primo numero non composto successore di n
 */
    
    public static BigInteger nextPrime (BigInteger n, double tol)
    {
        
        /* la ricerca del numero primo successore � fatta  per tentativi   */
        
        BigInteger due = new BigInteger ("2");  // costante 2
        
        if (n.getLowestSetBit() != 0)
        {
        /* se n.getLowestSetBit() != 0 allora n � divisibile per 2, quindi n+1 � dispari   */
            
            n=n.add(BigInteger.ONE);
        }
        else n=n.add(due);
        
        /* se n � dispari il primo numero non composto successore di n � >= n+2 */
        
        /* da qui in poi n � dispari ed � il primo numero su cui fare la verifica, il numero
           primo � ricercato tra i dispari perch� l'unico primo pari � 2*/
        
        // ricerca per tentativi
        
        while (!isPrime(n,tol)) // test di primalit�
        {
            n=n.add(due); // numero successivo su cui fare il test
        }
        return n;
    }
    
/**
 * Genera un numero primo casuale. Si garantisce che il numero generato � primo con
 * probabilit� di errore <= tol; inoltre si garantisce che il numero primo generato
 * � >= 2^(numBit-1).
 *
 * @param numBit numero minimo di bit significativi garantiti per il numero casuale
 * generato
 * @param tol probabilit� massima che il numero generato non sia primo
 * @return numero primo casuale >= 2^(numBit-1)
 */
    public static BigInteger randomPrime (int numBit, double tol)
    {
        Random rnd = new Random();
        // inizializzazione numeri casuali
        BigInteger n = new BigInteger(numBit,rnd);
        
    /* generazione di n come intero casuale di numBit bit */
        
        /* si setta il bit di n in posizione numBit, in questo modo n � un numero casuale
           >= 2^(numBit-1), ci� garantisce che n ha un ordine di grandezza stabilito a priori
           attraverso il parametro  NumBit */
        
        n=n.setBit(numBit);
        
        return nextPrime (n,tol);
        
        /* per costruzione nextPrime(n) � un numero primi casuali, con lo stesso ordine di
           grandezza di n (stabilito mediante il parametro numBit)   */
    }
    
    
/* metodo main privato perch� solo di test e di esempio, si commenta da solo */
    
    public static void main (String[] argv)
    {
        BigInteger n=new BigInteger("1054895555218651654445454541251");
        System.out.println ("numero n");
        System.out.println (n);
        System.out.print("test di primalit� (con tolleranza 1e-40): ");
        System.out.println (isPrime(n,1e-40));
        System.out.println ("il 'primo numero primo' sucessore di n �");
        System.out.println (nextPrime(n,1e-40));
        System.out.println("numero primo casuale (con tolleranza 1e-40): ");
        System.out.println (randomPrime(100,1e-40));
    }
}
