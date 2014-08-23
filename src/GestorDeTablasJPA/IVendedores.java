/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GestorDeTablasJPA;

import ControladoresJPA.VendedoresJpaController;
import ControladoresJPA.exceptions.IllegalOrphanException;
import ControladoresJPA.exceptions.NonexistentEntityException;
import EntidadesJPA.TipoVendedores;
import EntidadesJPA.Vendedores;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

/**
 *
 * @author Ch470
 */
public class IVendedores {
     public IVendedores(){}
     public String guardar(String nombre,String apellido,String telefonoCelular, String telefonoCasa,TipoVendedores tipoVendedor, String DPI)
    {
        try{
            float saldo =0;
            Vendedores vendedor = new Vendedores();
            EntityManagerFactory emf = Conexion.getInstancia().getEMF();
            VendedoresJpaController controlador = new VendedoresJpaController(emf);
            vendedor.setNombre(nombre);
            vendedor.setApellido(apellido);
            vendedor.setTelefonoCelular(telefonoCelular);
            vendedor.setTelefonoCasa(telefonoCasa);
            vendedor.setTipoVendedoresidTipoVendedores(tipoVendedor);
            vendedor.setSaldoAnterior(saldo);
            vendedor.setEliminado(false);
            vendedor.setDpi(DPI);
            controlador.create(vendedor);
             return "Nuevo  Vendedor Creado Correctamente";
        }
        catch (Exception e){
            return "Error al Ingresar el Nuevo Vendedor";
        }
           
    }
    
    public List<Vendedores> listaDeVendedores(){
        EntityManagerFactory emf = Conexion.getInstancia().getEMF();
        EntityManager em = emf.createEntityManager();
        Query qr;
        qr = em.createNamedQuery("Vendedores.findByListaNombre");
        if(!qr.getResultList().isEmpty())
        {
            return qr.getResultList();
        }
        else
        {
            return null;
        }
    }
    
    public String modificar ( Vendedores vendedor)
    {
        try{
            EntityManagerFactory emf = Conexion.getInstancia().getEMF();
            VendedoresJpaController controlador = new VendedoresJpaController (emf);
            controlador.edit(vendedor);
            return "Vendedor Modificado Correctamente";
        }
        catch (Exception e)
        {
            return "Error al Modificar Vendedor";
        }
    }
    
    public String eliminar (int id){
        try
        {
            EntityManagerFactory emf = Conexion.getInstancia().getEMF();
            VendedoresJpaController controlador = new VendedoresJpaController (emf);
            controlador.destroy(id);
            return "Vendedor Eliminado Correctamente";
        }
        catch (IllegalOrphanException | NonexistentEntityException e)
        {
            return "Error al Eliminar Vendedor";
        }
    }
    
    public String eliminar ( Vendedores vendedor)
    {
        try{
            EntityManagerFactory emf = Conexion.getInstancia().getEMF();
            VendedoresJpaController controlador = new VendedoresJpaController (emf);
            controlador.edit(vendedor);
            return "Vendedor Eliminado Correctamente";
        }
        catch (Exception e)
        {
            return "Error al Eliminar Vendedor";
        }
    }
    
    public List<Vendedores> buscarVendedoresPorNombre (String nombreVendedor)
     {
        EntityManagerFactory emf = Conexion.getInstancia().getEMF();
        EntityManager em = emf.createEntityManager();
        Query qr;
        qr = em.createNamedQuery("Vendedores.findByNombre");
        qr.setParameter("nombre", "%"+nombreVendedor+"%");
        if(!qr.getResultList().isEmpty())
        {
            return qr.getResultList();
        }
        else
        {
            return null;
        }
     }
    
    public Vendedores buscarVendedorPorId (int id)
    {
        EntityManagerFactory emf = Conexion.getInstancia().getEMF();
        EntityManager em = emf.createEntityManager();
        Query qr;
        qr = em.createNamedQuery("Vendedores.findByIdvendedores");
        qr.setParameter("idvendedores", id);
        if (!qr.getResultList().isEmpty())
        {
            return (Vendedores)qr.getSingleResult();
        }
        else
        {
            return null; 
        }
    }
    public boolean modificarSaldoAnterior ()
    {
        return true;
    }
}
