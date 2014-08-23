/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ControladoresGUI;

import AdministradoresGUI.ReportesLiquidacionAdministrador;
import EntidadesJPA.BodegaProductos;
import EntidadesJPA.Vendedores;
import Especiales.GeneradordeReportes;
import Especiales.Validaciones;
import GestorDeTablasJPA.IBodegaProductos;
import GestorDeTablasJPA.IVendedores;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 *
 * @author luis__000
 */
public class ReportesLiquidacionControlador 
{
    ReportesLiquidacionAdministrador ReportesLiquidacionAdministrador;
    public ComboBox ComBoMesVendedores;
    public ComboBox ComBoMesBodegas;
    public ComboBox ComBoBodega;
    public TextField TFAñoVendedores;
    public TextField TFAñoBodegas;
    public TextField Id;
    public Validaciones Validar;
    public Label lblNombre;
    
    public void initialize() {}
  
    public void initManager(final ReportesLiquidacionAdministrador ReportesLiquidacionAdministrador)
    {
        this.ReportesLiquidacionAdministrador = ReportesLiquidacionAdministrador;
        Validar = new Validaciones();
        llenarBodega();
    }
    
    public void AbrirReporteVendedoresMensuales(ActionEvent event)
    {
        GeneradordeReportes Reporte = new GeneradordeReportes();
        int Mes = ConvertirMes((String)ComBoMesVendedores.getValue());
        if(Validar.ValidarNumeros(this.TFAñoVendedores.getText()) && this.TFAñoVendedores.getText().length() <= 4 && Mes > 0)
        {
            Reporte.AbrirReporte("UtilidadesVendedoresMensuales.jasper",Integer.parseInt(Id.getText()),Integer.parseInt(TFAñoVendedores.getText()), Mes);
        }
        else if(!Validar.ValidarNumeros(this.TFAñoVendedores.getText()))
        {
            this.ReportesLiquidacionAdministrador.showMensajes("Año no Valido");
        }
        else if(Mes == 0)
        {
            this.ReportesLiquidacionAdministrador.showMensajes("Seleccione un Mes");
        }      
    }
    
    public void AbrirReporteBodegasMensuales(ActionEvent event)
    {
        GeneradordeReportes Reporte = new GeneradordeReportes();
        int Mes = ConvertirMes((String)ComBoMesBodegas.getValue());
        String Bodegas = (String)ComBoBodega.getValue();
        if(Validar.ValidarNumeros(this.TFAñoBodegas.getText()) && this.TFAñoBodegas.getText().length() <= 4 && Mes > 0 && Bodegas != null)
        {
            BodegaProductos Bodega = new IBodegaProductos().buscarBodegaPorNombre(Bodegas);
            Reporte.AbrirReporte("UtilidadesBodegaMensuales.jasper",Bodega.getIdbodega(),Integer.parseInt(TFAñoBodegas.getText()), Mes);
        }
        else if(!Validar.ValidarNumeros(this.TFAñoBodegas.getText()))
        {
            this.ReportesLiquidacionAdministrador.showMensajes("Año no Valido");
        }
        else if(Mes == 0)
        {
            this.ReportesLiquidacionAdministrador.showMensajes("Seleccione un Mes");
        }
        else if(Bodegas == null)
        {
            this.ReportesLiquidacionAdministrador.showMensajes("Seleccione una Bodega");
        } 
    }
    
    public void BuscarVenedor(ActionEvent event)
    {
        if(Validar.ValidarNumeros(Id.getText()))
        {
            Vendedores Vendedor = new IVendedores().buscarVendedorPorId(Integer.parseInt(Id.getText()));
            lblNombre.setText(Vendedor.getNombre()+" "+Vendedor.getApellido());
            ComBoMesVendedores.requestFocus();
        }
        else
        {
            this.ReportesLiquidacionAdministrador.showMensajes("Id no Valido");
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
    
    public void llenarBodega()
    {         
         List<String> list = new ArrayList<String>();
         if (new IBodegaProductos().listaDeBodegasDeProductos()!=null)
         {
            for (BodegaProductos bodega:new IBodegaProductos().listaDeBodegasDeProductos())
            {
                list.add(bodega.getNombre());
            }
            ObservableList<String> observableList = FXCollections.observableList(list);
            ComBoBodega.setItems(observableList);
         }
    }
}

