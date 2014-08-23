/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package AdministradoresGUI;

import ControladoresGUI.EditarBancoControlador;
import ControladoresGUI.EditarBodegaControlador;
import ControladoresGUI.EditarBodegaMPControlador;
import ControladoresGUI.EditarChequeClienteControlador;
import ControladoresGUI.EditarChequeProveedorControlador;
import ControladoresGUI.EditarTipoClienteControlador;
import EntidadesJPA.Bancos;
import EntidadesJPA.BodegaProduccion;
import EntidadesJPA.BodegaProductos;
import EntidadesJPA.ChequesClientes;
import EntidadesJPA.ChequesProveedores;
import EntidadesJPA.TipoClientes;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author luis__000
 */
public class EditarChequeProveedorAdministrador 
{
    public Scene scene;
    public Stage stage;
    public Parent root;
    public VBox pnlPrincipal;
    public ChequesProveedores ChequeProveedor;
    
    public EditarChequeProveedorAdministrador(Scene scene, Stage stage, ChequesProveedores ChequeProveedor) {
        this.scene = scene;
        this.stage = stage;
        this.ChequeProveedor = ChequeProveedor;
    }

    public void abrirEditarChequeProveedor()
    {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Principal/EditarChequeProveedor.fxml"));
            scene.setRoot((Parent) loader.load());
            EditarChequeProveedorControlador controller = loader.<EditarChequeProveedorControlador>getController();
            controller.initManager(this);
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();
        } 
        catch (IOException ex) 
        {
            Logger.getLogger(EditarChequeProveedorAdministrador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void cerrarEditarChequeProveedor()
    {
        this.stage.close();
    }
    
    public void showMensajes(String Mensaje)
    {
         Scene scen = new Scene(new StackPane());
         Stage stag = new Stage();
         MensajesAdministrador MensajesAdministrador = new MensajesAdministrador(scen,stag,Mensaje);
         MensajesAdministrador.abrirMensajes();
    }
}
