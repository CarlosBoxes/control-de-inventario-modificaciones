/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package AdministradoresGUI;

import ControladoresGUI.EditarBodegaControlador;
import ControladoresGUI.EditarBodegaMPControlador;
import ControladoresGUI.EditarTipoClienteControlador;
import EntidadesJPA.BodegaProduccion;
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
public class EditarBodegaMPAdministrador 
{
    public Scene scene;
    public Stage stage;
    public Parent root;
    public VBox pnlPrincipal;
    public BodegaProduccion Bodega;
    
    public EditarBodegaMPAdministrador(Scene scene, Stage stage, BodegaProduccion Bodega) {
        this.scene = scene;
        this.stage = stage;
        this.Bodega = Bodega;
    }

    public void abrirEditarBodegaMP(){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Principal/EditarBodegaMP.fxml"));
            scene.setRoot((Parent) loader.load());
            EditarBodegaMPControlador controller = loader.<EditarBodegaMPControlador>getController();
            controller.initManager(this);
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();
        } 
        catch (IOException ex) 
        {
            Logger.getLogger(EditarBodegaMPAdministrador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void cerrarEditarBodegaMP()
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
