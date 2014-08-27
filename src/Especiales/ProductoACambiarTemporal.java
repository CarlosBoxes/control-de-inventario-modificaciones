/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Especiales;

/**
 *
 * @author Ch470
 */
public class ProductoACambiarTemporal {
    public String nombre;
    public float cantidad;
    public float precio;
    public ProductoACambiarTemporal(){
        this.nombre = "";
        this.cantidad =0;
        this.precio = 0;
    }
    public void setProducto (String nombre,float cantidad,float precio)
    {
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.precio = precio;
    }
    
    
}
