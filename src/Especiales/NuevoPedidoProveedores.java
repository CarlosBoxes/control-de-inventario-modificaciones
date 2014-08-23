/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Especiales;

import EntidadesJPA.DescripcionPedidoProveedores;
import EntidadesJPA.PedidoProveedores;
import EntidadesJPA.Productos;
import EntidadesJPA.Proveedores;
import GestorDeTablasJPA.IDescripcionPedidoProveedores;
import GestorDeTablasJPA.IPedidoProveedores;
import GestorDeTablasJPA.IProveedores;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Ch470
 */
public class NuevoPedidoProveedores {
    public NuevoPedidoProveedores(){}
    
    private IPedidoProveedores manejoPedido = new IPedidoProveedores ();
    private IDescripcionPedidoProveedores descripcionPedido = new IDescripcionPedidoProveedores ();
    private PedidoProveedores pedidoC = new PedidoProveedores ();
    private Proveedores proveedor = new Proveedores();
    private IProveedores manejoProveedor= new IProveedores();
    
    
    private List<DescripcionPedidoProveedores> listaDeDescripcion = new ArrayList();
    
    public void crearNuevoPedidoSinDescripcion (Date fecha,boolean aplicado,String NoFactura,float total,Proveedores proveedor)
    {       
        manejoPedido.guardar(fecha, aplicado, NoFactura, total, proveedor);
        pedidoC = manejoPedido.listaDePedidoProveedores().get(manejoPedido.listaDePedidoProveedores().size()-1);
    }
    
    public void añadirDescripcionAUltimoPedido(int cantidad,float precioProducto,Productos producto)
    {
        descripcionPedido.guardar(cantidad, precioProducto, producto, pedidoC);
        listaDeDescripcion.add(descripcionPedido.listaDeDescripcionPedidoProveedores().get(descripcionPedido.listaDeDescripcionPedidoProveedores().size()-1));
    }
    
    public void asignarDescripcionAPedido (List<DescripcionPedidoProveedores> lista)
    {
        for (DescripcionPedidoProveedores descripcion:lista)
        {
            this.añadirDescripcionAUltimoPedido(descripcion.getCantidad(),descripcion.getPrecioProducto(),descripcion.getProductosidProductos());
        }
    }
    
    //procedimiento para borrar el pedido si no se ha terminado
    
    public void borrarPedidoEnCurso ()
    {
       
        manejoPedido.eliminar(this.pedidoC.getIdpedidoProveedores());
        for (DescripcionPedidoProveedores descPedido:this.listaDeDescripcion)
        {
            descripcionPedido.eliminar(descPedido.getIddescripcionP());
        }
    }
    
}
