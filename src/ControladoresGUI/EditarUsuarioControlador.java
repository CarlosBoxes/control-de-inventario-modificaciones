/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ControladoresGUI;

import AdministradoresGUI.EditarUsuarioAdministrador;
import EntidadesJPA.Usuario;
import GestorDeTablasJPA.IUsuarios;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author luis__000
 */
public class EditarUsuarioControlador
{
    
    EditarUsuarioAdministrador EditarUsuarioAdminsitrador;
    @FXML
    public VBox pnlPrincipal;
    public TextField TFNombre;
    public TextField TFContraseña;
    public TextField TFTipo;
    private Usuario Usuario;
    
    
    public void initialize() {}
  
    public void initManager(final EditarUsuarioAdministrador EditarUsuarioAdministrador) {
        this.EditarUsuarioAdminsitrador = EditarUsuarioAdministrador;
        this.TFNombre.setText(EditarUsuarioAdministrador.Usuario.getNombre());
        this.TFTipo.setText(EditarUsuarioAdministrador.Usuario.getPuesto());
        this.TFNombre.setDisable(true);
        this.TFTipo.setDisable(true);
        this.Usuario = EditarUsuarioAdministrador.Usuario;
    }
    
    public void Cancelar(ActionEvent event)
    {
        this.EditarUsuarioAdminsitrador.cerrarEditarUsuario();
    }
    
    public void ModificarContraseña(ActionEvent event)
    {
        IUsuarios Modificar = new IUsuarios();
        
        if("".equals(TFContraseña.getText()))
        {
            this.EditarUsuarioAdminsitrador.showMensajes("Ingrese la Nueva Contraeña");
        }
        
            
        else
        {
            Usuario = Modificar.buscarUsuarioPorNombre(TFNombre.getText());
            String Mensaje = Modificar.crearNuevaContraseña(Usuario,TFContraseña.getText());
            this.EditarUsuarioAdminsitrador.showMensajes(Mensaje);
        }
    }
}
