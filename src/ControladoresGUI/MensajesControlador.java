/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ControladoresGUI;

import AdministradoresGUI.MensajesAdministrador;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.scene.control.Label;

import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author luis__000
 */
public class MensajesControlador
{
    
    MensajesAdministrador MensajesAdminsitrador;
    @FXML
    public VBox pnlPrincipal;
    public Label Mensaje;
      
    public void initialize() {}
  
    public void initManager(final MensajesAdministrador MensajesAdministrador) {
        this.MensajesAdminsitrador = MensajesAdministrador;
        this.Mensaje.setText(MensajesAdministrador.Mensaje);
    }
    
    public void Cancelar(ActionEvent event)
    {
        this.MensajesAdminsitrador.cerrarMensajes();
    }
}
