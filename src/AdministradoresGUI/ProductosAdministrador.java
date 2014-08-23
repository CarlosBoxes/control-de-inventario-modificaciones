/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package AdministradoresGUI;

import ControladoresGUI.ProductosControlador;
import EntidadesJPA.Productos;
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
public class ProductosAdministrador 
{
    private Scene scene;
    private Stage stage;
    public Parent root;
    public BodegasAdministrador BodegasAdministrador;
    
    public ProductosAdministrador(Scene scene, Stage stage, BodegasAdministrador BodegasAdministrador)
    {
        this.scene = scene;
        this.stage = stage;
        this.BodegasAdministrador = BodegasAdministrador;
    }

    public void abrirPanelProducto()
    {
        try 
        {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Principal/Productos.fxml"));
            root = (Parent) loader.load();
            ProductosControlador controller = loader.<ProductosControlador>getController();
            controller.initManager(this);
        } 
        catch (IOException ex) 
        {
            Logger.getLogger(ProductosAdministrador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void showPnlProducto(){
        BodegasAdministrador.showPnlProducto(); 
    }
    
    public void showNuevoProducto()
    {
         Scene scen = new Scene(new StackPane());
         Stage stag = new Stage();
         NuevoProductoAdministrador NuevoProductoAdministrador = new NuevoProductoAdministrador(scen,stag);
         NuevoProductoAdministrador.abrirNuevoProducto();
    }
    
    public void showEditarProducto(Productos Producto)
    {
         Scene scen = new Scene(new StackPane());
         Stage stag = new Stage();
         EditarProductoAdministrador EditarProductoAdministrador = new EditarProductoAdministrador(scen,stag,Producto);
         EditarProductoAdministrador.abrirEditarProducto();
    }
    
    public void showMensajes(String Mensaje)
    {
         Scene scen = new Scene(new StackPane());
         Stage stag = new Stage();
         MensajesAdministrador MensajesAdministrador = new MensajesAdministrador(scen,stag,Mensaje);
         MensajesAdministrador.abrirMensajes();
    }
}
