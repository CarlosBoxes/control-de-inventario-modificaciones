/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ControladoresGUI;

import AdministradoresGUI.FinanzasAdministrador;
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
public class FinanzasControlador
{
    FinanzasAdministrador FinanzasAdministrador;
    Dimension TamañoVentana = Toolkit.getDefaultToolkit().getScreenSize();
    @FXML 
    private VBox pnlPrincipal;
    
    public void initialize() {}
  
    public void initManager(final FinanzasAdministrador FinanzasAdministrador) 
    {
        this.FinanzasAdministrador = FinanzasAdministrador;
        this.FinanzasAdministrador.pnlPrincipal = this.pnlPrincipal;
        this.pnlPrincipal.setPrefSize(TamañoVentana.getWidth()-180, TamañoVentana.getWidth());
        //this.PedidoAdministrador.showPnlProductos();
    }
    
    public void abrirPnlDepositos(ActionEvent event)
    {
        FinanzasAdministrador.showPnlDepositos();
    }
    
    public void abrirPnlCheques(ActionEvent event)
    {
        FinanzasAdministrador.showPnlCheques();
    }
    
    public void abrirPnlBancos(ActionEvent event)
    {
        FinanzasAdministrador.showPnlBancos();
    }
    
    public void abrirPnlFacturas(ActionEvent event)
    {
        FinanzasAdministrador.showPnlFacturas();
    }
}
