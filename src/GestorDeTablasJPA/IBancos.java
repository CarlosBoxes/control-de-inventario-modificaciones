/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GestorDeTablasJPA;

import ControladoresJPA.BancosJpaController;
import EntidadesJPA.Bancos;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

/**
 *
 * @author Ch470
 */
public class IBancos {
    
    public IBancos (){}
    
    public String guardar (String nombre,String telefono)
    {
        try
        {
            Bancos banco = new Bancos();
            EntityManagerFactory emf = Conexion.getInstancia().getEMF();
            BancosJpaController controlador = new BancosJpaController(emf);
            banco.setNombre(nombre);
            banco.setTelefono(telefono);
            banco.setEliminado(false);
            if (!this.buscarExistente(nombre))
            {
                return "Banco Ya Existente";
            }
            else
            {
                controlador.create(banco);
                return "Datos De Bancos Ingresados Correctamente";
            }
        }
        catch (Exception e)
                {
                    return "Error en el Ingresos de Datos";
                }
    }
    
    public List<Bancos> retornarListaDeBancos ()
    {
        EntityManagerFactory emf = Conexion.getInstancia().getEMF();
        EntityManager em = emf.createEntityManager();
        Query qr;
        qr = em.createNamedQuery("Bancos.findByListaNombre");
        if(!qr.getResultList().isEmpty())
        {
            return qr.getResultList();
        }
        else
        {
            return null;
        }
    }
    
    public String modificar(Bancos banco)
    {
        try
        {
            EntityManagerFactory emf = Conexion.getInstancia().getEMF();
            BancosJpaController controlador = new BancosJpaController(emf); 
            controlador.edit(banco);
            return "Banco Modificado Correctamente"; 
        }
        catch (Exception e)
        {
            return "Error en Modificacion de Banco";
        }
    }
    
    public String eliminar(int id)
    {
        try
        {
            EntityManagerFactory emf = Conexion.getInstancia().getEMF();
            BancosJpaController controlador = new BancosJpaController(emf); 
            controlador.destroy(id);
            return "Banco Eliminado Correctamente";
        }
        catch (Exception e)
        {
            return "Error en Eliminación de Banco";
        }
    }
    
    public String eliminar(Bancos banco)
    {
        try
        {
            EntityManagerFactory emf = Conexion.getInstancia().getEMF();
            BancosJpaController controlador = new BancosJpaController(emf); 
            controlador.edit(banco);
            return "Banco Eliminado Correctamente"; 
        }
        catch (Exception e)
        {
            return "Error en Eliminación de Banco";
        }
    }
    
     public Bancos buscarBancoPorNombre(String nombreBancos)
     {
        EntityManagerFactory emf = Conexion.getInstancia().getEMF();
        EntityManager em = emf.createEntityManager();
        Query qr;
        qr = em.createNamedQuery("Bancos.findByNombre");
        qr.setParameter("nombre", nombreBancos);
        if(!qr.getResultList().isEmpty())
        {
            return (Bancos)qr.getSingleResult();
        }
        else
        {
            return null;
        }
     }
     
     public Bancos buscarBancoPorId(int id)
     {
        EntityManagerFactory emf = Conexion.getInstancia().getEMF();
        EntityManager em = emf.createEntityManager();
        Query qr;
        qr = em.createNamedQuery("Bancos.findByIdbancos");
        qr.setParameter("idbancos", id);
        if(!qr.getResultList().isEmpty())
        {
            return (Bancos)qr.getSingleResult();
        }
        else
        {
            return null;
        }        
     }
     
     public List<Bancos> buscarListaBancosPorNombre(String nombreBancos)
     {
        EntityManagerFactory emf = Conexion.getInstancia().getEMF();
        EntityManager em = emf.createEntityManager();
        Query qr;
        qr = em.createNamedQuery("Bancos.findByNombreLike");
        qr.setParameter("nombre", "%"+nombreBancos+"%");
        if(!qr.getResultList().isEmpty())
        {
            return  qr.getResultList();
        }
        else
        {
            return null;
        }                 
     }
     
     public Boolean buscarExistente (String nombreBancos)
     {
         EntityManagerFactory emf = Conexion.getInstancia().getEMF();
        EntityManager em = emf.createEntityManager();
        Query qr;
        qr = em.createNamedQuery("Bancos.findByNombre");
        qr.setParameter("nombre", nombreBancos);
        List<Bancos> lista = qr.getResultList();
        if (lista.isEmpty())                
            return true;                
        else
            return false;    
     }
    
}
