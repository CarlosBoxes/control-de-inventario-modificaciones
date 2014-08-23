/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelos;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;


/**
 *
 * @author luis__000
 */
public class BancoModelo
{
        public SimpleIntegerProperty Id;
        public SimpleStringProperty Nombre;
        public SimpleStringProperty Telefono;
        
        public BancoModelo(Integer Id, String Nombre, String Telefono)
        {
            this.Id = new SimpleIntegerProperty(Id);
            this.Nombre = new SimpleStringProperty(Nombre);
            this.Telefono = new SimpleStringProperty(Telefono);
        }
        
        public Integer getId()
        {
            return Id.get();
        }
        
        public String getNombre()
        {
            return Nombre.get();
        }
        
        public String getTelefono()
        {
            return Telefono.get();
        }
}
