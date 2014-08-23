/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package AdministradoresGUI;

import ControladoresGUI.ProductosClienteControlador;
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
public class ProductosClienteAdministrador 
{
    public Scene scene;
    public Stage stage;
    public Parent root;
    public VBox pnlPrincipal;
    public TipoClientes Tipo;
    
    public ProductosClienteAdministrador(Scene scene, Stage stage, TipoClientes Tipo) {
        this.scene = scene;
        this.stage = stage;
        this.Tipo = Tipo;
    }

    public void abrirProductosCliente(){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Principal/ProductosCliente.fxml"));
            scene.setRoot((Parent) loader.load());
            ProductosClienteControlador controller = loader.<ProductosClienteControlador>getController();
            controller.initManager(this);
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();
        } 
        catch (IOException ex) 
        {
            Logger.getLogger(ProductosClienteAdministrador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void cerrarProductosCliente()
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
