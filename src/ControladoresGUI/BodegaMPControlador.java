/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ControladoresGUI;

import AdministradoresGUI.BodegaMPAdministrador;
import EntidadesJPA.BodegaProduccion;
import GestorDeTablasJPA.IBodegaProduccion;
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
public class BodegaMPControlador 
{
    BodegaMPAdministrador BodegaMPAdministrador;
    @FXML
    public TableView TablaBodegas;
    public TableColumn Id;
    public TableColumn Nombre;
    public TextField TFBusqueda;
    private ObservableList<BodegaModelo> data;
    
    public void initialize() {}
  
    public void initManager(final BodegaMPAdministrador BodegaMPAdministrador)
    {
        this.BodegaMPAdministrador = BodegaMPAdministrador;
        data = FXCollections.observableArrayList();
        Id = new TableColumn();
        Nombre = new TableColumn();
        CargarColumnas();
    }
    
    public void abrirNuevaBodegaMP()
    {
        this.BodegaMPAdministrador.showNuevaBodegaMP();
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
            
            IBodegaProduccion busqueda = new IBodegaProduccion();
            List<BodegaProduccion> Bodega = busqueda.listaDeBodegasDeProduccion();
            if(Bodega != null)
            {
                for(BodegaProduccion nuevo:Bodega )
                {
                    BodegaModelo BodegaModelo = new BodegaModelo(nuevo.getIdbodegaProduccion(), nuevo.getNombre());
                    data.add(BodegaModelo);
                }
            }
            else
            {
                this.BodegaMPAdministrador.showMensajes("No Existen Bodegas de Materia Prima");
            }
        }
        else
        {
            IBodegaProduccion busqueda = new IBodegaProduccion();
            List<BodegaProduccion> Bodegas = busqueda.buscarListaBodegasPorNombre(TFBusqueda.getText());
            if(Bodegas != null)
            {
                for(BodegaProduccion Bodega:Bodegas)
                {
                    BodegaModelo Modelo = new BodegaModelo(Bodega.getIdbodegaProduccion(), Bodega.getNombre());
                    data.add(Modelo);
                }
            }
            else
            {
                this.BodegaMPAdministrador.showMensajes("No Existen Bodegas de Materia Prima que Contenga(n) esa(s) Letra(s)");
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
            this.BodegaMPAdministrador.showMensajes("Debe Selecionar un Dato");
        }
        else
        {
            BodegaProduccion Bodega = new IBodegaProduccion().buscarBodegaPorId(Seleccionado.getId());
            Bodega.setEliminado(true);
            String Mensaje = new IBodegaProduccion().eleiminar(Bodega);
            this.BodegaMPAdministrador.showMensajes(Mensaje);
            if(Mensaje.equals("Bodega Eliminada Correctamente"))
            {
                data.remove(indice);
                TablaBodegas.setItems(data);
            }
        }
    }
    
    public void EditarBodegaMP(ActionEvent Event)
    {
        BodegaModelo Seleccionado = (BodegaModelo)TablaBodegas.getSelectionModel().getSelectedItem();
        if(Seleccionado == null)
        {
            this.BodegaMPAdministrador.showMensajes("Debe Seleccionar un Dato");
        }
        else
        {
            BodegaProduccion Editar = new BodegaProduccion();
            Editar.setIdbodegaProduccion(Seleccionado.getId());
            Editar.setNombre(Seleccionado.getNombre());
            this.BodegaMPAdministrador.showEditarBodegaMP(Editar);
        }
    }
}

