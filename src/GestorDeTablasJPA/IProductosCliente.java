/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GestorDeTablasJPA;

import ControladoresJPA.ProductosClientesJpaController;
import EntidadesJPA.ProductosClientes;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

/**
 *
 * @author Luis
 */
public class IProductosCliente {
    public IProductosCliente (){}
    
    
    
    public String guardar(List<ProductosClientes> Productos)
    {
        try
        {
            EntityManagerFactory emf = Conexion.getInstancia().getEMF();
            ProductosClientesJpaController Controlador = new ProductosClientesJpaController(emf);
            for(ProductosClientes Producto: Productos)
            {
                Controlador.create(Producto);
            }
             return "Lista de Productos Ingresada Correctamente";
        }
        catch (Exception e)
        {
            return "Error al Ingresar la Lista de Productos";
        }
           
    }

    public String modificar(List<ProductosClientes> Productos)
    {
        try{
            EntityManagerFactory emf = Conexion.getInstancia().getEMF();
            ProductosClientesJpaController Controlador = new ProductosClientesJpaController(emf);
            for(ProductosClientes Producto: Productos)
            {    
                Controlador.edit(Producto);
            }
            return "Lista de Productos Modificada Correctamente";
        }
        catch (Exception e)
        {
            return "Error al Modificar la Lista de Productos";
        }
    }
    
     public String eliminar(Integer id)
    {
        try{
            EntityManagerFactory emf = Conexion.getInstancia().getEMF();
            ProductosClientesJpaController controlador = new ProductosClientesJpaController(emf);
            controlador.destroy(id);
            return "Producto Eliminado Correctamente";
        }
        catch (Exception e)
        {
            return "Error al Elinimar Producto";
        }
    }
     
    public ProductosClientes buscarProductoPorId(int idProducto)
    {
        EntityManagerFactory emf = Conexion.getInstancia().getEMF();
        EntityManager em = emf.createEntityManager();
        Query qr;
        qr = em.createNamedQuery("ProductosClientes.findByIdproductosespecialclientes");
        qr.setParameter("idproductosespecialclientes",idProducto);
        if (!qr.getResultList().isEmpty())
        {
            return (ProductosClientes)qr.getSingleResult();
        }
        else
        {
            return null;  
        }
         
     }
    
}
