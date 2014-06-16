package iuy.rsa;

/**
 * <p>Titre : </p>
 * <p>Description : </p>
 * <p>Copyright : Copyright (c) 2004</p>
 * <p>Soci�t� : </p>
 * @author non attribuable
 * @version 1.0
 */
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.RandomAccessFile;
import java.math.BigInteger;
import java.util.Vector;

public class RSAFormat
{
    public static BigInteger[] StringToRAS(String aEncryte, double lSegment)
    {
        int nbSeg = (int) Math.ceil(aEncryte.length() / lSegment);
        BigInteger[] vRetour = new BigInteger[(int) nbSeg];
        for (int i = 0; i < nbSeg - 1; i++)
        {
            vRetour[i] = new BigInteger(
                    (aEncryte.substring((int) (i * lSegment),
                            (int) ((i + 1) * lSegment))).getBytes());
        }
        vRetour[nbSeg - 1] = new BigInteger(
                (aEncryte.substring((int) ((nbSeg - 1) * lSegment))).getBytes());
        return vRetour;
        
    }
    
    //test
    public static void main(String args[])
    {
        
        try
        {
            
            RSA encrypteur = new RSA(512);
            RSA decrypteur = new RSA(512);
            encrypteur.setPublicKey(decrypteur.getPublicKey(),
                    decrypteur.getModulus());
            File source = new File("test.txt");
            File destCrypte = new File("test1.txt");
            File destDecrypte = new File("testdoc1.txt");
            
            Vector aEncrypte = new Vector();
            Vector crypte;
            Vector decrypte;
            
            aEncrypte = lireFichier(source, 512 / 8);
            for (int j = 0; j < aEncrypte.size(); j++)
                System.out.println(aEncrypte.elementAt(j));
            
            crypte = encrypteBigIntegers(aEncrypte, encrypteur);
            
            ecrireFichierEncrypter(destCrypte, crypte);
            
            crypte = lireFichierEncrypter(destCrypte, 512 / 8);
            
            decrypte = decrypteBigIntegers(crypte, decrypteur);
            
            ecrireFichier(destDecrypte, decrypte);
            
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        
    }
    
    public static Vector encrypteBigIntegers(Vector BIntegers, RSA rsa)
    {
        Vector vec = new Vector();
        for (int i = 0; i < BIntegers.size(); i++)
        {
            vec.add(rsa.encrypt((BigInteger) BIntegers.elementAt(i)));
        }
        return vec;
    }
    
    public static Vector decrypteBigIntegers(Vector BIntegers, RSA rsa)
    {
        Vector vec = new Vector();
        for (int i = 0; i < BIntegers.size(); i++)
        {
            vec.add(rsa.decrypt((BigInteger) BIntegers.elementAt(i)));
        }
        return vec;
    }
    
    public static void ecrireFichier(File fos, Vector vec) throws IOException
    {
        ecrireFichier(new RandomAccessFile(fos, "rw"), vec);
    }
    
    public static void ecrireFichier(RandomAccessFile fos, Vector vec)
            throws IOException
    {
        fos.seek(0);
        byte[] temp;
        for (int i = 0; i < vec.size(); i++)
        {
            temp = ((BigInteger) vec.elementAt(i)).toByteArray();
            for (int j = 1; j < temp.length; j++)
            {
                fos.writeByte(temp[j]);
                
            }
        }
        fos.close();
    }
    
    public static void ecrireFichierEncrypter(File fos, Vector vec)
            throws IOException
    {
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(
                fos));
        oos.writeObject(vec);
    }
    
    public static Vector lireFichierEncrypter(File fis, int nbBit)
            throws IOException
    {
        Vector vec = null;
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fis));
        try
        {
            vec = (Vector) ois.readObject();
        }
        catch (ClassNotFoundException ex)
        {
            vec = null;
        }
        catch (IOException ex)
        {
            vec = null;
        }
        return vec;
    }
    
    //lit un fichier en acc�s direct et met l'information dans un vecteur de biginteger
    public static Vector lireFichier(RandomAccessFile fis, int nbBit)
            throws IOException
    {
        fis.seek(0);
        
        byte[] tabBytePlus1 = new byte[nbBit];
        //initialise le premier chiffre � 1 pour �viter que biginteger enl�ve le premier 0
        tabBytePlus1[0] = 1;
        Vector vec = new Vector();
        //transfaire les octects dans un tableau de longueur nbBit qui est ensuite transform� en Biginteger
        
        int j = 1;
        for (int i = 0; i < fis.length(); i++, j++)
        {
            if (j >= nbBit)
            {
                j = 1;
                vec.add(new BigInteger(tabBytePlus1));
                tabBytePlus1 = new byte[nbBit];
                
                tabBytePlus1[0] = 1;
                
            }
            byte temp = fis.readByte();
            
            tabBytePlus1[j] = temp;
        }
        if (j != 1)
        {
            byte tabtemp[] = new byte[j];
            
            for (int i = 0; i < j; i++)
            {
                tabtemp[i] = tabBytePlus1[i];
            }
            vec.add(new BigInteger(tabtemp));
        }
        fis.close();
        
        return vec;
    }
    
    public static Vector lireFichier(File fis, int nbBit) throws IOException
    {
        return lireFichier(new RandomAccessFile(fis, "r"), nbBit);
    }
}
