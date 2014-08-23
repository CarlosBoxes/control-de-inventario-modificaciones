/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package AdministradoresGUI;

import ControladoresGUI.EditarBodegaControlador;
import ControladoresGUI.EditarBodegaMPControlador;
import ControladoresGUI.EditarMateriaPrimaControlador;
import ControladoresGUI.EditarTipoClienteControlador;
import EntidadesJPA.BodegaProduccion;
import EntidadesJPA.BodegaProductos;
import EntidadesJPA.MateriaPrima;
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
public class EditarMateriaPrimaAdministrador 
{
    public Scene scene;
    public Stage stage;
    public Parent root;
    public VBox pnlPrincipal;
    public MateriaPrima MateriaPrima;
    
    public EditarMateriaPrimaAdministrador(Scene scene, Stage stage, MateriaPrima MateriaPrima) {
        this.scene = scene;
        this.stage = stage;
        this.MateriaPrima = MateriaPrima;
    }

    public void abrirEditarMateriaPrima(){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Principal/EditarMateriaPrima.fxml"));
            scene.setRoot((Parent) loader.load());
            EditarMateriaPrimaControlador controller = loader.<EditarMateriaPrimaControlador>getController();
            controller.initManager(this);
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();
        } 
        catch (IOException ex) 
        {
            Logger.getLogger(EditarMateriaPrimaAdministrador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void cerrarEditarMateriaPrima()
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
