/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ControladoresGUI;

import AdministradoresGUI.ProductosAdministrador;
import EntidadesJPA.BodegaProductos;
import EntidadesJPA.Productos;
import Especiales.GeneradordeReportes;
import Especiales.Validaciones;
import GestorDeTablasJPA.IBodegaProductos;
import GestorDeTablasJPA.IInventarioProducto;
import GestorDeTablasJPA.IProductos;
import Modelos.ProductoModelo;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
/**
 *
 * @author luis__000
 */
public class ProductosControlador 
{
    ProductosAdministrador ProductoAdministrador;
    @FXML
    public TableView TablaProductos;
    public TableColumn Id;
    public TableColumn Nombre;
    public TableColumn Cantidad;
    public TableColumn Presentacion;
    public TableColumn UnidadMedida;
    public TableColumn PrecioCosto;
    public TableColumn PrecioVenta;
    public TableColumn FechaVencimiento;
    public TableColumn Descripcion;
    public TableColumn Categoria;
    public TextField TFBusqueda;
    public TextField TFFechaI;
    public TextField TFFechaF;
    public ComboBox ComBoBodega;
    private Validaciones Validar;
    private int ContadorTeclaI;
    private int ContadorTeclaF;
    private ObservableList<ProductoModelo> data;
    
    public void initialize() {}
  
    public void initManager(final ProductosAdministrador ProductoAdministrador)
    {
        this.ProductoAdministrador = ProductoAdministrador;
        data = FXCollections.observableArrayList();
        Id = new TableColumn();
        Nombre = new TableColumn();
        Cantidad = new TableColumn();
        Presentacion = new TableColumn();
        UnidadMedida = new TableColumn();
        PrecioCosto = new TableColumn();
        PrecioVenta = new TableColumn();
        FechaVencimiento = new TableColumn();
        Descripcion = new TableColumn();
        Categoria = new TableColumn();
        Validar = new Validaciones();
        CargarColumnas();
        llenarBodega ();
    }
    
    public void abrirNuevoProducto()
    {
        if(new IBodegaProductos().listaDeBodegasDeProductos() != null)
        {
            this.ProductoAdministrador.showNuevoProducto();
        }
        else
        {
            this.ProductoAdministrador.showMensajes("No hay Bodegas Ingresadas, Diríjase a Bodegas de Productos");
        }
    }
    
    private void CargarColumnas() 
    {
        Id.setCellValueFactory(new PropertyValueFactory<ProductoModelo,Integer>("Id"));
        Nombre.setCellValueFactory(new PropertyValueFactory<ProductoModelo,String>("Nombre"));
        Presentacion.setCellValueFactory(new PropertyValueFactory<ProductoModelo,String>("Presentacion"));
        Cantidad.setCellValueFactory(new PropertyValueFactory<ProductoModelo,Integer>("Cantidad"));
        UnidadMedida.setCellValueFactory(new PropertyValueFactory<ProductoModelo,String>("UnidadMedida")); 
        PrecioCosto.setCellValueFactory(new PropertyValueFactory<ProductoModelo,String>("PrecioCosto")); 
        PrecioVenta.setCellValueFactory(new PropertyValueFactory<ProductoModelo,String>("PrecioVenta"));
        FechaVencimiento.setCellValueFactory(new PropertyValueFactory<ProductoModelo,String>("FechaVencimiento"));
        Descripcion.setCellValueFactory(new PropertyValueFactory<ProductoModelo,String>("Descripcion"));
        Categoria.setCellValueFactory(new PropertyValueFactory<ProductoModelo,String>("Categoria"));
        Id.setText("Id");
        Nombre.setText("Nombre");
        Cantidad.setText("Cantidad");
        Presentacion.setText("Presentación");
        UnidadMedida.setText("Unidad de Medida");
        PrecioCosto.setText("Precio Costo");
        PrecioVenta.setText("Precio Venta");
        FechaVencimiento.setText("Fecha de Vencimiento");
        Descripcion.setText("Descripcion");
        Categoria.setText("Categoria");
        Id.setPrefWidth(50);
        Nombre.setPrefWidth(200);
        Cantidad.setPrefWidth(100);
        Presentacion.setPrefWidth(100);
        UnidadMedida.setPrefWidth(110);
        PrecioCosto.setPrefWidth(100);
        PrecioVenta.setPrefWidth(100);
        FechaVencimiento.setPrefWidth(100);
        Descripcion.setPrefWidth(300);
        Categoria.setPrefWidth(100);
        TablaProductos.getColumns().add(Id);
        TablaProductos.getColumns().add(Nombre);
        TablaProductos.getColumns().add(Cantidad);
        TablaProductos.getColumns().add(Presentacion);
        TablaProductos.getColumns().add(UnidadMedida);
        TablaProductos.getColumns().add(PrecioCosto);
        TablaProductos.getColumns().add(PrecioVenta);
        TablaProductos.getColumns().add(FechaVencimiento);
        TablaProductos.getColumns().add(Categoria);
        TablaProductos.getColumns().add(Descripcion);
    }
    
    public void LLenarTabla()
    {
        if("".equals(TFBusqueda.getText()))
        {
            List<Productos> Producto = new IProductos().listaDeProductos();
            if(Producto != null)
            {
                for(Productos nuevo:Producto)
                {
                    float cantidad = new IInventarioProducto().buscarInventarioPorProducto(nuevo).getCantidad();
                    SimpleDateFormat Formato = new java.text.SimpleDateFormat("dd/MM/yyyy");
                    String Fecha = null;
                    if(nuevo.getFechaDeVencimiento() != null)
                    {
                        Fecha = Formato.format(nuevo.getFechaDeVencimiento());
                    }
                    ProductoModelo ProductoModelo = new ProductoModelo(nuevo.getIdProductos(), nuevo.getNombre(),cantidad , nuevo.getPresentacion(), nuevo.getUnidadDeMedida(), nuevo.getPrecioCosto(), nuevo.getPrecioVenta(), Fecha, nuevo.getDescripcion(), nuevo.getCategoria());
                    data.add(ProductoModelo);
                }
            }
            else
            {
                this.ProductoAdministrador.showMensajes("No Existen Productos");
            }
        }
        else
        {
            List<Productos> Productos = new IProductos().buscarListaProductoPorNombre(TFBusqueda.getText());
            if(Productos != null)
            {
                for(Productos nuevo:Productos)
                {
                    float cantidad = new IInventarioProducto().buscarInventarioPorProducto(nuevo).getCantidad();
                    SimpleDateFormat Formato = new java.text.SimpleDateFormat("dd/MM/yyyy");
                    String Fecha = null;
                    if(nuevo.getFechaDeVencimiento() != null)
                    {
                        Fecha = Formato.format(nuevo.getFechaDeVencimiento());
                    }
                    ProductoModelo ProductoModelo = new ProductoModelo(nuevo.getIdProductos(), nuevo.getNombre(), cantidad, nuevo.getPresentacion(), nuevo.getUnidadDeMedida(), nuevo.getPrecioCosto(), nuevo.getPrecioVenta(), Fecha, nuevo.getDescripcion(), nuevo.getCategoria());
                    data.add(ProductoModelo);
                }
}
            else
            {
                this.ProductoAdministrador.showMensajes("No Existen Productos que Contenga(n) esa(s) Letra(s)");
            }
        }
 }
    
    public void Buscar(ActionEvent event)
    {
        data.clear();
        TablaProductos.setItems(data);
        LLenarTabla();
        TablaProductos.setItems(data);
    }
     
    public void EditarProducto(ActionEvent event)
    {
        ProductoModelo Seleccionado = (ProductoModelo)TablaProductos.getSelectionModel().getSelectedItem();
        if(Seleccionado == null)
        {
            this.ProductoAdministrador.showMensajes("Debe Seleccionar un Dato");
        }
        else
        {
            Productos Editar = new IProductos().buscarProductoPorId(Seleccionado.getId());            
            this.ProductoAdministrador.showEditarProducto(Editar);
        }
    }
    
    public void BorrarProducto(ActionEvent event)
    {
        ProductoModelo Seleccionado = (ProductoModelo) TablaProductos.getSelectionModel().getSelectedItem();
        int indice = TablaProductos.getSelectionModel().getSelectedIndex();
        if(Seleccionado == null)
        {
            this.ProductoAdministrador.showMensajes("Debe Selecionar un Dato");
        }
        else
        {
            Productos Producto = new IProductos().buscarProductoPorId(Seleccionado.getId());
            Producto.setEliminado(true);
            String Mensaje = new IProductos().eliminar(Producto);
            this.ProductoAdministrador.showMensajes(Mensaje);
            if(Mensaje.equals("Producto Eliminado Correctamente"))
            {
                data.remove(indice);
                TablaProductos.setItems(data);
        }
        }
    }
    
    public void AbrirReporte(ActionEvent event)
    {
        GeneradordeReportes Generar = new GeneradordeReportes();
        Generar.AbrirReporte("InventarioProductos.jasper");
    }
    
    public void AbrirReporteBodega(ActionEvent event)
    {
        if(ComBoBodega.getValue() != null)
        {
            BodegaProductos Bodega = new IBodegaProductos().buscarBodegaPorNombre((String)ComBoBodega.getValue());
            GeneradordeReportes Generar = new GeneradordeReportes();
            Generar.AbrirReporte("InventarioProductosBodega.jasper", Bodega.getIdbodega());
        }
        else
        {
            this.ProductoAdministrador.showMensajes("Seleccione un Bodega");
        }
    }
    
    public void AbrirReporteVentas(ActionEvent event)
    {
        ProductoModelo Seleccionado = (ProductoModelo) TablaProductos.getSelectionModel().getSelectedItem();
        if(Seleccionado == null)
        {
            this.ProductoAdministrador.showMensajes("Debe Selecionar un Dato");
        }
        else
        {
            GeneradordeReportes Reporte = new GeneradordeReportes();
            if(ValidarFecha(TFFechaI.getText()) && ValidarFecha(TFFechaF.getText()))
            {
                String FechaIn = TFFechaI.getText();
                String FechaFin = TFFechaF.getText();
                DateFormat Formato = DateFormat.getDateInstance(DateFormat.SHORT);
                Date FechaI = null;
                Date FechaF = null;
                try 
                {
                    FechaI = Formato.parse(FechaIn);
                    FechaF = Formato.parse(FechaFin);
                } 
                catch (ParseException ex) 
                {
                    Logger.getLogger(NuevoProductoControlador.class.getName()).log(Level.SEVERE, null, ex);
                }
                Reporte.AbrirReporte("VentasPorProductos.jasper",Seleccionado.getId(),FechaI,FechaF);
            }
            else
            {
                this.ProductoAdministrador.showMensajes("Verifique los campos Fecha Inicio o Fecha Fin");
            }  
        }
    }
    
    public void SoltoTeclaI(KeyEvent e)
    {
        String Tecla = e.getText();
        int Key;
        Key = e.getCode().impl_getCode();
        boolean Valido = Validar.ValidarNumero(Tecla);
        if(Key == 8)
        {
            ComprobarTamañoI();
        }
        else if(Key == 111 || Key == 55)
        {
            AgregarCeroI();
        }
        else
        {
            if((Key > 46 && Key < 106))
            {
                if(!Valido)
                {
                    TFFechaI.deletePreviousChar();
                }
                else
                {

                    ContadorTeclaI++;
                    if(TFFechaI.getText().length()<6)
                    {
                        if(ContadorTeclaI == 2)
                        {
                            TFFechaI.setText(TFFechaI.getText()+"/");
                            TFFechaI.end();
                            ComprobarTamañoI();
                        }
                    }
                    else if(TFFechaI.getText().length() > 10)
                    {
                        TFFechaI.deletePreviousChar();
                    }
                    ComprobarTamañoI();
                }
            }
        }
    }
    
    private void ComprobarTamañoI()
    {
        if(TFFechaI.getText().length() == 0 || TFFechaI.getText().length() == 3 || TFFechaI.getText().length() == 5)
        {
            ContadorTeclaI = 0;
        }
        if(TFFechaI.getText().length() == 1 || TFFechaI.getText().length() == 4)
        {
            ContadorTeclaI = 1;
        }
    }
    
    private void AgregarCeroI()
    {
        if(TFFechaI.getText().length() == 2)
        {
            TFFechaI.setText("0"+TFFechaI.getText());
            TFFechaI.end();
            ContadorTeclaI = 0;
        }
        else if(TFFechaI.getText().length() == 5)
        {
            TFFechaI.insertText(3, "0");
            TFFechaI.end();
            ContadorTeclaI = 0;
        }
        else if(TFFechaI.getText().length() < 3)
        {
            TFFechaI.deletePreviousChar();
        }
        else if(TFFechaI.getText().length() > 3 && TFFechaI.getText().length() < 6)
        {
            TFFechaI.deletePreviousChar();
        }
        else if(TFFechaI.getText().length() > 6)
        {
            TFFechaI.deletePreviousChar();
        }
    }
    
    public void SoltoTeclaF(KeyEvent e)
    {
        String Tecla = e.getText();
        int Key;
        Key = e.getCode().impl_getCode();
        boolean Valido = Validar.ValidarNumero(Tecla);
        if(Key == 8)
        {
            ComprobarTamañoF();
        }
        else if(Key == 111 || Key == 55)
        {
            AgregarCeroF();
        }
        else
        {
            if((Key > 46 && Key < 106))
            {
                if(!Valido)
                {
                    TFFechaF.deletePreviousChar();
                }
                else
                {

                    ContadorTeclaF++;
                    if(TFFechaF.getText().length()<6)
                    {
                        if(ContadorTeclaF == 2)
                        {
                            TFFechaF.setText(TFFechaF.getText()+"/");
                            TFFechaF.end();
                            ComprobarTamañoF();
                        }
                    }
                    else if(TFFechaF.getText().length() > 10)
                    {
                        TFFechaF.deletePreviousChar();
                    }
                    ComprobarTamañoF();
                }
            }
        }
    }
    
    private void ComprobarTamañoF()
    {
        if(TFFechaF.getText().length() == 0 || TFFechaF.getText().length() == 3 || TFFechaF.getText().length() == 5)
        {
            ContadorTeclaF = 0;
        }
        if(TFFechaF.getText().length() == 1 || TFFechaF.getText().length() == 4)
        {
            ContadorTeclaF = 1;
        }
    }
    
    private void AgregarCeroF()
    {
        if(TFFechaF.getText().length() == 2)
        {
            TFFechaF.setText("0"+TFFechaF.getText());
            TFFechaF.end();
            ContadorTeclaF = 0;
        }
        else if(TFFechaF.getText().length() == 5)
        {
            TFFechaF.insertText(3, "0");
            TFFechaF.end();
            ContadorTeclaF = 0;
        }
        else if(TFFechaF.getText().length() < 3)
        {
            TFFechaF.deletePreviousChar();
        }
        else if(TFFechaF.getText().length() > 3 && TFFechaF.getText().length() < 6)
        {
            TFFechaF.deletePreviousChar();
        }
        else if(TFFechaF.getText().length() > 6)
        {
            TFFechaF.deletePreviousChar();
        }
    }
    
    private boolean ValidarFecha(String Fecha)
    {
        if(Validar.FormatoFecha(Fecha))
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    
    public void llenarBodega ()
    {         
         List<String> list = new ArrayList<String>();
         if (new IBodegaProductos().listaDeBodegasDeProductos()!= null)
         {
            for (BodegaProductos bodega:new IBodegaProductos().listaDeBodegasDeProductos())
            {
                list.add(bodega.getNombre());
            }
            ObservableList<String> observableList = FXCollections.observableList(list);
            ComBoBodega.setItems(observableList);
         }
    }
}

