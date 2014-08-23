/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package AdministradoresGUI;

import ControladoresGUI.FinanzasControlador;
import EntidadesJPA.Usuario;
import java.io.IOException;
import java.util.logging.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author luis__000
 */
public class FinanzasAdministrador {

    private Scene scene;
    private Stage stage;
    public Parent root;
    public VBox pnlPrincipal;
    public Usuario Usuario;

    public FinanzasAdministrador(Scene scene, Stage stage, Usuario Usuario) {
        this.scene = scene;
        this.stage = stage;
        this.Usuario = Usuario;
    }

    public void abrirPanelFinanzas() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Principal/Finanzas.fxml"));
            root = (Parent) loader.load();
            FinanzasControlador controller = loader.<FinanzasControlador>getController();
            controller.initManager(this);
        } catch (IOException ex) {
            Logger.getLogger(FinanzasAdministrador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void showPnlDepositos() {
        Parent root = null;
        DepositosAdministrador DepositosAdministrador = new DepositosAdministrador(scene, stage, this, Usuario);
        DepositosAdministrador.abrirPanelDepositos();
        root = DepositosAdministrador.root;
        if (pnlPrincipal.getChildren().size() > 1) {
            pnlPrincipal.getChildren().remove(1);
        }
        pnlPrincipal.getChildren().add(root);
    }
    
    public void showPnlCheques() {
        Parent root = null;
        ChequesAdministrador ChequesAdministrador = new ChequesAdministrador(scene, stage, this, Usuario);
        ChequesAdministrador.abrirPanelCheques();
        root = ChequesAdministrador.root;
        if (pnlPrincipal.getChildren().size() > 1) {
            pnlPrincipal.getChildren().remove(1);
        }
        pnlPrincipal.getChildren().add(root);
    }
    
    public void showPnlBancos() {
        Parent root = null;
        BancosAdministrador BancosAdministrador = new BancosAdministrador(scene, stage, this);
        BancosAdministrador.abrirPanelBancos();
        root = BancosAdministrador.root;
        if (pnlPrincipal.getChildren().size() > 1) {
            pnlPrincipal.getChildren().remove(1);
        }
        pnlPrincipal.getChildren().add(root);
    }
    
    public void showPnlFacturas() {
        Parent root = null;
        FacturasAdministrador FacturasAdministrador = new FacturasAdministrador(scene, stage, this);
        FacturasAdministrador.abrirPanelFacturas();
        root = FacturasAdministrador.root;
        if (pnlPrincipal.getChildren().size() > 1) {
            pnlPrincipal.getChildren().remove(1);
        }
        pnlPrincipal.getChildren().add(root);
    }
}
