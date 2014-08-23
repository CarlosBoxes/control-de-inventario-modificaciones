/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GestorDeTablasJPA;

import ControladoresJPA.TipoVendedoresJpaController;
import EntidadesJPA.TipoVendedores;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

/**
 *
 * @author Ch470
 */
public class ITipoVendedor {
    public ITipoVendedor(){}
     public String guardar(String nombre, boolean Lista)
    {
        try{
            TipoVendedores tipoVendedor = new TipoVendedores();
            EntityManagerFactory emf = Conexion.getInstancia().getEMF();
            TipoVendedoresJpaController controlador = new TipoVendedoresJpaController(emf);
            tipoVendedor.setNombre(nombre);
            tipoVendedor.setListaproductos(Lista);
            tipoVendedor.setEliminado(false);
            controlador.create(tipoVendedor);
             return "Nuevo  Tipo de Vendedor Creado Correctamente";
        }
        catch (Exception e){
            return "Error al Ingresar el Nuevo Tipo de Vendedor";
        }
           
    }
     
     
    
    public List<TipoVendedores> listaDeTipoDeVendedores(){
        EntityManagerFactory emf = Conexion.getInstancia().getEMF();
        EntityManager em = emf.createEntityManager();
        Query qr;
        qr = em.createNamedQuery("TipoVendedores.findByListaNombre");
        if (!qr.getResultList().isEmpty())
        {
            return qr.getResultList();
        }
        else
        {
             return null; 
        }      
    }
    
    public String modificar (TipoVendedores tipoVendedores)
    {
        try{
            EntityManagerFactory emf = Conexion.getInstancia().getEMF();
            TipoVendedoresJpaController controlador = new TipoVendedoresJpaController (emf);
            controlador.edit(tipoVendedores);
            return "Tipo de Vendedor Modificado Correctamente";
        }
        catch (Exception e)
        {
            return "Error al Modificar Tipo de Vendedor";
        }
    }
    
    public String eliminar (int id){
        try
        {
            EntityManagerFactory emf = Conexion.getInstancia().getEMF();
            TipoVendedoresJpaController controlador = new TipoVendedoresJpaController (emf);
            controlador.destroy(id);
            return "Tipo de Vendedor Eliminado Correctamente";
        }
        catch (Exception e)
        {
            return "Error al Eliminar Tipo de Vendedor";
        }
    }
    
    public String eliminar(TipoVendedores tipoVendedores)
    {
        try{
            EntityManagerFactory emf = Conexion.getInstancia().getEMF();
            TipoVendedoresJpaController controlador = new TipoVendedoresJpaController (emf);
            controlador.edit(tipoVendedores);
            return "Tipo de Vendedor Eliminado Correctamente";
        }
        catch (Exception e)
        {
            return "Error al Eliminar Tipo de Vendedor";
        }
    }
    
    public TipoVendedores buscarTipoVendedorPorNombre (String nombreTipoVendedor)
     {
        EntityManagerFactory emf = Conexion.getInstancia().getEMF();
        EntityManager em = emf.createEntityManager();
        Query qr;
        qr = em.createNamedQuery("TipoVendedores.findByNombre");
        qr.setParameter("nombre", nombreTipoVendedor);
        if (!qr.getResultList().isEmpty())
        {
            return (TipoVendedores)qr.getSingleResult();
        }
        else
        {
             return null; 
        }   
     }
    
    public TipoVendedores buscarTipoVendedorPorId(int id)
     {
        EntityManagerFactory emf = Conexion.getInstancia().getEMF();
        EntityManager em = emf.createEntityManager();
        Query qr;
        qr = em.createNamedQuery("TipoVendedores.findByIdTipoVendedores");
        qr.setParameter("idTipoVendedores", id);
        if (!qr.getResultList().isEmpty())
        {
            return (TipoVendedores)qr.getSingleResult();
        }
        else
        {
             return null; 
        }   
     }
    
    public List<TipoVendedores> buscarListaTipoVendedorPorNombre(String nombreTipoVendedor)
     {
        EntityManagerFactory emf = Conexion.getInstancia().getEMF();
        EntityManager em = emf.createEntityManager();
        Query qr;
        qr = em.createNamedQuery("TipoVendedores.findByNombreLike");
        qr.setParameter("nombre", "%"+nombreTipoVendedor+"%");
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
