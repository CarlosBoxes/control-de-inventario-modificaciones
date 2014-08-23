/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package AdministradoresGUI;

import ControladoresGUI.EditarDepositoControlador;
import EntidadesJPA.Depositos;
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
public class EditarDepositoAdministrador 
{
    public Scene scene;
    public Stage stage;
    public Parent root;
    public VBox pnlPrincipal;
    public Depositos Deposito;
    
    public EditarDepositoAdministrador(Scene scene, Stage stage, Depositos Deposito) {
        this.scene = scene;
        this.stage = stage;
        this.Deposito = Deposito;
    }

    public void abrirEditarDeposito(){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Principal/EditarDeposito.fxml"));
            scene.setRoot((Parent) loader.load());
            EditarDepositoControlador controller = loader.<EditarDepositoControlador>getController();
            controller.initManager(this);
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();
        } 
        catch (IOException ex) 
        {
            Logger.getLogger(EditarDepositoAdministrador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void cerrarEditarDeposito()
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
