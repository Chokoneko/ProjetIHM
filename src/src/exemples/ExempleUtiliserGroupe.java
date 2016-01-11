/*
 * Module 2105 : module IHM : Carnet d'adresse
 */
package src.exemples;

/**
 * @author IUT2
 */
import java.awt.Point;
import src.nf.Contact;
import src.nf.Symbole;
import src.nf.Groupe;
import src.nf.Mois;
import src.nf.Region;

/*
 * Cette classe illustre l'utilisation du code du noyau fonctionnel par l'emploi
 * des classes : Contact, GroupeContacts, Evenement et CarnetPlanning
 */
public class ExempleUtiliserGroupe {
    public static void main(String [] args) {
        Groupe groupe = new Groupe();

        // nom du groupe
        groupe.setNom("Copains IUT2");
        
        // Symboles du groupe
        groupe.addSymbole(Symbole.FLEUR);
        groupe.addSymbole(Symbole.ARBRE);
        
        // Logo du groupe (ici un carré)
        Point [] points = new Point[4];
        points[0] = new Point(20,20);
        points[1] = new Point(100,20);
        points[2] = new Point(100,100);
        points[3] = new Point(20,100);
        groupe.setLogo(points);
        
        // Contacts du groupe (ici ajout d'un contact)
        Contact contact = new Contact();
        contact.setNom("Dupont");
        contact.setPrenom("Amélie");
        contact.setRegion(Region.ALSACE);
        contact.setEmail("amelie.dupont@gmail.com");
        contact.setNumeroTelephone("03.89.76.54.20");
        contact.setDateNaissance(5, Mois.JUILLET, 2001);
        groupe.addContact(contact);
        
        // Affichage du groupe dans la console
        groupe.afficherDansConsole("");
    }
}
