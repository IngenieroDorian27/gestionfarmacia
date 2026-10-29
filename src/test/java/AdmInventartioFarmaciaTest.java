package Control;

import java.sql.*;
import java.time.LocalDate;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AdmInventartioFarmaciaTest {

    private AdmInventartioFarmacia adm;
    private JTable tblCliente;
    private JLabel lblTotal;

    @BeforeEach
    public void setUp() {
        adm = AdmInventartioFarmacia.getAdm();
        tblCliente = new JTable(new DefaultTableModel(
            new Object[][] {},
            new String[] {"ID", "Fecha Elaboración", "Fecha Vencimiento", "Nombre", "Stock", "Precio", "Proveedor"}
        ));
        lblTotal = new JLabel("0");
    }

    @Test
    public void testIngresar() {
        java.sql.Date fechaElaboracion = java.sql.Date.valueOf(LocalDate.of(2024, 7, 31));
        java.sql.Date fechaVencimiento = java.sql.Date.valueOf(LocalDate.of(2025, 7, 31));
        String nombreProducto = "Producto Test";
        String proveedor = "Disponible";
        int stock = 100;
        float precio = 29.99f;

        adm.ingresar(fechaElaboracion, fechaVencimiento, nombreProducto, stock, precio, proveedor);

    }
  
   
}
