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
public class ClienteModelo
{
        public SimpleIntegerProperty Id;
        public SimpleStringProperty Nombre;
        public SimpleStringProperty Direccion;
        public SimpleStringProperty Nit;
        public SimpleStringProperty Celular;
        public SimpleStringProperty Telefono;
        public SimpleStringProperty Tipo;
        public SimpleFloatProperty Saldo;
        
        public ClienteModelo(Integer Id, String Nombre, String Direccion, String Nit, String Celular, String Telefono, String Tipo, Float Saldo)
        {
            this.Id = new SimpleIntegerProperty(Id);
            this.Nombre = new SimpleStringProperty(Nombre);
            this.Direccion = new SimpleStringProperty(Direccion);
            this.Nit = new SimpleStringProperty(Nit);
            this.Celular = new SimpleStringProperty(Celular);
            this.Telefono = new SimpleStringProperty(Telefono);
            this.Tipo = new SimpleStringProperty(Tipo);
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
        
        public String getDireccion()
        {
            return Direccion.get();
        }
        
        public String getNit()
        {
            return Nit.get();
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
        
        public Float getSaldo()
        {
            return Saldo.get();
        }
}
