/*******************************************************************

� �RSA.java

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
import java.lang.*;
import java.util.*;

/**
 * Questa classe fornisce � una libreria con le principali funzioni necessarie per la
 * codifica e decodifica RSA
 *
 * @author Luca Bariani  (LucaBariani@yahoo.com)
 * @version 1.0      ottobre 2001 
 * @see <a href="http://www.cacr.math.uwaterloo.ca/hac/"> Handbook of applied cryptograpy </a>
 */

public final class RSA
{
    /* classe che fornisce solo una libreria, quindi non ha senso ereditare da RSA*/

    private RSA()
    {
    }
    
    /* costruttore vuoto e privato, in questo modo non viene creato il costruttore
       predefinito di default; inoltre essendo il costruttore privato si nega a chiunque
       la possibilit� di creare oggetti RSA (infatti non ha senso un oggetto RSA*/

    /**
    Generazione di chiave pubblica e chiave privata per la codifica RSA. Se il metodo non
    riesce a generare chiavi pubblica e privata che soddisfano le ipotesi dell'algoritmo
    RSA si ritorna un vettore non allocato (null). Se il metodo fallisce una volta, si pu�
    reiterare esternamnte; se i fallimenti sono numerosi � opportuno aumentare il
    parametro numBit (non dovrebbero esserci problemi con numBit >= 8)
    
    @param numBit numero di bit minimo che compongono i fattori primi p e q che generano
                  n= p*q (� garantito che p,q >= 2^(numBit-1)
    @param tol    probabilit� di errore massima sui test di primalit� dei numeri generati
    @return       vettore contenente quattro elementi = [n, e, d, phiN]; n = modulo delle
                  operazioni (sia per la chiave pubblica che per quella privata);
                  e = chiave pubblica (con n); d = chiave privata (con n); phiN = funzione
                  di Eulero di N = (p-1) (q-1).
     */
    public static BigInteger[] generateRSAKeys(int numBit, double tol)
    {
        
        BigInteger N, phiN, d, p = Primes.randomPrime(numBit, tol), q = Primes.randomPrime(numBit,
                tol);
        
        /* p e q numeri primi casuali >= 2^(numBit-1)*/

        BigInteger[] gxy;
        /* vettore che conterr� il GCD e gli inversi x e y risultato dell'algoritmo
           extendedEuclideGCD */

        if (q.equals(p))
        {
            /* il caso p = q non � desiderato (anche se � molto infrequente)*/

            q = Primes.nextPrime(q, tol); // cambia q con il primo successivo
        }
        
        N = p.multiply(q); // N= p*q
        phiN = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));
        /*  phiN = (p-1)*(q-1) */

        BigInteger e = Primes.randomPrime(2 * numBit - 4, tol);
        
        /* e = numero primo casuale >= 2^(2*numBit-4)
         
           poich� phiN >= 2^(2*numBit-2), p,q >= 2^(numBit-1)
         
           per numBit ragionevoli si ha   max(p,q) < "e" < phiN
         
           essendo "e" primo si ha anche GCD (e,phiN) = 1 che � una condizione essenziale
           per l'algoritmo RSA
         */

        gxy = Euclide.extendedEuclideGCD(e, phiN);
        
        /* GCD = gxy[0] deve essere = 1 */

        /* gxy[1] � l'inverso moltiplicativo di e modulo phiN cio� "gxy[1]*e mod phiN = 1"  */

        if (gxy[1].compareTo(BigInteger.ZERO) < 0)
            gxy[1] = gxy[1].add(phiN);
        
        if (!gxy[0].equals(BigInteger.ONE))
            return null;
        /* se il GCD(e,phiN) != 1 allora il processo di generazione ha fallito: si restituisce
           un vettore vuoto */

        else
            return new BigInteger[] { N, e, gxy[1], phiN };
    }
    
    /**
    Codifica con algoritmo RSA un messaggio rappresentato da un array di blocchi di byte.
    Questo metodo codifica singolarmente con la chiave specificata ogni blocco del vettore
    di ingresso (ad ogni blocco nell'array in ingresso corrisponde, nella stessa posizione,
    un elemento del vettore criptato in uscita; in questo modo la dimensione del vettore di
    ingresso e quella del vettore di uscita coincidono). L'algoritmo usa come chiave di
    criptazione key e come modulo N. NB: affinch� la sicurezza del messaggio criptato sia
    massima (a parit� di chiave) � bene che il numero di bit che compongono un blocco non
    sia maggiore del numero di bit che compongono i fattori p e q che dividono N (N=p*q).
    
    @param testoChiaroInBlocchi messaggio da codificare suddiviso in blocchi di byte
    @param key chiave per la criptazione
    @param N modulo dell'algoritmo (N=p*q)
    
    @return vettore con il messaggio criptato
     */
    public static BigInteger[] encodeRSA(byte[][] testoChiaroInBlocchi,
            BigInteger N, BigInteger key)
    {
        
        BigInteger[] testoCriptato = new BigInteger[testoChiaroInBlocchi.length];
        
        /* un elemento del vettore criptato per ogni elemento del vettore in ingresso */

        int i; // contatore
        
        for (i = 0; i < testoChiaroInBlocchi.length; i++)
        {
            testoCriptato[i] = new BigInteger(testoChiaroInBlocchi[i]);
            
            testoCriptato[i] = testoCriptato[i].modPow(key, N);
            
            /* l'operazione di criptatura RSA � tutta in questa operazione, che in forma
               pi� chiara �
             
                testoCriptato = (testoChiaro^key) mod N  */
        }
        
        return testoCriptato;
    }
    
    /**
      Decodifica con algorimo RSA di un messaggio criptato. Il messaggio criptato �
      rappresentato da un vettore di interi, ad ogni intero di questo vettore viene
      associato un blocco di byte corrispondenti alla decodifica RSA dell'intero stesso
      (quindi il vettore di uscita contiene tanti blocchi di byte quanti sono gli interi
      nel vettore di ingresso). L'algoritmo usa come chiave di decriptazione key ed usa
      una aritmetica in modulo N.
    
      @param testoCriptato vettore contenente gli interi da decriptare
      @param modulo dell'algoritmo (N=p*q)
      @param key chiave di decriptazione
     */
    public static byte[][] decodeRSA(BigInteger[] testoCriptato, BigInteger N,
            BigInteger key)
    {
        if (testoCriptato == null)
            return null;
        /* se non vi � nulla da decriptare non si ritorna nulla */

        byte[][] testoChiaroInBlocchi = new byte[testoCriptato.length][];
        
        /* testoChiaroInBlocchi � un vettore lungo testoCriptato.length i cui elementi
           sono vettori di byte ancora da allocare */

        int i;
        int lung = testoCriptato.length;
        
        for (i = 0; i < lung; i++)
        {
            /* ciclo per ogni elemento da decriptare */

            testoChiaroInBlocchi[i] = testoCriptato[i].modPow(key, N)
                    .toByteArray();
            /*
               decriptazione:
             
               testoChiaro(blocco) = (testoCriptato^key) mod N
             
               in seguito l'intero BigInteger corrispondente al testo in chiaro viene
               convertito in un array di byte e assegnato al vettore testoChiaroInBlocchi
               (l'allocazione del vettore avviene nel metodo toByteaArray, con l'assegnazione
               il vettore di byte diviene un blocco del vettore di blocchi)
             */
        }
        return testoChiaroInBlocchi;
    }
    
    /**
    Converte un array di byte nel corrispondente array di blocchi di byte. I blocchi di
    byte hanno lunghezza (in byte) specificata dal parametro numBytePerBlocco. Se il vettore
    di byte non contiene esattamente un numero di elementi multiplo di numBytePerBlocco,
    allora l'ultimo blocco ha un numero di byte inferiore.
    
    @param arrayByte array di byte da convertire
    @param numBytePerBlocco dimensione in byte di un singolo blocco
    
    @return array di blocchi della dimensione specificata contenente i byte di ingresso
     */
    public static byte[][] byteToBlocchi(byte[] arrayByte, int numBytePerBlocco)
    {
        int dimArrayBlocchi = (arrayByte.length - 1) / numBytePerBlocco + 1, indiceArrayByte = 0;
        
        byte[][] arrayBlocchi = new byte[dimArrayBlocchi][];
        /* arrayBlocchi = array di dimArrayBlocchi elementi i quali sono array
           ancora da allocare  */

        int i, j; // contatori per i cicli
        
        /* ciclando da 0 a dimArrayBlocchi-1 (tutti i blocchi tranne l'ultimo) si � sicuri che
           tutti i blocchi da creare devono avere esattamente numBytePerBlocco byte ciascuno,
           infatti se la dimensione di arrayByte non � multipla di numBytePerBlocco solo
           l'ultimo blocco da allocare ha meno di numBytePerBlocco byte */

        for (i = 0; i < dimArrayBlocchi - 1; i++)
        {
            // scansione dei blocchi: si creano e si riempiono
            
            arrayBlocchi[i] = new byte[numBytePerBlocco];
            
            for (j = 0; j < numBytePerBlocco; j++, indiceArrayByte++)
            {
                /* ad ogni iterazione di questo ciclo interno si pone un byte di arrayByte
                   dentro il blocco i selezionato dal ciclo esterno*/

                arrayBlocchi[i][j] = arrayByte[indiceArrayByte];
                
                /* il byte con posizione indiceArrayByte in arrayByte � collocato nel byte in
                    posizione j del blocco in posizione i */
            }
        }
        
        i = dimArrayBlocchi - 1; // assegnazione inutile
        
        arrayBlocchi[i] = new byte[arrayByte.length - indiceArrayByte];
        
        for (j = 0; j < arrayBlocchi[i].length; j++, indiceArrayByte++)
        {
            arrayBlocchi[i][j] = arrayByte[indiceArrayByte];
            
            /* il byte con posizione indiceArrayByte in arrayByte � collocato nel byte in
               posizione j del blocco in posizione i */
        }
        return arrayBlocchi;
    }
    
    /**
    Converte un array di Blocchi di byte in un array di byte. Questo metodo � il reciproco
    del metodo byteToBlocchi. La dimensione di un singolo blocco � estratta direttamente
    dall'array di blocchi, quindi non � necessaria. Se i blocchi in arrayBlocchi non sono
    tutti allocati allora il metodo ritorna in uscita un vettore non allocato. Se i blocchi
    hanno dimensioni diverse il metodo funziona ugualmente.
    
    @param arrayBlocchi array di blocchi da convertire in byte
    
    @return array di byte estratti dai blocchi
     */
    
    public static byte[] blocchiToByte(byte[][] arrayBlocchi)
    {
        
        if (arrayBlocchi == null)
            return null;
        /* se non arrayBlocchi non � allocato, allora non si alloca nulla */

        int i, // contatore
        dimArrayByte = 0; // accumulatore
        
        for (i = 0; i < arrayBlocchi.length; i++)
        {
            if (arrayBlocchi[i] == null)
                return null;
            /* se un blocco non � allocato allora si esce con un vettore non allocato*/

            dimArrayByte = dimArrayByte + arrayBlocchi[i].length;
            /* si accumulano le lunghezza di tutti i blocchi (che non necessariamente hanno
               la stessa lunghezza)*/
        }
        int indiceArrayByte = 0; // indice
        
        byte[] arrayByte = new byte[dimArrayByte];
        /* si crea un vettore di byte con dimensione sufficiente per copiarci dentro
           tutti i byte contenuti nei blocchi do arrayBlocchi */

        int j; // contatore
        
        for (i = 0; i < arrayBlocchi.length; i++)
        {
            for (j = 0; j < arrayBlocchi[i].length; j++, indiceArrayByte++)
            {
                arrayByte[indiceArrayByte] = arrayBlocchi[i][j];
                // copia del singolo byte
            }
        }
        
        return arrayByte;
    }
    
    public static void main(String[] argv)
    {
        BigInteger[] nedphi = generateRSAKeys(32, 1e-20);
        BigInteger n;
        
        byte[] messaggioOrig = new byte[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 }, messaggioDecr;
        byte[][] messaggioDecrInBlocchi;
        BigInteger[] mesCriptato;
        
        int i;
        // n e d phi
        if (nedphi != null)
        {
            
            System.out.print("n (modulo): ");
            System.out.println(nedphi[0]);
            System.out.print("e (chiave pubblica): ");
            System.out.println(nedphi[1]);
            System.out.print("d (chiave segreta): ");
            System.out.println(nedphi[2]);
            System.out.print("phi di n: ");
            System.out.println(nedphi[3]);
            
        }
        n = nedphi[1].multiply(nedphi[2]).remainder(nedphi[3]);
        if (n.compareTo(BigInteger.ZERO) < 0)
        {
            /* nelle operazioni in modulo (classi di resto)
               1=1-n */

            n = n.add(nedphi[3]);
            System.out.println("correzione");
        }
        System.out.println(n);
        
        System.out.println("messaggio originale");
        
        for (i = 0; i < messaggioOrig.length; i++)
        {
            System.out.print("   ");
            System.out.print(messaggioOrig[i]);
        }
        
        System.out.println();
        
        mesCriptato = encodeRSA(byteToBlocchi(messaggioOrig, 4),
                nedphi[0],
                nedphi[1]);
        
        System.out.println("messaggio criptato");
        
        for (i = 0; i < mesCriptato.length; i++)
        {
            System.out.print("   ");
            System.out.print(mesCriptato[i]);
        }
        
        System.out.println();
        
        messaggioDecrInBlocchi = decodeRSA(mesCriptato, nedphi[0], nedphi[2]);
        
        messaggioDecr = blocchiToByte(messaggioDecrInBlocchi);
        
        System.out.println("messaggio decriptato");
        
        for (i = 0; i < messaggioDecr.length; i++)
        {
            System.out.print("   ");
            System.out.print(messaggioDecr[i]);
        }
        
        System.out.println();
    }
}
