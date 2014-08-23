/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ControladoresGUI;

import AdministradoresGUI.NuevoProveedorAdministrador;
import Especiales.Validaciones;
import GestorDeTablasJPA.IProveedores;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author luis__000
 */
public class NuevoProveedorControlador
{
    
    NuevoProveedorAdministrador NuevoProveedorAdminsitrador;
    @FXML
    public VBox pnlPrincipal;
    public TextField TFNombre;
    public TextField TFNit;
    public TextField TFDireccion;
    public TextField TFTelefono;
    public TextField TFCelular;
    private Validaciones validar;
    
    
    public void initialize() {}
  
    public void initManager(final NuevoProveedorAdministrador NuevoProveedorAdministrador) {
        this.NuevoProveedorAdminsitrador = NuevoProveedorAdministrador;
        this.validar = new Validaciones();
    }
    
    public void Cancelar(ActionEvent event)
    {
        this.NuevoProveedorAdminsitrador.cerrarNuevoProveedor();
    }
    
    public void CrearNuevoProveedor(ActionEvent event)
    {
        if (this.verificarCamposVacios())
        {
            this.NuevoProveedorAdminsitrador.showMensajes("Se Necesita los Campos de Nombre,Nit y Dirección");
        }
        else
        {
            IProveedores Nuevo = new IProveedores();
            String Mensaje = Nuevo.guardar(TFNombre.getText(), TFNit.getText(), TFDireccion.getText(), TFTelefono.getText(), TFCelular.getText());
            this.NuevoProveedorAdminsitrador.showMensajes(Mensaje);
            TFNombre.setText("");
            TFNit.setText("");
            TFDireccion.setText("");
            TFTelefono.setText("");
            TFCelular.setText("");
        }
        
    }
     public boolean verificarCamposVacios ()
    {
        if ((this.TFNombre.getText().isEmpty())||(this.TFDireccion.getText().isEmpty())||(this.TFNit.getText().isEmpty()))
        {
            return true;
        }
        else
        {
            return false;
        }
    }
}
