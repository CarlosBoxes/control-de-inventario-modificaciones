/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ControladoresGUI;

import AdministradoresGUI.EditarMateriaPrimaAdministrador;
import EntidadesJPA.BodegaProduccion;
import EntidadesJPA.InventarioMateriaPrima;
import EntidadesJPA.MateriaPrima;
import Especiales.Validaciones;
import GestorDeTablasJPA.IBodegaProduccion;
import GestorDeTablasJPA.IInventarioMateriaPrima;
import GestorDeTablasJPA.IMateriaPrima;
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
public class EditarMateriaPrimaControlador
{
    
    EditarMateriaPrimaAdministrador EditarMateriaPrimaAdminsitrador;
    @FXML
    public VBox pnlPrincipal;
    public TextField TFNombre;
    public TextField TFPresentacion;
    public TextField TFPrecio;
    public ComboBox ComBoBodega;
    private Validaciones validar;
    
    public void initialize() {}
  
    public void initManager(final EditarMateriaPrimaAdministrador EditarMateriaPrimaAdministrador) {
        this.EditarMateriaPrimaAdminsitrador = EditarMateriaPrimaAdministrador;
        this.TFNombre.setText(EditarMateriaPrimaAdministrador.MateriaPrima.getNombre());
        this.TFPresentacion.setText(EditarMateriaPrimaAdministrador.MateriaPrima.getPresentacion());
        this.TFPrecio.setText(String.valueOf(EditarMateriaPrimaAdministrador.MateriaPrima.getPrecio()));
        for(InventarioMateriaPrima Inventario: EditarMateriaPrimaAdministrador.MateriaPrima.getInventarioMateriaPrimaCollection())
        {
            this.ComBoBodega.setValue(Inventario.getBodegaProduccionidbodegaProduccion().getNombre());
        }
        this.validar = new Validaciones();
        llenarBodega();
    }
    
    public void EditarMateriaPrima(ActionEvent event)
    {
        if (this.verificarCamposVacios())
        {
            this.EditarMateriaPrimaAdminsitrador.showMensajes("Rellene Los Campos Vacios");
        }
        else
        if (!validar.ValidarMontos(this.TFPrecio.getText()))
        {
            this.EditarMateriaPrimaAdminsitrador.showMensajes("Verifique el Precio");
        }
        else
        if(this.ComBoBodega.getValue() == null)
        {
            this.EditarMateriaPrimaAdminsitrador.showMensajes("Seleccione una Bodega");
        }
        else
        {
            IMateriaPrima buscar = new IMateriaPrima();
            MateriaPrima Editar = buscar.buscarMateriaPrimaPorNombre(EditarMateriaPrimaAdminsitrador.MateriaPrima.getNombre());
            Editar.setNombre(TFNombre.getText());
            Editar.setPresentacion(TFPresentacion.getText());
            Editar.setPrecio(Float.parseFloat(TFPrecio.getText()));
            InventarioMateriaPrima Inventario = new IInventarioMateriaPrima().buscarInventarioPorIdMateriaPrima(Editar);
            BodegaProduccion Bodega = new IBodegaProduccion().buscarBodegaPorNombre((String)ComBoBodega.getValue());
            Inventario.setBodegaProduccionidbodegaProduccion(Bodega);
            new IInventarioMateriaPrima().modificar(Inventario);
            String Mensajes = buscar.modificar(Editar);
            this.EditarMateriaPrimaAdminsitrador.showMensajes(Mensajes);
            TFNombre.setText("");
            TFPresentacion.setText("");
            TFPrecio.setText("");
        }
    }
    
    public void Cancelar(ActionEvent event)
    {
        this.EditarMateriaPrimaAdminsitrador.cerrarEditarMateriaPrima();
    }
    
     public boolean verificarCamposVacios()
     {
         if ((this.TFNombre.getText().isEmpty())||(this.TFPresentacion.getText().isEmpty())||(this.TFPrecio.getText().isEmpty())||(this.ComBoBodega.getValue() == null))
         {
             return true;
         }
         return false;
     }
     
     public void llenarBodega ()
    {         
         List<String> list = new ArrayList<String>();
         for (BodegaProduccion bodegaS:new IBodegaProduccion().listaDeBodegasDeProduccion())
         {
             list.add(bodegaS.getNombre());
         }
         ObservableList<String> observableList = FXCollections.observableList(list);
         ComBoBodega.setItems(observableList);
    }
}
