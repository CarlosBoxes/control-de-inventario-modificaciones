/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ControladoresGUI;

import AdministradoresGUI.EditarClienteAdministrador;
import EntidadesJPA.Clientes;
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
public class EditarClienteControlador
{
    
    EditarClienteAdministrador EditarClienteAdminsitrador;
    @FXML
    public VBox pnlPrincipal;
    public TextField TFNombre;
    public TextField TFDireccion;
    public TextField TFNit;
    public TextField TFTelefonoCelular;
    public TextField TFRazonSocial;
    public TextField TFTelefonoCasa;
    public ComboBox ComBoTipoCliente;
    public IClientes cliente;
    public ITipoClientes tipoCliente;
    private Validaciones validar;

    
    
    public void initialize() {}
  
    public void initManager(final EditarClienteAdministrador EditarClienteAdministrador) {
        this.EditarClienteAdminsitrador = EditarClienteAdministrador;
        this.cliente = new IClientes();
        this.tipoCliente = new ITipoClientes();
        this.validar = new Validaciones();
        this.llenarTipo();
        TFNombre.setText(EditarClienteAdministrador.Cliente.getNombre());
        TFRazonSocial.setText(EditarClienteAdministrador.Cliente.getRazonsocial());
        TFDireccion.setText(EditarClienteAdministrador.Cliente.getDireccion());
        TFNit.setText(EditarClienteAdministrador.Cliente.getNit());
        TFTelefonoCelular.setText(EditarClienteAdministrador.Cliente.getTelefonoCelular());
        TFTelefonoCasa.setText(EditarClienteAdministrador.Cliente.getTelefonoCasa());
        ComBoTipoCliente.setValue(EditarClienteAdministrador.Cliente.getTipoClientesidTipoClientes().getNombre());
    }
    
    public void EditarCliente()
    {
        Clientes Editar = new Clientes();
        Clientes EditarRazonSocial = new Clientes();
        Clientes EditarNit = new Clientes();
        IClientes busqueda = new IClientes();
         if (this.verificarCamposVacios())
        {
            this.EditarClienteAdminsitrador.showMensajes("Rellene los campos: Nombre, Nit y Dirección");
        }
        else
        if ((!this.validar.ValidarNumerosTelefonicos(this.TFTelefonoCasa.getText()))||(!this.validar.ValidarNumerosTelefonicos(this.TFTelefonoCelular.getText())))
        {
            this.EditarClienteAdminsitrador.showMensajes("Verifique los Numeros Telefonicos");
        }        
        else
        if (this.ComBoTipoCliente.getValue()==null)
            
        {
            this.EditarClienteAdminsitrador.showMensajes("Selecciones un Tipo de Cliente");
        }
        
        else
        {
            Editar = busqueda.buscarClientesPorId(this.EditarClienteAdminsitrador.Cliente.getIdCliente()); 
            Editar.setNombre(TFNombre.getText());
            Editar.setRazonsocial(TFRazonSocial.getText());
            Editar.setDireccion(TFDireccion.getText());
            Editar.setNit(TFNit.getText());
            Editar.setTelefonoCasa(TFTelefonoCasa.getText());
            Editar.setTelefonoCelular(TFTelefonoCelular.getText());
            ITipoClientes tipo = new ITipoClientes();
            TipoClientes EditarTipo = tipo.buscarTipoClientePorNombre((String)ComBoTipoCliente.getValue());
            Editar.setTipoClientesidTipoClientes(EditarTipo);
            String mensaje = busqueda.modificar(Editar);
            this.EditarClienteAdminsitrador.showMensajes(mensaje);
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
        this.EditarClienteAdminsitrador.cerrarEditarCliente();
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
