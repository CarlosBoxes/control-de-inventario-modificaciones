/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package AdministradoresGUI;
import ControladoresGUI.VentanaPrincipalControlador;
import EntidadesJPA.Usuario;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author luis__000
 */
public class VentanaPrincipalAdministrador 
{
    private Scene scene;
    private Stage stage;
    public Usuario Usuario;
    
    public VentanaPrincipalAdministrador(Scene scene, Stage stage, Usuario Usuario) 
    {
        this.scene = scene;
        this.stage = stage;
        this.Usuario = Usuario;
    }
    
     public void showVentanaPrincipal()
     {
        try 
        {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Principal/VentanaPrincipal.fxml"));
            scene.setRoot((Parent) loader.load());
            VentanaPrincipalControlador controller = loader.<VentanaPrincipalControlador>getController();
            controller.initManager(this);
        } 
        catch (IOException ex) 
        {
            Logger.getLogger(IniciorSesionAdministrador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     
     public Parent showPedidos()
     {
         Parent root = null;
         PedidosAdministrador PedidoAdministrador = new PedidosAdministrador(scene,stage);
         PedidoAdministrador.abrirPanelPedidos();
         root = PedidoAdministrador.root;
         return root;
     }
     
     public Parent showBodegas()
     {
         Parent root = null;
         BodegasAdministrador BodegasAdministrador = new BodegasAdministrador(scene,stage);
         BodegasAdministrador.abrirPanelBodegas();
         root = BodegasAdministrador.root;
         return root;
     }
     
     public Parent showLiquidaciones()
     {
         Parent root = null;
         LiquidacionesAdministrador LiquidacionesAdministrador = new LiquidacionesAdministrador(scene,stage);
         LiquidacionesAdministrador.abrirPanelLiquidaciones();
         root = LiquidacionesAdministrador.root;
         return root;
     }
     
     public Parent showFinanzas()
     {
         Parent root = null;
         FinanzasAdministrador FinanzasAdministrador = new FinanzasAdministrador(scene,stage, Usuario);
         FinanzasAdministrador.abrirPanelFinanzas();
         root = FinanzasAdministrador.root;
         return root;
     }
     
     public Parent showOpciones()
     {
         Parent root = null;
         OpcionesAdministrador OpcionesAdministrador = new OpcionesAdministrador(scene,stage,Usuario);
         OpcionesAdministrador.abrirPanelOpciones();
         root = OpcionesAdministrador.root;
         return root;
     }
     
     public Parent showProduccion()
     {
         Parent root = null;
         ProduccionAdministrador ProduccionAdministrador = new ProduccionAdministrador(scene,stage);
         ProduccionAdministrador.abrirPanelProduccion();
         root = ProduccionAdministrador.root;
         return root;
     }
}
