/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ControladoresGUI;
import AdministradoresGUI.NuevaMateriaPrimaAdministrador;
import EntidadesJPA.BodegaProduccion;
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
public class NuevaMateriaPrimaControlador
{
    
    NuevaMateriaPrimaAdministrador NuevaMateriaPrimaAdminsitrador;
    @FXML
    public VBox pnlPrincipal;
    
    public TextField TFNombre;
    public TextField TFPresentacion;
    public TextField TFCantidad;
    public TextField TFPrecio;
    public ComboBox ComBoBodega;
    private IMateriaPrima materiaPrima;
    private IInventarioMateriaPrima inventarioMateriaPrimaGestor;
    private MateriaPrima MatePri;
    private BodegaProduccion bodega;
    private IBodegaProduccion bodegaGestor;
    private Validaciones validar;
    
    
    public void initialize() {}
  
    public void initManager(final NuevaMateriaPrimaAdministrador NuevaMateriaPrimaAdministrador) {
        this.NuevaMateriaPrimaAdminsitrador = NuevaMateriaPrimaAdministrador;
        this.materiaPrima = new IMateriaPrima();
        this.MatePri = new MateriaPrima();
        this.bodega = new BodegaProduccion();
        this.bodegaGestor = new IBodegaProduccion();
        this.inventarioMateriaPrimaGestor = new IInventarioMateriaPrima();
        this.validar = new Validaciones();
        this.llenarBodega();
    }
    
    public void crearNuevaMateriaPrima ()
    {
        String mensaje;
        
        try
        {
            if (this.verificarCamposVacios())
            {
                this.NuevaMateriaPrimaAdminsitrador.showMensajes("Rellene Todos los Campos");
            }
            else
            if (!this.validar.ValidarMontos(this.TFCantidad.getText()))
            {
                this.NuevaMateriaPrimaAdminsitrador.showMensajes("Verifique la Cantidad");
            }
            else
            if (!this.validar.ValidarMontos(this.TFPrecio.getText()))
            {
                this.NuevaMateriaPrimaAdminsitrador.showMensajes("Verifique el Precio");
            }
            else
            if(this.ComBoBodega.getValue() == null)
            {
                this.NuevaMateriaPrimaAdminsitrador.showMensajes("Seleccione una Bodega");
            }
            else
            if ((this.materiaPrima.buscarMateriaPrimaPorNombre(this.TFNombre.getText())!=null)&&(this.materiaPrima.buscarMateriaPrimaPorPresentacion(this.TFPresentacion.getText())!=null))
            {
                this.NuevaMateriaPrimaAdminsitrador.showMensajes("Nombre de Materia Prima ya Existe");
            }
            else
            {             
            
                mensaje = this.materiaPrima.guardar(TFNombre.getText(), TFPresentacion.getText(), Float.parseFloat(TFPrecio.getText()));        
                TFNombre.setText("");
                TFPresentacion.setText("");
                //ingreso al inventario de materia prima
                MatePri = materiaPrima.retornarUltimoIngresado();
                bodega = this.bodegaGestor.buscarBodegaPorNombre((String)ComBoBodega.getValue());
                inventarioMateriaPrimaGestor.guardar(Float.parseFloat(TFCantidad.getText()), MatePri, bodega);
                this.NuevaMateriaPrimaAdminsitrador.showMensajes(mensaje);

                this.TFCantidad.setText("");
                this.TFNombre.setText("");
                this.TFPresentacion.setText("");
                this.TFNombre.requestFocus();
            }
        }
        catch(Exception e)
        {
            this.NuevaMateriaPrimaAdminsitrador.showMensajes("Error Al Ingresar Materia Prima");
        }
    }
    
    
    public void Cancelar(ActionEvent event)
    {
        this.NuevaMateriaPrimaAdminsitrador.cerrarNuevaMateriaPrima();
    }
    
     public void llenarBodega ()
    {         
         List<String> list = new ArrayList<String>();
         for (BodegaProduccion bodegaS:bodegaGestor.listaDeBodegasDeProduccion())
         {
             list.add(bodegaS.getNombre());
         }
         ObservableList<String> observableList = FXCollections.observableList(list);
         ComBoBodega.setItems(observableList);
    }
    
     public boolean verificarCamposVacios()
     {
         if ((this.TFNombre.getText().isEmpty())||(this.TFCantidad.getText().isEmpty())||(this.TFPresentacion.getText().isEmpty())||(this.ComBoBodega.getValue()==null) )
         {
             return true;
         }
         return false;
     }
}
