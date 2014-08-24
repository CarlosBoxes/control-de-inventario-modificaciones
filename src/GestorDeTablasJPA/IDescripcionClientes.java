/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GestorDeTablasJPA;

import ControladoresJPA.DescripcionClientesJpaController;
import EntidadesJPA.Clientes;
import EntidadesJPA.DescripcionClientes;
import EntidadesJPA.Pedido;
import EntidadesJPA.Productos;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

/**
 *
 * @author Ch470
 */
public class IDescripcionClientes {
    
     public IDescripcionClientes (){}
 
    public String guardar(Productos pro,Clientes cli,Pedido pe,float cantidad,float subTotal,float precio)
    {
        try
        {
            DescripcionClientes descripcion = new DescripcionClientes();
            EntityManagerFactory emf = Conexion.getInstancia().getEMF();
            DescripcionClientesJpaController Controlador = new DescripcionClientesJpaController(emf);
            descripcion.setProductosidProductos(pro);
            descripcion.setClientesidCliente(cli);
            descripcion.setCantidad(cantidad);
            descripcion.setPedidoIdpedido(pe);
            descripcion.setTotal(subTotal);
            descripcion.setPrecio(precio);
            Controlador.create(descripcion);
             return "Descripcion de Clientes Ingresada Correctamente";
        }
        catch (Exception e)
        {
            return "Error al Ingresar la Lista de Productos";
        }    
    }
    
    public DescripcionClientes buscarDescripcionId(Integer Id)
     {
        EntityManagerFactory emf = Conexion.getInstancia().getEMF();
        EntityManager em = emf.createEntityManager();
        Query qr;
        qr = em.createNamedQuery("Descripcionclientes.findByIddescripcionclientes");
        qr.setParameter("iddescripcionclientes", Id);
        if (!qr.getResultList().isEmpty())
        {
            return (DescripcionClientes)qr.getSingleResult();
        }
        else
        {
            return null;  
        }     
     }
    
    public String modificar (DescripcionClientes Descripcion){
        try
        {
            EntityManagerFactory emf = Conexion.getInstancia().getEMF();
            DescripcionClientesJpaController controlador = new DescripcionClientesJpaController(emf);
            controlador.edit(Descripcion);
            return "Descripción Modificada Correctamente";
        }
        catch (Exception e)
        {
            return "Error al Modificar Descripción";
        } 
    }
    
    public String eliminar (int id){
        try
        {
          EntityManagerFactory emf = Conexion.getInstancia().getEMF();
          DescripcionClientesJpaController controlador = new DescripcionClientesJpaController (emf);
            controlador.destroy(id);
            return "Descripción de Pedido De Clientes Eliminada Correctamente";
        }
        catch (Exception e)
        {
            return "Error al Eliminar Descripción de Pedido De Clientes";
        }
    }
}
