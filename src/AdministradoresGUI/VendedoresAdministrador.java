/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package AdministradoresGUI;

import ControladoresGUI.VendedoresControlador;
import EntidadesJPA.Vendedores;
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
public class VendedoresAdministrador 
{
    private Scene scene;
    private Stage stage;
    public Parent root;
    public PedidosAdministrador PedidoAdministrador;
    
    public VendedoresAdministrador(Scene scene, Stage stage, PedidosAdministrador PedidoAdministrador)
    {
        this.scene = scene;
        this.stage = stage;
        this.PedidoAdministrador = PedidoAdministrador;
    }
    
    public void abrirPanelVendedores()
    {
        try 
        {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Principal/Vendedores.fxml"));
            root = (Parent) loader.load();
            VendedoresControlador controller = loader.<VendedoresControlador>getController();
            controller.initManager(this);
        } 
        catch (IOException ex) 
        {
            Logger.getLogger(VendedoresAdministrador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void showPnlVendedodres(){
        PedidoAdministrador.showPnlVendedores();
    }
    
    public void showNuevoVendedor()
    {
         Scene scen = new Scene(new StackPane());
         Stage stag = new Stage();
         NuevoVendedorAdministrador NuevoVendedorAdministrador = new NuevoVendedorAdministrador(scen,stag);
         NuevoVendedorAdministrador.abrirNuevoVendedor();
    }
    
    public void showEditarVendedores(Vendedores Vendedores)
    {
         Scene scen = new Scene(new StackPane());
         Stage stag = new Stage();
         EditarVendedorAdministrador EditarVendedorAdministrador = new EditarVendedorAdministrador(scen,stag,Vendedores);
         EditarVendedorAdministrador.abrirEditarVendedor();
    }

    public void showMensajes(String Mensaje)
    {
         Scene scen = new Scene(new StackPane());
         Stage stag = new Stage();
         MensajesAdministrador MensajesAdministrador = new MensajesAdministrador(scen,stag,Mensaje);
         MensajesAdministrador.abrirMensajes();
    }

    public void showListaClientes(Vendedores Vendedor) 
    {
        Scene scen = new Scene(new StackPane());
        Stage stag = new Stage();
        ListaClientesAdministrador ListaClientesAdministrador = new ListaClientesAdministrador(scen,stag,Vendedor);
        ListaClientesAdministrador.abrirListaClientes();
    }
}
