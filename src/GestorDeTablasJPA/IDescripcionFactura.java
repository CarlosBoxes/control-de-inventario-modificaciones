/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GestorDeTablasJPA;

import ControladoresJPA.DescripcionFacturaJpaController;
import EntidadesJPA.DescripcionFactura;
import EntidadesJPA.Facturas;
import EntidadesJPA.Productos;
import java.util.List;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Ch470
 */
public class IDescripcionFactura {
     public IDescripcionFactura(){}
     public String guardar(int cantidad,float subTotal, float Precio, Productos Producto, Facturas Factura)
    {
        try{
            DescripcionFactura DescripcionFactura = new DescripcionFactura();
            EntityManagerFactory emf = Conexion.getInstancia().getEMF();
            DescripcionFacturaJpaController controlador = new DescripcionFacturaJpaController(emf);
            DescripcionFactura.setCantidad(cantidad);
            DescripcionFactura.setSubTotal(subTotal);
            DescripcionFactura.setPrecio(Precio);
            DescripcionFactura.setProductosidProductos(Producto);
            DescripcionFactura.setFacturasidFacturas(Factura);
            controlador.create(DescripcionFactura);
            return "Nueva  Descripcion de Factura Creado Correctamente";
        }
        catch (Exception e){
            return "Error al Ingresar el Nueva Descripcion de Factura";
        }
           
    } 
    
    public List<DescripcionFactura> ListaDeDescripcionesDeFactura(){
        EntityManagerFactory emf = Conexion.getInstancia().getEMF();
        DescripcionFacturaJpaController controlador = new DescripcionFacturaJpaController (emf);
        return controlador.findDescripcionFacturaEntities();
    }
    
    public String modificar (DescripcionFactura  descripcion){
        try
        {
          EntityManagerFactory emf = Conexion.getInstancia().getEMF();
        DescripcionFacturaJpaController controlador = new DescripcionFacturaJpaController(emf);
            controlador.edit( descripcion);
            return "Descripción de Pedido De Clientes Modificada Correctamente";
        }
        catch (Exception e)
        {
            return "Error al Modificar La Descripción de Pedido De Clientes";
        }
        
    }
    public String eliminar (int id){
        try
        {
          EntityManagerFactory emf = Conexion.getInstancia().getEMF();
          DescripcionFacturaJpaController controlador = new DescripcionFacturaJpaController(emf);
            controlador.destroy(id);
            return "Descripción de Factura Eliminada Correctamente";
        }
        catch (Exception e)
        {
            return "Error al Eliminar Descripción de Factura";
        
    }
    }
    
    public DescripcionFactura retornarUltimoIngresado()
    {
        return this.ListaDeDescripcionesDeFactura().get(this.ListaDeDescripcionesDeFactura().size()-1);
    }
}
