/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package AdministradoresGUI;

import ControladoresGUI.BancosControlador;
import ControladoresGUI.DepositosControlador;
import EntidadesJPA.Bancos;
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
public class BancosAdministrador 
{
    private Scene scene;
    private Stage stage;
    public Parent root;
    public FinanzasAdministrador FinanzasAdministrador;
    
    public BancosAdministrador(Scene scene, Stage stage, FinanzasAdministrador FinanzasAdministrador)
    {
        this.scene = scene;
        this.stage = stage;
        this.FinanzasAdministrador = FinanzasAdministrador;
    }

    public void abrirPanelBancos()
    {
        try 
        {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Principal/Bancos.fxml"));
            root = (Parent) loader.load();
            BancosControlador controller = loader.<BancosControlador>getController();
            controller.initManager(this);
        } 
        catch (IOException ex) 
        {
            Logger.getLogger(BancosAdministrador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void showPnlBancos(){
        FinanzasAdministrador.showPnlBancos(); 
    }
    
    public void showNuevoBanco()
    {
         Scene scen = new Scene(new StackPane());
         Stage stag = new Stage();
         NuevoBancoAdministrador NuevoBancoAdministrador = new NuevoBancoAdministrador(scen,stag);
         NuevoBancoAdministrador.abrirNuevoBanco();
    }
    
    public void showEditarBanco(Bancos Banco)
    {
         Scene scen = new Scene(new StackPane());
         Stage stag = new Stage();
         EditarBancoAdministrador EditarBancoAdministrador = new EditarBancoAdministrador(scen,stag,Banco);
         EditarBancoAdministrador.abrirEditarBanco();
    }
    
    public void showMensajes(String Mensaje)
    {
         Scene scen = new Scene(new StackPane());
         Stage stag = new Stage();
         MensajesAdministrador MensajesAdministrador = new MensajesAdministrador(scen,stag,Mensaje);
         MensajesAdministrador.abrirMensajes();
    }
}
