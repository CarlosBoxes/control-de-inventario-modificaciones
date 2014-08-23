/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GestorDeTablasJPA;

import ControladoresJPA.AsignacionDePermisosJpaController;
import EntidadesJPA.AsignacionDePermisos;
import EntidadesJPA.Permisos;
import EntidadesJPA.Usuario;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Ch470
 */
public class IAsignacionDePermisos {
    public IAsignacionDePermisos (){}
    
    public void guardar(Usuario usuario, Permisos permiso)
    {
        EntityManagerFactory emf = Conexion.getInstancia().getEMF();
       AsignacionDePermisos asignacion = new AsignacionDePermisos();
        AsignacionDePermisosJpaController controlador = new AsignacionDePermisosJpaController(emf);
        asignacion.setUsuarioidUsuario(usuario);
        asignacion.setPermisosidPermisos(permiso);
        controlador.create(asignacion);
        
    }
    
}
