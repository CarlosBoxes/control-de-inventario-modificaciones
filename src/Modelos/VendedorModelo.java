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
public class VendedorModelo
{
        public SimpleIntegerProperty Id;
        public SimpleStringProperty NombreyApellido;
        public SimpleStringProperty Celular;
        public SimpleStringProperty Telefono;
        public SimpleStringProperty Tipo;
        
        public VendedorModelo(Integer Id, String NombreyApellido, String Celular, String Telefono, String Tipo)
        {
            this.Id = new SimpleIntegerProperty(Id);
            this.NombreyApellido = new SimpleStringProperty(NombreyApellido);
            this.Celular = new SimpleStringProperty(Celular);
            this.Telefono = new SimpleStringProperty(Telefono);
            this.Tipo = new SimpleStringProperty(Tipo);
        }
        
        public Integer getId()
        {
            return Id.get();
        }
        
        public String getNombre()
        {
            return NombreyApellido.get();
        }
        
        public String getCelular()
        {
            return Celular.get();
        }
        
        public String getTelefono()
        {
            return Telefono.get();
        }
        
        public String getTipo()
        {
            return Tipo.get();
        }
}
