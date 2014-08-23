/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GestorDeTablasJPA;

import ControladoresJPA.ClientesJpaController;
import EntidadesJPA.Clientes;
import EntidadesJPA.TipoClientes;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

/**
 *
 * @author Ch470
 */
public class IClientes {
     public IClientes(){}
     public String guardar(String nombre,String razonSocial, String direccion,String nit,String telefonoCelular,String telefonoCasa,TipoClientes tipoCliente)
    {
        try{
            Clientes cliente = new Clientes();
            EntityManagerFactory emf = Conexion.getInstancia().getEMF();
            ClientesJpaController controlador = new ClientesJpaController(emf);
            cliente.setNombre(nombre);
            cliente.setRazonsocial(razonSocial);
            cliente.setDireccion(direccion);
            cliente.setNit(nit);
            cliente.setTelefonoCelular(telefonoCelular);
            cliente.setTelefonoCasa(telefonoCasa);
            cliente.setTipoClientesidTipoClientes(tipoCliente);
            cliente.setEliminado(false);
            controlador.create(cliente);
             return "Nuevo  Cliente Creado Correctamente";
        }
        catch (Exception e){
            return "Error al Ingresar el Nuevo Cliente";
        }
           
    }   
    
    public List<Clientes> listaDeClientes(){
        EntityManagerFactory emf = Conexion.getInstancia().getEMF();
        EntityManager em = emf.createEntityManager();
        Query qr;
        qr = em.createNamedQuery("Clientes.findByListaNombre");
        if (!qr.getResultList().isEmpty())
        {
            return qr.getResultList();
        }
        else
        {
            return null;  
        }  
    }
    
     public String modificar (Clientes cliente){
        try
        {
            EntityManagerFactory emf = Conexion.getInstancia().getEMF();
        ClientesJpaController controlador = new ClientesJpaController (emf);
            controlador.edit(cliente);
            return "Cliente Modificado Correctamente";
        }
        catch (Exception e)
        {
            return "Error al Modificar Cliente";
        }
    }
     
    public String eliminar (int id){
        try
        {
            EntityManagerFactory emf = Conexion.getInstancia().getEMF();
            ClientesJpaController controlador = new ClientesJpaController (emf);
            controlador.destroy(id);
            return "Cliente Eliminado Correctamente";
        }
        catch (Exception e)
        {
            return "Error al Eliminar Cliente";
        }
    }
    
    public String eliminar (Clientes cliente){
        try
        {
            EntityManagerFactory emf = Conexion.getInstancia().getEMF();
        ClientesJpaController controlador = new ClientesJpaController (emf);
            controlador.edit(cliente);
            return "Cliente Eliminado Correctamente";
        }
        catch (Exception e)
        {
            return "Error al Eliminado Cliente";
        }
    }
    
    public List<Clientes> buscarListaClientesPorNombre(String nombreCliente)
    {
        EntityManagerFactory emf = Conexion.getInstancia().getEMF();
        EntityManager em = emf.createEntityManager();
        Query qr;
        qr = em.createNamedQuery("Clientes.findByNombreLike");
        qr.setParameter("nombre","%"+nombreCliente+"%");
        if (!qr.getResultList().isEmpty())
        {
            return qr.getResultList();
        }
        else
        {
            return null;  
        }    
     }
    
    public Clientes buscarClientesPorNombre (String nombreCliente)
     {
        EntityManagerFactory emf = Conexion.getInstancia().getEMF();
        EntityManager em = emf.createEntityManager();
        Query qr;
        qr = em.createNamedQuery("Clientes.findByNombre");
        qr.setParameter("nombre",nombreCliente);
        if (!qr.getResultList().isEmpty())
        {
            return (Clientes)qr.getSingleResult();
        }
        else
        {
            return null;  
        }    
     }
    
    public Clientes buscarClientesPorNit(String Nit)
    {
        EntityManagerFactory emf = Conexion.getInstancia().getEMF();
        EntityManager em = emf.createEntityManager();
        Query qr;
        qr = em.createNamedQuery("Clientes.findByNitSolo");
        qr.setParameter("nit", Nit);
        if (!qr.getResultList().isEmpty())
        {
            return (Clientes)qr.getSingleResult();
        }
        else
        {
            return null;  
        }    
    }
    
    public Clientes buscarClientesPorId(Integer Id)
    {
        EntityManagerFactory emf = Conexion.getInstancia().getEMF();
        EntityManager em = emf.createEntityManager();
        Query qr;
        qr = em.createNamedQuery("Clientes.findByIdCliente");
        qr.setParameter("idCliente", Id);
        if (!qr.getResultList().isEmpty())
        {
            return (Clientes)qr.getSingleResult();
        }
        else
        {
            return null;  
        }    
    }
    
    public Clientes buscarClientesPorNitNombre(String Nit, String Nombre)
    {
        EntityManagerFactory emf = Conexion.getInstancia().getEMF();
        EntityManager em = emf.createEntityManager();
        Query qr;
        qr = em.createNamedQuery("Clientes.findByNit");
        qr.setParameter("nit", Nit);
        qr.setParameter("nombre", Nombre);
        if (!qr.getResultList().isEmpty())
        {
            return (Clientes)qr.getSingleResult();
        }
        else
        {
            return null;  
        }    
    }
}
