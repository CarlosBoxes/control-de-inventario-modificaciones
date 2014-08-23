/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ControladoresGUI;

import AdministradoresGUI.NuevoTipoClienteAdministrador;
import GestorDeTablasJPA.ITipoClientes;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author luis__000
 */
public class NuevoTipoClienteControlador
{
    
    NuevoTipoClienteAdministrador NuevoTipoClienteAdminsitrador;
    @FXML
    public VBox pnlPrincipal;
    public TextField TFNombre;
    private ITipoClientes tipoCliente;
    
    
    public void initialize() {}
  
    public void initManager(final NuevoTipoClienteAdministrador NuevoTipoClienteAdministrador) {
        this.NuevoTipoClienteAdminsitrador = NuevoTipoClienteAdministrador;
        this.tipoCliente = new ITipoClientes();
    }
    
    public void crearNuevoTipoDeCliente (ActionEvent event)
    {
        String mensaje;
        if (this.TFNombre.getText().isEmpty())
        {
            this.NuevoTipoClienteAdminsitrador.showMensajes("Rellene todos los campos");
        }
        else
            if (this.VerificarExistente(this.TFNombre.getText()))
            {
                this.NuevoTipoClienteAdminsitrador.showMensajes("Tipo de Cliente Ya Existe");
            }
        else
        {
            try
            {
            mensaje = tipoCliente.guardar(TFNombre.getText());
            this.NuevoTipoClienteAdminsitrador.showMensajes(mensaje);//aca da el error
            TFNombre.setText("");
            TFNombre.setFocusTraversable(true);
            }
            catch(Exception e)
            {
                this.NuevoTipoClienteAdminsitrador.showMensajes("Error Al Crear Nuevo Tipo de Cliente");
            }
        }
    }
    public void Cancelar(ActionEvent event)
    {
        this.NuevoTipoClienteAdminsitrador.cerrarNuevoTipoCliente();
    }
    
    public boolean  VerificarExistente (String nombre)
    {
        if (this.tipoCliente.buscarTipoClientePorNombre(nombre)!=null)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
  
}
