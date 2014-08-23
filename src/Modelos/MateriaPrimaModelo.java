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
public class MateriaPrimaModelo
{
        public SimpleIntegerProperty Id;
        public SimpleStringProperty Nombre;
        public SimpleFloatProperty Cantidad;
        public SimpleStringProperty Presentacion;
        
        public MateriaPrimaModelo(Integer Id, String Nombre, Float Cantidad, String Presentacion)
        {
            this.Id = new SimpleIntegerProperty(Id);
            this.Nombre = new SimpleStringProperty(Nombre);
            this.Presentacion = new SimpleStringProperty(Presentacion);
            this.Cantidad = new SimpleFloatProperty(Cantidad);
        }
        
        public Integer getId()
        {
            return Id.get();
        }
        
        public String getNombre()
        {
            return Nombre.get();
        }
        
        public Float getCantidad()
        {
            return Cantidad.get();
        }
        
        public String getPresentacion()
        {
            return Presentacion.get();
        }
}
