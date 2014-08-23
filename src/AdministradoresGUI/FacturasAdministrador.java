/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package AdministradoresGUI;

import ControladoresGUI.FacturasControlador;
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
public class FacturasAdministrador 
{
    private Scene scene;
    private Stage stage;
    public Parent root;
    public FinanzasAdministrador FinanzasAdministrador;
    
    public FacturasAdministrador(Scene scene, Stage stage, FinanzasAdministrador FinanzasAdministrador)
    {
        this.scene = scene;
        this.stage = stage;
        this.FinanzasAdministrador = FinanzasAdministrador;
    }

    public void abrirPanelFacturas()
    {
        try 
        {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Principal/Facturas.fxml"));
            root = (Parent) loader.load();
            FacturasControlador controller = loader.<FacturasControlador>getController();
            controller.initManager(this);
        } 
        catch (IOException ex) 
        {
            Logger.getLogger(FacturasAdministrador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void showPnlFacturas(){
        FinanzasAdministrador.showPnlFacturas(); 
    }
    
    public void showMensajes(String Mensaje)
    {
         Scene scen = new Scene(new StackPane());
         Stage stag = new Stage();
         MensajesAdministrador MensajesAdministrador = new MensajesAdministrador(scen,stag,Mensaje);
         MensajesAdministrador.abrirMensajes();
    }
}
