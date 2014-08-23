/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ControladoresGUI;

import AdministradoresGUI.ReportesFinanzasAdministrador;
import EntidadesJPA.Proveedores;
import Especiales.GeneradordeReportes;
import Especiales.Validaciones;
import GestorDeTablasJPA.IProveedores;
import javafx.event.ActionEvent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
/**
 *
 * @author luis__000
 */
public class ReportesFinanzasControlador 
{
    ReportesFinanzasAdministrador ReportesFinanzasAdministrador;
    public ComboBox ComBoMesProveedores;
    public TextField TFAñoMes;
    public TextField TFAño;
    public TextField IdMes;
    public TextField IdAño;
    public Validaciones Validar;
    public Label LblMes;
    public Label LblAño;
    
    public void initialize() {}
  
    public void initManager(final ReportesFinanzasAdministrador ReportesFinanzasAdministrador)
    {
        this.ReportesFinanzasAdministrador = ReportesFinanzasAdministrador;
        Validar = new Validaciones();
    }
    
    public void AbrirReporteComprasMensuales(ActionEvent event)
    {
        GeneradordeReportes Reporte = new GeneradordeReportes();
        int Mes = ConvertirMes((String)ComBoMesProveedores.getValue());
        if(Validar.ValidarNumeros(this.TFAñoMes.getText()) && this.TFAñoMes.getText().length() <= 4 && Mes > 0)
        {
            Reporte.AbrirReporte("ComprasMensuales.jasper",Integer.parseInt(IdMes.getText()),Integer.parseInt(TFAñoMes.getText()), Mes);
            Reporte.AbrirReporte("ComprasMensualesMateriaPrima.jasper",Integer.parseInt(IdMes.getText()),Integer.parseInt(TFAñoMes.getText()), Mes);
        }
        else if(!Validar.ValidarNumeros(this.TFAñoMes.getText()))
        {
            this.ReportesFinanzasAdministrador.showMensajes("Año no Valido");
        }
        else if(Mes == 0)
        {
            this.ReportesFinanzasAdministrador.showMensajes("Seleccione un Mes");
        }      
    }
    
    public void AbrirReporteComprasAnuales(ActionEvent event)
    {
        GeneradordeReportes Reporte = new GeneradordeReportes();
        if(Validar.ValidarNumeros(this.TFAño.getText()) && this.TFAño.getText().length() <= 4)
        {
            Reporte.AbrirReporte("ComprasAnuales.jasper",Integer.parseInt(IdAño.getText()),Integer.parseInt(TFAño.getText()));
            Reporte.AbrirReporte("ComprasAnualesMateriaPrima.jasper",Integer.parseInt(IdAño.getText()),Integer.parseInt(TFAño.getText()));
        }
        else if(!Validar.ValidarNumeros(this.TFAño.getText()))
        {
            this.ReportesFinanzasAdministrador.showMensajes("Año no Valido");
        }
    }
    
    public void ProveedorMes(ActionEvent event)
    {
        LblMes.setText(BuscarProveedor(IdMes.getText()));
    }
    
    public void ProveedorAño(ActionEvent event)
    {
        LblAño.setText(BuscarProveedor(IdAño.getText()));
    }
    
    public String BuscarProveedor(String Id)
    {
        if(Validar.ValidarNumeros(Id))
        {
            Proveedores Proveedores = new IProveedores().buscarProveedorPorId(Integer.parseInt(Id));
            return Proveedores.getNombre();
        }
        else
        {
            this.ReportesFinanzasAdministrador.showMensajes("Id no Valido");
            return "";
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

