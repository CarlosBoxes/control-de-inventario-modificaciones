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
public class DepositoModelo
{
        public SimpleIntegerProperty Id;
        public SimpleFloatProperty Monto;
        public SimpleStringProperty NumeroBoleta;
        public SimpleStringProperty Banco;
        
        public DepositoModelo(Integer Id, float Monto, String NumeroBoleta, String Banco)
        {
            this.Id = new SimpleIntegerProperty(Id);
            this.Monto = new SimpleFloatProperty(Monto);
            this.NumeroBoleta = new SimpleStringProperty(NumeroBoleta);
            this.Banco = new SimpleStringProperty(Banco);
        }
        
        public Integer getId()
        {
            return Id.get();
        }
        
        public float getMonto()
        {
            return Monto.get();
        }
        
        public String getNumeroBoleta()
        {
            return NumeroBoleta.get();
        }
        
        public String getBanco()
        {
            return Banco.get();
        }
}
