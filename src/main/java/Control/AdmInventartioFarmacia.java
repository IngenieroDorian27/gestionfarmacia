package Control;

import util.DatabaseUtil;
import java.sql.*;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeParseException;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class AdmInventartioFarmacia {

    private static AdmInventartioFarmacia adm;

    private AdmInventartioFarmacia() {}

    public static AdmInventartioFarmacia getAdm() {
        if (adm == null) {
            adm = new AdmInventartioFarmacia();
        }
        return adm;
    }
    
public void ingresar(java.sql.Date fechaElaboracion, java.sql.Date fechaVencimiento, String nombreProducto, int stock, float precio, String proveedor) {
    String sql = "INSERT INTO productos (fecha_elaboracion, fecha_vencimiento, nombre_producto, stock_producto, precio_producto, proveedor) VALUES (?, ?, ?, ?, ?, ?)";

    try (Connection conn = DatabaseUtil.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        // Set parameters for PreparedStatement
        stmt.setDate(1, fechaElaboracion);
        stmt.setDate(2, fechaVencimiento);
        stmt.setString(3, nombreProducto);
        stmt.setInt(4, stock);
        stmt.setFloat(5, precio);
        stmt.setString(6, proveedor);

        stmt.executeUpdate();
        JOptionPane.showMessageDialog(null, "Producto ingresado correctamente");
    } catch (SQLException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(null, "Error al ingresar producto: " + e.getMessage());
    }
}

    
    public void llenarTabla(JTable tblCliente, JLabel lblTotal) {
        String sql = "SELECT * FROM productos";
        DefaultTableModel modelo = (DefaultTableModel) tblCliente.getModel();
        modelo.setRowCount(0);

        try (Connection conn = DatabaseUtil.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            int fila = 0;
            while (rs.next()) {
                modelo.addRow(new Object[1]);
                tblCliente.setValueAt(rs.getInt("id_producto"), fila, 0);
                tblCliente.setValueAt(rs.getDate("fecha_elaboracion").toLocalDate(), fila, 1);
                tblCliente.setValueAt(rs.getDate("fecha_vencimiento").toLocalDate(), fila, 2);
                tblCliente.setValueAt(rs.getString("nombre_producto"), fila, 3);
                tblCliente.setValueAt(rs.getString("stock_producto"), fila, 4);
                tblCliente.setValueAt(rs.getFloat("precio_producto"), fila, 5);
                tblCliente.setValueAt(rs.getString("proveedor"), fila, 6);
                fila++;
            }
            lblTotal.setText(fila + "");
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al cargar datos");
        }
    }
    
    public void llenarProveedor(JTable tblProveedor, JLabel lblTotal) {
        String sql = "SELECT * FROM proveedor";
        DefaultTableModel modelo = (DefaultTableModel) tblProveedor.getModel();
        modelo.setRowCount(0);

        try (Connection conn = DatabaseUtil.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            int fila = 0;
            while (rs.next()) {
                modelo.addRow(new Object[1]);
                tblProveedor.setValueAt(rs.getInt("id_proveedor"), fila, 0);
                tblProveedor.setValueAt(rs.getString("ruc_proveedor"), fila, 1);
                tblProveedor.setValueAt(rs.getString("nombre_proveedor"), fila, 2);
                tblProveedor.setValueAt(rs.getString("direccion_proveedor"), fila, 3);
                tblProveedor.setValueAt(rs.getString("telefono_proveedor"), fila, 4);
                tblProveedor.setValueAt(rs.getString("email_proveedor"), fila, 5);
                fila++;
            }
            lblTotal.setText(fila + "");
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al cargar datos");
        }
    }

    public void filtrarNombre(String nombre, JTable tblCliente, JLabel lblTotal) {
        String sql = "SELECT * FROM productos WHERE nombre_producto = ?";
        DefaultTableModel modelo = (DefaultTableModel) tblCliente.getModel();
        modelo.setRowCount(0);

        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, nombre);
            try (ResultSet rs = stmt.executeQuery()) {
                int fila = 0;
                while (rs.next()) {
                    modelo.addRow(new Object[1]);
                    tblCliente.setValueAt(rs.getInt("id_producto"), fila, 0);
                    tblCliente.setValueAt(rs.getDate("fecha_elaboracion").toLocalDate(), fila, 1);
                    tblCliente.setValueAt(rs.getDate("fecha_vencimiento").toLocalDate(), fila, 2);
                    tblCliente.setValueAt(rs.getString("nombre_producto"), fila, 3);
                    tblCliente.setValueAt(rs.getString("stock_producto"), fila, 4);
                    tblCliente.setValueAt(rs.getFloat("precio_producto"), fila, 5);
                    tblCliente.setValueAt(rs.getString("proveedor"), fila, 6);
                    fila++;
                }
                lblTotal.setText(fila + "");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al filtrar por nombre");
        }
    }

    public void eliminar(JTable tblCliente, JLabel lblTotal) {
        int fila = tblCliente.getSelectedRow();
        if (fila >= 0) {
            int idProducto = (Integer) tblCliente.getValueAt(fila, 0);
            String sql = "DELETE FROM productos WHERE id_producto = ?";

            try (Connection conn = DatabaseUtil.getConnection();
                 PreparedStatement stmt = conn.prepareStatement(sql)) {

                stmt.setInt(1, idProducto);
                stmt.executeUpdate();
                ((DefaultTableModel) tblCliente.getModel()).removeRow(fila);
                lblTotal.setText(tblCliente.getRowCount() + "");
                JOptionPane.showMessageDialog(null, "Fila eliminada correctamente");
            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error al eliminar fila");
            }
        } else {
            JOptionPane.showMessageDialog(null, "No ha seleccionado una fila para eliminar");
        }
    }

    public void editar(JTable tblCliente) {
    int fila = tblCliente.getSelectedRow();
    if (fila >= 0) {
        int idProducto = (Integer) tblCliente.getValueAt(fila, 0);

        String nuevoNombreProducto = JOptionPane.showInputDialog("Ingrese nuevo nombre de producto", tblCliente.getValueAt(fila, 3));
        String nuevoStock = JOptionPane.showInputDialog("Ingrese nuevo stock", tblCliente.getValueAt(fila, 4));
        String nuevoPrecio = JOptionPane.showInputDialog("Ingrese nuevo precio", tblCliente.getValueAt(fila, 5));
        String nuevoProveedor = JOptionPane.showInputDialog("Ingrese nuevo proveedor", tblCliente.getValueAt(fila, 6));
        String nuevaFechaElaboracion = JOptionPane.showInputDialog("Ingrese nueva fecha de elaboración (AAAA-MM-DD)", tblCliente.getValueAt(fila, 1));
        String nuevaFechaVencimiento = JOptionPane.showInputDialog("Ingrese nueva fecha de vencimiento (AAAA-MM-DD)", tblCliente.getValueAt(fila, 2));

        if (nuevoNombreProducto.isEmpty() || nuevoStock.isEmpty() || nuevoPrecio.isEmpty() || nuevoProveedor.isEmpty() || nuevaFechaElaboracion.isEmpty() || nuevaFechaVencimiento.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Por favor, complete todos los campos.");
            return;
        }

        try {
            Integer.parseInt(nuevoStock);
            Float.parseFloat(nuevoPrecio);
            LocalDate.parse(nuevaFechaElaboracion);
            LocalDate.parse(nuevaFechaVencimiento);
        } catch (NumberFormatException | DateTimeParseException e) {
            JOptionPane.showMessageDialog(null, "Formato de entrada inválido.");
            return;
        }

        String sql = "UPDATE productos SET nombre_producto = ?, stock_producto = ?, precio_producto = ?, proveedor = ?, fecha_elaboracion = ?, fecha_vencimiento = ? WHERE id_producto = ?";

        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, nuevoNombreProducto);
            stmt.setInt(2, Integer.parseInt(nuevoStock));
            stmt.setFloat(3, Float.parseFloat(nuevoPrecio));
            stmt.setString(4, nuevoProveedor);
            stmt.setDate(5, Date.valueOf(LocalDate.parse(nuevaFechaElaboracion)));
            stmt.setDate(6, Date.valueOf(LocalDate.parse(nuevaFechaVencimiento)));
            stmt.setInt(7, idProducto);

            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected > 0) {
                tblCliente.setValueAt(nuevoNombreProducto, fila, 3);
                tblCliente.setValueAt(nuevoStock, fila, 4);
                tblCliente.setValueAt(nuevoPrecio, fila, 5);
                tblCliente.setValueAt(nuevoProveedor, fila, 6);
                tblCliente.setValueAt(LocalDate.parse(nuevaFechaElaboracion), fila, 1);
                tblCliente.setValueAt(LocalDate.parse(nuevaFechaVencimiento), fila, 2);

                JOptionPane.showMessageDialog(null, "Fila editada correctamente");
            } else {
                JOptionPane.showMessageDialog(null, "No se encontró el producto para actualizar.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al editar fila: " + e.getMessage());
        }
    } else {
        JOptionPane.showMessageDialog(null, "No ha seleccionado una fila para editar");
    }
}


   public void filtroFecha(Date fecha, JTable tblCliente, JLabel lblTotal) {
    DefaultTableModel modelo = (DefaultTableModel) tblCliente.getModel();
    modelo.setRowCount(0); // Limpiar la tabla

    String sql = "SELECT * FROM productos WHERE fecha_vencimiento = ?";
    try (Connection conn = DatabaseUtil.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setDate(1, fecha);
        try (ResultSet rs = stmt.executeQuery()) {
            int fila = 0;
            while (rs.next()) {
                modelo.addRow(new Object[1]);
                tblCliente.setValueAt(rs.getInt("id_producto"), fila, 0);
                tblCliente.setValueAt(rs.getDate("fecha_elaboracion").toLocalDate(), fila, 1);
                tblCliente.setValueAt(rs.getDate("fecha_vencimiento").toLocalDate(), fila, 2);
                tblCliente.setValueAt(rs.getString("nombre_producto"), fila, 3);
                tblCliente.setValueAt(rs.getString("stock_producto"), fila, 4);
                tblCliente.setValueAt(rs.getFloat("precio_producto"), fila, 5);
                tblCliente.setValueAt(rs.getString("proveedor"), fila, 6);
                fila++;
            }
            lblTotal.setText(fila + "");
        }
    } catch (SQLException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(null, "Error al filtrar por fecha");
    }
}

    public void llenarcombo(JComboBox<String> cmbProveedor) {
        
        String sql = "SELECT nombre_proveedor FROM proveedor";

    try (Connection conn = DatabaseUtil.getConnection();
         Statement stmt = conn.createStatement();
         ResultSet rs = stmt.executeQuery(sql)) {

        cmbProveedor.removeAllItems();

        while (rs.next()) {
            cmbProveedor.addItem(rs.getString("nombre_proveedor"));
        }
    } catch (SQLException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(null, "Error al cargar datos");
    }
    }

}
