/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package EntidadesJPA;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
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
@Table(name = "pedido materia prima")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PedidoMateriaPrima.findAll", query = "SELECT p FROM PedidoMateriaPrima p"),
    @NamedQuery(name = "PedidoMateriaPrima.findByLista", query = "SELECT p FROM PedidoMateriaPrima p WHERE p.aplicado = false"),
    @NamedQuery(name = "PedidoMateriaPrima.findByIdpedidoMP", query = "SELECT p FROM PedidoMateriaPrima p WHERE p.idpedidoMP = :idpedidoMP"),
    @NamedQuery(name = "PedidoMateriaPrima.findByFecha", query = "SELECT p FROM PedidoMateriaPrima p WHERE p.fecha = :fecha"),
    @NamedQuery(name = "PedidoMateriaPrima.findByAplicado", query = "SELECT p FROM PedidoMateriaPrima p WHERE p.aplicado = :aplicado"),
    @NamedQuery(name = "PedidoMateriaPrima.findByTotal", query = "SELECT p FROM PedidoMateriaPrima p WHERE p.total = :total")})
public class PedidoMateriaPrima implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idpedidoMP")
    private Integer idpedidoMP;
    @Basic(optional = false)
    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @Column(name = "aplicado")
    private Boolean aplicado;
    @Basic(optional = false)
    @Column(name = "total")
    private float total;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pedidomateriaprimaidpedidoMP")
    private Collection<DescripcionPedidoMateriaPrima> descripcionPedidoMateriaPrimaCollection;
    @JoinColumn(name = "Proveedores_idProveedores", referencedColumnName = "idProveedores")
    @ManyToOne(optional = false)
    private Proveedores proveedoresidProveedores;

    public PedidoMateriaPrima() {
    }

    public PedidoMateriaPrima(Integer idpedidoMP) {
        this.idpedidoMP = idpedidoMP;
    }

    public PedidoMateriaPrima(Integer idpedidoMP, Date fecha, float total) {
        this.idpedidoMP = idpedidoMP;
        this.fecha = fecha;
        this.total = total;
    }

    public Integer getIdpedidoMP() {
        return idpedidoMP;
    }

    public void setIdpedidoMP(Integer idpedidoMP) {
        this.idpedidoMP = idpedidoMP;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Boolean getAplicado() {
        return aplicado;
    }

    public void setAplicado(Boolean aplicado) {
        this.aplicado = aplicado;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    @XmlTransient
    public Collection<DescripcionPedidoMateriaPrima> getDescripcionPedidoMateriaPrimaCollection() {
        return descripcionPedidoMateriaPrimaCollection;
    }

    public void setDescripcionPedidoMateriaPrimaCollection(Collection<DescripcionPedidoMateriaPrima> descripcionPedidoMateriaPrimaCollection) {
        this.descripcionPedidoMateriaPrimaCollection = descripcionPedidoMateriaPrimaCollection;
    }

    public Proveedores getProveedoresidProveedores() {
        return proveedoresidProveedores;
    }

    public void setProveedoresidProveedores(Proveedores proveedoresidProveedores) {
        this.proveedoresidProveedores = proveedoresidProveedores;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idpedidoMP != null ? idpedidoMP.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PedidoMateriaPrima)) {
            return false;
        }
        PedidoMateriaPrima other = (PedidoMateriaPrima) object;
        if ((this.idpedidoMP == null && other.idpedidoMP != null) || (this.idpedidoMP != null && !this.idpedidoMP.equals(other.idpedidoMP))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades.PedidoMateriaPrima[ idpedidoMP=" + idpedidoMP + " ]";
    }
    
}
