/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GestorDeTablasJPA;

import ControladoresJPA.PedidoMateriaPrimaJpaController;
import EntidadesJPA.PedidoMateriaPrima;
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
public class IPedidoMateriaPrima {
    
    public  IPedidoMateriaPrima(){}
    
    
    public String guardar(Date fecha, boolean aplicado,float total,Proveedores proveedor)
    {
        try{
            PedidoMateriaPrima pedidoMateriaPrima = new PedidoMateriaPrima();
            EntityManagerFactory emf = Conexion.getInstancia().getEMF();
            PedidoMateriaPrimaJpaController controlador = new PedidoMateriaPrimaJpaController(emf);
            pedidoMateriaPrima.setFecha(fecha);
            pedidoMateriaPrima.setAplicado(aplicado);
            pedidoMateriaPrima.setTotal(total);
            pedidoMateriaPrima.setProveedoresidProveedores(proveedor);
            controlador.create(pedidoMateriaPrima);
             return "Nuevo Pedido de Materia Prima Creado Correctamente";
        }
        catch (Exception e){
            return "Error al Ingresar el Nuevo Pedido De Materia Prima";
        }
           
    }
    
    public List<PedidoMateriaPrima> listaDePedidoMateriaPrima(){
        EntityManagerFactory emf = Conexion.getInstancia().getEMF();
        PedidoMateriaPrimaJpaController controlador = new PedidoMateriaPrimaJpaController (emf);
        return controlador.findPedidoMateriaPrimaEntities();
    }
    
    public String modificar ( PedidoMateriaPrima pedidoMateriaPrima){
        try
        {
            EntityManagerFactory emf = Conexion.getInstancia().getEMF();
            PedidoMateriaPrimaJpaController controlador = new PedidoMateriaPrimaJpaController (emf);
            controlador.edit(pedidoMateriaPrima);
            return "Pedido de Materia Prima Modificado Correctamente";
        }
        catch (Exception e)
        {
            return "Error al Modificar Pedido de Materia Prima";
        }
        
    }
    public String eliminar (int id){
        try
        {
            EntityManagerFactory emf = Conexion.getInstancia().getEMF();
            PedidoMateriaPrimaJpaController controlador = new PedidoMateriaPrimaJpaController (emf);    
            controlador.destroy(id);
            return "Pedido de Materia Prima Eliminado Correctamente";
        }
        catch (Exception e)
        {
            return "Error al Eliminar Pedido de Materia Prima";
        
    }
    }
    
    public PedidoMateriaPrima retornarUltimoIngresado()
    {
        return this.listaDePedidoMateriaPrima().get(this.listaDePedidoMateriaPrima().size()-1);        
    }
    
    public PedidoMateriaPrima buscarPedidoPorId (int IdPedido)
     {
        EntityManagerFactory emf = Conexion.getInstancia().getEMF();
        EntityManager em = emf.createEntityManager();
        Query qr;
        qr = em.createNamedQuery("PedidoMateriaPrima.findByIdpedidoMP");
        qr.setParameter("idpedidoMP", IdPedido);     
        List<PedidoMateriaPrima> lista = qr.getResultList();
        if (lista.isEmpty())
        {
            return null;
        }
        else
        {
            return lista.get(0);  
        } 
     }
    
    public List<PedidoMateriaPrima> buscarPedido()
    {
        EntityManagerFactory emf = Conexion.getInstancia().getEMF();
        EntityManager em = emf.createEntityManager();
        Query qr;
        qr = em.createNamedQuery("PedidoMateriaPrima.findByLista");
        if (qr.getResultList().isEmpty())
        {
            return null;
        }
        else
        {
            return qr.getResultList();  
        } 
     }
}
