/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package AdministradoresGUI;

import ControladoresGUI.RecuperarContraseñasControlador;
import EntidadesJPA.Usuario;
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
public class RecuperarContraseñasAdministrador 
{
    private Scene scene;
    private Stage stage;
    public Parent root;
    public OpcionesAdministrador OpcionesAdministrador;
    
    public RecuperarContraseñasAdministrador(Scene scene, Stage stage, OpcionesAdministrador OpcionesAdministrador)
    {
        this.scene = scene;
        this.stage = stage;
        this.OpcionesAdministrador = OpcionesAdministrador;
    }

    public void abrirPanelRecuperarContraseñas()
    {
        try 
        {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Principal/RecuperarContraseñas.fxml"));
            root = (Parent) loader.load();
            RecuperarContraseñasControlador controller = loader.<RecuperarContraseñasControlador>getController();
            controller.initManager(this);
        } 
        catch (IOException ex) 
        {
            Logger.getLogger(RecuperarContraseñasAdministrador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void showPnlRecuperarContraseñas(){
        OpcionesAdministrador.showPnlRecuperarContraseñas(); 
    }
    
    public void showNuevoUsuarios()
    {
         Scene scen = new Scene(new StackPane());
         Stage stag = new Stage();
         NuevoUsuarioAdministrador NuevoUsuarioAdministrador = new NuevoUsuarioAdministrador(scen,stag);
         NuevoUsuarioAdministrador.abrirNuevoUsuario();
    }
    
    public void showEditarUsuarios(Usuario Usuario)
    {
         Scene scen = new Scene(new StackPane());
         Stage stag = new Stage();
         EditarUsuarioAdministrador EditarUsuarioAdministrador = new EditarUsuarioAdministrador(scen,stag,Usuario);
         EditarUsuarioAdministrador.abrirEditarUsuario();
    }
    
    public void showMensajes(String Mensaje)
    {
         Scene scen = new Scene(new StackPane());
         Stage stag = new Stage();
         MensajesAdministrador MensajesAdministrador = new MensajesAdministrador(scen,stag,Mensaje);
         MensajesAdministrador.abrirMensajes();
    }
}
