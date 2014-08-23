/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package AdministradoresGUI;
import ControladoresGUI.PedidosControlador;
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
public class PedidosAdministrador 
{
    private Scene scene;
    private Stage stage;
    public Parent root;
    public VBox pnlPrincipal;
    
    public PedidosAdministrador(Scene scene, Stage stage) 
    {
        this.scene = scene;
        this.stage = stage;
    }

    public void abrirPanelPedidos()
    {
        try 
        {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Principal/Pedidos.fxml"));
            root = (Parent) loader.load();
            PedidosControlador controller = loader.<PedidosControlador>getController();
            controller.initManager(this);
        } 
        catch (IOException ex) 
        {
            Logger.getLogger(PedidosAdministrador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void showPnlPedidosNuevo()
    {      
         Parent root = null;
         PedidoNuevoAdministrador pedidoNuevoAdministrador = new PedidoNuevoAdministrador(scene,stage, this);
         pedidoNuevoAdministrador.abrirPanelPedidoNuevo();
         root = pedidoNuevoAdministrador.root;
         if(pnlPrincipal.getChildren().size()>1)
            pnlPrincipal.getChildren().remove(1);
        pnlPrincipal.getChildren().add(root);
    }
    
    public void showPnlPedidosNuevoEspecial()
    {      
         Parent root = null;
         PedidoNuevoEspecialAdministrador PedidoNuevoEspecialAdministrador = new PedidoNuevoEspecialAdministrador(scene,stage, this);
         PedidoNuevoEspecialAdministrador.abrirPanelPedidoNuevoEspecial();
         root = PedidoNuevoEspecialAdministrador.root;
         if(pnlPrincipal.getChildren().size()>1)
            pnlPrincipal.getChildren().remove(1);
        pnlPrincipal.getChildren().add(root);
    }
    
     public void showPnlVendedores()
     {      
         Parent root = null;
         VendedoresAdministrador VendedoresAdministrador = new VendedoresAdministrador(scene,stage, this);
         VendedoresAdministrador.abrirPanelVendedores();
         root = VendedoresAdministrador.root;
         if(pnlPrincipal.getChildren().size()>1)
            pnlPrincipal.getChildren().remove(1);
        pnlPrincipal.getChildren().add(root);
    }
     
     public void showPnlTipoVendedor()
     {      
         Parent root = null;
         TipoAdministrador TipoVendedorAdministrador = new TipoAdministrador(scene,stage, this);
         TipoVendedorAdministrador.abrirPanelTipoVendedor();
         root = TipoVendedorAdministrador.root;
         if(pnlPrincipal.getChildren().size()>1)
            pnlPrincipal.getChildren().remove(1);
        pnlPrincipal.getChildren().add(root);
    }
     
     public void showPnlClientes()
     {      
         Parent root = null;
         ClientesAdministrador ClientesAdministrador = new ClientesAdministrador(scene,stage, this);
         ClientesAdministrador.abrirPanelTipoVendedor();
         root = ClientesAdministrador.root;
         if(pnlPrincipal.getChildren().size()>1)
            pnlPrincipal.getChildren().remove(1);
        pnlPrincipal.getChildren().add(root);
    }
     
     
}
