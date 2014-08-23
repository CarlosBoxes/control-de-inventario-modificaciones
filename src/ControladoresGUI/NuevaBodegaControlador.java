/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ControladoresGUI;

import AdministradoresGUI.NuevaBodegaAdministrador;
import GestorDeTablasJPA.IBodegaProductos;
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
public class NuevaBodegaControlador
{
    
    NuevaBodegaAdministrador NuevaBodegaAdminsitrador;
    @FXML
    public VBox pnlPrincipal;
  
    public TextField TFNombre;
    public ComboBox ComBoTipo;
    private IBodegaProductos bodega;
    
    
    public void initialize() {}
  
    public void initManager(final NuevaBodegaAdministrador NuevaBodegaAdministrador) {
        this.NuevaBodegaAdminsitrador = NuevaBodegaAdministrador;
        this.bodega = new IBodegaProductos();
    }
    
    public void Cancelar(ActionEvent event)
    {
        this.NuevaBodegaAdminsitrador.cerrarNuevoUsuario();
    }
    
    public void crearNuevaBodega()
    {
        String mensaje;
        if (this.TFNombre.getText().isEmpty())
            {
                this.NuevaBodegaAdminsitrador.showMensajes("Ingrese el Nombre de La Bodega");
            }
            else
            if (this.verificaBodegaExistente(this.TFNombre.getText()))
            {
                this.NuevaBodegaAdminsitrador.showMensajes("Bodega Ya Existe");
            }
            else
        {
            mensaje = this.bodega.guardar(TFNombre.getText());
            this.NuevaBodegaAdminsitrador.showMensajes(mensaje);
            TFNombre.requestFocus();
            TFNombre.setText("");
        }
    }
    
    public boolean verificaBodegaExistente(String nombreBodega)
    {
        if (this.bodega.buscarBodegaPorNombre(nombreBodega)!=null)
        {
            return true;
        }
        else
            return false;
    }
  
}
