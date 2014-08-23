/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package AdministradoresGUI;

import ControladoresGUI.NuevaLiquidacionEspecialControlador;
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
public class NuevaLiquidacionEspecialAdministrador 
{
    private Scene scene;
    private Stage stage;
    public Parent root;
    public LiquidacionesAdministrador LiquidacionesAdministrador;
    
    public NuevaLiquidacionEspecialAdministrador(Scene scene, Stage stage, LiquidacionesAdministrador LiquidacionesAdministrador)
    {
        this.scene = scene;
        this.stage = stage;
        this.LiquidacionesAdministrador = LiquidacionesAdministrador;
    }

    public void abrirPanelNuevaLiquidacionEspecial()
    {
        try 
        {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Principal/NuevaLiquidacionEspecial.fxml"));
            root = (Parent) loader.load();
            NuevaLiquidacionEspecialControlador controller = loader.<NuevaLiquidacionEspecialControlador>getController();
            controller.initManager(this);
        } 
        catch (IOException ex) 
        {
            Logger.getLogger(NuevaLiquidacionEspecialAdministrador.class.getName()).log(Level.SEVERE, null, ex);
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
