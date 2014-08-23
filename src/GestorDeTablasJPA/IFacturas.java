/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GestorDeTablasJPA;

import ControladoresJPA.FacturasJpaController;
import EntidadesJPA.Clientes;
import EntidadesJPA.Facturas;
import EntidadesJPA.Pedido;
import EntidadesJPA.Vendedores;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

/**
 *
 * @author Ch470
 */
public class IFacturas {
     public IFacturas(){}
     public String guardar(String serie,int numero,float total, String CantidadLetras, Clientes cliente, Vendedores Vendedor, Pedido Pedido)
    {
        try{
            Facturas factura = new Facturas();
            EntityManagerFactory emf = Conexion.getInstancia().getEMF();
            FacturasJpaController controlador = new FacturasJpaController(emf);
            factura.setSerie(serie);
            factura.setNumero(numero);
            factura.setTotal(total);
            factura.setClientesidCliente(cliente);
            factura.setCantidadletras(CantidadLetras);
            factura.setVendedoridVendedor(Vendedor);
            factura.setAnulada(false);
            factura.setPedidoidPedido(Pedido);
            controlador.create(factura);
             return "Factura Creada Correctamente";
        }
        catch (Exception e){
            return "Error al Ingresar La Nueva Factura";
        }
           
    }
     
     
    
    public List<Facturas> listaDeFacturas(){
        
        EntityManagerFactory emf = Conexion.getInstancia().getEMF();
        FacturasJpaController controlador = new FacturasJpaController (emf);
        return controlador.findFacturasEntities();
       
    }
    
    public String modificar (Facturas  factura){
        try
        {
          EntityManagerFactory emf = Conexion.getInstancia().getEMF();
        FacturasJpaController controlador = new FacturasJpaController (emf);
            controlador.edit( factura);
            return "Factura Modificada Correctamente";
        }
        catch (Exception e)
        {
            return "Error al Modificar Factura";
        }
        
    }
   
    public Facturas buscarFacturaPorNumero(int Numero)
    {
        EntityManagerFactory emf = Conexion.getInstancia().getEMF();
        EntityManager em = emf.createEntityManager();
        Query qr;
        qr = em.createNamedQuery("Facturas.findByNumero");
        qr.setParameter("numero", Numero);
        List<Facturas> lista = qr.getResultList();
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
