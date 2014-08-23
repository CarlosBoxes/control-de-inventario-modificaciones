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
@Table(name = "descripcion pedido materia prima")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DescripcionPedidoMateriaPrima.findAll", query = "SELECT d FROM DescripcionPedidoMateriaPrima d"),
    @NamedQuery(name = "DescripcionPedidoMateriaPrima.findByIddescripcionMP", query = "SELECT d FROM DescripcionPedidoMateriaPrima d WHERE d.iddescripcionMP = :iddescripcionMP"),
    @NamedQuery(name = "DescripcionPedidoMateriaPrima.findByCantidad", query = "SELECT d FROM DescripcionPedidoMateriaPrima d WHERE d.cantidad = :cantidad"),
    @NamedQuery(name = "DescripcionPedidoMateriaPrima.findByPrecioMateriaPrima", query = "SELECT d FROM DescripcionPedidoMateriaPrima d WHERE d.precioMateriaPrima = :precioMateriaPrima"),
    @NamedQuery(name = "DescripcionPedidoMateriaPrima.findBySubTotal", query = "SELECT d FROM DescripcionPedidoMateriaPrima d WHERE d.subTotal = :subTotal")})
public class DescripcionPedidoMateriaPrima implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "iddescripcionMP")
    private Integer iddescripcionMP;
    @Basic(optional = false)
    @Column(name = "cantidad")
    private float cantidad;
    @Basic(optional = false)
    @Column(name = "precioMateriaPrima")
    private float precioMateriaPrima;
    @Basic(optional = false)
    @Column(name = "subTotal")
    private float subTotal;
    @JoinColumn(name = "pedido_materia_prima_idpedidoMP", referencedColumnName = "idpedidoMP")
    @ManyToOne(optional = false)
    private PedidoMateriaPrima pedidomateriaprimaidpedidoMP;
    @JoinColumn(name = "materiaPrima_idmateriaPrima", referencedColumnName = "idmateriaPrima")
    @ManyToOne(optional = false)
    private MateriaPrima materiaPrimaidmateriaPrima;

    public DescripcionPedidoMateriaPrima() {
    }

    public DescripcionPedidoMateriaPrima(Integer iddescripcionMP) {
        this.iddescripcionMP = iddescripcionMP;
    }

    public DescripcionPedidoMateriaPrima(Integer iddescripcionMP, float cantidad, float precioMateriaPrima, float subTotal) {
        this.iddescripcionMP = iddescripcionMP;
        this.cantidad = cantidad;
        this.precioMateriaPrima = precioMateriaPrima;
        this.subTotal = subTotal;
    }

    public Integer getIddescripcionMP() {
        return iddescripcionMP;
    }

    public void setIddescripcionMP(Integer iddescripcionMP) {
        this.iddescripcionMP = iddescripcionMP;
    }

    public float getCantidad() {
        return cantidad;
    }

    public void setCantidad(float cantidad) {
        this.cantidad = cantidad;
    }

    public float getPrecioMateriaPrima() {
        return precioMateriaPrima;
    }

    public void setPrecioMateriaPrima(float precioMateriaPrima) {
        this.precioMateriaPrima = precioMateriaPrima;
    }

    public float getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(float subTotal) {
        this.subTotal = subTotal;
    }

    public PedidoMateriaPrima getPedidomateriaprimaidpedidoMP() {
        return pedidomateriaprimaidpedidoMP;
    }

    public void setPedidomateriaprimaidpedidoMP(PedidoMateriaPrima pedidomateriaprimaidpedidoMP) {
        this.pedidomateriaprimaidpedidoMP = pedidomateriaprimaidpedidoMP;
    }

    public MateriaPrima getMateriaPrimaidmateriaPrima() {
        return materiaPrimaidmateriaPrima;
    }

    public void setMateriaPrimaidmateriaPrima(MateriaPrima materiaPrimaidmateriaPrima) {
        this.materiaPrimaidmateriaPrima = materiaPrimaidmateriaPrima;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iddescripcionMP != null ? iddescripcionMP.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DescripcionPedidoMateriaPrima)) {
            return false;
        }
        DescripcionPedidoMateriaPrima other = (DescripcionPedidoMateriaPrima) object;
        if ((this.iddescripcionMP == null && other.iddescripcionMP != null) || (this.iddescripcionMP != null && !this.iddescripcionMP.equals(other.iddescripcionMP))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades.DescripcionPedidoMateriaPrima[ iddescripcionMP=" + iddescripcionMP + " ]";
    }
    
}
