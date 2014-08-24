/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Especiales;

import EntidadesJPA.DescripcionPedido;
import EntidadesJPA.Pedido;
import EntidadesJPA.Productos;
import EntidadesJPA.Vendedores;
import GestorDeTablasJPA.IDescripcionPedido;
import GestorDeTablasJPA.IPedido;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Ch470
 */
public class NuevoPedido {
    private IPedido manejoPedido;
    private IDescripcionPedido descripcionPedido;
    private Pedido pedidoC;
    private Vendedores vendedor;
    
    public NuevoPedido (){
        manejoPedido = new IPedido ();
        descripcionPedido = new IDescripcionPedido ();
        pedidoC = new Pedido ();
        vendedor = new Vendedores();
    }
  
    
    
    private List<DescripcionPedido> listaDeDescripcion = new ArrayList();
    
    public void crearNuevoPedidoSinDescripcion (Date fecha,boolean aplicado,float subTotal,float total,Vendedores vendedor, String Obsevaciones)
    {   
        this.vendedor = vendedor;
        manejoPedido.guardar(fecha, aplicado,subTotal, total, vendedor, Obsevaciones);
        pedidoC = manejoPedido.listaDePedidos().get(manejoPedido.listaDePedidos().size()-1);
        this.listaDeDescripcion.clear();
    }
    
    public void añadirDescripcionAUltimoPedido(float cantidad,float subTotal,Productos idProductos,float precio)
    {
        
        descripcionPedido.guardar(cantidad, subTotal, idProductos,this.pedidoC,precio);
        listaDeDescripcion.add(descripcionPedido.listaDeDescripcionesDePedidos().get(descripcionPedido.listaDeDescripcionesDePedidos().size()-1));
        
    }
    
    public void asignarDescripcionAPedido (List<DescripcionPedido> lista)
    {
        for (DescripcionPedido descripcion:lista)
        {
            this.añadirDescripcionAUltimoPedido(descripcion.getCantidad(), descripcion.getSubTotal(), descripcion.getProductosidProductos(),descripcion.getPrecio());
        }
    }
    
    //procedimiento para borrar el pedido si no se ha terminado
    
    public void borrarPedidoEnCurso ()
    {
       
        manejoPedido.eliminar(this.pedidoC.getIdpedido());
        for (DescripcionPedido descPedido:this.listaDeDescripcion)
        {
            descripcionPedido.eliminar(descPedido.getIddescripcionPedido());
        }
    }
    
    
    
}
