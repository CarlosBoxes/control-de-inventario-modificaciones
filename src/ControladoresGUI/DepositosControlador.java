/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ControladoresGUI;

import AdministradoresGUI.DepositosAdministrador;
import EntidadesJPA.Depositos;
import GestorDeTablasJPA.IBancos;
import GestorDeTablasJPA.IDepositos;
import Modelos.DepositoModelo;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 *
 * @author luis__000
 */
public class DepositosControlador 
{
    DepositosAdministrador DepositosAdministrador;
    @FXML
    public TableView TablaBodegas;
    public TableColumn Id;
    public TableColumn Monto;
    public TableColumn NumeroBoleta;
    public TableColumn Banco;
    public TextField TFBusqueda;
    private ObservableList<DepositoModelo> data;
    public Button BtnEliminar;
    public Button BtnEditar;
    
    public void initialize() {}
  
    public void initManager(final DepositosAdministrador DepositosAdministrador)
    {
        this.DepositosAdministrador = DepositosAdministrador;
        data = FXCollections.observableArrayList();
        Id = new TableColumn();
        Monto = new TableColumn();
        NumeroBoleta = new TableColumn();
        Banco = new TableColumn();
        CargarColumnas();
        if(this.DepositosAdministrador.Usuario.getPuesto().equals("Administrador") || this.DepositosAdministrador.Usuario.getPuesto().equals("Gerente"))
        {
            BtnEliminar.setDisable(false);
            BtnEditar.setDisable(false);
        }
        else
        {
            BtnEliminar.setDisable(true);
            BtnEditar.setDisable(true);
        }
    }
    
    public void abrirNuevoDeposito()
    {
        if(new IBancos().retornarListaDeBancos() != null)
        {
            this.DepositosAdministrador.showNuevoDeposito();
        }
        else
        {
            this.DepositosAdministrador.showMensajes("No hay Bancos Ingresados, Diríjase a Bancos");
        }
    }
    
     private void CargarColumnas() 
    {
        Id.setCellValueFactory(new PropertyValueFactory<DepositoModelo,Integer>("Id"));
        Monto.setCellValueFactory(new PropertyValueFactory<DepositoModelo,Float>("Monto"));
        NumeroBoleta.setCellValueFactory(new PropertyValueFactory<DepositoModelo,String>("NumeroBoleta"));
        Banco.setCellValueFactory(new PropertyValueFactory<DepositoModelo,String>("Banco"));
        Id.setText("Id");
        Monto.setText("Monto");
        NumeroBoleta.setText("Numero Boleta");
        Banco.setText("Banco");
        Id.setPrefWidth(50);
        Monto.setPrefWidth(200);
        NumeroBoleta.setPrefWidth(200);
        Banco.setPrefWidth(200);
        TablaBodegas.getColumns().add(Id);
        TablaBodegas.getColumns().add(Monto);
        TablaBodegas.getColumns().add(NumeroBoleta);
        TablaBodegas.getColumns().add(Banco);
    }
     
    public void LLenarTabla()
    {
        if("".equals(TFBusqueda.getText()))
        {
            
            IDepositos busqueda = new IDepositos();
            List<Depositos> Deposito = busqueda.retornarListaDeDepositos();
            if(Deposito != null)
            {
                for(Depositos nuevo:Deposito )
                {
                    DepositoModelo BodegaModelo = new DepositoModelo(nuevo.getIdDepositos(), nuevo.getMonto(), nuevo.getNumeroDeBoleta(), nuevo.getBancosIdbancos().getNombre());
                    data.add(BodegaModelo);
                }
            }
            else
            {
                this.DepositosAdministrador.showMensajes("No Existen Depositos");
            }
        }
        else
        {
            IDepositos busqueda = new IDepositos();
            Depositos Deposito = busqueda.buscarDepositoPorNumeroBoleta(TFBusqueda.getText());
            if (Deposito == null)
            {
                this.DepositosAdministrador.showMensajes("No Existen Depositos con ese Numero");
            }
            else
            {
                DepositoModelo TipoClienteModelo = new DepositoModelo(Deposito.getIdDepositos(), Deposito.getMonto(), Deposito.getNumeroDeBoleta(), Deposito.getBancosIdbancos().getNombre());
                data.add(TipoClienteModelo);
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
    
    public void BorrarDeposito(ActionEvent event)
    {
        DepositoModelo Seleccionado = (DepositoModelo)TablaBodegas.getSelectionModel().getSelectedItem();
        int indice = TablaBodegas.getSelectionModel().getSelectedIndex();
        if(Seleccionado == null)
        {
            this.DepositosAdministrador.showMensajes("Debe Selecionar un Dato");
        }
        else
        {
            Depositos borrar = new IDepositos().buscarDepositoPorNumeroBoleta(Seleccionado.getNumeroBoleta());
            borrar.setEliminado(true);
            String Mensaje = new IDepositos().eliminar(borrar);
            this.DepositosAdministrador.showMensajes(Mensaje);
            if(Mensaje.equals("Deposito Eliminado Correctamente"))
            {
                data.remove(indice);
                TablaBodegas.setItems(data);
            }
        }
    }
    
    public void EditarDeposito(ActionEvent Event)
    {
        DepositoModelo Seleccionado = (DepositoModelo)TablaBodegas.getSelectionModel().getSelectedItem();
        if(Seleccionado == null)
        {
            this.DepositosAdministrador.showMensajes("Debe Seleccionar un Dato");
        }
        else
        {
            Depositos Editar = new Depositos();
            Editar.setIdDepositos(Seleccionado.getId());
            Editar.setMonto(Seleccionado.getMonto());
            Editar.setNumeroDeBoleta(Seleccionado.getNumeroBoleta());
            Editar.setBancosIdbancos(new IBancos().buscarBancoPorNombre(Seleccionado.getBanco()));
            this.DepositosAdministrador.showEditarDeposito(Editar);
        }
    }
}

