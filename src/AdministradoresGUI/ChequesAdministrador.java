/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package AdministradoresGUI;

import ControladoresGUI.ChequesControlador;
import EntidadesJPA.ChequesClientes;
import EntidadesJPA.ChequesProveedores;
import EntidadesJPA.Usuario;
import java.io.IOException;
import java.util.logging.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.scene.layout.StackPane;

import javafx.stage.Stage;

/**
 *
 * @author luis__000
 */
public class ChequesAdministrador 
{
    private Scene scene;
    private Stage stage;
    public Parent root;
    public FinanzasAdministrador FinanzasAdministrador;
    public Usuario Usuario;
    
    public ChequesAdministrador(Scene scene, Stage stage, FinanzasAdministrador FinanzasAdministrador, Usuario Usuario)
    {
        this.scene = scene;
        this.stage = stage;
        this.FinanzasAdministrador = FinanzasAdministrador;
        this.Usuario = Usuario;
    }

    public void abrirPanelCheques()
    {
        try 
        {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Principal/Cheques.fxml"));
            root = (Parent) loader.load();
            ChequesControlador controller = loader.<ChequesControlador>getController();
            controller.initManager(this);
        } 
        catch (IOException ex) 
        {
            Logger.getLogger(ChequesAdministrador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void showPnlChequesDepositos(){
        FinanzasAdministrador.showPnlCheques(); 
    }
    
    public void showNuevoChequeCliente()
    {
         Scene scen = new Scene(new StackPane());
         Stage stag = new Stage();
         NuevoChequeClienteAdministrador NuevoChequeClienteAdministrador = new NuevoChequeClienteAdministrador(scen,stag);
         NuevoChequeClienteAdministrador.abrirNuevoChequeCliente();
    }
    
    public void showNuevoChequeProveedor()
    {
         Scene scen = new Scene(new StackPane());
         Stage stag = new Stage();
         NuevoChequeProveedorAdministrador NuevoChequeProveedorAdministrador = new NuevoChequeProveedorAdministrador(scen,stag);
         NuevoChequeProveedorAdministrador.abrirNuevoChequeProveedor();
    }
    
    public void showEditarChequeCliente(ChequesClientes ChequeCliente)
    {
         Scene scen = new Scene(new StackPane());
         Stage stag = new Stage();
         EditarChequeClienteAdministrador EditarChequeClienteAdministrador = new EditarChequeClienteAdministrador(scen,stag,ChequeCliente);
         EditarChequeClienteAdministrador.abrirEditarChequeCliente();
    }
    
    public void showEditarChequeProveedor(ChequesProveedores ChequeProveedor)
    {
         Scene scen = new Scene(new StackPane());
         Stage stag = new Stage();
         EditarChequeProveedorAdministrador EditarChequeProveedorAdministrador = new EditarChequeProveedorAdministrador(scen,stag,ChequeProveedor);
         EditarChequeProveedorAdministrador.abrirEditarChequeProveedor();
    }
    
    public void showMensajes(String Mensaje)
    {
         Scene scen = new Scene(new StackPane());
         Stage stag = new Stage();
         MensajesAdministrador MensajesAdministrador = new MensajesAdministrador(scen,stag,Mensaje);
         MensajesAdministrador.abrirMensajes();
    }
}
