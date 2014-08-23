/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package EntidadesJPA;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Luis
 */
@Entity
@Table(name = "descripcion liquidacion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DescripcionLiquidacion.findAll", query = "SELECT d FROM DescripcionLiquidacion d"),
    @NamedQuery(name = "DescripcionLiquidacion.findByIdDescripcionLiquidacion", query = "SELECT d FROM DescripcionLiquidacion d WHERE d.idDescripcionLiquidacion = :idDescripcionLiquidacion")})
public class DescripcionLiquidacion implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idDescripcionLiquidacion")
    private Integer idDescripcionLiquidacion;
    @JoinColumn(name = "liquidacion_idliquidacion", referencedColumnName = "idliquidacion")
    @ManyToOne(optional = false)
    private Liquidacion liquidacionIdliquidacion;
    @JoinColumn(name = "Depositos_idDepositos", referencedColumnName = "idDepositos")
    @ManyToOne
    private Depositos depositosidDepositos;
    @JoinColumn(name = "cheques_clientes_idcheques_clientes", referencedColumnName = "idcheques_clientes")
    @ManyToOne
    private ChequesClientes chequesClientesIdchequesClientes;

    public DescripcionLiquidacion() {
    }

    public DescripcionLiquidacion(Integer idDescripcionLiquidacion) {
        this.idDescripcionLiquidacion = idDescripcionLiquidacion;
    }

    public Integer getIdDescripcionLiquidacion() {
        return idDescripcionLiquidacion;
    }

    public void setIdDescripcionLiquidacion(Integer idDescripcionLiquidacion) {
        this.idDescripcionLiquidacion = idDescripcionLiquidacion;
    }

    public Liquidacion getLiquidacionIdliquidacion() {
        return liquidacionIdliquidacion;
    }

    public void setLiquidacionIdliquidacion(Liquidacion liquidacionIdliquidacion) {
        this.liquidacionIdliquidacion = liquidacionIdliquidacion;
    }

    public Depositos getDepositosidDepositos() {
        return depositosidDepositos;
    }

    public void setDepositosidDepositos(Depositos depositosidDepositos) {
        this.depositosidDepositos = depositosidDepositos;
    }

    public ChequesClientes getChequesClientesIdchequesClientes() {
        return chequesClientesIdchequesClientes;
    }

    public void setChequesClientesIdchequesClientes(ChequesClientes chequesClientesIdchequesClientes) {
        this.chequesClientesIdchequesClientes = chequesClientesIdchequesClientes;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idDescripcionLiquidacion != null ? idDescripcionLiquidacion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DescripcionLiquidacion)) {
            return false;
        }
        DescripcionLiquidacion other = (DescripcionLiquidacion) object;
        if ((this.idDescripcionLiquidacion == null && other.idDescripcionLiquidacion != null) || (this.idDescripcionLiquidacion != null && !this.idDescripcionLiquidacion.equals(other.idDescripcionLiquidacion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades.DescripcionLiquidacion[ idDescripcionLiquidacion=" + idDescripcionLiquidacion + " ]";
    }
    
}
