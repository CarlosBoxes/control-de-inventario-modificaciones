/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package AdministradoresGUI;

import ControladoresGUI.TiposControlador;
import EntidadesJPA.TipoClientes;
import EntidadesJPA.TipoVendedores;
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
public class TipoAdministrador 
{
    private Scene scene;
    private Stage stage;
    public Parent root;
    public PedidosAdministrador PedidosAdministrador;
    
    public TipoAdministrador(Scene scene, Stage stage, PedidosAdministrador PedidosAdministrador)
    {
        this.scene = scene;
        this.stage = stage;
        this.PedidosAdministrador = PedidosAdministrador;
    }

    public void abrirPanelTipoVendedor()
    {
        try 
        {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Principal/Tipos.fxml"));
            root = (Parent) loader.load();
            TiposControlador controller = loader.<TiposControlador>getController();
            controller.initManager(this);
        } 
        catch (IOException ex) 
        {
            Logger.getLogger(TipoAdministrador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void showPnlTipoVendedor(){
        PedidosAdministrador.showPnlTipoVendedor(); 
    }
    
    public void showNuevoTipoVendedor()
    {
         Scene scen = new Scene(new StackPane());
         Stage stag = new Stage();
         NuevoTipoVendedorAdministrador NuevoTipoVendedorAdministrador = new NuevoTipoVendedorAdministrador(scen,stag);
         NuevoTipoVendedorAdministrador.abrirNuevoTipoVendedor();
    }
    
    public void showNuevoTipoCliente()
    {
         Scene scen = new Scene(new StackPane());
         Stage stag = new Stage();
         NuevoTipoClienteAdministrador NuevoTipoClienteAdministrador = new NuevoTipoClienteAdministrador(scen,stag);
         NuevoTipoClienteAdministrador.abrirNuevoTipoCliente();
    }
    
    public void showEditarTipoCliente(TipoClientes TipoCliente)
    {
         Scene scen = new Scene(new StackPane());
         Stage stag = new Stage();
         EditarTipoClienteAdministrador EditarTipoClienteAdministrador = new EditarTipoClienteAdministrador(scen,stag,TipoCliente);
         EditarTipoClienteAdministrador.abrirEditarTipoCliente();
    }
    
    public void showEditarTipoVendedor(TipoVendedores TipoVendedor)
    {
         Scene scen = new Scene(new StackPane());
         Stage stag = new Stage();
         EditarTipoVendedorAdministrador EditarTipoVendedorAdministrador = new EditarTipoVendedorAdministrador(scen,stag,TipoVendedor);
         EditarTipoVendedorAdministrador.abrirEditarTipoCliente();
    }
    
    public void showMensajes(String Mensaje)
    {
         Scene scen = new Scene(new StackPane());
         Stage stag = new Stage();
         MensajesAdministrador MensajesAdministrador = new MensajesAdministrador(scen,stag,Mensaje);
         MensajesAdministrador.abrirMensajes();
    }
    
    public void showListaClientes(TipoClientes Tipo)
    {
         Scene scen = new Scene(new StackPane());
         Stage stag = new Stage();
         ProductosClienteAdministrador ProductosClienteAdministrador = new ProductosClienteAdministrador(scen,stag,Tipo);
         ProductosClienteAdministrador.abrirProductosCliente();
    }
    
    public void showListaVendedores(TipoVendedores Tipo)
    {
         Scene scen = new Scene(new StackPane());
         Stage stag = new Stage();
         ProductosVendedorAdministrador ProductosVendedorAdministrador = new ProductosVendedorAdministrador(scen,stag,Tipo);
         ProductosVendedorAdministrador.abrirProductosCliente();
    }
}
