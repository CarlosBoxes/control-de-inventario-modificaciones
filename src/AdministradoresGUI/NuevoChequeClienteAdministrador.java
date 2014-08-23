/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package AdministradoresGUI;

import ControladoresGUI.NuevoBancoControlador;
import ControladoresGUI.NuevoChequeClienteControlador;
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
public class NuevoChequeClienteAdministrador 
{
    public Scene scene;
    public Stage stage;
    public Parent root;
    public VBox pnlPrincipal;
    
    public NuevoChequeClienteAdministrador(Scene scene, Stage stage) {
        this.scene = scene;
        this.stage = stage;
    }

    public void abrirNuevoChequeCliente(){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Principal/NuevoChequeCliente.fxml"));
            scene.setRoot((Parent) loader.load());
            NuevoChequeClienteControlador controller = loader.<NuevoChequeClienteControlador>getController();
            controller.initManager(this);
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();
        } 
        catch (IOException ex) 
        {
            Logger.getLogger(NuevoChequeClienteAdministrador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void cerrarNuevoChequeCliente()
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
