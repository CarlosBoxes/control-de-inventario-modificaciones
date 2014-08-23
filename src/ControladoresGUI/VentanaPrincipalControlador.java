/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ControladoresGUI;

import javafx.event.*;
import javafx.fxml.FXML;
import AdministradoresGUI.VentanaPrincipalAdministrador;
import EntidadesJPA.AsignacionDePermisos;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.IOException;
import java.util.List;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
/**
 *
 * @author luis__000
 * comentario para commit
 */
public class VentanaPrincipalControlador
{
    VentanaPrincipalAdministrador ventanaAdministrador;
    private Dimension TamañoVentana = Toolkit.getDefaultToolkit().getScreenSize();
    @FXML
    public HBox pnlPrincipal;
    public VBox pnlImagen;
    public VBox pnlMenu;
    public Button btnPedidos;
    public Button btnBodegas;
    public Button btnProduccion;
    public Button btnLiquidaciones;
    public Button btnFinanzas;
    
    public void initialize() {}
  
    public void initManager(final VentanaPrincipalAdministrador ventanaManager) 
    {    
        this.ventanaAdministrador = ventanaManager;     
        this.pnlMenu.setPrefSize(200, this.TamañoVentana.getHeight());
        this.pnlImagen.setPrefSize(this.TamañoVentana.getWidth(), this.TamañoVentana.getHeight());
        btnPedidos.setDisable(true);
        btnBodegas.setDisable(true);
        btnProduccion.setDisable(true);
        btnLiquidaciones.setDisable(true);
        btnFinanzas.setDisable(true);
        AbilitarBotones();
    }
    
    @FXML
    public void abrirPedidos(ActionEvent event) throws IOException
    {      
        if(pnlPrincipal.getChildren().size()>1)
            pnlPrincipal.getChildren().remove(1);
        pnlPrincipal.getChildren().add(this.ventanaAdministrador.showPedidos());
    }
    
    public void abrirBodegas(ActionEvent event) throws IOException
    {
        if(pnlPrincipal.getChildren().size()>1)
            pnlPrincipal.getChildren().remove(1);
        pnlPrincipal.getChildren().add(this.ventanaAdministrador.showBodegas());
    }
    
    public void abrirLiquidaciones(ActionEvent event) throws IOException
    {
        if(pnlPrincipal.getChildren().size()>1)
            pnlPrincipal.getChildren().remove(1);
        pnlPrincipal.getChildren().add(this.ventanaAdministrador.showLiquidaciones());
    }
    
    public void abrirFinanzas(ActionEvent event) throws IOException
    {
        if(pnlPrincipal.getChildren().size()>1)
            pnlPrincipal.getChildren().remove(1);
        pnlPrincipal.getChildren().add(this.ventanaAdministrador.showFinanzas());
    }
    
    public void abrirOpciones(ActionEvent event) throws IOException
    {
        if(pnlPrincipal.getChildren().size()>1)
            pnlPrincipal.getChildren().remove(1);
        pnlPrincipal.getChildren().add(this.ventanaAdministrador.showOpciones());
    }
    
    public void abrirProduccion(ActionEvent event) throws IOException
    {
        if(pnlPrincipal.getChildren().size()>1)
            pnlPrincipal.getChildren().remove(1);
        pnlPrincipal.getChildren().add(this.ventanaAdministrador.showProduccion());
    }
    
    public void cerrarAplicacion(ActionEvent evetn)
    {
        System.exit(0);
    }
    
    private void AbilitarBotones()
    {
        List<AsignacionDePermisos> Permisos = (List<AsignacionDePermisos>) this.ventanaAdministrador.Usuario.getAsignacionDePermisosCollection();
        for(int i = 0; i < Permisos.size(); i++)
        {
            if(Permisos.get(i).getPermisosidPermisos().getNombre().equals("Acceso a Pedidos"))
            {
                btnPedidos.setDisable(false);
            }
            else if(Permisos.get(i).getPermisosidPermisos().getNombre().equals("Acceso a Producción"))
            {
                btnProduccion.setDisable(false);
            }
            else if(Permisos.get(i).getPermisosidPermisos().getNombre().equals("Acceso a Bodegas"))
            {
                btnBodegas.setDisable(false);
            }
            else if(Permisos.get(i).getPermisosidPermisos().getNombre().equals("Acceso a Liquidaciones"))
            {
                btnLiquidaciones.setDisable(false);
            }
            else if(Permisos.get(i).getPermisosidPermisos().getNombre().equals("Acceso a Finanzas"))
            {
                btnFinanzas.setDisable(false);
            }
        }
    }
}
