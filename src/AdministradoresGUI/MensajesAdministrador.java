/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package AdministradoresGUI;

import ControladoresGUI.MensajesControlador;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author luis__000
 */
public class MensajesAdministrador 
{
    public Scene scene;
    public Stage stage;
    public Parent root;
    public VBox pnlPrincipal;
    public String Mensaje;
    
    public MensajesAdministrador(Scene scene, Stage stage, String Mensaje) {
        this.scene = scene;
        this.stage = stage;
        this.Mensaje = Mensaje;
    }

    public void abrirMensajes(){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Principal/Mensajes.fxml"));
            scene.setRoot((Parent) loader.load());
            MensajesControlador controller = loader.<MensajesControlador>getController();
            controller.initManager(this);
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();
        } 
        catch (IOException ex) 
        {
            Logger.getLogger(MensajesAdministrador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void cerrarMensajes()
    {
        this.stage.close();
    }
}
