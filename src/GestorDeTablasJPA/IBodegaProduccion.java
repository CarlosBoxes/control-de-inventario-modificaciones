/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GestorDeTablasJPA;

import ControladoresJPA.BodegaProduccionJpaController;
import EntidadesJPA.BodegaProduccion;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

/**
 *
 * @author Ch470
 */
public class IBodegaProduccion {
    public  IBodegaProduccion(){}
    public String guardar(String nombre)
    {
        try{
            BodegaProduccion bodegaProduccion = new BodegaProduccion();
            EntityManagerFactory emf = Conexion.getInstancia().getEMF();
            BodegaProduccionJpaController controlador = new BodegaProduccionJpaController(emf);
            bodegaProduccion.setNombre(nombre);
            bodegaProduccion.setEliminado(false);
            controlador.create(bodegaProduccion);
             return "Nueva Bodega De Produccion Creada Correctamente";
        }
        catch (Exception e){
            return "Error al Ingresar La Nueva Bodega De Produccion";
        }
           
    }
    
    
    public List<BodegaProduccion> listaDeBodegasDeProduccion(){
        EntityManagerFactory emf = Conexion.getInstancia().getEMF();
        EntityManager em = emf.createEntityManager();
        Query qr;
        qr = em.createNamedQuery("Bodegaproduccion.findByListaNombre");
        if(!qr.getResultList().isEmpty())
        {
            return qr.getResultList();
        }
        else
        {
            return null;
        }
    }
    
    public String modificar (BodegaProduccion bodega){
        try
        {
            EntityManagerFactory emf = Conexion.getInstancia().getEMF();
            BodegaProduccionJpaController controlador = new BodegaProduccionJpaController (emf);
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
            BodegaProduccionJpaController controlador = new BodegaProduccionJpaController (emf);
            controlador.destroy(id);
            return "Bodega Eliminada Correctamente";
        }
        catch (Exception e)
        {
            return "Error al Eliminar Bodega";
        }  
    }
    
    public String eleiminar (BodegaProduccion bodega){
        try
        {
            EntityManagerFactory emf = Conexion.getInstancia().getEMF();
            BodegaProduccionJpaController controlador = new BodegaProduccionJpaController (emf);
            controlador.edit(bodega);
            return "Bodega Eliminada Correctamente";
        }
        catch (Exception e)
        {
            return "Error al Eliminar Bodega";
        }  
    }
    
    public BodegaProduccion buscarBodegaPorNombre(String nombreBodega)
    {
        EntityManagerFactory emf = Conexion.getInstancia().getEMF();
        EntityManager em = emf.createEntityManager();
        Query qr;
        qr = em.createNamedQuery("Bodegaproduccion.findByNombre");
        qr.setParameter("nombre", nombreBodega);
        if(!qr.getResultList().isEmpty())
        {
            return (BodegaProduccion)qr.getSingleResult();
        }
        else
        {
            return null;
        }
    }
    
    public BodegaProduccion buscarBodegaPorId(int Id)
    {
        EntityManagerFactory emf = Conexion.getInstancia().getEMF();
        EntityManager em = emf.createEntityManager();
        Query qr;
        qr = em.createNamedQuery("Bodegaproduccion.findByIdbodegaProduccion");
        qr.setParameter("idbodegaProduccion", Id);
        if(!qr.getResultList().isEmpty())
        {
            return (BodegaProduccion)qr.getSingleResult();
        }
        else
        {
            return null;
        }
    }
    
    public List<BodegaProduccion> buscarListaBodegasPorNombre(String nombreBodega)
    {
        EntityManagerFactory emf = Conexion.getInstancia().getEMF();
        EntityManager em = emf.createEntityManager();
        Query qr;
        qr = em.createNamedQuery("Bodegaproduccion.findByNombreLike");
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
