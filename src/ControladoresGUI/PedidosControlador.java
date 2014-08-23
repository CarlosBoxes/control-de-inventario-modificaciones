/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ControladoresGUI;

import AdministradoresGUI.PedidosAdministrador;
import java.awt.Dimension;
import java.awt.Toolkit;
import javafx.event.*;
import javafx.fxml.FXML;
import javafx.scene.layout.VBox;
/**
 *
 * @author luis__000
 */
public class PedidosControlador
{
    PedidosAdministrador PedidoAdministrador;
    Dimension TamañoVentana = Toolkit.getDefaultToolkit().getScreenSize();
    @FXML 
    private VBox pnlPrincipal;
    
    public void initialize() {}
  
    public void initManager(final PedidosAdministrador PedidoAdministrador) 
    {
        this.PedidoAdministrador = PedidoAdministrador;
        this.PedidoAdministrador.pnlPrincipal = this.pnlPrincipal;
        this.pnlPrincipal.setPrefSize(TamañoVentana.getWidth()-180, TamañoVentana.getWidth());
        this.PedidoAdministrador.showPnlPedidosNuevo();
    }
    
    @FXML
    public void abrirPnlPedidoNuevo(ActionEvent event)
    {
        PedidoAdministrador.showPnlPedidosNuevo();    
    }
    
    public void abrirPnlPedidoNuevoEspecial(ActionEvent event)
    {
        PedidoAdministrador.showPnlPedidosNuevoEspecial();
    }
    
    public void abrirPnlVendedodres(ActionEvent event)
    {
        PedidoAdministrador.showPnlVendedores();
    }
    
    public void abrirPnlTipoVendedor(ActionEvent event)
    {
        PedidoAdministrador.showPnlTipoVendedor();
    }
    
    public void abrirPnlClientes(ActionEvent event)
    {
        PedidoAdministrador.showPnlClientes();
    }
}