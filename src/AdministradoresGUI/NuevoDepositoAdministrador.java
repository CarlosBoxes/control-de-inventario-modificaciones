/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package AdministradoresGUI;

import ControladoresGUI.NuevoBancoControlador;
import ControladoresGUI.NuevoDepositoControlador;
import ControladoresGUI.NuevoProductoControlador;
import ControladoresGUI.NuevoProveedorControlador;
import ControladoresGUI.NuevoUsuarioControlador;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author luis__000
 */
public class NuevoDepositoAdministrador 
{
    public Scene scene;
    public Stage stage;
    public Parent root;
    public VBox pnlPrincipal;
    
    public NuevoDepositoAdministrador(Scene scene, Stage stage) {
        this.scene = scene;
        this.stage = stage;
    }

    public void abrirNuevoDeposito(){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Principal/NuevoDeposito.fxml"));
            scene.setRoot((Parent) loader.load());
            NuevoDepositoControlador controller = loader.<NuevoDepositoControlador>getController();
            controller.initManager(this);
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();
        } 
        catch (IOException ex) 
        {
            Logger.getLogger(NuevoDepositoAdministrador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void cerrarNuevoDeposito()
    {
        this.stage.close();
    }
    
    public void showMensajes(String Mensaje)
    {
         Scene scen = new Scene(new StackPane());
         Stage stag = new Stage();
         MensajesAdministrador MensajesAdministrador = new MensajesAdministrador(scen,stag,Mensaje);
         MensajesAdministrador.abrirMensajes();
    }
}
