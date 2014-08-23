/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ControladoresGUI;

import AdministradoresGUI.EditarBancoAdministrador;
import EntidadesJPA.Bancos;
import Especiales.Validaciones;
import GestorDeTablasJPA.IBancos;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author luis__000
 */
public class EditarBancoControlador
{
    
    EditarBancoAdministrador EditarBancoAdminsitrador;
    @FXML
    public VBox pnlPrincipal;
    public TextField TFNombre;
    public TextField TFTelefono;
    private Validaciones validar ;
    
    public void initialize() {}
  
    public void initManager(final EditarBancoAdministrador EditarBancoAdministrador) {
        this.EditarBancoAdminsitrador = EditarBancoAdministrador;
        this.TFNombre.setText(EditarBancoAdministrador.Banco.getNombre());
        this.TFTelefono.setText(EditarBancoAdministrador.Banco.getTelefono());
        this.validar = new Validaciones ();
    }
    
    public void EditarBanco(ActionEvent event)
    {
        if ("".equals(this.TFNombre.getText()))
        {
            this.EditarBancoAdminsitrador.showMensajes("Debe Ingresar El Nombre del Banco");
        }
        else
        if ((this.validar.ValidarNumerosTelefonicos(this.TFTelefono.getText()))&&(this.TFTelefono.getText().length()<=8))
        {
            IBancos busqueda = new IBancos();
            Bancos Editar = busqueda.buscarBancoPorNombre(this.EditarBancoAdminsitrador.Banco.getNombre());
            Editar.setNombre(TFNombre.getText());
            Editar.setTelefono(TFTelefono.getText());
            String Mensaje = busqueda.modificar(Editar);
            this.EditarBancoAdminsitrador.showMensajes(Mensaje);
            this.TFNombre.setText("");
            this.TFTelefono.setText("");
        }
        
    }
    
    public void Cancelar(ActionEvent event)
    {
        this.EditarBancoAdminsitrador.cerrarEditarBanco();
    }
}
