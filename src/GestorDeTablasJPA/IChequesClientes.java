/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GestorDeTablasJPA;

import ControladoresJPA.ChequesClientesJpaController;
import EntidadesJPA.Bancos;
import EntidadesJPA.ChequesClientes;
import EntidadesJPA.Clientes;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

/**
 *
 * @author Ch470
 */
public class IChequesClientes {
    public IChequesClientes(){}
    
    public String guardar (int numeroDeCheque,float monto,Date fecha, Bancos banco, Clientes cliente)
    {
        try
        {
            ChequesClientes chequesCliente = new ChequesClientes();
            EntityManagerFactory emf = Conexion.getInstancia().getEMF();
            ChequesClientesJpaController controlador = new ChequesClientesJpaController(emf);
            chequesCliente.setNumero(numeroDeCheque);
            chequesCliente.setMonto(monto);
            chequesCliente.setUsado(false);
            chequesCliente.setFecha(fecha);
            chequesCliente.setBancosIdbancos(banco);
            chequesCliente.setClientesidCliente(cliente);
            chequesCliente.setEliminado(false);
            controlador.create(chequesCliente);
            return "Datos De Los Cheques De Clientes Ingresados Correctamente";
        }
        catch (Exception e)
                {
                    return "Error en el Ingresos de Datos";
                
        
                }
    }
    
    public List<ChequesClientes> retornarListaDeChequesClientes ()
    {
        EntityManagerFactory emf = Conexion.getInstancia().getEMF();
        EntityManager em = emf.createEntityManager();
        Query qr;
        qr = em.createNamedQuery("ChequesClientes.findByListaNumero");
        if (!qr.getResultList().isEmpty())
        {
            return qr.getResultList();
        }
        else
        {
            return null;      
        } 
    }
    
    public String modificar (ChequesClientes cheque){
        try
        {
             EntityManagerFactory emf = Conexion.getInstancia().getEMF();
            ChequesClientesJpaController controlador = new ChequesClientesJpaController(emf);
            controlador.edit(cheque);
            return "Cheque Modificado Correctamente";
        }
        catch (Exception e)
        {
            return "Error al Modificar Cheque";
        }       
    }
    
    public String eliminar (int id){
        try
        {
             EntityManagerFactory emf = Conexion.getInstancia().getEMF();
        ChequesClientesJpaController controlador = new ChequesClientesJpaController(emf);
            controlador.destroy(id);
            return "Cheque Eliminado Correctamente";
        }
        catch (Exception e)
        {
            return "Error al Eliminar Cheque";
        }
    }
    
    public String elimiar (ChequesClientes cheque){
        try
        {
             EntityManagerFactory emf = Conexion.getInstancia().getEMF();
            ChequesClientesJpaController controlador = new ChequesClientesJpaController(emf);
            controlador.edit(cheque);
            return "Cheque Eliminado Correctamente";
        }
        catch (Exception e)
        {
            return "Error al Eliminar Cheque";
        }       
    }
    
    public ChequesClientes buscarChequesNumeroChueque(int numeroCheque)
     {
        EntityManagerFactory emf = Conexion.getInstancia().getEMF();
        EntityManager em = emf.createEntityManager();
        Query qr;
        qr = em.createNamedQuery("ChequesClientes.findByNumero");
        qr.setParameter("numero", numeroCheque);
        List<ChequesClientes> lista = qr.getResultList();
        if (!qr.getResultList().isEmpty())
        {
            return (ChequesClientes)qr.getSingleResult();
        }
        else
        {
            return null;      
        }      
     }
    
    public ChequesClientes buscarChequesNumeroChuequeUsado(int numeroCheque)
    {
        EntityManagerFactory emf = Conexion.getInstancia().getEMF();
        EntityManager em = emf.createEntityManager();
        Query qr;
        qr = em.createNamedQuery("ChequesClientes.findByNumeroUsado");
        qr.setParameter("numero", numeroCheque);
        List<ChequesClientes> lista = qr.getResultList();
        if (!qr.getResultList().isEmpty())
        {
            return (ChequesClientes)qr.getSingleResult();
        }
        else
        {
            return null;      
        }      
     }
}
