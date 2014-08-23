/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package AdministradoresGUI;

import ControladoresGUI.EditarTipoVendedorControlador;
import EntidadesJPA.TipoVendedores;
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
public class EditarTipoVendedorAdministrador 
{
    public Scene scene;
    public Stage stage;
    public Parent root;
    public VBox pnlPrincipal;
    public TipoVendedores TipoVendedores;
    
    public EditarTipoVendedorAdministrador(Scene scene, Stage stage, TipoVendedores TipoVendedores) {
        this.scene = scene;
        this.stage = stage;
        this.TipoVendedores = TipoVendedores;
    }

    public void abrirEditarTipoCliente(){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Principal/EditarTipoVendedor.fxml"));
            scene.setRoot((Parent) loader.load());
            EditarTipoVendedorControlador controller = loader.<EditarTipoVendedorControlador>getController();
            controller.initManager(this);
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();
        } 
        catch (IOException ex) 
        {
            Logger.getLogger(EditarTipoVendedorAdministrador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void cerrarEditarTipoVendedor()
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
