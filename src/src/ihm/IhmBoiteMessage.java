/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src.ihm;

import javax.swing.JOptionPane;

/**
 *
 * @author Joris
 */
public class IhmBoiteMessage {
    
    public IhmBoiteMessage(){
        
    }
    
    public boolean afficherBoiteMessage(String message,int mode){
            if (mode==0){
                JOptionPane.showConfirmDialog(null,message, "OK.",JOptionPane.CLOSED_OPTION,JOptionPane.INFORMATION_MESSAGE);
            }
            else if (mode==1){
                JOptionPane.showConfirmDialog(null,message, "Attention !",JOptionPane.OK_CANCEL_OPTION,JOptionPane.WARNING_MESSAGE);
            }

            return true;
        }
    
}
