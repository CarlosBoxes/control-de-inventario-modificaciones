/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package AdministradoresGUI;

import ControladoresGUI.DespachoControlador;
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
public class DespachoAdministrador 
{
    private Scene scene;
    private Stage stage;
    public Parent root;
    public BodegasAdministrador BodegasAdministrador;
    
    public DespachoAdministrador(Scene scene, Stage stage, BodegasAdministrador BodegasAdministrador)
    {
        this.scene = scene;
        this.stage = stage;
        this.BodegasAdministrador = BodegasAdministrador;
    }

    public void abrirPanelDespacho()
    {
        try 
        {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Principal/Despacho.fxml"));
            root = (Parent) loader.load();
            DespachoControlador controller = loader.<DespachoControlador>getController();
            controller.initManager(this);
        } 
        catch (IOException ex) 
        {
            Logger.getLogger(DespachoAdministrador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void showPnlDespacho(){
        BodegasAdministrador.showPnlDespacho(); 
    }
    
    public void showMensajes(String Mensaje)
    {
         Scene scen = new Scene(new StackPane());
         Stage stag = new Stage();
         MensajesAdministrador MensajesAdministrador = new MensajesAdministrador(scen,stag,Mensaje);
         MensajesAdministrador.abrirMensajes();
    }   
}
