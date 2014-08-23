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
 * @author Ch470
 */
@Entity
@Table(name = "traslado")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Traslado.findAll", query = "SELECT t FROM Traslado t"),
    @NamedQuery(name = "Traslado.findByIdtraslado", query = "SELECT t FROM Traslado t WHERE t.idtraslado = :idtraslado"),
    @NamedQuery(name = "Traslado.findByCantidad", query = "SELECT t FROM Traslado t WHERE t.cantidad = :cantidad"),
    @NamedQuery(name = "Traslado.findByFecha", query = "SELECT t FROM Traslado t WHERE t.fecha = :fecha")})
public class Traslado implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "idtraslado")
    private Integer idtraslado;
    @Column(name = "cantidad")
    private Integer cantidad;
    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @JoinColumn(name = "productos_idProductos", referencedColumnName = "idProductos")
    @ManyToOne(optional = false)
    private Productos productosidProductos;

    public Traslado() {
    }

    public Traslado(Integer idtraslado) {
        this.idtraslado = idtraslado;
    }

    public Integer getIdtraslado() {
        return idtraslado;
    }

    public void setIdtraslado(Integer idtraslado) {
        this.idtraslado = idtraslado;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Productos getProductosidProductos() {
        return productosidProductos;
    }

    public void setProductosidProductos(Productos productosidProductos) {
        this.productosidProductos = productosidProductos;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idtraslado != null ? idtraslado.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Traslado)) {
            return false;
        }
        Traslado other = (Traslado) object;
        if ((this.idtraslado == null && other.idtraslado != null) || (this.idtraslado != null && !this.idtraslado.equals(other.idtraslado))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "EntidadesJPA.Traslado[ idtraslado=" + idtraslado + " ]";
    }
    
}
