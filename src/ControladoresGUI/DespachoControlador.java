/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ControladoresGUI;

import AdministradoresGUI.DespachoAdministrador;
import EdicionTablas.EditarPrecioF;
import EntidadesJPA.Clientes;
import EntidadesJPA.DescripcionPedido;
import EntidadesJPA.Facturas;
import EntidadesJPA.Pedido;
import EntidadesJPA.Productos;
import EntidadesJPA.Vendedores;
import Especiales.ConvertirNumeroaLetras;
import Especiales.GeneradordeReportes;
import Especiales.ProductoFacturado;
import Especiales.Validaciones;
import GestorDeTablasJPA.IClientes;
import GestorDeTablasJPA.IDescripcionFactura;
import GestorDeTablasJPA.IDescripcionPedido;
import GestorDeTablasJPA.IFacturas;
import GestorDeTablasJPA.IPedido;
import GestorDeTablasJPA.IProductos;
import Modelos.DescripcionDespachoModelo;
import Modelos.DescripcionFModelo;
import Modelos.DescripcionModelo;
import Modelos.DespachoModelo;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

/**
 *
 * @author luis__000
 */
public class DespachoControlador 
{
    DespachoAdministrador DespachoAdministrador;
    @FXML
    public TableView TablaDespacho;
    
    public TableColumn Id;
    public TableColumn Nombre;
    public TableColumn Total;
    private ObservableList<DespachoModelo> data;
    public TableView TablaFacturas;
    public TableColumn IdFactura;
    public TableColumn NombreFactura;
    public TableColumn CantidadFactura;
    public TableColumn SubTotalFactura;
    private ObservableList<DescripcionFModelo> dataFacturas;
    public TableView TablaDescripciones;
    public TableColumn IdDescripcion;
    public TableColumn NombreDescripcion;
    public TableColumn CantidadDescripcion;
    public TableColumn FacturadoDescripcion;
    public TableColumn SubTotalDescripcion;
    public TableColumn Precio;
    private ObservableList<DescripcionDespachoModelo> dataDescripcion;
    public TextField TFTotal;
    public TextField TFNit;
    public TextField TFNumero;
    public TextField TFSerie;
    public TextField TFNombre;
    public TextField TFDireccion;
    public TextField TFCantidad;
    public TextArea TALetras;
    public Button Factura;
    int IdPedido;
    Validaciones Validar;
    List<ProductoFacturado> Facturado;
    Callback<TableColumn, TableCell> cellFactory;
    
    public void initialize() {}
  
    public void initManager(final DespachoAdministrador DespachoAdministrador)
    {
        cellFactory = new Callback<TableColumn, TableCell>() 
        {
            public TableCell call(TableColumn p) {
                return new EditarPrecioF();
            }
	};
        this.DespachoAdministrador = DespachoAdministrador;
        data = FXCollections.observableArrayList();
        Id = new TableColumn();
        Nombre = new TableColumn();
        Total = new TableColumn();
        dataFacturas = FXCollections.observableArrayList();
        IdFactura = new TableColumn();
        NombreFactura = new TableColumn();
        CantidadFactura = new TableColumn();
        SubTotalFactura = new TableColumn();
        dataDescripcion = FXCollections.observableArrayList();
        IdDescripcion = new TableColumn();
        FacturadoDescripcion = new TableColumn();
        NombreDescripcion = new TableColumn();
        CantidadDescripcion = new TableColumn();
        SubTotalDescripcion = new TableColumn();
        Precio = new TableColumn();
        CargarTablaDespacho();
        CargarColumnasDescripcion();
        LLenarTablaDespacho();
        TablaDespacho.setItems(data);
        CargarColumnasFacturas();
        Validar = new Validaciones();
        Facturado = new ArrayList();
    } 
    
    private void CargarTablaDespacho()
    {
        Id.setCellValueFactory(new PropertyValueFactory<DespachoModelo,Integer>("Id"));
        Nombre.setCellValueFactory(new PropertyValueFactory<DespachoModelo,Integer>("Nombre"));
        Total.setCellValueFactory(new PropertyValueFactory<DespachoModelo,Integer>("Total"));
        Id.setText("Id");
        Nombre.setText("Vendedor");
        Total.setText("Total");
        Id.setPrefWidth(50);
        Nombre.setPrefWidth(300);
        Total.setPrefWidth(200);
        TablaDespacho.getColumns().add(Id);
        TablaDespacho.getColumns().add(Nombre);
        TablaDespacho.getColumns().add(Total);
    }
    
    private void CargarColumnasDescripcion() 
    {
        IdDescripcion.setCellValueFactory(new PropertyValueFactory<DescripcionModelo,Integer>("IdProducto"));
        NombreDescripcion.setCellValueFactory(new PropertyValueFactory<DescripcionModelo,String>("Nombre"));
        CantidadDescripcion.setCellValueFactory(new PropertyValueFactory<DescripcionModelo,Integer>("Cantidad"));
        FacturadoDescripcion.setCellValueFactory(new PropertyValueFactory<DescripcionModelo, Integer>("Facturado"));
        SubTotalDescripcion.setCellValueFactory(new PropertyValueFactory<DescripcionModelo,Float>("SubTotal"));
        Precio.setCellValueFactory(new PropertyValueFactory<DescripcionModelo,Float>("Precio"));
        IdDescripcion.setText("Id");
        NombreDescripcion.setText("Nombre");
        CantidadDescripcion.setText("Cantidad");
        FacturadoDescripcion.setText("Facturado");
        SubTotalDescripcion.setText("SubTotal");
        Precio.setText("Precio");
        IdDescripcion.setPrefWidth(50);
        NombreDescripcion.setPrefWidth(300);
        CantidadDescripcion.setPrefWidth(100);
        FacturadoDescripcion.setPrefWidth(100);
        SubTotalDescripcion.setPrefWidth(100);
        TablaDescripciones.getColumns().add(IdDescripcion);
        TablaDescripciones.getColumns().add(NombreDescripcion);
        TablaDescripciones.getColumns().add(CantidadDescripcion);
        TablaDescripciones.getColumns().add(FacturadoDescripcion);
        TablaDescripciones.getColumns().add(SubTotalDescripcion);
        TablaDescripciones.getColumns().add(Precio);
    }
    
    private void CargarColumnasFacturas()
    {
        IdFactura.setCellValueFactory(new PropertyValueFactory<DescripcionModelo,Integer>("IdProducto"));
        NombreFactura.setCellValueFactory(new PropertyValueFactory<DescripcionModelo,Integer>("Nombre"));
        CantidadFactura.setCellValueFactory(new PropertyValueFactory<DescripcionModelo,Integer>("Cantidad"));
        SubTotalFactura.setCellValueFactory(new PropertyValueFactory<DescripcionModelo,Float>("Precio"));
        SubTotalFactura.setCellFactory(cellFactory);
        SubTotalFactura.setOnEditCommit(
                new EventHandler<CellEditEvent<DescripcionFModelo,String>>()
                {
                    @Override
                    public void handle(CellEditEvent<DescripcionFModelo,String> t)
                    {
                        DescripcionFModelo L = (DescripcionFModelo) t.getTableView().getItems().get(t.getTablePosition().getRow());
                        if(Validar.ValidarMontos(t.getNewValue()))
                        {
                            L.setPrecio(t.getNewValue());
                            L.setSubTotal(L.getCantidad() * Float.parseFloat(t.getNewValue()));
                            CalcularTotal();
                            CalcularTotalLetras();
                        }
                        else
                        {
                            DespachoAdministrador.showMensajes("Precio no Valido");
                            L.setPrecio(t.getOldValue());
                        }
                    }
                });
        IdFactura.setText("Id");
        NombreFactura.setText("Nombre");
        CantidadFactura.setText("Cantidad");
        SubTotalFactura.setText("Precio");
        IdFactura.setPrefWidth(50);
        NombreFactura.setPrefWidth(350);
        CantidadFactura.setPrefWidth(100);
        SubTotalFactura.setPrefWidth(150);
        TablaFacturas.getColumns().add(IdFactura);
        TablaFacturas.getColumns().add(NombreFactura);
        TablaFacturas.getColumns().add(CantidadFactura);
        TablaFacturas.getColumns().add(SubTotalFactura);
    }
    
    private void LLenarTablaDespacho()
    {
        IPedido busqueda = new IPedido();
        List<Pedido> ListaPedidos = busqueda.ListaPedidosMes();
        if(ListaPedidos != null)
        {
            for(Pedido Pedido: ListaPedidos)
            {
                if(Pedido.getAplicado() == false)
                {
                    DespachoModelo Despacho = new DespachoModelo(Pedido.getIdpedido(),Pedido.getVendedoresIdvendedores().getNombre()+" "+Pedido.getVendedoresIdvendedores().getApellido(), Pedido.getTotal());
                    data.add(Despacho);
                }
            }
        }
        else
        {
            Factura.setDisable(true);
        }
    }
    
    public void AgregarDescripcion(ActionEvent evnet)
    {
        dataDescripcion.clear();
        BorrarTodo();
        DespachoModelo Seleccionado = (DespachoModelo)TablaDespacho.getSelectionModel().getSelectedItem();
        if(Seleccionado == null)
        {
            this.DespachoAdministrador.showMensajes("Debe Selecionar un Dato");
        }
        else
        {
            IPedido buscar = new IPedido();
            Pedido Pedido = buscar.buscarPedidoPorId(Seleccionado.getId());
            for(DescripcionPedido nuevo: Pedido.getDescripcionPedidoCollection())
            {
                Productos Producto = nuevo.getProductosidProductos();
                DescripcionDespachoModelo Descripcion = new DescripcionDespachoModelo(Producto.getIdProductos(), Producto.getNombre()+ " " + Producto.getPresentacion(), nuevo.getCantidad(), nuevo.getFacturado(), nuevo.getSubTotal(),nuevo.getPrecio());
                dataDescripcion.add(Descripcion);
            }
            TablaDescripciones.setItems(dataDescripcion);
            IdPedido = Seleccionado.getId();
            CalcularTotal();
            CalcularTotalLetras();
        }
    }
    
    private void CalcularTotal()
    {
        float TotalFactura = 0;
        for(DescripcionFModelo SubTotales: dataFacturas)
        {
            TotalFactura = TotalFactura + SubTotales.getSubTotal();
        }
        TFTotal.setText(String.valueOf(TotalFactura));
        
    }
    
    private void CalcularTotalLetras()
    {
        TALetras.setText(ConvertirNumeroaLetras.convertNumberToLetter(TFTotal.getText()));
    }
    
    public void GenerarFactura(ActionEvent event)
    {
        if(Validar.FormatoNit(TFNit.getText()) && Validar.ValidarNumeros(TFNumero.getText()) && !dataFacturas.isEmpty())
        {
            Clientes Cliente = new IClientes().buscarClientesPorNit(TFNit.getText());
            Vendedores Vendedor = new IPedido().buscarPedidoPorId(IdPedido).getVendedoresIdvendedores();
            Pedido Pedido = new IPedido().buscarPedidoPorId(IdPedido);
            String Mensaje = new IFacturas().guardar(TFSerie.getText(), Integer.parseInt(TFNumero.getText()),Float.parseFloat(TFTotal.getText()), TALetras.getText() ,Cliente, Vendedor, Pedido);
            this.DespachoAdministrador.showMensajes(Mensaje);
            if(Mensaje.equals("Factura Creada Correctamente"))
            {
                TFSerie.setText("");
                TFNumero.setText("");
                TFTotal.setText("");
                TFNit.setText("");
                TFNombre.setText("");
                TFDireccion.setText("");
                int NoFactura = new IFacturas().listaDeFacturas().size() - 1;
                Facturas Facturas = new IFacturas().listaDeFacturas().get(NoFactura);
                for(DescripcionFModelo Descripcion: dataFacturas)
                {
                    Productos Producto = new IProductos().buscarProductoPorId(Descripcion.getIdProducto());
                    new IDescripcionFactura().guardar(Descripcion.getCantidad(), Descripcion.getSubTotal(), Float.parseFloat(Descripcion.getPrecio()), Producto, Facturas);
                }
                for(ProductoFacturado Producto: Facturado)
                {
                    Producto.Descripcion.setFacturado(Producto.Descripcion.getFacturado() + Producto.Cantidad);
                    new IDescripcionPedido().modificar(Producto.Descripcion);
                }
                dataFacturas.clear();
                TablaFacturas.setItems(dataFacturas);
                dataDescripcion.clear();
                TablaDescripciones.setItems(dataDescripcion);
                Facturado.clear();
                GeneradordeReportes Generar = new GeneradordeReportes();
                Generar.AbrirReporte("Factura.jasper", Facturas.getIdFacturas());
            }
        }
        else if(!Validar.FormatoNit(TFNit.getText()))
        {
            this.DespachoAdministrador.showMensajes("Verifique el Nit del Cliente");
        }
        else if(!Validar.ValidarNumeros(TFNumero.getText()))
        {
            this.DespachoAdministrador.showMensajes("Verifique el Numero de la Factura");
        }
        else if(dataFacturas.isEmpty())
        {
            this.DespachoAdministrador.showMensajes("No Hay Descripcion de la Factura");
        }
    }
    
    public void BuscarCliente(ActionEvent event)
    {
        Clientes Cliente = new IClientes().buscarClientesPorNit(TFNit.getText());
        if(Cliente == null)
        {
            this.DespachoAdministrador.showMensajes("El Cliente no Existe");
        }
        else
        {
            TFNombre.setText(Cliente.getNombre());
            TFDireccion.setText(Cliente.getDireccion());            
        }
    }
    
    public void AgregaraFactura(ActionEvent event)
    {
        DescripcionDespachoModelo Seleccionado = (DescripcionDespachoModelo)TablaDescripciones.getSelectionModel().getSelectedItem();
        if(Seleccionado == null)
        {
            this.DespachoAdministrador.showMensajes("Debe Selecionar un Dato");
        }
        else
        {
            Productos Producto = new IProductos().buscarProductoPorId(Seleccionado.getIdProducto());
            Pedido Pedido = new IPedido().buscarPedidoPorId(IdPedido);
            DescripcionPedido Descripcion = new IDescripcionPedido().BuscarDescripcion(Pedido,Producto,Seleccionado.getPrecio());
            if(Validar.ValidarNumeros(TFCantidad.getText()))
            {
                boolean validar = true;
                for(DescripcionFModelo Modelo: dataFacturas)
                {
                    if(Producto.getIdProductos() == Modelo.getIdProducto())
                    {
                        validar = false;
                    }
                }
                if(validar)
                {
                    int Cantidad = Integer.parseInt(TFCantidad.getText());
                    if(Cantidad <= (Descripcion.getCantidad() - Descripcion.getFacturado()))
                    {
                        DecimalFormat df = new DecimalFormat("0.00"); 
                        String Precio = df.format(Descripcion.getPrecio());
                        DescripcionFModelo Modelo = new DescripcionFModelo(Producto.getIdProductos(), Producto.getNombre()+" "+Producto.getPresentacion(), Cantidad,Precio,Descripcion.getPrecio()*Cantidad);
                        dataFacturas.add(Modelo);
                        TablaFacturas.setItems(dataFacturas);
                        Facturado.add(new ProductoFacturado(Descripcion, Cantidad));
                        TFCantidad.setText("");
                    }
                    else
                    {
                        this.DespachoAdministrador.showMensajes("La Cantidad Excede a lo que se Puede Facturar");
                    }
                }
                else
                {
                    this.DespachoAdministrador.showMensajes("Producto ya Ingresado en la Factura");
                }
            }
            else
            {
                this.DespachoAdministrador.showMensajes("Verifique la Cantidad");
            }
            CalcularTotal();
            CalcularTotalLetras();
        }
    }
    
    public void SacarFactura(ActionEvent Event)
    {
        DescripcionModelo Seleccionado = (DescripcionModelo)TablaFacturas.getSelectionModel().getSelectedItem();
        int Index = TablaDescripciones.getSelectionModel().getSelectedIndex();
        if(Seleccionado == null)
        {
            this.DespachoAdministrador.showMensajes("Debe Selecionar un Dato");
        }
        else
        {
            dataFacturas.remove(Index);
            Facturado.remove(Index);
            TablaFacturas.setItems(dataFacturas);
            CalcularTotal();
            CalcularTotalLetras();
        }
    }
    
    public void BorrarTodo()
    {
        TFSerie.setText("");
        TFNumero.setText("");
        TFTotal.setText("");
        TFNit.setText("");
        TFNombre.setText("");
        TFDireccion.setText("");
        TALetras.setText("");
        dataFacturas.clear();
        TablaFacturas.setItems(dataFacturas);
        dataDescripcion.clear();
        TablaDescripciones.setItems(dataDescripcion);
        Facturado.clear();
        TFTotal.setText("0.00");
    }
}

