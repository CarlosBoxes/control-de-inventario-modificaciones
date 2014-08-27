/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ControladoresGUI;

import AdministradoresGUI.RecepcionAdministrador;
import EntidadesJPA.DescripcionPedidoMateriaPrima;
import EntidadesJPA.DescripcionPedidoProveedores;
import EntidadesJPA.MateriaPrima;
import EntidadesJPA.PedidoMateriaPrima;
import EntidadesJPA.PedidoProveedores;
import EntidadesJPA.Productos;
import EntidadesJPA.Proveedores;
import Especiales.Validaciones;
import GestorDeTablasJPA.IDescripcionPedidoMateriaPrima;
import GestorDeTablasJPA.IDescripcionPedidoProveedores;
import GestorDeTablasJPA.IInventarioMateriaPrima;
import GestorDeTablasJPA.IInventarioProducto;
import GestorDeTablasJPA.IMateriaPrima;
import GestorDeTablasJPA.IPedidoMateriaPrima;
import GestorDeTablasJPA.IPedidoProveedores;
import GestorDeTablasJPA.IProductos;
import GestorDeTablasJPA.IProveedores;
import Modelos.DescripcionCompraModelo;
import Modelos.RecepcionModelo;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
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
public class RecepcionControlador 
{
    RecepcionAdministrador RecepcionAdministrador;
    @FXML
    public TableView TablaRecepcion;
    public TableView TablaDescripciones;
    public TableColumn Id;
    public TableColumn Nombre;
    public TableColumn Total;
    public TableColumn IdProducto;
    public TableColumn NombreProducto;
    public TableColumn CantidadProducto;
    public TableColumn PrecioProducto;
    public TextField TFCantidad;
    public VBox VBDatosExtra;
    public Button btnVer;
    public Button btnDescripcion;
    public TextField TFNoFactura;
    public TextField TFFecha;
    public TextField TFSaldo;
    public TextField TFPrecio;
    public TextField TFPago;
    public Label LblNombre;
    public Button btnGuardar;
    public Button btnCancelar;
    private ObservableList<RecepcionModelo> data;
    private ObservableList<DescripcionCompraModelo> dataDescripcion;
    private List<Productos> ListaProducto;
    private List<MateriaPrima> ListaMateriaPrima;
    private List<Float> ListaCantidad;
    private List<Float> ListaPrecios;
    int IdPedido;
    boolean TipoPedido;
    int Index;
    private int ContadorTecla;
    private Validaciones validar;
    PedidoProveedores PedidoCambiado;
    private int idProducto =0;
    private int idMateriaPrima =0;
    
    public void initialize() {}
  
    public void initManager(final RecepcionAdministrador RecepcionAdministrador)
    {
        this.RecepcionAdministrador = RecepcionAdministrador;
        data = FXCollections.observableArrayList();
        dataDescripcion = FXCollections.observableArrayList();
        Id = new TableColumn();
        Nombre = new TableColumn();
        Total = new TableColumn();
        IdProducto = new TableColumn();
        NombreProducto = new TableColumn();
        CantidadProducto = new TableColumn();
        PrecioProducto = new TableColumn();
        CargarTablaRecepcion();
        LLenarTablaRecepcion();
        TablaRecepcion.setItems(data);
        CargarTablaDescripcion();
        btnGuardar.setDisable(true);
        btnCancelar.setDisable(true);
        TFCantidad.setDisable(true);
        TFPrecio.setDisable(true);
        VBDatosExtra.setDisable(true);
        ListaProducto = new ArrayList();
        ListaMateriaPrima = new ArrayList();
        ListaCantidad = new ArrayList();
        ListaPrecios = new ArrayList();
        validar = new Validaciones();
    } 
    
    private void CargarTablaRecepcion()
    {
        Id.setCellValueFactory(new PropertyValueFactory<RecepcionModelo,Integer>("Id"));
        Nombre.setCellValueFactory(new PropertyValueFactory<RecepcionModelo,Integer>("Nombre"));
        Total.setCellValueFactory(new PropertyValueFactory<RecepcionModelo,Integer>("Total"));
        Id.setText("Id");
        Nombre.setText("Proveedor");
        Total.setText("Total");
        Id.setPrefWidth(50);
        Nombre.setPrefWidth(300);
        Total.setPrefWidth(200);
        TablaRecepcion.getColumns().add(Id);
        TablaRecepcion.getColumns().add(Nombre);
        TablaRecepcion.getColumns().add(Total);
    }
    
    private void CargarTablaDescripcion()
    {
        IdProducto.setCellValueFactory(new PropertyValueFactory<RecepcionModelo,Integer>("IdProducto"));
        NombreProducto.setCellValueFactory(new PropertyValueFactory<RecepcionModelo,Integer>("Nombre"));
        CantidadProducto.setCellValueFactory(new PropertyValueFactory<RecepcionModelo,Integer>("Cantidad"));
        PrecioProducto.setCellValueFactory(new PropertyValueFactory<RecepcionModelo,Float>("Precio"));
        IdProducto.setText("Id");
        NombreProducto.setText("Nombre");
        CantidadProducto.setText("Cantidad");
        PrecioProducto.setText("Precio");
        IdProducto.setPrefWidth(50);
        NombreProducto.setPrefWidth(300);
        CantidadProducto.setPrefWidth(200);
        PrecioProducto.setPrefWidth(200);
        TablaDescripciones.getColumns().add(IdProducto);
        TablaDescripciones.getColumns().add(NombreProducto);
        TablaDescripciones.getColumns().add(CantidadProducto);
        TablaDescripciones.getColumns().add(PrecioProducto);
    }
    
    private void LLenarTablaRecepcion()
    {
        data.clear();
        List<PedidoProveedores> ListaPedidos = new IPedidoProveedores().buscarPedidos();
        if(ListaPedidos != null)
        {
            for(PedidoProveedores Pedido: ListaPedidos)
            {
                if (!Pedido.getAplicado())
                {
                RecepcionModelo Recepcion = new RecepcionModelo(Pedido.getIdpedidoProveedores(), Pedido.getProveedoresidProveedores().getNombre(), Pedido.getTotal(), true, Pedido.getAlmacenado());
                data.add(Recepcion);       
                }
            }
        }
        List<PedidoMateriaPrima> ListaPedido = new IPedidoMateriaPrima().buscarPedido();
        if(ListaPedido != null)
        {
            for(PedidoMateriaPrima Pedido: ListaPedido)
            {
                if (!Pedido.getAplicado())
                {
                RecepcionModelo Recepcion = new RecepcionModelo(Pedido.getIdpedidoMP(), Pedido.getProveedoresidProveedores().getNombre(), Pedido.getTotal(), false, Pedido.getAplicado());
                data.add(Recepcion); 
                }
            }
        }
    }
    
    public void AgregarDescripcion(ActionEvent event)
    {
        btnVer.setDisable(true);
        dataDescripcion.clear();
        RecepcionModelo Seleccionado = (RecepcionModelo)TablaRecepcion.getSelectionModel().getSelectedItem();
        if(Seleccionado == null)
        {
            this.RecepcionAdministrador.showMensajes("Debe Selecionar un Dato");
        }
        else
        {
            if(Seleccionado.getAlmacenado() && Seleccionado.getTipo())
            {
                HabilitarDatosExtra(Seleccionado.getTipo());
                btnDescripcion.setDisable(true);
                TFPago.setText(String.valueOf(Seleccionado.getTotal()));
                PedidoCambiado = new IPedidoProveedores().buscarPedidoPorId(Seleccionado.getId());
            }
            else if(!Seleccionado.getAlmacenado() && Seleccionado.getTipo())
            {
                btnCancelar.setDisable(false);
                PedidoProveedores Pedido = new IPedidoProveedores().buscarPedidoPorId(Seleccionado.getId());
                btnDescripcion.setDisable(false);
                for(DescripcionPedidoProveedores nuevo: Pedido.getDescripcionPedidoProveedoresCollection())
                {
                    Productos Producto = nuevo.getProductosidProductos();
                    DescripcionCompraModelo Descripcion = new DescripcionCompraModelo(Producto.getIdProductos(), Producto.getNombre(), nuevo.getCantidad(), nuevo.getPrecioProducto(), 0);
                    dataDescripcion.add(Descripcion);
                }
                TablaDescripciones.setItems(dataDescripcion);
                IdPedido = Seleccionado.getId();
                TipoPedido = Seleccionado.getTipo();
            }
            else
            {
                btnCancelar.setDisable(false);
                PedidoMateriaPrima Pedido = new IPedidoMateriaPrima().buscarPedidoPorId(Seleccionado.getId());
                for(DescripcionPedidoMateriaPrima nuevo: Pedido.getDescripcionPedidoMateriaPrimaCollection())
                {
                    MateriaPrima Producto = nuevo.getMateriaPrimaidmateriaPrima();
                    DescripcionCompraModelo Descripcion = new DescripcionCompraModelo(Producto.getIdmateriaPrima(), Producto.getNombre(), nuevo.getCantidad(), nuevo.getPrecioMateriaPrima(), 0);
                    dataDescripcion.add(Descripcion);
                }
                TablaDescripciones.setItems(dataDescripcion);
                IdPedido = Seleccionado.getId();
                TipoPedido = Seleccionado.getTipo();
            }
        }
    }
    
    public void SeleccionarProducto(ActionEvent click)
    {
        DescripcionCompraModelo Seleccionado = (DescripcionCompraModelo)TablaDescripciones.getSelectionModel().getSelectedItem();
        if(Seleccionado == null)
        {
            this.RecepcionAdministrador.showMensajes("Debe Selecionar un Dato");
        }
        else
        {
            TFCantidad.setText(String.valueOf(Seleccionado.getCantidad()));
            TFPrecio.setText(String.valueOf(Seleccionado.getPrecio()));
            LblNombre.setText(Seleccionado.getNombre());
            this.idProducto = Seleccionado.getIdProducto();
            Index = TablaDescripciones.getSelectionModel().getSelectedIndex();
            btnGuardar.setDisable(false);
            TFCantidad.setDisable(false);
            TFPrecio.setDisable(false);
        }
    }
    
    public void Cancelar(ActionEvent evnet)
    {
        TFCantidad.setText("");
        LblNombre.setText("");
        dataDescripcion.clear();
        TablaDescripciones.setItems(dataDescripcion);
        btnGuardar.setDisable(true);
        btnCancelar.setDisable(true);
        TFCantidad.setDisable(true);
        TFPrecio.setDisable(true);
        ListaProducto.clear();
        ListaCantidad.clear();
        ListaPrecios.clear();
        ListaMateriaPrima.clear();
        btnVer.setDisable(false);
    }
    
    public void CancelarDatosExtra(ActionEvent evnet)
    {
        TFNoFactura.setText("");
        TFFecha.setText("");
        ContadorTecla = 0;
        TFSaldo.setText("");
        btnVer.setDisable(false);
        PedidoCambiado = null;
        TFPago.setText("");
        VBDatosExtra.setDisable(true);
    }
    
    public void GuardarInventario(ActionEvent event)
    {
        if(TipoPedido)
        {
            if(validar.ValidarMontos(TFCantidad.getText()) && validar.ValidarMontos(TFPrecio.getText()))
            {
                Productos Producto = new IProductos().buscarProductoPorId(this.idProducto);
                ListaProducto.add(Producto);
                ListaCantidad.add(Float.parseFloat(TFCantidad.getText()));
                ListaPrecios.add(Float.parseFloat(TFPrecio.getText()));
                dataDescripcion.remove(Index);
                TablaDescripciones.setItems(dataDescripcion);
                TFCantidad.setText("");
                LblNombre.setText("");
                TFPrecio.setText("");
                btnGuardar.setDisable(true);
                TFCantidad.setDisable(true);
                TFPrecio.setDisable(true);
            }
            else if(!validar.ValidarMontos(TFCantidad.getText()) && validar.ValidarMontos(TFPrecio.getText()))
            {
                this.RecepcionAdministrador.showMensajes("Verifique la Cantidad");
            }
            else if(validar.ValidarMontos(TFCantidad.getText()) && !validar.ValidarMontos(TFPrecio.getText()))
            {
                this.RecepcionAdministrador.showMensajes("Verifique el Precio");
            }
            else
            {
                this.RecepcionAdministrador.showMensajes("Verifique la Cantidad y el Precio");
            }
        }
        else
        {
            if(validar.ValidarMontos(TFCantidad.getText()) && validar.ValidarMontos(TFPrecio.getText()))
            {
                MateriaPrima Producto = new IMateriaPrima().buscarMateriaPrimaPorId(this.idProducto);
                ListaMateriaPrima.add(Producto);
                ListaCantidad.add(Float.parseFloat(TFCantidad.getText()));
                ListaPrecios.add(Float.parseFloat(TFPrecio.getText()));
                dataDescripcion.remove(Index);
                TablaDescripciones.setItems(dataDescripcion);
                TFCantidad.setText("");
                LblNombre.setText("");
                TFPrecio.setText("");
                btnGuardar.setDisable(true);
                TFCantidad.setDisable(true);
                TFPrecio.setDisable(true);
                btnVer.setDisable(false);
            }
            else if(!validar.ValidarMontos(TFCantidad.getText()) && validar.ValidarMontos(TFPrecio.getText()))
            {
                this.RecepcionAdministrador.showMensajes("Verifique la Cantidad");
            }
            else if(validar.ValidarNumeros(TFCantidad.getText()) && !validar.ValidarMontos(TFPrecio.getText()))
            {
                this.RecepcionAdministrador.showMensajes("Verifique el Precio");
            }
            else
            {
                this.RecepcionAdministrador.showMensajes("Verifique la Cantidd y el Precio");
            }
        }
        HabilitarDatosExtra(TipoPedido);
        GuardarCambios();
    }
    
    private void GuardarCambios()
    { 
        if(dataDescripcion.size() == 0)
        {
            if(TipoPedido)
            {
                PedidoProveedores Pedido = new IPedidoProveedores().buscarPedidoPorId(IdPedido);
                Pedido.setAlmacenado(true);
                new IPedidoProveedores().modificar(Pedido);
                for(int i = 0; i < ListaProducto.size();i++)
                {
                    float NumeroReal=ListaCantidad.get(i);
                    String auxNumeroReal;
                    float Cantidad;
                    auxNumeroReal=String.valueOf(NumeroReal);
                    Cantidad=(float) NumeroReal;
                    new IInventarioProducto().meterAlInventario(ListaProducto.get(i), Cantidad);
                    for(DescripcionPedidoProveedores nuevo: Pedido.getDescripcionPedidoProveedoresCollection())
                    {
                        if(nuevo.getProductosidProductos().equals(ListaProducto.get(i)))
                        {
                            float NumeroReal2=ListaCantidad.get(i);
                            String auxNumeroReal2;
                            float Cantidad2;
                            auxNumeroReal=String.valueOf(NumeroReal2);
                            Cantidad2=(float) NumeroReal;
                            nuevo.setCantidad(Cantidad2);
                            nuevo.setPrecioProducto(ListaPrecios.get(i));
                            new IDescripcionPedidoProveedores().modificar(nuevo);
                        }
                    }
                }
                CalcularNuevoTotal(Pedido);
                LLenarTablaRecepcion();
                TablaRecepcion.setItems(data);
                TFPago.setText(String.valueOf(Pedido.getTotal()));
                ListaProducto.clear();
                ListaCantidad.clear();
                ListaPrecios.clear();
                this.PedidoCambiado = Pedido;
                this.RecepcionAdministrador.showMensajes("Pedido Guardado en Bodega");
            }
            else
            {
                PedidoMateriaPrima Pedido = new IPedidoMateriaPrima().buscarPedidoPorId(IdPedido);
                Pedido.setAplicado(true);
                new IPedidoMateriaPrima().modificar(Pedido);
                for(int i = 0; i < ListaMateriaPrima.size();i++)
                {
                    new IInventarioMateriaPrima().meterAlInventario(ListaMateriaPrima.get(i), ListaCantidad.get(i));
                    for(DescripcionPedidoMateriaPrima nuevo: Pedido.getDescripcionPedidoMateriaPrimaCollection())
                    {
                        if(nuevo.getMateriaPrimaidmateriaPrima().equals(ListaMateriaPrima.get(i)))
                        {
                            nuevo.setCantidad(ListaCantidad.get(i));
                            nuevo.setPrecioMateriaPrima(ListaPrecios.get(i));
                            nuevo.setSubTotal(ListaCantidad.get(i) * ListaPrecios.get(i));
                            new IDescripcionPedidoMateriaPrima().modificar(nuevo);
                        }
                    }
                }
                CalcularNuevoTotal(Pedido);
                ListaMateriaPrima.clear();
                ListaCantidad.clear();
                ListaPrecios.clear();
                data.clear();
                LLenarTablaRecepcion();
                TablaRecepcion.setItems(data);
                btnCancelar.setDisable(true);
                this.RecepcionAdministrador.showMensajes("Pedido Guardado en Bodega");
            }
        }
    }
    
    private void CalcularNuevoTotal(PedidoProveedores Pedido)
    {
        Float TotalPedido = 0f;
        for(DescripcionPedidoProveedores nuevo: Pedido.getDescripcionPedidoProveedoresCollection())
        {
            TotalPedido = TotalPedido + (nuevo.getPrecioProducto() * nuevo.getCantidad());
        }
        Pedido.setTotal(TotalPedido);
        new IPedidoProveedores().modificar(Pedido);
    }
    
    private void CalcularNuevoTotal(PedidoMateriaPrima Pedido)
    {
        Float TotalPedido = 0f;
        for(DescripcionPedidoMateriaPrima nuevo: Pedido.getDescripcionPedidoMateriaPrimaCollection())
        {
            TotalPedido = TotalPedido + nuevo.getSubTotal();
        }
    }
    
    private void HabilitarDatosExtra(boolean Pedido)
    {
        if(dataDescripcion.size() == 0 && Pedido)
        {
            VBDatosExtra.setDisable(false);
            btnCancelar.setDisable(true);
        }
    }
    
    public void GuardarCambiosPedido(ActionEvent Evnet)
    {
        if (this.verificarCamposVacios()!=null)
            {
                this.RecepcionAdministrador.showMensajes(this.verificarCamposVacios());
            }   
            else
            
        if(Float.parseFloat(TFSaldo.getText()) <= PedidoCambiado.getTotal())
        {
            DateFormat Formato = DateFormat.getDateInstance(DateFormat.SHORT);
            Date Fecha = null;
            try 
            {
                Fecha = Formato.parse(TFFecha.getText());
            } 
            catch (ParseException ex) 
            {
                Logger.getLogger(RecepcionControlador.class.getName()).log(Level.SEVERE, null, ex);
            }
            
                PedidoCambiado.setFechaVencimiento(Fecha);
                PedidoCambiado.setNoFactura(TFNoFactura.getText());
                PedidoCambiado.setSaldo(PedidoCambiado.getTotal() - Float.parseFloat(TFSaldo.getText()));
            
            {
            new IPedidoProveedores().modificar(PedidoCambiado);
            
            if(PedidoCambiado.getTotal() == Float.parseFloat(TFSaldo.getText()))
            {
                this.RecepcionAdministrador.showMensajes("Datos Guardados");
                btnVer.setDisable(false);
            }
            else
            {
                this.RecepcionAdministrador.showMensajes("Datos Guardados \n El Saldo de la Factura es: Q. " + String.valueOf(PedidoCambiado.getTotal() - Float.parseFloat(TFSaldo.getText())));
                Proveedores Proveedor = PedidoCambiado.getProveedoresidProveedores();
                Float Saldo = Proveedor.getSaldo() + (PedidoCambiado.getTotal() - Float.parseFloat(TFSaldo.getText()));
                Proveedor.setSaldo(Saldo);
                new IProveedores().modificar(Proveedor);
                btnVer.setDisable(false);
            }
            PedidoCambiado.setAplicado(true);
            new IPedidoProveedores().modificar(PedidoCambiado);
            PedidoCambiado = null;
            TFNoFactura.setText("");
            TFSaldo.setText("");
            TFFecha.setText("");
            ContadorTecla = 0;
            TFPago.setText("");
            data.clear();
            LLenarTablaRecepcion();
            TablaRecepcion.setItems(data);
            VBDatosExtra.setDisable(true);
            }
        }
        else
        {
            this.RecepcionAdministrador.showMensajes("Está Intentando Pagar Más de lo Debido");
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
    
    public String verificarCamposVacios ()
    {
        if (this.TFNoFactura.getText().isEmpty())
        {
            return "Debe Ingresar el Numero de Factura o Recibo";
        }
        else
            if (this.TFFecha.getText().isEmpty())
            {
                return "Debe ingresar la fecha de vencimiento de la Factura";
            }
        else
                if (this.TFSaldo.getText().isEmpty())
                {
                    return "Debe ingresar el Saldo de la Factura";
                }
        else
                {
                    return null;
                }
    }
}


