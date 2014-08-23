/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GestorDeTablasJPA;

import ControladoresJPA.ProductoaCambiarJpaController;
import EntidadesJPA.Liquidacion;
import EntidadesJPA.ProductoaCambiar;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Ch470
 */
public class IProductosACambiar {
    public  IProductosACambiar(){}
    public String guardar(String nombre, int cantidad,Liquidacion liquidacion,Float precio)
    {
        try{
            ProductoaCambiar ProductoAcambiar = new ProductoaCambiar();
            EntityManagerFactory emf = Conexion.getInstancia().getEMF();
            ProductoaCambiarJpaController controlador = new ProductoaCambiarJpaController(emf);
            ProductoAcambiar.setProducto(nombre);
            ProductoAcambiar.setCantidad(cantidad);
            ProductoAcambiar.setPrecio(precio);
            ProductoAcambiar.setLiquidacionIdliquidacion(liquidacion);
            controlador.create(ProductoAcambiar);
             return "Nuevo  producto A Cambiar Ingresado Correctamente";
        }
        catch (Exception e){
            return "Error al Ingresar  producto A Cambiar Ingresado Correctamente";
        }
           
    }
    
}
