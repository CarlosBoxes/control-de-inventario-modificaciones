/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package AdministradoresGUI;

import ControladoresGUI.EditarBodegaControlador;
import ControladoresGUI.EditarTipoClienteControlador;
import EntidadesJPA.BodegaProductos;
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
public class EditarBodegaAdministrador 
{
    public Scene scene;
    public Stage stage;
    public Parent root;
    public VBox pnlPrincipal;
    public BodegaProductos Bodega;
    
    public EditarBodegaAdministrador(Scene scene, Stage stage, BodegaProductos Bodega) {
        this.scene = scene;
        this.stage = stage;
        this.Bodega = Bodega;
    }

    public void abrirEditarBodega(){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Principal/EditarBodega.fxml"));
            scene.setRoot((Parent) loader.load());
            EditarBodegaControlador controller = loader.<EditarBodegaControlador>getController();
            controller.initManager(this);
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();
        } 
        catch (IOException ex) 
        {
            Logger.getLogger(EditarBodegaAdministrador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void cerrarEditarBodega()
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
