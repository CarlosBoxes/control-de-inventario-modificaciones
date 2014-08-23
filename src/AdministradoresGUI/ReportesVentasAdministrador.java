/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package AdministradoresGUI;

import ControladoresGUI.ReportesVentasControlador;
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
public class ReportesVentasAdministrador 
{
    private Scene scene;
    private Stage stage;
    public Parent root;
    public LiquidacionesAdministrador LiquidacionesAdministrador;
    
    public ReportesVentasAdministrador(Scene scene, Stage stage, LiquidacionesAdministrador LiquidacionesAdministrador)
    {
        this.scene = scene;
        this.stage = stage;
        this.LiquidacionesAdministrador = LiquidacionesAdministrador;
    }

    public void abrirPanelReportes()
    {
        try 
        {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Principal/ReportesVentas.fxml"));
            root = (Parent) loader.load();
            ReportesVentasControlador controller = loader.<ReportesVentasControlador>getController();
            controller.initManager(this);
        } 
        catch (IOException ex) 
        {
            Logger.getLogger(ReportesVentasAdministrador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void showPnlReportes(){
        LiquidacionesAdministrador.showPnlReportes(); 
    }

    public void showMensajes(String Mensaje) 
    {
        Scene scen = new Scene(new StackPane());
         Stage stag = new Stage();
         MensajesAdministrador MensajesAdministrador = new MensajesAdministrador(scen,stag,Mensaje);
         MensajesAdministrador.abrirMensajes();
    }
    
}
