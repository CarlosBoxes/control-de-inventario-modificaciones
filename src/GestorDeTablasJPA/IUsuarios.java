/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GestorDeTablasJPA;

import ControladoresJPA.AsignacionDePermisosJpaController;
import ControladoresJPA.UsuarioJpaController;
import ControladoresJPA.exceptions.PreexistingEntityException;
import EntidadesJPA.AsignacionDePermisos;
import EntidadesJPA.Permisos;
import EntidadesJPA.Usuario;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

/**
 *
 * @author Ch470
 */
public class IUsuarios {
    public IUsuarios (){}
     
    //private IUsuarios busC = new IUsuarios();
    public String guardar(String nombre,String contraseña,String puesto,List<String> listaDePermisos) throws PreexistingEntityException, Exception
    {    
        try{
            Usuario usuario = new Usuario();
            Seguridad encriptado = new Seguridad ();
            String contraseñas = encriptado.getStringMessageDigest(contraseña);
            EntityManagerFactory emf = Conexion.getInstancia().getEMF();
            UsuarioJpaController controlador = new UsuarioJpaController(emf);
            IPermisos manejoPermisos = new IPermisos ();
            usuario.setNombre(nombre);            
            //parte para encriptar password
            usuario.setContraseña(contraseñas);
            usuario.setPuesto(puesto);
            usuario.setEliminado(false);
            controlador.create(usuario);
            usuario = this.listaDeUsuarios().get(this.listaDeUsuarios().size()-1);
            this.asignarPermisos(usuario, manejoPermisos.listaDePermisosBuscados(listaDePermisos));
            return "Nuevo Usuario Creado Correctamente";
        }
        catch (Exception e){
            return "Error al Ingresar el Nuevo Usuario";
        }
           
              
    }
    public Usuario retornarUsuario(int id)
    {
        EntityManagerFactory emf = Conexion.getInstancia().getEMF();
        UsuarioJpaController controlador = new UsuarioJpaController(emf);
        return controlador.findUsuario(id);
    }
    public boolean buscarNombreDeUsuarioExistente(String nombre)
    {
        EntityManagerFactory emf = Conexion.getInstancia().getEMF();
        UsuarioJpaController controlador = new UsuarioJpaController(emf);
        for (Usuario usuario:this.listaDeUsuarios())
        {
            if (nombre.equals(usuario.getNombre())) 
            {
                return false;
            }
        
        }
        return true;
    }
    

    public List<Usuario> listaDeUsuarios()
    {
        EntityManagerFactory emf = Conexion.getInstancia().getEMF();
        UsuarioJpaController controlador = new UsuarioJpaController (emf);
        return controlador.findUsuarioEntities();
    }
    
    public String asignarPermisos (Usuario usuarios, List<Permisos> permisos)
    {
        EntityManagerFactory emf = Conexion.getInstancia().getEMF();
        AsignacionDePermisosJpaController controlador = new AsignacionDePermisosJpaController (emf);
        try          
        {
            for (Permisos permiso:permisos)
            {
                AsignacionDePermisos asignarPermisos = new AsignacionDePermisos ();
                asignarPermisos.setUsuarioidUsuario(usuarios);
                asignarPermisos.setPermisosidPermisos(permiso);
                controlador.create(asignarPermisos);
            }
            return "Los Permisos Fueron Asignados Correctamente";
        }
        catch(Exception e)
                {
                    return "Los Permisos No Fueron Asignados";
                }
        
    }
    
    public String modificar (Usuario usuario)
    {
        Seguridad encriptado = new Seguridad ();
        String contraseña = encriptado.getStringMessageDigest(usuario.getContraseña());
        usuario.setContraseña(contraseña);
        EntityManagerFactory emf = Conexion.getInstancia().getEMF();
        UsuarioJpaController controlador = new UsuarioJpaController(emf);
        try{
            controlador.edit(usuario);
            return "Datos Modificados con Exito";
        }
        catch (Exception e)
                {
                    return "Error en la Modificación";
                }
    }
    
    public String crearNuevaContraseña (Usuario usuario,String nuevaContraseña)
    {
        
        
        Seguridad encriptado = new Seguridad ();
        String contraseñas = encriptado.getStringMessageDigest(nuevaContraseña);
        usuario.setContraseña(contraseñas);
        EntityManagerFactory emf = Conexion.getInstancia().getEMF();
        UsuarioJpaController controlador = new UsuarioJpaController(emf);
        try{
            controlador.edit(usuario);
            return "Datos Modificados con Exito";
        }
        catch (Exception e)
                {
                    return "Error en la Modificación";
                }
    }
     public String eliminar (int id)
    {
        EntityManagerFactory emf = Conexion.getInstancia().getEMF();
        UsuarioJpaController controlador = new UsuarioJpaController(emf);
        try{
            controlador.destroy(id);
            return "Usuario Elminado Correctamente";
        }
        catch (Exception e)
                {
                    return "Error Al Eliminar Usuario";
                }
    }
     
     //Procedimiento para buscar Usuario Por NOmbre
     public Usuario buscarUsuarioPorNombre (String nombreUsuario)
     {
        EntityManagerFactory emf = Conexion.getInstancia().getEMF();
        EntityManager em = emf.createEntityManager();
        Query qr;
        qr = em.createNamedQuery("Usuario.findByNombre");
        qr.setParameter("nombre", nombreUsuario);
        List<Usuario> lista = qr.getResultList();
        if (lista.isEmpty())
        {
            return null;      
        }
         else
        {
            return lista.get(0);
        }
     }
     
     public List<Usuario> buscarListaUsuarioPorNombre (String nombreUsuario)
     {
        EntityManagerFactory emf = Conexion.getInstancia().getEMF();
        EntityManager em = emf.createEntityManager();
        Query qr;
        qr = em.createNamedQuery("Usuario.findByNombreLike");
        qr.setParameter("nombre", "%"+nombreUsuario+"%");
        if (!qr.getResultList().isEmpty())
        {
            return qr.getResultList();      
        }
        else
        {
            return null;
        }
     }
     
     public boolean comprobarContraseña (String nombre,String contraseña)
             
     {
         Usuario usuario = new Usuario ();
         
         usuario = this.buscarUsuarioPorNombre(nombre);
         if (usuario == null)
         {
             return false;
             
         }
         else
         {
            Seguridad seguridad = new Seguridad();
            if(seguridad.getStringMessageDigest(contraseña).equals(usuario.getContraseña()))
            {
                return true;
            }
            else
            {
                return false;
            }
         }
     }
   
}
