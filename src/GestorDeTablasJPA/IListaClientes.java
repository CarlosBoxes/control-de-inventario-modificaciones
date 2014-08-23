/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GestorDeTablasJPA;

import ControladoresJPA.ListaClientesJpaController;
import EntidadesJPA.ListaClientes;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

/**
 *
 * @author Luis
 */
public class IListaClientes {
    public IListaClientes (){}
    
    
    
    public String guardar(List<ListaClientes> Clientes)
    {
        try
        {
            EntityManagerFactory emf = Conexion.getInstancia().getEMF();
            ListaClientesJpaController Controlador = new ListaClientesJpaController(emf);
            for(ListaClientes Cliente: Clientes)
            {
                Controlador.create(Cliente);
            }
             return "Lista de Clientes Ingresada Correctamente";
        }
        catch (Exception e)
        {
                return "Error al Ingresar la Lista de Clientes";
        }
           
    }

    public String modificar(List<ListaClientes> Clientes)
    {
        try{
            EntityManagerFactory emf = Conexion.getInstancia().getEMF();
            ListaClientesJpaController Controlador = new ListaClientesJpaController(emf);
            for(ListaClientes Cliente: Clientes)
            {    
                Controlador.edit(Cliente);
            }
            return "Lista de Clientes Modificada Correctamente";
        }
        catch (Exception e)
        {
            return "Error al Modificar la Lista de Clietnes";
        }
    }
    
     public String eliminar(Integer id)
    {
        try{
            EntityManagerFactory emf = Conexion.getInstancia().getEMF();
            ListaClientesJpaController controlador = new ListaClientesJpaController(emf);
            controlador.destroy(id);
            return "Cliente Eliminado Correctamente";
        }
        catch (Exception e)
        {
            return "Error al Elinimar Cliente";
        }
    }
     
    public ListaClientes buscarClientePorId(int idCliente)
    {
        EntityManagerFactory emf = Conexion.getInstancia().getEMF();
        EntityManager em = emf.createEntityManager();
        Query qr;
        qr = em.createNamedQuery("ListaClientes.findByIdlistaclientes");
        qr.setParameter("idlistaclientes",idCliente);
        if (!qr.getResultList().isEmpty())
        {
            return (ListaClientes)qr.getSingleResult();
        }
        else
        {
            return null;  
        }
         
     }
    
}
