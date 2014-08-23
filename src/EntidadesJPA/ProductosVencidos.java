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
@Table(name = "productos vencidos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ProductosVencidos.findAll", query = "SELECT p FROM ProductosVencidos p"),
    @NamedQuery(name = "ProductosVencidos.findByIdProductosVencidos", query = "SELECT p FROM ProductosVencidos p WHERE p.idProductosVencidos = :idProductosVencidos"),
    @NamedQuery(name = "ProductosVencidos.findByCantidad", query = "SELECT p FROM ProductosVencidos p WHERE p.cantidad = :cantidad")})
public class ProductosVencidos implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idProductosVencidos")
    private Integer idProductosVencidos;
    @Basic(optional = false)
    @Column(name = "cantidad")
    private int cantidad;
    @JoinColumn(name = "Productos_idProductos", referencedColumnName = "idProductos")
    @ManyToOne(optional = false)
    private Productos productosidProductos;

    public ProductosVencidos() {
    }

    public ProductosVencidos(Integer idProductosVencidos) {
        this.idProductosVencidos = idProductosVencidos;
    }

    public ProductosVencidos(Integer idProductosVencidos, int cantidad) {
        this.idProductosVencidos = idProductosVencidos;
        this.cantidad = cantidad;
    }

    public Integer getIdProductosVencidos() {
        return idProductosVencidos;
    }

    public void setIdProductosVencidos(Integer idProductosVencidos) {
        this.idProductosVencidos = idProductosVencidos;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
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
        hash += (idProductosVencidos != null ? idProductosVencidos.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProductosVencidos)) {
            return false;
        }
        ProductosVencidos other = (ProductosVencidos) object;
        if ((this.idProductosVencidos == null && other.idProductosVencidos != null) || (this.idProductosVencidos != null && !this.idProductosVencidos.equals(other.idProductosVencidos))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades.ProductosVencidos[ idProductosVencidos=" + idProductosVencidos + " ]";
    }
    
}
