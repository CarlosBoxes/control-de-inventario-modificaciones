/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package AdministradoresGUI;

import ControladoresGUI.NuevaLiquidacionControlador;
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
public class NuevaLiquidacionAdministrador 
{
    private Scene scene;
    private Stage stage;
    public Parent root;
    public LiquidacionesAdministrador LiquidacionesAdministrador;
    
    public NuevaLiquidacionAdministrador(Scene scene, Stage stage, LiquidacionesAdministrador LiquidacionesAdministrador)
    {
        this.scene = scene;
        this.stage = stage;
        this.LiquidacionesAdministrador = LiquidacionesAdministrador;
    }

    public void abrirPanelNuevaLiquidacion()
    {
        try 
        {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Principal/NuevaLiquidacion.fxml"));
            root = (Parent) loader.load();
            NuevaLiquidacionControlador controller = loader.<NuevaLiquidacionControlador>getController();
            controller.initManager(this);
        } 
        catch (IOException ex) 
        {
            Logger.getLogger(NuevaLiquidacionAdministrador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void showPnlNuevaLiquidacion(){
        LiquidacionesAdministrador.showPnlNuevaLiquidacion(); 
    }
    
    public void showMensajes(String Mensaje)
    {
         Scene scen = new Scene(new StackPane());
         Stage stag = new Stage();
         MensajesAdministrador MensajesAdministrador = new MensajesAdministrador(scen,stag,Mensaje);
         MensajesAdministrador.abrirMensajes();
    }
    
}
