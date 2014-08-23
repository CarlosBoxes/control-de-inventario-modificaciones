/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package AdministradoresGUI;

import ControladoresGUI.PedidoNuevoControlador;
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
public class PedidoNuevoAdministrador 
{
    private Scene scene;
    private Stage stage;
    public Parent root;
    public PedidosAdministrador PedidoAdministrador;
    
    public PedidoNuevoAdministrador(Scene scene, Stage stage, PedidosAdministrador PedidoAdministrador)
    {
        this.scene = scene;
        this.stage = stage;
        this.PedidoAdministrador = PedidoAdministrador;
    }

    public void abrirPanelPedidoNuevo()
    {
        try 
        {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Principal/PedidoNuevo.fxml"));
            root = (Parent) loader.load();
            PedidoNuevoControlador controller = loader.<PedidoNuevoControlador>getController();
            controller.initManager(this);
        } 
        catch (IOException ex) 
        {
            Logger.getLogger(PedidoNuevoAdministrador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void showPnlPedidoNuevo(){
        PedidoAdministrador.showPnlPedidosNuevo(); 
    }
    
    public void showMensajes(String Mensaje)
    {
         Scene scen = new Scene(new StackPane());
         Stage stag = new Stage();
         MensajesAdministrador MensajesAdministrador = new MensajesAdministrador(scen,stag,Mensaje);
         MensajesAdministrador.abrirMensajes();
    }
}
