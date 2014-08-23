/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GestorDeTablasJPA;

import ControladoresJPA.TipoClientesJpaController;
import EntidadesJPA.TipoClientes;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

/**
 *
 * @author Ch470
 */
public class ITipoClientes {
     public ITipoClientes(){}
     public String guardar(String nombre)
    {
        try{
            TipoClientes tipoClientes = new TipoClientes();
            EntityManagerFactory emf = Conexion.getInstancia().getEMF();
            TipoClientesJpaController controlador = new TipoClientesJpaController(emf);
            tipoClientes.setNombre(nombre);
            tipoClientes.setEliminado(false);
            controlador.create(tipoClientes);
             return "Nuevo Tipo de Clientes Creado Correctamente";
        }
        catch (Exception e){
            return "Error al Ingresar el Nuevo Tipo de Clientes";
        }
           
    }
    
    public List<TipoClientes> listaDeTipoDeClientes(){
        EntityManagerFactory emf = Conexion.getInstancia().getEMF();
        EntityManager em = emf.createEntityManager();
        Query qr;
        qr = em.createNamedQuery("TipoClientes.findByListaNombre");
        if (!qr.getResultList().isEmpty())
        {
            return qr.getResultList();
        }
        else
        {
            return null;      
        }
    }
    
    public String modificar (TipoClientes tipoCliente)
    {
        try{
            EntityManagerFactory emf = Conexion.getInstancia().getEMF();
            TipoClientesJpaController controlador = new TipoClientesJpaController (emf);
            controlador.edit(tipoCliente);
            return "Tipo de Cliente Modificado Correctamente";
        }
        catch (Exception e)
        {
            return "Error al Modificar Tipo de Cliente";
        }
    }
    
    public String eliminar (int id){
        try
        {
            EntityManagerFactory emf = Conexion.getInstancia().getEMF();
            TipoClientesJpaController controlador = new TipoClientesJpaController (emf);
            controlador.destroy(id);
            return "Tipo de Cliente Eliminado Correctamente";
        }
        catch (Exception e)
        {
            return "Error al Eliminar Tipo de Cliente";
        }
    }
    
    public String eliminar (TipoClientes tipoCliente)
    {
        try{
            EntityManagerFactory emf = Conexion.getInstancia().getEMF();
            TipoClientesJpaController controlador = new TipoClientesJpaController (emf);
            controlador.edit(tipoCliente);
            return "Tipo de Cliente Eliminado Correctamente";
        }
        catch (Exception e)
        {
            return "Error al Eliminar Tipo de Cliente";
        }
    }
    
    public TipoClientes buscarTipoClientePorNombre (String nombreTipoCliente)
     {
        EntityManagerFactory emf = Conexion.getInstancia().getEMF();
        EntityManager em = emf.createEntityManager();
        Query qr;
        qr = em.createNamedQuery("TipoClientes.findByNombre");
        qr.setParameter("nombre", nombreTipoCliente);
        if (!qr.getResultList().isEmpty())
        {
            return (TipoClientes)qr.getSingleResult();
        }
        else
        {
            return null;
        }
     }
    
    public List<TipoClientes> buscarListaTipoClientePorNombre(String nombreTipoCliente)
     {
        EntityManagerFactory emf = Conexion.getInstancia().getEMF();
        EntityManager em = emf.createEntityManager();
        Query qr;
        qr = em.createNamedQuery("TipoClientes.findByNombreLike");
        qr.setParameter("nombre", "%"+nombreTipoCliente+"%");
        if (!qr.getResultList().isEmpty())
        {
            return qr.getResultList();
        }
        else
        {
            return null;      
        }
     }
    
    public TipoClientes buscarTipoClientePorId(int IdTipoCliente)
    {
        EntityManagerFactory emf = Conexion.getInstancia().getEMF();
        EntityManager em = emf.createEntityManager();
        Query qr;
        qr = em.createNamedQuery("TipoClientes.findByIdTipoClientes");
        qr.setParameter("idTipoClientes", IdTipoCliente);
        if (!qr.getResultList().isEmpty())
        {
            return (TipoClientes)qr.getSingleResult();
        }
        else
        {
            return null;      
        }
    }
    
}
