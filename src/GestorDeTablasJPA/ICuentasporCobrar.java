/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GestorDeTablasJPA;

import ControladoresJPA.CuentaporCobrarJpaController;
import EntidadesJPA.ChequesClientes;
import EntidadesJPA.Clientes;
import EntidadesJPA.CuentaporCobrar;
import EntidadesJPA.Depositos;
import java.util.Date;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Ch470
 */
public class ICuentasporCobrar {
    public  ICuentasporCobrar(){}
    public String guardar(ChequesClientes Cheque, Depositos Deposito,Float Efectivo,Date Fecha, Clientes Cliente )
    {
        try{
            CuentaporCobrar Cuenta = new CuentaporCobrar();
            EntityManagerFactory emf = Conexion.getInstancia().getEMF();
            CuentaporCobrarJpaController controlador = new CuentaporCobrarJpaController(emf);
            Cuenta.setChequesClientesIdchequesClientes(Cheque);
            Cuenta.setClientesidCliente(Cliente);
            Cuenta.setDepositosidDepositos(Deposito);
            Cuenta.setEfectivo(Efectivo);
            Cuenta.setFecha(Fecha);
            controlador.create(Cuenta);
            return "Abono Ingresado Correctamente";
        }
        catch (Exception e){
            return "Error al Ingresar el Abono";
        }
           
    }
    
}
