/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ControladoresGUI;

import AdministradoresGUI.BodegasAdministrador;
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
public class BodegasControlador
{
    BodegasAdministrador BodegaAdministrador;
    Dimension TamañoVentana = Toolkit.getDefaultToolkit().getScreenSize();
    @FXML 
    private VBox pnlPrincipal;
    
    public void initialize() {}
  
    public void initManager(final BodegasAdministrador BodegasAdministrador) 
    {
        this.BodegaAdministrador = BodegasAdministrador;
        this.BodegaAdministrador.pnlPrincipal = this.pnlPrincipal;
        this.pnlPrincipal.setPrefSize(TamañoVentana.getWidth()-180, TamañoVentana.getWidth());
        this.BodegaAdministrador.showPnlProducto();
    }
    
    public void abrirPnlProducto(ActionEvent event)
    {
        BodegaAdministrador.showPnlProducto();
    }
    
    public void abrirPnlProveedores(ActionEvent event)
    {
        BodegaAdministrador.showPnlProveedores();
    }
    
    public void abrirPnlNuevaBodega(ActionEvent event)
    {
        BodegaAdministrador.showPnlBodega();
    }
    
    public void abrirPnlDespacho(ActionEvent event)
    {
        BodegaAdministrador.showPnlDespacho();
    }
    
    public void abrirPnlPedidoProveedores(ActionEvent event)
    {
        BodegaAdministrador.showPnlPedidoProveedores();
    }
    
    public void abrirPnlDefectuoso(ActionEvent event)
    {
        BodegaAdministrador.showPnlDefectuoso();
    }
}
