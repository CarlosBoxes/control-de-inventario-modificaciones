/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package EntidadesJPA;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Luis
 */
@Entity
@Table(name = "cuentaporcobrar")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Cuentaporcobrar.findAll", query = "SELECT c FROM CuentaporCobrar c"),
    @NamedQuery(name = "Cuentaporcobrar.findByIdcuenta", query = "SELECT c FROM CuentaporCobrar c WHERE c.idcuenta = :idcuenta"),
    @NamedQuery(name = "Cuentaporcobrar.findByEfectivo", query = "SELECT c FROM CuentaporCobrar c WHERE c.efectivo = :efectivo"),
    @NamedQuery(name = "Cuentaporcobrar.findByFecha", query = "SELECT c FROM CuentaporCobrar c WHERE c.fecha = :fecha")})
public class CuentaporCobrar implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "idcuenta")
    private Integer idcuenta;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "Efectivo")
    private Float efectivo;
    @Basic(optional = false)
    @Column(name = "Fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @JoinColumn(name = "Depositos_idDepositos", referencedColumnName = "idDepositos")
    @ManyToOne
    private Depositos depositosidDepositos;
    @JoinColumn(name = "Clientes_idCliente", referencedColumnName = "idCliente")
    @ManyToOne(optional = false)
    private Clientes clientesidCliente;
    @JoinColumn(name = "cheques_clientes_idcheques_clientes", referencedColumnName = "idcheques_clientes")
    @ManyToOne
    private ChequesClientes chequesClientesIdchequesClientes;

    public CuentaporCobrar() {
    }

    public CuentaporCobrar(Integer idcuenta) {
        this.idcuenta = idcuenta;
    }

    public CuentaporCobrar(Integer idcuenta, Date fecha) {
        this.idcuenta = idcuenta;
        this.fecha = fecha;
    }

    public Integer getIdcuenta() {
        return idcuenta;
    }

    public void setIdcuenta(Integer idcuenta) {
        this.idcuenta = idcuenta;
    }

    public Float getEfectivo() {
        return efectivo;
    }

    public void setEfectivo(Float efectivo) {
        this.efectivo = efectivo;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Depositos getDepositosidDepositos() {
        return depositosidDepositos;
    }

    public void setDepositosidDepositos(Depositos depositosidDepositos) {
        this.depositosidDepositos = depositosidDepositos;
    }

    public Clientes getClientesidCliente() {
        return clientesidCliente;
    }

    public void setClientesidCliente(Clientes clientesidCliente) {
        this.clientesidCliente = clientesidCliente;
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
        hash += (idcuenta != null ? idcuenta.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CuentaporCobrar)) {
            return false;
        }
        CuentaporCobrar other = (CuentaporCobrar) object;
        if ((this.idcuenta == null && other.idcuenta != null) || (this.idcuenta != null && !this.idcuenta.equals(other.idcuenta))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades.CuentaporCobrar[ idcuenta=" + idcuenta + " ]";
    }
    
}
