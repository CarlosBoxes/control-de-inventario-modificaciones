/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package AdministradoresGUI;

import ControladoresGUI.EditarProveedorControlador;
import EntidadesJPA.Proveedores;
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
public class EditarProveedorAdministrador 
{
    public Scene scene;
    public Stage stage;
    public Parent root;
    public VBox pnlPrincipal;
    public Proveedores Proveedores;
    
    public EditarProveedorAdministrador(Scene scene, Stage stage, Proveedores Proveedores) {
        this.scene = scene;
        this.stage = stage;
        this.Proveedores = Proveedores;
    }

    public void abrirEditarProveedor(){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Principal/EditarProveedor.fxml"));
            scene.setRoot((Parent) loader.load());
            EditarProveedorControlador controller = loader.<EditarProveedorControlador>getController();
            controller.initManager(this);
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();
        } 
        catch (IOException ex) 
        {
            Logger.getLogger(EditarProveedorAdministrador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void cerrarEditarProveedor()
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
