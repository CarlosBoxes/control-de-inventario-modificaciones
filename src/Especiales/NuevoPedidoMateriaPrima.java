/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Especiales;

import EntidadesJPA.DescripcionPedidoMateriaPrima;
import EntidadesJPA.MateriaPrima;
import EntidadesJPA.PedidoMateriaPrima;
import EntidadesJPA.Proveedores;
import GestorDeTablasJPA.IDescripcionPedidoMateriaPrima;
import GestorDeTablasJPA.IPedidoMateriaPrima;
import GestorDeTablasJPA.IProveedores;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Ch470
 */
public class NuevoPedidoMateriaPrima {
    
    private IPedidoMateriaPrima manejoPedido = new IPedidoMateriaPrima ();
    private IDescripcionPedidoMateriaPrima descripcionPedido = new IDescripcionPedidoMateriaPrima ();
    private PedidoMateriaPrima pedidoC = new PedidoMateriaPrima ();
    private Proveedores proveedor = new Proveedores();
    private IProveedores manejoProveedor= new IProveedores();
    public NuevoPedidoMateriaPrima(){
        manejoPedido = new IPedidoMateriaPrima ();
        descripcionPedido = new IDescripcionPedidoMateriaPrima ();
        pedidoC = new PedidoMateriaPrima ();
        proveedor = new Proveedores();
        manejoProveedor= new IProveedores();
        listaDeDescripcion = new ArrayList();
    }
    
     
    
    
    private List<DescripcionPedidoMateriaPrima> listaDeDescripcion = new ArrayList();
    
    public void crearNuevoPedidoSinDescripcion (Date fecha,boolean aplicado,float total,Proveedores proveedor)
    {       
        manejoPedido.guardar(fecha, aplicado, total, proveedor);
        pedidoC = manejoPedido.retornarUltimoIngresado();
    }
    
    public void añadirDescripcionAUltimoPedido(float cantidad,float precioProducto,MateriaPrima producto,float subTotal)
    {
        descripcionPedido.guardar(cantidad,precioProducto,producto,this.pedidoC,subTotal);
        listaDeDescripcion.add(descripcionPedido.retornarUltimoIngresado());
    }
    
    public void asignarDescripcionAPedido (List<DescripcionPedidoMateriaPrima> lista)
    {
        for (DescripcionPedidoMateriaPrima descripcion:lista)
        {
            this.añadirDescripcionAUltimoPedido(descripcion.getCantidad(),descripcion.getPrecioMateriaPrima(),descripcion.getMateriaPrimaidmateriaPrima(),descripcion.getSubTotal());
        }
    }
    
    //procedimiento para borrar el pedido si no se ha terminado
    
    public void borrarPedidoEnCurso ()
    {
       
        manejoPedido.eliminar(this.pedidoC.getIdpedidoMP());
        for (DescripcionPedidoMateriaPrima descPedido:this.listaDeDescripcion)
        {
            descripcionPedido.eliminar(descPedido.getIddescripcionMP());
        }
    }
    
    
}
