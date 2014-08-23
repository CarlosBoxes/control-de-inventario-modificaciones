/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package AdministradoresGUI;

import ControladoresGUI.UsuariosControlador;
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
public class UsuariosAdministrador 
{
    public Scene scene;
    public Stage stage;
    public Parent root;
    public OpcionesAdministrador OpcionesAdministrador;
    
    public UsuariosAdministrador(Scene scene, Stage stage, OpcionesAdministrador OpcionesAdministrador)
    {
        this.scene = scene;
        this.stage = stage;
        this.OpcionesAdministrador = OpcionesAdministrador;
    }

    public void abrirPanelUsuarios()
    {
        try 
        {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Principal/Usuarios.fxml"));
            root = (Parent) loader.load();
            UsuariosControlador controller = loader.<UsuariosControlador>getController();
            controller.initManager(this);
        } 
        catch (IOException ex) 
        {
            Logger.getLogger(UsuariosAdministrador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void showPnlUsuarios(){
        OpcionesAdministrador.showPnlUsuarios(); 
    }
    
    public void showMensajes(String Mensaje)
    {
         Scene scen = new Scene(new StackPane());
         Stage stag = new Stage();
         MensajesAdministrador MensajesAdministrador = new MensajesAdministrador(scen,stag,Mensaje);
         MensajesAdministrador.abrirMensajes();
    }
}
