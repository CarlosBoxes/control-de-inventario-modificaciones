/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GestorDeTablasJPA;

import ControladoresJPA.PedidoJpaController;
import EntidadesJPA.Pedido;
import EntidadesJPA.Vendedores;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

/**
 *
 * @author Ch470
 */
public class IPedido {
     public IPedido(){}
     public String guardar(Date fecha,boolean aplicado,float subTotal,float total,Vendedores vendedor,String observacion)
    {
        try{
            Pedido pedido = new Pedido();
            EntityManagerFactory emf = Conexion.getInstancia().getEMF();
            PedidoJpaController controlador = new PedidoJpaController(emf);
            pedido.setFecha(fecha);
            pedido.setAplicado(aplicado);
            pedido.setSubtotal(subTotal);
            pedido.setLiquidado(false);
            pedido.setObservaciones(observacion);
            pedido.setContraseñas((float)0.00);
            pedido.setIsr((float)0.00);
            pedido.setTotal(total);
            pedido.setVendedoresIdvendedores(vendedor);
            controlador.create(pedido);
             return "Nuevo  Pedido Creado Correctamente";
        }
        catch (Exception e){
            return "Error al Ingresar el Nuevo Pedido";
        }
           
    }
     
     
    
    public List<Pedido> listaDePedidos(){
        EntityManagerFactory emf = Conexion.getInstancia().getEMF();
        PedidoJpaController controlador = new PedidoJpaController (emf);
        return controlador.findPedidoEntities();
    }
    
    public List<Pedido> ListaPedidosMes()
    {
       EntityManagerFactory emf = Conexion.getInstancia().getEMF();
        EntityManager em = emf.createEntityManager();
        Query qr;
        qr = em.createNamedQuery("Pedido.findByLista");
        Calendar c = Calendar.getInstance();
        Date d = new Date();
        String FechaActual = "01/"+String.valueOf(c.get(Calendar.MONTH)+1)+"/"+String.valueOf(c.get(Calendar.YEAR));
        DateFormat Formato = DateFormat.getDateInstance(DateFormat.SHORT);      
        Date Fecha = null;
        try 
        {
            Fecha = Formato.parse(FechaActual);
        } 
        catch (ParseException ex) 
        {
            Logger.getLogger(IPedido.class.getName()).log(Level.SEVERE, null, ex);
        } 
        qr.setParameter("fecha",Fecha);
        if (qr.getResultList().isEmpty())
        {
            return null;
        }
        else
        {
            return qr.getResultList();
        }  
    }
    
    public String modificar ( Pedido pedido){
        try
        {
            EntityManagerFactory emf = Conexion.getInstancia().getEMF();
            PedidoJpaController controlador = new PedidoJpaController (emf);
            controlador.edit(pedido);
            return "Pedido Modificado Correctamente";
        }
        catch (Exception e)
        {
            return "Error al Modificar Pedido";
        }
        
    }
    public String eliminar (int id){
        try
        {
            EntityManagerFactory emf = Conexion.getInstancia().getEMF();
            PedidoJpaController controlador = new PedidoJpaController (emf);
            controlador.destroy(id);
            return "Pedido Eliminado Correctamente";
        }
        catch (Exception e)
        {
            return "Error al Eliminar Pedido";
        
    }
    }
    
    public Pedido retornarUltimoPedidoIngresado ()
    {
        return this.listaDePedidos().get(this.listaDePedidos().size()-1);
    }
    
    public Pedido buscarPedidoPorId (int IdPedido)
     {
        EntityManagerFactory emf = Conexion.getInstancia().getEMF();
        EntityManager em = emf.createEntityManager();
        Query qr;
        qr = em.createNamedQuery("Pedido.findByIdpedido");
        qr.setParameter("idpedido", IdPedido);
     
        List<Pedido> lista = qr.getResultList();
        if (lista.isEmpty())
        {
            return null;
        }
        else
        {
            return lista.get(0);  
        } 
     }
}
