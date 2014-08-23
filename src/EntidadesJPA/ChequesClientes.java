/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package EntidadesJPA;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Luis
 */
@Entity
@Table(name = "cheques clientes")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ChequesClientes.findAll", query = "SELECT c FROM ChequesClientes c"),
    @NamedQuery(name = "ChequesClientes.findByIdchequesClientes", query = "SELECT c FROM ChequesClientes c WHERE c.idchequesClientes = :idchequesClientes"),
    @NamedQuery(name = "ChequesClientes.findByNumero", query = "SELECT c FROM ChequesClientes c WHERE c.numero = :numero AND c.eliminado = false"),
    @NamedQuery(name = "ChequesClientes.findByNumeroUsado", query = "SELECT c FROM ChequesClientes c WHERE c.numero = :numero AND c.eliminado = false AND c.usado = false"),
    @NamedQuery(name = "ChequesClientes.findByListaNumero", query = "SELECT c FROM ChequesClientes c WHERE c.eliminado = false"),
    @NamedQuery(name = "ChequesClientes.findByMonto", query = "SELECT c FROM ChequesClientes c WHERE c.monto = :monto"),
    @NamedQuery(name = "ChequesClientes.findByFecha", query = "SELECT c FROM ChequesClientes c WHERE c.fecha = :fecha"),
    @NamedQuery(name = "ChequesClientes.findByEliminado", query = "SELECT c FROM ChequesClientes c WHERE c.eliminado = :eliminado"),
    @NamedQuery(name = "ChequesClientes.findByUsado", query = "SELECT c FROM ChequesClientes c WHERE c.usado = :usado")})
public class ChequesClientes implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idcheques_clientes")
    private Integer idchequesClientes;
    @Basic(optional = false)
    @Column(name = "numero")
    private int numero;
    @Basic(optional = false)
    @Column(name = "monto")
    private float monto;
    @Basic(optional = false)
    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @Basic(optional = false)
    @Column(name = "eliminado")
    private boolean eliminado;
    @Basic(optional = false)
    @Column(name = "usado")
    private boolean usado;
    @OneToMany(mappedBy = "chequesClientesIdchequesClientes")
    private Collection<DescripcionLiquidacion> descripcionLiquidacionCollection;
    @OneToMany(mappedBy = "chequesClientesIdchequesClientes")
    private Collection<CuentaporCobrar> cuentaporcobrarCollection;
    @JoinColumn(name = "Clientes_idCliente", referencedColumnName = "idCliente")
    @ManyToOne(optional = false)
    private Clientes clientesidCliente;
    @JoinColumn(name = "bancos_idbancos", referencedColumnName = "idbancos")
    @ManyToOne(optional = false)
    private Bancos bancosIdbancos;

    public ChequesClientes() {
    }

    public ChequesClientes(Integer idchequesClientes) {
        this.idchequesClientes = idchequesClientes;
    }

    public ChequesClientes(Integer idchequesClientes, int numero, float monto, Date fecha, boolean eliminado, boolean usado) {
        this.idchequesClientes = idchequesClientes;
        this.numero = numero;
        this.monto = monto;
        this.fecha = fecha;
        this.eliminado = eliminado;
        this.usado = usado;
    }

    public Integer getIdchequesClientes() {
        return idchequesClientes;
    }

    public void setIdchequesClientes(Integer idchequesClientes) {
        this.idchequesClientes = idchequesClientes;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public float getMonto() {
        return monto;
    }

    public void setMonto(float monto) {
        this.monto = monto;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public boolean getEliminado() {
        return eliminado;
    }

    public void setEliminado(boolean eliminado) {
        this.eliminado = eliminado;
    }

    public boolean getUsado() {
        return usado;
    }

    public void setUsado(boolean usado) {
        this.usado = usado;
    }

    @XmlTransient
    public Collection<DescripcionLiquidacion> getDescripcionLiquidacionCollection() {
        return descripcionLiquidacionCollection;
    }

    public void setDescripcionLiquidacionCollection(Collection<DescripcionLiquidacion> descripcionLiquidacionCollection) {
        this.descripcionLiquidacionCollection = descripcionLiquidacionCollection;
    }

    @XmlTransient
    public Collection<CuentaporCobrar> getCuentaporcobrarCollection() {
        return cuentaporcobrarCollection;
    }

    public void setCuentaporcobrarCollection(Collection<CuentaporCobrar> cuentaporcobrarCollection) {
        this.cuentaporcobrarCollection = cuentaporcobrarCollection;
    }

    public Clientes getClientesidCliente() {
        return clientesidCliente;
    }

    public void setClientesidCliente(Clientes clientesidCliente) {
        this.clientesidCliente = clientesidCliente;
    }

    public Bancos getBancosIdbancos() {
        return bancosIdbancos;
    }

    public void setBancosIdbancos(Bancos bancosIdbancos) {
        this.bancosIdbancos = bancosIdbancos;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idchequesClientes != null ? idchequesClientes.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ChequesClientes)) {
            return false;
        }
        ChequesClientes other = (ChequesClientes) object;
        if ((this.idchequesClientes == null && other.idchequesClientes != null) || (this.idchequesClientes != null && !this.idchequesClientes.equals(other.idchequesClientes))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades.ChequesClientes[ idchequesClientes=" + idchequesClientes + " ]";
    }
    
}
