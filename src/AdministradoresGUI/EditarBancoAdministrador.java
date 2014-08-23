/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package AdministradoresGUI;

import ControladoresGUI.EditarBancoControlador;
import ControladoresGUI.EditarBodegaControlador;
import ControladoresGUI.EditarBodegaMPControlador;
import ControladoresGUI.EditarTipoClienteControlador;
import EntidadesJPA.Bancos;
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
public class EditarBancoAdministrador 
{
    public Scene scene;
    public Stage stage;
    public Parent root;
    public VBox pnlPrincipal;
    public Bancos Banco;
    
    public EditarBancoAdministrador(Scene scene, Stage stage, Bancos Banco) {
        this.scene = scene;
        this.stage = stage;
        this.Banco = Banco;
    }

    public void abrirEditarBanco(){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Principal/EditarBanco.fxml"));
            scene.setRoot((Parent) loader.load());
            EditarBancoControlador controller = loader.<EditarBancoControlador>getController();
            controller.initManager(this);
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();
        } 
        catch (IOException ex) 
        {
            Logger.getLogger(EditarBancoAdministrador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void cerrarEditarBanco()
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
