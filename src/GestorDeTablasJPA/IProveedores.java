/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GestorDeTablasJPA;

import ControladoresJPA.ProveedoresJpaController;
import EntidadesJPA.Proveedores;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

/**
 *
 * @author Ch470
 */
public class IProveedores {
    public IProveedores(){}
     public String guardar(String nombre, String nit,String direccion,String telefono1,String telefono2)
    {
        try{
            Proveedores proveedor = new Proveedores();
            EntityManagerFactory emf = Conexion.getInstancia().getEMF();
            ProveedoresJpaController controlador = new ProveedoresJpaController(emf);
            proveedor.setNombre(nombre);
            proveedor.setNit(nit);
            proveedor.setDireccion(direccion);
            proveedor.setTelefono1(telefono1);
            proveedor.setTelefono2(telefono2);
            proveedor.setSaldo(0f);
            proveedor.setEliminado(false);
            controlador.create(proveedor);
             return "Nuevo Proveedor Creado Correctamente";
        }
        catch (Exception e){
            return "Error al Ingresar el Nuevo Proveedor";
        }
           
    }
    
    public List<Proveedores> listaDeProveedores(){
        EntityManagerFactory emf = Conexion.getInstancia().getEMF();
        EntityManager em = emf.createEntityManager();
        Query qr;
        qr = em.createNamedQuery("Proveedores.findByListaNombre");
        if (!qr.getResultList().isEmpty())
        {
            return qr.getResultList();
        }
        else
        {
            return null;  
        }
    }
    
    public String modificar (Proveedores proveedor)
    {
        try{
            EntityManagerFactory emf = Conexion.getInstancia().getEMF();
            ProveedoresJpaController controlador = new ProveedoresJpaController (emf);
            controlador.edit(proveedor);
            return "Proveedor Modificado Correctamente";
        }
        catch (Exception e)
        {
            return "Error al Modificar Proveedor";
        }
    }
    
    public String eliminar (int id){
        try
        {
            EntityManagerFactory emf = Conexion.getInstancia().getEMF();
            ProveedoresJpaController controlador = new ProveedoresJpaController (emf);
            controlador.destroy(id);
            return "Proveedor Eliminado Correctamente";
        }
        catch (Exception e)
        {
            return "Error al Eliminar Proveedor";
        }
    }
    
    public String eliminar (Proveedores proveedor)
    {
        try{
            EntityManagerFactory emf = Conexion.getInstancia().getEMF();
            ProveedoresJpaController controlador = new ProveedoresJpaController (emf);
            controlador.edit(proveedor);
            return "Proveedor Eliminado Correctamente";
        }
        catch (Exception e)
        {
            return "Error al Eliminar Proveedor";
        }
    }
    
    public List<Proveedores> buscarListaProveedorPorNombre(String nombreProveedor)
    {
        EntityManagerFactory emf = Conexion.getInstancia().getEMF();
        EntityManager em = emf.createEntityManager();
        Query qr;
        qr = em.createNamedQuery("Proveedores.findByNombreLike");
        qr.setParameter("nombre", "%"+nombreProveedor+"%");
        if (qr.getResultList().isEmpty())
        {
            return null;
        }
        else
        {
            return qr.getResultList();  
        }
    }
    
    public Proveedores buscarProveedorPorNombre(String nombreProveedor)
    {
        EntityManagerFactory emf = Conexion.getInstancia().getEMF();
        EntityManager em = emf.createEntityManager();
        Query qr;
        qr = em.createNamedQuery("Proveedores.findByNombre");
        qr.setParameter("nombre", nombreProveedor);
        if (!qr.getResultList().isEmpty())
        {
            return (Proveedores)qr.getSingleResult();
        }
        else
        {
            return null;  
        }
    }
    
    public Proveedores buscarProveedorPorId (int idProveedor)
     {
        EntityManagerFactory emf = Conexion.getInstancia().getEMF();
        EntityManager em = emf.createEntityManager();
        Query qr;
        qr = em.createNamedQuery("Proveedores.findByIdProveedores");
        qr.setParameter("idProveedores", idProveedor);
        List<Proveedores> lista = qr.getResultList();
        if (!qr.getResultList().isEmpty())
        {
            return (Proveedores)qr.getSingleResult();
        }
        else
        {
            return null;  
        }     
     }   
}
