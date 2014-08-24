/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ControladoresGUI;

import AdministradoresGUI.PedidoNuevoAdministrador;
import EntidadesJPA.DescripcionPedido;
import EntidadesJPA.Pedido;
import EntidadesJPA.Productos;
import EntidadesJPA.ProductosVendedores;
import EntidadesJPA.Vendedores;
import Especiales.GeneradordeReportes;
import Especiales.NuevoPedido;
import Especiales.Validaciones;
import GestorDeTablasJPA.IDescripcionPedido;
import GestorDeTablasJPA.IInventarioProducto;
import GestorDeTablasJPA.IPedido;
import GestorDeTablasJPA.IProductos;
import GestorDeTablasJPA.IProductosVendedor;
import GestorDeTablasJPA.IVendedores;
import Modelos.DescripcionModelo;
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
import javafx.scene.control.SplitPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/** 
 *
 * @author luis__000
 */
public class PedidoNuevoControlador 
{
    PedidoNuevoAdministrador PedidoNuevoAdministrador;
    @FXML
    public VBox pnlBusqueda;
    public HBox HBoxProducto;
    public VBox VBoxTabla;
    public SplitPane SPTotales;
    public TextField TFFecha;
    public TextField TFIdVendedor;
    public TextField TFIdProducto;
    public TextField TFCantidad;
    public TextField TFSubTotal;
    public TextField TFTotal;
    public TextArea TAObservaciones;
    public Label LblNombreVendedor;
    public Label LblNombreDeProducto;
    public TableView TablaDescripciones;
    public TableColumn Id;
    public TableColumn Nombre;
    public TableColumn Cantidad;
    public TableColumn Precio;
    public TableColumn SubTotal;
    private ObservableList<DescripcionModelo> data;
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
    public TextField TFBusqueda;
    private ObservableList<ProductoModelo> dataProductos;
    private List<DescripcionPedido> listaDeDescripcion;
    public IDescripcionPedido descripcionGestor;
    public IPedido pedidos;
    public IProductos productoGestor;
    public IVendedores vendedor;
    private Vendedores vendedorPedido;
    private IProductosVendedor gestorDeProductosVendedor;
    public IInventarioProducto inventarioGestor;
    public NuevoPedido nuevoPedido;
    public Pedido pedido;
    private int ContadorTecla;
    private Validaciones Validar;
   
    public void initialize() {}
    
    public void initManager(final PedidoNuevoAdministrador PedidoNuevoAdministrador)
    {
        this.PedidoNuevoAdministrador = PedidoNuevoAdministrador;       
        Id = new TableColumn();
        Nombre = new TableColumn();
        Cantidad = new TableColumn();
        SubTotal = new TableColumn();
        Precio = new TableColumn();
        nuevoPedido = new NuevoPedido();
        data = FXCollections.observableArrayList();
        TablaDescripciones.setEditable(true);
        Cantidad.setEditable(true);
        CargarColumnasPedido();
        dataProductos = FXCollections.observableArrayList();
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
        // controladores para generar pedidos
        vendedor = new IVendedores();
        productoGestor = new IProductos();
        pedidos = new IPedido ();
        descripcionGestor = new IDescripcionPedido();
        listaDeDescripcion = new ArrayList();
        pedido = new Pedido();
        vendedorPedido = new Vendedores ();
        gestorDeProductosVendedor = new IProductosVendedor();
        //fin de controladores
        inventarioGestor = new IInventarioProducto();
        TFSubTotal.setText("0.00");
        TFTotal.setText("0.00");
        pnlBusqueda.setVisible(false);
        
        //Validadores de Fecha
        ContadorTecla = 0;
        Validar = new Validaciones();
    }
    
    public void HabilitarBusqueda(ActionEvent event)
    {
        pnlBusqueda.setVisible(true);
    }
    
    public void DeshabilitarBusqueda(ActionEvent event)
    {
        pnlBusqueda.setVisible(false);
    }
    
    public void crearNuevoPedido(ActionEvent event)
    {
        if(ValidarFecha())
        {
            
            String fechaIN = TFFecha.getText();
            DateFormat Formato = DateFormat.getDateInstance(DateFormat.SHORT);      
            Date Fecha = null;
            try 
            {
                Fecha = Formato.parse(fechaIN);
            } 
            catch (ParseException ex) 
            {
                Logger.getLogger(NuevoProductoControlador.class.getName()).log(Level.SEVERE, null, ex);
            } 
            if (!this.verificarCamposVaciosParaPedido())
            {
                nuevoPedido.crearNuevoPedidoSinDescripcion(Fecha, false,Float.parseFloat(TFSubTotal.getText()),Float.parseFloat(TFTotal.getText()),vendedor.buscarVendedorPorId(Integer.parseInt(TFIdVendedor.getText())),TAObservaciones.getText());    
                pedido = pedidos.retornarUltimoPedidoIngresado();
                this.convertirDeModeloAListaDescripciones();  
                this.sacarDeInventario();
                this.PedidoNuevoAdministrador.showMensajes("Pedido Ingresado Correctamente");
                this.limpiarTodo();
                this.DeshabilitarVentanas();
                data.clear();
                TablaDescripciones.setItems(data);
                GeneradordeReportes Generar = new GeneradordeReportes();
                Generar.AbrirReporte("Pedido.jasper", pedido.getIdpedido());
            }
            else
            {
                this.PedidoNuevoAdministrador.showMensajes("Verifique los campos de: fecha, id. Vendedor o Verifique las Descripciones");
            }
        }
        else if(!ValidarFecha())
        {
            this.PedidoNuevoAdministrador.showMensajes("Fecha");
        }
        else if(!ValidarFecha())
        {
            this.PedidoNuevoAdministrador.showMensajes("Fecha no Valida");
        }       
        
    }
    private void CargarColumnasPedido() 
    {
        Id.setCellValueFactory(new PropertyValueFactory<DescripcionModelo,Integer>("IdProducto"));
        Nombre.setCellValueFactory(new PropertyValueFactory<DescripcionModelo,String>("Nombre"));
        Cantidad.setCellValueFactory(new PropertyValueFactory<DescripcionModelo,Integer>("Cantidad"));
        Precio.setCellValueFactory(new PropertyValueFactory<DescripcionModelo,Float>("Precio"));
        SubTotal.setCellValueFactory(new PropertyValueFactory<DescripcionModelo,Float>("SubTotal"));
        Id.setText("Id");
        Nombre.setText("Nombre");
        Cantidad.setText("Cantidad");
        Precio.setText("Precio");
        SubTotal.setText("SubTotal");
        Id.setPrefWidth(50);
        Nombre.setPrefWidth(350);
        Cantidad.setPrefWidth(100);
        Precio.setPrefWidth(150);
        SubTotal.setPrefWidth(150);
        TablaDescripciones.getColumns().add(Id);
        TablaDescripciones.getColumns().add(Nombre);
        TablaDescripciones.getColumns().add(Cantidad);
        TablaDescripciones.getColumns().add(Precio);
        TablaDescripciones.getColumns().add(SubTotal);
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
        Cantidad.setPrefWidth(100);
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
    
    public void LLenarTablaProductos()
    {
        if("".equals(TFBusqueda.getText()))
        {
            
            IProductos busqueda = new IProductos();
            List<Productos> Producto = busqueda.listaDeProductos();
            if(Producto != null)
            {
                for(Productos Lista:Producto)
                {
                    float cantidad = new IInventarioProducto().buscarInventarioPorProducto(Lista).getCantidad();
                    SimpleDateFormat Formato = new java.text.SimpleDateFormat("dd/MM/yyyy");
                    String Fecha = Formato.format(Lista.getFechaDeVencimiento());
                    ProductoModelo ProductoModelo = new ProductoModelo(Lista.getIdProductos(), Lista.getNombre(), cantidad, Lista.getPresentacion(), Lista.getUnidadDeMedida(), Lista.getPrecioCosto(), Lista.getPrecioVenta(), Fecha, Lista.getDescripcion(), Lista.getCategoria());
                    dataProductos.add(ProductoModelo);
                }
            }
        }
        else
        {
            IProductos busqueda = new IProductos();
            List<Productos> Producto = busqueda.buscarListaProductoPorNombre(TFBusqueda.getText());
            if(Producto != null)
            {
                for(Productos Lista:Producto)
                {
                    float cantidad = new IInventarioProducto().buscarInventarioPorProducto(Lista).getCantidad();
                    SimpleDateFormat Formato = new java.text.SimpleDateFormat("dd/MM/yyyy");
                    String Fecha = Formato.format(Lista.getFechaDeVencimiento());
                    ProductoModelo ProductoModelo = new ProductoModelo(Lista.getIdProductos(), Lista.getNombre(),cantidad, Lista.getPresentacion(), Lista.getUnidadDeMedida(), Lista.getPrecioCosto(), Lista.getPrecioVenta(), Fecha, Lista.getDescripcion(), Lista.getCategoria());
                    dataProductos.add(ProductoModelo);
                }
            }
        }
    }
    
    private void LLenarTablaPedido()
    {
        if(ValidarNumeroDecimales(TFCantidad.getText()))
        {
            IProductos busqueda = new IProductos();
            int id = Integer.parseInt(TFIdProducto.getText()); 
            Productos Producto = busqueda.buscarProductoPorId(id);
             if (Producto.getEliminado())
            {
                this.PedidoNuevoAdministrador.showMensajes("Este producto NO existe");
            }
            else
            {
               float cantidad = Float.parseFloat(TFCantidad.getText());
               float precio = this.PrecioDelProducto(id);
               float subtotal = (precio * cantidad);
               DescripcionModelo DescripcionModelo = new DescripcionModelo(Producto.getIdProductos(), Producto.getNombre(),cantidad,precio,subtotal);
               data.add(DescripcionModelo);
               this.calculoDeTotales();
             }
        }
        else if(!ValidarNumeroDecimales(TFCantidad.getText()))
        {
            this.PedidoNuevoAdministrador.showMensajes("Cantidad del Producto no Validos");
        }
        else if(!ValidarNumeroDecimales(TFCantidad.getText()))
        {
            this.PedidoNuevoAdministrador.showMensajes("Cantidad no Valida");
        }
    }
    
    public void Buscar(ActionEvent event)
    {
        dataProductos.clear();
        TablaProductos.setItems(dataProductos);
        LLenarTablaProductos();
        TablaProductos.setItems(dataProductos);
    }
    
    public void AgregarProducto(ActionEvent event)
    {
         if (this.verificarDescripcionYaIngresada(Integer.parseInt(this.TFIdProducto.getText())))
        {
            this.PedidoNuevoAdministrador.showMensajes("Producto ya ingresado, Modifique el que ya Ingreso");
        }  
        else                
        {
        LLenarTablaPedido();
        TablaDescripciones.setItems(data);
        this.limpiarDescripcion();
        }
    }
    
    public void AgregarId(ActionEvent event)
    {
        ProductoModelo Seleccionado = (ProductoModelo)TablaProductos.getSelectionModel().getSelectedItem();
        if(Seleccionado == null)
        {
            this.PedidoNuevoAdministrador.showMensajes("Debe Seleccionar un Dato");
        }
        else
        {
            this.TFIdProducto.setText(String.valueOf(Seleccionado.getId()));
        }
    }
    
    public void SacarProducto(ActionEvent event)
    {
        DescripcionModelo Seleccionado = (DescripcionModelo)TablaDescripciones.getSelectionModel().getSelectedItem();
        int indice = TablaDescripciones.getSelectionModel().getSelectedIndex();
        if(Seleccionado == null)
        {
            this.PedidoNuevoAdministrador.showMensajes("Debe Seleccionar un Dato");
        }
        else
        {
            data.remove(indice);
            TablaDescripciones.setItems(data);
            this.calculoDeTotales();
        }
    }

    public void convertirDeModeloAListaDescripciones ()
    {
        this.listaDeDescripcion.clear();
        for (DescripcionModelo modelo:this.data)
        {            
            this.descripcionGestor.guardar(modelo.getCantidad(),modelo.getSubTotal(),productoGestor.buscarProductoPorId(modelo.getIdProducto()),this.pedido,modelo.getPrecio());
            DescripcionPedido descripcion = this.descripcionGestor.retornarUltimoIngresado();
            this.listaDeDescripcion.add(descripcion);
        }  
    }
   
    public void mostrarVendedor(ActionEvent e)
    {
        if (this.TFFecha.getText().isEmpty())
        {
            this.PedidoNuevoAdministrador.showMensajes("Ingrese Una Fecha Para Poder Iniciar el Pedido");
        }
        else
        if(ValidarNumero(TFIdVendedor.getText()))
        {
            this.vendedorPedido =this.vendedor.buscarVendedorPorId(Integer.parseInt(TFIdVendedor.getText()));
            String nombreVendedor;
            String apellidoVendedor;
           
            if (this.vendedorPedido==null)
            {
                this.PedidoNuevoAdministrador.showMensajes("Vendedor no Encontrado");
                this.DeshabilitarVentanas();
            }
            else
            {
                if (!this.vendedorPedido.getListaclientesCollection().isEmpty())
                {
                    this.PedidoNuevoAdministrador.showMensajes("Este Vendedor Contiene Una Lista de Clientes,Dirijase al Pedido Especial. ");
                }
                else
                if (this.vendedorPedido.getTipoVendedoresidTipoVendedores().getListaproductos())
                {
                     this.PedidoNuevoAdministrador.showMensajes("Este Vendedor Contiene Una Lista de Productos con Descuento Se Aplicaran Los Precios ");
                }
                nombreVendedor=this.vendedorPedido.getNombre();
                apellidoVendedor=this.vendedorPedido.getApellido();                
                LblNombreVendedor.setText(nombreVendedor+" "+apellidoVendedor);
                TFIdProducto.requestFocus();
                this.HabilitarVentanas();
            }
        }
        else
        {
            this.PedidoNuevoAdministrador.showMensajes("Id del Vendedor no Valido");
        }
    }
    
    public void mostrarProducto (ActionEvent e)
    {
        if(ValidarNumero(TFIdProducto.getText()))
        {
            if ((this.productoGestor.buscarProductoPorId(Integer.parseInt(TFIdProducto.getText()))==null)||(this.productoGestor.buscarProductoPorId(Integer.parseInt(TFIdProducto.getText())).getEliminado()))
            {
                this.PedidoNuevoAdministrador.showMensajes("Producto no Encontrado");
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
            this.PedidoNuevoAdministrador.showMensajes("Id del Producto no Valido");
        }
        
        
    }
    
    public void sacarDeInventario ()
    {
        for (DescripcionPedido descrip:this.listaDeDescripcion)
        {
            inventarioGestor.sacarDeInventario(descrip.getProductosidProductos(), descrip.getCantidad());
        }
    }
    
    public void comprobarInventarioProducto(ActionEvent e)
    {
        if(ValidarNumeroDecimales(TFCantidad.getText()))
        {
            if (!inventarioGestor.verificarInventario(this.productoGestor.buscarProductoPorId(Integer.parseInt(TFIdProducto.getText())), Float.parseFloat(TFCantidad.getText())))
            {
                this.PedidoNuevoAdministrador.showMensajes("No Existe Suficiente Producto en Bodega");
                TFCantidad.requestFocus();
            }
            else
            {
                this.AgregarProducto(null);
            }
                  
        }
        else
        {
            this.PedidoNuevoAdministrador.showMensajes("Cantidad no Valida");
        }
    }
    
      public void calculoDeTotales ()
    {
        float subTotal = 0;
        float total;
        for (DescripcionModelo modelo:this.data)
        {            
            subTotal = subTotal + modelo.getSubTotal();          
        }
        TFSubTotal.setText(String.valueOf(subTotal));
        total = subTotal;
        TFTotal.setText(String.valueOf(total));    
    }
      
    

    public void limpiarDescripcion ()
    {
        TFIdProducto.setText("");
        TFIdProducto.requestFocus();
        TFCantidad.setText("");
    }

    public void limpiarTodo()
    {
        TFIdVendedor.setText("");
        LblNombreVendedor.setText("");
        TFIdProducto.setText("");
        TFIdProducto.requestFocus();
        TFCantidad.setText("");
        LblNombreDeProducto.setText("");
        TFFecha.requestFocus();
        TFTotal.setText("0.00");
        TFSubTotal.setText("0.00");
        TAObservaciones.setText("");
        data.clear();
        TablaDescripciones.setItems(data);
    }

    public void cancelar (ActionEvent e)
    {
        this.limpiarTodo();
        data.clear();
        TablaDescripciones.setItems(data);
        this.DeshabilitarVentanas();
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
    
    private boolean ValidarFecha()
    {
        if(Validar.FormatoFecha(TFFecha.getText()))
        {
            return true;
        }
        else
        {
            return false;
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
     private boolean ValidarNumeroDecimales(String Numero)
    {
        
        if(Validar.ValidarMontos(Numero))
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    
    private boolean verificarCamposVaciosParaPedido ()
    {
        if ((this.TFIdVendedor.getText().isEmpty())||(this.TFFecha.getText().isEmpty())||(this.data.isEmpty()))
        {
            return true;
        }
    else
        {
        return false;
        }
    }
    
    public boolean verificarDescripcionYaIngresada(int a)            
    {
        for (DescripcionModelo ProductoModelo: this.data)
        {
            if (ProductoModelo.getIdProducto()==a)
            {
                return true;
            }
        }
        return false;
    }
    
    private void HabilitarVentanas ()
    {
        this.HBoxProducto.setDisable(false);
        this.VBoxTabla.setDisable(false);
        this.SPTotales.setDisable(false);
    }
    
     private void DeshabilitarVentanas ()
    {
        this.HBoxProducto.setDisable(true);
        this.VBoxTabla.setDisable(true);
        this.SPTotales.setDisable(true);
    }
    
    private float PrecioDelProducto (int idProducto)
    {   
        float precio =0;
        if(!this.vendedorPedido.getTipoVendedoresidTipoVendedores().getProductosvendedoresCollection().isEmpty())
        {
        for(ProductosVendedores productoVendedor:this.gestorDeProductosVendedor.listaDeProductosPorIdTipo(this.vendedorPedido.getTipoVendedoresidTipoVendedores()))
        {
            if (productoVendedor.getProductosidProductos().getIdProductos()==idProducto)
            {
                precio = productoVendedor.getPrecioespecial();
                return precio;
            }
        }
        }
        precio = this.productoGestor.buscarProductoPorId(idProducto).getPrecioVenta();
        return precio;
    }
}

