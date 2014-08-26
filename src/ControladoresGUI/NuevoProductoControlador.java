/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ControladoresGUI;

import AdministradoresGUI.NuevoProductoAdministrador;
import EntidadesJPA.BodegaProductos;
import EntidadesJPA.Productos;
import Especiales.Validaciones;
import GestorDeTablasJPA.IBodegaProductos;
import GestorDeTablasJPA.IInventarioProducto;
import GestorDeTablasJPA.IProductos;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author luis__000
 */
public class NuevoProductoControlador
{
    
    NuevoProductoAdministrador NuevoProductoAdminsitrador;
    @FXML
    public VBox pnlPrincipal;
    public TextField TFNombre;
    public TextField TFPresentacion;
    public TextField TFUnidadDeMedida;
    public TextField TFCategoria;
    public TextField TFFecha;
    public CheckBox CBCambio;
    public CheckBox CBVencimiento;
    public TextField TFPorcentajeDeDevolucion;
    public TextField TFPrecioCosto;
    public TextField TFPrecioVenta;
    public TextField TFCantidad;
    public ComboBox ComBoBodega;
    public TextArea TADescripcion;
    public IBodegaProductos bodegas;
    private Validaciones validar;
    public HBox fechaDeVencimiento;
    private int ContadorTecla;
    private IProductos gestorProductos;
    private Productos producto;


    
    
    public void initialize() {}
  
    public void initManager(final NuevoProductoAdministrador NuevoProductoAdministrador) {        
        this.NuevoProductoAdminsitrador = NuevoProductoAdministrador;
        this.gestorProductos = new IProductos();
        this.bodegas = new IBodegaProductos();
        this.llenarBodega();
        TFNombre.setText("");
        TFNombre.setFocusTraversable(true);
        TFPresentacion.setText("");
        TFUnidadDeMedida.setText("");
        TFCategoria.setText("");
        TFFecha.setText("");
        ContadorTecla = 0;
        CBCambio.setSelected(false);
        TFPrecioCosto.setText("0.00");
        TFPrecioVenta.setText("0.00");
        TADescripcion.setText("");
        TFCantidad.setText("0");
        this.validar = new Validaciones ();
                
    }
    
    
    public void Cancelar(ActionEvent event)
    {
        this.NuevoProductoAdminsitrador.cerrarNuevoProducto();
    }
    
    public void CrearNuevoProducto (ActionEvent event)
    {
        if (this.verificarCamposVacios())
        {
            this.NuevoProductoAdminsitrador.showMensajes("Debe Rellenar los campos Vacios.");
            
        }
        else
        if (!this.validar.ValidarNumeros(this.TFPorcentajeDeDevolucion.getText()))
        {
            this.NuevoProductoAdminsitrador.showMensajes("Verifique El Porcentaje de Devolucion.");
        }   
        else
        if (!this.validar.ValidarMontos(this.TFPrecioCosto.getText()))
        {
            this.NuevoProductoAdminsitrador.showMensajes("Verifique El Precio Costo Del Producto.");
        }
        else
        if (!this.validar.ValidarMontos(this.TFPrecioVenta.getText()))
        {
            this.NuevoProductoAdminsitrador.showMensajes("Verifique El Precio De Venta Del Producto.");
        }        
        else
        if (!this.validar.ValidarMontos(this.TFCantidad.getText()))
        {
            this.NuevoProductoAdminsitrador.showMensajes("Verifique La Cantidad Del Producto.");
        }
        else
            if(!this.verificarProductoExistente(this.TFNombre.getText(), this.TFPresentacion.getText()))
            {
                this.NuevoProductoAdminsitrador.showMensajes("Producto ya Existente.");
            }
        else
        {
            IProductos productoNuevo  = new IProductos ();
            float precioCosto= Float.parseFloat( TFPrecioCosto.getText());
            float precioVenta = Float.parseFloat(TFPrecioVenta.getText());
            boolean cambio = CBCambio.isSelected();
            float porcentajeDevolucion = Float.parseFloat( this.TFPorcentajeDeDevolucion.getText());
            int unidadDeMedida = Integer.parseInt(TFUnidadDeMedida.getText());
            java.util.Date fechaVencimiento = null;
            if (this.CBVencimiento.isSelected())
            { 
                DateFormat df = DateFormat.getDateInstance(DateFormat.SHORT);                
                try {
                    fechaVencimiento = df.parse(TFFecha.getText());
                } catch (ParseException ex) {
                    Logger.getLogger(NuevoProductoControlador.class.getName()).log(Level.SEVERE, null, ex);
                }       
            }
            
            {
                String mensaje = productoNuevo.guardar(TFNombre.getText(), TFPresentacion.getText(),unidadDeMedida,precioCosto,precioVenta ,fechaVencimiento,TADescripcion.getText(), TFCategoria.getText(),cambio,porcentajeDevolucion);
                this.ingresarAlInventariNuevoProducto(bodegas.buscarBodegaPorNombre((String)ComBoBodega.getValue()),productoNuevo.retornarUltimoIngresado(),Float.parseFloat(TFCantidad.getText()));
                this.NuevoProductoAdminsitrador.showMensajes(mensaje);  
                this.limpiarTodo();
            }
        }
    }
    
    public void habilitarFechaDeVencimiento (ActionEvent event)
    {
        if (this.CBVencimiento.isSelected())
        {
            this.fechaDeVencimiento.setDisable(false);
        }
        else
        {
            this.fechaDeVencimiento.setDisable(true);
        }
    }
            
     public void llenarBodega ()
    {         
         List<String> list = new ArrayList<String>();
         for (BodegaProductos bodega:bodegas.listaDeBodegasDeProductos())
         {
             list.add(bodega.getNombre());
         }
         ObservableList<String> observableList = FXCollections.observableList(list);
         ComBoBodega.setItems(observableList);
    }
     
     public void ingresarAlInventariNuevoProducto (BodegaProductos bodega,Productos producto,float cantidad)
     {
         IInventarioProducto inventario = new IInventarioProducto ();
         
         inventario.guardar(producto, bodega, cantidad);
         
     }
    
     public boolean verificarCamposVacios () //falta la validacion para que ingrese la fecha de vencimiento y que sea posible no ingresar fecha de vencimiento
     {
         if ((this.TFNombre.getText().isEmpty())||
             (this.TFPresentacion.getText().isEmpty())||
             (this.TFUnidadDeMedida.getText().isEmpty())||
             (this.TFCategoria.getText().isEmpty())||    
             (this.TFPrecioCosto.getText().isEmpty())||
             (this.TFPrecioVenta.getText().isEmpty())||
             (this.TFCantidad.getText().isEmpty())||
             (this.ComBoBodega.getValue()==null)||
             (this.TFPorcentajeDeDevolucion.getText().isEmpty())
            )
         {
             return true;
         }
         else
         {
            return false;
         }
         
     }
     
     private void  limpiarTodo ()
     {
         TFNombre.setText("");
        TFNombre.requestFocus();
        TFPresentacion.setText("");
        TFUnidadDeMedida.setText("");
        TFCategoria.setText("");
        TFFecha.setText("");
        ContadorTecla = 0;
        CBCambio.setSelected(false);
        TFPrecioCosto.setText("0.00");
        TFPrecioVenta.setText("0.00");
        TADescripcion.setText("");
        TFCantidad.setText("0");
     }
     
     public void SoltoTecla(KeyEvent e)
    {
        String Tecla = e.getText();
        int Key;
        Key = e.getCode().impl_getCode();
        boolean Valido = validar.ValidarNumero(Tecla);
        if(Key == 8)
        {
            ComprobarTamaño();
        }
        else if(Key == 111 || Key == 55)
        {
            AgregarCero();
        }
        else
        {
            if((Key > 46 && Key < 106))
            {
                if(!Valido)
                {
                    TFFecha.deletePreviousChar();
                }
                else
                {

                    ContadorTecla++;
                    if(TFFecha.getText().length()<6)
                    {
                        if(ContadorTecla == 2)
                        {
                            TFFecha.setText(TFFecha.getText()+"/");
                            TFFecha.end();
                            ComprobarTamaño();
                        }
                    }
                    else if(TFFecha.getText().length() > 10)
                    {
                        TFFecha.deletePreviousChar();
                    }
                    ComprobarTamaño();
                }
            }
        }
    }
    
    private void ComprobarTamaño()
    {
        if(TFFecha.getText().length() == 0 || TFFecha.getText().length() == 3 || TFFecha.getText().length() == 5)
        {
            ContadorTecla = 0;
        }
        if(TFFecha.getText().length() == 1 || TFFecha.getText().length() == 4)
        {
            ContadorTecla = 1;
        }
    }
    
    private void AgregarCero()
    {
        if(TFFecha.getText().length() == 2)
        {
            TFFecha.setText("0"+TFFecha.getText());
            TFFecha.end();
            ContadorTecla = 0;
        }
        else if(TFFecha.getText().length() == 5)
        {
            TFFecha.insertText(3, "0");
            TFFecha.end();
            ContadorTecla = 0;
        }
        else if(TFFecha.getText().length() < 3)
        {
            TFFecha.deletePreviousChar();
        }
        else if(TFFecha.getText().length() > 3 && TFFecha.getText().length() < 6)
        {
            TFFecha.deletePreviousChar();
        }
        else if(TFFecha.getText().length() > 6)
        {
            TFFecha.deletePreviousChar();
        }
    } 
    
    public boolean verificarProductoExistente (String nombre,String Presentacion)
    {
        producto = this.gestorProductos.Existente(nombre, Presentacion);
        if (producto == null)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    
}
