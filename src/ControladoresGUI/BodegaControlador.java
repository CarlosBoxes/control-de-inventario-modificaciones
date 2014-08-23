/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ControladoresGUI;

import AdministradoresGUI.BodegaAdministrador;
import EntidadesJPA.BodegaProductos;
import GestorDeTablasJPA.IBodegaProductos;
import Modelos.BodegaModelo;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 *
 * @author luis__000
 */
public class BodegaControlador 
{
    BodegaAdministrador BodegaAdministrador;
    @FXML
    public TableView TablaBodegas;
    public TableColumn Id;
    public TableColumn Nombre;
    public TextField TFBusqueda;
    private ObservableList<BodegaModelo> data;
    
    public void initialize() {}
  
    public void initManager(final BodegaAdministrador BodegaAdministrador)
    {
        this.BodegaAdministrador = BodegaAdministrador;
        data = FXCollections.observableArrayList();
        Id = new TableColumn();
        Nombre = new TableColumn();
        CargarColumnas();
    }
    
    public void abrirNuevaBodega()
    {
        this.BodegaAdministrador.showNuevaBodega();
    }

    private void CargarColumnas() 
    {
        Id.setCellValueFactory(new PropertyValueFactory<BodegaModelo,Integer>("Id"));
        Nombre.setCellValueFactory(new PropertyValueFactory<BodegaModelo,String>("Nombre"));
        Id.setText("Id");
        Nombre.setText("Nombre");
        Id.setPrefWidth(50);
        Nombre.setPrefWidth(200);
        TablaBodegas.getColumns().add(Id);
        TablaBodegas.getColumns().add(Nombre);
    }
    
    public void LLenarTabla()
    {
        if("".equals(TFBusqueda.getText()))
        {
            IBodegaProductos busqueda = new IBodegaProductos();
            List<BodegaProductos> Bodega = busqueda.listaDeBodegasDeProductos();
            if(Bodega != null)
            {
                for(BodegaProductos nuevo:Bodega )
                {
                    BodegaModelo BodegaModelo = new BodegaModelo(nuevo.getIdbodega(), nuevo.getNombre());
                    data.add(BodegaModelo);
                }
            }
            else
            {
                this.BodegaAdministrador.showMensajes("No Exitene Bodegas de Productos");
            }
        }
        else
        {
            IBodegaProductos busqueda = new IBodegaProductos();
            List<BodegaProductos> Bodegas = busqueda.buscarListaBodegasPorNombre(TFBusqueda.getText());
            if(Bodegas != null)
            {
                for(BodegaProductos Bodega:Bodegas)
                {
                    BodegaModelo TipoClienteModelo = new BodegaModelo(Bodega.getIdbodega(), Bodega.getNombre());
                    data.add(TipoClienteModelo);
                }
            }
            else
            {
                this.BodegaAdministrador.showMensajes("No Existen Bodegas de Productos que Contenga(n) esa(s) Letra(s)");
            }
        }
    }
    
    public void Buscar(ActionEvent event)
    {
        data.clear();
        TablaBodegas.setItems(data);
        LLenarTabla();
        TablaBodegas.setItems(data);
    }
    
    public void BorrarBodega(ActionEvent event)
    {
        BodegaModelo Seleccionado = (BodegaModelo) TablaBodegas.getSelectionModel().getSelectedItem();
        int indice = TablaBodegas.getSelectionModel().getSelectedIndex();
        if(Seleccionado == null)
        {
            this.BodegaAdministrador.showMensajes("Debe Selecionar un Dato");
        }
        else
        {
            BodegaProductos Bodega = new IBodegaProductos().buscarBodegaPorId(Seleccionado.getId());
            Bodega.setEliminado(true);
            String Mensaje = new IBodegaProductos().eliminar(Bodega);
            this.BodegaAdministrador.showMensajes(Mensaje);
            if(Mensaje.equals("Bodega Eliminada Correctamente"))
            {
                data.remove(indice);
                TablaBodegas.setItems(data);
            }
        }
    }
    
    public void EditarBodega()
    {
        BodegaModelo Seleccionado = (BodegaModelo)TablaBodegas.getSelectionModel().getSelectedItem();
        if(Seleccionado == null)
        {
            this.BodegaAdministrador.showMensajes("Debe Seleccionar un Dato");
        }
        else
        {
            BodegaProductos Editar = new BodegaProductos();
            Editar.setIdbodega(Seleccionado.getId());
            Editar.setNombre(Seleccionado.getNombre());
            this.BodegaAdministrador.showEditarBodega(Editar);
        }
    }
}

