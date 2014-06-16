package iuy.Interface;

import iuy.util.OutilsWindow;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class AjoutContact
    extends JDialog
{
  private JPanel pCentre = new JPanel();
  private BorderLayout borderLayout1 = new BorderLayout();
  private JPanel jPanel1 = new JPanel();
  private BorderLayout borderLayout2 = new BorderLayout();
  private JPanel jPanel2 = new JPanel();
  private JButton bAnnuler = new JButton();
  private JButton bAjouter = new JButton();
  private JPanel jPanel3 = new JPanel();
  private GridBagLayout gridBagLayout1 = new GridBagLayout();
  private JTextArea tModulos = new JTextArea();
  private JLabel lModulos = new JLabel();
  private JTextArea tCleePublic = new JTextArea();
  private JLabel lCleePublic = new JLabel();
  private JTextField tNom = new JTextField();
  private JLabel lNom = new JLabel();
  private int valeurRetour = 0;

  public static final int ERREUR = -1;
  public static final int PAS_INITIALISER = 0;
  public static final int AUCUNE_ERREUR = 1;

  public AjoutContact(Frame frame, String title, boolean modal)
  {
    super(frame, title, modal);
    try
    {
      jbInit();
      OutilsWindow.centrer(this);
      pack();
      show();
      hide();
      dispose();
    }
    catch (Exception ex)
    {
      ex.printStackTrace();
    }
  }

  public String getCleePublic()
  {
    return tCleePublic.getText().trim();
  }

  public String getCleeModulo()
  {

    return tModulos.getText().trim();
  }

  public String getNom()
  {
    return tNom.getText();
  }

  public void addBAnnulerMouseListener(MouseListener e)
  {
    bAnnuler.addMouseListener(e);
  }

  public void addBAjouterMouseListener(MouseListener e)
  {
    bAjouter.addMouseListener(e);
  }

  public AjoutContact()
  {
    this(null, "", false);
  }

  private void jbInit() throws Exception
  {
    pCentre.setLayout(borderLayout1);
    jPanel1.setLayout(borderLayout2);
    bAnnuler.setText("Annuler");
    bAnnuler.addActionListener(new AjoutContact_bAnnuler_actionAdapter(this));
    bAjouter.setText("Ajouter");
    bAjouter.addActionListener(new AjoutContact_bAjouter_actionAdapter(this));
    jPanel3.setLayout(gridBagLayout1);
    tModulos.setBorder(BorderFactory.createLineBorder(Color.black));
    tModulos.setPreferredSize(new Dimension(200, 100));
    tModulos.setText("");
    tModulos.setLineWrap(true);
    lModulos.setText("Modulos");
    tCleePublic.setBorder(BorderFactory.createLineBorder(Color.black));
    tCleePublic.setOpaque(true);
    tCleePublic.setPreferredSize(new Dimension(200, 100));
    tCleePublic.setText("");
    tCleePublic.setLineWrap(true);
    lCleePublic.setText("Cl�e public");
    this.setModal(true);
    this.setResizable(false);
    this.setTitle("Nouveau contact");
    tNom.setText("");
    tNom.setColumns(10);
    lNom.setText("Nom");
    getContentPane().add(pCentre);
    pCentre.add(jPanel3, BorderLayout.NORTH);
    this.getContentPane().add(jPanel1, BorderLayout.SOUTH);
    jPanel1.add(jPanel2, BorderLayout.EAST);
    jPanel2.add(bAjouter, null);
    jPanel2.add(bAnnuler, null);
    jPanel3.add(tModulos, new GridBagConstraints(2, 0, 1, 1, 0.0, 0.0
                                                 , GridBagConstraints.CENTER,
                                                 GridBagConstraints.NONE,
                                                 new Insets(0, 0, 1, 0), 0, 0));

    jPanel3.add(lModulos, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0
                                                 , GridBagConstraints.CENTER,
                                                 GridBagConstraints.NONE,
                                                 new Insets(0, 0, 0, 0), 0, 0));

    jPanel3.add(tCleePublic, new GridBagConstraints(2, 1, 1, 1, 0.0, 0.0,
        GridBagConstraints.CENTER,
        GridBagConstraints.NONE,
        new Insets(1, 0, 0, 0), 0, 0));
    jPanel3.add(lCleePublic, new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0,
        GridBagConstraints.CENTER,
        GridBagConstraints.NONE,
        new Insets(0, 0, 0, 0), 0, 0));
    jPanel3.add(tNom, new GridBagConstraints(2, 2, 1, 1, 0.0, 0.0
                                             , GridBagConstraints.CENTER,
                                             GridBagConstraints.NONE,
                                             new Insets(0, 0, 0, 0), 0, 0));
    jPanel3.add(lNom, new GridBagConstraints(1, 2, 1, 1, 0.0, 0.0
                                             , GridBagConstraints.CENTER,
                                             GridBagConstraints.NONE,
                                             new Insets(0, 0, 0, 0), 0, 0));

  }

//Ajout d'un contact
  protected void bAjouter_actionPerformed(ActionEvent e)
  {
//validation
    if (tNom.getText() != null && tNom.getText().compareTo("") != 0 &&
        tCleePublic.getText() != null &&
        tCleePublic.getText().compareTo("") != 0 && tModulos.getText() != null &&
        tModulos.getText().compareTo("") != 0)
    {
      valeurRetour = 1;
      hide();
    }
    else
      JOptionPane.showMessageDialog(null, "Vous devez remplir tout les champs",
                                    "Vous devez remplir tout les champs",
                                    JOptionPane.ERROR_MESSAGE);
  }

  protected void bAnnuler_actionPerformed(ActionEvent e)
  {
    valeurRetour = -1;
    hide();
  }

  public int getValeurRetour()
  {
    return valeurRetour;
  }
}

class AjoutContact_bAjouter_actionAdapter
    implements ActionListener
{
  AjoutContact adaptee;

  AjoutContact_bAjouter_actionAdapter(AjoutContact adaptee)
  {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e)
  {
    adaptee.bAjouter_actionPerformed(e);
  }
}

class AjoutContact_bAnnuler_actionAdapter
    implements ActionListener
{
  AjoutContact adaptee;

  AjoutContact_bAnnuler_actionAdapter(AjoutContact adaptee)
  {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e)
  {
    adaptee.bAnnuler_actionPerformed(e);
  }
}
