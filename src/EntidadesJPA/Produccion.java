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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
@Table(name = "produccion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Produccion.findAll", query = "SELECT p FROM Produccion p"),
    @NamedQuery(name = "Produccion.findByIdproduccion", query = "SELECT p FROM Produccion p WHERE p.idproduccion = :idproduccion"),
    @NamedQuery(name = "Produccion.findByCantidad", query = "SELECT p FROM Produccion p WHERE p.cantidad = :cantidad"),
    @NamedQuery(name = "Produccion.findByFecha", query = "SELECT p FROM Produccion p WHERE p.fecha = :fecha"),
    @NamedQuery(name = "Produccion.findByFechaPorMes", query = "SELECT p FROM Produccion p WHERE p.fecha = :fecha")})
public class Produccion implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idproduccion")
    private Integer idproduccion;
    @Basic(optional = false)
    @Column(name = "cantidad")
    private int cantidad;
    @Basic(optional = false)
    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @JoinColumn(name = "productos_idProductos", referencedColumnName = "idProductos")
    @ManyToOne(optional = false)
    private Productos productosidProductos;

    public Produccion() {
    }

    public Produccion(Integer idproduccion) {
        this.idproduccion = idproduccion;
    }

    public Produccion(Integer idproduccion, int cantidad, Date fecha) {
        this.idproduccion = idproduccion;
        this.cantidad = cantidad;
        this.fecha = fecha;
    }

    public Integer getIdproduccion() {
        return idproduccion;
    }

    public void setIdproduccion(Integer idproduccion) {
        this.idproduccion = idproduccion;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
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
        hash += (idproduccion != null ? idproduccion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Produccion)) {
            return false;
        }
        Produccion other = (Produccion) object;
        if ((this.idproduccion == null && other.idproduccion != null) || (this.idproduccion != null && !this.idproduccion.equals(other.idproduccion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "EntidadesJPA.Produccion[ idproduccion=" + idproduccion + " ]";
    }
    
}
