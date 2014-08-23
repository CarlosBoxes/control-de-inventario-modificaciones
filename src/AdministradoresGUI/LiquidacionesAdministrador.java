/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package AdministradoresGUI;
import ControladoresGUI.LiquidacionesControlador;
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
public class LiquidacionesAdministrador 
{
    private Scene scene;
    private Stage stage;
    public Parent root;
    public VBox pnlPrincipal;
    
    public LiquidacionesAdministrador(Scene scene, Stage stage) 
    {
        this.scene = scene;
        this.stage = stage;
    }

    public void abrirPanelLiquidaciones()
    {
        try 
        {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Principal/Liquidaciones.fxml"));
            root = (Parent) loader.load();
            LiquidacionesControlador controller = loader.<LiquidacionesControlador>getController();
            controller.initManager(this);
        } 
        catch (IOException ex) 
        {
            Logger.getLogger(LiquidacionesAdministrador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void showPnlNuevaLiquidacion()
    {      
         Parent root = null;
         NuevaLiquidacionAdministrador NuevaLiquidacionAdministrador = new NuevaLiquidacionAdministrador(scene,stage, this);
         NuevaLiquidacionAdministrador.abrirPanelNuevaLiquidacion();
         root = NuevaLiquidacionAdministrador.root;
         if(pnlPrincipal.getChildren().size()>1)
            pnlPrincipal.getChildren().remove(1);
        pnlPrincipal.getChildren().add(root);
    }
    
    public void showPnlReportesLiquidacion()
    {      
         Parent root = null;
         ReportesLiquidacionAdministrador ReportesLiquidacionAdministrador = new ReportesLiquidacionAdministrador(scene,stage, this);
         ReportesLiquidacionAdministrador.abrirPanelReportesLiquidacion();
         root = ReportesLiquidacionAdministrador.root;
         if(pnlPrincipal.getChildren().size()>1)
            pnlPrincipal.getChildren().remove(1);
        pnlPrincipal.getChildren().add(root);
    }
    
    public void showPnlNuevaLiquidacionEspecial()
    {      
         Parent root = null;
         NuevaLiquidacionEspecialAdministrador NuevaLiquidacionAdministrador = new NuevaLiquidacionEspecialAdministrador(scene,stage, this);
         NuevaLiquidacionAdministrador.abrirPanelNuevaLiquidacionEspecial();
         root = NuevaLiquidacionAdministrador.root;
         if(pnlPrincipal.getChildren().size()>1)
            pnlPrincipal.getChildren().remove(1);
         pnlPrincipal.getChildren().add(root);
    }
    
    public void showPnlReportesFinanzas()
    {      
         Parent root = null;
         ReportesFinanzasAdministrador ReportesFinanzasAdministrador = new ReportesFinanzasAdministrador(scene,stage, this);
         ReportesFinanzasAdministrador.abrirPanelReportesFinanzas();
         root = ReportesFinanzasAdministrador.root;
         if(pnlPrincipal.getChildren().size()>1)
            pnlPrincipal.getChildren().remove(1);
        pnlPrincipal.getChildren().add(root);
    }
    
    public void showPnlReportes()
    {      
         Parent root = null;
         ReportesVentasAdministrador ReportesFinanzasAdministrador = new ReportesVentasAdministrador(scene,stage, this);
         ReportesFinanzasAdministrador.abrirPanelReportes();
         root = ReportesFinanzasAdministrador.root;
         if(pnlPrincipal.getChildren().size()>1)
            pnlPrincipal.getChildren().remove(1);
        pnlPrincipal.getChildren().add(root);
    }
}
