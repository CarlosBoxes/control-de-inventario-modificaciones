/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Especiales;

import EntidadesJPA.DescripcionClientes;
import EntidadesJPA.DescripcionPedido;
import EntidadesJPA.DescripcionPedidoProveedores;
import EntidadesJPA.Pedido;
import EntidadesJPA.PedidoProveedores;
import GestorDeTablasJPA.IDescripcionClientes;
import GestorDeTablasJPA.IDescripcionFactura;
import GestorDeTablasJPA.IDescripcionPedido;
import GestorDeTablasJPA.IDescripcionPedidoProveedores;
import GestorDeTablasJPA.IInventarioProducto;
import GestorDeTablasJPA.IPedido;
import GestorDeTablasJPA.IPedidoProveedores;
import java.util.List;

/**
 *
 * @author Ch470
 */
public class EliminarPedido {
    private Pedido pedido;
    private IPedido gestorPedido;   
    private IDescripcionPedido gestorDescripcionPedido;
    private IDescripcionClientes gestorDescripcionClientes;
    private IDescripcionFactura gestorDescripcionFacturas;
    
     private PedidoProveedores pedidoProveedor;
    private IPedidoProveedores gestorPedidoProveedor;   
    private IDescripcionPedidoProveedores gestorDescripcionPedidoProveedor;
    private IInventarioProducto gestorInventario;
    
     public EliminarPedido ()
    {
        this.pedido = new Pedido ();
        this.gestorPedido = new IPedido ();
        this.gestorDescripcionClientes = new IDescripcionClientes();
        this.gestorDescripcionPedido = new IDescripcionPedido ();
        this.gestorDescripcionFacturas = new IDescripcionFactura();
        
        this.pedidoProveedor = new PedidoProveedores ();
        this.gestorPedidoProveedor = new IPedidoProveedores ();
        this.gestorDescripcionPedidoProveedor = new IDescripcionPedidoProveedores ();
    }
    
    public String eliminarPedido (int idPedido)
    {
        try
        {
            this.pedido = this.gestorPedido.buscarPedidoPorId(idPedido);
            if (this.pedido == null)
            {
                return "NO existe el Pedido";
            }
            else
            if (!this.pedido.getFacturasCollection().isEmpty())
            {
                return "No se puede eliminar el Pedido, Contiene Facturas Asignadas";
            }
            else
            {
                this.retornarProductos();
                this.pedido.setEliminado(true);
                this.gestorPedido.modificar(pedido);
                return "Pedido Eliminado";
            }
        }
        catch (Exception e)
        {
            return "Error Al Eliminar Pedido";
        }
    }
    
    public void borrarDescripciones ()
    {
        if (!this.pedido.getDescripcionPedidoCollection().isEmpty() || !this.pedido.getDescripcionPedidoCollection().isEmpty() )
        {
            for (DescripcionPedido descripcion:this.pedido.getDescripcionPedidoCollection())
            {
                try
                {
                this.gestorDescripcionPedido.eliminar(descripcion.getIddescripcionPedido());
                }
                catch (Exception e)
                {
                    System.out.println(e);
                }
            }
            for (DescripcionPedido descripcion:this.pedido.getDescripcionPedidoCollection())
            {
                this.gestorDescripcionPedido.eliminar(descripcion.getIddescripcionPedido());
            }
        }
        if (!this.pedido.getDescripcionClientesCollection().isEmpty())
        {
            for (DescripcionClientes descripcion:this.pedido.getDescripcionClientesCollection())
            {
                this.gestorDescripcionClientes.eliminar(descripcion.getIddescripcionclientes());
            }
        }
    }
    
    public String elimnarPedidoProveedor (int idPedido)
    {
        try
        {
            this.pedidoProveedor = this.gestorPedidoProveedor.buscarPedidoPorId(idPedido);
            if (this.pedidoProveedor == null)
            {
                return "NO existe el Pedido";
            }
            else
            {
                this.pedidoProveedor.setEliminado(true);
                this.gestorPedidoProveedor.modificar(pedidoProveedor);
                return "Pedido Eliminado";
            }
        }
        catch (Exception e)
        {
            return "Error Al Eliminar Pedido";
        }
    }
    
    public void borrarDescripcionesProveedor ()
    {
        if (!this.pedidoProveedor.getDescripcionPedidoProveedoresCollection().isEmpty())
        {
            for (DescripcionPedidoProveedores descripcion:this.pedidoProveedor.getDescripcionPedidoProveedoresCollection())
            {
                try
                {
                    this.gestorDescripcionPedido.eliminar(descripcion.getIddescripcionP());
                }
                catch (Exception e)
                {
                    System.out.println(e);
                }
            }
        }
       
    }
    
    public void retornarProductos ()
    {
        if (!this.pedidoProveedor.getDescripcionPedidoProveedoresCollection().isEmpty())
        {
            for (DescripcionPedidoProveedores descripcion:this.pedidoProveedor.getDescripcionPedidoProveedoresCollection())
            {
                this.gestorInventario.meterAlInventario(descripcion.getProductosidProductos(),descripcion.getCantidad());
            }
        }
    }
}
