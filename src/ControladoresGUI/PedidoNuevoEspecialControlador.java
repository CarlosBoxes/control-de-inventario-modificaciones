/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ControladoresGUI;

import AdministradoresGUI.PedidoNuevoEspecialAdministrador;
import EntidadesJPA.Clientes;
import EntidadesJPA.DescripcionPedido;
import EntidadesJPA.ListaClientes;
import EntidadesJPA.Pedido;
import EntidadesJPA.Productos;
import EntidadesJPA.ProductosClientes;
import EntidadesJPA.Vendedores;
import Especiales.GeneradordeReportes;
import Especiales.NuevoPedido;
import Especiales.NuevoPedidoAClientes;
import Especiales.Validaciones;
import GestorDeTablasJPA.IClientes;
import GestorDeTablasJPA.IDescripcionPedido;
import GestorDeTablasJPA.IInventarioProducto;
import GestorDeTablasJPA.IPedido;
import GestorDeTablasJPA.IProductos;
import GestorDeTablasJPA.IProductosCliente;
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
import javafx.scene.control.ComboBox;
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
public class PedidoNuevoEspecialControlador 
{
    PedidoNuevoEspecialAdministrador PedidoNuevoAdministrador;
    @FXML
    public VBox pnlBusqueda;
    public HBox HBoxProducto;
    public HBox HBoxClientes;
    public VBox VBoxTabla;
    public SplitPane SPTotales;
    public TextField TFFecha;
    public TextField TFIdVendedor;
    public TextField TFIdProducto;
    public TextField TFCantidad;
    public TextField TFSubTotal;
    public TextField TFTotalCliente;
    public TextField TFTotal;
    public TextArea TAObservaciones;
    public Label LblNombreVendedor;
    public Label LblNombreDeProducto;
    public TableView TablaDescripciones;
    public TableView TablaResumen;
    public TableColumn Id;
    public TableColumn Nombre;
    public TableColumn Cantidad;
    public TableColumn Precio;
    public TableColumn SubTotal;
    
    public TableColumn Id2;
    public TableColumn Nombre2;
    public TableColumn Cantidad2;
    public TableColumn Precio2;
    public TableColumn SubTotal2;
    
    private ObservableList<DescripcionModelo> data;
    private ObservableList<DescripcionModelo> data2;
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
    private IProductosCliente gestorDeProductosCliente;
    public IInventarioProducto inventarioGestor;
    public NuevoPedido nuevoPedido;
    public Pedido pedido;
    private int ContadorTecla;
    private Validaciones Validar;
    
    public ComboBox ComBoClientes;
   
    public void initialize() {}
    
    public void initManager(final PedidoNuevoEspecialAdministrador PedidoNuevoAdministrador)
    {
        this.PedidoNuevoAdministrador = PedidoNuevoAdministrador;       
        Id = new TableColumn();
        Nombre = new TableColumn();
        Cantidad = new TableColumn();
        SubTotal = new TableColumn();
        Precio = new TableColumn();
        
        Id2 = new TableColumn();
        Nombre2 = new TableColumn();
        Cantidad2 = new TableColumn();
        SubTotal2 = new TableColumn();
        Precio2 = new TableColumn();
        
        nuevoPedido = new NuevoPedido();
        
        data = FXCollections.observableArrayList();
        data2 = FXCollections.observableArrayList();
        
        TablaDescripciones.setEditable(true);
        TablaResumen.setEditable(true);
        Cantidad.setEditable(true);
        CargarColumnasPedido() ;
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
        // controladores para generar pedidos
        vendedor = new IVendedores();
        productoGestor = new IProductos();
        pedidos = new IPedido ();
        descripcionGestor = new IDescripcionPedido();
        listaDeDescripcion = new ArrayList();
        pedido = new Pedido();
        vendedorPedido = new Vendedores ();
        gestorDeProductosCliente = new IProductosCliente();
        clienteCambiado = new Clientes();
        //fin de controladores
        inventarioGestor = new IInventarioProducto();
        TFSubTotal.setText("0.00");
        TFTotal.setText("0.00");
        
        //Validadores de Fecha
        ContadorTecla = 0;
        Validar = new Validaciones();
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
        
        
        
        Id2.setCellValueFactory(new PropertyValueFactory<DescripcionModelo,Integer>("IdProducto"));
        Nombre2.setCellValueFactory(new PropertyValueFactory<DescripcionModelo,String>("Nombre"));
        Cantidad2.setCellValueFactory(new PropertyValueFactory<DescripcionModelo,Integer>("Cantidad"));
        Precio2.setCellValueFactory(new PropertyValueFactory<DescripcionModelo,Float>("Precio"));
        SubTotal2.setCellValueFactory(new PropertyValueFactory<DescripcionModelo,Float>("SubTotal"));
        Id2.setText("Id");
        Nombre2.setText("Nombre");
        Cantidad2.setText("Cantidad");
        Precio2.setText("Precio");
        SubTotal2.setText("SubTotal");
        Id2.setPrefWidth(50);
        Nombre2.setPrefWidth(350);
        Cantidad2.setPrefWidth(100);
        Precio2.setPrefWidth(150);
        SubTotal2.setPrefWidth(150);
        TablaResumen.getColumns().add(Id2);
        TablaResumen.getColumns().add(Nombre2);
        TablaResumen.getColumns().add(Cantidad2);
        TablaResumen.getColumns().add(Precio2);
        TablaResumen.getColumns().add(SubTotal2);
        

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
            if (!this.verificarCamposVaciosParaPedido())
            {
                String mensaje = this.pedidoEspecial.guardarDescripciones(this.TAObservaciones.getText());
                this.PedidoNuevoAdministrador.showMensajes(mensaje);
                this.pedido = this.pedidoEspecial.getPedido();
                this.limpiarTodo();
                data.clear();
                this.calculoDeTotalCliente();
                this.DeshabilitarVentanas();
                TablaDescripciones.setItems(data);
                GeneradordeReportes Generar = new GeneradordeReportes();
                Generar.AbrirReporte("PedidoEspecial.jasper", pedido.getIdpedido());
            }
            else
            {
                this.PedidoNuevoAdministrador.showMensajes("Verifique los campos de: fecha, id. Vendedor o Verifique las Descripciones");
            }
        }
        else if(!ValidarFecha() )
        {
            this.PedidoNuevoAdministrador.showMensajes("Fecha no Valida");
        }
        else if(!ValidarFecha())
        {
            this.PedidoNuevoAdministrador.showMensajes("Fecha no Valida");
        }        
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
                    int cantidad = new IInventarioProducto().buscarInventarioPorProducto(Lista).getCantidad();
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
                    int cantidad = new IInventarioProducto().buscarInventarioPorProducto(Lista).getCantidad();
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
            int cantidad = Integer.parseInt(TFCantidad.getText());
            if (Producto.getEliminado())
            {
                this.PedidoNuevoAdministrador.showMensajes("Este producto NO existe");
            }
            else
            if(!this.inventarioGestor.verificarInventario(Producto,cantidad))
            {
                this.PedidoNuevoAdministrador.showMensajes("No Existe suficiente Producto en Bodega");
            }
            else
            {
                float precio = this.PrecioDelProducto(id);
                float subtotal = (precio * cantidad);
                DescripcionModelo DescripcionModelo = new DescripcionModelo(Producto.getIdProductos(), Producto.getNombre(),cantidad,precio,subtotal);
                data.add(DescripcionModelo);
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
        calculoDeTotalCliente ();
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
            this.pedidoEspecial.borrarDescripcionExistente(Seleccionado, clienteCambiado);
            data.remove(indice);
            TablaDescripciones.setItems(data);
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
                 if (!this.vendedorPedido.getTipoVendedoresidTipoVendedores().getListaproductos() && !this.vendedorPedido.getListaclientesCollection().isEmpty())
                    {
                        if (this.TFFecha.getText().isEmpty())
                        {
                            this.PedidoNuevoAdministrador.showMensajes("Ingrese Una Fecha para poder Iniciar El Pedido");
                        }
                        else
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
                            this.PedidoNuevoAdministrador.showMensajes("Este Vendedor Contiene Una Lista de Clientes Asignados, se Aplicaran Precios Especiales ");
                            this.HabilitarVentanas();
                            nombreVendedor=this.vendedorPedido.getNombre();
                            apellidoVendedor=this.vendedorPedido.getApellido();                
                            LblNombreVendedor.setText(nombreVendedor+" "+apellidoVendedor);
                            TFIdProducto.requestFocus();
                            this.pedidoEspecial = new NuevoPedidoAClientes();
                            this.pedidoEspecial.IniciarPedido(Fecha, vendedorPedido);
                            llenarClientes ();
                        }
                    }
                 else
                 {
                     this.PedidoNuevoAdministrador.showMensajes("Este Vendedor No Esta Asignado para Este Área de Pedido");
                 }
                
                
            }
        }
        else
        {
            this.PedidoNuevoAdministrador.showMensajes("Id del Vendedor no Valido");
        }
    }
     public void llenarClientes ()
    {       
         List<String> list = new ArrayList<String>();
         for (ListaClientes cliente:this.vendedorPedido.getListaclientesCollection())
         {
             list.add(cliente.getClientesidCliente().getNombre());
         }
         ObservableList<String> observableList = FXCollections.observableList(list);
         this.ComBoClientes.setItems(observableList);
         this.ComBoClientes.setValue(String.valueOf(observableList.get(0)));
         this.cambioCliente();
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
    
      public void calculoDeTotalCliente ()
    {
        float subTotal = 0;
        for (DescripcionModelo modelo:this.data)
        {            
            subTotal = subTotal + modelo.getSubTotal();          
        }
        TFTotalCliente.setText(String.valueOf(subTotal));    
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
        TFTotalCliente.setText("0.00");
        LblNombreDeProducto.setText("");
        TFFecha.requestFocus();
        TFTotal.setText("0.00");
        TFSubTotal.setText("0.00");
        TAObservaciones.setText("");
        data.clear();
        data2.clear();
        TablaDescripciones.setItems(data);
        TablaResumen.setItems(data2);
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
        this.HBoxClientes.setDisable(false);
        this.VBoxTabla.setDisable(false);
        this.SPTotales.setDisable(false);
    }
    
     private void DeshabilitarVentanas ()
    {
        this.HBoxProducto.setDisable(true);
        this.HBoxClientes.setDisable(true);
        this.VBoxTabla.setDisable(true);
        this.SPTotales.setDisable(true);
    }
    
    IClientes GestorClientes = new IClientes ();
    
    private float PrecioDelProducto (int idProducto)
    {   
        float precio =0;
        for(ProductosClientes pro:this.clienteCambiado.getTipoClientesidTipoClientes().getProductosclientesCollection())
        {
             if (idProducto==pro.getProductosidProductos().getIdProductos())
             {
                 precio = pro.getPrecio();
                 return precio;
             }
        }
        precio = this.productoGestor.buscarProductoPorId(idProducto).getPrecioVenta();
        return precio;
    }
    
    //Gestores para Pedido
    NuevoPedidoAClientes pedidoEspecial;
    Clientes clienteCambiado;
    
    public void cambioCliente ()
    {
        clienteCambiado = GestorClientes.buscarClientesPorNombre(String.valueOf(this.ComBoClientes.getValue()));
        this.data = pedidoEspecial.descripcionCliente(clienteCambiado);
        TablaDescripciones.setItems(data);
        this.calculoDeTotalCliente();
    }
    
    public void ingresarDescripcion ()
    {
        this.pedidoEspecial.AgregarDescripciones(data, clienteCambiado);
        this.TablaResumen.setItems(this.pedidoEspecial.descripcionResumen());
        this.TFSubTotal.setText(String.valueOf(this.pedidoEspecial.getSubTotal()));
        this.TFTotal.setText(String.valueOf(this.pedidoEspecial.getTotal()));
    }
}

