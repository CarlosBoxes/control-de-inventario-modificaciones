/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ControladoresGUI;

import AdministradoresGUI.PedidoProveedoresAdministrador;
import EntidadesJPA.DescripcionPedidoProveedores;
import EntidadesJPA.PedidoProveedores;
import EntidadesJPA.Productos;
import EntidadesJPA.Proveedores;
import Especiales.GeneradordeReportes;
import Especiales.NuevoPedidoProveedores;
import Especiales.Validaciones;
import GestorDeTablasJPA.IDescripcionPedidoProveedores;
import GestorDeTablasJPA.IInventarioProducto;
import GestorDeTablasJPA.IPedidoProveedores;
import GestorDeTablasJPA.IProductos;
import GestorDeTablasJPA.IProveedores;
import Modelos.DescripcionCompraModelo;
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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;

/**
 *
 * @author luis__000
 */
public class PedidoProveedoresControlador 
{
    PedidoProveedoresAdministrador PedidoProveedoresAdministrador;
    @FXML
    public TableView TablaProductos;
    public TableColumn IdProducto;
    public TableColumn NombreProducto;
    public TableColumn CantidadProducto;
    public TableColumn Presentacion;
    public TableColumn UnidadMedida;
    public TableColumn PrecioCosto;
    public TableColumn PrecioVenta;
    public TableColumn FechaVencimiento; 
    public TableColumn Descripcion;
    public TableColumn Categoria;
    public TableView TablaDescripciones;
    public TableColumn Id;
    public TableColumn Nombre;
    public TableColumn Cantidad;
    public TableColumn Precio;
    public TableColumn SubTotal;
    
    
    public TextField TFBusqueda;
    public TextField TFIdProducto;
    public TextField TFCantidad;
    public TextField TFPrecio;
    public TextField TFTotal;
    public TextField TFSubTotal;
    public TextField TFDescuento;
    public TextField TFFecha;
    public TextField TFIdProveedor;
  
    
    public Label LblNombreProveedor;
    public Label LblNombreDeProducto;
    public VBox pnlBusqueda;
    private ObservableList<DescripcionCompraModelo> dataDescripciones;
    private ObservableList<ProductoModelo> dataProductos;
    
    private List<DescripcionPedidoProveedores> listaDeDescripcion;
    public IDescripcionPedidoProveedores descripcionGestor;
    public IPedidoProveedores pedidos;
    public IProductos productoGestor;
    public IProveedores proveedor;
    public IInventarioProducto inventarioGestor;
    public NuevoPedidoProveedores nuevoPedido;
    public PedidoProveedores pedido;
    private Validaciones Validar;
    private int ContadorTecla;
    
    public void initialize() {}
  
    public void initManager(final PedidoProveedoresAdministrador PedidoProveedoresAdministrador)
    {
        this.PedidoProveedoresAdministrador = PedidoProveedoresAdministrador;
        dataProductos = FXCollections.observableArrayList();
        dataDescripciones = FXCollections.observableArrayList();
        IdProducto = new TableColumn();
        NombreProducto = new TableColumn();
        CantidadProducto = new TableColumn();
        Presentacion = new TableColumn();
        UnidadMedida = new TableColumn();
        PrecioCosto = new TableColumn();
        PrecioVenta = new TableColumn();
        FechaVencimiento = new TableColumn();
        Descripcion = new TableColumn();
        Categoria = new TableColumn();
        CargarColumnasProducto();
        pnlBusqueda.setVisible(false);
        Id = new TableColumn();
        Nombre = new TableColumn();
        Cantidad = new TableColumn();
        Precio = new TableColumn();
        SubTotal = new TableColumn();
        
        // para ingresar nuevo pedido a proveedores
        proveedor = new IProveedores();
        productoGestor = new IProductos();
        pedidos = new IPedidoProveedores ();
        descripcionGestor = new IDescripcionPedidoProveedores();
        listaDeDescripcion = new ArrayList();
        pedido = new PedidoProveedores();
        inventarioGestor = new IInventarioProducto();
        nuevoPedido = new NuevoPedidoProveedores ();
        Validar = new Validaciones();
        //
        
        
        
        CargarColumnasDescripcion();
    }
    
     public void crearNuevoPedido(ActionEvent event)
    {
        
        if (this.dataDescripciones.isEmpty())
        {
            this.PedidoProveedoresAdministrador.showMensajes("Debe Crear Descripciones Para el Pedido");
        }
        
        else
        {
            try
            {    
                DateFormat df = DateFormat.getDateInstance(DateFormat.SHORT);      
                Date fecha = null;
                try {
                    fecha = df.parse(TFFecha.getText());
                } catch (ParseException ex) {
                    Logger.getLogger(NuevoProductoControlador.class.getName()).log(Level.SEVERE, null, ex);
                }
                float total = Float.parseFloat(TFTotal.getText());
                float subTotal =Float.parseFloat(TFSubTotal.getText());
                float descuento =Float.parseFloat(TFDescuento.getText())/100;
                Proveedores proveedorX  = proveedor.buscarProveedorPorId(Integer.parseInt(TFIdProveedor.getText()));
                nuevoPedido.crearNuevoPedidoSinDescripcion(fecha, false,"",total,proveedorX,descuento,subTotal);    
                pedido = pedidos.retornarUltimoIngresado();       
                this.convertirDeModeloAListaDescripciones();
                this.PedidoProveedoresAdministrador.showMensajes("Pedido Ingresado Correctamente");
                this.limpiarTodo();
                dataDescripciones.clear();
                TablaDescripciones.setItems(dataDescripciones);
                GeneradordeReportes Generar = new GeneradordeReportes();
                Generar.AbrirReporte("PedidoAProveedores.jasper", pedido.getIdpedidoProveedores());
            }
            catch (Exception e)
            {
                this.PedidoProveedoresAdministrador.showMensajes("Error al Ingresar el Pedido");
            }
        }
    }
     
     public void convertirDeModeloAListaDescripciones ()
    {
        DescripcionPedidoProveedores descripcion = new DescripcionPedidoProveedores();
        for (DescripcionCompraModelo modelo:this.dataDescripciones)
        {            
            this.descripcionGestor.guardar(Math.round(modelo.getCantidad()),modelo.getPrecio(),productoGestor.buscarProductoPorId(modelo.getIdProducto()),this.pedido);
            descripcion = this.descripcionGestor.retornarUltimoIngresado();
            this.listaDeDescripcion.add(descripcion);
        }  
    }
   
      public void limpiarDescripcion ()
      {
          TFIdProducto.setText("");
          TFIdProducto.requestFocus();
          TFCantidad.setText("");
          TFPrecio.setText("");
      }
      
      public void limpiarTodo()
      {
          TFFecha.setText("");
          ContadorTecla = 0;
          TFIdProveedor.setText("");
          LblNombreProveedor.setText("");
          TFIdProducto.setText("");
          TFIdProducto.requestFocus();
          TFCantidad.setText("");
          TFFecha.requestFocus();
          TFTotal.setText("0.00");
          TFPrecio.setText("0.00");
      }
      
      public void cancelar (ActionEvent e)
      {
          this.limpiarTodo();
          dataDescripciones.clear();
          TablaDescripciones.setItems(dataDescripciones);
      }
      
      
      
      public void mostrarProveedor(ActionEvent e)
    {       
            String nombreProveedor;
            if (!this.Validar.ValidarNumeros(this.TFIdProveedor.getText()))
            {
                this.PedidoProveedoresAdministrador.showMensajes("Verifique El Id. Del Proveedor");
                this.TFIdProveedor.requestFocus();
            }
            else
            if (this.proveedor.buscarProveedorPorId(Integer.parseInt(TFIdProveedor.getText()))==null)
            {
                this.PedidoProveedoresAdministrador.showMensajes("Proveedor no Encontrado");
                TFIdProveedor.requestFocus();
            }
            else
            {
                nombreProveedor=this.proveedor.buscarProveedorPorId(Integer.parseInt(TFIdProveedor.getText())).getNombre();
                LblNombreProveedor.setText(nombreProveedor);
                TFIdProducto.requestFocus();
            }
    }
    
      public void irAPrecio (ActionEvent e)
      {
          TFPrecio.requestFocus();
      }
    private void CargarColumnasProducto() 
    {
        IdProducto.setCellValueFactory(new PropertyValueFactory<ProductoModelo,Integer>("Id"));
        NombreProducto.setCellValueFactory(new PropertyValueFactory<ProductoModelo,String>("Nombre"));
        CantidadProducto.setCellValueFactory(new PropertyValueFactory<ProductoModelo,Integer>("Cantidad"));
        Presentacion.setCellValueFactory(new PropertyValueFactory<ProductoModelo,String>("Presentacion")); 
        UnidadMedida.setCellValueFactory(new PropertyValueFactory<ProductoModelo,String>("UnidadMedida")); 
        PrecioCosto.setCellValueFactory(new PropertyValueFactory<ProductoModelo,String>("PrecioCosto")); 
        PrecioVenta.setCellValueFactory(new PropertyValueFactory<ProductoModelo,String>("PrecioVenta"));
        FechaVencimiento.setCellValueFactory(new PropertyValueFactory<ProductoModelo,String>("FechaVencimiento"));
        Descripcion.setCellValueFactory(new PropertyValueFactory<ProductoModelo,String>("Descripcion"));
        Categoria.setCellValueFactory(new PropertyValueFactory<ProductoModelo,String>("Categoria"));
        IdProducto.setText("Id");
        NombreProducto.setText("Nombre");
        CantidadProducto.setText("Cantidad");
        Presentacion.setText("Presentación");
        UnidadMedida.setText("Unidad de Medida");
        PrecioCosto.setText("Precio Costo");
        PrecioVenta.setText("Preico Venta");
        FechaVencimiento.setText("Fecha de Vencimiento");
        Descripcion.setText("Descripcion");
        Categoria.setText("Categoria");
        IdProducto.setPrefWidth(50);
        NombreProducto.setPrefWidth(200);
        CantidadProducto.setPrefWidth(100);
        Presentacion.setPrefWidth(100);
        UnidadMedida.setPrefWidth(110);
        PrecioCosto.setPrefWidth(100);
        PrecioVenta.setPrefWidth(100);
        FechaVencimiento.setPrefWidth(100);
        Descripcion.setPrefWidth(300);
        Categoria.setPrefWidth(100);
        TablaProductos.getColumns().add(IdProducto);
        TablaProductos.getColumns().add(NombreProducto);
        TablaProductos.getColumns().add(CantidadProducto);
        TablaProductos.getColumns().add(Presentacion);
        TablaProductos.getColumns().add(UnidadMedida);
        TablaProductos.getColumns().add(PrecioCosto);
        TablaProductos.getColumns().add(PrecioVenta);
        TablaProductos.getColumns().add(FechaVencimiento);
        TablaProductos.getColumns().add(Categoria);
        TablaProductos.getColumns().add(Descripcion);
    }
    
    private void CargarColumnasDescripcion() 
    {
        Id.setCellValueFactory(new PropertyValueFactory<ProductoModelo,Integer>("IdProducto"));
        Nombre.setCellValueFactory(new PropertyValueFactory<ProductoModelo,String>("Nombre"));
        Cantidad.setCellValueFactory(new PropertyValueFactory<ProductoModelo,Integer>("Cantidad")); 
        Precio.setCellValueFactory(new PropertyValueFactory<ProductoModelo,Float>("Precio"));
        SubTotal.setCellValueFactory(new PropertyValueFactory<ProductoModelo,Float>("SubTotal"));
        Id.setText("Id");
        Nombre.setText("Nombre");
        Cantidad.setText("Cantidad");
        Precio.setText("Precio");
        SubTotal.setText("SubTotal");
        Id.setPrefWidth(50);
        Nombre.setPrefWidth(200);
        Cantidad.setPrefWidth(100);
        Precio.setPrefWidth(110);
        SubTotal.setPrefWidth(110);
        TablaDescripciones.getColumns().add(Id);
        TablaDescripciones.getColumns().add(Nombre);
        TablaDescripciones.getColumns().add(Cantidad);
        TablaDescripciones.getColumns().add(Precio);
        TablaDescripciones.getColumns().add(SubTotal);
    }
    
    public void LLenarTablaProductos()
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
                    dataProductos.add(ProductoModelo);
                }
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
                    dataProductos.add(ProductoModelo);
                }
            }
            
        }
    }
    
    private void LLenarTablaDescripcion()
    {
        int id = Integer.parseInt(TFIdProducto.getText());
        Productos Producto = new IProductos().buscarProductoPorId(id);
        float cantidad = Float.parseFloat(TFCantidad.getText());
        float subtotal = Float.parseFloat(TFPrecio.getText()) * cantidad;
        DescripcionCompraModelo DescripcionModelo = new DescripcionCompraModelo(Producto.getIdProductos(), Producto.getNombre(), cantidad, Float.parseFloat(TFPrecio.getText()), subtotal);
        dataDescripciones.add(DescripcionModelo);
        this.calculoDeTotales();
    }
    
    public void Buscar(ActionEvent event)
    {
        dataProductos.clear();
        TablaProductos.setItems(dataProductos);
        LLenarTablaProductos();
        TablaProductos.setItems(dataProductos);
    }
    
    public void AgregarId(ActionEvent event)
    {
        ProductoModelo Seleccionado = (ProductoModelo)TablaProductos.getSelectionModel().getSelectedItem();
        if(Seleccionado == null)
        {
            this.PedidoProveedoresAdministrador.showMensajes("Debe Seleccionar un Dato");
        }
        else
        {
            this.TFIdProducto.setText(String.valueOf(Seleccionado.getId()));
        }
    }
    
    public void AgregarProducto(ActionEvent event)
    {
        if (this.verificarCampoVacios())
        {
            this.PedidoProveedoresAdministrador.showMensajes("Rellene Todos Los Campos");
        }
        else        
        if (!this.Validar.ValidarNumeros(this.TFIdProducto.getText()))
        {
            this.PedidoProveedoresAdministrador.showMensajes("Verifique El Id. Del Producto");
            this.TFIdProducto.requestFocus();
        }
        else
        if (!this.Validar.ValidarMontos(this.TFCantidad.getText()))
        {
            this.PedidoProveedoresAdministrador.showMensajes("Verifique la Cantidad Del Producto");
            this.TFCantidad.requestFocus();
        }
        else
        if (!this.Validar.ValidarMontos(this.TFPrecio.getText()))
        {
            this.PedidoProveedoresAdministrador.showMensajes("Verifique el Precio del Producto");
            this.TFPrecio.requestFocus();
        }
        else
        {
            LLenarTablaDescripcion();
            TablaDescripciones.setItems(dataDescripciones);
        }
    }
    
    public void SacarProducto(ActionEvent event)
    {
        DescripcionCompraModelo Seleccionado = (DescripcionCompraModelo)TablaDescripciones.getSelectionModel().getSelectedItem();
        int indice = TablaDescripciones.getSelectionModel().getSelectedIndex();
        if(Seleccionado == null)
        {
            this.PedidoProveedoresAdministrador.showMensajes("Debe Seleccionar un Dato");
        }
        else
        {
            dataDescripciones.remove(indice);
            TablaDescripciones.setItems(dataDescripciones);
            this.calculoDeTotales();
        }
    }
    
    public void HabilitarBusqueda(ActionEvent event)
    {
        pnlBusqueda.setVisible(true);
    }
    
    public void DeshabilitarBusqueda(ActionEvent event)
    {
        pnlBusqueda.setVisible(false);
    }
    
      public void calculoDeTotales ()
    {
        float total =0;
        for (DescripcionCompraModelo modelo:this.dataDescripciones)
        {            
            total = total + modelo.getSubTotal();
          
        }
        TFSubTotal.setText(String.valueOf(total));
        TFTotal.setText(String.valueOf(total));
    }
      
    public void AplicarDescuento(ActionEvent event)
    {
        
        float total =0;
        float subTotal =0;
        float descuento = 0;
        for (DescripcionCompraModelo modelo:this.dataDescripciones)
        {            
            subTotal = subTotal + modelo.getSubTotal();
            
        }
        descuento = Float.parseFloat(TFDescuento.getText())/100;
        total = subTotal - subTotal*descuento;
        TFTotal.setText(String.valueOf(total));
    }
      
     public void mostrarProducto (ActionEvent e)
    {
        if(ValidarNumero(TFIdProducto.getText()))
        {
            if ((this.productoGestor.buscarProductoPorId(Integer.parseInt(TFIdProducto.getText()))==null)||(this.productoGestor.buscarProductoPorId(Integer.parseInt(TFIdProducto.getText())).getEliminado()))
            {
                this.PedidoProveedoresAdministrador.showMensajes("Producto no Encontrado");
                TFIdProducto.requestFocus();
            }
            else
            {
                LblNombreDeProducto.setText(this.productoGestor.buscarProductoPorId(Integer.parseInt(TFIdProducto.getText())).getNombre());
                TFCantidad.requestFocus();
            } 
        }
        else
        {
            this.PedidoProveedoresAdministrador.showMensajes("Id del Producto no Valido");
        }
        
        
    }
     private boolean ValidarNumero(String Numero)
    {
        
        if(Validar.ValidarNumeros(Numero))
        {
            return true;
        }
        else
        {
            return false;
        }
    }
     
     private boolean verificarCampoVacios ()
     {
         if ((this.TFIdProveedor.getText().isEmpty())||(this.IdProducto.getText().isEmpty())||(this.TFCantidad.getText().isEmpty())||(this.TFPrecio.getText().isEmpty()))
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
        boolean Valido = Validar.ValidarNumero(Tecla);
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
    
    
}

