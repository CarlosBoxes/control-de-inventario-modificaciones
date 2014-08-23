/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package AdministradoresGUI;

import ControladoresGUI.FabricacionControlador;
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
public class FabricacionAdministrador 
{
    private Scene scene;
    private Stage stage;
    public Parent root;
    public ProduccionAdministrador ProduccionAdministrador;
    
    public FabricacionAdministrador(Scene scene, Stage stage, ProduccionAdministrador ProduccionAdministrador)
    {
        this.scene = scene;
        this.stage = stage;
        this.ProduccionAdministrador = ProduccionAdministrador;
    }

    public void abrirPanelFabricacion()
    {
        try 
        {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Principal/Fabricacion.fxml"));
            root = (Parent) loader.load();
            FabricacionControlador controller = loader.<FabricacionControlador>getController();
            controller.initManager(this);
        } 
        catch (IOException ex) 
        {
            Logger.getLogger(FabricacionAdministrador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void showPnlFabricacion(){
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
