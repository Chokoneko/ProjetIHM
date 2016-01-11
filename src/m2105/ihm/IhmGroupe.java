/*
 * Module 2105 : module IHM : Carnet d'adresse
 */
package m2105.ihm;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.PopupMenu;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashSet;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import m2105.nf.Contact;
import m2105.nf.Groupe;
import m2105.nf.Symbole;

/**
 *
 * @author IUT2
 */
public class IhmGroupe extends javax.swing.JPanel {
    /*
     * Composants graphiques constituants l'interface
     */
    private IhmLogo         ihmLogo;
    private IhmBoiteMessage ihmBM;
    private JTextField      champNom;
    private JPanel          panWorkspace, panSCENTER, panSSOUTH, panSWEST, panSEAST, panNORTH, panCENTER, panSOUTH ; 
    private JButton         btnValider, btnEffacer, btnAnnuler, btnSupprGrp ;
    private JTable          tableContacts ;
    private DefaultTableModel model;
    private JCheckBox[]      checkSymboles; 
    private HashSet<JCheckBox> listeCheckSymboles ; 
    
    
    
    /**
     * Creates new form CarnetUI
     */
    public IhmGroupe() { 
        super();
        
        initUIComponents();    
    }

    /**
     * Crée et positionne les composants graphiques constituant l'interface
     * avec : 
     *  - en haut : les informations du groupe (nom, icone, symbole,...)
     *  - au centre : la liste des membres du groupe
     *  - en bas : les boutons d'action sur le groupe
     */
    private void initUIComponents() {      
        
        
        
        //Champ texte nom groupe
        ihmBM = new IhmBoiteMessage();
        
        panWorkspace= new JPanel () ;
        
        panNORTH=new JPanel();
        panCENTER=new JPanel();
        panSOUTH=new JPanel();
        

        
        panSSOUTH=new JPanel();
        panSCENTER=new JPanel();
        panSEAST=new JPanel();
        panSWEST=new JPanel();
        
        
        panWorkspace.setLayout(new BorderLayout());
        panWorkspace.add(panNORTH,BorderLayout.NORTH);
        panWorkspace.add(panCENTER,BorderLayout.CENTER);
        panWorkspace.add(panSOUTH,BorderLayout.SOUTH);
        
        panNORTH.setLayout(new BorderLayout());
        panSOUTH.setLayout(new BorderLayout());
        
        panNORTH.add(new JLabel("Nom :"),BorderLayout.WEST);
        champNom = new JTextField(30);
        panNORTH.add(champNom, BorderLayout.CENTER);
        
        panSOUTH.add(panSSOUTH, BorderLayout.SOUTH);
        panSOUTH.add(panSCENTER, BorderLayout.CENTER);
        panSOUTH.add(panSEAST, BorderLayout.EAST);
        panSOUTH.add(panSWEST, BorderLayout.WEST);
        
        // Canvas zone de dessin south/center
        ihmLogo = new IhmLogo();        
        
        panSCENTER.add(ihmLogo);

          // Boutton effacer     south   / east
        btnEffacer = new JButton("Effacer");
        panSEAST.add(btnEffacer);
        
        btnEffacer.addMouseListener(new MouseListener(){
            @Override
            public void mouseEntered(MouseEvent e) {}


            @Override
            public void mouseClicked(MouseEvent e) {
                ihmLogo.effacer();
            }

            @Override
            public void mousePressed(MouseEvent e) {                
            }

            @Override
            public void mouseReleased(MouseEvent e) {                
            }
            
            @Override
            public void mouseExited(MouseEvent e) {
            }
        });
        
          // bouttons options s/s
        btnValider = new JButton("Valider");
        
        btnValider.addMouseListener(new MouseListener(){
            @Override
            public void mouseEntered(MouseEvent e) {}


            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("Bouton Valider cliqué");
                ihmBM.afficherBoiteMessage("Validé",0);
            }

            @Override
            public void mousePressed(MouseEvent e) {                
            }

            @Override
            public void mouseReleased(MouseEvent e) {                
            }
            
            @Override
            public void mouseExited(MouseEvent e) {
            }
        });
        
        
        btnAnnuler = new JButton("Annuler");
        
        btnAnnuler.addMouseListener(new MouseListener(){
            @Override
            public void mouseEntered(MouseEvent e) {}


            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("Bouton Annuler cliqué");
                ihmBM.afficherBoiteMessage("Annulé",0);
            }

            @Override
            public void mousePressed(MouseEvent e) {                
            }

            @Override
            public void mouseReleased(MouseEvent e) {                
            }
            
            @Override
            public void mouseExited(MouseEvent e) {
            }
        });
        
        btnSupprGrp = new JButton("Supprimer Groupe");
        
        btnSupprGrp.addMouseListener(new MouseListener(){
            @Override
            public void mouseEntered(MouseEvent e) {}


            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("Bouton Supprimer Groupe cliqué");
                ihmBM.afficherBoiteMessage("Attention vous allez supprimer ce groupe",1);
            }

            @Override
            public void mousePressed(MouseEvent e) {                
            }

            @Override
            public void mouseReleased(MouseEvent e) {                
            }
            
            @Override
            public void mouseExited(MouseEvent e) {
            }
        });
        
        
        panSSOUTH.add(btnValider);
        panSSOUTH.add(btnAnnuler);
        panSSOUTH.add(btnSupprGrp);
        
        
        
        
        // Tableau liste contacts
        


        String[] entetes = {"Prénom", "Nom"};
        model = new DefaultTableModel();
        model.setColumnIdentifiers(entetes);
        
        tableContacts = new JTable(model);
        JPanel panelTableContact = new JPanel(new BorderLayout());
        panelTableContact.add(tableContacts.getTableHeader(), BorderLayout.NORTH);
        panelTableContact.add(tableContacts, BorderLayout.CENTER);
        panCENTER.add(panelTableContact,BorderLayout.CENTER);
        
        
          
      
        
        // Liste de symboles south/west
        
        panSWEST.setLayout(new GridLayout(Symbole.values().length,1));
        checkSymboles = new JCheckBox[Symbole.values().length];
        listeCheckSymboles = new HashSet() ;
        for (Symbole symbole : Symbole.values()){
                checkSymboles[symbole.ordinal()]= new JCheckBox(symbole.name()) ;
                listeCheckSymboles.add(checkSymboles[symbole.ordinal()]);
                panSWEST.add(checkSymboles[symbole.ordinal()]);
            }

      
        this.add(panWorkspace);
    }
    
    /**
     * Affecte les valeurs des attributs d'un groupe aux widgets de l'IHM
     * @param groupe groupe de contacts
     */    
    public void displayGroupe(Groupe groupe) {
        if (groupe != null) { 
            
            //Nom
            champNom.setText(groupe.getNom());
            // tableau contact
            for (Contact contact : groupe.getContacts()) {
                String[] ligne = new String[] {contact.getNom(), contact.getPrenom()};
                model.addRow(ligne);
            }
            //logo

            ihmLogo.setLogo(groupe.getLogo());
            
            //symboles
            //il faudrait pas tout mettre a false avant ?
            for (JCheckBox checkSymbole : listeCheckSymboles){
                
                for(Symbole symbole : groupe.getSymboles()){
                    if (checkSymbole.getText().equals(symbole.name())){
                        checkSymbole.setSelected(true);
                    }
                }
            }
        }
    }
    
    /**
     * Retourne les valeurs associées au formulaire de fiche groupe de contacts
     * @param groupe
    */    
    public void majGroupeDepuisIhm(Groupe groupe) {
        if (groupe != null) { 
            // A compléter en TP2
            
            //Nom
            groupe.setNom(champNom.getText());
            //contact
            
            //symboles
            for (JCheckBox cb : listeCheckSymboles){
                if (cb.isSelected()) {
                    for (Symbole s : Symbole.values()){
                        if (s.name().equals(cb.getText())){
                            groupe.addSymbole(s);                
                        }
                    }
                } else {
                    for (Symbole s : Symbole.values()){
                        if (s.name().equals(cb.getText())){
                            groupe.removeSymbole(s);                
                        }
                    }
                }
            }    
          
            //logo
            
            groupe.setLogo(ihmLogo.getLogo());
            
        }
    }
       
}
