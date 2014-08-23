/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package AdministradoresGUI;

import ControladoresGUI.ListaClientesControlador;
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
public class ListaClientesAdministrador 
{
    public Scene scene;
    public Stage stage;
    public Parent root;
    public VBox pnlPrincipal;
    public Vendedores Vendedor;
    
    public ListaClientesAdministrador(Scene scene, Stage stage, Vendedores Vendedor) {
        this.scene = scene;
        this.stage = stage;
        this.Vendedor = Vendedor;
    }

    public void abrirListaClientes(){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Principal/ListaClientes.fxml"));
            scene.setRoot((Parent) loader.load());
            ListaClientesControlador controller = loader.<ListaClientesControlador>getController();
            controller.initManager(this);
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();
        } 
        catch (IOException ex) 
        {
            Logger.getLogger(ListaClientesAdministrador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void cerrarListaClientes()
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
