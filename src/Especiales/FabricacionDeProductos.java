/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Especiales;

import EntidadesJPA.MateriaPrima;
import EntidadesJPA.Productos;
import GestorDeTablasJPA.IInventarioMateriaPrima;
import GestorDeTablasJPA.IInventarioProducto;
import GestorDeTablasJPA.IMateriaPrima;
import GestorDeTablasJPA.IProductos;

/**
 *
 * @author Ch470
 */
public class FabricacionDeProductos {
    private IInventarioProducto inventarioGestor ;
    private IInventarioMateriaPrima inventarioMateriaPrima;
    
    private IProductos productoGestor;
    private IMateriaPrima materiaPrimaGestor;
    
    public FabricacionDeProductos(){
        this.inventarioGestor = new IInventarioProducto ();
        this.inventarioMateriaPrima = new IInventarioMateriaPrima();
        this.productoGestor = new IProductos();
        this.materiaPrimaGestor = new IMateriaPrima();
    }
    
    public void ingresarAlInventario (int id,float cantidad)
    {
        this.inventarioGestor.meterAlInventario(this.encontrarProducto(id), cantidad);
    }
    
    public void sacarDelInventario (int id,float cantidad)
    {
        this.inventarioGestor.sacarDeInventario(this.encontrarProducto(id), cantidad);
    }
    
    private Productos encontrarProducto (int id)
    {
        return this.productoGestor.buscarProductoPorId(id);
    }
    
    public void sacarMateriaPrimaDeInventario (int id,float cantidad)
    {
        this.inventarioMateriaPrima.sacarDeInventario(this.encontrarMateriaPrima(id), cantidad);
    }
    
    private MateriaPrima encontrarMateriaPrima (int id)
    {
        return this.materiaPrimaGestor.buscarMateriaPrimaPorId(id);
    }
    
    
}
