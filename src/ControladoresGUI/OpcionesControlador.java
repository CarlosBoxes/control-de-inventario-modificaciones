/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ControladoresGUI;

import AdministradoresGUI.OpcionesAdministrador;
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
public class OpcionesControlador
{
    OpcionesAdministrador OpcionesAdministrador;
    Dimension TamañoVentana = Toolkit.getDefaultToolkit().getScreenSize();
    @FXML 
    private VBox pnlPrincipal;
    public Button btnUsuarios;
    
    public void initialize() {}
  
    public void initManager(final OpcionesAdministrador OpcionesAdministrador) 
    {
        this.OpcionesAdministrador = OpcionesAdministrador;
        this.OpcionesAdministrador.pnlPrincipal = this.pnlPrincipal;
        this.pnlPrincipal.setPrefSize(TamañoVentana.getWidth()-180, TamañoVentana.getWidth());
        this.btnUsuarios.setDisable(true);
        AbilitarBoton();
    }
    
    public void abrirPnlUsurios(ActionEvent event)
    {
        OpcionesAdministrador.showPnlUsuarios();
    }
    
    public void abrirPnlRecuperarContraseñas(ActionEvent event)
    {
        OpcionesAdministrador.showPnlRecuperarContraseñas();
    }
    
    private void AbilitarBoton()
    {
        List<AsignacionDePermisos> Permisos = (List<AsignacionDePermisos>) this.OpcionesAdministrador.Usuario.getAsignacionDePermisosCollection();
        for(int i = 0; i < Permisos.size(); i++)
        {
            if(Permisos.get(i).getPermisosidPermisos().getNombre().equals("Modificar Usuarios"))
            {
                btnUsuarios.setDisable(false);
            }
        }
    }
}
