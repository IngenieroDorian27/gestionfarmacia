import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.awt.event.KeyEvent;
import javax.swing.JTextField; // Importa un componente de interfaz gráfica

import Control.ValidacionRDNT;

public class ValidacionRDNTTest {

    @Test
    public void testNombre_ValidCharacter() {
        ValidacionRDNT validacion = new ValidacionRDNT();
        JTextField textField = new JTextField(); // Componente de texto simulado
        KeyEvent mockEvent = new KeyEvent(textField, KeyEvent.KEY_TYPED, System.currentTimeMillis(), 0, KeyEvent.VK_UNDEFINED, 'a'); // Carácter válido

        validacion.nombre(mockEvent);

        // Verificar que el carácter sea aceptado
        assertFalse(mockEvent.isConsumed());
    }

    @Test
    public void testNombre_InvalidCharacter() {
        ValidacionRDNT validacion = new ValidacionRDNT();
        JTextField textField = new JTextField(); // Componente de texto simulado
        KeyEvent mockEvent = new KeyEvent(textField, KeyEvent.KEY_TYPED, System.currentTimeMillis(), 0, KeyEvent.VK_UNDEFINED, '1'); // Carácter inválido

        validacion.nombre(mockEvent);

        // Verificar que el carácter no sea aceptado
        assertTrue(mockEvent.isConsumed());
    }

    @Test
    public void testCedula_ValidCharacter() {
        ValidacionRDNT validacion = new ValidacionRDNT();
        JTextField textField = new JTextField(); // Componente de texto simulado
        KeyEvent mockEvent = new KeyEvent(textField, KeyEvent.KEY_TYPED, System.currentTimeMillis(), 0, KeyEvent.VK_UNDEFINED, '5'); // Carácter válido

        validacion.Cedula(mockEvent);

        // Verificar que el carácter sea aceptado
        assertFalse(mockEvent.isConsumed());
    }

    @Test
    public void testCedula_InvalidCharacter() {
        ValidacionRDNT validacion = new ValidacionRDNT();
        JTextField textField = new JTextField(); // Componente de texto simulado
        KeyEvent mockEvent = new KeyEvent(textField, KeyEvent.KEY_TYPED, System.currentTimeMillis(), 0, KeyEvent.VK_UNDEFINED, 'a'); // Carácter inválido

        validacion.Cedula(mockEvent);

        // Verificar que el carácter no sea aceptado
        assertTrue(mockEvent.isConsumed());
    }
}
