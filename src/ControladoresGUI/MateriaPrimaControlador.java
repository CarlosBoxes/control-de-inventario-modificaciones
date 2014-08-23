/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ControladoresGUI;

import AdministradoresGUI.MateriaPrimaAdministrador;
import EntidadesJPA.BodegaProduccion;
import EntidadesJPA.MateriaPrima;
import Especiales.GeneradordeReportes;
import GestorDeTablasJPA.IBodegaProduccion;
import GestorDeTablasJPA.IInventarioMateriaPrima;
import GestorDeTablasJPA.IMateriaPrima;
import Modelos.MateriaPrimaModelo;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
/**
 *
 * @author luis__000
 */
public class MateriaPrimaControlador 
{
    MateriaPrimaAdministrador MateriaPrimaAdministrador;
    @FXML
    public TableView TablaMateriaPrima;
    public TableColumn Id;
    public TableColumn Nombre;
    public TableColumn Cantidad;
    public TableColumn Presentacion;
    public TextField TFBusqueda;
    public ComboBox ComBoBodega;
    private ObservableList<MateriaPrimaModelo> data;
    
    public void initialize() {}
  
    public void initManager(final MateriaPrimaAdministrador MateriaPrimaAdministrador)
    {
        this.MateriaPrimaAdministrador = MateriaPrimaAdministrador;
        data = FXCollections.observableArrayList();
        Id = new TableColumn();
        Nombre = new TableColumn();
        Cantidad = new TableColumn();
        Presentacion = new TableColumn();
        CargarColumnas();
        llenarBodega ();
    }
    
    public void abrirNuevaMateriaPrima()
    {
        if(new IBodegaProduccion().listaDeBodegasDeProduccion() != null)
        {
            this.MateriaPrimaAdministrador.showNuevaMateriaPrima();
        }
        else
        {
            this.MateriaPrimaAdministrador.showMensajes("No hay Bodegas Ingresadas, Diríjase a Bodegas de Materia Prima");
        }
    }
    
    private void CargarColumnas() 
    {
        Id.setCellValueFactory(new PropertyValueFactory<MateriaPrimaModelo,Integer>("Id"));
        Nombre.setCellValueFactory(new PropertyValueFactory<MateriaPrimaModelo,String>("Nombre"));
        Cantidad.setCellValueFactory(new PropertyValueFactory<MateriaPrimaModelo,Integer>("Cantidad"));
        Presentacion.setCellValueFactory(new PropertyValueFactory<MateriaPrimaModelo,String>("Presentacion"));
        Id.setText("Id");
        Nombre.setText("Nombre");
        Cantidad.setText("Cantidad");
        Presentacion.setText("Presentacion");
        Id.setPrefWidth(50);
        Nombre.setPrefWidth(200);
        Cantidad.setPrefWidth(200);
        Presentacion.setPrefWidth(200);
        TablaMateriaPrima.getColumns().add(Id);
        TablaMateriaPrima.getColumns().add(Nombre);
        TablaMateriaPrima.getColumns().add(Cantidad);
        TablaMateriaPrima.getColumns().add(Presentacion);
    }
    
    public void LLenarTabla()
    {
        if("".equals(TFBusqueda.getText()))
        {            
            IMateriaPrima busqueda = new IMateriaPrima();
            List<MateriaPrima> Bodega = busqueda.listaDeMateriaPrima();
            if(Bodega != null)
            {
                for(MateriaPrima nuevo:Bodega )
                {
                    IInventarioMateriaPrima buscar = new IInventarioMateriaPrima();
                    float cantidad = buscar.buscarInventarioPorIdMateriaPrima(nuevo).getCantidad();
                    MateriaPrimaModelo BodegaModelo = new MateriaPrimaModelo(nuevo.getIdmateriaPrima(), nuevo.getNombre(), cantidad, nuevo.getPresentacion());
                    data.add(BodegaModelo);
                }
            }
            else
            {
                this.MateriaPrimaAdministrador.showMensajes("No Existen Materia Prima");
            }
        }
        else
        {
            IMateriaPrima busqueda = new IMateriaPrima();
            List<MateriaPrima> LMateriaPrima = busqueda.buscarListaMateriaPrimaPorNombre(TFBusqueda.getText());
            if(LMateriaPrima != null)
            {
                for(MateriaPrima MateriaPrima:LMateriaPrima)
                {
                    IInventarioMateriaPrima buscar = new IInventarioMateriaPrima();
                    float cantidad = buscar.buscarInventarioPorIdMateriaPrima(MateriaPrima).getCantidad();
                    MateriaPrimaModelo ModeloMateriaPrima = new MateriaPrimaModelo(MateriaPrima.getIdmateriaPrima(),MateriaPrima.getNombre(), cantidad, MateriaPrima.getPresentacion());
                    data.add(ModeloMateriaPrima); 
                }
            }
            else
            {
                this.MateriaPrimaAdministrador.showMensajes("No Existe Materia Prima que Contenga esa(s) Letra(s)");
            }
        }
    }
    
    public void Buscar(ActionEvent event)
    {
        data.clear();
        TablaMateriaPrima.setItems(data);
        LLenarTabla();
        TablaMateriaPrima.setItems(data);
    }
    
    public void BorrarMateriaPrima(ActionEvent event)
    {
        MateriaPrimaModelo Seleccionado = (MateriaPrimaModelo) TablaMateriaPrima.getSelectionModel().getSelectedItem();
        int indice = TablaMateriaPrima.getSelectionModel().getSelectedIndex();
        if(Seleccionado == null)
        {
            this.MateriaPrimaAdministrador.showMensajes("Debe Selecionar un Dato");
        }
        else
        {
            MateriaPrima MateriaPrima = new IMateriaPrima().buscarMateriaPrimaPorId(Seleccionado.getId());
            MateriaPrima.setEliminado(true);
            String Mensaje = new IMateriaPrima().eliminar(MateriaPrima);
            this.MateriaPrimaAdministrador.showMensajes(Mensaje);
            if(Mensaje.equals("Materia Prima Eliminada Correctamente"))
            {
                data.remove(indice);
                TablaMateriaPrima.setItems(data);
            }
        }
    }
    
    public void EditarMateriaPrima(ActionEvent Event)
    {
        MateriaPrimaModelo Seleccionado = (MateriaPrimaModelo)TablaMateriaPrima.getSelectionModel().getSelectedItem();
        if(Seleccionado == null)
        {
            this.MateriaPrimaAdministrador.showMensajes("Debe Seleccionar un Dato");
        }
        else
        {
            MateriaPrima Editar = new IMateriaPrima().buscarMateriaPrimaPorId(Seleccionado.getId());
            this.MateriaPrimaAdministrador.showEditarMateriaPrima(Editar);
        }
    }
    
    public void AbrirReporte(ActionEvent Event)
    {
        GeneradordeReportes Generar = new GeneradordeReportes();
        Generar.AbrirReporte("InventarioMateriaPrima.jasper");
    }
    
    public void AbrirReporteBodega(ActionEvent event)
    {
        if(ComBoBodega.getValue() != null)
        {
            BodegaProduccion Bodega = new IBodegaProduccion().buscarBodegaPorNombre((String)ComBoBodega.getValue());
            GeneradordeReportes Generar = new GeneradordeReportes();
            Generar.AbrirReporte("InventarioMateriaPrimaBodega.jasper", Bodega.hashCode());
        }
        else
        {
            this.MateriaPrimaAdministrador.showMensajes("Seleccione una Bodega");
        }
    }
    
    public void llenarBodega ()
    {         
         List<String> list = new ArrayList<String>();
         if (new IBodegaProduccion().listaDeBodegasDeProduccion()!=null)
         {
            for (BodegaProduccion bodega:new IBodegaProduccion().listaDeBodegasDeProduccion())
            {
                list.add(bodega.getNombre());
            }
            ObservableList<String> observableList = FXCollections.observableList(list);
            ComBoBodega.setItems(observableList);
         }
    }
}

