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
public class ProveedorModelo
{
        public SimpleIntegerProperty Id;
        public SimpleStringProperty Nombre;
        public SimpleStringProperty Nit;
        public SimpleStringProperty Direccion;
        public SimpleStringProperty Telefono;
        public SimpleStringProperty Celular;
        public SimpleFloatProperty Saldo;
        
        public ProveedorModelo(Integer Id, String Nombre, String Nit, String Direccion, String Telefono, String Celular, Float Saldo)
        {
            this.Id = new SimpleIntegerProperty(Id);
            this.Nombre = new SimpleStringProperty(Nombre);
            this.Nit = new SimpleStringProperty(Nit);
            this.Direccion = new SimpleStringProperty(Direccion);
            this.Telefono = new SimpleStringProperty(Telefono);
            this.Celular = new SimpleStringProperty(Celular);
            this.Saldo = new SimpleFloatProperty(Saldo);
        }
        
        public Integer getId()
        {
            return Id.get();
        }
        
        public String getNombre()
        {
            return Nombre.get();
        }
        
        public String getNit()
        {
            return Nit.get();
        }
        
        public String getDireccion()
        {
            return Direccion.get();
        }
        
        public String getTelefono()
        {
            return Telefono.get();
        }
        
        public String getCelular()
        {
            return Celular.get();
        }
        
        public Float getSaldo()
        {
            return Saldo.get();
        }
}
