/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ControladoresGUI;

import AdministradoresGUI.ProductosVendedorAdministrador;
import EdicionTablas.EditarPrecio;
import EntidadesJPA.Productos;
import EntidadesJPA.ProductosVendedores;
import Especiales.Validaciones;
import GestorDeTablasJPA.IProductos;
import GestorDeTablasJPA.IProductosVendedor;
import Modelos.ListaProductosModelo;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author luis__000
 */
public class ProductosVendedorControlador
{
    
    ProductosVendedorAdministrador ProductosVendedorAdministrador;
    @FXML
    public TextField TFId;
    public TextField TFPrecio;
    public Label LblNombre;
    public TableView TablaLista;
    public TableColumn Id;
    public TableColumn Producto;
    public TableColumn Precio;
    private ObservableList<ListaProductosModelo> data;
    private Validaciones Validar;
    private boolean banderaCambioPrecio = false;
    Callback<TableColumn, TableCell> cellFactory;

    public void initialize() {}
  
    public void initManager(final ProductosVendedorAdministrador ProductosVendedorAdministrador) {
        this.ProductosVendedorAdministrador = ProductosVendedorAdministrador;
        this.Id = new TableColumn();
        this.Producto = new TableColumn();
        this.Precio = new TableColumn();
        this.data = FXCollections.observableArrayList();
        this.banderaCambioPrecio = false;
        Validar = new Validaciones();
        cellFactory = new Callback<TableColumn, TableCell>() 
        {
            public TableCell call(TableColumn p) {
                return new EditarPrecio();
            }
	};
        CargarColumnas();
        LlenarTabla();
    }
    
    private void CargarColumnas()
    {
        Id.setCellValueFactory(new PropertyValueFactory<ListaProductosModelo,Integer>("IdProducto"));
        Producto.setCellValueFactory(new PropertyValueFactory<ListaProductosModelo,String>("Nombre"));
        Precio.setCellValueFactory(new PropertyValueFactory<ListaProductosModelo,String>("Precio"));
        Precio.setCellFactory(cellFactory);
        Precio.setOnEditCommit(
                new EventHandler<CellEditEvent<ListaProductosModelo,String>>()
                {
                    @Override
                    public void handle(CellEditEvent<ListaProductosModelo,String> t)
                    {
                        ListaProductosModelo L = (ListaProductosModelo) t.getTableView().getItems().get(t.getTablePosition().getRow());
                        L.setPrecio(t.getNewValue());
                        banderaCambioPrecio = true;
                    }
                });
        Id.setText("Id");
        Producto.setText("Producto");
        Precio.setText("Precio");
        Id.setPrefWidth(50);
        Producto.setPrefWidth(400);
        Precio.setPrefWidth(150);
        TablaLista.getColumns().add(Id);
        TablaLista.getColumns().add(Producto);
        TablaLista.getColumns().add(Precio);
        TFId.requestFocus();
    }
    
    private void LlenarTabla()
    {
       if(!this.ProductosVendedorAdministrador.Tipo.getProductosvendedoresCollection().isEmpty())
       {
           for(ProductosVendedores Lista:this.ProductosVendedorAdministrador.Tipo.getProductosvendedoresCollection())
           {
               ListaProductosModelo Productos = new ListaProductosModelo(Lista.getIdlisitaproductosvendedores(), Lista.getProductosidProductos().getIdProductos(), Lista.getProductosidProductos().getNombre()+" "+Lista.getProductosidProductos().getPresentacion(), String.valueOf(Lista.getPrecioespecial()));
               data.add(Productos);
           }
       }
       TablaLista.setItems(data);
    }
    
    public void BuscarProducto(ActionEvent evetn)
    {
        if(Validar.ValidarNumeros(TFId.getText()))
        {
            Productos Productos = new IProductos().buscarProductoPorId(Integer.parseInt(TFId.getText()));
            LblNombre.setText(Productos.getNombre()+" "+Productos.getPresentacion());
            TFPrecio.requestFocus();
        }
        else
        {
            this.ProductosVendedorAdministrador.showMensajes("El Id No es Valido");
        }
    }
    
    public void AgregarProducto(ActionEvent event)
    {
        if(Validar.ValidarNumeros(TFId.getText()) && Validar.ValidarMontos(TFPrecio.getText()))
        {
            if(VerificarProducto(TFId.getText()))
            {
                Productos Productos = new IProductos().buscarProductoPorId(Integer.parseInt(TFId.getText()));
                ListaProductosModelo Modelo = new ListaProductosModelo(0, Productos.getIdProductos(), Productos.getNombre()+" "+Productos.getPresentacion(), TFPrecio.getText());
                data.add(Modelo);
                TablaLista.setItems(data);
                TFId.setText("");
                TFPrecio.setText("");
                TFId.requestFocus();
            }
            else
            {
                this.ProductosVendedorAdministrador.showMensajes("El Producto ya está en la Lista");
                TFId.requestFocus();
            }
        }
        else if(!Validar.ValidarNumeros(TFId.getText()))
        {
            this.ProductosVendedorAdministrador.showMensajes("El Id No es Valido");
        }
        else if(!Validar.ValidarMontos(TFPrecio.getText()))
        {
            this.ProductosVendedorAdministrador.showMensajes("El Precio No es Valido");
        }
    }
    
    private boolean VerificarProducto(String Id)
    {
        for(ListaProductosModelo Lista: data)
        {
            if(Integer.parseInt(Id) == Lista.getIdProducto())
            {
                return false;
            }
        }
        return true;
    }
    
    public void Guardar(ActionEvent event)
    {
        boolean verificar = VerificarPrecios();
        if(!verificar)
        {
            this.ProductosVendedorAdministrador.showMensajes("Verifique los Precios en la Tabla");
        }
        else
        {
            List<ProductosVendedores> Guardar = new ArrayList();
            List<ProductosVendedores> Modificar = new ArrayList();
            for(ListaProductosModelo Lista: data)
            {
                if (!this.datosNuevo(Lista.getIdProducto()) || this.banderaCambioPrecio)
                {
                    if(Lista.getId() == 0)
                    {
                        ProductosVendedores Productos = new ProductosVendedores();
                        Productos.setPrecioespecial(Float.parseFloat(Lista.getPrecio()));
                        Productos IdProducto = new IProductos().buscarProductoPorId(Lista.getIdProducto());
                        Productos.setProductosidProductos(IdProducto);
                        Productos.setTipovendedoresidTipoVendedores(this.ProductosVendedorAdministrador.Tipo);
                        Guardar.add(Productos);
                    }
                    else
                    {
                        ProductosVendedores Productos = new IProductosVendedor().buscarProductoPorId(Lista.getId());
                        Productos.setPrecioespecial(Float.parseFloat(Lista.getPrecio()));
                        Modificar.add(Productos);
                    }
                }
            }
            new IProductosVendedor().guardar(Guardar);
            new IProductosVendedor().modificar(Modificar);
            this.ProductosVendedorAdministrador.showMensajes("Lista de Productos Guardada");
        }
    }
    
    private boolean VerificarPrecios()
    {
        for(ListaProductosModelo Lista: data)
        {
            if(!Validar.ValidarMontos(Lista.getPrecio()))
            {
                return false;
            }
        }
        return true;
    }
    
    public void Eliminar(ActionEvent event)
    {
        ListaProductosModelo Seleccionado = (ListaProductosModelo) TablaLista.getSelectionModel().getSelectedItem();
        int indice = TablaLista.getSelectionModel().getSelectedIndex();
        if(Seleccionado == null)
        {
            this.ProductosVendedorAdministrador.showMensajes("Debe Selecionar un Dato");
        }
        else
        {
            if(Seleccionado.getId() > 0)
            {
                String Mensaje = new IProductosVendedor().eliminar(Seleccionado.getId());
                this.ProductosVendedorAdministrador.showMensajes(Mensaje);
                if(Mensaje.equals("Producto Eliminado Correctamente"))
                {
                    data.remove(indice);
                    TablaLista.setItems(data);
                }
            }
            else
            {
                data.remove(indice);
                TablaLista.setItems(data);
            }
        }
    }
    
    public void Cancelar(ActionEvent event)
    {
        this.ProductosVendedorAdministrador.cerrarProductosVendedor();
    }
    
    public boolean datosNuevo(int idProducto)
    {
        for(ProductosVendedores Lista:this.ProductosVendedorAdministrador.Tipo.getProductosvendedoresCollection())
           {
               if (Lista.getProductosidProductos().getIdProductos()==idProducto)
               {
                   return true;
               }
           }
        return false;
    }
}
