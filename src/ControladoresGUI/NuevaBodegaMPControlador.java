/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ControladoresGUI;

import AdministradoresGUI.NuevaBodegaMPAdministrador;
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
public class NuevaBodegaMPControlador
{
    
    NuevaBodegaMPAdministrador NuevaBodegaMPAdminsitrador;
    @FXML
    public VBox pnlPrincipal;
    public TextField TFNombre;
    private IBodegaProduccion bodega;
    
    
    public void initialize() {}
  
    public void initManager(final NuevaBodegaMPAdministrador NuevaBodegaMPAdministrador) {
        this.NuevaBodegaMPAdminsitrador = NuevaBodegaMPAdministrador;
        this.bodega = new IBodegaProduccion();
    }
    
    public void Cancelar(ActionEvent event)
    {
        this.NuevaBodegaMPAdminsitrador.cerrarNuevaBodegaMP();
    }
    
    public void crearNuebaBodegaDeMP ()
    {
        try
        {
            if (this.TFNombre.getText().isEmpty())
            {
                this.NuevaBodegaMPAdminsitrador.showMensajes("Ingrese el Nombre de La Bodega");
            }
            else
            if (this.verificaBodegaExistente(this.TFNombre.getText()))
            {
                this.NuevaBodegaMPAdminsitrador.showMensajes("Bodega Ya Existe");
            }
        else
            {
            String mensaje ;
            mensaje = this.bodega.guardar(TFNombre.getText());
            TFNombre.setText("");
            this.NuevaBodegaMPAdminsitrador.showMensajes(mensaje);
            }
        }
        catch (Exception e)
        {
            this.NuevaBodegaMPAdminsitrador.showMensajes("Error Al Guardar Nueva Bodega");
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
