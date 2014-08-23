/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Especiales;

import EntidadesJPA.DescripcionPedido;
import EntidadesJPA.Productos;

/**
 *
 * @author Ch470
 */
public class ProductoACambiar {
    private int CantidadPuedeDevolver ;
    private float MontoPuedeDevolver;
    private float precioADevolver;
    private Productos producto;
    public ProductoACambiar(){}
    
    public ProductoACambiar (DescripcionPedido descripcion)
    {
        this.producto = descripcion.getProductosidProductos();
        this.precioADevolver = (descripcion.getPrecio());
        this.MontoPuedeDevolver = (float)((descripcion.getCantidad()*this.precioADevolver)*((this.producto.getDevoluciones())/100));
        this.CantidadPuedeDevolver = Math.round(MontoPuedeDevolver/this.precioADevolver);
        
    }
    
    public int getCantidadPuedeDevolver()
    {
        return this.CantidadPuedeDevolver;
    }
    public float getMontoPuedeDevolver()
    {
        return this.MontoPuedeDevolver;
    }
     public Productos getProducto()
    {
        return this.producto;
    }
    public float precioAdevolver()
    {
        return this.precioADevolver;
    }
}
