/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ControladoresGUI;

import AdministradoresGUI.ClientesAdministrador;
import EntidadesJPA.ChequesClientes;
import EntidadesJPA.Clientes;
import EntidadesJPA.Depositos;
import Especiales.GeneradordeReportes;
import Especiales.Validaciones;
import GestorDeTablasJPA.IChequesClientes;
import GestorDeTablasJPA.IClientes;
import GestorDeTablasJPA.ICuentasporCobrar;
import GestorDeTablasJPA.IDepositos;
import GestorDeTablasJPA.ITipoClientes;
import GestorDeTablasJPA.ICargosClientes;
import Modelos.ClienteModelo;
import Modelos.DescripcionLiquidacionModelo;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
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
public class ClientesControlador 
{
    ClientesAdministrador ClientesAdministrador;
   
    @FXML
    public TableView TablaClientes;
    public TableColumn Id;
    public TableColumn Nombre;
    public TableColumn Direccion;
    public TableColumn Nit;
    public TableColumn Celular;
    public TableColumn Telefono;
    public TableColumn Tipo;
    public TableColumn Saldo;
    public TextField TFBusqueda;
    public TextField TFCantidad;
    public TextField TFFechaC;
    public Label LblNumero;
    public ComboBox ComBoPago;
    public TextField TFAbono;
    public TextField TFSaldo;
    public VBox VBAbono;
    public VBox VBCargo;
    public TextField TFFecha;
    public TextField TFPrestamo;
    public TableView TablaDescripciones;
    public TableColumn NumeroBoleta;
    public TableColumn NumeroCheque;
    public TableColumn Monto;
    private ObservableList<ClienteModelo> data;
    public ObservableList<DescripcionLiquidacionModelo> dataDescripcion;
    private Validaciones Validar;
    private Clientes Cliente;
    private int ContadorTecla;
    private int ContadorTecla2;
    public Label LblClienteA;
    public Label LblClienteC;
     
    public void initialize() {}
  
    public void initManager(final ClientesAdministrador ClientesAdministrador)
    {
        this.ClientesAdministrador = ClientesAdministrador;
        data = FXCollections.observableArrayList();
        dataDescripcion = FXCollections.observableArrayList();
        Id = new TableColumn();
        Nombre = new TableColumn();
        Direccion = new TableColumn();
        Nit = new TableColumn();
        Celular = new TableColumn();
        Telefono = new TableColumn();
        Tipo = new TableColumn();
        Saldo = new TableColumn();
        CargarColumnas();
        NumeroBoleta = new TableColumn();
        NumeroCheque = new TableColumn();
        Monto = new TableColumn();
        CargarColumnasD();
        Validar = new Validaciones();
        VBAbono.setDisable(true);
        VBCargo.setDisable(true);
    }
    
    public void abrirNuevoCliente()
    {
        if(new ITipoClientes().listaDeTipoDeClientes() != null)
        {
            this.ClientesAdministrador.showNuevoClientes();
        }
        else
        {
            this.ClientesAdministrador.showMensajes("No hay Tipos de Clientes Ingresadas, Diríjase a Tipos");
        }
    }

    private void CargarColumnas() 
    {
        Id.setCellValueFactory(new PropertyValueFactory<ClienteModelo,Integer>("Id"));
        Nombre.setCellValueFactory(new PropertyValueFactory<ClienteModelo,String>("Nombre"));
        Direccion.setCellValueFactory(new PropertyValueFactory<ClienteModelo,String>("Direccion"));
        Nit.setCellValueFactory(new PropertyValueFactory<ClienteModelo,String>("Nit"));
        Celular.setCellValueFactory(new PropertyValueFactory<ClienteModelo,String>("Celular"));
        Telefono.setCellValueFactory(new PropertyValueFactory<ClienteModelo,String>("Telefono"));
        Tipo.setCellValueFactory(new PropertyValueFactory<ClienteModelo,String>("Tipo"));
        Saldo.setCellValueFactory(new PropertyValueFactory<ClienteModelo,Float>("Saldo"));
        Id.setText("Id");
        Nombre.setText("Nombre");
        Direccion.setText("Direccion");
        Nit.setText("Nit");
        Celular.setText("Celular");
        Telefono.setText("Telefono");
        Tipo.setText("Tipo");
        Saldo.setText("Saldo");
        Id.setPrefWidth(50);
        Nombre.setPrefWidth(200);
        Direccion.setPrefWidth(300);
        Nit.setPrefWidth(100);
        Celular.setPrefWidth(100);
        Telefono.setPrefWidth(100);
        Tipo.setPrefWidth(150);
        Saldo.setPrefWidth(100);
        TablaClientes.getColumns().add(Id);
        TablaClientes.getColumns().add(Nombre);
        TablaClientes.getColumns().add(Direccion);
        TablaClientes.getColumns().add(Nit);
        TablaClientes.getColumns().add(Tipo);
        TablaClientes.getColumns().add(Celular);
        TablaClientes.getColumns().add(Telefono);
        TablaClientes.getColumns().add(Saldo);
        TFAbono.setDisable(true);
    }
    
    public void LLenarTabla()
    {
        if("".equals(TFBusqueda.getText()))
        {
            
            IClientes busqueda = new IClientes();
            List<Clientes> Clientes = busqueda.listaDeClientes();
            if(Clientes != null)
            {
                for(Clientes nuevo:Clientes )
                {
                    ClienteModelo ClienteModelo = new ClienteModelo(nuevo.getIdCliente(), nuevo.getNombre(), nuevo.getDireccion(), nuevo.getNit(), nuevo.getTelefonoCelular(), nuevo.getTelefonoCasa(), nuevo.getTipoClientesidTipoClientes().getNombre(), nuevo.getSaldo());
                    data.add(ClienteModelo);
                }
            }
            else
            {
                this.ClientesAdministrador.showMensajes("No Existen Clientes");
            }
        }
        else
        {
            IClientes busqueda = new IClientes();
            List<Clientes> Clientes = busqueda.buscarListaClientesPorNombre(TFBusqueda.getText());
            if(Clientes != null)
            {
                for(Clientes Client:Clientes)
                {
                    ClienteModelo ClienteModelo = new ClienteModelo(Client.getIdCliente(), Client.getNombre(), Client.getDireccion(), Client.getNit(), Client.getTelefonoCelular(), Client.getTelefonoCasa(), Client.getTipoClientesidTipoClientes().getNombre(), Client.getSaldo());
                    data.add(ClienteModelo);
                }
            }
            else
            {
                this.ClientesAdministrador.showMensajes("No Existen Clientes que Contenga(n) esa(s) Letra(s)");
            }
        }
    }
    
    public void Buscar(ActionEvent event)
    {
        data.clear();
        TablaClientes.setItems(data);
        LLenarTabla();
        TablaClientes.setItems(data);
        TFSaldo.setText(String.valueOf(CalcularSaldos()));
    }
    
    public void BorrarCliente(ActionEvent event)
    {
        ClienteModelo Seleccionado = (ClienteModelo) TablaClientes.getSelectionModel().getSelectedItem();
        int indice = TablaClientes.getSelectionModel().getSelectedIndex();
        if(Seleccionado == null)
        {
            this.ClientesAdministrador.showMensajes("Debe Selecionar un Dato");
        }
        else
        {
            Clientes Clientes = new IClientes().buscarClientesPorNitNombre(Seleccionado.getNit(),Seleccionado.getNombre());
            Clientes.setEliminado(true);
            String Mensaje = new IClientes().eliminar(Clientes);
            this.ClientesAdministrador.showMensajes(Mensaje);
            if(Mensaje.equals("Cliente Eliminado Correctamente"))
            {
                data.remove(indice);
                TablaClientes.setItems(data);
            }
        }
    }
    
    public void EditarCliente(ActionEvent event)
    {
        ClienteModelo Seleccionado = (ClienteModelo)TablaClientes.getSelectionModel().getSelectedItem();
        if(Seleccionado == null)
        {
            this.ClientesAdministrador.showMensajes("Debe Seleccionar un Dato");
        }
        else
        {
            Clientes Editar = new IClientes().buscarClientesPorNitNombre(Seleccionado.getNit(),Seleccionado.getNombre());
            this.ClientesAdministrador.showEditarClientes(Editar);
        }
    }

    public void Seleccionado(ActionEvent event)
    {
        if(ComBoPago.getValue().equals("Contraseña/Efectivo"))
        {
            LblNumero.setText("Monto:  Q.");
            TFCantidad.setText("");
            TFCantidad.setDisable(false);
        }
        else if(ComBoPago.getValue().equals("Deposito"))
        {
            LblNumero.setText("No. Deposito");
            TFCantidad.setText("");
            TFCantidad.setDisable(false);
        }
        else if(ComBoPago.getValue().equals("Cheque"))
        {
            LblNumero.setText("No. Cheque");
            TFCantidad.setText("");
            TFCantidad.setDisable(false);
        }
    }
    
    private void CargarColumnasD() 
    {
        NumeroBoleta.setCellValueFactory(new PropertyValueFactory<DescripcionLiquidacionModelo,String>("NumeroBoleta"));
        NumeroCheque.setCellValueFactory(new PropertyValueFactory<DescripcionLiquidacionModelo,String>("NumeroCheque"));
        Monto.setCellValueFactory(new PropertyValueFactory<DescripcionLiquidacionModelo,Float>("Monto")); 
        NumeroBoleta.setText("Numero Boleta");
        NumeroCheque.setText("Numero Cheque");
        Monto.setText("Monto");
        NumeroBoleta.setPrefWidth(200);
        NumeroCheque.setPrefWidth(200);
        Monto.setPrefWidth(200);
        TablaDescripciones.getColumns().add(NumeroBoleta);
        TablaDescripciones.getColumns().add(NumeroCheque);
        TablaDescripciones.getColumns().add(Monto);
    }
    
    public void LLenarTablaDescripcionCheque(String NoCheque)
    {
        IChequesClientes busqueda = new IChequesClientes();
        ChequesClientes Cheques = busqueda.buscarChequesNumeroChuequeUsado(Integer.parseInt(NoCheque));
        if(Cheques != null)
        {
            if(Cheques.getUsado())
            {
                    this.ClientesAdministrador.showMensajes("El Cheque Ya Fue Usado");
            }
            else if(VerificarCheque(NoCheque))
            {
                DescripcionLiquidacionModelo DescripcionModelo = new DescripcionLiquidacionModelo(null,String.valueOf(Cheques.getNumero()),Cheques.getMonto());
                dataDescripcion.add(DescripcionModelo);
            }
            else
            {
                this.ClientesAdministrador.showMensajes("Cheque ya Ingresado");
            }
        }
        else
        {
            this.ClientesAdministrador.showMensajes("El Cheque No Existe");
        }
    }
    
    private void LLenarTablaDescripcionDeposito(String NoBoleta)
    {
        IDepositos busqueda = new IDepositos();
        Depositos Deposito = busqueda.buscarDepositoPorNumeroBoletaUsada(NoBoleta);
        if(Deposito != null)
        {
            
            if(Deposito.getUsado())
            {
                this.ClientesAdministrador.showMensajes("El Deposito Ya Fue Usado");
            }
            else if(VerificarDeposito(NoBoleta))
            {
                DescripcionLiquidacionModelo DescripcionModelo = new DescripcionLiquidacionModelo(Deposito.getNumeroDeBoleta(),null,Deposito.getMonto());
                dataDescripcion.add(DescripcionModelo);
            }
            else
            {
                this.ClientesAdministrador.showMensajes("Deposito ya Ingresado");
            }
        }
        else
        {
            this.ClientesAdministrador.showMensajes("El Deposito No Existe");
        }
    }
    
    private boolean VerificarDeposito(String NoBoleta)
    {
        for(DescripcionLiquidacionModelo Modelo: dataDescripcion)
        {
            if(Modelo.getNumeroBoleta().equals(NoBoleta))
            {
                return false;
            }
        }
        return true;     
    }
    
    private boolean VerificarCheque(String Cheque)
    {
        for(DescripcionLiquidacionModelo Modelo: dataDescripcion)
        {
            if(Modelo.getNumeroCheque().equals(Cheque))
            {
                return false;
            }
        }
        return true;
    }
    
    private void LlenarTablaDescripcionContraseñasEfectivo(Float Monto)
    {
        DescripcionLiquidacionModelo DescripcionModelo = new DescripcionLiquidacionModelo(null,null,Monto);
        dataDescripcion.add(DescripcionModelo);
    }
    
    public void AgregarDescripcion(ActionEvent event)
    {
        if(ComBoPago.getValue().equals("Contraseña/Efectivo"))
        {
            if(Validar.ValidarMontos(TFCantidad.getText()))
            {
                LlenarTablaDescripcionContraseñasEfectivo(Float.parseFloat(TFCantidad.getText()));
                TablaDescripciones.setItems(dataDescripcion);
                LblNumero.setText("");
                TFCantidad.setText("");
                ComBoPago.setValue("");
                ComBoPago.requestFocus();
                TFAbono.setText(String.valueOf(CalcularAbono()));
            }
            else
            {
                this.ClientesAdministrador.showMensajes("Verifique el Monto");
            }
        }
        else if(ComBoPago.getValue().equals("Deposito"))
        {
            LLenarTablaDescripcionDeposito(TFCantidad.getText());
            TablaDescripciones.setItems(dataDescripcion);
            LblNumero.setText("");
            TFCantidad.setText("");
            ComBoPago.setValue("");
            ComBoPago.requestFocus();TFAbono.setText(String.valueOf(CalcularAbono()));
        }
        else if(ComBoPago.getValue().equals("Cheque"))
        {
            LLenarTablaDescripcionCheque(TFCantidad.getText());
            TablaDescripciones.setItems(dataDescripcion);
            LblNumero.setText("");
            TFCantidad.setText("");
            ComBoPago.setValue("");
            ComBoPago.requestFocus();
            TFAbono.setText(String.valueOf(CalcularAbono()));
        }
    }
    
    public void BorrarDescripcion(ActionEvent event)
    {
        DescripcionLiquidacionModelo Seleccionado = (DescripcionLiquidacionModelo) TablaDescripciones.getSelectionModel().getSelectedItem();
        int indice = TablaDescripciones.getSelectionModel().getSelectedIndex();
        if(Seleccionado == null)
        {
            this.ClientesAdministrador.showMensajes("Debe Selecionar un Dato");
        }
        else
        {
            dataDescripcion.remove(indice);
            TablaDescripciones.setItems(dataDescripcion);
            TFCantidad.setDisable(true);
        }
    }
    
    private Float CalcularAbono()
    {
        Float Total = 0f;
        for(DescripcionLiquidacionModelo Modelo:dataDescripcion)
        {
            Total = Total + Modelo.getMonto();
        }
        return Total; 
    }
    
    private Float CalcularSaldos()
    {
        Float Total = 0f;
        for(ClienteModelo Modelo:data)
        {
            Total = Total + Modelo.getSaldo();
        }
        return Total;
    }
    
    public void Cancelar(ActionEvent event)
    {
        dataDescripcion.clear();
        TablaDescripciones.setItems(dataDescripcion);
        TFAbono.setText("");
        VBAbono.setDisable(true);
        VBCargo.setDisable(true);
        TFCantidad.setDisable(true);
        ComBoPago.setValue("");
        LblClienteA.setText("");
        LblClienteC.setText("");
        LblNumero.setText("");
    }
    
    public void HabilitarCreditos(ActionEvent event)
    {
        Cliente = null;
        ClienteModelo Seleccionado = (ClienteModelo)TablaClientes.getSelectionModel().getSelectedItem();
        if(Seleccionado == null)
        {
            this.ClientesAdministrador.showMensajes("Debe Seleccionar un Dato");
        }
        else
        {
            Cliente = new IClientes().buscarClientesPorNitNombre(Seleccionado.getNit(),Seleccionado.getNombre());
            if(Cliente.getSaldo() <= 0)
            {
                VBAbono.setDisable(true);
                ComBoPago.setValue("");
                LblNumero.setText("");
                LblClienteA.setText("");
                LblClienteC.setText("Cliente: "+Cliente.getNombre()+" Nit: "+Cliente.getNit()+" Saldo: "+String.valueOf(Cliente.getSaldo()));
                VBCargo.setDisable(false);
            }
            else
            {
                VBAbono.setDisable(false);
                VBCargo.setDisable(false);
                ComBoPago.setValue("");
                LblNumero.setText("");
                LblClienteA.setText("Cliente: "+Cliente.getNombre()+" Nit: "+Cliente.getNit()+" Saldo: "+String.valueOf(Cliente.getSaldo()));
                LblClienteC.setText("Cliente: "+Cliente.getNombre()+" Nit: "+Cliente.getNit()+" Saldo: "+String.valueOf(Cliente.getSaldo()));
            }
        }
    }
    
    public void CancelarCargo(ActionEvent event)
    {
        TFPrestamo.setText("");
        TFFechaC.setText("");
        VBAbono.setDisable(true);
        VBCargo.setDisable(true);
        Cliente = null;
        ComBoPago.setValue("");
        LblClienteA.setText("");
        LblClienteC.setText("");
        LblNumero.setText("");
    }
    
    
    public void Cargar(ActionEvent event)
    {
        if(Validar.ValidarMontos(TFPrestamo.getText()) && Validar.FormatoFecha(TFFechaC.getText()))
        {
            Float Prestamo;
            Prestamo = Cliente.getSaldo() + Float.parseFloat(TFPrestamo.getText());
            Cliente.setSaldo(Prestamo);
            NuevoSaldoCliente(Prestamo,true);
            try
            {
                new IClientes().modificar(Cliente);
                DateFormat Formato = DateFormat.getDateInstance(DateFormat.SHORT);
                Date Fecha = null;
                try 
                {
                    Fecha = Formato.parse(TFFechaC.getText());
                } 
                catch (ParseException ex) 
                {
                    Logger.getLogger(ClientesControlador.class.getName()).log(Level.SEVERE, null, ex);
                }
                new ICargosClientes().guardar(Float.parseFloat(TFPrestamo.getText()), Fecha, Cliente);
                TFFechaC.setText("");
                TFPrestamo.setText("");
                LblClienteA.setText("");
                LblClienteC.setText("");
                VBAbono.setDisable(true);
                VBCargo.setDisable(true);
            }
            catch(Exception e)
            {
                this.ClientesAdministrador.showMensajes("Error al Cargar el Préstamo");
            }
        }
        else
        {
            this.ClientesAdministrador.showMensajes("Verifique el Préstamo");
        }
    }
    
    public void Abonar(ActionEvent event)
    {
        if(!dataDescripcion.isEmpty())
        {
            if(Float.parseFloat(TFAbono.getText()) <= Cliente.getSaldo() && Validar.FormatoFecha(TFFecha.getText()))
            {
                Float Saldos = Cliente.getSaldo();
                Cliente.setSaldo(Saldos - Float.parseFloat(TFAbono.getText()));
                NuevoSaldoCliente(Cliente.getSaldo(),false);
                new IClientes().modificar(Cliente);
                GuardarCuenta();
                dataDescripcion.clear();
                TablaDescripciones.setItems(dataDescripcion);
                TFAbono.setText("");
                TFFecha.setText("");
                VBAbono.setDisable(true);
                VBCargo.setDisable(true);
                LblClienteA.setText("");
                LblClienteC.setText("");
            }
            else if(Float.parseFloat(TFAbono.getText()) > Cliente.getSaldo())
            {
                this.ClientesAdministrador.showMensajes("Intenta Abonar maás de lo Debido");
            }
            else if(!Validar.FormatoFecha(TFFecha.getText()))
            {
                this.ClientesAdministrador.showMensajes("Verifique la Fecha");
            }
        }
        else
        {
            this.ClientesAdministrador.showMensajes("No Ingreso Datos para el Abono");
        }
    }
    
    private void GuardarCuenta()
    {
        for(DescripcionLiquidacionModelo Modelo:dataDescripcion)
        {
            if(Modelo.getNumeroBoleta() != null)
            {
                Depositos Deposito = new IDepositos().buscarDepositoPorNumeroBoleta(Modelo.getNumeroBoleta());
                DateFormat df = DateFormat.getDateInstance(DateFormat.SHORT);      
                Date fecha = null;
                try 
                {
                    fecha = df.parse(TFFecha.getText());
                } 
                catch (ParseException ex) 
                {
                    Logger.getLogger(ClientesControlador.class.getName()).log(Level.SEVERE, null, ex);
                }
                new ICuentasporCobrar().guardar(null, Deposito, Deposito.getMonto(),fecha, Cliente);
                Deposito.setUsado(true);
                new IDepositos().modificar(Deposito);
            }
            else if(Modelo.getNumeroCheque() != null)
            {
                ChequesClientes Cheque = new IChequesClientes().buscarChequesNumeroChueque(Integer.parseInt(Modelo.getNumeroCheque()));
                DateFormat df = DateFormat.getDateInstance(DateFormat.SHORT);      
                Date fecha = null;
                try 
                {
                    fecha = df.parse(TFFecha.getText());
                } 
                catch (ParseException ex) 
                {
                    Logger.getLogger(ClientesControlador.class.getName()).log(Level.SEVERE, null, ex);
                }
                new ICuentasporCobrar().guardar(Cheque, null, Cheque.getMonto(),fecha, Cliente);
                Cheque.setUsado(true);
                new IChequesClientes().modificar(Cheque);
            }
            else
            {
                DateFormat df = DateFormat.getDateInstance(DateFormat.SHORT);      
                Date fecha = null;
                try 
                {
                    fecha = df.parse(TFFecha.getText());
                } 
                catch (ParseException ex) 
                {
                    Logger.getLogger(ClientesControlador.class.getName()).log(Level.SEVERE, null, ex);
                }
                new ICuentasporCobrar().guardar(null, null, Modelo.getMonto(),fecha, Cliente);
            }
        }
    }
    
    private void NuevoSaldoCliente(Float NuevoSaldo, boolean tipo)
    {
        for(int i = 0; i < data.size();i++)
        {
            if(data.get(i).getId() == Cliente.getIdCliente())
            {
                if(NuevoSaldo == 0)
                {
                    ClienteModelo Modelo = new ClienteModelo(Cliente.getIdCliente(), Cliente.getNombre(), Cliente.getDireccion(), Cliente.getNit(), Cliente.getTelefonoCelular(), Cliente.getTelefonoCasa(), Cliente.getTipoClientesidTipoClientes().getNombre(), NuevoSaldo);
                    data.set(i, Modelo);
                    TablaClientes.setItems(data);
                    this.ClientesAdministrador.showMensajes("La Deuda del Cliente a sido Cancelada");
                }
                else
                {
                    ClienteModelo Modelo = new ClienteModelo(Cliente.getIdCliente(), Cliente.getNombre(), Cliente.getDireccion(), Cliente.getNit(), Cliente.getTelefonoCelular(), Cliente.getTelefonoCasa(), Cliente.getTipoClientesidTipoClientes().getNombre(), NuevoSaldo);
                    data.set(i, Modelo);
                    TablaClientes.setItems(data);
                    if(tipo)
                    {
                        this.ClientesAdministrador.showMensajes("Préstamo Cargado Correctamente \n"+"La Deuda del Cliente es de: Q. "+String.valueOf(NuevoSaldo));
                    }
                    else
                    {
                        this.ClientesAdministrador.showMensajes("Préstamo Abonado Correctamente \n"+"La Deuda del Cliente es de: Q. "+String.valueOf(NuevoSaldo));   
                    }
                }
            }
        }
        TFSaldo.setText(String.valueOf(CalcularSaldos()));
    }
    
    public void AbrirReporte(ActionEvent e)
    {
        ClienteModelo Seleccionado = (ClienteModelo)TablaClientes.getSelectionModel().getSelectedItem();
        if(Seleccionado == null)
        {
            this.ClientesAdministrador.showMensajes("Debe Seleccionar un Dato");
        }
        else
        {
           GeneradordeReportes Reporte = new GeneradordeReportes();
           Reporte.AbrirReporte("CreditoCliente.jasper", Seleccionado.getId());
        }
    }
    
    public void SoltoTecla(KeyEvent e)
    {
        String Tecla = e.getText();
        int Key;
        Key = e.getCode().impl_getCode();
        boolean Valido = Validar.ValidarNumero(Tecla);
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
    
    public void SoltoTecla2(KeyEvent e)
    {
        String Tecla = e.getText();
        int Key;
        Key = e.getCode().impl_getCode();
        boolean Valido = Validar.ValidarNumero(Tecla);
        if(Key == 8)
        {
            ComprobarTamaño2();
        }
        else if(Key == 111 || Key == 55)
        {
            AgregarCero2();
        }
        else
        {
            if((Key > 46 && Key < 106))
            {
                if(!Valido)
                {
                    TFFechaC.deletePreviousChar();
                }
                else
                {

                    ContadorTecla2++;
                    if(TFFechaC.getText().length()<6)
                    {
                        if(ContadorTecla2 == 2)
                        {
                            TFFechaC.setText(TFFechaC.getText()+"/");
                            TFFechaC.end();
                            ComprobarTamaño2();
                        }
                    }
                    else if(TFFechaC.getText().length() > 10)
                    {
                        TFFechaC.deletePreviousChar();
                    }
                    ComprobarTamaño2();
                }
            }
        }
    }
    
    private void ComprobarTamaño2()
    {
        if(TFFechaC.getText().length() == 0 || TFFechaC.getText().length() == 3 || TFFechaC.getText().length() == 5)
        {
            ContadorTecla2 = 0;
        }
        if(TFFechaC.getText().length() == 1 || TFFechaC.getText().length() == 4)
        {
            ContadorTecla2 = 1;
        }
    }
    
    private void AgregarCero2()
    {
        if(TFFechaC.getText().length() == 2)
        {
            TFFechaC.setText("0"+TFFechaC.getText());
            TFFechaC.end();
            ContadorTecla2 = 0;
        }
        else if(TFFechaC.getText().length() == 5)
        {
            TFFechaC.insertText(3, "0");
            TFFechaC.end();
            ContadorTecla2 = 0;
        }
        else if(TFFechaC.getText().length() < 3)
        {
            TFFechaC.deletePreviousChar();
        }
        else if(TFFechaC.getText().length() > 3 && TFFechaC.getText().length() < 6)
        {
            TFFechaC.deletePreviousChar();
        }
        else if(TFFechaC.getText().length() > 6)
        {
            TFFechaC.deletePreviousChar();
        }
    } 
    
}

