/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ControladoresGUI;

import AdministradoresGUI.FabricacionAdministrador;
import EntidadesJPA.MateriaPrima;
import EntidadesJPA.Productos;
import Especiales.FabricacionDeProductos;
import Especiales.Validaciones;
import GestorDeTablasJPA.IMateriaPrima;
import GestorDeTablasJPA.IProduccion;
import GestorDeTablasJPA.IProductos;
import Modelos.MateriaPrimaModelo;
import java.util.Date;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;

/**
 *
 * @author luis__000
 */
public class FabricacionControlador 
{
    FabricacionAdministrador FabricacionAdministrador;
    @FXML
    public TextField TFProducto;
    public TextField TFCantidad;
    public TextField TFMateriaPrima;
    public TextField TFCantidadMP;
    public TableView TablaProductos;
    public TableColumn IdProductos;
    public TableColumn NombreProductos;
    public TableColumn PresentacionProductos;
    public TableColumn CantidadProductos;
    public TableView TablaMateriaPrima;
    public TableColumn IdMateriaPrima;
    public TableColumn NombreMateriaPrima;
    public TableColumn PresentacionMateriaPrima;
    public TableColumn CantidadMateriaPrima;
    public Label LblNombreProducto;
    public Label LblNombreMP;
    
    public RadioButton RBIngresar;
    public RadioButton RBExtraer;
    
    private ObservableList<MateriaPrimaModelo> dataProductos;
    private ObservableList<MateriaPrimaModelo> dataMateriaPrima;
    public VBox pnlFunciones;
    private Validaciones validar ;
    private IProductos gestorProductos;
    private IMateriaPrima gestorMateriaPrima;
    
   private FabricacionDeProductos fabricacionGestor;
   private IProduccion produccionGestor;
    
    public void initialize() {}
  
    public void initManager(final FabricacionAdministrador FabricacionAdministrador)
    {
        this.FabricacionAdministrador = FabricacionAdministrador;
        this.fabricacionGestor = new FabricacionDeProductos();
        this.produccionGestor = new IProduccion();
        IdProductos = new TableColumn();
        NombreProductos = new TableColumn();
        PresentacionProductos = new TableColumn();
        CantidadProductos = new TableColumn();
        IdMateriaPrima = new TableColumn();
        NombreMateriaPrima = new TableColumn();
        PresentacionMateriaPrima = new TableColumn();
        CantidadMateriaPrima = new TableColumn();
        dataProductos = FXCollections.observableArrayList();
        dataMateriaPrima = FXCollections.observableArrayList();
        CargarColumnasProductos();
        CargarColumnasMateriaPrima();
        this.validar = new Validaciones();
        this.gestorProductos = new IProductos ();
        this.gestorMateriaPrima = new IMateriaPrima ();
    }
    
    private void CargarColumnasProductos()
    {
        IdProductos.setCellValueFactory(new PropertyValueFactory<MateriaPrimaModelo,Integer>("Id"));
        NombreProductos.setCellValueFactory(new PropertyValueFactory<MateriaPrimaModelo,String>("Nombre"));
        CantidadProductos.setCellValueFactory(new PropertyValueFactory<MateriaPrimaModelo,Integer>("Cantidad"));
        PresentacionProductos.setCellValueFactory(new PropertyValueFactory<MateriaPrimaModelo,String>("Presentacion"));
        IdProductos.setText("Id");
        NombreProductos.setText("Nombre");
        CantidadProductos.setText("Cantidad");
        PresentacionProductos.setText("Presentacion");
        IdProductos.setPrefWidth(50);
        NombreProductos.setPrefWidth(200);
        CantidadProductos.setPrefWidth(200);
        PresentacionProductos.setPrefWidth(200);
        TablaProductos.getColumns().add(IdProductos);
        TablaProductos.getColumns().add(NombreProductos);
        TablaProductos.getColumns().add(CantidadProductos);
        TablaProductos.getColumns().add(PresentacionProductos);
    }
    
    private void CargarColumnasMateriaPrima()
    {
        IdMateriaPrima.setCellValueFactory(new PropertyValueFactory<MateriaPrimaModelo,Integer>("Id"));
        NombreMateriaPrima.setCellValueFactory(new PropertyValueFactory<MateriaPrimaModelo,String>("Nombre"));
        CantidadMateriaPrima.setCellValueFactory(new PropertyValueFactory<MateriaPrimaModelo,Integer>("Cantidad"));
        PresentacionMateriaPrima.setCellValueFactory(new PropertyValueFactory<MateriaPrimaModelo,String>("Presentacion"));
        IdMateriaPrima.setText("Id");
        NombreMateriaPrima.setText("Nombre");
        CantidadMateriaPrima.setText("Cantidad");
        PresentacionMateriaPrima.setText("Presentacion");
        IdMateriaPrima.setPrefWidth(50);
        NombreMateriaPrima.setPrefWidth(200);
        CantidadMateriaPrima.setPrefWidth(200);
        PresentacionMateriaPrima.setPrefWidth(200);
        TablaMateriaPrima.getColumns().add(IdMateriaPrima);
        TablaMateriaPrima.getColumns().add(NombreMateriaPrima);
        TablaMateriaPrima.getColumns().add(CantidadMateriaPrima);
        TablaMateriaPrima.getColumns().add(PresentacionMateriaPrima);
    }
    

    private void LLenarTablaProductos()
    {
        IProductos busqueda = new IProductos();
        Productos Producto = busqueda.buscarProductoPorId(Integer.parseInt(TFProducto.getText()));
        MateriaPrimaModelo Modelo = new MateriaPrimaModelo(Producto.getIdProductos(), Producto.getNombre(), Float.parseFloat(TFCantidad.getText()), Producto.getPresentacion());
        dataProductos.add(Modelo);
    }
    
    private void LLenarTablaMateriaPrima()
    {
        IMateriaPrima busqueda = new IMateriaPrima();
        MateriaPrima Producto = busqueda.buscarMateriaPrimaPorId(Integer.parseInt(TFMateriaPrima.getText()));
        MateriaPrimaModelo Modelo = new MateriaPrimaModelo(Producto.getIdmateriaPrima(), Producto.getNombre(), Float.parseFloat(TFCantidadMP.getText()), Producto.getPresentacion());
        dataMateriaPrima.add(Modelo);
    }
    
    public void AgregarProducto()
    {
        if (buscarProductoInsertado (Integer.parseInt(this.TFProducto.getText())))
        {
            this.FabricacionAdministrador.showMensajes("Producto Ya Ingresado, Edite el que Ya Ingreso");
            this.TFProducto.setText("");
            this.TFCantidad.setText("");
            this.TFProducto.requestFocus();
        }   
        else
        if (!this.validar.ValidarNumeros(this.TFProducto.getText())||(this.TFProducto.getText().isEmpty()))
        {
            this.FabricacionAdministrador.showMensajes("Verificar Id del Producto");
            this.TFProducto.requestFocus();
        }
        else
            if (!this.validar.ValidarMontos(this.TFCantidad.getText())||(this.TFCantidad.getText().isEmpty()))
            {
                this.FabricacionAdministrador.showMensajes("Verificar la Cantidad Producida");
                this.TFCantidad.requestFocus();
            }
        else
        {
            LLenarTablaProductos();
            TablaProductos.setItems(dataProductos);
        }
    }
    
    public boolean buscarProductoInsertado (int idProducto)
    {
        for (MateriaPrimaModelo modelo : this.dataProductos)
        {
            if (modelo.getId().equals(idProducto))
            {
                return true;
            }
        }
        return false;
    }
     public boolean buscarMateriaPrimaInsertada (int id)
    {
        for (MateriaPrimaModelo modelo : this.dataMateriaPrima)
        {
            if (modelo.getId()==id)
            {
                return true;
            }
        }
        return false;
    }
    
    
    
    public void buscarProducto ()
    {
        Productos producto = new Productos ();
        if (!this.validar.ValidarNumeros(this.TFProducto.getText())||(this.TFProducto.getText().isEmpty()))
        {
            this.FabricacionAdministrador.showMensajes("Verificar Id del Producto");
            this.TFProducto.requestFocus();
        }
        else
        if ( this.gestorProductos.buscarProductoPorId(Integer.parseInt(this.TFProducto.getText()))==null)
        {
            this.LblNombreProducto.setText("Producto no Encontrado, Verifique Id ");
            this.TFProducto.requestFocus();
        }
        else
        {
            producto =  this.gestorProductos.buscarProductoPorId(Integer.parseInt(this.TFProducto.getText()));
            this.LblNombreProducto.setText(producto.getNombre()+producto.getPresentacion());
            this.TFCantidad.requestFocus();
        }
    }
    
    public void buscarMateriaPrima ()
    {
        MateriaPrima MP = this.gestorMateriaPrima.buscarMateriaPrimaPorId(Integer.parseInt(this.TFMateriaPrima.getText()));
        if (!(this.validar.ValidarNumeros(this.TFMateriaPrima.getText()))||(this.TFMateriaPrima.getText().isEmpty()))
        {
            this.FabricacionAdministrador.showMensajes("Verifique el Id de Materia Prima");
            this.TFMateriaPrima.requestFocus();
        }
        else
        if ((MP == null)||(MP.getEliminado()))
        {
            this.LblNombreMP.setText("Materia Prima No Encontrada, Verifique Id");
            this.TFMateriaPrima.requestFocus();
        }    
        else
        {
            this.LblNombreMP.setText(MP.getNombre());
            this.TFCantidadMP.requestFocus();
        }
            
    }
    
    public void AgregarMateriaPrima()
    {
        if (this.buscarMateriaPrimaInsertada(Integer.parseInt(this.TFMateriaPrima.getText())))
        {
            this.FabricacionAdministrador.showMensajes("Materia Prima ya Fue Ingresada, Edite La que Ya Ingreso");
            this.TFMateriaPrima.setText("");
            this.CantidadMateriaPrima.setText("");
            this.TFMateriaPrima.requestFocus();
        }
        else
        if (!(this.validar.ValidarNumeros(this.TFMateriaPrima.getText()))||(this.TFMateriaPrima.getText().isEmpty()))
        {
            this.FabricacionAdministrador.showMensajes("Verifique el Id de Materia Prima");
            this.TFMateriaPrima.requestFocus();
        }
        else
            if((!this.validar.ValidarMontos(this.TFCantidadMP.getText()))||(this.TFCantidadMP.getText().isEmpty()))
            {
                this.FabricacionAdministrador.showMensajes("Verifique la Cantidad de Materia Prima"); 
                this.TFCantidadMP.requestFocus();
            }
        else
            {
                LLenarTablaMateriaPrima();
                TablaMateriaPrima.setItems(dataMateriaPrima);
            }
    }
    
    public void SacarProducto()
    {
        MateriaPrimaModelo Seleccionado = (MateriaPrimaModelo)TablaProductos.getSelectionModel().getSelectedItem();
        int indice = TablaProductos.getSelectionModel().getSelectedIndex();
        if(Seleccionado == null)
        {
            this.FabricacionAdministrador.showMensajes("Debe Seleccionar un Dato");
        }
        else
        {
            dataProductos.remove(indice);
            TablaProductos.setItems(dataProductos);
        }
    }
    
    public void SacarMateriaPrima()
    {
        MateriaPrimaModelo Seleccionado = (MateriaPrimaModelo)TablaMateriaPrima.getSelectionModel().getSelectedItem();
        int indice = TablaMateriaPrima.getSelectionModel().getSelectedIndex();
        if(Seleccionado == null)
        {
            this.FabricacionAdministrador.showMensajes("Debe Seleccionar un Dato");
        }
        else
        {
            dataMateriaPrima.remove(indice);
            TablaMateriaPrima.setItems(dataMateriaPrima);
        }
    }
    
    public void meterAlInventarioProducto (ActionEvent event)
    {
        java.util.Date fecha = new Date();
        try {
        Productos producto = new Productos ();
        
        for (MateriaPrimaModelo modelo:this.dataProductos)
        {
            producto = this.gestorProductos.buscarProductoPorId(modelo.getId());
            this.produccionGestor.guardar(producto, fecha,  Math.round(modelo.getCantidad()));
            this.fabricacionGestor.ingresarAlInventario(modelo.getId(),  Math.round(modelo.getCantidad()));            
        }
        this.FabricacionAdministrador.showMensajes("Productos Transladados Correctamente");
        this.limpiarProductos();
        }
        catch (Exception e)
        {
            this.FabricacionAdministrador.showMensajes("Error Al Ingresar Los Productos");
        }
        
    }
    
    public void sacarDelInventarioProducto (ActionEvent event)
    {
        java.util.Date fecha = new Date();
        try {
        Productos producto = new Productos ();
        
        for (MateriaPrimaModelo modelo:this.dataProductos)
        {
            producto = this.gestorProductos.buscarProductoPorId(modelo.getId());
            this.produccionGestor.guardar(producto, fecha,  Math.round(modelo.getCantidad()));
            this.fabricacionGestor.sacarDelInventario(modelo.getId(),  Math.round(modelo.getCantidad()));            
        }
        this.FabricacionAdministrador.showMensajes("Productos Retirados Correctamente");
        this.limpiarProductos();
        }
        catch (Exception e)
        {
            this.FabricacionAdministrador.showMensajes("Error Al Retirar Productos");
        }
        
    }
    
    public void sacarDelInventarioMateriaPrima (ActionEvent event)
    {
        try
        {
        for (MateriaPrimaModelo modelo:this.dataMateriaPrima)
        {
            this.fabricacionGestor.sacarMateriaPrimaDeInventario(modelo.getId(),modelo.getCantidad());  
        }  
        this.FabricacionAdministrador.showMensajes("Inventario de Materia Prima Actualizado");
        this.limpiarMateriaPrima();
        }
        catch (Exception e)
        {
            this.FabricacionAdministrador.showMensajes("Error Al Sacar Materia Prima de Bodega");
        }
    }
    
    public void verificarIdProducto (ActionEvent event)
    {
        IProductos productoGestor = new IProductos();
        Productos producto = new Productos();
        if (!this.validar.ValidarNumeros(this.TFProducto.getText())||(this.TFProducto.getText().isEmpty()))
        {
            this.FabricacionAdministrador.showMensajes("Verificar Id del Producto");
            this.LblNombreProducto.setText("");
        }
        else
        if ((productoGestor.buscarProductoPorId(Integer.parseInt(this.TFProducto.getText())))==null)
        {
            this.FabricacionAdministrador.showMensajes("Producto No Encontrado");
            this.TFProducto.requestFocus();
        }
        else 
        {
            producto = productoGestor.buscarProductoPorId(Integer.parseInt(this.TFProducto.getText()));
            this.LblNombreProducto.setText(producto.getNombre());
            this.TFCantidad.requestFocus();
        }
    }
    
    public void manejoDeInventario ()
    {
        if (this.RBIngresar.isSelected())
        {
            this.meterAlInventarioProducto(null);
        }
        if (this.RBExtraer.isSelected())
        {
            this.sacarDelInventarioProducto(null);
        }
    }
    
    
    public void verificarIdMateriaPrima (ActionEvent event)
    {
        IMateriaPrima materiaPrimaGestor = new IMateriaPrima();
        MateriaPrima materiaPrima = new MateriaPrima ();
         if (!(this.validar.ValidarNumeros(this.TFMateriaPrima.getText()))||(this.TFMateriaPrima.getText().isEmpty()))
        {
            this.FabricacionAdministrador.showMensajes("Verifique el Id de Materia Prima");
            this.LblNombreMP.setText("");
        }
        else
        if ((materiaPrimaGestor.buscarMateriaPrimaPorId(Integer.parseInt(this.TFMateriaPrima.getText())))==null)
        {
            this.FabricacionAdministrador.showMensajes("Materia Prima No Encontrada");
            this.TFMateriaPrima.requestFocus();
        }
        else
        {
            materiaPrima = materiaPrimaGestor.buscarMateriaPrimaPorId(Integer.parseInt(this.TFMateriaPrima.getText()));
            this.LblNombreMP.setText(materiaPrima.getNombre());
            this.TFCantidadMP.requestFocus();
        }
    }
    
    public void limpiarProductos (ActionEvent event)
    {
        this.TFCantidad.setText("");
        this.TFProducto.setText("");
        this.TFProducto.requestFocus();
        this.dataProductos.clear();
        this.TablaProductos.setItems(dataProductos);
    }
    
    public void limpiarMateriaPrima (ActionEvent event)
    {
        this.TFCantidadMP.setText("");
        this.TFMateriaPrima.setText("");
        this.TFMateriaPrima.requestFocus();
        this.dataMateriaPrima.clear();
        this.TablaMateriaPrima.setItems(dataMateriaPrima);
    }
    
    public void limpiarProductos ()
    {
        this.TFCantidad.setText("");
        this.TFProducto.setText("");
        this.TFProducto.requestFocus();
        this.dataProductos.clear();
        this.TablaProductos.setItems(dataProductos);
    }
    
    public void limpiarMateriaPrima ()
    {
        this.TFCantidadMP.setText("");
        this.TFMateriaPrima.setText("");
        this.TFMateriaPrima.requestFocus();
        this.dataMateriaPrima.clear();
        this.TablaMateriaPrima.setItems(dataMateriaPrima);
    }
    
    public void cambioDeSeleccionExtraer () //este procedimiento funciona para que al momento de seleccionar un radio button se des-seleccione el otro, para retirar y extraer productos
    {
       if (this.RBExtraer.isSelected())
       {
           this.RBExtraer.setSelected(true);
           this.RBIngresar.setSelected(false);
           this.FabricacionAdministrador.showMensajes("Se Retirarán productos del Inventario");
       }
    }
    
    public void cambioDeSeleccionIngresar () //este procedimiento funciona para que al momento de seleccionar un radio button se des-seleccione el otro, para retirar y extraer productos
    {
       if (this.RBIngresar.isSelected())
       {
           this.RBIngresar.setSelected(true);
           this.RBExtraer.setSelected(false);
           this.FabricacionAdministrador.showMensajes("Se Ingresarán productos del Inventario");
       }
    }
   
}

