/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GestorDeTablasJPA;

import ControladoresJPA.MateriaPrimaJpaController;
import EntidadesJPA.MateriaPrima;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

/**
 *
 * @author Ch470
 */
public class IMateriaPrima {
     public  IMateriaPrima(){}
    public String guardar(String nombre, String presentacion, Float precio)
    {
        try{
            MateriaPrima materiaPrima = new MateriaPrima();
            EntityManagerFactory emf = Conexion.getInstancia().getEMF();
            MateriaPrimaJpaController controlador = new MateriaPrimaJpaController(emf);
            materiaPrima.setNombre(nombre);
            materiaPrima.setPresentacion(presentacion);
            materiaPrima.setEliminado(false);
            materiaPrima.setPrecio(precio);
            controlador.create(materiaPrima);
             return "Nueva  Materia Prima Creada Correctamente";
        }
        catch (Exception e){
            return "Error al Ingresar el Nueva Materia Prima";
        }
           
    }
    
    public List<MateriaPrima> listaDeMateriaPrima(){
        EntityManagerFactory emf = Conexion.getInstancia().getEMF();
        EntityManager em = emf.createEntityManager();
        Query qr;
        qr = em.createNamedQuery("Materiaprima.findByListaNombre");
        if (!qr.getResultList().isEmpty())
        {
            return qr.getResultList();
        }
        else
        {
            return null;  
        }
    }
    
    public String modificar ( MateriaPrima materiaPrima){
        try
        {
             EntityManagerFactory emf = Conexion.getInstancia().getEMF();
            MateriaPrimaJpaController controlador = new MateriaPrimaJpaController (emf);
            controlador.edit(materiaPrima);
            return "Materia Prima Modificada Correctamente";
        }
        catch (Exception e)
        {
            return "Error al Modificar Materia Prima";
        }
    }
    
    public String eliminar (int id){
        try
        {
             EntityManagerFactory emf = Conexion.getInstancia().getEMF();
             MateriaPrimaJpaController controlador = new MateriaPrimaJpaController (emf);
            controlador.destroy(id);
            return "Materia Prima Eliminada Correctamente";
        }
        catch (Exception e)
        {
            return "Error al Eliminar Materia Prima";
        }
    }
    
    public String eliminar ( MateriaPrima materiaPrima){
        try
        {
             EntityManagerFactory emf = Conexion.getInstancia().getEMF();
            MateriaPrimaJpaController controlador = new MateriaPrimaJpaController (emf);
            controlador.edit(materiaPrima);
            return "Materia Prima Eliminada Correctamente";
        }
        catch (Exception e)
        {
            return "Error al Eliminar Materia Prima";
        }
    }
    
    public MateriaPrima buscarMateriaPrimaPorNombre(String nombreMateriaPrima)
    {
        EntityManagerFactory emf = Conexion.getInstancia().getEMF();
        EntityManager em = emf.createEntityManager();
        Query qr;
        qr = em.createNamedQuery("Materiaprima.findByNombre");
        qr.setParameter("nombre", nombreMateriaPrima);
        if (!qr.getResultList().isEmpty())
        {
            return (MateriaPrima)qr.getSingleResult();
        }
        else
        {
            return null;  
        }
    }
    
    public List<MateriaPrima> buscarListaMateriaPrimaPorNombre(String nombreMateriaPrima)
    {
        EntityManagerFactory emf = Conexion.getInstancia().getEMF();
        EntityManager em = emf.createEntityManager();
        Query qr;
        qr = em.createNamedQuery("Materiaprima.findByNombreLike");
        qr.setParameter("nombre", "%"+nombreMateriaPrima+"%");
        if (!qr.getResultList().isEmpty())
        {
            return qr.getResultList();
        }
        else
        {
            return null;  
        }
    }
    
    public MateriaPrima buscarMateriaPrimaPorId(int idMateria)
    {
        EntityManagerFactory emf = Conexion.getInstancia().getEMF();
        EntityManager em = emf.createEntityManager();
        Query qr;
        qr = em.createNamedQuery("Materiaprima.findByIdmateriaPrima");
        qr.setParameter("idmateriaPrima", idMateria);
        List<MateriaPrima> lista = qr.getResultList();
        if (!qr.getResultList().isEmpty())
        {
            return (MateriaPrima)qr.getSingleResult();
        }
        else
        {
            return null;   
        }
    }
    
    public MateriaPrima buscarMateriaPrimaPorPresentacion(String presentacion)
    {
        EntityManagerFactory emf = Conexion.getInstancia().getEMF();
        EntityManager em = emf.createEntityManager();
        Query qr;
        qr = em.createNamedQuery("Materiaprima.findByPresentacion");
        qr.setParameter("presentacion", presentacion);
        List<MateriaPrima> lista = qr.getResultList();
        if (!qr.getResultList().isEmpty())
        {
            return lista.get(0);
        }
        else
        {
            return null;   
        }
    }
    
    public MateriaPrima retornarUltimoIngresado()
    {
        return this.listaDeMateriaPrima().get(this.listaDeMateriaPrima().size()-1);
    }
            
    
    
}
