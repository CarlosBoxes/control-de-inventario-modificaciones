/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GestorDeTablasJPA;

import ControladoresJPA.InventarioProductoJpaController;
import EntidadesJPA.BodegaProductos;
import EntidadesJPA.InventarioProducto;
import EntidadesJPA.Productos;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

/**
 *
 * @author Ch470
 */
public class IInventarioProducto {
    public IInventarioProducto(){}
     public String guardar(Productos producto,BodegaProductos bodega,int cantidad)
    {
        try{
            InventarioProducto inventarioProducto = new InventarioProducto();
            EntityManagerFactory emf = Conexion.getInstancia().getEMF();
            InventarioProductoJpaController controlador = new InventarioProductoJpaController(emf);
            inventarioProducto.setProductosidProductos(producto);
            inventarioProducto.setBodegaProductosidbodega(bodega);
            inventarioProducto.setCantidad(cantidad);
            controlador.create(inventarioProducto);
             return "Nuevo Inventario de Producto  Ingresado Correctamente";
        }
        catch (Exception e){
            return "Error al Ingresar El Nuevo inventario Producto";
        }
           
    }
    
    public List<InventarioProducto> listaDeInventarioProductos(){
        EntityManagerFactory emf = Conexion.getInstancia().getEMF();
        InventarioProductoJpaController controlador = new InventarioProductoJpaController (emf);
        return controlador.findInventarioProductoEntities();
    }
    
     public String modificar (InventarioProducto  inventarioDeProducto){
        try
        {
            EntityManagerFactory emf = Conexion.getInstancia().getEMF();
            InventarioProductoJpaController controlador = new InventarioProductoJpaController (emf);
            controlador.edit(inventarioDeProducto);
            return "Inventario de Producto Modificado Correctamente";
        }
        catch (Exception e)
        {
            return "Error al Modificar Inventario de Producto";
        }
        
    }
     
     public InventarioProducto buscarInventarioPorProducto(Productos Producto)
     {
        EntityManagerFactory emf = Conexion.getInstancia().getEMF();
        EntityManager em = emf.createEntityManager();
        Query qr;
        qr = em.createNamedQuery("InventarioProducto.findByInventario");
        qr.setParameter("idProducto", Producto);
        List<InventarioProducto> lista = qr.getResultList();
        if (lista.isEmpty())
        {
            return null;
        }
        else
        {
            return lista.get(0); 
        }
     }
    public String eliminar (int id){
        try
        {
         EntityManagerFactory emf = Conexion.getInstancia().getEMF();
        InventarioProductoJpaController controlador = new InventarioProductoJpaController (emf);
            controlador.destroy(id);
            return "Inventario de Producto Eliminada Correctamente";
        }
        catch (Exception e)
        {
            return "Error al Eliminar Inventario Producto";
        
    }
    }
    
    InventarioProducto inven = new InventarioProducto ();
    public void sacarDeInventario (Productos producto,int cantidad)
    {
        try{
            for (InventarioProducto inventa:this.listaDeInventarioProductos())
            {
                if  (inventa.getProductosidProductos().getIdProductos()==producto.getIdProductos())
                {
                    inventa.setCantidad(inventa.getCantidad()-cantidad);
                    this.modificar(inventa);
                    break;
                }
            }
            
        }
        catch(Exception e)
                {
                    
                }
    }
    
    public void meterAlInventario (Productos producto,int cantidad)
    {
        try{
            for (InventarioProducto inventa:this.listaDeInventarioProductos())
            {
                if  (inventa.getProductosidProductos().getIdProductos()==producto.getIdProductos())
                {
                    inventa.setCantidad(inventa.getCantidad()+cantidad);
                    this.modificar(inventa);
                    break;
                }
            }
            
        }
        catch(Exception e)
                {
                    
                }
    }
    
    public boolean verificarInventario (Productos producto,float cantidad)
    {
        boolean verificado=false;
        try
        {
            for (InventarioProducto inventa:this.listaDeInventarioProductos())
            {
                if  (inventa.getProductosidProductos().getIdProductos()==producto.getIdProductos())
                {
                    if (cantidad<=inventa.getCantidad())
                    {
                        verificado =  true;
                    }
                    else 
                    {
                        verificado = false;
                    }
                    break;
                }
            }
            return verificado;
        }
        catch (Exception e)
        {
            return verificado;
        }
    }
}
