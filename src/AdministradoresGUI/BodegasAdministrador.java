/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package AdministradoresGUI;
import ControladoresGUI.BodegasControlador;
import java.io.IOException;
import java.util.logging.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
/**
 *
 * @author luis__000
 */
public class BodegasAdministrador 
{
    private Scene scene;
    private Stage stage;
    public Parent root;
    public VBox pnlPrincipal;
    
    public BodegasAdministrador(Scene scene, Stage stage) 
    {
        this.scene = scene;
        this.stage = stage;
    }

    public void abrirPanelBodegas()
    {
        try 
        {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Principal/Bodegas.fxml"));
            root = (Parent) loader.load();
            BodegasControlador controller = loader.<BodegasControlador>getController();
            controller.initManager(this);
        } 
        catch (IOException ex) 
        {
            Logger.getLogger(BodegasAdministrador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void showPnlProducto()
     {      
         Parent root = null;
         ProductosAdministrador ProductoAdministrador = new ProductosAdministrador(scene,stage, this);
         ProductoAdministrador.abrirPanelProducto();
         root = ProductoAdministrador.root;
         if(pnlPrincipal.getChildren().size()>1)
            pnlPrincipal.getChildren().remove(1);
        pnlPrincipal.getChildren().add(root);
    }
    
    public void showPnlProveedores()
     {      
         Parent root = null;
         ProveedoresAdministrador ProveedoresAdministrador = new ProveedoresAdministrador(scene,stage, this);
         ProveedoresAdministrador.abrirPanelProveedores();
         root = ProveedoresAdministrador.root;
         if(pnlPrincipal.getChildren().size()>1)
            pnlPrincipal.getChildren().remove(1);
        pnlPrincipal.getChildren().add(root);
    }
    
    public void showPnlBodega()
     {      
         Parent root = null;
         BodegaAdministrador NuevaBodegaAdministrador = new BodegaAdministrador(scene,stage, this);
         NuevaBodegaAdministrador.abrirPanelNuevaBodega();
         root = NuevaBodegaAdministrador.root;
         if(pnlPrincipal.getChildren().size()>1)
            pnlPrincipal.getChildren().remove(1);
        pnlPrincipal.getChildren().add(root);
    }
    
    public void showPnlDespacho()
     {      
         Parent root = null;
         DespachoAdministrador DespachoAdministrador = new DespachoAdministrador(scene,stage, this);
         DespachoAdministrador.abrirPanelDespacho();
         root = DespachoAdministrador.root;
         if(pnlPrincipal.getChildren().size()>1)
            pnlPrincipal.getChildren().remove(1);
        pnlPrincipal.getChildren().add(root);
    }
    
    public void showPnlPedidoProveedores()
    {      
         Parent root = null;
         PedidoProveedoresAdministrador PedidoProveedoresAdministrador = new PedidoProveedoresAdministrador(scene,stage, this);
         PedidoProveedoresAdministrador.abrirPanelPedidoProveedores();
         root = PedidoProveedoresAdministrador.root;
         if(pnlPrincipal.getChildren().size()>1)
            pnlPrincipal.getChildren().remove(1);
        pnlPrincipal.getChildren().add(root);
    }
    
    public void showPnlDefectuoso()
    {      
         Parent root = null;
         DefectuosoAdministrador DefectuosoAdministrador = new DefectuosoAdministrador(scene,stage, this);
         DefectuosoAdministrador.abrirPanelDefectuoso();
         root = DefectuosoAdministrador.root;
         if(pnlPrincipal.getChildren().size()>1)
            pnlPrincipal.getChildren().remove(1);
        pnlPrincipal.getChildren().add(root);
    }
}
