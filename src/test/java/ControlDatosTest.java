import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.InputMismatchException;

import Control.ControlDatos;

public class ControlDatosTest {

    private ControlDatos controlDatos;
    private InputStream originalSystemIn;

    @BeforeEach
    public void setUp() {
        controlDatos = new ControlDatos();
        originalSystemIn = System.in;
    }

    @AfterEach
    public void tearDown() {
        // Restaurar la entrada estándar original
        System.setIn(originalSystemIn);
    }

    @Test
    public void testLeerEntero() {
        // Simulamos la entrada del usuario
        String input = "123\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        // Llamamos al método y verificamos el resultado esperado
        int resultado = controlDatos.leerEntero("Ingrese un número entero:");
        assertEquals(123, resultado);
    }

    @Test
    public void testLeerReal() {
        // Simulamos la entrada del usuario
        String input = "10.5\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        // Llamamos al método y verificamos el resultado esperado
        double resultado = controlDatos.leerReal("Ingrese un número real:");
        assertEquals(10.5, resultado, 0.01); // Usamos delta para tolerancia en comparaciones de double
    }

    @Test
    public void testLeerLetra() {
        String input = "a\n"; // Simular entrada de usuario 'a'
        InputStream in = new ByteArrayInputStream(input.getBytes());
        ControlDatos controlDatos = new ControlDatos();

        char resultado = controlDatos.leerLetra("Ingrese una letra (a o b): ", 'a', 'b', in);

        assertEquals('a', resultado);
    }

    @Test
    public void testLeerLetraInvalida() {
        String input = "b\n"; // Simular entrada de usuario 'b'
        InputStream in = new ByteArrayInputStream(input.getBytes());
        ControlDatos controlDatos = new ControlDatos();

        char resultado = controlDatos.leerLetra("Ingrese una letra (a o b): ", 'a', 'b', in);

        assertEquals('b', resultado);
    }
}