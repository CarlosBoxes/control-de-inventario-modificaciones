/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package AdministradoresGUI;

import ControladoresGUI.DepositosControlador;
import EntidadesJPA.Depositos;
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
public class DepositosAdministrador 
{
    private Scene scene;
    private Stage stage;
    public Parent root;
    public Usuario Usuario;
    public FinanzasAdministrador FinanzasAdministrador;
    
    public DepositosAdministrador(Scene scene, Stage stage, FinanzasAdministrador FinanzasAdministrador, Usuario Usuario)
    {
        this.scene = scene;
        this.stage = stage;
        this.FinanzasAdministrador = FinanzasAdministrador;
        this.Usuario = Usuario;
    }

    public void abrirPanelDepositos()
    {
        try 
        {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Principal/Depositos.fxml"));
            root = (Parent) loader.load();
            DepositosControlador controller = loader.<DepositosControlador>getController();
            controller.initManager(this);
        } 
        catch (IOException ex) 
        {
            Logger.getLogger(DepositosAdministrador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void showPnlDepositos(){
        FinanzasAdministrador.showPnlDepositos(); 
    }
    
    public void showNuevoDeposito()
    {
         Scene scen = new Scene(new StackPane());
         Stage stag = new Stage();
         NuevoDepositoAdministrador NuevoDepositoAdministrador = new NuevoDepositoAdministrador(scen,stag);
         NuevoDepositoAdministrador.abrirNuevoDeposito();
    }
    
    public void showEditarDeposito(Depositos Deposito)
    {
         Scene scen = new Scene(new StackPane());
         Stage stag = new Stage();
         EditarDepositoAdministrador EditarDepositoAdministrador = new EditarDepositoAdministrador(scen,stag,Deposito);
         EditarDepositoAdministrador.abrirEditarDeposito();
    }
    
    public void showMensajes(String Mensaje)
    {
         Scene scen = new Scene(new StackPane());
         Stage stag = new Stage();
         MensajesAdministrador MensajesAdministrador = new MensajesAdministrador(scen,stag,Mensaje);
         MensajesAdministrador.abrirMensajes();
    }
}
