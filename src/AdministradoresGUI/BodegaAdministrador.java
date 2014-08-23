/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package AdministradoresGUI;

import ControladoresGUI.BodegaControlador;
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
public class BodegaAdministrador 
{
    private Scene scene;
    private Stage stage;
    public Parent root;
    public BodegasAdministrador BodegasAdministrador;
    
    public BodegaAdministrador(Scene scene, Stage stage, BodegasAdministrador BodegasAdministrador)
    {
        this.scene = scene;
        this.stage = stage;
        this.BodegasAdministrador = BodegasAdministrador;
    }

    public void abrirPanelNuevaBodega()
    {
        try 
        {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Principal/Bodega.fxml"));
            root = (Parent) loader.load();
            BodegaControlador controller = loader.<BodegaControlador>getController();
            controller.initManager(this);
        } 
        catch (IOException ex) 
        {
            Logger.getLogger(BodegaAdministrador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void showPnlBodega(){
        BodegasAdministrador.showPnlBodega(); 
    }
    
    public void showNuevaBodega()
    {
         Scene scen = new Scene(new StackPane());
         Stage stag = new Stage();
         NuevaBodegaAdministrador NuevaBodegaAdministrador = new NuevaBodegaAdministrador(scen,stag);
         NuevaBodegaAdministrador.abrirNuevaBodega();
    }
    
    public void showEditarBodega(BodegaProductos Bodega)
    {
         Scene scen = new Scene(new StackPane());
         Stage stag = new Stage();
         EditarBodegaAdministrador EditarBodegaAdministrador = new EditarBodegaAdministrador(scen,stag,Bodega);
         EditarBodegaAdministrador.abrirEditarBodega();
    }
    
    public void showMensajes(String Mensaje)
    {
         Scene scen = new Scene(new StackPane());
         Stage stag = new Stage();
         MensajesAdministrador MensajesAdministrador = new MensajesAdministrador(scen,stag,Mensaje);
         MensajesAdministrador.abrirMensajes();
    }
}
