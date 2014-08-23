/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GestorDeTablasJPA;

import ControladoresJPA.BodegaProductosJpaController;
import EntidadesJPA.BodegaProductos;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

/**
 *
 * @author Ch470
 */
public class IBodegaProductos {
    public IBodegaProductos (){}
     public String guardar(String nombre )
    {
        try{
            BodegaProductos bodegaProducto = new BodegaProductos();
            EntityManagerFactory emf = Conexion.getInstancia().getEMF();
            BodegaProductosJpaController controlador = new BodegaProductosJpaController(emf);
            bodegaProducto.setNombre(nombre);
            bodegaProducto.setEliminado(false);
            controlador.create(bodegaProducto);
             return "Nueva Bodega  De Productos Ingresada Correctamente";
        }
        catch (Exception e){
            return "Error al Ingresar la nueva bodega de productos";
        }
           
    }
    
    public List<BodegaProductos> listaDeBodegasDeProductos(){
        EntityManagerFactory emf = Conexion.getInstancia().getEMF();
        EntityManager em = emf.createEntityManager();
        Query qr;
        qr = em.createNamedQuery("Bodegaproductos.findByListaNombre");
        if(!qr.getResultList().isEmpty())
        {
            return qr.getResultList();
        }
        else
        {
            return null;
        }
    }
    
    
    public String modificar (BodegaProductos bodega){
        try
        {
             EntityManagerFactory emf = Conexion.getInstancia().getEMF();
            BodegaProductosJpaController controlador = new BodegaProductosJpaController (emf);
            controlador.edit(bodega);
            return "Bodega Modificada Correctamente";
        }
        catch (Exception e)
        {
            return "Error al Modificar Bodega";
        }  
    }
    public String eliminar (int id){
        try
        {
             EntityManagerFactory emf = Conexion.getInstancia().getEMF();
            BodegaProductosJpaController controlador = new BodegaProductosJpaController (emf);
            controlador.destroy(id);
            return "Bodega Eliminada Correctamente";
        }
        catch (Exception e)
        {
            return "Error al Eliminar Bodega";
        }
        
    }
    
    public String eliminar (BodegaProductos bodega){
        try
        {
             EntityManagerFactory emf = Conexion.getInstancia().getEMF();
            BodegaProductosJpaController controlador = new BodegaProductosJpaController (emf);
            controlador.edit(bodega);
            return "Bodega Eliminada Correctamente";
        }
        catch (Exception e)
        {
            return "Error al Eliminar Bodega";
        }  
    }
    
    public BodegaProductos retornarUltimoIngresado ()
    {        
        return this.listaDeBodegasDeProductos().get(this.listaDeBodegasDeProductos().size()-1);
    }
    
    public BodegaProductos buscarBodegaPorNombre (String nombreBodega)
    {
        EntityManagerFactory emf = Conexion.getInstancia().getEMF();
        EntityManager em = emf.createEntityManager();
        Query qr;
        qr = em.createNamedQuery("Bodegaproductos.findByNombre");
        qr.setParameter("nombre", nombreBodega);
        if(!qr.getResultList().isEmpty())
        {
            return (BodegaProductos)qr.getSingleResult();
        }
        else
        {
            return null;
        }
    }
    
    public BodegaProductos buscarBodegaPorId(int id)
    {
        EntityManagerFactory emf = Conexion.getInstancia().getEMF();
        EntityManager em = emf.createEntityManager();
        Query qr;
        qr = em.createNamedQuery("Bodegaproductos.findByIdbodega");
        qr.setParameter("idbodega", id);
        if(!qr.getResultList().isEmpty())
        {
            return (BodegaProductos)qr.getSingleResult();
        }
        else
        {
            return null;
        }
    }
    
    public List<BodegaProductos> buscarListaBodegasPorNombre(String nombreBodega)
    {
        EntityManagerFactory emf = Conexion.getInstancia().getEMF();
        EntityManager em = emf.createEntityManager();
        Query qr;
        qr = em.createNamedQuery("Bodegaproductos.findByNombreLike");
        qr.setParameter("nombre", "%"+nombreBodega+"%");
        if(!qr.getResultList().isEmpty())
        {
            return qr.getResultList();
        }
        else
        {
            return null;
        }
    }
}
