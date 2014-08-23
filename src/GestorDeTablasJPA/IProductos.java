/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GestorDeTablasJPA;

import ControladoresJPA.ProductosJpaController;
import ControladoresJPA.exceptions.IllegalOrphanException;
import ControladoresJPA.exceptions.NonexistentEntityException;
import EntidadesJPA.Productos;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

/**
 *
 * @author Ch470
 */
public class IProductos {
    public IProductos(){}
    public String guardar(String nombre,String presentacion,int unidadDeMedida,float precioCosto,float precioVenta,Date fechaDeVencimiento,
            String descripcion,String categoria,boolean cambio,float devoluciones)
    {
        try{
            Productos producto = new Productos();
            EntityManagerFactory emf = Conexion.getInstancia().getEMF();
            ProductosJpaController controlador = new ProductosJpaController(emf);
            producto.setNombre(nombre);
            producto.setPresentacion(presentacion);
            producto.setUnidadDeMedida(unidadDeMedida);
            producto.setPrecioCosto(precioCosto);
            producto.setPrecioVenta(precioVenta);
            producto.setFechaDeVencimiento(fechaDeVencimiento);
            producto.setDescripcion(descripcion);
            producto.setCategoria(categoria);
            producto.setEliminado(false);
            producto.setCambio(cambio);
            producto.setDevoluciones(devoluciones);
            controlador.create(producto);
            return "Nuevo Producto Ingresado Correctamente";
        }
        catch (Exception e){
            return "Error al Ingresar El Nuevo Producto";
        }
           
    }
    
    public List<Productos> listaDeProductos(){
        EntityManagerFactory emf = Conexion.getInstancia().getEMF();
        EntityManager em = emf.createEntityManager();
        Query qr;
        qr = em.createNamedQuery("Productos.findByListaNombre");
        if(!qr.getResultList().isEmpty())
        {
            return qr.getResultList();
        }
        else
        {
            return null;
        } 
    }
    
    public String modificar (Productos productos)
    {
        try{
            EntityManagerFactory emf = Conexion.getInstancia().getEMF();
            ProductosJpaController controlador = new ProductosJpaController (emf);
            controlador.edit(productos);
            return "Producto Modificado Correctamente";
        }
        catch (Exception e)
        {
            return "Error al Modificar Producto";
        }
    }
    
    public String eliminar (int id){
        try
        {
            EntityManagerFactory emf = Conexion.getInstancia().getEMF();
            ProductosJpaController controlador = new ProductosJpaController (emf); 
            controlador.destroy(id);
            return "Poducto Eliminado Correctamente";
        }
        catch (IllegalOrphanException | NonexistentEntityException e)
        {
            return "Error al Eliminar Producto";
        }
    }
    
    public String eliminar (Productos productos)
    {
        try{
            EntityManagerFactory emf = Conexion.getInstancia().getEMF();
            ProductosJpaController controlador = new ProductosJpaController (emf);
            controlador.edit(productos);
            return "Producto Eliminado Correctamente";
        }
        catch (Exception e)
        {
            return "Error al Eliminar Producto";
        }
    }
    
    public Productos retornarUltimoIngresado()
    {
        return this.listaDeProductos().get(this.listaDeProductos().size()-1);
    }
    
    public Productos buscarProductoPorId(int idProducto)
    {
        EntityManagerFactory emf = Conexion.getInstancia().getEMF();
        EntityManager em = emf.createEntityManager();
        Query qr;
        qr = em.createNamedQuery("Productos.findByIdProductos");
        qr.setParameter("idProductos",idProducto);
        if (!qr.getResultList().isEmpty())
        {
            return (Productos)qr.getSingleResult();
        }
        else
        {
            return null;  
        }
         
     }
    
    public List<Productos> buscarListaProductoPorNombre(String nombreProducto)
    {
        EntityManagerFactory emf = Conexion.getInstancia().getEMF();
        EntityManager em = emf.createEntityManager();
        Query qr;
        qr = em.createNamedQuery("Productos.findByNombreLike");
        qr.setParameter("nombre","%"+nombreProducto+"%");
        if(!qr.getResultList().isEmpty())
        {
            return qr.getResultList();
        }
        else
        {
            return null;
        }            
     }
    
    public Productos buscarProductoPorNombre(String Producto)
    {
        EntityManagerFactory emf = Conexion.getInstancia().getEMF();
        EntityManager em = emf.createEntityManager();
        Query qr;
        qr = em.createNamedQuery("Productos.findByNombre");
        qr.setParameter("nombre",Producto);
        List<Productos> lista = qr.getResultList();
        if(!lista.isEmpty())
        {
            return (Productos)qr.getSingleResult();
        }
        else
        {
            return null;
        }                      
     }
    
    public List<Productos> ListaProductosVencidos()
    {
        EntityManagerFactory emf = Conexion.getInstancia().getEMF();
        EntityManager em = emf.createEntityManager();
        Query qr;
        qr = em.createNamedQuery("Productos.findByFechaDeVencimiento");
        qr.setParameter("fechaDeVencimiento",new Date());
        if(!qr.getResultList().isEmpty())
        {
            return qr.getResultList();
        }
        else
        {
            return null;
        }
    }
    
    public Productos Existente(String nombre, String presentacion)
    {
        EntityManagerFactory emf = Conexion.getInstancia().getEMF();
        EntityManager em = emf.createEntityManager();
        Query qr;
        qr = em.createNamedQuery("Productos.findByExistente");
        qr.setParameter("nombre", nombre);
        qr.setParameter("presentacion",presentacion);
        if(!qr.getResultList().isEmpty())
        {
            return (Productos)qr.getSingleResult();
        }
        else
        {
            return null;
        }
    }
}
