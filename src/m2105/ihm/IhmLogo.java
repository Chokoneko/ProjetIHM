/*
 * Module 2105 : module IHM : Carnet d'adresse
 */
package m2105.ihm;

import java.awt.Point;
import java.awt.Color;
import java.awt.Canvas;
import java.awt.Polygon;
import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 *
 * @author IUT2
 */
/**
 * 
 * @class IhmIcone
 * Zone d'édition du logo d'un groupe
 */
public class IhmLogo extends Canvas  {

    // Liste des points définissant le logo
    private Polygon polygon;
    
    public IhmLogo() {
        super();
                
        polygon = new Polygon();
        
        this.addMouseListener(new MouseListener(){
            @Override
            public void mouseEntered(MouseEvent e) {}


            @Override
            public void mouseClicked(MouseEvent e) {                
            }

            @Override
            public void mousePressed(MouseEvent e) {
                polygon.addPoint(e.getX(),e.getY());
                repaint();
            }

            @Override
            public void mouseReleased(MouseEvent e) {                
            }
            
            @Override
            public void mouseExited(MouseEvent e) {
            }
        });
        // Abonne le canvas aux évènements souris
        /** TP 2 : à compléter **/
        
    }
    
    /**
     * Dessine le contenu du canvas, c'est-à-dire l'icone
     * @param g un contexte graphique
     */
    @Override
    public void paint(Graphics g) {
        Dimension dim = this.getSize();

        // Dessine un rectangle à fond blanc de la taille du canvas
        g.setColor(Color.white);
        g.fillRect(0, 0, dim.width, dim.height);
        
        // Dessine un rectangle avec un liseré bleu
        /** TP 2 : à modifier et compléter **/
        g.setColor(Color.blue);
        g.drawRect(0, 0, dim.width-1, dim.height-1);
        
        // Dessine le polygone en rouge
        /** TP 2 : à modifier et compléter **/
        g.setColor(Color.red);
        g.drawPolygon(polygon);
       
        
        
    }

    /**
     * Efface le dessin
     */
    public void effacer() {
        polygon.reset();

        // Redessine le contenu du canvas
        repaint();
    }
        
    /**
     * Affecte une icône au polygone
     * @param logo tableau de points
     */
    public void setLogo(Point[] logo) {
        
        // Affecte le polyhone à partir du tableau de points logo
        if (logo != null) {           
            for (Point p : logo){
                polygon.addPoint(p.x, p.y);
            }
            
            // Redessine le contenu du canvas
            repaint();            
        }
    }

    /**
     * Retourne les points définissant l'icône
     * @return tableau d'entiers
     */
    public Point[] getLogo() {
        Point[] res = new Point[polygon.npoints];
       for (int i =0 ; i<polygon.npoints; i++){
           res[i].setLocation(polygon.xpoints[i], polygon.ypoints[i]);
       }
        return res;
    }
        
    /*
     * Taille fixe
     */
    @Override
    public Dimension getSize() {
        return new Dimension(150, 150);        
    }          
    @Override
    public Dimension getMinimumSize() {
        return this.getSize();        
    }          
    @Override
    public Dimension getPreferredSize() {
        return this.getSize();        
    }          
}