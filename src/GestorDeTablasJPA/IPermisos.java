/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GestorDeTablasJPA;

import ControladoresJPA.PermisosJpaController;

import EntidadesJPA.Permisos;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

/**
 *
 * @author Ch470
 */
public class IPermisos {
    public IPermisos (){}
    
    
    
    public String guardar(String permiso)
    {
        try{
            Permisos permisos = new Permisos();
            EntityManagerFactory emf = Conexion.getInstancia().getEMF();
            PermisosJpaController controlador = new PermisosJpaController(emf);
            permisos.setNombre(permiso);
            controlador.create(permisos);
             return "Nuevo Permiso Creado Correctamente";
        }
        catch (Exception e){
            return "Error al Ingresar el Nuevo Permiso";
        }
           
    }
    // funcion para retornar la lista de permisos completa
    public List<Permisos> listaDePermisos(){
        EntityManagerFactory emf = Conexion.getInstancia().getEMF();
        PermisosJpaController controlador = new PermisosJpaController (emf);
        return controlador.findPermisosEntities();
    }
    public  List<Permisos> listaDePermisosBuscados (List<String> listaDePermisos)
    {
        ArrayList<Permisos> permisos = new ArrayList();
        try
        {
            for (String nombre:listaDePermisos)
            {
                for (Permisos permiso:this.listaDePermisos())
                {
                    if(nombre.equals(permiso.getNombre()))
                    {
                        permisos.add(permiso);
                    }
                }
            }
            return permisos;
        }
        catch (Exception e){
            return null;
            
        }
    }
    
    public List<Permisos> listaDePermisosPorNombre(String nombre){
        EntityManagerFactory emf = Conexion.getInstancia().getEMF();
        EntityManager em = emf.createEntityManager();
        Query qr;
        qr = em.createNamedQuery("Permisos.findByNombre");
        qr.setParameter("Nombre", nombre);
        List<Permisos> lista = qr.getResultList();
        return lista;
    }
    // procedimiento para modificar los permisos
    public String modificar (Permisos permiso)
    {
        try{
            EntityManagerFactory emf = Conexion.getInstancia().getEMF();
            PermisosJpaController controlador = new PermisosJpaController(emf);
            controlador.edit(permiso);
            return "Permisos Modificado Correctamente";
        }
        catch (Exception e)
        {
            return "Error al Modificar Permiso";
        }
    }
     public String eliminar (int id)
    {
        try{
            EntityManagerFactory emf = Conexion.getInstancia().getEMF();
            PermisosJpaController controlador = new PermisosJpaController(emf);
            controlador.destroy(id);
            return "Permiso Eliminado Correctamente";
        }
        catch (Exception e)
        {
            return "Error al Elinimar Permiso";
        }
    }
    
}
