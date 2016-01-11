/*
 * Module 2105 : module IHM : Carnet d'adresse
 */
package m2105.ihm;
//TODO changer police et couleur pour toute la page (par defaut)
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.Calendar;
import java.util.HashSet;
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
import m2105.nf.Contact;
import m2105.nf.DispoSortie;
import m2105.nf.Hobby;
import m2105.nf.Mois;
import m2105.nf.Region;

/**
 * @author IUT2
 */
public class IhmContact extends JPanel {

    private JTextField       champNom, champPrenom, champMail, champTel; // Widget de type champ texte pour afficher et saisir le nom du contact
    private JComboBox        comboJours, comboMois, comboAnnee, comboRegion ;
    private JRadioButton[]   radioDispo;  
    private JCheckBox[]      checkHobbies; 
    
    private ButtonGroup      groupDispo;
    private JPanel           panCentre, panCGauche, panCDroite,panInfosperso,panILabels,panIChamps, panDate, panPreference, panHobbies, panDispo, panOptions ;  //panWorkspace,panInformations,
    private String []        listeMois;
 

    private JButton          btnValider, btnAnnuler, btnTester ; 
    private HashSet<JRadioButton> listeRadioDispo; 
    private HashSet<JCheckBox> listeCheckHobbies ; 
    
  
    
    
    /*
     * Formulaire pour saisir les informations relatives à un contact
     */
    public IhmContact() {
        super();

        initUIComponents();
    }
    
    /**
     * Crée et positionne les composants graphiques constituant l'interface
     */
    private void initUIComponents() {      
        /** Pour l'exemple **/
        //panWorkspace= new JPanel () ;
        
        //panWorkspace.setLayout(new BorderLayout());
        this.setLayout(new BorderLayout());
//        panInformations = new JPanel ();
//        panInformations.setLayout(new GridLayout(2,1));
        panCentre = new JPanel();
        panCentre.setLayout(new GridLayout(1,2));
        this.add(panCentre,BorderLayout.CENTER);
        panCGauche = new JPanel() ;
        panCentre.add(panCGauche);
        panCGauche.setLayout(new BorderLayout());
        panCDroite = new JPanel() ;
        panCentre.add(panCDroite);
        panCDroite.setLayout(new BorderLayout());
        
        panInfosperso= new JPanel () ;
        panInfosperso.setLayout(new GridLayout(1,2));   
        panPreference = new JPanel () ;
        panPreference.setLayout(new GridLayout(2,1));
        panILabels = new JPanel();
        panILabels.setLayout(new GridLayout(6,1));
        panILabels.setForeground(Color.yellow);
        panIChamps = new JPanel();
        panIChamps.setLayout(new GridLayout(6,1));  
        
        
 
        
        //nom
        JLabel labelNom = new JLabel("Nom :", JLabel.RIGHT);
        labelNom.setForeground(Color.red);
        panILabels.add(labelNom);
        champNom = new JTextField(30);        
        JPanel panChampNom = new JPanel() ;
        panChampNom.add(champNom);
        panIChamps.add(panChampNom);
        
        
       
        
        
        //prénom
        panILabels.add(new JLabel("Prenom : ", JLabel.RIGHT));
        champPrenom = new JTextField(30);
       // JPanel panChampPrenom = new JPanel() ;
       // panChampPrenom.add(champPrenom);
        //panIChamps.add(panChampPrenom);
        panIChamps.add(champPrenom);
        
        //mail
        panILabels.add(new JLabel("Mail : ", JLabel.RIGHT));
        champMail = new JTextField(50);
        JPanel panChampMail = new JPanel() ;
        panChampMail.add(champMail);
        panIChamps.add(panChampMail);
        
        //tel
        panILabels.add(new JLabel("Telephone : ", JLabel.RIGHT));
        champTel = new JTextField(10);
        JPanel panChampTel = new JPanel() ;
        panChampTel.add(champTel);
        panIChamps.add(panChampTel);

        //Region
        panILabels.add(new JLabel("Region : ", JLabel.RIGHT));
        comboRegion = new JComboBox(Region.values());
        JPanel panComboRegion = new JPanel() ;
        panComboRegion.add(comboRegion);
        panIChamps.add(panComboRegion);
        
        
                
        //date de naissance
        
        //jour
        panILabels.add(new JLabel("Date de naissance : ", JLabel.RIGHT));
        panDate = new JPanel() ;
        panIChamps.add(panDate);
       
        
        
        
        
        comboJours = new JComboBox();
        for (int i = 1 ; i <= 31 ; i++){
            comboJours.addItem(i);
        }
        panDate.add(comboJours);
        
        //mois
        listeMois = new String[Mois.values().length];
        for (Mois mois : Mois.values()){
            listeMois[mois.ordinal()] = mois.toString();
           
        }
        comboMois = new JComboBox(listeMois);
        panDate.add(comboMois);  
        
                
        //année
        Calendar currentDate = Calendar.getInstance();
        comboAnnee = new JComboBox();
        for (int i = currentDate.get(Calendar.YEAR)-100 ; i <= currentDate.get(Calendar.YEAR) ; i++){ // now() en java
            comboAnnee.addItem(i); 
        }
        panDate.add(comboAnnee);                      
        
        
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
            
        
        

        
        panOptions = new JPanel() ; 
        
        //valider
        btnValider = new JButton("Valider");
        panOptions.add(btnValider);
        
        //tester
        btnTester = new JButton("Tester");
        panOptions.add(btnTester);

        //annuler
        btnAnnuler = new JButton("Annuler");
        panOptions.add(btnAnnuler);
        
        //mise en forme
//        panCGauche.add(new JPanel(),BorderLayout.NORTH);
//        panCGauche.add(new JPanel(),BorderLayout.WEST);
//        panCGauche.add(new JPanel(),BorderLayout.EAST);
//        panCGauche.add(new JPanel(),BorderLayout.SOUTH);
        //panInfosperso.setBorder(BorderFactory.createLineBorder());
        panInfosperso.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.RED), "Informations Personnelles"));
        
        panCDroite.add(new JPanel(),BorderLayout.NORTH);
        panCDroite.add(new JPanel(),BorderLayout.WEST);
        panCDroite.add(new JPanel(),BorderLayout.EAST);
        panCDroite.add(new JPanel(),BorderLayout.SOUTH);
        panPreference.setBorder(BorderFactory.createLineBorder(Color.RED));
        
        
        //couleur du bg : nous aimerions mettre la police en beige, tant que ce n'est pas le cas le fond doit rester clair
//        this.setBackground(Color.DARK_GRAY);
//        panCDroite.setBackground(Color.DARK_GRAY);
//        panCGauche.setBackground(Color.DARK_GRAY);
//        panCentre.setBackground(Color.DARK_GRAY);
//        panInfosperso.setBackground(Color.DARK_GRAY);
//        panPreference.setBackground(Color.DARK_GRAY);
//        panDispo.setBackground(Color.DARK_GRAY);
//        panHobbies.setBackground(Color.DARK_GRAY);
//        panIChamps.setBackground(Color.DARK_GRAY);
//        panChampMail.setBackground(Color.DARK_GRAY);
//        panChampNom.setBackground(Color.DARK_GRAY);
//        panChampPrenom.setBackground(Color.DARK_GRAY);
//        panChampTel.setBackground(Color.DARK_GRAY);
//        panComboRegion.setBackground(Color.DARK_GRAY);
//        panOptions.setBackground(Color.DARK_GRAY);
//        panILabels.setBackground(Color.DARK_GRAY);
//        panDate.setBackground(Color.DARK_GRAY);
        
        
        
        
        
        
       
        
        //ajout des panels à l'espace de travail
        panPreference.add(panHobbies);
        panPreference.add(panDispo);
        //panWorkspace.add(panInformations, BorderLayout.CENTER);
//        this.add(panInformations, BorderLayout.CENTER);
//        panInformations.add(panInfosperso);       
//        panInformations.add(panPreference);
        //panWorkspace.add(panOptions, BorderLayout.SOUTH); 
        panInfosperso.add(panILabels);
        panInfosperso.add(panIChamps);
        panCGauche.add(panInfosperso, BorderLayout.CENTER);
        panCDroite.add(panPreference, BorderLayout.CENTER);
        this.add(panOptions, BorderLayout.SOUTH); 
        
        //this.add(panWorkspace);
        
   
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
       
        //naissance$
        Calendar currentDate = Calendar.getInstance();
        contact.setDateNaissance(comboJours.getSelectedIndex()+1, Mois.values()[comboMois.getSelectedIndex()], comboAnnee.getSelectedIndex()+(currentDate.get(Calendar.YEAR)-100));
        
        return true;
    }
}