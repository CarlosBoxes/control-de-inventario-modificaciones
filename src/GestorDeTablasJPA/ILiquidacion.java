/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GestorDeTablasJPA;

import ControladoresJPA.LiquidacionJpaController;
import EntidadesJPA.Liquidacion;
import EntidadesJPA.Pedido;
import java.util.List;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Ch470
 */
public class ILiquidacion {
     public ILiquidacion(){}
     public String guardar(float subTotal, float total,Pedido pedido)
    {
        try{
            Liquidacion liquidacion = new Liquidacion();
            EntityManagerFactory emf = Conexion.getInstancia().getEMF();
            LiquidacionJpaController controlador = new LiquidacionJpaController(emf);
            liquidacion.setSubtotal(subTotal);
            liquidacion.setTotal(total);
            liquidacion.setPedidoIdpedido(pedido);
            controlador.create(liquidacion);
             return "Nueva Liquidación Creada Correctamente";
        }
        catch (Exception e){
            return "Error al Ingresar La Nueva Liquidación";
        }
           
    }
     
     
    
    public List<Liquidacion> listaDeLiquidaciones(){
        EntityManagerFactory emf = Conexion.getInstancia().getEMF();
        LiquidacionJpaController controlador = new LiquidacionJpaController (emf);
        return controlador.findLiquidacionEntities();
    }
    
       public String modificar (Liquidacion  liquidacion){
        try
        {
            EntityManagerFactory emf = Conexion.getInstancia().getEMF();
            LiquidacionJpaController controlador = new LiquidacionJpaController (emf);
            controlador.edit(liquidacion);
            return "Liquidación Modificada Correctamente";
        }
        catch (Exception e)
        {
            return "Error al Modificar Liquidación";
        }
        
    }
    public String eliminar (int id){
        try
        {
            EntityManagerFactory emf = Conexion.getInstancia().getEMF();
            LiquidacionJpaController controlador = new LiquidacionJpaController (emf);
            controlador.destroy(id);
            return "Liquidación Eliminada Correctamente";
        }
        catch (Exception e)
        {
            return "Error al Eliminar Liquidación";
        
    }
    }
    
    public Liquidacion retornarUltimaIngresada ()
    {
        return this.listaDeLiquidaciones().get(this.listaDeLiquidaciones().size()-1);
    }
}
