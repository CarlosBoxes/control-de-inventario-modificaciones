/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GestorDeTablasJPA;

import ControladoresJPA.DepositosJpaController;
import EntidadesJPA.Bancos;
import EntidadesJPA.Depositos;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

/**
 *
 * @author Ch470
 */
public class IDepositos {
    public IDepositos(){}
    public String guardar (float monto, String numeroDeBoleta,Bancos banco)
    {
        try
        {
            Depositos deposito = new Depositos();
            EntityManagerFactory emf = Conexion.getInstancia().getEMF();
            DepositosJpaController controlador = new DepositosJpaController(emf);
            deposito.setMonto(monto);
            deposito.setUsado(false);
            deposito.setNumeroDeBoleta(numeroDeBoleta);
            deposito.setBancosIdbancos(banco);
            deposito.setEliminado(false);
            deposito.setUsado(false);
            controlador.create(deposito);
            return "Datos De Depositos Ingresados Correctamente";
        }
        catch (Exception e)
                {
                    return "Error en el Ingresos de Datos";
                
        
                }
    }
    
    public List<Depositos> retornarListaDeDepositos ()
    {
        EntityManagerFactory emf = Conexion.getInstancia().getEMF();
        EntityManager em = emf.createEntityManager();
        Query qr;
        qr = em.createNamedQuery("Depositos.findByListaNumeroDeBoleta");
        if (!qr.getResultList().isEmpty())
        {
            return qr.getResultList();
        }
        else
        {
            return null;  
        } 
    }
    
     public String modificar (Depositos deposito){
        try
        {
           EntityManagerFactory emf = Conexion.getInstancia().getEMF();
        DepositosJpaController controlador = new DepositosJpaController(emf);
            controlador.edit(deposito);
            return "Deposito Modificado Correctamente";
        }
        catch (Exception e)
        {
            return "Error al Modificar Deposito";
        } 
    }
     
    public String eliminar (int id){
        try
        {
            EntityManagerFactory emf = Conexion.getInstancia().getEMF();
        DepositosJpaController controlador = new DepositosJpaController(emf);
            controlador.destroy(id);
            return "Deposito Eliminado Correctamente";
        }
        catch (Exception e)
        {
            return "Error al Eliminar Deposito";
        }
    }
    
    public String eliminar (Depositos deposito){
        try
        {
           EntityManagerFactory emf = Conexion.getInstancia().getEMF();
        DepositosJpaController controlador = new DepositosJpaController(emf);
            controlador.edit(deposito);
            return "Deposito Eliminado Correctamente";
        }
        catch (Exception e)
        {
            return "Error al Eliminar Deposito";
        } 
    }
    
    public Depositos buscarDepositoPorNumeroBoleta (String numeroBoleta)
     {
        EntityManagerFactory emf = Conexion.getInstancia().getEMF();
        EntityManager em = emf.createEntityManager();
        Query qr;
        qr = em.createNamedQuery("Depositos.findByNumeroDeBoleta");
        qr.setParameter("numeroDeBoleta", numeroBoleta);
        if (!qr.getResultList().isEmpty())
        {
            return (Depositos)qr.getSingleResult();
        }
        else
        {
            return null;  
        }     
     }
    
    public Depositos buscarDepositoPorNumeroBoletaUsada(String numeroBoleta)
     {
        EntityManagerFactory emf = Conexion.getInstancia().getEMF();
        EntityManager em = emf.createEntityManager();
        Query qr;
        qr = em.createNamedQuery("Depositos.findByNumeroDeBoletaUsada");
        qr.setParameter("numeroDeBoleta", numeroBoleta);
        if (!qr.getResultList().isEmpty())
        {
            return (Depositos)qr.getSingleResult();
        }
        else
        {
            return null;  
        }     
     }
    
}
