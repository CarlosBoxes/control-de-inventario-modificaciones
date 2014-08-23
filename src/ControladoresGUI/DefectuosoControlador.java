/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ControladoresGUI;

import AdministradoresGUI.DefectuosoAdministrador;
import EntidadesJPA.Productos;
import EntidadesJPA.ProductosDefectuoso;
import EntidadesJPA.ProductosVencidos;
import EntidadesJPA.Vendedores;
import Especiales.Validaciones;
import GestorDeTablasJPA.IInventarioProducto;
import GestorDeTablasJPA.IProductos;
import GestorDeTablasJPA.IProductosDefectuoso;
import GestorDeTablasJPA.IProductosVencidos;
import GestorDeTablasJPA.IVendedores;
import Modelos.DefectuososModelo;
import Modelos.VencidosModelo;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 *
 * @author luis__000
 */
public class DefectuosoControlador 
{
    DefectuosoAdministrador DefectuosoAdministrador;
    @FXML
    public TableView TablaVencidos;
    public TableView TablaDefectuosos;
    public TableColumn IdV;
    public TableColumn NombreV;
    public TableColumn CantidadV;
    public TableColumn IdD;
    public TableColumn ProductoD;
    public TableColumn CantidadD;
    public TableColumn VendedorD;
    public TableColumn DescripcionD;
    public TextField TFCVencido;
    public TextField TFCantidad;
    public TextField TFIdProducto;
    public TextField TFVendedor;
    public TextArea TADescripcion;
    public Label lblVendedor;
    public Button btnSacar;
    private ObservableList<VencidosModelo> dataVencidos;
    private ObservableList<DefectuososModelo> dataDefectuosos;
    Validaciones Validar;
    
    public void initialize() {}
  
    public void initManager(final DefectuosoAdministrador DefectuosoAdministrador)
    {
        this.DefectuosoAdministrador = DefectuosoAdministrador;
        dataVencidos = FXCollections.observableArrayList();
        dataDefectuosos = FXCollections.observableArrayList();
        IdV = new TableColumn();
        NombreV = new TableColumn();
        CantidadV = new TableColumn();
        IdD = new TableColumn();
        ProductoD = new TableColumn();
        CantidadD = new TableColumn();
        DescripcionD = new TableColumn();
        VendedorD = new TableColumn();
        CargarColumnasV();
        CargarColumnasD();
        LLenarTablaV();
        LLenarTablaD();
        Validar = new Validaciones();
    }
    
    public void abrirDefectuoso()
    {
        this.DefectuosoAdministrador.abrirPanelDefectuoso();
    }

    private void CargarColumnasV() 
    {
        IdV.setCellValueFactory(new PropertyValueFactory<VencidosModelo,Integer>("Id"));
        NombreV.setCellValueFactory(new PropertyValueFactory<VencidosModelo,String>("Nombre"));
        CantidadV.setCellValueFactory(new PropertyValueFactory<VencidosModelo,Integer>("Cantidad"));
        IdV.setText("Id");
        NombreV.setText("Nombre");
        CantidadV.setText("Cantidad");
        IdV.setPrefWidth(50);
        NombreV.setPrefWidth(400);
        CantidadV.setPrefWidth(200);
        TablaVencidos.getColumns().add(IdV);
        TablaVencidos.getColumns().add(NombreV);
        TablaVencidos.getColumns().add(CantidadV);
    }
    
    private void CargarColumnasD() 
    {
        IdD.setCellValueFactory(new PropertyValueFactory<VencidosModelo,Integer>("Id"));
        ProductoD.setCellValueFactory(new PropertyValueFactory<VencidosModelo,String>("Nombre"));
        CantidadD.setCellValueFactory(new PropertyValueFactory<VencidosModelo,Integer>("Cantidad"));
        VendedorD.setCellValueFactory(new PropertyValueFactory<DefectuososModelo,String>("Vendedor"));
        DescripcionD.setCellValueFactory(new PropertyValueFactory<DefectuososModelo,String>("Descripcion"));
        IdD.setText("Id");
        ProductoD.setText("Nombre");
        CantidadD.setText("Cantidad");
        DescripcionD.setText("Descripcion");
        VendedorD.setText("Vendedor");
        IdD.setPrefWidth(50);
        ProductoD.setPrefWidth(200);
        CantidadD.setPrefWidth(100);
        VendedorD.setPrefWidth(200);
        DescripcionD.setPrefWidth(200);
        TablaDefectuosos.getColumns().add(IdD);
        TablaDefectuosos.getColumns().add(ProductoD);
        TablaDefectuosos.getColumns().add(CantidadD);
        TablaDefectuosos.getColumns().add(VendedorD);
        TablaDefectuosos.getColumns().add(DescripcionD);
    }
    
    private void LLenarTablaV()
    {
        dataVencidos.clear();
        if(new IProductos().ListaProductosVencidos() != null)
        {
            for(Productos Producto: new IProductos().ListaProductosVencidos())        
            {
                VencidosModelo Modelo = new VencidosModelo(Producto.getIdProductos(), Producto.getNombre(),new IInventarioProducto().buscarInventarioPorProducto(Producto).getCantidad());
                dataVencidos.add(Modelo);
            }
        }
        if(dataVencidos.isEmpty())
        {
            btnSacar.setDisable(true);
            TFCVencido.setDisable(true);
        }
        else
        {
            TablaVencidos.setItems(dataVencidos);
        }
    }
    
    public void SacarProductos(ActionEvent event)
    {
        VencidosModelo Seleccionado = (VencidosModelo)TablaVencidos.getSelectionModel().getSelectedItem();
        if(Seleccionado == null)
        {
            this.DefectuosoAdministrador.showMensajes("Debe Seleccionar un Dato");
        }
        else
        {
            Productos Producto = new IProductos().buscarProductoPorId(Seleccionado.getId());
            if(Validar.ValidarNumeros(TFCVencido.getText()))
            {
                new IInventarioProducto().sacarDeInventario(Producto, Integer.parseInt(TFCVencido.getText()));
                LLenarTablaV();
                this.DefectuosoAdministrador.showMensajes("Productos Retirados Correctamente");
            }
            else
            {
                this.DefectuosoAdministrador.showMensajes("Verifica la Cantida");
            }
        }
    }
    
    public void AgregarProductoDefectuoso(ActionEvent evnet)
    {
        Productos Producto = new IProductos().buscarProductoPorId(Integer.parseInt(TFIdProducto.getText()));
        Vendedores Vendedor = BuscarVendedor(TFVendedor.getText());
        if(Vendedor != null)
        {
            String Mensaje = new IProductosDefectuoso().guardar(Producto, Integer.parseInt(TFCantidad.getText()), TADescripcion.getText(),Vendedor);
            this.DefectuosoAdministrador.showMensajes(Mensaje);
            DefectuososModelo Modelo = new DefectuososModelo(Producto.getIdProductos(), Producto.getNombre(), Integer.parseInt(TFCantidad.getText()),Vendedor.getNombre()+" "+Vendedor.getApellido(),  TADescripcion.getText());
            dataDefectuosos.add(Modelo);
            TablaDefectuosos.setItems(dataDefectuosos);
            TFIdProducto.setText("");
            TFCantidad.setText("");
            TADescripcion.setText("");
            TFVendedor.setText("");
            lblVendedor.setText("");
        }
    }
    
    public void SacarProductoDefectusos(ActionEvent evnet)
    {
        DefectuososModelo Seleccionado = (DefectuososModelo)TablaDefectuosos.getSelectionModel().getSelectedItem();
        int Index = TablaDefectuosos.getSelectionModel().getSelectedIndex();
        if(Seleccionado == null)
        {
            this.DefectuosoAdministrador.showMensajes("Debe Seleccionar un Dato");
        }
        else
        {
            Productos Buscar = new IProductos().buscarProductoPorId(Seleccionado.getId());
            int IdProducto  = 0;
            for(ProductosDefectuoso Producto: new IProductosDefectuoso().listaDeProductosDefectuosos())
            {
                if(Producto.getProductosidProductos().equals(Buscar))
                {
                    IdProducto = Producto.getIdProductoDefectuoso();
                }
            }
            String Mensaje =  new IProductosDefectuoso().eliminar(IdProducto);
            dataDefectuosos.remove(Index);
            TablaDefectuosos.setItems(dataDefectuosos);
            this.DefectuosoAdministrador.showMensajes(Mensaje);
        }
    }
    
    private void LLenarTablaD()
    {
        if(new IProductosDefectuoso().listaDeProductosDefectuosos() != null)
        {
            dataDefectuosos.clear();
            for(ProductosDefectuoso Producto: new IProductosDefectuoso().listaDeProductosDefectuosos())        
            {
                DefectuososModelo Modelo = new DefectuososModelo(Producto.getProductosidProductos().getIdProductos(), Producto.getProductosidProductos().getNombre(), Producto.getCantidad(), Producto.getVendedoresIdvendedores().getNombre() +" "+ Producto.getVendedoresIdvendedores().getApellido(),  Producto.getDescripcion());
                dataDefectuosos.add(Modelo);
            }
            TablaDefectuosos.setItems(dataDefectuosos);
        }
    }
    
    private Vendedores BuscarVendedor(String TFVendedor)
    {
        int IdVendedor = Integer.parseInt(TFVendedor);
        Vendedores Vendedor = new IVendedores().buscarVendedorPorId(IdVendedor);        
        return Vendedor;
    }
    
    public void Vendedor(ActionEvent Evnet)
    {
        Vendedores Vendedor = BuscarVendedor(TFVendedor.getText());
        if(Vendedor != null)
        {
            lblVendedor.setText(Vendedor.getNombre()+" "+Vendedor.getApellido());
        }
        else
        {
            this.DefectuosoAdministrador.showMensajes("Vendedor no Encontrado");
        }
    }
}

