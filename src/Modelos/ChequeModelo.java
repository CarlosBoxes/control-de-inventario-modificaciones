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
public class ChequeModelo
{
        public SimpleFloatProperty Monto;
        public SimpleIntegerProperty NumeroCheque;
        public SimpleStringProperty Fecha;
        public SimpleStringProperty Nombre;
        public SimpleStringProperty Banco;
        
        public ChequeModelo(float Monto, int NumeroCheque, String Fecha, String Nombre, String Banco)
        {
            this.Monto = new SimpleFloatProperty(Monto);
            this.NumeroCheque = new SimpleIntegerProperty(NumeroCheque);
            this.Fecha = new SimpleStringProperty(Fecha);
            this.Nombre = new SimpleStringProperty(Nombre);
            this.Banco = new SimpleStringProperty(Banco);
        }
        
        public float getMonto()
        {
            return Monto.get();
        }
        
        public int getNumeroCheque()
        {
            return NumeroCheque.get();
        }
        
        public String getFecha()
        {
            return Fecha.get();
        }
        
        public String getNombre()
        {
            return Nombre.get();
        }
        
        public String getBanco()
        {
            return Banco.get();
        }
}
