/*
 * Module 2105 : module IHM : Carnet d'adresse
 */
package src.ihm;
//TODO changer police et couleur pour toute la page (par defaut)
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.Calendar;
import java.util.HashSet;
import javafx.scene.control.ComboBox;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import src.nf.Contact;
import src.nf.DispoSortie;
import src.nf.Hobby;
import src.nf.Mois;
import src.nf.Region;

/**
 * @author IUT2
 */
public class IhmContact2 extends JPanel {

    private JTextField              champNom, champPrenom, champMail, champTel;
    private GridLayout              gridContacts, gridInfoPerso, gridCoordonnées, gridDispoHobby;
    private JPanel                  panInfoPerso, panCoordonnées, panDispoHobby, panOptions, panNomPrenom, panDate, panHobbies, panDispo;
    private JComboBox               comboJours, comboMois, comboAnnee, comboRegion;   
    private JCheckBox[]             checkHobbies;
    private JRadioButton[]          radioDispo;
    
    private ButtonGroup             groupDispo;
    
    private String []               listeMois;
    
    private HashSet<JRadioButton>   listeRadioDispo; 
    private HashSet<JCheckBox>      listeCheckHobbies ; 
    
    /*
     * Formulaire pour saisir les informations relatives à un contact
     */
    public IhmContact2() {
        super();

        initUIComponents();
    }
    
    /**
     * Crée et positionne les composants graphiques constituant l'interface
     */
    private void initUIComponents() {      

        
        gridContacts = new GridLayout(4,1);
        gridInfoPerso = new GridLayout(2,1);
        gridCoordonnées = new GridLayout(2,1);
        gridDispoHobby = new GridLayout(2,1);
  
        this.setLayout(gridContacts);
        panInfoPerso.setBorder(BorderFactory.createTitledBorder("Informations personnelles"));
	panCoordonnées.setBorder(BorderFactory.createTitledBorder("Coordonnées"));
	panDispoHobby.setBorder(BorderFactory.createTitledBorder("Préférences"));
   
        panInfoPerso.setLayout(gridInfoPerso);
        panCoordonnées.setLayout(gridCoordonnées);
        panDispoHobby.setLayout(gridDispoHobby);
        
        
        panNomPrenom.add(new JLabel("Nom : "));
        champNom = new JTextField(30);
        panNomPrenom.add(champNom);
        
        panNomPrenom.add(new JLabel("Prenom : "));
        champPrenom = new JTextField(30);
        panNomPrenom.add(champPrenom);
        
        
        // ComboBox pour DATE de naissance
            // jours
        comboJours = new JComboBox();
        for (int i = 1 ; i <= 31 ; i++){
            comboJours.addItem(i);
        }
        panDate.add(comboJours);
        
            // mois
        listeMois = new String[Mois.values().length];
        for (Mois mois : Mois.values()){
            listeMois[mois.ordinal()] = mois.toString();
           
        }
        comboMois = new JComboBox(listeMois);
        panDate.add(comboMois);     
                
            // année
        Calendar currentDate = Calendar.getInstance();
        comboAnnee = new JComboBox();
        for (int i = currentDate.get(Calendar.YEAR)-100 ; i <= currentDate.get(Calendar.YEAR) ; i++){ // now() en java
            comboAnnee.addItem(i); 
        }
        panDate.add(comboAnnee);
        
        // Fin Combo DATE
        
        panInfoPerso.add(new JLabel("Date de naissance : "));
        panInfoPerso.add(panDate);
        
        
        
        
        //hobbies
        panHobbies = new JPanel();
        checkHobbies = new JCheckBox[Hobby.values().length];
        listeCheckHobbies = new HashSet() ;
        panHobbies.add(new JLabel("Hobbies : "));
        for (Hobby hobby : Hobby.values()){
            checkHobbies[hobby.ordinal()]= new JCheckBox(hobby.toString());
            panHobbies.add(checkHobbies[hobby.ordinal()]);
            listeCheckHobbies.add( checkHobbies[hobby.ordinal()]);          
        }
        
                //disponibilité
        
        panDispo = new JPanel() ;
        groupDispo = new ButtonGroup();
        radioDispo = new JRadioButton[DispoSortie.values().length] ;
        listeRadioDispo = new HashSet() ;
        panDispo.add(new JLabel("Disponibilité : "));
        
        for (DispoSortie dispo : DispoSortie.values()){
           radioDispo[dispo.ordinal()]= new JRadioButton(dispo.getLabel());
           groupDispo.add(radioDispo[dispo.ordinal()]);
           panDispo.add(radioDispo[dispo.ordinal()]); 
           listeRadioDispo.add(radioDispo[dispo.ordinal()]);
        }   
        
        
        panDispoHobby.add(panDispo);
        panDispoHobby.add(panHobbies);
        
        
        
        this.add(panInfoPerso);
        this.add(panCoordonnées);
        this.add(panDispoHobby);       
        this.add(panOptions);
        
    }
    
    /**
     * Affecte des valeurs au formulaire de fiche contact
     * @param contact un contact pour mettre à jour à l'IHM
     * @return vrai si ok
     */
    public boolean displayContact(Contact contact) {
        if (contact == null) { return false; }
        // Nom du contact
        champNom.setText(contact.getNom()); 
        champPrenom.setText(contact.getPrenom());
        champMail.setText(contact.getEmail());
        champTel.setText(contact.getNumeroTelephone());
        
        
        //region
        comboRegion.setSelectedIndex(contact.getRegion().ordinal());
        
        // boucle sur les elements du tableau
        for (JRadioButton rb : listeRadioDispo){
            if (contact.getDisponibilite().getLabel().equals(rb.getText())){
                rb.setSelected(true);                
            }             
        }
        

        
        //hobbies
        for (JCheckBox cb : listeCheckHobbies){    
            for (Hobby h : contact.getHobbies()){
                if (h.toString().equals(cb.getText())){
                    cb.setSelected(true);                
                } 
            }            
        }
        
         
        //naissance
        //jour
        comboJours.setSelectedIndex(contact.getDateNaissanceJour()-1);
        //mois
        comboMois.setSelectedIndex(contact.getDateNaissanceMois().ordinal());
        //annee
        Calendar currentDate = Calendar.getInstance();
        comboAnnee.setSelectedIndex(contact.getDateNaissanceAnnee()-(currentDate.get(Calendar.YEAR)-100));
        // la date de naissance du contact est sous forme AAAA, or notre index doit se trouver entre 0 et 100, on traduit donc grâce à la date actuelle
        
       
        return true;
    }
    
    /**
     * Relit les 
     * @param contact un contact à mettre à jour à partir de l'IHM
     * @return vrai si ok
     */
    public Boolean modifyContact(Contact contact) {
        if (contact == null) { return false; }
            
// Affecte la valeur du champ nom à l'attribut nom du contact
        contact.setNom(champNom.getText()); 
        contact.setPrenom(champPrenom.getText());
        contact.setEmail(champMail.getText());
        contact.setNumeroTelephone(champTel.getText());
        
       
        //region
        contact.setRegion(Region.values()[comboRegion.getSelectedIndex()]);
        
        //dispo
        for (JRadioButton rb : listeRadioDispo) { 
            if (rb.isSelected()) {
                for(DispoSortie d : DispoSortie.values()){
                    if (rb.getText().equals(d.getLabel())){
                        contact.setDisponibilite(d);
                    }
                }
            }
            
        }
        
        //hobbies
        contact.removeAllHobbies();
        for (JCheckBox cb : listeCheckHobbies){
            if (cb.isSelected()){                               
                for (Hobby h : Hobby.values()){
                    if (h.toString().equals(cb.getText())){
                        
                        contact.addHobby(h);
                    }
                }
            }            
        }
       
        //naissance
        Calendar currentDate = Calendar.getInstance();
        contact.setDateNaissance(comboJours.getSelectedIndex()+1, Mois.values()[comboMois.getSelectedIndex()], comboAnnee.getSelectedIndex()+(currentDate.get(Calendar.YEAR)-100));
       
    
        return true;
    }
}