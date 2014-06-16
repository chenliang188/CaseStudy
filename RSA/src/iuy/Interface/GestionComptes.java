package iuy.Interface;

import java.awt.AWTEvent;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.MouseListener;
import java.awt.event.WindowListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class GestionComptes extends JFrame
{
    private JPanel contentPane;
    
    private BorderLayout borderLayout1 = new BorderLayout();
    
    private JPanel pCentre = new JPanel();
    
    private JPanel pBas = new JPanel();
    
    private JPanel pBasDroite = new JPanel();
    
    private BorderLayout borderLayout2 = new BorderLayout();
    
    private FlowLayout flowLayout1 = new FlowLayout();
    
    private JButton bQuitter = new JButton();
    
    private GridBagLayout gridBagLayout1 = new GridBagLayout();
    
    private JLabel lNom = new JLabel();
    
    private JTextField tNom = new JTextField();
    
    private JLabel lPass = new JLabel();
    
    private JPasswordField pMotpasse = new JPasswordField();
    
    private JButton bEfface = new JButton();
    
    private JButton bAjoute = new JButton();
    
    private JButton bConnection = new JButton();
    
    private JButton bSupprimer = new JButton();
    
    private JButton jButton1 = new JButton();
    
    //Construire le cadre
    public GestionComptes()
    {
        enableEvents(AWTEvent.WINDOW_EVENT_MASK);
        try
        {
            jbInit();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    
    //ajout d'action qui surveille la fermeture de la fen�tre (0)(0)
    public void addWindowsListenerClosing(WindowListener e)
    {
        addWindowListener(e);
    }
    
    public void clear()
    {
        tNom.setText("");
        pMotpasse.setText("");
    }
    
    public String getNom()
    {
        return tNom.getText();
    }
    
    public char[] getMotPass()
    {
        return pMotpasse.getPassword();
    }
    
    public void addBConnection(MouseListener e)
    {
        bConnection.addMouseListener(e);
    }
    
    public void addBQuitter(MouseListener e)
    {
        bQuitter.addMouseListener(e);
    }
    
    public void addBAjoute(MouseListener e)
    {
        bAjoute.addMouseListener(e);
    }
    
    public void addBEfface(MouseListener e)
    {
        bEfface.addMouseListener(e);
    }
    
    public void addBSupprimer(MouseListener e)
    {
        bSupprimer.addMouseListener(e);
    }
    
    //Initialiser le composant
    private void jbInit() throws Exception
    {
        contentPane = (JPanel) this.getContentPane();
        contentPane.setLayout(borderLayout1);
        this.setResizable(false);
        this.setSize(new Dimension(355, 157));
        this.setTitle("DarkRSA");
        pBas.setDebugGraphicsOptions(0);
        pBas.setLayout(borderLayout2);
        pBasDroite.setLayout(flowLayout1);
        bQuitter.setText("Quitter");
        pCentre.setLayout(gridBagLayout1);
        lNom.setText("Nom");
        lPass.setText("Mot de passe");
        pMotpasse.setText("");
        pMotpasse.setColumns(10);
        
        tNom.setCaretPosition(0);
        tNom.setText("");
        tNom.setColumns(9);
        bEfface.setText("Effac�");
        bAjoute.setText("Ajouter");
        bConnection.setText("Connection");
        bSupprimer.setText("Supprimer");
        jButton1.setText("jButton1");
        contentPane.add(pBas, BorderLayout.SOUTH);
        pBas.add(pBasDroite, BorderLayout.EAST);
        pBasDroite.add(bQuitter, null);
        contentPane.add(pCentre, BorderLayout.CENTER);
        pCentre.add(lNom, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(
                        0, 0, 0, 0), 0, 0));
        pCentre.add(tNom, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(
                        0, 0, 0, 0), 0, 0));
        pCentre.add(lPass, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(
                        0, 0, 0, 0), 0, 0));
        pCentre.add(pMotpasse, new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(
                        0, 0, 0, 0), 0, 0));
        pCentre.add(bEfface, new GridBagConstraints(1, 2, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(
                        0, 0, 0, 2), 0, 0));
        pCentre.add(bAjoute, new GridBagConstraints(2, 0, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(
                        0, 0, 0, 0), 0, 0));
        pCentre.add(bSupprimer, new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(
                        0, 0, 0, 0), 0, 0));
        pCentre.add(bConnection, new GridBagConstraints(2, 1, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(
                        0, 0, 0, 0), 0, 0));
        
    }
    
}
