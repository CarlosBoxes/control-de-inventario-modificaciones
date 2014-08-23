/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ControladoresGUI;

import AdministradoresGUI.VendedoresAdministrador;
import EntidadesJPA.Vendedores;
import Especiales.GeneradordeReportes;
import Especiales.Validaciones;
import GestorDeTablasJPA.ITipoVendedor;
import GestorDeTablasJPA.IVendedores;
import Modelos.VendedorModelo;
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
 * FXML Controller class
 *
 * @author luis__000
 */
public class VendedoresControlador
{

    VendedoresAdministrador VendedoresAdministrador;
    @FXML
    public TableView TablaVendedores;
    public TableColumn Id;
    public TableColumn NombreyApellido;
    public TableColumn Celular;
    public TableColumn Telefono;
    public TableColumn Tipo;
    public TextField TFBusqueda;
    public TextField TFAnual;
    public TextField TFAño;
    public ComboBox ComBoMes;
    private ObservableList<VendedorModelo> data;
    private Validaciones Validar;
    
    public void initialize() {}
    
    public void initManager(final VendedoresAdministrador VendedoresAdministrador)
    {
        this.VendedoresAdministrador = VendedoresAdministrador;
        data = FXCollections.observableArrayList();
        Id = new TableColumn();
        NombreyApellido = new TableColumn();
        Celular = new TableColumn();
        Telefono = new TableColumn();
        Tipo = new TableColumn();
        CargarColumnas();
        Validar = new Validaciones();
    }
    
    
    public void abrirNuevoVendedor()
    {
        if(new ITipoVendedor().listaDeTipoDeVendedores() != null)
        {
            this.VendedoresAdministrador.showNuevoVendedor();
        }
        else
        {
            this.VendedoresAdministrador.showMensajes("No hay Tipos de Vendedores Ingresadas, Diríjase a Tipos");
        }
    }
    
    private void CargarColumnas() 
    {
        Id.setCellValueFactory(new PropertyValueFactory<VendedorModelo,Integer>("Id"));
        NombreyApellido.setCellValueFactory(new PropertyValueFactory<VendedorModelo,String>("Nombre"));
        Celular.setCellValueFactory(new PropertyValueFactory<VendedorModelo,String>("Celular"));
        Telefono.setCellValueFactory(new PropertyValueFactory<VendedorModelo,String>("Telefono"));
        Tipo.setCellValueFactory(new PropertyValueFactory<VendedorModelo,String>("Tipo"));
        Id.setText("Id");
        NombreyApellido.setText("Nombre");
        Celular.setText("Celular");
        Telefono.setText("Telefono");
        Tipo.setText("Tipo");
        Id.setPrefWidth(50);
        NombreyApellido.setPrefWidth(350);
        Celular.setPrefWidth(100);
        Telefono.setPrefWidth(100);
        Tipo.setPrefWidth(150);
        TablaVendedores.getColumns().add(Id);
        TablaVendedores.getColumns().add(NombreyApellido);
        TablaVendedores.getColumns().add(Tipo);
        TablaVendedores.getColumns().add(Celular);
        TablaVendedores.getColumns().add(Telefono);
    }
    
    public void LLenarTabla()
    {
        if("".equals(TFBusqueda.getText()))
        {
            
            IVendedores busqueda = new IVendedores();
            List<Vendedores> Vendedores = busqueda.listaDeVendedores();
            if(Vendedores != null)
            {
                for(Vendedores nuevo: Vendedores )
                {
                    VendedorModelo VendedorModelo = new VendedorModelo(nuevo.getIdvendedores(), nuevo.getNombre()+" "+nuevo.getApellido(), nuevo.getTelefonoCelular(), nuevo.getTelefonoCasa(), nuevo.getTipoVendedoresidTipoVendedores().getNombre());
                    data.add(VendedorModelo);
                }
            }
            else
            {
                this.VendedoresAdministrador.showMensajes("No Existen Vendedores");
            }
        }
        else
        {
            List<Vendedores> Vendedores = new IVendedores().buscarVendedoresPorNombre(TFBusqueda.getText());
            if(Vendedores != null)
            {
                for(Vendedores nuevo: Vendedores)
                {
                    VendedorModelo ClienteModelo = new VendedorModelo(nuevo.getIdvendedores(), nuevo.getNombre()+" "+nuevo.getApellido(), nuevo.getTelefonoCelular(), nuevo.getTelefonoCasa(), nuevo.getTipoVendedoresidTipoVendedores().getNombre());
                    data.add(ClienteModelo);
                }
            }
            else
            {
                this.VendedoresAdministrador.showMensajes("No Existen Vendedores que Contenga(n) esa(s) Letra(s)");
            }
        }
    }
    
    public void Buscar(ActionEvent event)
    {
        data.clear();
        TablaVendedores.setItems(data);
        LLenarTabla();
        TablaVendedores.setItems(data);
    }
    
    public void BorrarVendedor(ActionEvent event)
    {
        VendedorModelo Seleccionado = (VendedorModelo) TablaVendedores.getSelectionModel().getSelectedItem();
        int indice = TablaVendedores.getSelectionModel().getSelectedIndex();
        if(Seleccionado == null)
        {
            this.VendedoresAdministrador.showMensajes("Debe Selecionar un Dato");
        }
        else
        {
            Vendedores Vendedor = new IVendedores().buscarVendedorPorId(Seleccionado.getId());
            Vendedor.setEliminado(true);
            String Mensaje = new IVendedores().eliminar(Vendedor);
            this.VendedoresAdministrador.showMensajes(Mensaje);
            if(Mensaje.equals("Vendedor Eliminado Correctamente"))
            {
                data.remove(indice);
                TablaVendedores.setItems(data);
            }
        }
    }
    
    public void EditarVendedor(ActionEvent event)
    {
        VendedorModelo Seleccionado = (VendedorModelo)TablaVendedores.getSelectionModel().getSelectedItem();
        if(Seleccionado == null)
        {
            this.VendedoresAdministrador.showMensajes("Debe Seleccionar un Dato");
        }
        else
        {
            Vendedores Editar = new IVendedores().buscarVendedorPorId(Seleccionado.getId());
            this.VendedoresAdministrador.showEditarVendedores(Editar);
        }
    }
    
    public void AbrirReporteVentasAnuales(ActionEvent event)
    {
        VendedorModelo Seleccionado = (VendedorModelo) TablaVendedores.getSelectionModel().getSelectedItem();
        if(Seleccionado == null)
        {
            this.VendedoresAdministrador.showMensajes("Debe Selecionar un Dato");
        }
        else
        {
            GeneradordeReportes Reporte = new GeneradordeReportes();
            if(Validar.ValidarNumeros(this.TFAnual.getText()) && this.TFAnual.getText().length() <= 4)
            {
                Reporte.AbrirReporte("VentasPorVendedoresAnuales.jasper",Seleccionado.getId(),Integer.parseInt(TFAnual.getText()));
            }
            else
            {
                this.VendedoresAdministrador.showMensajes("Año no Valido");
            }
        }    
    }
    
    public void AbrirReporteVentasMensuales(ActionEvent event)
    {
        VendedorModelo Seleccionado = (VendedorModelo) TablaVendedores.getSelectionModel().getSelectedItem();
        if(Seleccionado == null)
        {
            this.VendedoresAdministrador.showMensajes("Debe Selecionar un Dato");
        }
        else
        {
            GeneradordeReportes Reporte = new GeneradordeReportes();
            int Mes = ConvertirMes((String)ComBoMes.getValue());
            if(Validar.ValidarNumeros(this.TFAño.getText()) && this.TFAño.getText().length() <= 4 && Mes > 0)
            {
                Reporte.AbrirReporte("VentasPorVendedoresMensuales.jasper",Seleccionado.getId(),Integer.parseInt(TFAño.getText()),Mes);
            }
            else if(!Validar.ValidarNumeros(this.TFAño.getText()))
            {
                this.VendedoresAdministrador.showMensajes("Año no Valido");
            }
            else if(Mes == 0)
            {
                this.VendedoresAdministrador.showMensajes("Seleccione un Mes");
            }
        }    
    }
    
    private int ConvertirMes(String Nombre)
    {
        if("Enero".equals(Nombre))
        {
            return 1;
        }
        else if("Febrero".equals(Nombre))
        {
            return 2;
        }
        else if("Marzo".equals(Nombre))
        {
            return 3;
        }
        else if("Abril".equals(Nombre))
        {
            return 4;
        }
        else if("Mayo".equals(Nombre))
        {
            return 5;
        }
        else if("Junio".equals(Nombre))
        {
            return 6;
        }
        else if("Julio".equals(Nombre))
        {
            return 7;
        }
        else if("Agosto".equals(Nombre))
        {
            return 8;
        }
        else if("Septiembre".equals(Nombre))
        {
            return 9;
        }
        else if("Octubre".equals(Nombre))
        {
            return 10;
        }
        else if("Noviembre".equals(Nombre))
        {
            return 11;
        }
        else if("Diciembre".equals(Nombre))
        {
            return 12;
        }
        else
        {
            return 0;
        }
    }
    
    public void AbrirListaClientes(ActionEvent Event)
    {
        VendedorModelo Seleccionado = (VendedorModelo) TablaVendedores.getSelectionModel().getSelectedItem();
        if(Seleccionado == null)
        {
            this.VendedoresAdministrador.showMensajes("Debe Selecionar un Dato");
        }
        else
        {
            Vendedores Vendedor = new IVendedores().buscarVendedorPorId(Seleccionado.getId());
            if(!Vendedor.getTipoVendedoresidTipoVendedores().getListaproductos())
            {
                this.VendedoresAdministrador.showListaClientes(Vendedor);
            }
            else
            {
                this.VendedoresAdministrador.showMensajes("Este Vendedor No Puede Tener Lista de Clientes");
            }
        }
    }
}
