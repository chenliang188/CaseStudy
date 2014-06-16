/*******************************************************************

� �Euclide.java

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
 * Questa classe fornisce � un metodo per calcolare il GCD e gli
 * inversi in una classe resto
 *
 * @author Luca Bariani  (LucaBariani@yahoo.com)
 * @version 1.0      ottobre 2001
 * @see <a href="http://www.cacr.math.uwaterloo.ca/hac/"> Handbook of applied cryptograpy </a>
 */

public final class Euclide

/* classe che fornisce solo una libreria, quindi non ha senso ereditare da Euclide*/
{
    private Euclide()
    {}
  /* costruttore vuoto e privato, in questo modo non viene creato il costruttore
     predefinito di default; inoltre essendo il costruttore privato si nega a chiunque
     la possibilit� di creare oggetti Euclide (infatti non ha senso un oggetto Euclide)*/
    
/**
 * Calcola il GCD (Great Common Divisor = Massimo Comun divisore MCD) tra "a" e "b"
 * utilizzando l'algoritmo di Euclide esteso; inoltre calcola i parametri x e y tali che GCD = a*x + b*y.
 *
 * @param a primo intero
 * @param b secondo intero
 * @return vettore di tre BigInteger = {GCD x y }
 */
    
    public static BigInteger[] extendedEuclideGCD (BigInteger a, BigInteger b)
    {
        int segnoA = a.signum(), /* segno dei valori immessi */
        segnoB = b.signum();
        
        BigInteger A = a.abs(),  /* modulo dei valori immessi */
        B = b.abs(),
        
        min, max,            /* variabili con cui calcolare il GCD */
        
        x = BigInteger.ONE,  /* variabili di appoggio */
        y = BigInteger.ZERO, /* per il calcolo dei    */
        x1= BigInteger.ZERO, /* parametri x e y tc GCD = a*x + b*y */
        x2= BigInteger.ONE,
        y1= BigInteger.ONE,
        y2= BigInteger.ZERO;
        
        BigInteger[] quozienteResto;
        /* quozienteResto[0] = quoziente divisione
           quozienteResto[1] = resto divisione      */
        
        boolean ordineInvertito;
          /* l'algoritmo di euclide ha come ipotesi a > b, poich� i
             parametri immessi sono qualsiasi, occorre tenerne conto
           
             ordineInvertito = false se A >= B
             ordineInvertito = true  se A < B     */
        
    /* in min compare sempre il minimo tra A e B
           in max compare sempre il massimo tra A e B */
        
        if (A.compareTo(B) == -1)
        {
            min = A;
            max = B;
            ordineInvertito = true;
        }
        else
        {
            min = B;
            max = A;
            ordineInvertito = false;
        }
        
        quozienteResto=new BigInteger[] {BigInteger.ZERO, min};
         /* inizializzazione di comodo del vettore: in realt� � necessario inizializzare
            solo quozienteResto[1]=min (per il ciclo while), quozienteResto[0] � inizializzato di
            conseguenza   */
        
    /* algoritmo di Euclide esteso effettivo */
        
        while (quozienteResto[1].compareTo(BigInteger.ZERO) != 0)
        {
                 /* algoritmo di Euclide classico */
            
            quozienteResto = max.divideAndRemainder(min);
                   /* quozienteResto[0] = max div min
                      quozienteResto[1] = max mod min */
            max = min;
            min = quozienteResto[1];
            
                 /*fine algoritmo Euclide classico*/
            
                 /* estensione dell'algoritmo di Euclide */
            
            x = x2.subtract(quozienteResto[0].multiply(x1));
                  /* x = x2 - quoziente * x1 */
            y = y2.subtract(quozienteResto[0].multiply(y1));
                  /* y = y2 - quoziente * y1 */
            x2 = x1;
            x1 = x;
            y2 = y1;
            y1 = y;
            
         /* fine dell'estensione dell'algoritmo di Euclide */
        }
        
        x=x2; /* assegnazione evitabile, aiuta la comprensione */
        y=y2; /* in riferimento all'algoritmo teorico          */
        
        /* x = coefficiente termine min, y = coefficiente termine max */
        
        /* occorre correggere i segni di x e y perch� si � lavorato
           solo con i moduli */
        
        if (ordineInvertito)
        {
       /* ordineInvertito = true: A < B y coeffiente di a, x coefficiente di b */
            if (segnoA == -1) y=y.negate();
       /* segnoA == -1 se "a" � negativo, poich� si sono fatti
          i conti con il modulo di a, si nega y */
            if (segnoB == -1) x=x.negate();
       /* segnoB == -1 se "b" � negativo, poich� si sono fatti
          i conti con il modulo di b, si nega x */
            return new BigInteger[] {max, y, x};
        }
        else
        {
     /* ordineInvertito = false:
      A >= B x coeffiente di a, y coefficiente di b */
            
            if (segnoA == -1) x=x.negate();
       /* segnoA == -1 se "a" � negativo, poich� si sono fatti i conti con il
          modulo di a, si nega x */
            if (segnoB == -1) y=y.negate();
       /* segnoB == -1 se "b" � negativo, poich� si sono fatti i conti con il
          modulo di b, si nega y */
            return new BigInteger[] {max, x, y};
        }
    }
    
  /* metodo di prova e di esempio */
    
    private static void main (String[] argv)
    {
        Random rnd = new Random();
        BigInteger a = new BigInteger (16,rnd), /* numero casuale */
        b = new BigInteger ("-222"),
        c;
        BigInteger[] risultato;
        
        risultato=extendedEuclideGCD (a,b);
        c=a.multiply(risultato[1]);
        c=c.add(b.multiply(risultato[2]));
        System.out.print(risultato[0]);
        System.out.print(" = ");
        System.out.print(a);
        System.out.print(" * ");
        System.out.print(risultato[1]);
        System.out.print(" + ");
        System.out.print(b);
        System.out.print(" * ");
        System.out.println(risultato[2]);
        System.out.println(c);
    }
    
}
