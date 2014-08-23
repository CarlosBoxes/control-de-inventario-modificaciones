/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package AdministradoresGUI;

import ControladoresGUI.FabricacionControlador;
import ControladoresGUI.MateriaPrimaControlador;
import EntidadesJPA.MateriaPrima;
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
public class MateriaPrimaAdministrador 
{
    private Scene scene;
    private Stage stage;
    public Parent root;
    public ProduccionAdministrador ProduccionAdministrador;
    
    public MateriaPrimaAdministrador(Scene scene, Stage stage, ProduccionAdministrador ProduccionAdministrador)
    {
        this.scene = scene;
        this.stage = stage;
        this.ProduccionAdministrador = ProduccionAdministrador;
    }

    public void abrirPanelMateriaPrima()
    {
        try 
        {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Principal/MateriaPrima.fxml"));
            root = (Parent) loader.load();
            MateriaPrimaControlador controller = loader.<MateriaPrimaControlador>getController();
            controller.initManager(this);
        } 
        catch (IOException ex) 
        {
            Logger.getLogger(MateriaPrimaAdministrador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void showPnlMateriaPrima(){
        ProduccionAdministrador.showPnlMateriaPrima(); 
    }
    
    public void showNuevaMateriaPrima()
    {
         Scene scen = new Scene(new StackPane());
         Stage stag = new Stage();
         NuevaMateriaPrimaAdministrador NuevaMateriaPrimaAdministrador = new NuevaMateriaPrimaAdministrador(scen,stag);
         NuevaMateriaPrimaAdministrador.abrirNuevaMateriaPrima();
    }
    
    public void showEditarMateriaPrima(MateriaPrima MateriaPrima)
    {
         Scene scen = new Scene(new StackPane());
         Stage stag = new Stage();
         EditarMateriaPrimaAdministrador EditarBodegaMPAdministrador = new EditarMateriaPrimaAdministrador(scen,stag,MateriaPrima);
         EditarBodegaMPAdministrador.abrirEditarMateriaPrima();
    }
    
    public void showMensajes(String Mensaje)
    {
         Scene scen = new Scene(new StackPane());
         Stage stag = new Stage();
         MensajesAdministrador MensajesAdministrador = new MensajesAdministrador(scen,stag,Mensaje);
         MensajesAdministrador.abrirMensajes();
    }
    
}
