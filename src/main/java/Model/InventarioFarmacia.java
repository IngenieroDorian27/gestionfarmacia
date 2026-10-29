/*
 * Clase cliente
 */
package Model;

import java.time.LocalDate;
import java.util.Map;

/**
 *
 * @author Grupo1
 */
public class InventarioFarmacia {
    private LocalDate fechaElaboracion = LocalDate.now();
    private LocalDate fechaVencimiento = LocalDate.now();
    private String nombreProducto;
    private String stock;
    private String precio;
    private String estado;

    public InventarioFarmacia(LocalDate fechaelaboracion,LocalDate fechavencimiento, String nombreproducto, String stock, String precio, String estado) {
        this.fechaElaboracion = fechaelaboracion;
        this.fechaVencimiento = fechavencimiento;
        this.nombreProducto = nombreproducto;
        this.stock = stock;
        this.precio = precio;
        this.estado = estado;
    }

    public LocalDate getFechaElaboracion() {
        return fechaElaboracion;
    }

    public void setFechaElaboracion(LocalDate fechaElaboracion) {
        this.fechaElaboracion = fechaElaboracion;
    }

    public LocalDate getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(LocalDate fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public String getStock() {
        return stock;
    }

    public void setStock(String stock) {
        this.stock = stock;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    

    

    @Override
    public String toString() {
        return "\nProducto:\n" + "Fecha Elaboracion: " + fechaElaboracion + "\nFecha Vencimiento: " + fechaVencimiento + "\nNombre Producto: " + nombreProducto + "\nStock: " + stock 
                + "\nPrecio: " + precio + "\nEstado: " + estado + "\n";
    }
     
}
