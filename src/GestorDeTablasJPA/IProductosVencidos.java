/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GestorDeTablasJPA;

import ControladoresJPA.ProductosVencidosJpaController;
import EntidadesJPA.Productos;
import EntidadesJPA.ProductosVencidos;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

/**
 *
 * @author Ch470
 */
public class IProductosVencidos {
    public IProductosVencidos(){}
      public String guardar(Productos producto,int cantidad)
    {
        try{
            ProductosVencidos productoVencido = new ProductosVencidos();
            EntityManagerFactory emf = Conexion.getInstancia().getEMF();
            ProductosVencidosJpaController controlador = new ProductosVencidosJpaController(emf);
            productoVencido.setProductosidProductos(producto);
            productoVencido.setCantidad(cantidad);
            controlador.create(productoVencido);
             return "Nuevo Producto Vencido Ingresado Correctamente";
        }
        catch (Exception e){
            return "Error al Ingresar El Nuevo Producto";
        }
           
    }
    
    public List<ProductosVencidos> listaDeProductosVencidos(){
        EntityManagerFactory emf = Conexion.getInstancia().getEMF();
        ProductosVencidosJpaController controlador = new ProductosVencidosJpaController (emf);
        return controlador.findProductosVencidosEntities();
    }
    
      public String modificar (ProductosVencidos productoVencidos)
    {
        try{
            EntityManagerFactory emf = Conexion.getInstancia().getEMF();
            ProductosVencidosJpaController controlador = new ProductosVencidosJpaController (emf);
            controlador.edit(productoVencidos);
            return "Producto Vencido Modificado Correctamente";
        }
        catch (Exception e)
        {
            return "Error al Eliminar Producto Defectuoso";
        }
    }
    
    public String eliminar (int id){
        try
        {
            EntityManagerFactory emf = Conexion.getInstancia().getEMF();
            ProductosVencidosJpaController controlador = new ProductosVencidosJpaController (emf);
            controlador.destroy(id);
            return "Poducto Vencido Eliminado Correctamente";
        }
        catch (Exception e)
        {
            return "Error al Eliminar Producto Vencido";
        
    }
    }
    
    public ProductosVencidos buscarProductoVencidoPorId(int IdProducto)
     {
        EntityManagerFactory emf = Conexion.getInstancia().getEMF();
        EntityManager em = emf.createEntityManager();
        Query qr;
        qr = em.createNamedQuery("ProductosVencidos.findByIdProductosVencidos");
        qr.setParameter("idProductosVencidos", IdProducto);
        List<ProductosVencidos> lista = qr.getResultList();
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
