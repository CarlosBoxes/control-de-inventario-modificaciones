/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package AdministradoresGUI;

import ControladoresGUI.ClientesControlador;
import EntidadesJPA.Clientes;
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
public class ClientesAdministrador 
{
    private Scene scene;
    private Stage stage;
    public Parent root;
    public PedidosAdministrador PedidosAdministrador;
    
    public ClientesAdministrador(Scene scene, Stage stage, PedidosAdministrador PedidosAdministrador)
    {
        this.scene = scene;
        this.stage = stage;
        this.PedidosAdministrador = PedidosAdministrador;
    }

    public void abrirPanelTipoVendedor()
    {
        try 
        {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Principal/Clientes.fxml"));
            root = (Parent) loader.load();
            ClientesControlador controller = loader.<ClientesControlador>getController();
            controller.initManager(this);
        } 
        catch (IOException ex) 
        {
            Logger.getLogger(ClientesAdministrador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void showPnlClientes(){
        PedidosAdministrador.showPnlTipoVendedor(); 
    }
    
    public void showNuevoClientes()
    {
         Scene scen = new Scene(new StackPane());
         Stage stag = new Stage();
         NuevoClienteAdministrador NuevoClienteAdministrador = new NuevoClienteAdministrador(scen,stag);
         NuevoClienteAdministrador.abrirNuevoCliete();
    }
    
    public void showEditarClientes(Clientes Cliente)
    {
         Scene scen = new Scene(new StackPane());
         Stage stag = new Stage();
         EditarClienteAdministrador EditarClienteAdministrador = new EditarClienteAdministrador(scen,stag,Cliente);
         EditarClienteAdministrador.abrirEditarCliete();
    }
    
    public void showMensajes(String Mensaje)
    {
         Scene scen = new Scene(new StackPane());
         Stage stag = new Stage();
         MensajesAdministrador MensajesAdministrador = new MensajesAdministrador(scen,stag,Mensaje);
         MensajesAdministrador.abrirMensajes();
    }
}
