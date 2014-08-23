/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package AdministradoresGUI;

import ControladoresGUI.EditarUsuarioControlador;
import EntidadesJPA.Usuario;
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
public class EditarUsuarioAdministrador 
{
    public Scene scene;
    public Stage stage;
    public Parent root;
    public VBox pnlPrincipal;
    public Usuario Usuario;
    
    public EditarUsuarioAdministrador(Scene scene, Stage stage, Usuario Usuario) {
        this.scene = scene;
        this.stage = stage;
        this.Usuario = Usuario;
    }

    public void abrirEditarUsuario(){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Principal/EditarUsuario.fxml"));
            scene.setRoot((Parent) loader.load());
            EditarUsuarioControlador controller = loader.<EditarUsuarioControlador>getController();
            controller.initManager(this);
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();
        } 
        catch (IOException ex) 
        {
            Logger.getLogger(EditarUsuarioAdministrador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void cerrarEditarUsuario()
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
