/*
 *
 */
package Control;

import Model.Usuario;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import javax.swing.JComboBox;

/**
 *
 * @author Grupo1
 */
public class AdmUsuario {

    private ArrayList<Usuario> datos = new ArrayList<Usuario>();
   

    public AdmUsuario() {
        Usuario p1 = new Usuario("admin01", "admin01");
        Usuario p2 = new Usuario("admin02", "admin02");
        Usuario p3 = new Usuario("admin03", "admin03");

        datos.add(p1);
        datos.add(p2);
        datos.add(p3);
    }
    
    public boolean verificarUsuario(String usuario, String contra) {
        for (Usuario u : datos) {
            if (u.getUsuario().equals(usuario) && u.getContra().equals(contra)) {
                return true;
            }
        }
        return false;
    }

}
