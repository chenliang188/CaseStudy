package iuy.Interface;

import iuy.rsa.Personne;
import iuy.rsa.RSA;
import iuy.rsa.RSAFormat;
import iuy.rsa.Utilisateur;
import iuy.util.OutilsWindow;

import java.awt.HeadlessException;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.math.BigInteger;
import java.util.Hashtable;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

public class Principal
{
    
    //fenetre encrytion
    private Encryption frm = null;
    
    //Si vous modifier cette constante vous devez r�initialiser vos utilisateurs (donc supprimer Utilisateur.data)
    //(Longueur des cl�) * 8
    private final int BIT_ENCRYPTION = 256;
    
    //fichier par defaut
    private final String DEFAULT = "Utilisateur.data";
    
    //variable fichier pas essenciel mais utile pour v�rifier si le fichier existe
    private File dataBase;
    
    //flux de sorti
    //pour object
    private ObjectOutputStream oos; // = new ObjectOutputStream(fos);
    
    //pour fichier
    private FileOutputStream fos;
    
    //flux d'entrer
    //pour object
    private ObjectInputStream ois;
    
    //pour fichier
    private FileInputStream fis;
    
    //base de donn�e des utilisateurs
    private Hashtable utilisateurs;
    
    //base de donn�e des contacts
    private Hashtable contacts = new Hashtable();
    
    //interface graphique
    private GestionComptes frame;
    
    //utilisateur courrant 0_0 mouahahah
    private Utilisateur utilisateurCourrant;
    
    private Personne ContactCourrent;
    
    private File fichierSource = null;
    
    private File fichierDestination = null;
    
    private byte[] motpas;
    
    //Construire l'application
    public Principal()
    {
        
        chargeBD();
        
        frame = new GestionComptes();
        
        frame.addWindowsListenerClosing(new GestionComptes_this_windowsClosingAdapter(
                this));
        frame.addBConnection(new GestionComptes_bConnection_MouseListener(this));
        frame.addBAjoute(new GestionComptes_bAjoute_MouseListener(this));
        frame.addBEfface(new GestionComptes_bEfface_MouseListener(this));
        frame.addBQuitter(new GestionComptes_bQuitter_MouseListener(this));
        frame.addBSupprimer(new GestionComptes_bSupprimer_MouseListener(this));
        
        //Centrer la fen�tre
        OutilsWindow.centrer(frame);
        frame.show();
        
    }
    
    //le nom le dit
    private void chargeBD()
    {
        try
        {
            dataBase = new File(DEFAULT);
            if (dataBase.exists())
            {
                fis = new FileInputStream(dataBase);
                ois = new ObjectInputStream(fis);
                utilisateurs = (Hashtable) ois.readObject();
                contacts = (Hashtable) ois.readObject();
            }
            else
            {
                dataBase.createNewFile();
                utilisateurs = new Hashtable();
            }
        }
        catch (IOException ex)
        {
            ex.printStackTrace();
        }
        catch (ClassNotFoundException ex)
        {
            ex.printStackTrace();
        }
        finally
        {
            try
            {
                if (ois != null)
                {
                    ois.close();
                }
                if (fis != null)
                {
                    fis.close();
                }
            }
            catch (IOException ex)
            {
                ex.printStackTrace();
            }
        }
        
    }
    
    private void sauvegarderBD()
    {
        try
        {
            
            fos = new FileOutputStream(dataBase);
            oos = new ObjectOutputStream(fos);
            oos.writeObject(utilisateurs);
            oos.writeObject(contacts);
        }
        catch (FileNotFoundException ex)
        {
            ex.printStackTrace();
        }
        catch (IOException ex)
        {
            ex.printStackTrace();
        }
    }
    
    //initialisation de la fen�tre d'encryption
    protected void ouvrirFenetreEncryption()
    {
        frame.hide();
        frm = new Encryption();
        OutilsWindow.centrer(frm);
        frm.setListeUser(contacts);
        
        //ajout d'�coute sur les boutons
        frm.addWindowsListenerClosing(new GestionComptes_this_windowsClosingAdapter(
                this));
        frm.addBAjouterMouseListener(new AjoutContact_bAjouter_MouseListener(
                this));
        frm.addBParSourceMouseListener(new Encryption_bSource_MouseListener(
                this));
        frm.addBParDestinationMouseListener(new Encryption_bDestination_MouseListener(
                this));
        frm.addBEncryptionMouseListener(new Encryption_bEncryption_MouseListener(
                this));
        frm.addBDecryptionMouseListener(new Encryption_bDecryption_MouseListener(
                this));
        frm.addBQuitterMouseListener(new GestionComptes_bQuitter_MouseListener(
                this));
        frm.addBRetournerCompteMouseListener(new Encryption_bRetournerCompte_MouseListener(
                this));
        
        //attribut les cl�es publics
        frm.setCleeModulos(utilisateurCourrant.getModulos().toString());
        frm.setCleePublic(utilisateurCourrant.getClePublic().toString());
        
        //affiche la fen�tre
        frm.show();
    }
    
    /**
     *  Cette m�thode est ex�cut� lorsque l'on appuit sur le bouton bEfface
     *
     * @param e MouseEvent
     *
     */
    
    protected void bEfface_actionPerformed(MouseEvent e)
    {
        frame.clear();
    }
    
    /**
     *
     * bAjoute_mouseClicked
     *
     * @param e MouseEvent
     *
     *
     * cette m�thode est ex�cut� lorsque l'on appuit sur le bouton bAjoute
     * */
    
    protected void bAjoute_mouseClicked(MouseEvent e)
    {
        String nom = frame.getNom();
        char motpasse[] = frame.getMotPass();
        // Object temp = utilisateurs.get(nom);
        if (nom.length() >= 4 && motpasse.length >= 4
                && utilisateurs.get(nom) == null)
        {
            RSA rsa = new RSA(BIT_ENCRYPTION);
            //      rsa.xOrClePrive(new BigInteger( (nom + new String(motpasse)).getBytes()));
            utilisateurs.put(nom, new Utilisateur(nom, rsa));
            frame.clear();
        }
        else
        {
            if (nom.length() < 4 && motpasse.length < 4)
            {
                JOptionPane.showMessageDialog(frame,
                        "Erreur dans le nom et le mot de" + " passe ",
                        "Le nom et le mot de passe " + "sont invalide",
                        JOptionPane.ERROR_MESSAGE);
            }
            else if (nom.length() < 4)
            {
                JOptionPane.showMessageDialog(frame,
                        "Erreur dans le nom",
                        "Le nom est invalide",
                        JOptionPane.ERROR_MESSAGE);
            }
            else if (motpasse.length < 4)
            {
                JOptionPane.showMessageDialog(frame,
                        "Erreur dans le mot de passe",
                        "Le mot de passe est invalide",
                        JOptionPane.ERROR_MESSAGE);
            }
            else if (utilisateurs.get(nom) != null)
            {
                JOptionPane.showMessageDialog(frame,
                        "Ce nom est d�ja utilis�",
                        "Erreur dans le nom",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    //cette m�thode est ex�cut� lors de la fermeture de la fen�tre GestionComptes
    protected void this_windowClosing(WindowEvent e)
    {
        fermer();
    }
    
    private void fermer()
    {
        sauvegarderBD();
        System.exit(0);
    }
    
    //M�thode main
    public static void main(String[] args)
    {
        try
        {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        new Principal();
    }
    
    /*private void jbInit() throws Exception
       {
       }*/

    /**
     * Cette m�thode est ex�cut� lorsque l'on appuit sur le bouton bQuitter
     *
     * bQuitter_actionPerformed
     *
     * @param e MouseEvent
     */
    protected void bQuitter_actionPerformed(MouseEvent e)
    {
        fermer();
    }
    
    /**
     * Cette m�thode est ex�cut� lorsque l'on appuit sur le bouton bSupprimer
     *
     * bSupprimer_actionPerformed
     *
     * @param e MouseEvent
     */
    protected void bSupprimer_actionPerformed(MouseEvent e)
    {
        if (utilisateurs.remove(frame.getNom()) != null)
        {
            JOptionPane.showMessageDialog(frame,
                    "Suppression r�ussi",
                    "Suppression r�ussi",
                    JOptionPane.PLAIN_MESSAGE);
        }
        else
        {
            JOptionPane.showMessageDialog(frame,
                    "la suppression a �chou�",
                    "la suppression a �chou�",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
    
    /**
     * Cette m�thode est ex�cut� lorsque l'on appuit sur le bouton bConnexion
     *
     * bConnexion_actionPerformed
     *
     * @param e MouseEvent
     */
    protected void bConnexion_actionPerformed(MouseEvent e)
    {
        Object util = utilisateurs.get(frame.getNom());
        if (util != null)
        {
            utilisateurCourrant = (Utilisateur) util;
            
        }
        if (utilisateurCourrant != null)
        {
            if (frame.getMotPass().length != 0)
            {
                motpas = new String(frame.getMotPass()).getBytes();
                
                //utilisateurCourrant.decryptCle(motpas);
                
                ouvrirFenetreEncryption();
            }
            
        }
        else
        {
            JOptionPane.showMessageDialog(frame,
                    "Cette utilisateur n'existe pas",
                    "Cette utilisateur n'existe pas",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
    
    /**
     * Cette m�thode est ex�cut� lors de l'ajout d'un contact
     *
     * AjoutContact_bAjouter_actionPerformed
     *
     * @param e MouseEvent
     */
    protected void AjoutContact_bAjouter_actionPerformed(MouseEvent e)
    {
        AjoutContact ac = new AjoutContact(frame, "Ajout de contact", true);
        if (ac.getValeurRetour() == ac.AUCUNE_ERREUR)
            contacts.put(ac.getNom(), new Personne(ac.getNom(), new BigInteger(
                    ac.getCleeModulo()), new BigInteger(ac.getCleePublic())));
        
        frm.setListeUser(contacts);
    }
    
    /**
     * Cette m�thode est ex�cut� lorsque l'on appuit sur le bouton bSource
     *
     * bSource_bAjouter_actionPerformed
     *
     * @param e MouseEvent
     */
    protected void encryption_bSource_actionPerformed(MouseEvent e)
    {
        fichierSource = selectionnerFichier();
        if (fichierSource != null)
            frm.setTextSource(fichierSource.getPath());
    }
    
    /**
     * Cette m�thode est ex�cut� lorsque l'on appuit sur le bouton bDestination
     *
     * bSource_bDestination_actionPerformed
     *
     * @param e MouseEvent
     */
    protected void encryption_bDestination_actionPerformed(MouseEvent e)
    {
        fichierDestination = selectionnerFichier();
        if (fichierDestination != null)
            frm.setTextDestination(fichierDestination.getPath());
    }
    
    //Cette m�thode est utilis� pour la s�lection de fichier
    private File selectionnerFichier() throws HeadlessException
    {
        JFileChooser jfc = new JFileChooser(new File("/"));
        if (JFileChooser.APPROVE_OPTION == jfc.showOpenDialog(jfc))
        {
            return jfc.getSelectedFile();
        }
        return null;
    }
    
    /**
     * cette m�thode est ex�cut� lorsque l'on appuit sur le bouton bEncrypter
     *
     * encryption_bEcryption_actionPerformed
     *
     * @param e MouseEvent
     */
    protected void encryption_bEcryption_actionPerformed(MouseEvent e)
    {
        if (fichierDestination == null || fichierSource == null)
            JOptionPane.showMessageDialog(frm,
                    "Vous devez attribuer les fichiers sources et destination",
                    "Erreur",
                    JOptionPane.ERROR_MESSAGE);
        else if (frm.getSelectedUser() != null)
        {
            ContactCourrent = (Personne) contacts.get(frm.getSelectedUser());
            
            RSA temp = utilisateurCourrant.getCleRSA();
            temp.setPublicKey(ContactCourrent.getClePublic(),
                    ContactCourrent.getModulos());
            //System.out.println(utilisateurCourrant.getCleRSA().toString());
            try
            {
                RSAFormat.ecrireFichierEncrypter(fichierDestination,
                        RSAFormat.encrypteBigIntegers(RSAFormat.lireFichier(fichierSource,
                                BIT_ENCRYPTION / 8),
                                temp));
            }
            catch (IOException ex)
            {
                ex.printStackTrace();
            }
        }
        else
            JOptionPane.showMessageDialog(frm,
                    "Vous devez selectionner un contact",
                    "Erreur",
                    JOptionPane.ERROR_MESSAGE);
        
    }
    
    /**
     * Cette m�thode est ex�cut� lorsque l'on appuit sur le bouton bDecryption
     *
     * encryption_bDecryption_actionPerformed
     *
     * @param e MouseEvent
     */
    protected void encryption_bDecryption_actionPerformed(MouseEvent e)
    {
        if (fichierDestination == null || fichierSource == null)
            JOptionPane.showMessageDialog(frm,
                    "Vous devez attribuer les fichiers sources et destination",
                    "Erreur",
                    JOptionPane.ERROR_MESSAGE);
        else
        {
            try
            {
                RSAFormat.ecrireFichier(fichierDestination,
                        RSAFormat.decrypteBigIntegers(RSAFormat.lireFichierEncrypter(fichierSource,
                                BIT_ENCRYPTION / 8),
                                utilisateurCourrant.getCleRSA()));
                //System.out.println(utilisateurCourrant.getCleRSA().toString());
            }
            catch (IOException ex)
            {
                ex.printStackTrace();
            }
        }
    }
    
    /**
     * Cette m�thode est ex�cut� lorsque l'on appuit sur le bouton bRetournerCompte
     *
     * encryption_bRetournerCompte_actionPerformed
     *
     * @param e MouseEvent
     */
    protected void encryption_bRetournerCompte_actionPerformed(MouseEvent e)
    {
        this.utilisateurCourrant = null;
        frm.hide();
        frame.clear();
        frame.show();
    }
}

//classe d'actions

class GestionComptes_this_windowsClosingAdapter extends WindowAdapter
{
    private Principal adaptee;
    
    GestionComptes_this_windowsClosingAdapter(Principal adaptee)
    {
        this.adaptee = adaptee;
    }
    
    public void windowClosing(WindowEvent e)
    {
        adaptee.this_windowClosing(e);
    }
}

class GestionComptes_bConnection_MouseListener implements MouseListener
{
    
    private Principal adaptee;
    
    GestionComptes_bConnection_MouseListener(Principal adaptee)
    {
        this.adaptee = adaptee;
    }
    
    public void mouseClicked(MouseEvent e)
    {
        adaptee.bConnexion_actionPerformed(e);
    }
    
    public void mouseEntered(MouseEvent e)
    {
    }
    
    public void mouseExited(MouseEvent e)
    {
    }
    
    public void mousePressed(MouseEvent e)
    {
    }
    
    public void mouseReleased(MouseEvent e)
    {
    }
}

class GestionComptes_bAjoute_MouseListener implements MouseListener
{
    
    private Principal adaptee;
    
    GestionComptes_bAjoute_MouseListener(Principal adaptee)
    {
        this.adaptee = adaptee;
    }
    
    public void mouseClicked(MouseEvent e)
    {
        adaptee.bAjoute_mouseClicked(e);
    }
    
    public void mouseEntered(MouseEvent e)
    {
    }
    
    public void mouseExited(MouseEvent e)
    {
    }
    
    public void mousePressed(MouseEvent e)
    {
    }
    
    public void mouseReleased(MouseEvent e)
    {
    }
}

class GestionComptes_bEfface_MouseListener implements MouseListener
{
    
    private Principal adaptee;
    
    GestionComptes_bEfface_MouseListener(Principal adaptee)
    {
        this.adaptee = adaptee;
    }
    
    public void mouseClicked(MouseEvent e)
    {
        adaptee.bEfface_actionPerformed(e);
    }
    
    public void mouseEntered(MouseEvent e)
    {
    }
    
    public void mouseExited(MouseEvent e)
    {
    }
    
    public void mousePressed(MouseEvent e)
    {
    }
    
    public void mouseReleased(MouseEvent e)
    {
    }
}

class GestionComptes_bQuitter_MouseListener implements MouseListener
{
    private Principal adaptee;
    
    GestionComptes_bQuitter_MouseListener(Principal adaptee)
    {
        this.adaptee = adaptee;
    }
    
    public void mouseClicked(MouseEvent e)
    {
        adaptee.bQuitter_actionPerformed(e);
    }
    
    public void mouseEntered(MouseEvent e)
    {
    }
    
    public void mouseExited(MouseEvent e)
    {
    }
    
    public void mousePressed(MouseEvent e)
    {
    }
    
    public void mouseReleased(MouseEvent e)
    {
    }
}

class GestionComptes_bSupprimer_MouseListener implements MouseListener
{
    private Principal adaptee;
    
    GestionComptes_bSupprimer_MouseListener(Principal adaptee)
    {
        this.adaptee = adaptee;
    }
    
    public void mouseClicked(MouseEvent e)
    {
        adaptee.bSupprimer_actionPerformed(e);
    }
    
    public void mouseEntered(MouseEvent e)
    {
    }
    
    public void mouseExited(MouseEvent e)
    {
    }
    
    public void mousePressed(MouseEvent e)
    {
    }
    
    public void mouseReleased(MouseEvent e)
    {
    }
}

class AjoutContact_bAjouter_MouseListener

implements MouseListener
{
    private Principal adaptee;
    
    AjoutContact_bAjouter_MouseListener(Principal adaptee)
    {
        this.adaptee = adaptee;
    }
    
    public void mouseClicked(MouseEvent e)
    {
        adaptee.AjoutContact_bAjouter_actionPerformed(e);
    }
    
    public void mouseEntered(MouseEvent e)
    {
    }
    
    public void mouseExited(MouseEvent e)
    {
    }
    
    public void mousePressed(MouseEvent e)
    {
    }
    
    public void mouseReleased(MouseEvent e)
    {
    }
}

class Encryption_bSource_MouseListener implements MouseListener
{
    private Principal adaptee;
    
    Encryption_bSource_MouseListener(Principal adaptee)
    {
        this.adaptee = adaptee;
    }
    
    public void mouseClicked(MouseEvent e)
    {
        adaptee.encryption_bSource_actionPerformed(e);
    }
    
    public void mouseEntered(MouseEvent e)
    {
    }
    
    public void mouseExited(MouseEvent e)
    {
    }
    
    public void mousePressed(MouseEvent e)
    {
    }
    
    public void mouseReleased(MouseEvent e)
    {
    }
}

class Encryption_bDestination_MouseListener implements MouseListener
{
    private Principal adaptee;
    
    Encryption_bDestination_MouseListener(Principal adaptee)
    {
        this.adaptee = adaptee;
    }
    
    public void mouseClicked(MouseEvent e)
    {
        adaptee.encryption_bDestination_actionPerformed(e);
    }
    
    public void mouseEntered(MouseEvent e)
    {
    }
    
    public void mouseExited(MouseEvent e)
    {
    }
    
    public void mousePressed(MouseEvent e)
    {
    }
    
    public void mouseReleased(MouseEvent e)
    {
    }
}

class Encryption_bEncryption_MouseListener implements MouseListener
{
    private Principal adaptee;
    
    Encryption_bEncryption_MouseListener(Principal adaptee)
    {
        this.adaptee = adaptee;
    }
    
    public void mouseClicked(MouseEvent e)
    {
        adaptee.encryption_bEcryption_actionPerformed(e);
    }
    
    public void mouseEntered(MouseEvent e)
    {
    }
    
    public void mouseExited(MouseEvent e)
    {
    }
    
    public void mousePressed(MouseEvent e)
    {
    }
    
    public void mouseReleased(MouseEvent e)
    {
    }
}

class Encryption_bDecryption_MouseListener implements MouseListener
{
    private Principal adaptee;
    
    Encryption_bDecryption_MouseListener(Principal adaptee)
    {
        this.adaptee = adaptee;
    }
    
    public void mouseClicked(MouseEvent e)
    {
        adaptee.encryption_bDecryption_actionPerformed(e);
    }
    
    public void mouseEntered(MouseEvent e)
    {
    }
    
    public void mouseExited(MouseEvent e)
    {
    }
    
    public void mousePressed(MouseEvent e)
    {
    }
    
    public void mouseReleased(MouseEvent e)
    {
    }
}

class Encryption_bRetournerCompte_MouseListener implements MouseListener
{
    private Principal adaptee;
    
    Encryption_bRetournerCompte_MouseListener(Principal adaptee)
    {
        this.adaptee = adaptee;
    }
    
    public void mouseClicked(MouseEvent e)
    {
        adaptee.encryption_bRetournerCompte_actionPerformed(e);
    }
    
    public void mouseEntered(MouseEvent e)
    {
    }
    
    public void mouseExited(MouseEvent e)
    {
    }
    
    public void mousePressed(MouseEvent e)
    {
    }
    
    public void mouseReleased(MouseEvent e)
    {
    }
}
