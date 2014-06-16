package iuy.rsa;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.Hashtable;

public class Utilisateur
    extends Personne
    implements Serializable
{
  protected RSA cleRSA;
  protected Hashtable contacts;

  public Utilisateur(String nom, RSA cleRSA)
  {
    super(nom, cleRSA.getModulus(), cleRSA.getPublicKey());
    super.nom = nom;
    this.cleRSA = cleRSA;
    contacts = new Hashtable();
  }

  private void readObject(ObjectInputStream ois) throws ClassNotFoundException,
      IOException
  {
    ois.defaultReadObject();
  }

  private void writeObject(ObjectOutputStream oos) throws IOException
  {
    oos.defaultWriteObject();
  }

  public void addContact(Object key, Object values)
  {
    contacts.put(key, values);
  }

  public Object removeContact(Object key)
  {
    return contacts.remove(key);
  }

  public Object getContact(Object key)
  {
    return contacts.get(key);
  }

  public RSA getCleRSA()
  {
    return cleRSA;
  }

  public Hashtable getContacts()
  {
    return contacts;
  }

  /**
   *
   * @deprecated
   * @param modulos BigInteger
   */
  public void setModulos(BigInteger modulos)
  {
    super.modulos = modulos;
  }

  /**
   *
   * @deprecated
   * @param clePublic BigInteger
   */
  public void setClePublic(BigInteger clePublic)
  {
    super.clePublic = clePublic;
  }

  public void setContacts(Hashtable contacts)
  {
    this.contacts = contacts;
  }

  public void decryptCle(byte[] password)
  {
    this.cleRSA.xOrClePrive(new BigInteger(password));
  }
}
