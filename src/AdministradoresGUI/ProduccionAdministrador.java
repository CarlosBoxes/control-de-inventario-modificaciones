/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package AdministradoresGUI;
import ControladoresGUI.ProduccionControlador;
import EntidadesJPA.Usuario;
import java.io.IOException;
import java.util.logging.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
/**
 *
 * @author luis__000
 */
public class ProduccionAdministrador 
{
    private Scene scene;
    private Stage stage;
    public Parent root;
    public VBox pnlPrincipal;
    public Usuario Usuario;
    
    public ProduccionAdministrador(Scene scene, Stage stage, Usuario Usuario) 
    {
        this.scene = scene;
        this.stage = stage;
        this.Usuario = Usuario;
    }

    public void abrirPanelProduccion()
    {
        try 
        {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Principal/Produccion.fxml"));
            root = (Parent) loader.load();
            ProduccionControlador controller = loader.<ProduccionControlador>getController();
            controller.initManager(this);
        } 
        catch (IOException ex) 
        {
            Logger.getLogger(ProduccionAdministrador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void showPnlFabricacion()
     {      
         Parent root = null;
         FabricacionAdministrador FabricacionAdministrador = new FabricacionAdministrador(scene,stage, this);
         FabricacionAdministrador.abrirPanelFabricacion();
         root = FabricacionAdministrador.root;
         if(pnlPrincipal.getChildren().size()>1)
            pnlPrincipal.getChildren().remove(1);
        pnlPrincipal.getChildren().add(root);
    }
//    
    public void showPnlPedidoMateriaPrima()
     {      
         Parent root = null;
         PedidoMateriaPrimaAdministrador PedidoMateriaPrimaAdministrador = new PedidoMateriaPrimaAdministrador(scene,stage, this);
         PedidoMateriaPrimaAdministrador.abrirPanelPedidoMateriaPrima();
         root = PedidoMateriaPrimaAdministrador.root;
         if(pnlPrincipal.getChildren().size()>1)
            pnlPrincipal.getChildren().remove(1);
        pnlPrincipal.getChildren().add(root);
    }
//    
    public void showPnlMateriaPrima()
     {      
         Parent root = null;
         MateriaPrimaAdministrador MateriaPrimaAdministrador = new MateriaPrimaAdministrador(scene,stage, this);
         MateriaPrimaAdministrador.abrirPanelMateriaPrima();
         root = MateriaPrimaAdministrador.root;
         if(pnlPrincipal.getChildren().size()>1)
            pnlPrincipal.getChildren().remove(1);
        pnlPrincipal.getChildren().add(root);
    }
//    
    public void showPnlBodegaMP()
     {      
         Parent root = null;
         BodegaMPAdministrador BodegaMPAdministrador = new BodegaMPAdministrador(scene,stage, this);
         BodegaMPAdministrador.abrirPanelNuevaBodegaMP();
         root = BodegaMPAdministrador.root;
         if(pnlPrincipal.getChildren().size()>1)
            pnlPrincipal.getChildren().remove(1);
        pnlPrincipal.getChildren().add(root);
    }
    
    public void showPnlRecepcion()
     {      
         Parent root = null;
         RecepcionAdministrador RecepcionAdministrador = new RecepcionAdministrador(scene,stage, this);
         RecepcionAdministrador.abrirPanelRecepcion();
         root = RecepcionAdministrador.root;
         if(pnlPrincipal.getChildren().size()>1)
            pnlPrincipal.getChildren().remove(1);
        pnlPrincipal.getChildren().add(root);
    }
}
