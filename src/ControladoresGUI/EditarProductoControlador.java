/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ControladoresGUI;

import AdministradoresGUI.EditarProductoAdministrador;
import EntidadesJPA.BodegaProductos;
import EntidadesJPA.InventarioProducto;
import EntidadesJPA.Productos;
import Especiales.Validaciones;
import GestorDeTablasJPA.IBodegaProductos;
import GestorDeTablasJPA.IInventarioProducto;
import GestorDeTablasJPA.IProductos;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
public class EditarProductoControlador
{
    
    EditarProductoAdministrador EditarProductoAdminsitrador;
    @FXML
    public VBox pnlPrincipal;
    public TextField TFNombre;
    public TextField TFPresentacion;
    public TextField TFUnidadDeMedida;
    public TextField TFCategoria;
    public TextField TFFecha;
    public CheckBox CBCambio;
    public ComboBox ComBoBodega;
    public CheckBox CBVencimiento;
    public TextField TFPorcentajeDeDevolucion;
    public TextField TFPrecioCosto;
    public TextField TFPrecioVenta;
    public TextArea TADescripcion;
    public IBodegaProductos bodegas;
    public HBox fechaDeVencimiento;
    private Validaciones validar ;
    private int ContadorTecla;
    private IProductos gestorProductos;
    private Productos producto;


    
    
    public void initialize() {}
  
    public void initManager(final EditarProductoAdministrador EditarProductoAdministrador) {
        this.EditarProductoAdminsitrador = EditarProductoAdministrador;
        this.bodegas = new IBodegaProductos();
        
        TFNombre.setText(EditarProductoAdministrador.Producto.getNombre());
        TFPresentacion.setText(EditarProductoAdministrador.Producto.getPresentacion());
        TFUnidadDeMedida.setText(String.valueOf(EditarProductoAdministrador.Producto.getUnidadDeMedida()));
        TFCategoria.setText(EditarProductoAdministrador.Producto.getCategoria());
        TFPorcentajeDeDevolucion.setText(String.valueOf(EditarProductoAdministrador.Producto.getDevoluciones()));
        SimpleDateFormat formato = new java.text.SimpleDateFormat("dd/MM/yyyy");
        String fecha = null;
        if(EditarProductoAdministrador.Producto.getFechaDeVencimiento() != null)
        {
            fecha = formato.format(EditarProductoAdministrador.Producto.getFechaDeVencimiento());
            this.fechaDeVencimiento.setDisable(false);
            this.CBVencimiento.setSelected(true);
        }
        TFFecha.setText(fecha);
        CBCambio.setSelected(EditarProductoAdministrador.Producto.getCambio());
        TFPrecioCosto.setText(String.valueOf(EditarProductoAdministrador.Producto.getPrecioCosto()));
        TFPrecioVenta.setText(String.valueOf(EditarProductoAdministrador.Producto.getPrecioVenta()));
        TADescripcion.setText(EditarProductoAdministrador.Producto.getDescripcion());
        for(InventarioProducto Inventario: EditarProductoAdministrador.Producto.getInventarioProductoCollection())
        {
            ComBoBodega.setValue(Inventario.getBodegaProductosidbodega().getNombre());
        }
        this.validar = new Validaciones ();
        this.gestorProductos = new IProductos ();
        this.producto = new Productos ();
        llenarBodega ();
    }
    
    public void Cancelar(ActionEvent event)
    {
        this.EditarProductoAdminsitrador.cerrarEditarProducto();
    }
    
    public void EditarProducto (ActionEvent event)
    {
        if (this.verificarCamposVacios())
        {
            this.EditarProductoAdminsitrador.showMensajes("Rellene todos los Campos");
       
        }
        else
        if (!this.validar.ValidarMontos(this.TFPorcentajeDeDevolucion.getText()))
        {
            this.EditarProductoAdminsitrador.showMensajes("Verifique El Porcentaje de Devolucion");
        }   
        else
        if (!this.validar.ValidarMontos(this.TFPrecioCosto.getText()))
        {
            this.EditarProductoAdminsitrador.showMensajes("Verifique El Precio Costo Del Producto");
        }
        else
        if (!this.validar.ValidarMontos(this.TFPrecioVenta.getText()))
        {
            this.EditarProductoAdminsitrador.showMensajes("Verifique El Precio De Venta Del Producto");
        }
        else
        if(this.ComBoBodega.getValue() == null)
        {
            this.EditarProductoAdminsitrador.showMensajes("Seleccione una Bodega");
        }
        else
        {
           IProductos Editar  = new IProductos ();
           float precioCosto= Float.parseFloat( TFPrecioCosto.getText());
           float precioVenta = Float.parseFloat(TFPrecioVenta.getText());
           float porcentajeDevolucion = Float.parseFloat(this.TFPorcentajeDeDevolucion.getText());
           int unidadDeMedida = Integer.parseInt(TFUnidadDeMedida.getText());
           Date fechaVencimiento = null;
            if (this.CBVencimiento.isSelected())
            { 
                DateFormat df = DateFormat.getDateInstance(DateFormat.SHORT);               
                try 
                {
                    fechaVencimiento = df.parse(TFFecha.getText());
                } catch (ParseException ex) 
                {
                    Logger.getLogger(EditarProductoControlador.class.getName()).log(Level.SEVERE, null, ex);
                }       
            }
            Productos Producto = Editar.Existente(this.EditarProductoAdminsitrador.Producto.getNombre(),this.EditarProductoAdminsitrador.Producto.getPresentacion());
            Producto.setPrecioCosto(precioCosto);
            Producto.setDescripcion(TADescripcion.getText());
            Producto.setCategoria(TFCategoria.getText());
            Producto.setPrecioVenta(precioVenta);
            Producto.setFechaDeVencimiento(fechaVencimiento);
            Producto.setCambio(CBCambio.isSelected());
            Producto.setNombre(TFNombre.getText());
            Producto.setPresentacion(TFPresentacion.getText());
            Producto.setDevoluciones(porcentajeDevolucion);
            Producto.setUnidadDeMedida(unidadDeMedida);
            InventarioProducto Inventario = new IInventarioProducto().buscarInventarioPorProducto(Producto);
            BodegaProductos Bodega = new IBodegaProductos().buscarBodegaPorNombre((String)ComBoBodega.getValue());
            Inventario.setBodegaProductosidbodega(Bodega);
            new IInventarioProducto().modificar(Inventario);
            String Mensaje = Editar.modificar(Producto);
            this.EditarProductoAdminsitrador.showMensajes(Mensaje);        
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
            this.Cancelar(event);
        }
    }
     
     public void ingresarAlInventariNuevoProducto (BodegaProductos bodega,Productos producto,int cantidad)
     {
         IInventarioProducto inventario = new IInventarioProducto ();
         
         inventario.guardar(producto, bodega, cantidad);
         
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
    
     public boolean verificarCamposVacios () //falta la validacion para que ingrese la fecha de vencimiento y que sea posible no ingresar fecha de vencimiento
     {
         String nombre = String.valueOf(this.TFNombre.getText());
         if ((this.TFNombre.getText().isEmpty())||
             (this.TFPresentacion.getText().isEmpty())||
             (this.TFUnidadDeMedida.getText().isEmpty())||
             (this.TFCategoria.getText().isEmpty())||    
             (this.TFPrecioCosto.getText().isEmpty())||
             (this.TFPrecioVenta.getText().isEmpty())||
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
}
