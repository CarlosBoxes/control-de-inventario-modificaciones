/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package AdministradoresGUI;

import java.io.IOException;
import java.util.logging.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import ControladoresGUI.InicioSesionControlador;
import EntidadesJPA.Usuario;
import java.awt.Dimension;
import java.awt.Toolkit;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author luis__000
 */
public class IniciorSesionAdministrador {
    private Scene scene;
    private Stage stage;
    
    public IniciorSesionAdministrador(Scene scene, Stage stage) {
        this.scene = scene;
        this.stage = stage;
    }
    public void showVentanaPrincipal(Usuario Usuario){
        scene = new Scene(new StackPane());
        VentanaPrincipalAdministrador ventanaPrincipalManager = new VentanaPrincipalAdministrador(scene,stage,Usuario);
        ventanaPrincipalManager.showVentanaPrincipal();
        stage.setTitle("Bienvenido - Ventana principal");
        stage.setX(0);
        stage.setY(0);
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        stage.setWidth(d.getWidth());
        stage.setHeight(d.getHeight());
        stage.setScene(scene);
        stage.show();
    }
    /**
     * Callback method invoked to notify that a user has logged out of the main application.
     * Will show the login application screen.
     */ 
    public void logout() {
      showLoginScreen();
    }

    public void showLoginScreen() {
      try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Principal/InicioSesion.fxml"));
        scene.setRoot((Parent) loader.load());
        InicioSesionControlador controller = loader.<InicioSesionControlador>getController();
        controller.initManager(this);
      } 
      catch (IOException ex) 
      {
        Logger.getLogger(IniciorSesionAdministrador.class.getName()).log(Level.SEVERE, null, ex);
      }
    }
    
    public void showMensajes(String Mensaje)
    {
         Scene scen = new Scene(new StackPane());
         Stage stag = new Stage();
         MensajesAdministrador MensajesAdministrador = new MensajesAdministrador(scen,stag,Mensaje);
         MensajesAdministrador.abrirMensajes();
    }
}
