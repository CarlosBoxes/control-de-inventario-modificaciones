/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelos;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;


/**
 *
 * @author luis__000
 */
public class RecepcionModelo
{
        public SimpleIntegerProperty Id;
        public SimpleStringProperty Nombre;
        public SimpleFloatProperty Total;
        public SimpleBooleanProperty Tipo;
        public SimpleBooleanProperty Almacenado;
        
        public RecepcionModelo(Integer Id, String Nombre, float Total, boolean Tipo, boolean Almacenado)
        {
            this.Id = new SimpleIntegerProperty(Id);
            this.Nombre = new SimpleStringProperty(Nombre);
            this.Total = new SimpleFloatProperty(Total);
            this.Tipo = new SimpleBooleanProperty(Tipo);
            this.Almacenado = new SimpleBooleanProperty(Almacenado);
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
        
        public boolean getTipo()
        {
            return Tipo.get();
        }
        
        public boolean getAlmacenado()
        {
            return Almacenado.get();
        }
}
