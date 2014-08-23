/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GestorDeTablasJPA;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Ch470
 */
public class Conexion {
      private static final Conexion objetoUnico = new Conexion();
    
    protected EntityManagerFactory emf;
     
    protected Conexion(){ }
    
    public static Conexion getInstancia(){
        return objetoUnico;
    }
    
    public EntityManagerFactory getEMF() {
        if (emf == null)
            setEmf();
        return emf;
    }
    
    public void setEmf() {
        if (emf == null)
           emf = Persistence.createEntityManagerFactory("ControlInventarioCCPU");
    }
    
    public void cerrarEMF() {
        if (this.emf != null)
            emf.close();
    }
    
}
