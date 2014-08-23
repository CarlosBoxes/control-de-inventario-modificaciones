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
@Table(name = "productosvendedores")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ProductosVendedores.findAll", query = "SELECT p FROM ProductosVendedores p"),
    @NamedQuery(name = "ProductosVendedores.findByIdlisitaproductosvendedores", query = "SELECT p FROM ProductosVendedores p WHERE p.idlisitaproductosvendedores = :idlisitaproductosvendedores"),
    @NamedQuery(name = "ProductosVendedores.findByPrecioespecial", query = "SELECT p FROM ProductosVendedores p WHERE p.precioespecial = :precioespecial"),
    @NamedQuery(name = "ProductosVendedores.findByIdTipoVendedor", query = "SELECT p FROM ProductosVendedores p WHERE p.tipovendedoresidTipoVendedores = :tipovendedoresidTipoVendedores"),
    @NamedQuery(name = "ProductosVendedores.findByEliminado", query = "SELECT p FROM ProductosVendedores p WHERE p.eliminado = :eliminado")})
public class ProductosVendedores implements Serializable {
    @Basic(optional = false)
    @Column(name = "eliminado")
    private boolean eliminado;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idlisitaproductosvendedores")
    private Integer idlisitaproductosvendedores;
    @Basic(optional = false)
    @Column(name = "precioespecial")
    private float precioespecial;
    @JoinColumn(name = "productos_idProductos", referencedColumnName = "idProductos")
    @ManyToOne(optional = false)
    private Productos productosidProductos;
    @JoinColumn(name = "tipovendedores_idTipo_Vendedores", referencedColumnName = "idTipo_Vendedores")
    @ManyToOne(optional = false)
    private TipoVendedores tipovendedoresidTipoVendedores;

    public ProductosVendedores() {
    }

    public ProductosVendedores(Integer idlisitaproductosvendedores) {
        this.idlisitaproductosvendedores = idlisitaproductosvendedores;
    }

    public ProductosVendedores(Integer idlisitaproductosvendedores, float precioespecial, boolean eliminado) {
        this.idlisitaproductosvendedores = idlisitaproductosvendedores;
        this.precioespecial = precioespecial;
        this.eliminado = eliminado;
    }

    public Integer getIdlisitaproductosvendedores() {
        return idlisitaproductosvendedores;
    }

    public void setIdlisitaproductosvendedores(Integer idlisitaproductosvendedores) {
        this.idlisitaproductosvendedores = idlisitaproductosvendedores;
    }

    public float getPrecioespecial() {
        return precioespecial;
    }

    public void setPrecioespecial(float precioespecial) {
        this.precioespecial = precioespecial;
    }

    public Productos getProductosidProductos() {
        return productosidProductos;
    }

    public void setProductosidProductos(Productos productosidProductos) {
        this.productosidProductos = productosidProductos;
    }

    public TipoVendedores getTipovendedoresidTipoVendedores() {
        return tipovendedoresidTipoVendedores;
    }

    public void setTipovendedoresidTipoVendedores(TipoVendedores tipovendedoresidTipoVendedores) {
        this.tipovendedoresidTipoVendedores = tipovendedoresidTipoVendedores;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idlisitaproductosvendedores != null ? idlisitaproductosvendedores.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProductosVendedores)) {
            return false;
        }
        ProductosVendedores other = (ProductosVendedores) object;
        if ((this.idlisitaproductosvendedores == null && other.idlisitaproductosvendedores != null) || (this.idlisitaproductosvendedores != null && !this.idlisitaproductosvendedores.equals(other.idlisitaproductosvendedores))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades.ProductosVendedores[ idlisitaproductosvendedores=" + idlisitaproductosvendedores + " ]";
    }

    public boolean getEliminado() {
        return eliminado;
    }

    public void setEliminado(boolean eliminado) {
        this.eliminado = eliminado;
    }
    
}
