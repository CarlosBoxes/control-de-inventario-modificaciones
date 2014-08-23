/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package AdministradoresGUI;

import ControladoresGUI.ReportesFinanzasControlador;
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
public class ReportesFinanzasAdministrador 
{
    private Scene scene;
    private Stage stage;
    public Parent root;
    public LiquidacionesAdministrador FinanzasAdministrador;
    
    public ReportesFinanzasAdministrador(Scene scene, Stage stage, LiquidacionesAdministrador LiquidacionesAdministrador)
    {
        this.scene = scene;
        this.stage = stage;
        this.FinanzasAdministrador = LiquidacionesAdministrador;
    }

    public void abrirPanelReportesFinanzas()
    {
        try 
        {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Principal/ReportesFinanzas.fxml"));
            root = (Parent) loader.load();
            ReportesFinanzasControlador controller = loader.<ReportesFinanzasControlador>getController();
            controller.initManager(this);
        } 
        catch (IOException ex) 
        {
            Logger.getLogger(ReportesFinanzasAdministrador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void showPnlReportesFinanzas(){
        FinanzasAdministrador.showPnlReportesFinanzas(); 
    }
    
    public void showMensajes(String Mensaje) 
    {
        Scene scen = new Scene(new StackPane());
         Stage stag = new Stage();
         MensajesAdministrador MensajesAdministrador = new MensajesAdministrador(scen,stag,Mensaje);
         MensajesAdministrador.abrirMensajes();
    }
    
}
