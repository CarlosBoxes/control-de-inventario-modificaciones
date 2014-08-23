/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ControladoresGUI;

import AdministradoresGUI.EditarProveedorAdministrador;
import EntidadesJPA.Proveedores;
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
public class EditarProveedorControlador
{
    
    EditarProveedorAdministrador EditarProveedoresAdminsitrador;
    @FXML
    public VBox pnlPrincipal;
    public TextField TFNombre;
    public TextField TFNit;
    public TextField TFDireccion;
    public TextField TFTelefono;
    public TextField TFCelular;
    private Validaciones validar;
    
    
    public void initialize() {}
  
    public void initManager(final EditarProveedorAdministrador EditarProveedoresAdministrador) {
        this.EditarProveedoresAdminsitrador = EditarProveedoresAdministrador;
        this.TFNombre.setText(EditarProveedoresAdministrador.Proveedores.getNombre());
        this.TFNit.setText(EditarProveedoresAdministrador.Proveedores.getNit());
        this.TFDireccion.setText(EditarProveedoresAdministrador.Proveedores.getDireccion());
        this.TFTelefono.setText(EditarProveedoresAdministrador.Proveedores.getTelefono1());
        this.TFCelular.setText(EditarProveedoresAdministrador.Proveedores.getTelefono2());
        this.validar = new Validaciones();
    }
    
    public void EditarProveedor(ActionEvent Event)
    {
        if (this.verificarCamposVacios())
        {
            this.EditarProveedoresAdminsitrador.showMensajes("Se Necesita los Campos de Nombre,Nit y Dirección");
        }
        else
        {
            IProveedores buscar = new IProveedores();
            Proveedores Proveedor = buscar.buscarProveedorPorNombre(EditarProveedoresAdminsitrador.Proveedores.getNombre());
            Proveedor.setNombre(TFNombre.getText());
            Proveedor.setNit(TFNit.getText());
            Proveedor.setDireccion(TFDireccion.getText());
            Proveedor.setTelefono1(TFTelefono.getText());
            Proveedor.setTelefono2(TFCelular.getText());
            String Mensaje = buscar.modificar(Proveedor);
            this.EditarProveedoresAdminsitrador.showMensajes(Mensaje);
            this.TFNombre.setText("");
            this.TFNit.setText("");
            this.TFDireccion.setText("");
            this.TFTelefono.setText("");
            this.TFCelular.setText("");
        }
    }
    
    public void Cancelar(ActionEvent event)
    {
        this.EditarProveedoresAdminsitrador.cerrarEditarProveedor();
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
