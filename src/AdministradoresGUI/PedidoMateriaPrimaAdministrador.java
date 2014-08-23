/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package AdministradoresGUI;

import ControladoresGUI.FabricacionControlador;
import ControladoresGUI.PedidoMateriaPrimaControlador;
import java.io.IOException;
import java.util.logging.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.scene.layout.StackPane;

import javafx.stage.Stage;

/**
 *
 * @author luis__000
 */
public class PedidoMateriaPrimaAdministrador 
{
    private Scene scene;
    private Stage stage;
    public Parent root;
    public ProduccionAdministrador ProduccionAdministrador;
    
    public PedidoMateriaPrimaAdministrador(Scene scene, Stage stage, ProduccionAdministrador ProduccionAdministrador)
    {
        this.scene = scene;
        this.stage = stage;
        this.ProduccionAdministrador = ProduccionAdministrador;
    }

    public void abrirPanelPedidoMateriaPrima()
    {
        try 
        {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Principal/PedidoMateriaPrima.fxml"));
            root = (Parent) loader.load();
            PedidoMateriaPrimaControlador controller = loader.<PedidoMateriaPrimaControlador>getController();
            controller.initManager(this);
        } 
        catch (IOException ex) 
        {
            Logger.getLogger(PedidoMateriaPrimaAdministrador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void showPnlPedidoMateriaPrima(){
        ProduccionAdministrador.showPnlFabricacion(); 
    }
    
    public void showMensajes(String Mensaje)
    {
         Scene scen = new Scene(new StackPane());
         Stage stag = new Stage();
         MensajesAdministrador MensajesAdministrador = new MensajesAdministrador(scen,stag,Mensaje);
         MensajesAdministrador.abrirMensajes();
    }
    
}
