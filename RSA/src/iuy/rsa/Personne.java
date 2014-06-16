package iuy.rsa;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.math.BigInteger;

public class Personne
    implements Serializable
{
  protected String nom;

  protected BigInteger modulos;
  protected BigInteger clePublic;

  public Personne(String nom, BigInteger modulos, BigInteger clePublic)
  {
    this.nom = nom;
    this.modulos = modulos;
    this.clePublic = clePublic;
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

  public String getNom()
  {
    return nom;
  }

  public BigInteger getClePublic()
  {
    return clePublic;
  }

  public void setClePublic(BigInteger clePublic)
  {
    this.clePublic = clePublic;
  }

  public BigInteger getModulos()
  {
    return modulos;
  }

  public void setModulos(BigInteger modulos)
  {
    this.modulos = modulos;
  }
}
