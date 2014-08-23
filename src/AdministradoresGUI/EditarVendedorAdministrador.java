/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package AdministradoresGUI;

import ControladoresGUI.EditarTipoVendedorControlador;
import ControladoresGUI.EditarVendedorControlador;
import EntidadesJPA.TipoVendedores;
import EntidadesJPA.Vendedores;
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
public class EditarVendedorAdministrador 
{
    public Scene scene;
    public Stage stage;
    public Parent root;
    public VBox pnlPrincipal;
    public Vendedores Vendedores;
    
    public EditarVendedorAdministrador(Scene scene, Stage stage, Vendedores Vendedores) {
        this.scene = scene;
        this.stage = stage;
        this.Vendedores = Vendedores;
    }

    public void abrirEditarVendedor(){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Principal/EditarVendedor.fxml"));
            scene.setRoot((Parent) loader.load());
            EditarVendedorControlador controller = loader.<EditarVendedorControlador>getController();
            controller.initManager(this);
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();
        } 
        catch (IOException ex) 
        {
            Logger.getLogger(EditarVendedorAdministrador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void cerrarEditarVendedor()
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
