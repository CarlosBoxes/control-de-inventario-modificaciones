/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelos;

import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleStringProperty;


/**
 *
 * @author luis__000
 */
public class DescripcionLiquidacionModelo
{
        public SimpleStringProperty NumeroBoleta;
        public SimpleStringProperty NumeroCheque;
        public SimpleFloatProperty Monto;
        
        public DescripcionLiquidacionModelo(String NumeroBoleta, String NumeroCheque, float Monto)
        {
            this.NumeroBoleta = new SimpleStringProperty(NumeroBoleta);
            this.NumeroCheque = new SimpleStringProperty(NumeroCheque);
            this.Monto = new SimpleFloatProperty(Monto);
        }
        
        public String getNumeroBoleta()
        {
            return NumeroBoleta.get();
        }
        
        public String getNumeroCheque()
        {
            return NumeroCheque.get();
        }
        
        public float getMonto()
        {
            return Monto.get();
        }
}
