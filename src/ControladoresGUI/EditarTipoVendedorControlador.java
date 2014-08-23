/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ControladoresGUI;

import AdministradoresGUI.EditarTipoVendedorAdministrador;
import EntidadesJPA.TipoVendedores;
import GestorDeTablasJPA.ITipoVendedor;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author luis__000
 */
public class EditarTipoVendedorControlador
{
    
    EditarTipoVendedorAdministrador EditarTipoVendedorAdminsitrador;
    @FXML
    public CheckBox Lista;
    public TextField TFNombre;
    private ITipoVendedor tipoVendedor;
    
    public void initialize() {}
  
    public void initManager(final EditarTipoVendedorAdministrador EditarTipoVendedorAdministrador) {
        this.EditarTipoVendedorAdminsitrador = EditarTipoVendedorAdministrador;
        this.TFNombre.setText(EditarTipoVendedorAdministrador.TipoVendedores.getNombre());
        if(this.EditarTipoVendedorAdminsitrador.TipoVendedores.getListaproductos())
        {
            this.Lista.setSelected(true);
        }
        else
        {
            this.Lista.setSelected(false);
        }
        this.tipoVendedor = new ITipoVendedor ();
    }
    
    public void EditarTipoDeVendedor(ActionEvent event)
    {
        TipoVendedores TipoVendedores = new TipoVendedores();
        ITipoVendedor Editar = new ITipoVendedor();
        if (this.TFNombre.getText().isEmpty())
        {
            this.EditarTipoVendedorAdminsitrador.showMensajes("Rellene Todos Los Campos");
        }
        else
        {
                try
                {
                    TipoVendedores = Editar.buscarTipoVendedorPorNombre(this.EditarTipoVendedorAdminsitrador.TipoVendedores.getNombre());
                    TipoVendedores.setNombre(TFNombre.getText());
                    if(Lista.isSelected())
                    {
                        TipoVendedores.setListaproductos(true);
                    }
                    else
                    {
                        TipoVendedores.setListaproductos(false);
                    }
                    String Mensaje = Editar.modificar(TipoVendedores);
                    this.EditarTipoVendedorAdminsitrador.showMensajes(Mensaje);
                    TFNombre.setText("");
                    TFNombre.setFocusTraversable(true);
                }
                catch (Exception e)
                {
                    this.EditarTipoVendedorAdminsitrador.showMensajes("Error Al Editar Tipo de Vendedor");
                }
        }
    }
    
    public void Cancelar(ActionEvent event)
    {
        this.EditarTipoVendedorAdminsitrador.cerrarEditarTipoVendedor();
    }
    
     public boolean VerificarExistente(String nombre)
    {
        if (this.tipoVendedor.buscarTipoVendedorPorNombre(nombre)!=null)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
}
