/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ControladoresGUI;

import AdministradoresGUI.NuevoClienteAdministrador;
import EntidadesJPA.TipoClientes;
import Especiales.Validaciones;
import GestorDeTablasJPA.IClientes;
import GestorDeTablasJPA.ITipoClientes;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author luis__000
 */
public class NuevoClienteControlador
{
    
    NuevoClienteAdministrador NuevoClienteAdminsitrador;
    @FXML
    public VBox pnlPrincipal;
    public TextField TFNombre;
    public TextField TFRazonSocial;
    public TextField TFDireccion;
    public TextField TFNit;
    public TextField TFTelefonoCelular;
    public TextField TFTelefonoCasa;
    public ComboBox ComBoTipoCliente;
    public IClientes cliente;
    public ITipoClientes tipoCliente;
    private Validaciones validar;

    
    
    public void initialize() {}
  
    public void initManager(final NuevoClienteAdministrador NuevoClienteAdministrador) {
        this.NuevoClienteAdminsitrador = NuevoClienteAdministrador;
        this.cliente = new IClientes();
        this.tipoCliente = new ITipoClientes();
        this.validar = new Validaciones();
        this.llenarTipo();
    }
    
    public void crearNuevoCliente()
    {
        String mensaje;
        if (this.verificarCamposVacios())
        {
            this.NuevoClienteAdminsitrador.showMensajes("Rellene los campos: Nombre, Nit y Dirección");
        }
        else
        if ((!this.validar.ValidarNumerosTelefonicos(this.TFTelefonoCasa.getText()))||(!this.validar.ValidarNumerosTelefonicos(this.TFTelefonoCelular.getText())))
        {
            this.NuevoClienteAdminsitrador.showMensajes("Verifique los Numeros Telefonicos");
        }
        else
        if (this.ComBoTipoCliente.getValue()==null)
            
        {
            this.NuevoClienteAdminsitrador.showMensajes("Selecciones un Tipo de Cliente");
        }
        else
        {
            mensaje = cliente.guardar(TFNombre.getText(),TFRazonSocial.getText(),TFDireccion.getText(),TFNit.getText(),TFTelefonoCelular.getText(),TFTelefonoCasa.getText(),tipoCliente.buscarTipoClientePorNombre((String)ComBoTipoCliente.getValue()));
            this.NuevoClienteAdminsitrador.showMensajes(mensaje);
            TFNombre.setText("");
            TFRazonSocial.setText("");
            TFDireccion.setText("");
            TFNit.setText("");
            TFTelefonoCelular.setText("");
            TFTelefonoCasa.setText("");
            TFNombre.setFocusTraversable(true);
        }
    }
    
    public void Cancelar(ActionEvent event)
    {
        this.NuevoClienteAdminsitrador.cerrarNuevoCliente();
    }
    public void llenarTipo ()
    {         
         List<String> list = new ArrayList<String>();
         for (TipoClientes tipo:tipoCliente.listaDeTipoDeClientes())
         {
             list.add(tipo.getNombre());
         }
         ObservableList<String> observableList = FXCollections.observableList(list);
         ComBoTipoCliente.setItems(observableList);
    }
    
    public boolean verificarCamposVacios()
    {
        if((this.TFNombre.getText().isEmpty())||(this.TFNit.getText().isEmpty())||(this.TFDireccion.getText().isEmpty()))
        {
            return true;
        }
        else
        {
            return false;
        }
    }
}
