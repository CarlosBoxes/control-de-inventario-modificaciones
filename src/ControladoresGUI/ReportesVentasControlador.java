/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ControladoresGUI;

import AdministradoresGUI.ReportesVentasAdministrador;
import EntidadesJPA.BodegaProductos;
import EntidadesJPA.Clientes;
import Especiales.GeneradordeReportes;
import Especiales.Validaciones;
import GestorDeTablasJPA.IBodegaProductos;
import GestorDeTablasJPA.IClientes;
import javafx.event.ActionEvent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 *
 * @author luis__000
 */
public class ReportesVentasControlador 
{
    ReportesVentasAdministrador Administrador;
    public ComboBox ComBoMesVendedores;
    public ComboBox ComBoMesBodegas;
    public ComboBox ComBoBodega;
    public TextField TFAñoMes;
    public TextField TFAño;
    public TextField IdMes;
    public TextField IdAño;
    public Validaciones Validar;
    public Label LblMes;
    public Label LblAño;
    
    public void initialize() {}
  
    public void initManager(final ReportesVentasAdministrador Administrador)
    {
        this.Administrador = Administrador;
        Validar = new Validaciones();
    }
    
    public void AbrirReporteMensual(ActionEvent event)
    {
        GeneradordeReportes Reporte = new GeneradordeReportes();
        int Mes = ConvertirMes((String)ComBoMesVendedores.getValue());
        if(Validar.ValidarNumeros(this.TFAñoMes.getText()) && this.TFAñoMes.getText().length() <= 4 && Mes > 0)
        {
            Reporte.AbrirReporte("VendasPorVendedoresMensuales.jasper",Integer.parseInt(IdMes.getText()),Integer.parseInt(TFAñoMes.getText()), Mes);
        }
        else if(!Validar.ValidarNumeros(this.TFAñoMes.getText()))
        {
            this.Administrador.showMensajes("Año no Valido");
        }
        else if(Mes == 0)
        {
            this.Administrador.showMensajes("Seleccione un Mes");
        }      
    }
    
    public void AbrirReporteAnual(ActionEvent event)
    {
        GeneradordeReportes Reporte = new GeneradordeReportes();
        if(Validar.ValidarNumeros(this.TFAño.getText()) && this.TFAño.getText().length() <= 4)
        {
            Reporte.AbrirReporte("VendasPorVendedoresAnuales.jasper",Integer.parseInt(IdMes.getText()),Integer.parseInt(TFAño.getText()));
        }
        else if(!Validar.ValidarNumeros(this.TFAño.getText()))
        {
            this.Administrador.showMensajes("Año no Valido");
        }    
    }
    
    public void BuscarClienteMes(ActionEvent event)
    {
        if(Validar.ValidarNumeros(IdMes.getText()))
        {
            Clientes Cliente = new IClientes().buscarClientesPorId(Integer.parseInt(IdMes.getText()));
            LblMes.setText(Cliente.getNombre());
            ComBoMesVendedores.requestFocus();
        }
        else
        {
            this.Administrador.showMensajes("Nit no Valido");
        }
    }
    
    public void BuscarClienteAño(ActionEvent event)
    {
        if(Validar.ValidarNumeros(IdAño.getText()))
        {
            Clientes Cliente = new IClientes().buscarClientesPorId(Integer.parseInt(IdAño.getText()));
            LblAño.setText(Cliente.getNombre());
            TFAño.requestFocus();
        }
        else
        {
            this.Administrador.showMensajes("Nit no Valido");
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
}

