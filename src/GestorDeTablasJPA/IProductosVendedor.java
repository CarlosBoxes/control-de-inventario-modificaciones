/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GestorDeTablasJPA;

import ControladoresJPA.ProductosVendedoresJpaController;
import EntidadesJPA.ProductosVendedores;
import EntidadesJPA.TipoVendedores;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

/**
 *
 * @author Luis
 */
public class IProductosVendedor {
    public IProductosVendedor (){}
    
    
    
    public String guardar(List<ProductosVendedores> Productos)
    {
        try
        {
            EntityManagerFactory emf = Conexion.getInstancia().getEMF();
            ProductosVendedoresJpaController Controlador = new ProductosVendedoresJpaController(emf);
            for(ProductosVendedores Producto: Productos)
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

    public String modificar(List<ProductosVendedores> Productos)
    {
        try{
            EntityManagerFactory emf = Conexion.getInstancia().getEMF();
            ProductosVendedoresJpaController Controlador = new ProductosVendedoresJpaController(emf);
            for(ProductosVendedores Producto: Productos)
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
            ProductosVendedoresJpaController controlador = new ProductosVendedoresJpaController(emf);
            controlador.destroy(id);
            return "Producto Eliminado Correctamente";
        }
        catch (Exception e)
        {
            return "Error al Elinimar Producto";
        }
    }
     
    public ProductosVendedores buscarProductoPorId(int idProducto)
    {
        EntityManagerFactory emf = Conexion.getInstancia().getEMF();
        EntityManager em = emf.createEntityManager();
        Query qr;
        qr = em.createNamedQuery("ProductosVendedores.findByIdlisitaproductosvendedores");
        qr.setParameter("idlisitaproductosvendedores",idProducto);
        if (!qr.getResultList().isEmpty())
        {
            return (ProductosVendedores)qr.getSingleResult();
        }
        else
        {
            return null;  
        }
         
     }
    public List<ProductosVendedores> listaDeProductosPorIdTipo(TipoVendedores tipo)
    {
        EntityManagerFactory emf = Conexion.getInstancia().getEMF();
        EntityManager em = emf.createEntityManager();
        Query qr;
        qr = em.createNamedQuery("ProductosVendedores.findByIdTipoVendedor");
        qr.setParameter("tipovendedoresidTipoVendedores",tipo);
        if (!qr.getResultList().isEmpty())
        {
            return qr.getResultList();
        }
        else
        {
            return null;  
        }
         
     }
    
}
