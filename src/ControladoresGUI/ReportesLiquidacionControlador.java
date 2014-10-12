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
import java.text.DateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

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
    public TextField TFFechaI;
    public TextField TFFechaF;
    public TextField Id;
    public Validaciones Validar;
    public Label lblNombre;
    private int ContadorTeclaI;
    private int ContadorTeclaF;
    
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
    
    public void AbrirReporteUtilidades(ActionEvent event)
    {
        GeneradordeReportes Reporte = new GeneradordeReportes();
        if(ValidarFecha(TFFechaI.getText()) && ValidarFecha(TFFechaF.getText()))
        {
            String FechaIn = TFFechaI.getText();
            String FechaFin = TFFechaF.getText();
            DateFormat Formato = DateFormat.getDateInstance(DateFormat.SHORT);
            Date FechaI = null;
            Date FechaF = null;
            try 
            {
                FechaI = Formato.parse(FechaIn);
                FechaF = Formato.parse(FechaFin);
            } 
            catch (ParseException ex) 
            {
                Logger.getLogger(NuevoProductoControlador.class.getName()).log(Level.SEVERE, null, ex);
            }
            Reporte.AbrirReporte("Utilidades.jasper",FechaI,FechaF);
        }
        else
        {
            this.ReportesLiquidacionAdministrador.showMensajes("Verifique los campos Fecha Inicio o Fecha Fin");
        }  
    }
    
    public void SoltoTeclaI(KeyEvent e)
    {
        String Tecla = e.getText();
        int Key;
        Key = e.getCode().impl_getCode();
        boolean Valido = Validar.ValidarNumero(Tecla);
        if(Key == 8)
        {
            ComprobarTamañoI();
        }
        else if(Key == 111 || Key == 55)
        {
            AgregarCeroI();
        }
        else
        {
            if((Key > 46 && Key < 106))
            {
                if(!Valido)
                {
                    TFFechaI.deletePreviousChar();
                }
                else
                {

                    ContadorTeclaI++;
                    if(TFFechaI.getText().length()<6)
                    {
                        if(ContadorTeclaI == 2)
                        {
                            TFFechaI.setText(TFFechaI.getText()+"/");
                            TFFechaI.end();
                            ComprobarTamañoI();
                        }
                    }
                    else if(TFFechaI.getText().length() > 10)
                    {
                        TFFechaI.deletePreviousChar();
                    }
                    ComprobarTamañoI();
                }
            }
        }
    }
    
    private void ComprobarTamañoI()
    {
        if(TFFechaI.getText().length() == 0 || TFFechaI.getText().length() == 3 || TFFechaI.getText().length() == 5)
        {
            ContadorTeclaI = 0;
        }
        if(TFFechaI.getText().length() == 1 || TFFechaI.getText().length() == 4)
        {
            ContadorTeclaI = 1;
        }
    }
    
    private void AgregarCeroI()
    {
        if(TFFechaI.getText().length() == 2)
        {
            TFFechaI.setText("0"+TFFechaI.getText());
            TFFechaI.end();
            ContadorTeclaI = 0;
        }
        else if(TFFechaI.getText().length() == 5)
        {
            TFFechaI.insertText(3, "0");
            TFFechaI.end();
            ContadorTeclaI = 0;
        }
        else if(TFFechaI.getText().length() < 3)
        {
            TFFechaI.deletePreviousChar();
        }
        else if(TFFechaI.getText().length() > 3 && TFFechaI.getText().length() < 6)
        {
            TFFechaI.deletePreviousChar();
        }
        else if(TFFechaI.getText().length() > 6)
        {
            TFFechaI.deletePreviousChar();
        }
    }
    
    public void SoltoTeclaF(KeyEvent e)
    {
        String Tecla = e.getText();
        int Key;
        Key = e.getCode().impl_getCode();
        boolean Valido = Validar.ValidarNumero(Tecla);
        if(Key == 8)
        {
            ComprobarTamañoF();
        }
        else if(Key == 111 || Key == 55)
        {
            AgregarCeroF();
        }
        else
        {
            if((Key > 46 && Key < 106))
            {
                if(!Valido)
                {
                    TFFechaF.deletePreviousChar();
                }
                else
                {

                    ContadorTeclaF++;
                    if(TFFechaF.getText().length()<6)
                    {
                        if(ContadorTeclaF == 2)
                        {
                            TFFechaF.setText(TFFechaF.getText()+"/");
                            TFFechaF.end();
                            ComprobarTamañoF();
                        }
                    }
                    else if(TFFechaF.getText().length() > 10)
                    {
                        TFFechaF.deletePreviousChar();
                    }
                    ComprobarTamañoF();
                }
            }
        }
    }
    
    private void ComprobarTamañoF()
    {
        if(TFFechaF.getText().length() == 0 || TFFechaF.getText().length() == 3 || TFFechaF.getText().length() == 5)
        {
            ContadorTeclaF = 0;
        }
        if(TFFechaF.getText().length() == 1 || TFFechaF.getText().length() == 4)
        {
            ContadorTeclaF = 1;
        }
    }
    
    private void AgregarCeroF()
    {
        if(TFFechaF.getText().length() == 2)
        {
            TFFechaF.setText("0"+TFFechaF.getText());
            TFFechaF.end();
            ContadorTeclaF = 0;
        }
        else if(TFFechaF.getText().length() == 5)
        {
            TFFechaF.insertText(3, "0");
            TFFechaF.end();
            ContadorTeclaF = 0;
        }
        else if(TFFechaF.getText().length() < 3)
        {
            TFFechaF.deletePreviousChar();
        }
        else if(TFFechaF.getText().length() > 3 && TFFechaF.getText().length() < 6)
        {
            TFFechaF.deletePreviousChar();
        }
        else if(TFFechaF.getText().length() > 6)
        {
            TFFechaF.deletePreviousChar();
        }
    }
    
    private boolean ValidarFecha(String Fecha)
    {
        if(Validar.FormatoFecha(Fecha))
        {
            return true;
        }
        else
        {
            return false;
        }
    }
}

