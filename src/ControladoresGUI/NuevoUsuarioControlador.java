/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ControladoresGUI;

import AdministradoresGUI.NuevoUsuarioAdministrador;
import ControladoresJPA.exceptions.PreexistingEntityException;
import Especiales.Validaciones;
import GestorDeTablasJPA.IUsuarios;
import java.util.ArrayList;
import java.util.List;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author luis__000
 */
public class NuevoUsuarioControlador
{
    
    NuevoUsuarioAdministrador NuevoUsuarioAdminsitrador;
    @FXML
    public VBox pnlPrincipal;
    public CheckBox CBAccesoPedidos;
    public CheckBox CBAccesoBodegas;
    public CheckBox CBAccesoProduccion;
    public CheckBox CBAccesoLiquidaciones;
    public CheckBox CBAccesoFinanzas;
    public CheckBox CBAccesoModificarUsuarios;
    public TextField TFNombre;
    public TextField TFContraseña;
    public ComboBox ComBoTipo;
    private IUsuarios Usuario;
    private Validaciones validar ;
    
    
    public void initialize() {}
  
    public void initManager(final NuevoUsuarioAdministrador NuevoUsuarioAdministrador) {
        this.NuevoUsuarioAdminsitrador = NuevoUsuarioAdministrador;
        this.Usuario = new IUsuarios();
        this.validar = new Validaciones();
    }
    
    public void Cancelar(ActionEvent event)
    {
        this.NuevoUsuarioAdminsitrador.cerrarNuevoUsuario();
    }
    
    public void SeleccionAutomatica(ActionEvent event)
    {
        String Seleccionado = (String)ComBoTipo.getValue();
        switch(Seleccionado)
        {
            case "Administrador":
                CBAccesoPedidos.setSelected(true);
                CBAccesoBodegas.setSelected(true);
                CBAccesoProduccion.setSelected(true);
                CBAccesoLiquidaciones.setSelected(true);
                CBAccesoFinanzas.setSelected(true);
                CBAccesoModificarUsuarios.setSelected(true);
                break;
            case "Bodeguero":
                CBAccesoPedidos.setSelected(true);
                CBAccesoBodegas.setSelected(true);
                CBAccesoProduccion.setSelected(true);
                CBAccesoLiquidaciones.setSelected(false);
                CBAccesoFinanzas.setSelected(false);
                CBAccesoModificarUsuarios.setSelected(false);
                break;
            case "Contador":
                CBAccesoPedidos.setSelected(false);
                CBAccesoBodegas.setSelected(false);
                CBAccesoProduccion.setSelected(false);
                CBAccesoLiquidaciones.setSelected(true);
                CBAccesoFinanzas.setSelected(true);
                CBAccesoModificarUsuarios.setSelected(false);
                break;
            case "Vendedores":
                CBAccesoPedidos.setSelected(true);
                CBAccesoBodegas.setSelected(false);
                CBAccesoProduccion.setSelected(false);
                CBAccesoLiquidaciones.setSelected(false);
                CBAccesoFinanzas.setSelected(false);
                CBAccesoModificarUsuarios.setSelected(false);
                break;
            case "Secretaria":
                CBAccesoPedidos.setSelected(true);
                CBAccesoBodegas.setSelected(true);
                CBAccesoProduccion.setSelected(true);
                CBAccesoLiquidaciones.setSelected(true);
                CBAccesoFinanzas.setSelected(true);
                CBAccesoModificarUsuarios.setSelected(false);
                break;
            case "Empleado":
                CBAccesoPedidos.setSelected(false);
                CBAccesoBodegas.setSelected(false);
                CBAccesoProduccion.setSelected(false);
                CBAccesoLiquidaciones.setSelected(false);
                CBAccesoFinanzas.setSelected(false);
                CBAccesoModificarUsuarios.setSelected(false);
                break;
        }
    }
    
    public void CrearNuevoUsuario(ActionEvent event) throws PreexistingEntityException, Exception
    {
        List<String> ListaPermisos = new ArrayList<String>();
        if(CBAccesoPedidos.isSelected())
        {
            ListaPermisos.add(CBAccesoPedidos.getText());
        }
        if(CBAccesoBodegas.isSelected())
        {
            ListaPermisos.add(CBAccesoBodegas.getText());
        }
        if(CBAccesoProduccion.isSelected())
        {
            ListaPermisos.add(CBAccesoProduccion.getText());
        }
        if(CBAccesoLiquidaciones.isSelected())
        {
            ListaPermisos.add(CBAccesoLiquidaciones.getText());
        }
        if(CBAccesoFinanzas.isSelected())
        {
            ListaPermisos.add(CBAccesoFinanzas.getText());
        }
        if(CBAccesoModificarUsuarios.isSelected())
        {
            ListaPermisos.add(CBAccesoModificarUsuarios.getText());
        }
        if(Usuario.buscarNombreDeUsuarioExistente(TFNombre.getText()))
        {
            String Mensaje = Usuario.guardar(TFNombre.getText(), TFContraseña.getText(), (String)ComBoTipo.getValue(), ListaPermisos);
            this.NuevoUsuarioAdminsitrador.showMensajes(Mensaje);
            TFNombre.setText("");
            TFContraseña.setText("");
            CBAccesoPedidos.setSelected(false);
            CBAccesoBodegas.setSelected(false);
            CBAccesoProduccion.setSelected(false);
            CBAccesoLiquidaciones.setSelected(false);
            CBAccesoFinanzas.setSelected(false);
            CBAccesoModificarUsuarios.setSelected(false);
        }
        else
        {
            this.NuevoUsuarioAdminsitrador.showMensajes("Nombre de Usuario no Valido");
            TFNombre.setText("");
        }
        
    }
}
