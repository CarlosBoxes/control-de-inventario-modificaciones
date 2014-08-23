/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ControladoresGUI;

import AdministradoresGUI.EditarBodegaMPAdministrador;
import EntidadesJPA.BodegaProduccion;
import GestorDeTablasJPA.IBodegaProduccion;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author luis__000
 */
public class EditarBodegaMPControlador
{
    
    EditarBodegaMPAdministrador EditarBodegaMPAdminsitrador;
    @FXML
    public VBox pnlPrincipal;
    public TextField TFNombre;
     IBodegaProduccion busqueda;
    
    public void initialize() {}
  
    public void initManager(final EditarBodegaMPAdministrador EditarBodegaMPAdministrador) {
        this.EditarBodegaMPAdminsitrador = EditarBodegaMPAdministrador;
        this.TFNombre.setText(EditarBodegaMPAdministrador.Bodega.getNombre());
        this.busqueda =  new IBodegaProduccion();
    }
    
    public void EditarBodegaMP(ActionEvent event)
    {
        try 
        {
             if (this.TFNombre.getText().isEmpty())
            {
                this.EditarBodegaMPAdminsitrador.showMensajes("Ingrese el Nombre de La Bodega");
            }
            else
                 if (this.verificaBodegaExistente(this.TFNombre.getText()))
            {
                this.EditarBodegaMPAdminsitrador.showMensajes("Bodega Ya Existe");
            }
        else
            {
               
                BodegaProduccion Editar = new BodegaProduccion();
                Editar = busqueda.buscarBodegaPorNombre(EditarBodegaMPAdminsitrador.Bodega.getNombre());
                Editar.setNombre(TFNombre.getText());
                String Mensaje = busqueda.modificar(Editar);
                this.EditarBodegaMPAdminsitrador.showMensajes(Mensaje);
                TFNombre.setText("");
            }
        }
        catch (Exception e)
        {
            this.EditarBodegaMPAdminsitrador.showMensajes("Error Al Editar La Bodega");
        }
    }
    
    public void Cancelar(ActionEvent event)
    {
        this.EditarBodegaMPAdminsitrador.cerrarEditarBodegaMP();
    }
    
    public boolean verificaBodegaExistente(String nombreBodega)
    {
        if (this.busqueda.buscarBodegaPorNombre(nombreBodega)!=null)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
}
