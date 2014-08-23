/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GestorDeTablasJPA;

import ControladoresJPA.ChequesProveedoresJpaController;
import EntidadesJPA.Bancos;
import EntidadesJPA.ChequesProveedores;
import EntidadesJPA.Proveedores;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

/**
 *
 * @author Ch470
 */
public class IChequesProveedores {
    public IChequesProveedores(){}
    public String guardar (int numeroDeCheque,float monto,Date fecha, Bancos banco, Proveedores proveedor)
    {
     try
        {
            ChequesProveedores chequesProveedor = new ChequesProveedores();
            EntityManagerFactory emf = Conexion.getInstancia().getEMF();
            ChequesProveedoresJpaController controlador = new ChequesProveedoresJpaController(emf);
            chequesProveedor.setNumero(numeroDeCheque);
            chequesProveedor.setMonto(monto);
            chequesProveedor.setFecha(fecha);
            chequesProveedor.setBancosIdbancos(banco);
            chequesProveedor.setProveedoresidProveedores(proveedor);
            chequesProveedor.setEliminado(false);
            controlador.create(chequesProveedor);
            return "Datos De Los Cheques De Proveedores Ingresados Correctamente";
        }
        catch (Exception e)
                {
                    return "Error en el Ingresos de Datos";
                
        
                }
    }
    
    public List<ChequesProveedores> retornarListaDeChequesProveedores ()
    {
        EntityManagerFactory emf = Conexion.getInstancia().getEMF();
        EntityManager em = emf.createEntityManager();
        Query qr;
        qr = em.createNamedQuery("ChequesProveedores.findByListaNumero");
        if(!qr.getResultList().isEmpty())
        {
            return qr.getResultList();
        }
        else
        {
            return null;
        }
    }
    
    public String modificar (ChequesProveedores cheque){
        try
        {
            EntityManagerFactory emf = Conexion.getInstancia().getEMF();
            ChequesProveedoresJpaController controlador = new ChequesProveedoresJpaController(emf);
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
            ChequesProveedoresJpaController controlador = new ChequesProveedoresJpaController(emf);
            controlador.destroy(id);
            return "Cheque Eliminado Correctamente";
        }
        catch (Exception e)
        {
            return "Error al Eliminar Cheque";      
        }
    }
    
    public String eliminar (ChequesProveedores cheque){
        try
        {
            EntityManagerFactory emf = Conexion.getInstancia().getEMF();
            ChequesProveedoresJpaController controlador = new ChequesProveedoresJpaController(emf);
            controlador.edit(cheque);
            return "Cheque Eliminado Correctamente";
        }
        catch (Exception e)
        {
            return "Error al Eliminar Cheque";
        }    
    }
    
    public ChequesProveedores buscarChequesNumeroChueque(int numeroCheque)
     {
        EntityManagerFactory emf = Conexion.getInstancia().getEMF();
        EntityManager em = emf.createEntityManager();
        Query qr;
        qr = em.createNamedQuery("ChequesProveedores.findByNumero");
        qr.setParameter("numero", numeroCheque);
        if(!qr.getResultList().isEmpty())
        {
            return (ChequesProveedores)qr.getSingleResult();
        }
        else
        {
            return null;
        }
     }
    
}
