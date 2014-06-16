package iuy.rsa;

/**
 * <p>Titre : RSA </p>
 * <p>Description : Encodage de donn�es selon le protocole RSA </p>
 * <p>Copyright : Copyright (c) 2004</p>
 * @author Fran�ois Bradette
 * @version 1.1
 * version originale de Robert Sedgewick and Kevin Wayne.Copyright � 2004
 * pris sur le site http://www.cs.princeton.edu/introcs/104crypto/RSA.java.html
 * Modifier par Fran�ois Bradette
 */
import java.io.Serializable;
import java.math.BigInteger;
import java.security.SecureRandom;

//import javax.swing.*;

public class RSA
    implements Serializable
{
  private final static BigInteger one = BigInteger.ONE;
  private final static SecureRandom random = new SecureRandom();

  private BigInteger privateKey = null;
  private BigInteger publicKey = null;
  private BigInteger modulus = null;

  private BigInteger p1 = null;
  private BigInteger p2 = null;

  //Ces variables doivent �tre initialis� pour l'encrytage de donn�es.
  private BigInteger modulusE;
  private BigInteger publicKeyE;

  private int N;
  private BigInteger phi0;
  public RSA(int N)
  {
    this.N = N;
    // generate an N-bit (roughly) public and private key
    //cl�s priv�
    //p1
    p1 = BigInteger.probablePrime(N / 2, random);
    //p2
    p2 = BigInteger.probablePrime(N / 2, random);
    //0
    phi0 = (p1.subtract(one)).multiply(p2.subtract(one));
    //
    //n
    modulus = p1.multiply(p2);
    //d
    setPrivateKey();
    //e
    publicKey = privateKey.modInverse(phi0);

    modulusE = modulus;
    publicKeyE = publicKey;
  }

//renvois la variable public modulo utilis� pour l'encryption
// des donn�
  public BigInteger getModulus()
  {
    return modulus;
  }

//renvois la variable public publicKey utilis� par d'autre pour l'encryption
  public BigInteger getPublicKey()
  {
    return publicKey;
  }

//Cette variable doit �tre initialis� pour �tre en mesure d'encrypt�
//Ces
  public void setPublicKey(BigInteger p, BigInteger n)
  {
    publicKeyE = p;
    modulusE = n;
  }

  /**
   * @param xor BigInteger
   *
   * Cette m�thode est employer pour encrypt� et pour d�crypt� les cl�es
   * C'est l'avantage du ou exclusif il sufi de r�p�t� la m�me opr�ration pour
   * encod� et d�cod�
   **/
  public void xOrClePrive(BigInteger xor)
  {
    xor = xor.pow(4);
    privateKey = privateKey.xor(xor);

  }

  /**
   * s'assure que privateKey
   * 1. n'a aucun autre diviseur que 1
   * 2. qu'il est plus grand que le plus grand entre p1 et p2
   * 3. qu'il est plus petit que p1*p2
   * */
  private void setPrivateKey()
  {
    do
    {
      privateKey = BigInteger.probablePrime(N / 2, random);
    }
    while (privateKey.gcd(phi0).intValue() != 1 ||
           privateKey.compareTo(modulus) != -1 ||
           privateKey.compareTo(p1.max(p2)) == -1);
  }

  /*
   Encrypte le message avec les cl�s public
   pour l'encryption les cl�s public doivent etre initialis�es
   le message doit �tre divis� en paket de N / 8 octects ou bytes
   */
  public BigInteger encrypt(BigInteger message)
  {
    BigInteger rep = null;
    String str_message = new String(message.toByteArray());
    if (message != null)
    {
      if (str_message.length() <= (N / 8))
        if (publicKeyE != null && modulusE != null &&
            message.toByteArray().length < Integer.MAX_VALUE)
          rep = message.modPow(publicKeyE, modulusE);
    }
    return rep;
  }

  public BigInteger encrypt(BigInteger message, BigInteger publicKeyP,
                            BigInteger modulusP)
  {
    BigInteger rep = null;
    String str_message = new String(message.toByteArray());

    if (str_message.length() <= (N / 8))
      if (publicKeyP != null && modulusP != null &&
          message.toByteArray().length < Integer.MAX_VALUE)
        rep = message.modPow(publicKeyP, modulusP);

    return rep;
  }

  //D�crypte le message avec les cl�s priv�
  public BigInteger decrypt(BigInteger encrypted)
  {
    return encrypted.modPow(privateKey, modulus);
  }

  public String toString()
  {
    String s = "";
    s += "public  = " + publicKey + "\n";
    s += "modulus = " + modulus+ "\n";

    return s;
  }
}
