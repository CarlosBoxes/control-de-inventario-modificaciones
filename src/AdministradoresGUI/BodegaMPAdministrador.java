/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package AdministradoresGUI;

import ControladoresGUI.FabricacionControlador;
import ControladoresGUI.MateriaPrimaControlador;
import ControladoresGUI.BodegaMPControlador;
import EntidadesJPA.BodegaProduccion;
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
public class BodegaMPAdministrador 
{
    private Scene scene;
    private Stage stage;
    public Parent root;
    public ProduccionAdministrador ProduccionAdministrador;
    
    public BodegaMPAdministrador(Scene scene, Stage stage, ProduccionAdministrador ProduccionAdministrador)
    {
        this.scene = scene;
        this.stage = stage;
        this.ProduccionAdministrador = ProduccionAdministrador;
    }

    public void abrirPanelNuevaBodegaMP()
    {
        try 
        {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Principal/BodegaMP.fxml"));
            root = (Parent) loader.load();
            BodegaMPControlador controller = loader.<BodegaMPControlador>getController();
            controller.initManager(this);
        } 
        catch (IOException ex) 
        {
            Logger.getLogger(BodegaMPAdministrador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void showPnlBodegaMP(){
        ProduccionAdministrador.showPnlBodegaMP(); 
    }
    
    public void showNuevaBodegaMP()
    {
         Scene scen = new Scene(new StackPane());
         Stage stag = new Stage();
         NuevaBodegaMPAdministrador NuevaBodegaMPAdministrador = new NuevaBodegaMPAdministrador(scen,stag);
         NuevaBodegaMPAdministrador.abrirNuevaBodegaMP();
    }
    
    public void showEditarBodegaMP(BodegaProduccion Bodega)
    {
         Scene scen = new Scene(new StackPane());
         Stage stag = new Stage();
         EditarBodegaMPAdministrador EditarBodegaMPAdministrador = new EditarBodegaMPAdministrador(scen,stag,Bodega);
         EditarBodegaMPAdministrador.abrirEditarBodegaMP();
    }
    
    public void showMensajes(String Mensaje)
    {
         Scene scen = new Scene(new StackPane());
         Stage stag = new Stage();
         MensajesAdministrador MensajesAdministrador = new MensajesAdministrador(scen,stag,Mensaje);
         MensajesAdministrador.abrirMensajes();
    }
    
}
