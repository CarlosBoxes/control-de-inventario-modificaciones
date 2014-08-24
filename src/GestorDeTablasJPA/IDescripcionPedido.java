/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GestorDeTablasJPA;

import ControladoresJPA.DescripcionPedidoJpaController;
import EntidadesJPA.DescripcionPedido;
import EntidadesJPA.Pedido;
import EntidadesJPA.Productos;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

/**
 *
 * @author Ch470
 */
public class IDescripcionPedido {
     public IDescripcionPedido(){}
     
     public String guardar(float cantidad,float subTotal,Productos producto,Pedido pedido,float precio)
    {
        try{
            DescripcionPedido descripcionPedido = new DescripcionPedido();
            EntityManagerFactory emf = Conexion.getInstancia().getEMF();
            DescripcionPedidoJpaController controlador = new DescripcionPedidoJpaController(emf);
            descripcionPedido.setCantidad(cantidad);
            descripcionPedido.setSubTotal(subTotal);
            descripcionPedido.setPrecio(precio);
            descripcionPedido.setProductosidProductos(producto);
            descripcionPedido.setPedidoIdpedido(pedido);
            controlador.create(descripcionPedido);
             return "Nueva  Descripcion de Pedido Creado Correctamente";
        }
        catch (Exception e){
            return "Error al Ingresar el Nueva Descripcion dePedido";
        }
           
    }
     
     
    
    public List<DescripcionPedido> listaDeDescripcionesDePedidos(){
        EntityManagerFactory emf = Conexion.getInstancia().getEMF();
        DescripcionPedidoJpaController controlador = new DescripcionPedidoJpaController (emf);
        return controlador.findDescripcionPedidoEntities();
    }
    
    public String modificar (DescripcionPedido  descripcionDePedido){
        try
        {
          EntityManagerFactory emf = Conexion.getInstancia().getEMF();
        DescripcionPedidoJpaController controlador = new DescripcionPedidoJpaController (emf);
            controlador.edit( descripcionDePedido);
            return "Descripción de Pedido De Clientes Modificada Correctamente";
        }
        catch (Exception e)
        {
            return "Error al Modificar La Descripción de Pedido De Clientes";
        }
        
    }
    public String eliminar (int id){
        try
        {
          EntityManagerFactory emf = Conexion.getInstancia().getEMF();
        DescripcionPedidoJpaController controlador = new DescripcionPedidoJpaController (emf);
            controlador.destroy(id);
            return "Descripción de Pedido De Clientes Eliminada Correctamente";
        }
        catch (Exception e)
        {
            return "Error al Eliminar Descripción de Pedido De Clientes";
        }
    }
    
    public DescripcionPedido BuscarDescripcion(Pedido pedido, Productos producto, float precio)
    {
        EntityManagerFactory emf = Conexion.getInstancia().getEMF();
        EntityManager em = emf.createEntityManager();
        Query qr ;
        qr = em.createNamedQuery("Descripcionpedido.findByPedido");
        qr.setParameter("idpedido", pedido);
        List lista  = qr.getResultList();
        
        for (Object desc:lista)
        {
            DescripcionPedido desPe = (DescripcionPedido)desc;
            if ((desPe.getProductosidProductos().equals(producto) )&&(desPe.getPrecio() == precio ))
            {
                return desPe;
            }
        }
        
        {
            return null;  
        } 
    }
   
    
    public DescripcionPedido retornarUltimoIngresado()
    {
        return this.listaDeDescripcionesDePedidos().get(this.listaDeDescripcionesDePedidos().size()-1);
    }
}
