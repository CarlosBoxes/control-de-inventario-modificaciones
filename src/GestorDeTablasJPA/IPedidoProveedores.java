/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GestorDeTablasJPA;

import ControladoresJPA.PedidoProveedoresJpaController;
import EntidadesJPA.PedidoProveedores;
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
public class IPedidoProveedores {
    public IPedidoProveedores(){}
     public String guardar(Date fecha,boolean aplicado,String noFactura,float total,Proveedores proveedor,float subTotal,float descuento)
    {
        try{
            PedidoProveedores pedidoProveedores = new PedidoProveedores();
            EntityManagerFactory emf = Conexion.getInstancia().getEMF();
            PedidoProveedoresJpaController controlador = new PedidoProveedoresJpaController(emf);
            pedidoProveedores.setFecha(fecha);
            pedidoProveedores.setAplicado(aplicado);
            pedidoProveedores.setNoFactura(noFactura);
            pedidoProveedores.setTotal(total);
            pedidoProveedores.setDescuento(descuento);
            pedidoProveedores.setSubTotal(subTotal);
            pedidoProveedores.setProveedoresidProveedores(proveedor);
            pedidoProveedores.setSaldo(0f);
            pedidoProveedores.setAlmacenado(false);
            controlador.create(pedidoProveedores);
             return "Nuevo Pedido a Proveedores Creado Correctamente";
        }
        catch (Exception e){
            return "Error al Ingresar el Nuevo Pedido a Proveedor";
        }
           
    }
    
    public List<PedidoProveedores> listaDePedidoProveedores(){
        EntityManagerFactory emf = Conexion.getInstancia().getEMF();
        PedidoProveedoresJpaController controlador = new PedidoProveedoresJpaController (emf);
        return controlador.findPedidoProveedoresEntities();
    }
    
     public String modificar ( PedidoProveedores pedidoProveedores){
        try
        {
            EntityManagerFactory emf = Conexion.getInstancia().getEMF();
            PedidoProveedoresJpaController controlador = new PedidoProveedoresJpaController (emf);
            controlador.edit(pedidoProveedores);
            return "Pedido a Proveedores  Modificado Correctamente";
        }
        catch (Exception e)
        {
            return "Error al Modificar Pedido  a Proveedores";
        }
        
    }
    public String eliminar (int id){
        try
        {
            EntityManagerFactory emf = Conexion.getInstancia().getEMF();
            PedidoProveedoresJpaController controlador = new PedidoProveedoresJpaController (emf);  
            controlador.destroy(id);
            return "Pedido  a Proveedores Eliminado Correctamente";
        }
        catch (Exception e)
        {
            return "Error al Eliminar Pedido  a Proveedores";
        
    }
    }
    
    public PedidoProveedores retornarUltimoIngresado()
    {
        return this.listaDePedidoProveedores().get(this.listaDePedidoProveedores().size()-1);
    }
    
    public PedidoProveedores buscarPedidoPorId (int IdPedido)
     {
        EntityManagerFactory emf = Conexion.getInstancia().getEMF();
        EntityManager em = emf.createEntityManager();
        Query qr;
        qr = em.createNamedQuery("PedidoProveedores.findByIdpedidoProveedores");
        qr.setParameter("idpedidoProveedores", IdPedido);     
        List<PedidoProveedores> lista = qr.getResultList();
        if (lista.isEmpty())
        {
            return null;
        }
        else
        {
            return lista.get(0);  
        } 
     }
    
    public List<PedidoProveedores> buscarPedidoPorProveedor(Proveedores IdProveedor)
     {
        EntityManagerFactory emf = Conexion.getInstancia().getEMF();
        EntityManager em = emf.createEntityManager();
        Query qr;
        qr = em.createNamedQuery("PedidoProveedores.findByIdProveedor");
        qr.setParameter("idProveedor", IdProveedor);     
        if (qr.getResultList().isEmpty())
        {
            return null;
        }
        else
        {
            return qr.getResultList();  
        } 
     }
    
    public List<PedidoProveedores> buscarPedidos()
     {
        EntityManagerFactory emf = Conexion.getInstancia().getEMF();
        EntityManager em = emf.createEntityManager();
        Query qr;
        qr = em.createNamedQuery("PedidoProveedores.findByLista");     
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
