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
public class DespachoModelo
{
        public SimpleIntegerProperty Id;
        public SimpleStringProperty Nombre;
        public SimpleFloatProperty Total;
        
        public DespachoModelo(Integer Id, String Nombre, float Total)
        {
            this.Id = new SimpleIntegerProperty(Id);
            this.Nombre = new SimpleStringProperty(Nombre);
            this.Total = new SimpleFloatProperty(Total);
        }
        
        public Integer getId()
        {
            return Id.get();
        }
        
        public String getNombre()
        {
            return Nombre.get();
        }
        
        public float getTotal()
        {
            return Total.get();
        }
}
