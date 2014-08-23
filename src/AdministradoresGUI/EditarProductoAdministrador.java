/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package AdministradoresGUI;

import ControladoresGUI.EditarProductoControlador;
import ControladoresGUI.NuevoProductoControlador;
import EntidadesJPA.Productos;
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
public class EditarProductoAdministrador 
{
    public Scene scene;
    public Stage stage;
    public Parent root;
    public VBox pnlPrincipal;
    public Productos Producto;
    
    public EditarProductoAdministrador(Scene scene, Stage stage, Productos Producto) {
        this.scene = scene;
        this.stage = stage;
        this.Producto = Producto;
    }

    public void abrirEditarProducto(){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Principal/EditarProducto.fxml"));
            scene.setRoot((Parent) loader.load());
            EditarProductoControlador controller = loader.<EditarProductoControlador>getController();
            controller.initManager(this);
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();
        } 
        catch (IOException ex) 
        {
            Logger.getLogger(EditarProductoAdministrador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void cerrarEditarProducto()
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
