/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package AdministradoresGUI;

import ControladoresGUI.PedidoNuevoEspecialControlador;
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
public class PedidoNuevoEspecialAdministrador 
{
    private Scene scene;
    private Stage stage;
    public Parent root;
    public PedidosAdministrador PedidoAdministrador;
    
    public PedidoNuevoEspecialAdministrador(Scene scene, Stage stage, PedidosAdministrador PedidoAdministrador)
    {
        this.scene = scene;
        this.stage = stage;
        this.PedidoAdministrador = PedidoAdministrador;
    }

    public void abrirPanelPedidoNuevoEspecial()
    {
        try 
        {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Principal/PedidoNuevoEspecial.fxml"));
            root = (Parent) loader.load();
            PedidoNuevoEspecialControlador controller = loader.<PedidoNuevoEspecialControlador>getController();
            controller.initManager(this);
        } 
        catch (IOException ex) 
        {
            Logger.getLogger(PedidoNuevoEspecialAdministrador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void showPnlPedidoNuevoEspecial(){
        PedidoAdministrador.showPnlPedidosNuevoEspecial(); 
    }
    
    public void showMensajes(String Mensaje)
    {
         Scene scen = new Scene(new StackPane());
         Stage stag = new Stage();
         MensajesAdministrador MensajesAdministrador = new MensajesAdministrador(scen,stag,Mensaje);
         MensajesAdministrador.abrirMensajes();
    }
}
