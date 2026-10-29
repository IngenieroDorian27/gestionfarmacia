/*
 * 
 */
package Control;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import javax.swing.JOptionPane;

/**
 *
 * @author Grupo1
 */
public class ValidacionRDNT {
    char n, c;
    
    public void nombre(KeyEvent evt){
        n = evt.getKeyChar();
        if(!Character.isLetter(n) && n !=KeyEvent.VK_BACK_SPACE && n !=KeyEvent.VK_SPACE){
            evt.consume();
            JOptionPane.showMessageDialog(null, "Caracter no permitido");
        
        }
    
    }

    public void Cedula(KeyEvent evt) {
        c = evt.getKeyChar();
        if(!Character.isDigit(c) && c !=KeyEvent.VK_BACK_SPACE){
            evt.consume();
            JOptionPane.showMessageDialog(null, "Caracter no permitido");
        
        }
    }
    
}
