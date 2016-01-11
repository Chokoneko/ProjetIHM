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
public class IhmContact2 extends JPanel {

    private GridLayout grilleContacts;
    
  
    
    
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

        
        grilleContacts = new GridLayout(4,1);
        
  
   
    }
    
    /**
     * Affecte des valeurs au formulaire de fiche contact
     * @param contact un contact pour mettre à jour à l'IHM
     * @return vrai si ok
     */
    public boolean displayContact(Contact contact) {
        if (contact == null) { return false; }

   
   
       
        return true;
    }
    
    /**
     * Relit les 
     * @param contact un contact à mettre à jour à partir de l'IHM
     * @return vrai si ok
     */
    public Boolean modifyContact(Contact contact) {
        if (contact == null) { return false; }

       
    
        return true;
    }
}