/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ControladoresGUI;

import AdministradoresGUI.NuevoBancoAdministrador;
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
public class NuevoBancoControlador
{
    
    NuevoBancoAdministrador NuevoBancoAdminsitrador;
    @FXML
    public VBox pnlPrincipal;
    public TextField TFNombre;
    public TextField TFTelefono;
    private IBancos bancos;
    private Validaciones  validar ;
    
    
    public void initialize() {}
  
    public void initManager(final NuevoBancoAdministrador NuevoBancoAdministrador) {
        this.NuevoBancoAdminsitrador = NuevoBancoAdministrador;
        this.bancos = new IBancos();
        this.validar = new Validaciones ();
    }
    
    public void crearNuevoBanco (ActionEvent event)
    {
        if ("".equals(this.TFNombre.getText()))
        {
            this.NuevoBancoAdminsitrador.showMensajes("Debe Ingresar Un Nombre");
        }            
        else            
        if ((validar.ValidarNumerosTelefonicos(this.TFTelefono.getText()))&& (this.TFTelefono.getText().length()<=8))
        {
            String mensaje;
            mensaje = bancos.guardar(TFNombre.getText(), TFTelefono.getText());
            this.NuevoBancoAdminsitrador.showMensajes(mensaje);
            TFNombre.setText("");
            TFTelefono.setText("");
            TFNombre.requestFocus();
        }
        else
        {
            this.NuevoBancoAdminsitrador.showMensajes("Verifique El Numero Telefonico");
        }
            
    }
    public void Cancelar(ActionEvent event)
    {
        this.NuevoBancoAdminsitrador.cerrarNuevoBanco();
    }
}
