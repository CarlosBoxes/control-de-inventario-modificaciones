/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package AdministradoresGUI;

import ControladoresGUI.BodegaControlador;
import ControladoresGUI.DefectuosoControlador;
import EntidadesJPA.BodegaProductos;
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
public class DefectuosoAdministrador 
{
    private Scene scene;
    private Stage stage;
    public Parent root;
    public BodegasAdministrador BodegasAdministrador;
    
    public DefectuosoAdministrador(Scene scene, Stage stage, BodegasAdministrador BodegasAdministrador)
    {
        this.scene = scene;
        this.stage = stage;
        this.BodegasAdministrador = BodegasAdministrador;
    }

    public void abrirPanelDefectuoso()
    {
        try 
        {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Principal/Defectuosos.fxml"));
            root = (Parent) loader.load();
            DefectuosoControlador controller = loader.<DefectuosoControlador>getController();
            controller.initManager(this);
        } 
        catch (IOException ex) 
        {
            Logger.getLogger(DefectuosoAdministrador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void showPnlBodega(){
        BodegasAdministrador.showPnlBodega(); 
    }
    
    public void showMensajes(String Mensaje)
    {
         Scene scen = new Scene(new StackPane());
         Stage stag = new Stage();
         MensajesAdministrador MensajesAdministrador = new MensajesAdministrador(scen,stag,Mensaje);
         MensajesAdministrador.abrirMensajes();
    }
}
