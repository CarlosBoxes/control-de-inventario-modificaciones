/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GestorDeTablasJPA;

import ControladoresJPA.DescripcionPedidoProveedoresJpaController;
import EntidadesJPA.DescripcionPedidoProveedores;
import EntidadesJPA.PedidoProveedores;
import EntidadesJPA.Productos;
import java.util.List;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Ch470
 */
public class IDescripcionPedidoProveedores {
    public IDescripcionPedidoProveedores(){}
     public String guardar(int cantidad, float precioProducto,Productos producto,PedidoProveedores pedido)
    {
        try{
            DescripcionPedidoProveedores descripcionPedidoProveedores = new DescripcionPedidoProveedores();
            EntityManagerFactory emf = Conexion.getInstancia().getEMF();
            DescripcionPedidoProveedoresJpaController controlador = new DescripcionPedidoProveedoresJpaController(emf);
            descripcionPedidoProveedores.setCantidad(cantidad);
            descripcionPedidoProveedores.setPrecioProducto(precioProducto);
            descripcionPedidoProveedores.setProductosidProductos(producto);
            descripcionPedidoProveedores.setPedidoProveedoresIdpedidoProveedores(pedido);
            controlador.create(descripcionPedidoProveedores);
             return "Nuevo Descripcion del Pedio a Proveedores Creado Correctamente";
        }
        catch (Exception e){
            return "Error al Ingresar La Nueva Descripcion del Pedido A Proveedores";
        }
           
    }
    
    public List<DescripcionPedidoProveedores> listaDeDescripcionPedidoProveedores(){
        EntityManagerFactory emf = Conexion.getInstancia().getEMF();
        DescripcionPedidoProveedoresJpaController controlador = new DescripcionPedidoProveedoresJpaController (emf);
        return controlador.findDescripcionPedidoProveedoresEntities();
    }
    public String modificar (DescripcionPedidoProveedores  descripcionDePedidoProveedores){
        try
        {
          EntityManagerFactory emf = Conexion.getInstancia().getEMF();
        DescripcionPedidoProveedoresJpaController controlador = new DescripcionPedidoProveedoresJpaController (emf);
            controlador.edit( descripcionDePedidoProveedores);
            return "Descripción de Pedido A Proveedores Modificada Correctamente";
        }
        catch (Exception e)
        {
            return "Error al Modificar La Descripción de Pedido A Proveedores";
        }
        
    }
    public String eliminar (int id){
        try
        {
          EntityManagerFactory emf = Conexion.getInstancia().getEMF();
         DescripcionPedidoProveedoresJpaController controlador = new DescripcionPedidoProveedoresJpaController (emf);
            controlador.destroy(id);
            return "Descripción de Pedido A Proveedores Eliminada Correctamente";
        }
        catch (Exception e)
        {
            return "Error al Eliminar Descripción de Pedido A Proveedores";
        
    }
    }
    public DescripcionPedidoProveedores retornarUltimoIngresado()
    {
        return this.listaDeDescripcionPedidoProveedores().get(this.listaDeDescripcionPedidoProveedores().size()-1);
    }
    
}
