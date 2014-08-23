/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package AdministradoresGUI;

import ControladoresGUI.NuevaLiquidacionControlador;
import ControladoresGUI.ReportesLiquidacionControlador;
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
public class ReportesLiquidacionAdministrador 
{
    private Scene scene;
    private Stage stage;
    public Parent root;
    public LiquidacionesAdministrador LiquidacionesAdministrador;
    
    public ReportesLiquidacionAdministrador(Scene scene, Stage stage, LiquidacionesAdministrador LiquidacionesAdministrador)
    {
        this.scene = scene;
        this.stage = stage;
        this.LiquidacionesAdministrador = LiquidacionesAdministrador;
    }

    public void abrirPanelReportesLiquidacion()
    {
        try 
        {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Principal/ReportesLiquidacion.fxml"));
            root = (Parent) loader.load();
            ReportesLiquidacionControlador controller = loader.<ReportesLiquidacionControlador>getController();
            controller.initManager(this);
        } 
        catch (IOException ex) 
        {
            Logger.getLogger(ReportesLiquidacionAdministrador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void showPnlReportesLiquidacion(){
        LiquidacionesAdministrador.showPnlReportesLiquidacion(); 
    }

    public void showMensajes(String Mensaje) 
    {
        Scene scen = new Scene(new StackPane());
         Stage stag = new Stage();
         MensajesAdministrador MensajesAdministrador = new MensajesAdministrador(scen,stag,Mensaje);
         MensajesAdministrador.abrirMensajes();
    }
    
}
