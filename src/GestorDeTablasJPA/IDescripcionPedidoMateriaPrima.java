/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GestorDeTablasJPA;

import ControladoresJPA.DescripcionPedidoMateriaPrimaJpaController;
import EntidadesJPA.DescripcionPedidoMateriaPrima;
import EntidadesJPA.MateriaPrima;
import EntidadesJPA.PedidoMateriaPrima;
import java.util.List;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Ch470
 */
public class IDescripcionPedidoMateriaPrima {
    public  IDescripcionPedidoMateriaPrima(){}
    public String guardar(float cantidad,float precioMateriaPrima,MateriaPrima materiaPrima,PedidoMateriaPrima pedido,float subTotal)
    {
        try{
            DescripcionPedidoMateriaPrima descripcionPedidoMateriaPrima = new DescripcionPedidoMateriaPrima();
            EntityManagerFactory emf = Conexion.getInstancia().getEMF();
            DescripcionPedidoMateriaPrimaJpaController controlador = new DescripcionPedidoMateriaPrimaJpaController(emf);
            descripcionPedidoMateriaPrima.setCantidad(cantidad);
            descripcionPedidoMateriaPrima.setPrecioMateriaPrima(precioMateriaPrima);
            descripcionPedidoMateriaPrima.setMateriaPrimaidmateriaPrima(materiaPrima);
            descripcionPedidoMateriaPrima.setPedidomateriaprimaidpedidoMP(pedido);
            descripcionPedidoMateriaPrima.setSubTotal(subTotal);
            controlador.create(descripcionPedidoMateriaPrima);
             return "Nueva Descripcion de Pedido Para Materia Prima Creado Correctamente";
        }
        catch (Exception e){
            return "Error al Ingresar el Nueva Descripcion de Pedido Para Materia Prima";
        }
           
    }
    
    public List<DescripcionPedidoMateriaPrima> listaDePedidosDeMateriaPrima(){
        EntityManagerFactory emf = Conexion.getInstancia().getEMF();
        DescripcionPedidoMateriaPrimaJpaController controlador = new DescripcionPedidoMateriaPrimaJpaController (emf);
        return controlador.findDescripcionPedidoMateriaPrimaEntities();
    }
    
      public String modificar (DescripcionPedidoMateriaPrima  descripcionDeMateriaPrima){
        try
        {
           EntityManagerFactory emf = Conexion.getInstancia().getEMF();
        DescripcionPedidoMateriaPrimaJpaController controlador = new DescripcionPedidoMateriaPrimaJpaController (emf);
            controlador.edit( descripcionDeMateriaPrima);
            return "Descripción de Materia Prima Modificada Correctamente";
        }
        catch (Exception e)
        {
            return "Error al Modificar La Descripción De Materia Prima";
        }
        
    }
    public String eliminar (int id){
        try
        {
          EntityManagerFactory emf = Conexion.getInstancia().getEMF();
        DescripcionPedidoMateriaPrimaJpaController controlador = new DescripcionPedidoMateriaPrimaJpaController (emf);
            controlador.destroy(id);
            return "Descripción De Materia Prima Eliminada Correctamente";
        }
        catch (Exception e)
        {
            return "Error al Eliminar Descripción de Materia Prima";
        
    }
    }
    
    public DescripcionPedidoMateriaPrima retornarUltimoIngresado()
    {
        return this.listaDePedidosDeMateriaPrima().get(this.listaDePedidosDeMateriaPrima().size()-1);
    }
}
