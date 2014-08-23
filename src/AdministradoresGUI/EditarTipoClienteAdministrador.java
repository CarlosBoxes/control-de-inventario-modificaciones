/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package AdministradoresGUI;

import ControladoresGUI.EditarTipoClienteControlador;
import EntidadesJPA.TipoClientes;
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
public class EditarTipoClienteAdministrador 
{
    public Scene scene;
    public Stage stage;
    public Parent root;
    public VBox pnlPrincipal;
    public TipoClientes TipoCliente;
    
    public EditarTipoClienteAdministrador(Scene scene, Stage stage, TipoClientes TipoClientes) {
        this.scene = scene;
        this.stage = stage;
        this.TipoCliente = TipoClientes;
    }

    public void abrirEditarTipoCliente(){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Principal/EditarTipoCliente.fxml"));
            scene.setRoot((Parent) loader.load());
            EditarTipoClienteControlador controller = loader.<EditarTipoClienteControlador>getController();
            controller.initManager(this);
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();
        } 
        catch (IOException ex) 
        {
            Logger.getLogger(EditarTipoClienteAdministrador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void cerrarEditarTipoCliente()
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
