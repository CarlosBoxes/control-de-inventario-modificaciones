/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package AdministradoresGUI;


import ControladoresGUI.ProveedoresControlador;
import EntidadesJPA.Proveedores;
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
public class ProveedoresAdministrador 
{
    private Scene scene;
    private Stage stage;
    public Parent root;
    public BodegasAdministrador BodegasAdministrador;
    
    public ProveedoresAdministrador(Scene scene, Stage stage, BodegasAdministrador BodegasAdministrador)
    {
        this.scene = scene;
        this.stage = stage;
        this.BodegasAdministrador = BodegasAdministrador;
    }

    public void abrirPanelProveedores()
    {
        try 
        {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Principal/Proveedores.fxml"));
            root = (Parent) loader.load();
            ProveedoresControlador controller = loader.<ProveedoresControlador>getController();
            controller.initManager(this);
        } 
        catch (IOException ex) 
        {
            Logger.getLogger(ProveedoresAdministrador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void showPnlProveedores(){
        BodegasAdministrador.showPnlProveedores(); 
    }
    
    public void showNuevoProveedor()
    {
         Scene scen = new Scene(new StackPane());
         Stage stag = new Stage();
         NuevoProveedorAdministrador NuevoProveedorAdministrador = new NuevoProveedorAdministrador(scen,stag);
         NuevoProveedorAdministrador.abrirNuevoProveedor();
    }
    
    public void showEditarProveedor(Proveedores Proveedor)
    {
         Scene scen = new Scene(new StackPane());
         Stage stag = new Stage();
         EditarProveedorAdministrador EditarProveedorAdministrador = new EditarProveedorAdministrador(scen,stag,Proveedor);
         EditarProveedorAdministrador.abrirEditarProveedor();
    }
    
    public void showMensajes(String Mensaje)
    {
         Scene scen = new Scene(new StackPane());
         Stage stag = new Stage();
         MensajesAdministrador MensajesAdministrador = new MensajesAdministrador(scen,stag,Mensaje);
         MensajesAdministrador.abrirMensajes();
    }
}
