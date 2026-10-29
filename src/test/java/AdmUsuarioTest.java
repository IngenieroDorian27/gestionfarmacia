import Control.AdmUsuario;
import Model.Usuario;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AdmUsuarioTest {
    
    private AdmUsuario admUsuario;
    
    @BeforeEach
    public void setUp() {
        admUsuario = new AdmUsuario();
    }

    @AfterEach
    public void tearDown() {
        // Aquí puedes realizar cualquier limpieza después de cada prueba, si es necesario
    }

    @Test
    public void testVerificarUsuarioExistente() {
        assertTrue(admUsuario.verificarUsuario("admin01", "admin01"));
    }
    
    @Test
    public void testVerificarUsuarioNoExistente() {
        assertFalse(admUsuario.verificarUsuario("admin04", "admin04"));
    }
    
    @Test
    public void testVerificarUsuarioContrasenaIncorrecta() {
        assertFalse(admUsuario.verificarUsuario("admin01", "incorrecta"));
    }
}
