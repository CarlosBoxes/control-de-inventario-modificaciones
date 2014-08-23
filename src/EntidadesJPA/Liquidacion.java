/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package EntidadesJPA;

import java.io.Serializable;
import java.util.Collection;
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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Luis
 */
@Entity
@Table(name = "liquidacion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Liquidacion.findAll", query = "SELECT l FROM Liquidacion l"),
    @NamedQuery(name = "Liquidacion.findByIdliquidacion", query = "SELECT l FROM Liquidacion l WHERE l.idliquidacion = :idliquidacion"),
    @NamedQuery(name = "Liquidacion.findByTotal", query = "SELECT l FROM Liquidacion l WHERE l.total = :total"),
    @NamedQuery(name = "Liquidacion.findBySubtotal", query = "SELECT l FROM Liquidacion l WHERE l.subtotal = :subtotal")})
public class Liquidacion implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idliquidacion")
    private Integer idliquidacion;
    @Basic(optional = false)
    @Column(name = "total")
    private float total;
    @Basic(optional = false)
    @Column(name = "subtotal")
    private float subtotal;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "liquidacionIdliquidacion")
    private Collection<DescripcionLiquidacion> descripcionLiquidacionCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "liquidacionIdliquidacion")
    private Collection<ProductoaCambiar> productoacambiarCollection;
    @JoinColumn(name = "pedido_idpedido", referencedColumnName = "idpedido")
    @ManyToOne(optional = false)
    private Pedido pedidoIdpedido;

    public Liquidacion() {
    }

    public Liquidacion(Integer idliquidacion) {
        this.idliquidacion = idliquidacion;
    }

    public Liquidacion(Integer idliquidacion, float total, float subtotal) {
        this.idliquidacion = idliquidacion;
        this.total = total;
        this.subtotal = subtotal;
    }

    public Integer getIdliquidacion() {
        return idliquidacion;
    }

    public void setIdliquidacion(Integer idliquidacion) {
        this.idliquidacion = idliquidacion;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public float getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(float subtotal) {
        this.subtotal = subtotal;
    }

    @XmlTransient
    public Collection<DescripcionLiquidacion> getDescripcionLiquidacionCollection() {
        return descripcionLiquidacionCollection;
    }

    public void setDescripcionLiquidacionCollection(Collection<DescripcionLiquidacion> descripcionLiquidacionCollection) {
        this.descripcionLiquidacionCollection = descripcionLiquidacionCollection;
    }

    @XmlTransient
    public Collection<ProductoaCambiar> getProductoacambiarCollection() {
        return productoacambiarCollection;
    }

    public void setProductoacambiarCollection(Collection<ProductoaCambiar> productoacambiarCollection) {
        this.productoacambiarCollection = productoacambiarCollection;
    }

    public Pedido getPedidoIdpedido() {
        return pedidoIdpedido;
    }

    public void setPedidoIdpedido(Pedido pedidoIdpedido) {
        this.pedidoIdpedido = pedidoIdpedido;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idliquidacion != null ? idliquidacion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Liquidacion)) {
            return false;
        }
        Liquidacion other = (Liquidacion) object;
        if ((this.idliquidacion == null && other.idliquidacion != null) || (this.idliquidacion != null && !this.idliquidacion.equals(other.idliquidacion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades.Liquidacion[ idliquidacion=" + idliquidacion + " ]";
    }
    
}
