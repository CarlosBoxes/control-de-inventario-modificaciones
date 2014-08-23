/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelos;

import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;


/**
 *
 * @author luis__000
 */
public class FacturaModelo
{
        public SimpleIntegerProperty Numero;
        public SimpleStringProperty Serie;
        public SimpleStringProperty Nombre;
        public SimpleFloatProperty Total;
        public SimpleStringProperty Anulada;
        
        public FacturaModelo(Integer Numero, String Serie, String Nombre, Float Total, String Anulada)
        {
            this.Numero = new SimpleIntegerProperty(Numero);
            this.Serie = new SimpleStringProperty(Serie);
            this.Nombre = new SimpleStringProperty(Nombre);
            this.Total = new SimpleFloatProperty(Total);
            this.Anulada = new SimpleStringProperty(Anulada);
        }
        
        public Integer getNumero()
        {
            return Numero.get();
        }
        
        public String getSerie()
        {
            return Serie.get();
        }
        
        public String getNombre()
        {
            return Nombre.get();
        }
        
        public Float getTotal()
        {
            return Total.get();
        }
        
        public String getAnulada()
        {
            return Anulada.get();
        }
}
