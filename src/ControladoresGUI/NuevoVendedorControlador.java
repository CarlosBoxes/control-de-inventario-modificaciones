/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ControladoresGUI;
import AdministradoresGUI.NuevoVendedorAdministrador;
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
public class NuevoVendedorControlador
{
    
    NuevoVendedorAdministrador NuevoVendedorAdminsitrador;
    @FXML
    public VBox pnlPrincipal;
    public TextField TFNombre;
    public TextField TFApellido;
    public TextField TFTelefonoCelular;
    public TextField TFTelefonoCasa;
    public TextField TFDPI;
    public ComboBox ComBoTipoVendedor;
    private IVendedores vendedor;
    private Validaciones validar ;
    ITipoVendedor tipoVendedor;
    
    
    public void initialize() {}
  
    public void initManager(final NuevoVendedorAdministrador NuevoVendedorAdministrador) {
        this.NuevoVendedorAdminsitrador = NuevoVendedorAdministrador;
        this.vendedor = new IVendedores();
        this.tipoVendedor = new ITipoVendedor ();
        this.validar = new Validaciones ();
        llenarTipo();
    }
    
    public void Cancelar(ActionEvent event)
    {
        this.NuevoVendedorAdminsitrador.cerrarNuevoVendedor();
    }
    
    public void crearNuevoVendedor ()
    {
        if (this.verificarVendedorYaIngresado())
        {
            this.NuevoVendedorAdminsitrador.showMensajes("Este Vendedor Ya Existe");
        }
        else
            if (this.verificarCamposVacios())
        {
            this.NuevoVendedorAdminsitrador.showMensajes("Verifique Los Campos de Nombre y Apellido");
        }
            else
                if ((!this.validar.ValidarNumerosTelefonicos(this.TFTelefonoCasa.getText()))||(!this.validar.ValidarNumerosTelefonicos(this.TFTelefonoCelular.getText())))
            {
                this.NuevoVendedorAdminsitrador.showMensajes("Verifique Los Numeros telefonicos");
                
            }
                else
                    if(this.ComBoTipoVendedor.getValue()==null)
                    {
                        this.NuevoVendedorAdminsitrador.showMensajes("Debe Seleccionar un Tipo de vendedor");
                    }
        else
        {
            try
            {
            String mensaje;
            mensaje = vendedor.guardar(TFNombre.getText(),TFApellido.getText(), TFTelefonoCelular.getText(), TFTelefonoCasa.getText(),tipoVendedor.buscarTipoVendedorPorNombre((String)ComBoTipoVendedor.getValue()), TFDPI.getText());
            this.NuevoVendedorAdminsitrador.showMensajes(mensaje);
            TFNombre.setText("");
            TFApellido.setText("");
            TFTelefonoCelular.setText("");
            TFTelefonoCasa.setText("");
            TFNombre.setFocusTraversable(true);
            }
            catch (Exception e)
            {
                this.NuevoVendedorAdminsitrador.showMensajes("Error al Ingresar Vendedor");
            }
        }
    }
  
    public void llenarTipo ()
    {         
         List<String> list = new ArrayList<String>();
         for (TipoVendedores tipo:tipoVendedor.listaDeTipoDeVendedores())
         {
             list.add(tipo.getNombre());
         }
         ObservableList<String> observableList = FXCollections.observableList(list);
         ComBoTipoVendedor.setItems(observableList);
    }  
    private boolean verificarVendedorYaIngresado()
    {
        IVendedores vend = new IVendedores ();
        List<Vendedores> Lista = vend.listaDeVendedores();
        if(Lista != null)
        {
            for (Vendedores vendedorBuscar:Lista)
            if(this.TFNombre.equals(vendedorBuscar.getNombre())&& this.TFApellido.equals(vendedorBuscar.getApellido()) )
            {
                 return true;
            }
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
