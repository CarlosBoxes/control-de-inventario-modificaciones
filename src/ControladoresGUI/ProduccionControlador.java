/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ControladoresGUI;

import AdministradoresGUI.ProduccionAdministrador;
import EntidadesJPA.AsignacionDePermisos;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.List;
import javafx.event.*;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author luis__000
 */
public class ProduccionControlador
{
    ProduccionAdministrador ProduccionAdministrador;
    Dimension TamañoVentana = Toolkit.getDefaultToolkit().getScreenSize();
    @FXML 
    private VBox pnlPrincipal;
    public Button BtnMateriaPrima;
    
    public void initialize() {}
  
    public void initManager(final ProduccionAdministrador ProduccionAdministrador) 
    {
       
        this.ProduccionAdministrador = ProduccionAdministrador;
        this.ProduccionAdministrador.pnlPrincipal = this.pnlPrincipal;
        this.pnlPrincipal.setPrefSize(TamañoVentana.getWidth()-180, TamañoVentana.getWidth());        
        this.ProduccionAdministrador.showPnlFabricacion();
        HabilitarBotones();
    }
    
    private void HabilitarBotones()
    {
        List<AsignacionDePermisos> Permisos = (List<AsignacionDePermisos>) this.ProduccionAdministrador.Usuario.getAsignacionDePermisosCollection();
        for(int i = 0; i < Permisos.size(); i++)
        {
            if(Permisos.get(i).getPermisosidPermisos().getNombre().equals("Gestión de Inventario"))
            {
                BtnMateriaPrima.setDisable(false);
            }
        }
    }
    
    public void abrirPnlFabricacion(ActionEvent event)
    {
        ProduccionAdministrador.showPnlFabricacion();
    }
//    
    public void abrirPnlPedidoMateriaPrima(ActionEvent event)
    {
        ProduccionAdministrador.showPnlPedidoMateriaPrima();
    }
//    
    public void abrirPnlMateriaPrima(ActionEvent event)
    {
        ProduccionAdministrador.showPnlMateriaPrima();
    }
//    
    public void abrirPnlNuevaBodegaMP(ActionEvent event)
    {
        ProduccionAdministrador.showPnlBodegaMP();
    }
    
    public void abrirPnlRecepcion(ActionEvent event)
    {
        ProduccionAdministrador.showPnlRecepcion();
    }
}
