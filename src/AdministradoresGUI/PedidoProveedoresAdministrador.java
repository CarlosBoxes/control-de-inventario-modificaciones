/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package AdministradoresGUI;


import ControladoresGUI.PedidoProveedoresControlador;
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
public class PedidoProveedoresAdministrador 
{
    private Scene scene;
    private Stage stage;
    public Parent root;
    public BodegasAdministrador BodegasAdministrador;
    
    public PedidoProveedoresAdministrador(Scene scene, Stage stage, BodegasAdministrador BodegasAdministrador)
    {
        this.scene = scene;
        this.stage = stage;
        this.BodegasAdministrador = BodegasAdministrador;
    }

    public void abrirPanelPedidoProveedores()
    {
        try 
        {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Principal/PedidoProveedores.fxml"));
            root = (Parent) loader.load();
            PedidoProveedoresControlador controller = loader.<PedidoProveedoresControlador>getController();
            controller.initManager(this);
        } 
        catch (IOException ex) 
        {
            Logger.getLogger(PedidoProveedoresAdministrador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void showPnlPedidoProveedores(){
        BodegasAdministrador.showPnlPedidoProveedores(); 
    }
    
    public void showMensajes(String Mensaje)
    {
         Scene scen = new Scene(new StackPane());
         Stage stag = new Stage();
         MensajesAdministrador MensajesAdministrador = new MensajesAdministrador(scen,stag,Mensaje);
         MensajesAdministrador.abrirMensajes();
    }
}
