/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ControladoresGUI;

import AdministradoresGUI.NuevoTipoVendedorAdministrador;
import GestorDeTablasJPA.ITipoVendedor;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author luis__000
 */
public class NuevoTipoVendedorControlador
{
    
    NuevoTipoVendedorAdministrador NuevoTipoVendedorAdminsitrador;
    @FXML
    public CheckBox Lista;
    public TextField TFNombre;
    private ITipoVendedor tipoVendedor;
    
    public void initialize() {}
  
    public void initManager(final NuevoTipoVendedorAdministrador NuevoTipoVendedorAdministrador) {
        this.NuevoTipoVendedorAdminsitrador = NuevoTipoVendedorAdministrador;
        this.tipoVendedor = new ITipoVendedor();
    }
    
    public void crearNuevoTipoVendedor (ActionEvent event)
    {
        if (this.TFNombre.getText().isEmpty())
        {
            this.NuevoTipoVendedorAdminsitrador.showMensajes("Rellene Todos Los Campos");
        }
        else
            if(this.VerificarExistente(this.TFNombre.getText()))
            {
                this.NuevoTipoVendedorAdminsitrador.showMensajes("Tipo de Vendedor Ya Existe");
            }
        else
        {
                try
                {
                    if(Lista.isSelected())
                    {
                        String mensaje = tipoVendedor.guardar(TFNombre.getText(), true);
                        this.NuevoTipoVendedorAdminsitrador.showMensajes(mensaje);
                    }
                    else
                    {
                        String mensaje = tipoVendedor.guardar(TFNombre.getText(), false);
                        this.NuevoTipoVendedorAdminsitrador.showMensajes(mensaje);
                    }
                    TFNombre.setText("");
                    Lista.setSelected(false);
                    TFNombre.setFocusTraversable(true);
                }
                catch (Exception e)
                {
                    this.NuevoTipoVendedorAdminsitrador.showMensajes("Error Al Crear Nuevo Tipo de Vendedor");
                }
        }
    }
    
    public void Cancelar(ActionEvent event)
    {
        this.NuevoTipoVendedorAdminsitrador.cerrarNuevoTipoVendedor();
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
