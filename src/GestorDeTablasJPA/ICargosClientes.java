/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GestorDeTablasJPA;

import ControladoresJPA.CargosClientesJpaController;
import EntidadesJPA.Clientes;
import EntidadesJPA.CargosClientes;
import java.util.Date;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Ch470
 */
public class ICargosClientes {
    public  ICargosClientes(){}
    public String guardar(Float Total,Date Fecha, Clientes Cliente )
    {
        try{
            CargosClientes Cargo = new CargosClientes();
            EntityManagerFactory emf = Conexion.getInstancia().getEMF();
            CargosClientesJpaController controlador = new CargosClientesJpaController(emf);
            Cargo.setTotal(Total);
            Cargo.setClientesidCliente(Cliente);
            Cargo.setFecha(Fecha);
            controlador.create(Cargo);
            return "Cargo Ingresado Correctamente";
        }
        catch (Exception e){
            return "Error al Ingresar el Cargo";
        }
           
    }
    
}
