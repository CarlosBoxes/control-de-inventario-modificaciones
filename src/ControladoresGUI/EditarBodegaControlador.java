/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ControladoresGUI;

import AdministradoresGUI.EditarBodegaAdministrador;
import EntidadesJPA.BodegaProductos;
import GestorDeTablasJPA.IBodegaProductos;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author luis__000
 */
public class EditarBodegaControlador
{
    
    EditarBodegaAdministrador EditarBodegaAdminsitrador;
    @FXML
    public VBox pnlPrincipal;
    public TextField TFNombre;
    IBodegaProductos Editar ;
    public void initialize() {}
  
    public void initManager(final EditarBodegaAdministrador EditarBodegaAdministrador) {
        this.EditarBodegaAdminsitrador = EditarBodegaAdministrador;
        this.TFNombre.setText(EditarBodegaAdministrador.Bodega.getNombre());
        this.Editar = new IBodegaProductos();
    }
    
    public void EditarBodega(ActionEvent event)
    {        
        BodegaProductos Bodega = new BodegaProductos();
        if (this.TFNombre.getText().isEmpty())
            {
                this.EditarBodegaAdminsitrador.showMensajes("Ingrese el Nombre de La Bodega");
            }
            else
            if (this.verificaBodegaExistente(this.TFNombre.getText()))
            {
                this.EditarBodegaAdminsitrador.showMensajes("Bodega Ya Existe");
            }
            else
            {
                Bodega = Editar.buscarBodegaPorNombre(EditarBodegaAdminsitrador.Bodega.getNombre());
                Bodega.setNombre(TFNombre.getText());
                String Mensaje = Editar.modificar(Bodega);
                this.EditarBodegaAdminsitrador.showMensajes(Mensaje);
                TFNombre.setText("");
                TFNombre.setFocusTraversable(true);
            }
    }
    
    public void Cancelar(ActionEvent event)
    {
        this.EditarBodegaAdminsitrador.cerrarEditarBodega();
    }
    
     public boolean verificaBodegaExistente(String nombreBodega)
    {
        if (this.Editar.buscarBodegaPorNombre(nombreBodega)!=null)
        {
            return true;
        }
        else
            return false;
    }
}
