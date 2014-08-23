/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 * this is a comment
 */

package ControladoresGUI;

import AdministradoresGUI.BancosAdministrador;
import EntidadesJPA.Bancos;
import GestorDeTablasJPA.IBancos;
import Modelos.BancoModelo;
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
public class BancosControlador 
{
    BancosAdministrador BancosAdministrador;
    @FXML
    public TableView TablaBancos;
    public TableColumn Id;
    public TableColumn Nombre;
    public TableColumn Telefono;
    public TextField TFBusqueda;
    private ObservableList<BancoModelo> data;
    
    public void initialize() {}
  
    public void initManager(final BancosAdministrador BancosAdministrador)
    {
        this.BancosAdministrador = BancosAdministrador;
        data = FXCollections.observableArrayList();
        Id = new TableColumn();
        Nombre = new TableColumn();
        Telefono = new TableColumn();
        CargarColumnas();
    }
    
    private void CargarColumnas() 
    {
        Id.setCellValueFactory(new PropertyValueFactory<BancoModelo,Integer>("Id"));
        Nombre.setCellValueFactory(new PropertyValueFactory<BancoModelo,String>("Nombre"));
        Telefono.setCellValueFactory(new PropertyValueFactory<BancoModelo,String>("Telefono"));
        Id.setText("Id");
        Nombre.setText("Nombre");
        Telefono.setText("Telefono");
        Id.setPrefWidth(50);
        Nombre.setPrefWidth(300);
        Telefono.setPrefWidth(200);
        TablaBancos.getColumns().add(Id);
        TablaBancos.getColumns().add(Nombre);
        TablaBancos.getColumns().add(Telefono);
    }
    
    public void LLenarTabla()
    {
        if("".equals(TFBusqueda.getText()))
        {
            
            IBancos busqueda = new IBancos();
            List<Bancos> Banco = busqueda.retornarListaDeBancos();
            if(Banco != null)
            {
                for(Bancos nuevo:Banco )
                {
                    BancoModelo BodegaModelo = new BancoModelo(nuevo.getIdbancos(), nuevo.getNombre(), nuevo.getTelefono());
                    data.add(BodegaModelo);
                }
            }
            else
            {
                this.BancosAdministrador.showMensajes("No Existen Bancos");
            }
        }
        else
        {
            IBancos busqueda = new IBancos();
            List<Bancos> Bancos = busqueda.buscarListaBancosPorNombre(TFBusqueda.getText());
            if (Bancos!=null)
            {
                for(Bancos Banco:Bancos)
                {
                    BancoModelo BancoModelo = new BancoModelo(Banco.getIdbancos(), Banco.getNombre(), Banco.getTelefono());
                    data.add(BancoModelo);
                }
            }
            else
            {
                this.BancosAdministrador.showMensajes("No Existen Bancos que Contenga(n) esa(s) Letra(s)");
            }
        }
    }
     
    public void Buscar(ActionEvent event)
    {
        data.clear();
        TablaBancos.setItems(data);
        LLenarTabla();
        TablaBancos.setItems(data);
    }
    
    public void BorrarBanco(ActionEvent event)
    {
        BancoModelo Seleccionado = (BancoModelo) TablaBancos.getSelectionModel().getSelectedItem();
        int indice = TablaBancos.getSelectionModel().getSelectedIndex();
        if(Seleccionado == null)
        {
            this.BancosAdministrador.showMensajes("Debe Selecionar un Dato");
        }
        else
        {
            Bancos Banco = new IBancos().buscarBancoPorId(Seleccionado.getId());
            Banco.setEliminado(true);
            String Mensaje = new IBancos().eliminar(Banco);
            this.BancosAdministrador.showMensajes(Mensaje);
            if(Mensaje.equals("Banco Eliminado Correctamente"))
            {
                data.remove(indice);
                TablaBancos.setItems(data);     
            }
        }
    }
    
    public void abrirNuevoBanco()
    {
        this.BancosAdministrador.showNuevoBanco();
    }
    
    public void EditarBanco(ActionEvent Event)
    {
        BancoModelo Seleccionado = (BancoModelo)TablaBancos.getSelectionModel().getSelectedItem();
        if(Seleccionado == null)
        {
            this.BancosAdministrador.showMensajes("Debe Seleccionar un Dato");
        }
        else
        {
            Bancos Editar = new Bancos();
            Editar.setIdbancos(Seleccionado.getId());
            Editar.setNombre(Seleccionado.getNombre());
            Editar.setTelefono(Seleccionado.getTelefono());
            this.BancosAdministrador.showEditarBanco(Editar);
        }
    }
}

