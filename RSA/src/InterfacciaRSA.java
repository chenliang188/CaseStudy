import java.math.BigInteger;
import java.util.StringTokenizer;

import javax.swing.JOptionPane;
import javax.swing.event.DocumentListener;

import algoritmoRSA.LucaBariani.Crittografia.RSA;

/*
 * InterfacciaRSA.java
 *
 * Created on 11 ottobre 2001
 */

/**
 *  Questa classe crea una interfaccia grafica come esempio di applicazione della libreria LucaBariani.Crittografia.RSA
 *  <a>
 *  In questa interfaccia grafica � possibile generare una chiave RSA con il numero di bit desiderato, codificare un
 *  qualsiasi messaggio di testo, vedere e modificare il messaggio codificato, infine decodificarlo . <a>
 *
 *  <b> Le possibilit� dell'interfaccia sono: </b> <a>
 *
 *   - si pu� generare una coppia di chiavi RSA a partire da numeri primi aventi numero di bit arbitrario (questo
 *     numero di bit deve essere > 9, per avere comodamente numeri primi distinti) <a>
 *   - si pu� immettere un testo e codificarlo con la chiave di cifratura <a>
 *   - si visualizza il messaggio codificato, lo si pu� decofificare con la chiave di decifratura <a>
 *   - le chiavi di cifratura e defifratura sono arbitrariamente modificabili <a>
 *   - il messaggio codificato � arbitrariamente modificabile <a>
 *
 *   <b> Le prove cosigliate sono le seguenti: </b> <a>
 *
 *   - generare chiavi con dimensione diverse <a>
 *   - fissata una coppia di chiavi, codicifare e decodificare un messaggio immesso: si ottiene il messaggio di partenza <a>
 *   - fissata una coppia di chiavi, codificare un messaggio immesso, alterare il messaggio codificato (anche solo di
 *     qualche bit, come cambiare un 1 in 0 e cos� via) e decodificare: si ottiene un messaggio diverso da quello di partenza
 *     <a>
 *   - generare una coppia di chiavi, alterare la chiave di cifratura e/o qualla di decifratura (in modo tale che il
 *     prodotto delle due chiavi modulo phiN sia diverso da 1); codificare e decodificare un messaggio immesso: si
 *     ottiene un messaggio che non ha nulla a che fare con quello di partenza <a>
 *
 *   <b> Bug noti:</b> <a>
 *   - la codifica/decodifica di messaggi con caratteri particolari tipo "�","�", "�"... spesso non genera un messaggio
 *      finale uguale a quello iniziale
 *
 *
 *
 * @author  Luca Bariani  (LucaBariani@yahoo.com)
 * @version 1.0  ottobre 2001
 *
 */

/*
    l'interfaccia grafica � stata realizzata utilizzando gli strumenti visuali di Forte versione 3, per questo motivo
 *  tra i sorgenti � allegato il file .form
 *
 *  conseguenza di tale utilizzo � la totale assenza di commenti per tutti gli aspetti legati alla grafica, il cui
 *  codice � stato generato in modo automatico
 */

public class InterfacciaRSA extends javax.swing.JFrame {
    
    /** Creates new form InterfacciaRSA */
    public InterfacciaRSA() {
        initComponents();
        
        /* codice aggiunto a mano */
        
        ascoltatoreChiaveCifratura = new ListenerChiaveCifratura();
        ascoltatoreChiaveDecifratura = new ListenerChiaveDecifratura();
        
         /* le due classi ListerChiave... sono definite in fondo al file*/
        
                 /* listener del campo di testo*/
        campoChiaveCifratura.getDocument().addDocumentListener(ascoltatoreChiaveCifratura);
        campoChiaveDecifratura.getDocument().addDocumentListener(ascoltatoreChiaveDecifratura);
        
        /* fine codice aggiunto a mano*/
        
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    private void initComponents() {//GEN-BEGIN:initComponents
        pannelloParametri = new javax.swing.JPanel();
        pannelloPQ = new javax.swing.JPanel();
        etichettaImmissionePQ = new javax.swing.JLabel();
        campoImmissionePQ = new javax.swing.JTextField();
        tastoGeneraKeyRSA = new javax.swing.JButton();
        pannelloN = new javax.swing.JPanel();
        etichettaN = new javax.swing.JLabel();
        campoN = new javax.swing.JTextField();
        pannelloPhiN = new javax.swing.JPanel();
        etichettaPhiN = new javax.swing.JLabel();
        campoPhiN = new javax.swing.JTextField();
        pannelloChiaveCifratura = new javax.swing.JPanel();
        etichettaChiaveCifratura = new javax.swing.JLabel();
        campoChiaveCifratura = new javax.swing.JTextField();
        pannelloChiaveDecifratura = new javax.swing.JPanel();
        etichettaChiaveDecifratura = new javax.swing.JLabel();
        campoChiaveDecifratura = new javax.swing.JTextField();
        pannelloProdottoChiavi = new javax.swing.JPanel();
        etichettaProdotto = new javax.swing.JLabel();
        campoProdottoChiavi = new javax.swing.JTextField();
        pannelloTestoIniziale = new javax.swing.JPanel();
        pannelloWelcomeTestoIniziale = new javax.swing.JPanel();
        sepatoreSinistro = new javax.swing.JSeparator();
        etichettaWelcomeTestoIniziale = new javax.swing.JLabel();
        separatoreCentrale = new javax.swing.JSeparator();
        tastoCodifica = new javax.swing.JButton();
        separatoreDestro = new javax.swing.JSeparator();
        pannelloScorrimentoTestoIniziale = new javax.swing.JScrollPane();
        areaTestoIniziale = new javax.swing.JTextArea();
        pannelloTestoCodificato = new javax.swing.JPanel();
        pannelloWelcomeTestoCodificato = new javax.swing.JPanel();
        jSeparator3 = new javax.swing.JSeparator();
        etichettaWelcomeTestoCodificato = new javax.swing.JLabel();
        jSeparator4 = new javax.swing.JSeparator();
        tastoDecodifica = new javax.swing.JButton();
        jSeparator5 = new javax.swing.JSeparator();
        pannelloScorrimentoTestoCodificato = new javax.swing.JScrollPane();
        areaTestoCodificato = new javax.swing.JTextArea();
        pannelloTestoDecodificato = new javax.swing.JPanel();
        pannelloWelcomeTestoDecodificato = new javax.swing.JPanel();
        jSeparator6 = new javax.swing.JSeparator();
        etichettaWelcomeTestoDecodificato = new javax.swing.JLabel();
        jSeparator7 = new javax.swing.JSeparator();
        pannelloScorrimentoTestoDecodificato = new javax.swing.JScrollPane();
        areaTestoDecodificato = new javax.swing.JTextArea();
        
        getContentPane().setLayout(new javax.swing.BoxLayout(getContentPane(), javax.swing.BoxLayout.Y_AXIS));
        
        setTitle("R.S.A.");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                exitForm(evt);
            }
        });
        
        pannelloParametri.setLayout(new javax.swing.BoxLayout(pannelloParametri, javax.swing.BoxLayout.Y_AXIS));
        
        pannelloPQ.setLayout(new javax.swing.BoxLayout(pannelloPQ, javax.swing.BoxLayout.X_AXIS));
        
        etichettaImmissionePQ.setText("Immetti la dimensione in bit dei numeri primi P e Q da generare ");
        pannelloPQ.add(etichettaImmissionePQ);
        
        campoImmissionePQ.setColumns(5);
        campoImmissionePQ.setText("32");
        pannelloPQ.add(campoImmissionePQ);
        
        tastoGeneraKeyRSA.setText("genera chiave RSA");
        tastoGeneraKeyRSA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tastoGeneraKeyRSAActionPerformed(evt);
            }
        });
        
        pannelloPQ.add(tastoGeneraKeyRSA);
        
        pannelloParametri.add(pannelloPQ);
        
        pannelloN.setLayout(new javax.swing.BoxLayout(pannelloN, javax.swing.BoxLayout.X_AXIS));
        
        etichettaN.setText("N=P*Q ");
        pannelloN.add(etichettaN);
        
        campoN.setEditable(false);
        campoN.setColumns(60);
        campoN.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        pannelloN.add(campoN);
        
        pannelloParametri.add(pannelloN);
        
        pannelloPhiN.setLayout(new javax.swing.BoxLayout(pannelloPhiN, javax.swing.BoxLayout.X_AXIS));
        
        etichettaPhiN.setText("Phi(N) ");
        pannelloPhiN.add(etichettaPhiN);
        
        campoPhiN.setEditable(false);
        campoPhiN.setColumns(60);
        campoPhiN.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        pannelloPhiN.add(campoPhiN);
        
        pannelloParametri.add(pannelloPhiN);
        
        pannelloChiaveCifratura.setLayout(new javax.swing.BoxLayout(pannelloChiaveCifratura, javax.swing.BoxLayout.X_AXIS));
        
        etichettaChiaveCifratura.setText("Chiave di cifratura ");
        pannelloChiaveCifratura.add(etichettaChiaveCifratura);
        
        campoChiaveCifratura.setColumns(54);
        campoChiaveCifratura.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        campoChiaveCifratura.setEnabled(false);
        pannelloChiaveCifratura.add(campoChiaveCifratura);
        
        pannelloParametri.add(pannelloChiaveCifratura);
        
        pannelloChiaveDecifratura.setLayout(new javax.swing.BoxLayout(pannelloChiaveDecifratura, javax.swing.BoxLayout.X_AXIS));
        
        etichettaChiaveDecifratura.setText("Chiave di decifratura ");
        pannelloChiaveDecifratura.add(etichettaChiaveDecifratura);
        
        campoChiaveDecifratura.setColumns(52);
        campoChiaveDecifratura.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        campoChiaveDecifratura.setEnabled(false);
        pannelloChiaveDecifratura.add(campoChiaveDecifratura);
        
        pannelloParametri.add(pannelloChiaveDecifratura);
        
        pannelloProdottoChiavi.setLayout(new javax.swing.BoxLayout(pannelloProdottoChiavi, javax.swing.BoxLayout.X_AXIS));
        
        etichettaProdotto.setText("Prodotto chiavi modulo Phi(N) ");
        pannelloProdottoChiavi.add(etichettaProdotto);
        
        campoProdottoChiavi.setEditable(false);
        campoProdottoChiavi.setColumns(48);
        campoProdottoChiavi.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        pannelloProdottoChiavi.add(campoProdottoChiavi);
        
        pannelloParametri.add(pannelloProdottoChiavi);
        
        getContentPane().add(pannelloParametri);
        
        pannelloTestoIniziale.setLayout(new javax.swing.BoxLayout(pannelloTestoIniziale, javax.swing.BoxLayout.Y_AXIS));
        
        pannelloWelcomeTestoIniziale.setLayout(new javax.swing.BoxLayout(pannelloWelcomeTestoIniziale, javax.swing.BoxLayout.X_AXIS));
        
        pannelloWelcomeTestoIniziale.add(sepatoreSinistro);
        
        etichettaWelcomeTestoIniziale.setText("Immetti il testo da decifrare ");
        etichettaWelcomeTestoIniziale.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        pannelloWelcomeTestoIniziale.add(etichettaWelcomeTestoIniziale);
        
        pannelloWelcomeTestoIniziale.add(separatoreCentrale);
        
        tastoCodifica.setText("Codifica con la chiave di cifratura");
        tastoCodifica.setEnabled(false);
        tastoCodifica.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tastoCodificaActionPerformed(evt);
            }
        });
        
        pannelloWelcomeTestoIniziale.add(tastoCodifica);
        
        pannelloWelcomeTestoIniziale.add(separatoreDestro);
        
        pannelloTestoIniziale.add(pannelloWelcomeTestoIniziale);
        
        areaTestoIniziale.setLineWrap(true);
        areaTestoIniziale.setColumns(50);
        areaTestoIniziale.setRows(4);
        areaTestoIniziale.setEnabled(false);
        pannelloScorrimentoTestoIniziale.setViewportView(areaTestoIniziale);
        
        pannelloTestoIniziale.add(pannelloScorrimentoTestoIniziale);
        
        getContentPane().add(pannelloTestoIniziale);
        
        pannelloTestoCodificato.setLayout(new javax.swing.BoxLayout(pannelloTestoCodificato, javax.swing.BoxLayout.Y_AXIS));
        
        pannelloWelcomeTestoCodificato.setLayout(new javax.swing.BoxLayout(pannelloWelcomeTestoCodificato, javax.swing.BoxLayout.X_AXIS));
        
        pannelloWelcomeTestoCodificato.add(jSeparator3);
        
        etichettaWelcomeTestoCodificato.setText("Testo codificato: se vuoi modificalo");
        etichettaWelcomeTestoCodificato.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        pannelloWelcomeTestoCodificato.add(etichettaWelcomeTestoCodificato);
        
        pannelloWelcomeTestoCodificato.add(jSeparator4);
        
        tastoDecodifica.setText("Decodifica con la chiave di decifratura");
        tastoDecodifica.setEnabled(false);
        tastoDecodifica.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tastoDecodificaActionPerformed(evt);
            }
        });
        
        pannelloWelcomeTestoCodificato.add(tastoDecodifica);
        
        pannelloWelcomeTestoCodificato.add(jSeparator5);
        
        pannelloTestoCodificato.add(pannelloWelcomeTestoCodificato);
        
        areaTestoCodificato.setLineWrap(true);
        areaTestoCodificato.setColumns(50);
        areaTestoCodificato.setRows(10);
        areaTestoCodificato.setEnabled(false);
        pannelloScorrimentoTestoCodificato.setViewportView(areaTestoCodificato);
        
        pannelloTestoCodificato.add(pannelloScorrimentoTestoCodificato);
        
        getContentPane().add(pannelloTestoCodificato);
        
        pannelloTestoDecodificato.setLayout(new javax.swing.BoxLayout(pannelloTestoDecodificato, javax.swing.BoxLayout.Y_AXIS));
        
        pannelloWelcomeTestoDecodificato.setLayout(new javax.swing.BoxLayout(pannelloWelcomeTestoDecodificato, javax.swing.BoxLayout.X_AXIS));
        
        pannelloWelcomeTestoDecodificato.add(jSeparator6);
        
        etichettaWelcomeTestoDecodificato.setText("Testo decodificato: confrontalo con quello iniziale");
        etichettaWelcomeTestoDecodificato.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        pannelloWelcomeTestoDecodificato.add(etichettaWelcomeTestoDecodificato);
        
        pannelloWelcomeTestoDecodificato.add(jSeparator7);
        
        pannelloTestoDecodificato.add(pannelloWelcomeTestoDecodificato);
        
        areaTestoDecodificato.setLineWrap(true);
        areaTestoDecodificato.setColumns(50);
        areaTestoDecodificato.setRows(4);
        areaTestoDecodificato.setEnabled(false);
        pannelloScorrimentoTestoDecodificato.setViewportView(areaTestoDecodificato);
        
        pannelloTestoDecodificato.add(pannelloScorrimentoTestoDecodificato);
        
        getContentPane().add(pannelloTestoDecodificato);
        
        pack();
    }//GEN-END:initComponents
    
    /* fine codice generato automaticamente*/
    
    /**
     * metodo che implementa l'azione di risposta alla pressione del tasto Decodifica
     */
    
    private void tastoDecodificaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tastoDecodificaActionPerformed
        // Add your handling code here:
        
        try {
            String testoCodificato = areaTestoCodificato.getText();
                /* lettura di ci� che � stato immesso in areaTestoCodificato */
            
            StringTokenizer sottoStringhe = new StringTokenizer(testoCodificato, " ", false);
                /* con la classe StringTokenizer � possibile estrarre da testoCodificato tutte le
                   sottostringhe separate da uno spazio*/
            
            int numeroToken = sottoStringhe.countTokens(); /* numero di sottostringhe in testoCodificato*/
            
            BigInteger[] messaggioCodificatoInBigInteger = new BigInteger[numeroToken];
                /* vettore di BigInteger, uno per ogni sottostringa */
            
            byte[] messaggioDecrInByte;      // vettori di appoggio
            byte[][] messaggioDecrInBlocchi;
            
            int i; // contatore
            
            
            for (i = 0; i < numeroToken; i++) {
                
                messaggioCodificatoInBigInteger[i] = new BigInteger(sottoStringhe.nextToken());
                
                /* da ogni sottostringa viene generato un BigInteger, se la conversione non va a buon
                   fine viene lanciata una eccezione, rilevata dal blocco successivo*/
            }
            
            /* a questo punto si arriva solo se non vengono generate eccezioni nel ciclo precedente */
            
            messaggioDecrInBlocchi = RSA.decodeRSA(messaggioCodificatoInBigInteger,nedphi[0],nedphi[2]);
            
            /* decodifica RSA: messaggioCodifcatoInBigInteger: messaggio da decodificare
                               nedphi[0] : modulo della cifratura
                               nedphi[2] : chiave di decifratura                         */
            
            messaggioDecrInByte = RSA.blocchiToByte(messaggioDecrInBlocchi);
            /* conversione da un array di array di byte ad un array semplice di byte*/
            
            String testoDecodificato = new String(messaggioDecrInByte);
             /* conversione da array di byte a stringa*/
            
            areaTestoDecodificato.setText(testoDecodificato);
              /* aggiorna il contenuto di areaDiTesto */
        }
        catch(NumberFormatException e) {
             /* questa eccezione viene lanciata dal costruttore di BigInteger nel caso
              in cui le stringa immesse siano mal formattata o contengano caratteri non
              numerici */
            
            JOptionPane.showMessageDialog(null, "Il messaggio da decodificare deve essere composto unicamente da numeri", "Errore", JOptionPane.ERROR_MESSAGE);
            // finestra di dialogo che segnale l'errore
        }
        
    }//GEN-LAST:event_tastoDecodificaActionPerformed
    
    /**
     * metodo che implementa l'azione di risposta alla pressione del tasto Codifica
     */
    
    private void tastoCodificaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tastoCodificaActionPerformed
        // Add your handling code here:
        
        //String testoIniziale = new String(areaTestoIniziale.getText());
        
        
        byte[] testoInizialeInByte = areaTestoIniziale.getText().getBytes();
        /* genera un vettore di byte corrispondente al testo contenuto in areaTestoIniziale
            implicitamente vi � il passaggio per il tipo String  */
        
        int numeroByte = (numeroBit-1) / 8;
          /* dimensione di un blocco di byte; il numero corrispondente ad un blocco deve essere coprimo con
             i numeri P e Q generati (che hanno almeno numeroBit bit significativi), se un blocco � composto da
             (numeroBit-1) / 8 byte, allora ogni numero corrispondente ad un blocco � minore di P e Q, poich�
             P e Q sono primi, allora la coprimalit� richiesta � garantita  */
        
        byte[][] testoInizialeInBlocchi = RSA.byteToBlocchi(testoInizialeInByte,numeroByte);
          /* conversione da un array semplice di byte a un array di array[numeroByte] di byte */
        
        BigInteger[] mesCriptato = RSA.encodeRSA(testoInizialeInBlocchi,nedphi[0],nedphi[1]);
          /* codifica RSA
             testoInizialeInBlocchi : messagio da codificare
             nedphi[0] : modulo della cifratura
             nedphi[1] : chiave di cifratura           */
        
        
        areaTestoCodificato.setText(""); // cancellazione del testo contenuto in areTestoCodificato
        
        int i; // contatore
        
        for (i=0; i < mesCriptato.length; i++) {
            // ciclo per ogni blocco cifrato
            areaTestoCodificato.append(mesCriptato[i].toString());
            // scrittura su araeTestoCodicato
            areaTestoCodificato.append("   "); // formattazione stringa
        }
        
        tastoDecodifica.setEnabled(true);
        areaTestoCodificato.setEnabled(true);
        /* si abilitano tastoDecodifica e areaTestoCodificato, affinch� possano essere selezionati */
    }//GEN-LAST:event_tastoCodificaActionPerformed
    
    
    /**
     * metodo che implementa l'azione di risposta alla pressione del tasto generaChiaveRSA
     */
    
    private void tastoGeneraKeyRSAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tastoGeneraKeyRSAActionPerformed
        // Add your handling code here:
        try {
            Integer intero = new Integer(campoImmissionePQ.getText());
          /* genera un Intero a partire dal testo contenuto in campoImmissionePQ */
            
            numeroBit = intero.intValue();
            
            if (numeroBit < 10) {
                
                /* si impone che il numero di bit sia >= 10, ci� per avere una buona generazione di
                   numeri primi (con 9 bit si possono avere 512 numeri interi, tra essi ci sono
                   pochi numeri primi)  */
                
                JOptionPane.showMessageDialog(null, "Il numero di bit immesso deve essere > 9", "Errore", JOptionPane.ERROR_MESSAGE);
                // finestra di dialogo che segnale l'errore
                
                campoImmissionePQ.setText("");
                // cancella il testo immesso
                
                return;
            }
            
            nedphi = RSA.generateRSAKeys(numeroBit,1e-40);
            /* generazione della chiave RSA, i numeri primi generati hanno almeno numeroBit bit,
               inoltre i numeri primi generati sono tali con probabilit� massima di errore 10^-40*/
            
            /*
               nedphi[0] : modulo N utilizato nell'algoritmo
               nedphi[1] : chiave di cifratura
               nedphi[2] : chiave di decifratura
               nedphi[3] : funzione phi di Eulero calcolata in N
             */
            
            campoN.setText(nedphi[0].toString()); // visualizzazione N
            
            
            /* campoChiaveCifratura e campoChiaveDecifratura hanno un Listener associato, quindi prima di
               modificare il testo contenuto in tali campi, occorre rimuovere il Listener, per poi
               ripristinarlo subito dopo */
            
            campoChiaveCifratura.getDocument().removeDocumentListener(ascoltatoreChiaveCifratura);
            
            campoChiaveCifratura.setText(nedphi[1].toString());
              /* visualizzazione chiave cifratura */
            
            campoChiaveCifratura.getDocument().addDocumentListener(ascoltatoreChiaveCifratura);
            
            campoChiaveDecifratura.getDocument().removeDocumentListener(ascoltatoreChiaveDecifratura);
            
            campoChiaveDecifratura.setText(nedphi[2].toString());
              /* visualizzazione chiave decifratura */
            
            campoChiaveDecifratura.getDocument().addDocumentListener(ascoltatoreChiaveDecifratura);
            
            campoPhiN.setText(nedphi[3].toString()); // visualizzazione phiN
            
            BigInteger prodotto=nedphi[1].multiply(nedphi[2]).remainder(nedphi[3]);
              /* prodotto delle chiavi modulo phiN: prodotto= e * d mod phiN */
            
            campoProdottoChiavi.setText(prodotto.toString()); // visualizzazione prodotto
            
            /* dopo aver generato una coppia di chiavi RSA � possibile iteragire con campoChiaveCifratura,
               campoChiaeDecifratura, tastoCodifica, areaTestoIniziale */
            
            campoChiaveCifratura.setEnabled(true);
            campoChiaveDecifratura.setEnabled(true);
            tastoCodifica.setEnabled(true);
            areaTestoIniziale.setEnabled(true);
            
        }
        
        
        catch(NumberFormatException e) {
          /* questa eccezione viene lanciata dal costruttore di Integer nel caso
              in cui la stringa immessa sia mal formattata o contenga caratteri non
              numerici */
            
            campoImmissionePQ.setText("");
            // cancella il testo immesso
            JOptionPane.showMessageDialog(null, "Numero di bit immesso non valido", "Errore", JOptionPane.ERROR_MESSAGE);
            // finestra di dialogo che segnale l'errore
        }
    }//GEN-LAST:event_tastoGeneraKeyRSAActionPerformed
    
    /** Exit the Application */
    private void exitForm(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_exitForm
        System.exit(0);
    }//GEN-LAST:event_exitForm
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        new InterfacciaRSA().show();
    }
    
    

   /* CLASSI PRIVATE */
    
    /** classe la cui istanza che ha funzione di Listener del documento contenuto 
       in campoChiaveCifratura */
    
    private class ListenerChiaveCifratura implements DocumentListener{
         /* metodi della intefaccia DocumentListener */
        
        public void insertUpdate(javax.swing.event.DocumentEvent e) {
            aggiornamento();
        }
        
        public void removeUpdate(javax.swing.event.DocumentEvent e) {
            aggiornamento();
        }
        
        public void changedUpdate(javax.swing.event.DocumentEvent e) {
        }
        
        /**
         * metodo che di fatto viene chiamato ad ogni cambiamento in campoChiaveCifratura;
         * vengono letti i cambiamenti, fatti i relativi controlli e si aggiorna il campi
         * testuale di campoProdottoChiavi
         */
        
        public void aggiornamento() {
            try {
                
                BigInteger nuovaChiaveCifratura = new BigInteger(campoChiaveCifratura.getText());
                
          /* genera un BigInteger a partire dal testo contenuto in campoChiaveCifratura */
                
                nedphi[1] = nuovaChiaveCifratura; // aggiornamento chiave di cifratura
                
                campoProdottoChiavi.setText(nedphi[1].multiply(nedphi[2]).remainder(nedphi[3]).toString());
                /* si calcola il prodotto di chiave pubblica e privata modulo phiN, poi lo si visualizza il
                   risultato */
            }
            
            
            catch(NumberFormatException errore) {
          /* questa eccezione viene lanciata dal costruttore di BigInteger nel caso
              in cui la stringa immessa sia mal formattata o contenga caratteri non
              numerici */
                
                JOptionPane.showMessageDialog(null, "Chiave di cifratura non valida", "Errore", JOptionPane.ERROR_MESSAGE);
                // finestra di dialogo che segnale l'errore
            }
        }
    }
    
    /** classe la cui istanza che ha funzione di Listener del documento contenuto 
       in campoChiaveDecifratura */    
    
    private class ListenerChiaveDecifratura implements DocumentListener{
         /* metodi della intefaccia DocumentListener */
        
        public void insertUpdate(javax.swing.event.DocumentEvent e) {
            aggiornamento();
        }
        
        public void removeUpdate(javax.swing.event.DocumentEvent e) {
            aggiornamento();
        }
        
        public void changedUpdate(javax.swing.event.DocumentEvent e)
        { }
        
        /**
         * metodo che di fatto viene chiamato ad ogni cambiamento in campoChiaveDecifratura;
         * vengono letti i cambiamenti, fatti i relativi controlli e si aggiorna il campi
         * testuale di campoProdottoChiavi
         */
        
        public void aggiornamento() {
            try {
                
                BigInteger nuovaChiaveDecifratura = new BigInteger(campoChiaveDecifratura.getText());
          /* genera un BigInteger a partire dal testo contenuto in campoChiaveDecifratura */
                
                nedphi[2] = nuovaChiaveDecifratura;  // aggiornamento chiave di decifratura
                
                campoProdottoChiavi.setText(nedphi[1].multiply(nedphi[2]).remainder(nedphi[3]).toString());
                /* si calcola il prodotto di chiave pubblica e privata modulo phiN, poi lo si visualizza il
                   risultato */
            }
            
            
            catch(NumberFormatException errore) {
          /* questa eccezione viene lanciata dal costruttore di BigInteger nel caso
              in cui la stringa immessa sia mal formattata o contenga caratteri non
              numerici */
                
                JOptionPane.showMessageDialog(null, "Chiave di decifratura non valida", "Errore", JOptionPane.ERROR_MESSAGE);
                // finestra di dialogo che segnale l'errore
            }
        }
    }
    
   /* FINE CLASSI PRIVATE */ 
    
    
    /* DICHIARAZIONE MANUALE VARIABILI */
    
    private BigInteger[] nedphi;
             /*
               nedphi[0] : modulo N utilizato nell'algoritmo
               nedphi[1] : chiave di cifratura
               nedphi[2] : chiave di decifratura
               nedphi[3] : funzione phi di Eulero calcolata in N
              */
    private int numeroBit;
    private ListenerChiaveCifratura ascoltatoreChiaveCifratura;
    private ListenerChiaveDecifratura ascoltatoreChiaveDecifratura;
    
    /* FINE DICHIARAZIONE MANUALE VARIABILI */
    
    
    /* INIZIO DICHIARAZIONE AUTOMATICA VARIABILI */
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel pannelloParametri;
    private javax.swing.JPanel pannelloPQ;
    private javax.swing.JLabel etichettaImmissionePQ;
    private javax.swing.JTextField campoImmissionePQ;
    private javax.swing.JButton tastoGeneraKeyRSA;
    private javax.swing.JPanel pannelloN;
    private javax.swing.JLabel etichettaN;
    private javax.swing.JTextField campoN;
    private javax.swing.JPanel pannelloPhiN;
    private javax.swing.JLabel etichettaPhiN;
    private javax.swing.JTextField campoPhiN;
    private javax.swing.JPanel pannelloChiaveCifratura;
    private javax.swing.JLabel etichettaChiaveCifratura;
    private javax.swing.JTextField campoChiaveCifratura;
    private javax.swing.JPanel pannelloChiaveDecifratura;
    private javax.swing.JLabel etichettaChiaveDecifratura;
    private javax.swing.JTextField campoChiaveDecifratura;
    private javax.swing.JPanel pannelloProdottoChiavi;
    private javax.swing.JLabel etichettaProdotto;
    private javax.swing.JTextField campoProdottoChiavi;
    private javax.swing.JPanel pannelloTestoIniziale;
    private javax.swing.JPanel pannelloWelcomeTestoIniziale;
    private javax.swing.JSeparator sepatoreSinistro;
    private javax.swing.JLabel etichettaWelcomeTestoIniziale;
    private javax.swing.JSeparator separatoreCentrale;
    private javax.swing.JButton tastoCodifica;
    private javax.swing.JSeparator separatoreDestro;
    private javax.swing.JScrollPane pannelloScorrimentoTestoIniziale;
    private javax.swing.JTextArea areaTestoIniziale;
    private javax.swing.JPanel pannelloTestoCodificato;
    private javax.swing.JPanel pannelloWelcomeTestoCodificato;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JLabel etichettaWelcomeTestoCodificato;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JButton tastoDecodifica;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JScrollPane pannelloScorrimentoTestoCodificato;
    private javax.swing.JTextArea areaTestoCodificato;
    private javax.swing.JPanel pannelloTestoDecodificato;
    private javax.swing.JPanel pannelloWelcomeTestoDecodificato;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JLabel etichettaWelcomeTestoDecodificato;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JScrollPane pannelloScorrimentoTestoDecodificato;
    private javax.swing.JTextArea areaTestoDecodificato;
    // End of variables declaration//GEN-END:variables
    
    
    /* FINE DICHIARAZIONE AUTOMATICA VARIABILI */
}
