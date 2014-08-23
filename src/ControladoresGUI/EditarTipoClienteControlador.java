/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ControladoresGUI;

import AdministradoresGUI.EditarTipoClienteAdministrador;
import EntidadesJPA.TipoClientes;
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
public class EditarTipoClienteControlador
{
    
    EditarTipoClienteAdministrador EditarTipoClienteAdminsitrador;
    @FXML
    public VBox pnlPrincipal;
    public TextField TFNombre;    
    public ITipoClientes tipoCliente;
    public void initialize() {}
  
    public void initManager(final EditarTipoClienteAdministrador EditarTipoClienteAdministrador) {
        this.EditarTipoClienteAdminsitrador = EditarTipoClienteAdministrador;
        this.TFNombre.setText(EditarTipoClienteAdministrador.TipoCliente.getNombre());
        this.tipoCliente = new ITipoClientes ();
    }
    
    public void EditarTipoDeCliente(ActionEvent event)
    {
        
        TipoClientes TipoCliente = new TipoClientes();
        ITipoClientes Editar = new ITipoClientes();
        if (this.TFNombre.getText().isEmpty())
        {
            this.EditarTipoClienteAdminsitrador.showMensajes("Rellene todos los campos");
        }
        else
            if (this.VerificarExistente(this.TFNombre.getText()))
            {
                this.EditarTipoClienteAdminsitrador.showMensajes("Tipo de Cliente Ya Existe");
            }
        else
        {
            try
            {
            TipoCliente = Editar.buscarTipoClientePorNombre(this.EditarTipoClienteAdminsitrador.TipoCliente.getNombre());
            TipoCliente.setNombre(TFNombre.getText());
            String Mensaje = Editar.modificar(TipoCliente);
            this.EditarTipoClienteAdminsitrador.showMensajes(Mensaje);
            TFNombre.setText("");
            TFNombre.setFocusTraversable(true);
            }
            catch (Exception e)
                    {
                        this.EditarTipoClienteAdminsitrador.showMensajes("Error Al Editar Tipo de Cliente");
                    }
        }
    }
    
    public void Cancelar(ActionEvent event)
    {
        this.EditarTipoClienteAdminsitrador.cerrarEditarTipoCliente();
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
