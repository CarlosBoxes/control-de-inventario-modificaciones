/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package AdministradoresGUI;
import ControladoresGUI.OpcionesControlador;
import EntidadesJPA.Usuario;
import java.io.IOException;
import java.util.logging.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.scene.layout.VBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
/**
 *
 * @author luis__000
 */
public class OpcionesAdministrador 
{
    private Scene scene;
    private Stage stage;
    public Parent root;
    public VBox pnlPrincipal;
    public Usuario Usuario;
    
    public OpcionesAdministrador(Scene scene, Stage stage, Usuario Usuario) 
    {
        this.scene = scene;
        this.stage = stage;
        this.Usuario = Usuario;
    }

    public void abrirPanelOpciones()
    {
        try 
        {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Principal/Opciones.fxml"));
            root = (Parent) loader.load();
            OpcionesControlador controller = loader.<OpcionesControlador>getController();
            controller.initManager(this);
        } 
        catch (IOException ex) 
        {
            Logger.getLogger(OpcionesAdministrador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void showPnlUsuarios()
     {      
         Parent root = null;
         UsuariosAdministrador UsuariosAdministrador = new UsuariosAdministrador(scene,stage, this);
         UsuariosAdministrador.abrirPanelUsuarios();
         root = UsuariosAdministrador.root;
         if(pnlPrincipal.getChildren().size()>1)
            pnlPrincipal.getChildren().remove(1);
        pnlPrincipal.getChildren().add(root);
    }
    
    public void showPnlRecuperarContraseñas()
     {      
         Parent root = null;
         RecuperarContraseñasAdministrador RecuperarContraseñasAdministrador = new RecuperarContraseñasAdministrador(scene,stage, this);
         RecuperarContraseñasAdministrador.abrirPanelRecuperarContraseñas();
         root = RecuperarContraseñasAdministrador.root;
         if(pnlPrincipal.getChildren().size()>1)
            pnlPrincipal.getChildren().remove(1);
        pnlPrincipal.getChildren().add(root);
    }
}
