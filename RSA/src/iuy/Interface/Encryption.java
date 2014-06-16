package iuy.Interface;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.MouseListener;
import java.awt.event.WindowListener;
import java.io.Serializable;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

//import com.borland.jbcl.layout.*;

public class Encryption extends JFrame implements Serializable
{
    //composantes graphiques de l'application
    private BorderLayout borderLayout1 = new BorderLayout();
    
    private JPanel pOuest = new JPanel();
    
    private JList lContact = new JList();
    
    private BorderLayout borderLayout2 = new BorderLayout();
    
    private Vector contacts = new Vector();
    
    private JScrollPane jScrollPane1 = new JScrollPane();
    
    private JPanel pCentre = new JPanel();
    
    private GridBagLayout gridBagLayout1 = new GridBagLayout();
    
    private JTextField tFSource = new JTextField();
    
    private JButton bParSource = new JButton();
    
    private JLabel lFSource = new JLabel();
    
    private JTextField tFDestination = new JTextField();
    
    private JLabel lFDestination = new JLabel();
    
    private JButton bParDestination = new JButton();
    
    private JPanel pEstOuest = new JPanel();
    
    private JButton bAjouter = new JButton();
    
    private JButton bEncrypter = new JButton();
    
    private JPanel pSud = new JPanel();
    
    private JPanel pCleePublic = new JPanel();
    
    private JTextArea taCleePublic = new JTextArea();
    
    private JTextArea taModulos = new JTextArea();
    
    private JPanel pEncryption = new JPanel();
    
    private JButton bDecrypter = new JButton();
    
    private int bitEncryption;
    
    private JLabel lModulos = new JLabel();
    
    private JLabel lCleePublic = new JLabel();
    
    private JButton bRetournerCompte = new JButton();
    
    private JButton bQuitter = new JButton();
    
    GridBagLayout gridBagLayout2 = new GridBagLayout();
    
    GridBagLayout gridBagLayout3 = new GridBagLayout();
    
    //pour la version 1.1 :p
    /*
      private JPopupMenu pmEncryption = new JPopupMenu();
      private JMenuItem miQuitter = new JMenuItem();
     */
    public Encryption()
    {
        try
        {
            
            jbInit();
        }
        catch (Exception exception)
        {
            exception.printStackTrace();
        }
    }
    
    public void setCleePublic(String str)
    {
        taCleePublic.setText(str);
    }
    
    public void setCleeModulos(String str)
    {
        taModulos.setText(str);
    }
    
    public void setListeUser(Hashtable ht)
    {
        Enumeration enu = ht.keys();
        contacts.removeAllElements();
        while (enu.hasMoreElements())
            contacts.add(enu.nextElement());
        
        lContact.setListData(contacts);
    }
    
    public void addBEncryptionMouseListener(MouseListener e)
    {
        bEncrypter.addMouseListener(e);
    }
    
    public void addBDecryptionMouseListener(MouseListener e)
    {
        bDecrypter.addMouseListener(e);
    }
    
    public void addBParSourceMouseListener(MouseListener e)
    {
        bParSource.addMouseListener(e);
    }
    
    public void addBParDestinationMouseListener(MouseListener e)
    {
        bParDestination.addMouseListener(e);
    }
    
    public void addBQuitterMouseListener(MouseListener e)
    {
        bQuitter.addMouseListener(e);
    }
    
    public void addBRetournerCompteMouseListener(MouseListener e)
    {
        bRetournerCompte.addMouseListener(e);
    }
    
    public void addBAjouterMouseListener(MouseListener e)
    {
        bAjouter.addMouseListener(e);
    }
    
    public void addWindowsListenerClosing(WindowListener e)
    {
        addWindowListener(e);
    }
    
    public Object getSelectedUser()
    {
        return lContact.getSelectedValue();
    }
    
    private void jbInit() throws Exception
    {
        //
        pCleePublic.setDebugGraphicsOptions(0);
        this.setResizable(false);
        this.setSize(new Dimension(645, 459));
        getContentPane().setLayout(borderLayout1);
        pOuest.setLayout(borderLayout2);
        pCentre.setLayout(gridBagLayout1);
        tFSource.setMargin(new Insets(1, 5, 2, 4));
        tFSource.setBackground(Color.white);
        tFSource.setForeground(Color.black);
        tFSource.setCaretPosition(0);
        tFSource.setDisabledTextColor(Color.lightGray);
        tFSource.setEditable(false);
        tFSource.setText("");
        tFSource.setColumns(10);
        bParSource.setText("Parcourir");
        lFSource.setToolTipText("");
        lFSource.setText("Fichier source");
        tFDestination.setBackground(Color.white);
        tFDestination.setDisabledTextColor(Color.lightGray);
        tFDestination.setEditable(false);
        tFDestination.setText("");
        tFDestination.setColumns(10);
        lFDestination.setText("Destination");
        bParDestination.setText("Parcourir");
        lContact.setMaximumSize(new Dimension(200, 0));
        lContact.setMinimumSize(new Dimension(100, 0));
        lContact.setPreferredSize(new Dimension(10, 0));
        jScrollPane1.setPreferredSize(new Dimension(100, 130));
        bAjouter.setText("Ajouter Contact");
        bEncrypter.setText("Encrypter");
        pCentre.setEnabled(true);
        pCleePublic.setLayout(gridBagLayout2);
        taCleePublic.setEnabled(true);
        taCleePublic.setMinimumSize(new Dimension(415, 75));
        taCleePublic.setPreferredSize(new Dimension(415, 50));
        taCleePublic.setDisabledTextColor(Color.darkGray);
        taCleePublic.setEditable(false);
        taCleePublic.setText("");
        taCleePublic.setLineWrap(true);
        taModulos.setDoubleBuffered(false);
        taModulos.setMinimumSize(new Dimension(415, 75));
        taModulos.setPreferredSize(new Dimension(415, 50));
        taModulos.setDisabledTextColor(Color.darkGray);
        taModulos.setEditable(false);
        taModulos.setText("");
        taModulos.setLineWrap(true);
        pCleePublic.setMinimumSize(new Dimension(400, 250));
        pCleePublic.setPreferredSize(new Dimension(425, 220));
        bDecrypter.setText("Decrypter");
        lModulos.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 11));
        lModulos.setPreferredSize(new Dimension(55, 14));
        lModulos.setText("Modulos");
        lCleePublic.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 11));
        lCleePublic.setPreferredSize(new Dimension(65, 14));
        lCleePublic.setDisplayedMnemonic('0');
        lCleePublic.setIconTextGap(4);
        lCleePublic.setText("Clee public");
        bRetournerCompte.setText("Retourner � la gestion de compte");
        bQuitter.setText("Quitter");
        pEncryption.setDebugGraphicsOptions(0);
        pEncryption.setLayout(gridBagLayout3);
        this.getContentPane().add(pOuest, BorderLayout.WEST);
        pOuest.add(jScrollPane1, BorderLayout.CENTER);
        pOuest.add(pEstOuest, BorderLayout.EAST);
        
        pCentre.add(tFSource, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(
                        0, 0, 0, 0), 0, 0));
        pCentre.add(bParSource, new GridBagConstraints(2, 0, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(
                        0, 0, 0, 0), 0, 0));
        pCentre.add(lFSource, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0,
                GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0,
                        0, 0, 0), 0, 0));
        pCentre.add(tFDestination, new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(
                        0, 0, 0, 0), 0, 0));
        pCentre.add(lFDestination, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0,
                GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0,
                        0, 0, 0), 0, 0));
        pCentre.add(bParDestination, new GridBagConstraints(2, 1, 1, 1, 0.0,
                0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE,
                new Insets(0, 0, 0, 0), 0, 0));
        pCentre.add(bEncrypter, new GridBagConstraints(1, 2, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(
                        0, 0, 0, 0), 0, 0));
        pCentre.add(bDecrypter, new GridBagConstraints(2, 2, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(
                        0, 0, 0, 0), 0, 0));
        pEncryption.add(pCleePublic, new GridBagConstraints(0, 1, 1, 1, 0.0,
                0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 0, 0), 0, 0));
        jScrollPane1.getViewport().add(lContact);
        this.getContentPane().add(pSud, BorderLayout.SOUTH);
        
        pSud.add(bRetournerCompte, null);
        pSud.add(bQuitter, null);
        this.getContentPane().add(pEncryption, BorderLayout.CENTER);
        
        pEstOuest.add(bAjouter, null);
        pCleePublic.add(taCleePublic, new GridBagConstraints(0, 1, 1, 1, 0.0,
                0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 0, 0), 0, 0));
        pCleePublic.add(taModulos, new GridBagConstraints(0, 3, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(
                        0, 0, 0, 0), 0, 0));
        pCleePublic.add(lCleePublic, new GridBagConstraints(0, 0, 1, 1, 0.0,
                0.0, GridBagConstraints.WEST, GridBagConstraints.NONE,
                new Insets(0, 0, 0, 0), 0, 0));
        pCleePublic.add(lModulos, new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0,
                GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0,
                        0, 0, 0), 0, 0));
        pEncryption.add(pCentre, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(
                        0, 0, 0, 0), 0, 0));
        
    }
    
    public void setTextSource(String str)
    {
        tFSource.setText(str);
    }
    
    public void setTextDestination(String str)
    {
        tFDestination.setText(str);
    }
    
    public void setTitre(String str)
    {
        setTitre(str);
    }
    
    public int getBitEncryption()
    {
        return bitEncryption;
    }
    
    public void setBitEncryption(int bitEncryption)
    {
        this.bitEncryption = bitEncryption;
    }
    
    //pour la version 1.1 :p
    /*  void this_mouseClicked(MouseEvent e)
      {
        if (e.getButton() == MouseEvent.BUTTON3)
          this.pmEncryption.show(this, e.getX(), e.getY());
      }*/
}
