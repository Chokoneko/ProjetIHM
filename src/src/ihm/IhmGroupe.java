/*
 * Module 2105 : module IHM : Carnet d'adresse
 */
package src.ihm;

//Test ! <3

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
import src.nf.Contact;
import src.nf.Groupe;
import src.nf.Symbole;

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
    private JPanel          panDroite,panGauche, panNomGroupe,panLogo,panSymboles,panOption,panTableContact ; 
    private JButton         btnValider, btnEffacer, btnAnnuler, btnSupprGrp ;
    private JTable          tableContacts ;
    private DefaultTableModel model;
    private JCheckBox[]      checkSymboles; 
    private HashSet<JCheckBox> listeCheckSymboles ; 
    private GridLayout      grilleWorkspace,grillePanGauche ;
    
    
    
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
        
        ihmBM = new IhmBoiteMessage();
        
        
      // Organisation de l'espace de travail        
        grilleWorkspace = new GridLayout(1,2);
        this.setLayout(grilleWorkspace);
        
        grillePanGauche = new GridLayout(4,1);
        panGauche = new JPanel();
        panGauche.setLayout(grillePanGauche);
        this.add(panGauche);
        
        panDroite = new JPanel();      
        this.add(panDroite);
        
        
        
          //Champ texte nom groupe
       
        panNomGroupe = new JPanel();
        panNomGroupe.add(new JLabel("Nom :"));
        champNom = new JTextField(30);
        panNomGroupe.add(champNom);
        
       
        // Canvas zone de dessin south/center
        ihmLogo = new IhmLogo();        
        
        panLogo.add(ihmLogo);

          // Boutton effacer     south   / east
        btnEffacer = new JButton("Effacer");
        panLogo.add(btnEffacer);
        
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
        
        panOption = new JPanel();
        panOption.add(btnValider);
        panOption.add(btnAnnuler);
        panOption.add(btnSupprGrp);
        
        
        
         // Liste de symboles 
        
        panSymboles.setLayout(new GridLayout(Symbole.values().length,1));
        checkSymboles = new JCheckBox[Symbole.values().length];
        listeCheckSymboles = new HashSet() ;
        for (Symbole symbole : Symbole.values()){
                checkSymboles[symbole.ordinal()]= new JCheckBox(symbole.name()) ;
                listeCheckSymboles.add(checkSymboles[symbole.ordinal()]);
                panSymboles.add(checkSymboles[symbole.ordinal()]);
            }
        
        
        
          
      
        
       
        // Tableau liste contacts
        


       String[] entetes = {"Prénom", "Nom"};
        model = new DefaultTableModel();
        model.setColumnIdentifiers(entetes);
        
        tableContacts = new JTable(model);
        panTableContact = new JPanel(new BorderLayout());
        panTableContact.add(tableContacts.getTableHeader(), BorderLayout.NORTH);
        panTableContact.add(tableContacts, BorderLayout.CENTER);
        
        
      
        panGauche.add(panNomGroupe);
        panGauche.add(panLogo);
        panGauche.add(panSymboles);
        panGauche.add(panOption);
        panDroite.add(panTableContact);
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
