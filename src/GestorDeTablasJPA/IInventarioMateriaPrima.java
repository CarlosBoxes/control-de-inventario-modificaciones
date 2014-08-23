/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GestorDeTablasJPA;

import ControladoresJPA.InventarioMateriaPrimaJpaController;
import EntidadesJPA.BodegaProduccion;
import EntidadesJPA.InventarioMateriaPrima;
import EntidadesJPA.MateriaPrima;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

/**
 *
 * @author Ch470
 */
public class IInventarioMateriaPrima {
    public  IInventarioMateriaPrima(){}
    public String guardar(float cantidad, MateriaPrima materiaPrima,BodegaProduccion bodega)
    {
        try{
            InventarioMateriaPrima inventarioMateriaPrima = new InventarioMateriaPrima();
            EntityManagerFactory emf = Conexion.getInstancia().getEMF();
            InventarioMateriaPrimaJpaController controlador = new InventarioMateriaPrimaJpaController(emf);
            inventarioMateriaPrima.setCantidad(cantidad);
            inventarioMateriaPrima.setMateriaPrimaidmateriaPrima(materiaPrima);
            inventarioMateriaPrima.setBodegaProduccionidbodegaProduccion(bodega);
            controlador.create(inventarioMateriaPrima);
             return "Nuevo Inventario de   Materia Prima Creada Correctamente";
        }
        catch (Exception e){
            return "Error al Ingresar el Nuevo Inventario De Materia Prima";
        }
           
    }
    
    public List<InventarioMateriaPrima> listaDeInventarioMateriaPrima(){
        EntityManagerFactory emf = Conexion.getInstancia().getEMF();
       InventarioMateriaPrimaJpaController controlador = new InventarioMateriaPrimaJpaController (emf);
        return controlador.findInventarioMateriaPrimaEntities();
    }
     public String modificar (InventarioMateriaPrima  inventarioMateriaPrima){
        try
        {
          EntityManagerFactory emf = Conexion.getInstancia().getEMF();
       InventarioMateriaPrimaJpaController controlador = new InventarioMateriaPrimaJpaController (emf);
            controlador.edit(inventarioMateriaPrima);
            return "Inventario de Materia  Prima Modificado Correctamente";
        }
        catch (Exception e)
        {
            return "Error al Modificar Inventario de Materia  Prima";
        }
        
    }
    public String eliminar (int id){
        try
        {
         EntityManagerFactory emf = Conexion.getInstancia().getEMF();
       InventarioMateriaPrimaJpaController controlador = new InventarioMateriaPrimaJpaController (emf);
            controlador.destroy(id);
            return "Inventario de Materia  Prima Eliminada Correctamente";
        }
        catch (Exception e)
        {
            return "Error al Eliminar Inventario de Materia  Prima";
        
    }
    }
    
     public boolean verificarInventario (MateriaPrima materiaPrima,int cantidad)
    {
        boolean verificado=false;
        try
        {
            for (InventarioMateriaPrima inventa:this.listaDeInventarioMateriaPrima())
            {
                if  (inventa.getMateriaPrimaidmateriaPrima().getIdmateriaPrima()==materiaPrima.getIdmateriaPrima())
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
     
     public void meterAlInventario (MateriaPrima materiaPrima,Float cantidad)
    {
        try{
            for (InventarioMateriaPrima inventa:this.listaDeInventarioMateriaPrima())
            {
                if  (inventa.getMateriaPrimaidmateriaPrima().getIdmateriaPrima()==materiaPrima.getIdmateriaPrima())
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
     
     public void sacarDeInventario (MateriaPrima materiaPrima,float cantidad)
    {
        try{
            for (InventarioMateriaPrima inventa:this.listaDeInventarioMateriaPrima())
            {
                if  (inventa.getMateriaPrimaidmateriaPrima().getIdmateriaPrima()==materiaPrima.getIdmateriaPrima())
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
     
     public InventarioMateriaPrima buscarInventarioPorIdMateriaPrima(MateriaPrima MateriaPrima)
     {
        EntityManagerFactory emf = Conexion.getInstancia().getEMF();
        EntityManager em = emf.createEntityManager();
        Query qr;
        qr = em.createNamedQuery("InventarioMateriaPrima.findByInventario");
        qr.setParameter("idMateriaPrima", MateriaPrima);
        List<InventarioMateriaPrima> lista = qr.getResultList();
        if (qr.getResultList().isEmpty())
        {
            return null;
        }
        else
        {
            return lista.get(0); 
        }
     }    
}
