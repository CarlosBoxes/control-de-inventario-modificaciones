/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GestorDeTablasJPA;

import ControladoresJPA.DescripcionLiquidacionJpaController;
import EntidadesJPA.ChequesClientes;
import EntidadesJPA.Depositos;
import EntidadesJPA.DescripcionLiquidacion;
import EntidadesJPA.Liquidacion;
import java.util.List;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Ch470
 */
public class IDescripcionLiquidacion {
     public IDescripcionLiquidacion(){}
     public String guardar(Liquidacion liquidacion,Depositos deposito,ChequesClientes chequesClientes)
    {
        try{
            DescripcionLiquidacion liquidaciones = new DescripcionLiquidacion();
            EntityManagerFactory emf = Conexion.getInstancia().getEMF();
            DescripcionLiquidacionJpaController controlador = new DescripcionLiquidacionJpaController(emf);
            liquidaciones.setLiquidacionIdliquidacion(liquidacion);
            liquidaciones.setDepositosidDepositos(deposito);
            liquidaciones.setChequesClientesIdchequesClientes(chequesClientes);
            controlador.create(liquidaciones);
             return "Nueva Descripcion de Liquidación Creada Correctamente";
        }
        catch (Exception e){
            return "Error al Ingresar La Nueva Descripcion de Liquidación";
        }
           
    }
     
     public List<DescripcionLiquidacion> listaDeDescripcionLiquidaciones(){
        EntityManagerFactory emf = Conexion.getInstancia().getEMF();
        DescripcionLiquidacionJpaController controlador = new DescripcionLiquidacionJpaController (emf);
        return controlador.findDescripcionLiquidacionEntities();
    }
     
      public String modificar (DescripcionLiquidacion  descripcionLiquidacion){
        try
        {
           EntityManagerFactory emf = Conexion.getInstancia().getEMF();
           DescripcionLiquidacionJpaController controlador = new DescripcionLiquidacionJpaController (emf);
            controlador.edit(descripcionLiquidacion);
            return "Descripción de Liquidacion Modificada Correctamente";
        }
        catch (Exception e)
        {
            return "Error al Modificar La Descripción de Liquidación";
        }
        
    }
    public String eliminar (int id){
        try
        {
           EntityManagerFactory emf = Conexion.getInstancia().getEMF();
           DescripcionLiquidacionJpaController controlador = new DescripcionLiquidacionJpaController (emf);
            controlador.destroy(id);
            return "Descripción de Liquidacion Eliminada Correctamente";
        }
        catch (Exception e)
        {
            return "Error al Eliminar Descripción de Liquidación";
        
    }
    }
    
}
