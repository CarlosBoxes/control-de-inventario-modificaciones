/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package AdministradoresGUI;

import ControladoresGUI.EditarBancoControlador;
import ControladoresGUI.EditarBodegaControlador;
import ControladoresGUI.EditarBodegaMPControlador;
import ControladoresGUI.EditarChequeClienteControlador;
import ControladoresGUI.EditarTipoClienteControlador;
import EntidadesJPA.Bancos;
import EntidadesJPA.BodegaProduccion;
import EntidadesJPA.BodegaProductos;
import EntidadesJPA.ChequesClientes;
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
public class EditarChequeClienteAdministrador 
{
    public Scene scene;
    public Stage stage;
    public Parent root;
    public VBox pnlPrincipal;
    public ChequesClientes ChequeCliente;
    
    public EditarChequeClienteAdministrador(Scene scene, Stage stage, ChequesClientes ChequeCliente) {
        this.scene = scene;
        this.stage = stage;
        this.ChequeCliente = ChequeCliente;
    }

    public void abrirEditarChequeCliente()
    {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Principal/EditarChequeCliente.fxml"));
            scene.setRoot((Parent) loader.load());
            EditarChequeClienteControlador controller = loader.<EditarChequeClienteControlador>getController();
            controller.initManager(this);
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();
        } 
        catch (IOException ex) 
        {
            Logger.getLogger(EditarChequeClienteAdministrador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void cerrarEditarChequeCliente()
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
