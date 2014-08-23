/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ControladoresGUI;


import AdministradoresGUI.PedidoMateriaPrimaAdministrador;
import EntidadesJPA.DescripcionPedidoMateriaPrima;
import EntidadesJPA.MateriaPrima;
import EntidadesJPA.PedidoMateriaPrima;
import EntidadesJPA.Proveedores;
import Especiales.GeneradordeReportes;
import Especiales.NuevoPedidoMateriaPrima;
import Especiales.Validaciones;
import GestorDeTablasJPA.IDescripcionPedidoMateriaPrima;
import GestorDeTablasJPA.IInventarioMateriaPrima;
import GestorDeTablasJPA.IMateriaPrima;
import GestorDeTablasJPA.IPedidoMateriaPrima;
import GestorDeTablasJPA.IProveedores;
import Modelos.DescripcionCompraModelo;
import Modelos.MateriaPrimaModelo;
import Modelos.ProductoModelo;
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
public class PedidoMateriaPrimaControlador 
{
    PedidoMateriaPrimaAdministrador PedidoMateriaPrimaAdministrador;
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
    public TextField TFFecha;
    public TextField TFIdProveedor;
    public Label LblNombreProveedor;
    private List<DescripcionPedidoMateriaPrima> listaDeDescripcion;
    public IDescripcionPedidoMateriaPrima descripcionGestor;
    public IPedidoMateriaPrima pedidos;
    public IMateriaPrima productoGestor;
    public IProveedores proveedor;
    public IInventarioMateriaPrima inventarioGestor;
    public NuevoPedidoMateriaPrima nuevoPedido;
    public PedidoMateriaPrima pedido;
    public VBox pnlBusqueda;
    private ObservableList<DescripcionCompraModelo> dataDescripciones;
    private ObservableList<MateriaPrimaModelo> dataProductos;
    private Validaciones validar;
    private int ContadorTecla;
    
    public void initialize() {}
  
    public void initManager(final PedidoMateriaPrimaAdministrador PedidoMateriaPrimaAdministrador)
    {
        this.PedidoMateriaPrimaAdministrador = PedidoMateriaPrimaAdministrador;
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
        CargarColumnasDescripcion();
        // para ingresar nuevo pedido a proveedores
        proveedor = new IProveedores();
        productoGestor = new IMateriaPrima();
        pedidos = new IPedidoMateriaPrima ();
        descripcionGestor = new IDescripcionPedidoMateriaPrima();
        listaDeDescripcion = new ArrayList();
        pedido = new PedidoMateriaPrima();
        inventarioGestor = new IInventarioMateriaPrima();
        nuevoPedido = new NuevoPedidoMateriaPrima ();
        this.validar = new Validaciones();
        calcularTotal();
    } 
    
    public void crearNuevoPedido(ActionEvent event)
    {
        try
        {       
            DateFormat df = DateFormat.getDateInstance(DateFormat.SHORT);      
            java.util.Date fecha = null;
            try {
                fecha = df.parse(TFFecha.getText());
            } catch (ParseException ex) {
                Logger.getLogger(NuevoProductoControlador.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            float total = Float.parseFloat(TFTotal.getText());
            Proveedores proveedorX  = proveedor.buscarProveedorPorId(Integer.parseInt(TFIdProveedor.getText()));
            nuevoPedido.crearNuevoPedidoSinDescripcion(fecha, false,total,proveedorX);    
            pedido = pedidos.retornarUltimoIngresado();
            this.convertirDeModeloAListaDescripciones();
            this.PedidoMateriaPrimaAdministrador.showMensajes("Pedido Ingresado Correctamente");
            this.limpiarTodo();
            dataDescripciones.clear();
            TablaDescripciones.setItems(dataDescripciones);
            GeneradordeReportes Generador = new GeneradordeReportes();
            Generador.AbrirReporte("PedidoMateriaPrima.jasper", pedido.getIdpedidoMP());
            
        }
        catch (Exception e)
        {
            this.PedidoMateriaPrimaAdministrador.showMensajes("Error al Ingresar el Pedido");
        }
    }
    
    public void convertirDeModeloAListaDescripciones ()
    {
        DescripcionPedidoMateriaPrima descripcion = new DescripcionPedidoMateriaPrima();
        for (DescripcionCompraModelo modelo:this.dataDescripciones)
        {      
           
            this.descripcionGestor.guardar(modelo.getCantidad(),modelo.getPrecio(),productoGestor.buscarMateriaPrimaPorId(modelo.getIdProducto()),this.pedido,modelo.getSubTotal());
            descripcion = this.descripcionGestor.retornarUltimoIngresado();
            this.listaDeDescripcion.add(descripcion);
        }
    }
    public void calcularTotal()
    {
        float total = 0;
        for (DescripcionCompraModelo modelo:this.dataDescripciones)
        { 
            total = total +modelo.getSubTotal();
        }
        TFTotal.setText(String.valueOf(total));
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
          TFIdProveedor.setText("");
          LblNombreProveedor.setText("");
          TFIdProducto.setText("");
          TFIdProducto.requestFocus();
          TFCantidad.setText("");
          TFFecha.requestFocus();
          TFTotal.setText("0.00");
          TFPrecio.setText("");
          ContadorTecla = 0;
      }
      
      public void cancelar (ActionEvent e)
      {
          this.limpiarTodo();
          dataDescripciones.clear();
          TablaDescripciones.setItems(dataDescripciones);
      }
      
       public void mostrarProducto (ActionEvent e)
    {
        if ((!this.validar.ValidarNumeros(this.TFIdProducto.getText()))||(this.TFIdProducto.getText().isEmpty()))
        {
            this.PedidoMateriaPrimaAdministrador.showMensajes("Verique Id. De Producto");
            this.TFIdProducto.requestFocus();
        }
        else
        if ((this.productoGestor.buscarMateriaPrimaPorId(Integer.parseInt(TFIdProducto.getText()))==null)||(this.productoGestor.buscarMateriaPrimaPorId(Integer.parseInt(TFIdProducto.getText())).getEliminado()))
        {
            this.PedidoMateriaPrimaAdministrador.showMensajes("Producto no Encontrado");
            
        }
        else
        {
            TFCantidad.requestFocus();
            TFCantidad.requestFocus();
        }
    }
        public void irAPrecio (ActionEvent e)
      {
          TFPrecio.requestFocus();
      }
       
        public void pasarAPrecio (ActionEvent event)
        {
            this.TFPrecio.requestFocus();
        }
      public void mostrarProveedor(ActionEvent e)
    {       
        String nombreProveedor;
        if ((!this.validar.ValidarNumeros(this.TFIdProveedor.getText()))||(this.TFIdProveedor.getText().isEmpty()))
        {
            this.PedidoMateriaPrimaAdministrador.showMensajes("Verificar Id. De Proveedor");
            this.LblNombreProveedor.setText("");
            this.TFIdProveedor.requestFocus();
        }
        else
        if (this.proveedor.buscarProveedorPorId(Integer.parseInt(TFIdProveedor.getText()))==null)
        {
            this.PedidoMateriaPrimaAdministrador.showMensajes("Vendedor no Encontrado");
        }
        else
        {
            nombreProveedor=this.proveedor.buscarProveedorPorId(Integer.parseInt(TFIdProveedor.getText())).getNombre();
            LblNombreProveedor.setText(nombreProveedor);
            TFIdProducto.requestFocus();
        }
    }


private void CargarColumnasProducto() 
    {
        IdProducto.setCellValueFactory(new PropertyValueFactory<MateriaPrimaModelo,Integer>("Id"));
        NombreProducto.setCellValueFactory(new PropertyValueFactory<MateriaPrimaModelo,String>("Nombre"));
        CantidadProducto.setCellValueFactory(new PropertyValueFactory<MateriaPrimaModelo,Integer>("Cantidad"));
        Presentacion.setCellValueFactory(new PropertyValueFactory<ProductoModelo,String>("Presentacion")); 
        IdProducto.setText("Id");
        NombreProducto.setText("Nombre");
        CantidadProducto.setText("Cantidad");
        Presentacion.setText("Presentación");
        IdProducto.setPrefWidth(50);
        NombreProducto.setPrefWidth(200);
        Presentacion.setPrefWidth(100);
        TablaProductos.getColumns().add(IdProducto);
        TablaProductos.getColumns().add(NombreProducto);
        TablaProductos.getColumns().add(CantidadProducto);
        TablaProductos.getColumns().add(Presentacion);
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
            
            IMateriaPrima busqueda = new IMateriaPrima();
            List<MateriaPrima> Producto = new ArrayList();
            Producto = busqueda.listaDeMateriaPrima();
            for(MateriaPrima nuevo:Producto)
            {
                IInventarioMateriaPrima buscar = new IInventarioMateriaPrima();
                float cantidad = buscar.buscarInventarioPorIdMateriaPrima(nuevo).getCantidad();
                MateriaPrimaModelo ProductoModelo = new MateriaPrimaModelo(nuevo.getIdmateriaPrima(),nuevo.getNombre(), cantidad, nuevo.getPresentacion());
                dataProductos.add(ProductoModelo);
            }
        }
        else
        {
            IMateriaPrima busqueda = new IMateriaPrima();
            MateriaPrima Producto = busqueda.buscarMateriaPrimaPorNombre(TFBusqueda.getText());
            IInventarioMateriaPrima buscar = new IInventarioMateriaPrima();
            float cantidad = buscar.buscarInventarioPorIdMateriaPrima(Producto).getCantidad();
            MateriaPrimaModelo ProductoModelo = new MateriaPrimaModelo(Producto.getIdmateriaPrima(),Producto.getNombre(), cantidad, Producto.getPresentacion());
            dataProductos.add(ProductoModelo);
        }
    }
    
    private void LLenarTablaDescripcion()
    {
        IMateriaPrima busqueda = new IMateriaPrima();
        MateriaPrima Producto = new MateriaPrima();
        int id = Integer.parseInt(TFIdProducto.getText());
        Producto = busqueda.buscarMateriaPrimaPorId(id);
        if (this.buscarMateriaPrimaInsertada(id))
        {
            this.PedidoMateriaPrimaAdministrador.showMensajes("Materia Prima ya Fue Ingresada, Edite La que Ya Ingreso");
            this.limpiarDescripcion();
        }
        else
        {
            float cantidad = Float.parseFloat(TFCantidad.getText());
            float subtotal = Float.parseFloat(TFPrecio.getText()) * cantidad;
            DescripcionCompraModelo DescripcionModelo = new DescripcionCompraModelo(Producto.getIdmateriaPrima(), Producto.getNombre(), cantidad, Float.parseFloat(TFPrecio.getText()), subtotal);
            dataDescripciones.add(DescripcionModelo);
            this.calculoDeTotales();
            this.limpiarDescripcion();
        }
    }
    
    public void Buscar(ActionEvent event)
    {
        if (this.TFBusqueda.getText().isEmpty())
        {
            this.PedidoMateriaPrimaAdministrador.showMensajes("Verifique el Nombre del Producto");
        }
        else
        {
            dataProductos.clear();
            TablaProductos.setItems(dataProductos);
            LLenarTablaProductos();
            TablaProductos.setItems(dataProductos);
        }
    }
    
    public void AgregarId(ActionEvent event)
    {
        MateriaPrimaModelo Seleccionado = (MateriaPrimaModelo)TablaProductos.getSelectionModel().getSelectedItem();
        if(Seleccionado == null)
        {
            this.PedidoMateriaPrimaAdministrador.showMensajes("Debe Seleccionar un Dato");
        }
        else
        {
            this.TFIdProducto.setText(String.valueOf(Seleccionado.getId()));
        }
    }
    
    public void AgregarProducto(ActionEvent event)
    {
        
        if (!this.validar.ValidarMontos(this.TFPrecio.getText()))
        {
            this.PedidoMateriaPrimaAdministrador.showMensajes("Verifique el Precio de la Materia Prima");
            this.TFPrecio.requestFocus();
        }
        else
       
        {
            LLenarTablaDescripcion();
            TablaDescripciones.setItems(dataDescripciones);
            calcularTotal();
        }
    }
    
    public void SacarProducto(ActionEvent event)
    {
        DescripcionCompraModelo Seleccionado = (DescripcionCompraModelo)TablaDescripciones.getSelectionModel().getSelectedItem();
        int indice = TablaDescripciones.getSelectionModel().getSelectedIndex();
        if(Seleccionado == null)
        {
            this.PedidoMateriaPrimaAdministrador.showMensajes("Debe Seleccionar un Dato");
        }
        else
        {
            dataDescripciones.remove(indice);
            TablaDescripciones.setItems(dataDescripciones);
            this.calculoDeTotales();
            calcularTotal();
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
        TFTotal.setText(String.valueOf(total));    
    }
   
    public boolean buscarMateriaPrimaInsertada (Integer id)//verificar este procedimiento
    {
        for (DescripcionCompraModelo modelo:this.dataDescripciones)
        {
            if ((Integer)modelo.getIdProducto()==id)
            {
                return true;
            }
        }
        return false;
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
}

