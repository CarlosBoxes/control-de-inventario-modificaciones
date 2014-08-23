/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ControladoresGUI;

import AdministradoresGUI.EditarVendedorAdministrador;
import EntidadesJPA.TipoVendedores;
import EntidadesJPA.Vendedores;
import Especiales.Validaciones;
import GestorDeTablasJPA.ITipoVendedor;
import GestorDeTablasJPA.IVendedores;
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
public class EditarVendedorControlador
{
    
    EditarVendedorAdministrador EditarVendedorAdminsitrador;
    @FXML
    public VBox pnlPrincipal;
    public TextField TFNombre;
    public TextField TFApellido;
    public TextField TFTelefonoCelular;
    public TextField TFTelefonoCasa;
    public TextField TFDPI;
    public ComboBox ComBoTipoVendedor;
    private Validaciones validar;
    
    public void initialize() {}
  
    public void initManager(final EditarVendedorAdministrador EditarVendedorAdministrador) {
        this.EditarVendedorAdminsitrador = EditarVendedorAdministrador;
        this.TFNombre.setText(EditarVendedorAdministrador.Vendedores.getNombre());
        this.TFApellido.setText(EditarVendedorAdministrador.Vendedores.getApellido());
        this.TFTelefonoCasa.setText(EditarVendedorAdministrador.Vendedores.getTelefonoCasa());
        this.TFTelefonoCelular.setText(EditarVendedorAdministrador.Vendedores.getTelefonoCelular());
        this.TFDPI.setText(EditarVendedorAdministrador.Vendedores.getDpi());
        this.ComBoTipoVendedor.setValue(EditarVendedorAdministrador.Vendedores.getTipoVendedoresidTipoVendedores().getNombre());
        this.validar= new Validaciones();
        llenarTipo();
    }
    
    public void EditarTipoDeVendedor(ActionEvent event)
    {
        if (this.verificarVendedorYaIngresado())
        {
            this.EditarVendedorAdminsitrador.showMensajes("Este Vendedor Ya Existe");
        }
        else
            if (this.verificarCamposVacios())
        {
            this.EditarVendedorAdminsitrador.showMensajes("Verifique Los Campos de Nombre y Apellido");
        }
            else
                if ((!this.validar.ValidarNumerosTelefonicos(this.TFTelefonoCasa.getText()))||(!this.validar.ValidarNumerosTelefonicos(this.TFTelefonoCelular.getText())))
            {
                this.EditarVendedorAdminsitrador.showMensajes("Verifique Los Numeros telefonicos");
                
            }
                else
                    if(this.ComBoTipoVendedor.getValue()==null)
                    {
                        this.EditarVendedorAdminsitrador.showMensajes("Debe Seleccionar un Tipo de vendedor");
                    }
        else
        {
            try
            {
        Vendedores Vendedores = new Vendedores();
        IVendedores Editar = new IVendedores();
        Vendedores = Editar.buscarVendedorPorId(this.EditarVendedorAdminsitrador.Vendedores.getIdvendedores());
        Vendedores.setNombre(TFNombre.getText());
        Vendedores.setApellido(TFApellido.getText());
        Vendedores.setTelefonoCasa(TFTelefonoCasa.getText());
        Vendedores.setTelefonoCelular(TFTelefonoCelular.getText());
        Vendedores.setDpi(TFDPI.getText());
        TipoVendedores Tipo = new TipoVendedores();
        ITipoVendedor buscar = new ITipoVendedor();
        Tipo = buscar.buscarTipoVendedorPorNombre((String)ComBoTipoVendedor.getValue());
        Vendedores.setTipoVendedoresidTipoVendedores(Tipo);
        String Mensaje = Editar.modificar(Vendedores);
        this.EditarVendedorAdminsitrador.showMensajes(Mensaje);
        TFNombre.setText("");
        TFNombre.setFocusTraversable(true);
            }
            catch (Exception e)
                    {
                        this.EditarVendedorAdminsitrador.showMensajes("Error Al Editar al Vendedor");
                    }
        }
    }
    
    public void Cancelar(ActionEvent event)
    {
        this.EditarVendedorAdminsitrador.cerrarEditarVendedor();
    }
    
    public void llenarTipo ()
    {
         ITipoVendedor buscar = new ITipoVendedor();
         List<String> list = new ArrayList<String>();
         for (TipoVendedores tipo:buscar.listaDeTipoDeVendedores())
         {
             list.add(tipo.getNombre());
         }
         ObservableList<String> observableList = FXCollections.observableList(list);
         ComBoTipoVendedor.setItems(observableList);
    }
        private boolean verificarVendedorYaIngresado()
    {
        IVendedores vend = new IVendedores ();
        for (Vendedores vendedorBuscar:vend.listaDeVendedores())
        if(this.TFNombre.equals(vendedorBuscar.getNombre())&& this.TFApellido.equals(vendedorBuscar.getApellido()) )
        {
             return true;
        }
        return false;
    }
    
    private boolean verificarCamposVacios ()
    {
        if ((this.TFNombre.getText().isEmpty())||(this.TFApellido.getText().isEmpty()))
        {
            return true;
        }
        else
            return false;
    }
}
