/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Especiales;

import EntidadesJPA.DescripcionPedido;

/**
 *
 * @author Ch470
 */
public class ProductoFacturado {
    
    public DescripcionPedido Descripcion;
    public int Cantidad;
    
    public ProductoFacturado(DescripcionPedido Descripcion, int Cantidad)
    {
        this.Descripcion = Descripcion;
        this.Cantidad = Cantidad;
    }
}
