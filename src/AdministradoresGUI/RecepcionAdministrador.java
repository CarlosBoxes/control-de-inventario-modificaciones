/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package AdministradoresGUI;

import ControladoresGUI.RecepcionControlador;
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
public class RecepcionAdministrador 
{
    private Scene scene;
    private Stage stage;
    public Parent root;
    public ProduccionAdministrador ProduccionAdministrador;
    
    public RecepcionAdministrador(Scene scene, Stage stage, ProduccionAdministrador BodegasAdministrador)
    {
        this.scene = scene;
        this.stage = stage;
        this.ProduccionAdministrador = BodegasAdministrador;
    }

    public void abrirPanelRecepcion()
    {
        try 
        {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Principal/Recepcion.fxml"));
            root = (Parent) loader.load();
            RecepcionControlador controller = loader.<RecepcionControlador>getController();
            controller.initManager(this);
        } 
        catch (IOException ex) 
        {
            Logger.getLogger(RecepcionAdministrador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void showPnlRecepcion(){
        ProduccionAdministrador.showPnlRecepcion(); 
    }
    
    public void showMensajes(String Mensaje)
    {
         Scene scen = new Scene(new StackPane());
         Stage stag = new Stage();
         MensajesAdministrador MensajesAdministrador = new MensajesAdministrador(scen,stag,Mensaje);
         MensajesAdministrador.abrirMensajes();
    } 
}
