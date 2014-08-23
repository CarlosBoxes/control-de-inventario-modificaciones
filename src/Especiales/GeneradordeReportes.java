/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Especiales;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author luis__000
 */
public class GeneradordeReportes
{
    final String Logo;
    
    public GeneradordeReportes()
    {
        Logo = "Logo.jpg";
    }
    
    private Connection conexionBaseDeDatos() throws ClassNotFoundException, SQLException
    {
        Class.forName("com.mysql.jdbc.Driver"); 
        Connection Conexion = DriverManager.getConnection("jdbc:mysql://192.168.1.8:3306/bdcc", "administradorCC", "corporacioncampo");
        //Connection Conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/bdcc", "root", "1234");
        return Conexion;
    }
    
    private JasperPrint GenerarReporte(String pathRaiz, Integer Id) throws ClassNotFoundException, SQLException, JRException
    {    
         JasperReport Reporte = (JasperReport) JRLoader.loadObject(pathRaiz);
         Map<String, Object> Parametro = new HashMap<String, Object>();
         Parametro.put("Id", Id); 
         Parametro.put("Logo", Logo);
         JasperPrint JasperPrint = JasperFillManager.fillReport(Reporte,Parametro,conexionBaseDeDatos());
         return JasperPrint;
    }
    
    private JasperPrint GenerarReporte(String pathRaiz, Integer Id, Float Saldo) throws ClassNotFoundException, SQLException, JRException
    {    
         JasperReport Reporte = (JasperReport) JRLoader.loadObject(pathRaiz);
         Map<String, Object> Parametro = new HashMap<String, Object>();
         Parametro.put("Id", Id); 
         Parametro.put("Logo", Logo);
         Parametro.put("Saldo Anterior", Saldo);
         JasperPrint JasperPrint = JasperFillManager.fillReport(Reporte,Parametro,conexionBaseDeDatos());
         return JasperPrint;
    }
    
    private JasperPrint GenerarReporte(String pathRaiz, Integer Id, Integer Año) throws ClassNotFoundException, SQLException, JRException
    {    
         JasperReport Reporte = (JasperReport) JRLoader.loadObject(pathRaiz);
         Map<String, Object> Parametro = new HashMap<String, Object>();
         Parametro.put("ID", Id);
         Parametro.put("Año", Año);
         Parametro.put("Logo", Logo);
         JasperPrint JasperPrint = JasperFillManager.fillReport(Reporte,Parametro,conexionBaseDeDatos());
         return JasperPrint;
    }
    
    private JasperPrint GenerarReporte(String pathRaiz, Integer Id, Integer Año, Integer Mes) throws ClassNotFoundException, SQLException, JRException
    {    
         JasperReport Reporte = (JasperReport) JRLoader.loadObject(pathRaiz);
         Map<String, Object> Parametro = new HashMap<String, Object>();
         Parametro.put("ID", Id);
         Parametro.put("Año", Año);
         Parametro.put("Mes", Mes);
         Parametro.put("Logo", Logo);
         JasperPrint JasperPrint = JasperFillManager.fillReport(Reporte,Parametro,conexionBaseDeDatos());
         return JasperPrint;
    }
    
    private JasperPrint GenerarReporte(String pathRaiz) throws JRException, ClassNotFoundException, SQLException
    {
        JasperReport Reporte = (JasperReport) JRLoader.loadObject(pathRaiz);
        Map<String, Object> Parametro = new HashMap<String, Object>();
        Parametro.put("Logo", Logo);
        JasperPrint JasperPrint = JasperFillManager.fillReport(Reporte,Parametro,conexionBaseDeDatos());
        return JasperPrint;
    }
    
    public boolean AbrirReporte(String pathRaiz, Integer Id)
    {
        try
        {
            JasperViewer.viewReport(GenerarReporte(pathRaiz, Id),false);
            return true;
        }
        catch (ClassNotFoundException | SQLException | JRException e)
        {
            System.out.println(e);
            JOptionPane.showInputDialog(null, e);
            return false;
        }                
    }
    
    public boolean AbrirReporte(String pathRaiz, Integer Id, Float Saldo)
    {
        try
        {
            JasperViewer.viewReport(GenerarReporte(pathRaiz, Id, Saldo),false);
            return true;
        }
        catch (ClassNotFoundException | SQLException | JRException e)
        {
            System.out.println(e);
            JOptionPane.showInputDialog(null, e);
            return false;
        }                
    }
    
    public boolean AbrirReporte(String pathRaiz, Integer Id, Integer Año)
    {
        try
        {
            JasperViewer.viewReport(GenerarReporte(pathRaiz, Id, Año),false);
            return true;
        }
        catch (ClassNotFoundException | SQLException | JRException e)
        {
            System.out.println(e);
            JOptionPane.showMessageDialog(null, e);
            return false;
        }                
    }
    
    public boolean AbrirReporte(String pathRaiz, Integer Id, Integer Año, Integer Mes)
    {
        try
        {
            JasperViewer.viewReport(GenerarReporte(pathRaiz, Id, Año, Mes),false);
            return true;
        }
        catch (ClassNotFoundException | SQLException | JRException e)
        {
            System.out.println(e);
            JOptionPane.showMessageDialog(null, e);
            return false;
        }                
    }
    
    public boolean AbrirReporte(String pathRaiz)
    {
        try
        {
            JasperViewer.viewReport(GenerarReporte(pathRaiz),false);
            return true;
        }
        catch (ClassNotFoundException | SQLException | JRException e)
        {
            System.out.println(e);
            JOptionPane.showMessageDialog(null, e);
            return false;
        }                
    }    
}
