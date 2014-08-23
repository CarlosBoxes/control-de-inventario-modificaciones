/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GestorDeTablasJPA;

import ControladoresJPA.ProductosDefectuosoJpaController;
import EntidadesJPA.Productos;
import EntidadesJPA.ProductosDefectuoso;
import EntidadesJPA.Vendedores;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

/**
 *
 * @author Ch470
 */
public class IProductosDefectuoso {
    public IProductosDefectuoso (){}
     public String guardar(Productos producto,int cantidad,String descripcion, Vendedores Vendedor)
    {
        try{
            ProductosDefectuoso productoDefectuoso = new ProductosDefectuoso();
            EntityManagerFactory emf = Conexion.getInstancia().getEMF();
            ProductosDefectuosoJpaController controlador = new ProductosDefectuosoJpaController(emf);
            productoDefectuoso.setProductosidProductos(producto);
            productoDefectuoso.setCantidad(cantidad);
            productoDefectuoso.setDescripcion(descripcion);
            productoDefectuoso.setVendedoresIdvendedores(Vendedor);
            controlador.create(productoDefectuoso);
             return "Nuevo Producto Defectuoso Ingresado Correctamente";
        }
        catch (Exception e){
            return "Error al Ingresar El Nuevo Producto";
        }
           
    }
    
    public List<ProductosDefectuoso> listaDeProductosDefectuosos(){
        EntityManagerFactory emf = Conexion.getInstancia().getEMF();
        EntityManager em = emf.createEntityManager();
        Query qr;
        qr = em.createNamedQuery("ProductosDefectuoso.findAll");
        if (!qr.getResultList().isEmpty())
        {
            return qr.getResultList();
        }
        else
        {
            return null;  
        }
    }
    
         public String modificar (ProductosDefectuoso productoDefectuoso)
    {
        try{
            EntityManagerFactory emf = Conexion.getInstancia().getEMF();
            ProductosDefectuosoJpaController controlador = new ProductosDefectuosoJpaController (emf);  
            controlador.edit(productoDefectuoso);
            return "Producto Defectuoso Modificado Correctamente";
        }
        catch (Exception e)
        {
            return "Error al Modificar Producto Defectuoso";
        }
    }
    
    public String eliminar (int id){
        try
        {
            EntityManagerFactory emf = Conexion.getInstancia().getEMF();
            ProductosDefectuosoJpaController controlador = new ProductosDefectuosoJpaController (emf);
            controlador.destroy(id);
            return "Poducto Defectuoso Eliminado Correctamente";
        }
        catch (Exception e)
        {
            return "Error al Eliminar Producto Defectuoso";
        
    }
    }
    
    public ProductosDefectuoso buscarProductoDefectusosPorId(int IdProducto)
     {
        EntityManagerFactory emf = Conexion.getInstancia().getEMF();
        EntityManager em = emf.createEntityManager();
        Query qr;
        qr = em.createNamedQuery("ProductosDefectuoso.findByIdProductoDefectuoso");
        qr.setParameter("idProductoDefectuoso", IdProducto);
        List<ProductosDefectuoso> lista = qr.getResultList();
        if (lista.isEmpty())
        {
            return null;
        }
        else
        {
            return lista.get(0);  
        }
     }
    
}
