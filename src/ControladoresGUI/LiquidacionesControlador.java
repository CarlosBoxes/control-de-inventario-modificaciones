/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ControladoresGUI;

import AdministradoresGUI.LiquidacionesAdministrador;
import java.awt.Dimension;
import java.awt.Toolkit;
import javafx.event.*;
import javafx.fxml.FXML;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author luis__000
 */
public class LiquidacionesControlador
{
    LiquidacionesAdministrador LiquidacionesAdministrador;
    Dimension TamañoVentana = Toolkit.getDefaultToolkit().getScreenSize();
    @FXML 
    private VBox pnlPrincipal;
    
    public void initialize() {}
  
    public void initManager(final LiquidacionesAdministrador LiquidacionesAdministrador) 
    {
        this.LiquidacionesAdministrador = LiquidacionesAdministrador;
        this.LiquidacionesAdministrador.pnlPrincipal = this.pnlPrincipal;
        this.pnlPrincipal.setPrefSize(TamañoVentana.getWidth()-180, TamañoVentana.getHeight());
        this.LiquidacionesAdministrador.showPnlNuevaLiquidacion();
    }
    
    public void abrirPnlNuevaLiquidacion(ActionEvent event)
    {
        LiquidacionesAdministrador.showPnlNuevaLiquidacion();
    }
    
    public void abrirPnlNuevaLiquidacionEspecial(ActionEvent event)
    {
        LiquidacionesAdministrador.showPnlNuevaLiquidacionEspecial();
    }
    
    public void abrirPnlReportesLiquidacion(ActionEvent event)
    {
        LiquidacionesAdministrador.showPnlReportesLiquidacion();
    }
    
    public void abrirPnlReportesFinanzas(ActionEvent event)
    {
        LiquidacionesAdministrador.showPnlReportesFinanzas();
    }
    
    public void abrirPnlReportesVentas(ActionEvent event)
    {
        LiquidacionesAdministrador.showPnlReportes();
    }
}
