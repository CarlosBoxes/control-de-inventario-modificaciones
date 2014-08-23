/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GestorDeTablasJPA;

import ControladoresJPA.ProduccionJpaController;
import EntidadesJPA.Produccion;
import EntidadesJPA.Productos;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

/**
 *
 * @author Ch470
 */
public class IProduccion {
    
     public IProduccion (){}
    
    
    
    public String guardar(Productos producto,Date fecha, int cantidad)
    {
        try{
            Produccion produccion = new Produccion();
            EntityManagerFactory emf = Conexion.getInstancia().getEMF();
            ProduccionJpaController controlador = new ProduccionJpaController(emf);
            produccion.setProductosidProductos(producto);
            produccion.setFecha(fecha);
            produccion.setCantidad(cantidad);
            controlador.create(produccion);
             return "Cantidad de producto agregada Correctamente";
        }
        catch (Exception e){
            return "Error al Ingresar Cantidad de Producto";
        }
           
    }
    
    public List<Produccion> listaDeProduccionCompleta(){
        EntityManagerFactory emf = Conexion.getInstancia().getEMF();
        EntityManager em = emf.createEntityManager();
        Query qr;
        qr = em.createNamedQuery("Produccion.findAll");
        if (!qr.getResultList().isEmpty())
        {
            return qr.getResultList();
        }
        else
        {
            return null;  
        }  
    }
    
    //falta agregar el query para que busque por mes y año.
        public List<Produccion> listaDeProduccionPorMes(int mes,int año){
        EntityManagerFactory emf = Conexion.getInstancia().getEMF();
        EntityManager em = emf.createEntityManager();
        Query qr;
        qr = em.createNamedQuery("Produccion.findAll");
        if (!qr.getResultList().isEmpty())
        {
            return qr.getResultList();
        }
        else
        {
            return null;  
        }  
    }
    
    public List<Produccion> listaDeProductosResumen (List<Produccion> lista)
    {
        List<Produccion> listaProductos = new ArrayList();
        for (Produccion productoInv:lista)
        {
            if (listaProductos.isEmpty())
            {
                listaProductos.add(productoInv);
            }
            else
            for (Produccion producto:listaProductos)
            {
                if (producto.getIdproduccion()==productoInv.getIdproduccion())
                {
                    listaProductos.add(productoInv);
                } 
                   
            }            
        }
        return listaProductos;
    }
}
