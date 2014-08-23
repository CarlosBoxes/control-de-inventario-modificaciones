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
@Table(name = "productosclientes")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ProductosClientes.findAll", query = "SELECT p FROM ProductosClientes p"),
    @NamedQuery(name = "ProductosClientes.findByIdproductosespecialclientes", query = "SELECT p FROM ProductosClientes p WHERE p.idproductosespecialclientes = :idproductosespecialclientes"),
    @NamedQuery(name = "ProductosClientes.findByPrecio", query = "SELECT p FROM ProductosClientes p WHERE p.precio = :precio"),
    @NamedQuery(name = "ProductosClientes.findByEliminado", query = "SELECT p FROM ProductosClientes p WHERE p.eliminado = :eliminado")})
public class ProductosClientes implements Serializable {
    @Basic(optional = false)
    @Column(name = "eliminado")
    private boolean eliminado;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idproductosespecialclientes")
    private Integer idproductosespecialclientes;
    @Basic(optional = false)
    @Column(name = "precio")
    private float precio;
    @JoinColumn(name = "productos_idProductos", referencedColumnName = "idProductos")
    @ManyToOne(optional = false)
    private Productos productosidProductos;
    @JoinColumn(name = "tipoclientes_idTipo_Clientes", referencedColumnName = "idTipo_Clientes")
    @ManyToOne(optional = false)
    private TipoClientes tipoclientesidTipoClientes;

    public ProductosClientes() {
    }

    public ProductosClientes(Integer idproductosespecialclientes) {
        this.idproductosespecialclientes = idproductosespecialclientes;
    }

    public ProductosClientes(Integer idproductosespecialclientes, float precio, boolean eliminado) {
        this.idproductosespecialclientes = idproductosespecialclientes;
        this.precio = precio;
        this.eliminado = eliminado;
    }

    public Integer getIdproductosespecialclientes() {
        return idproductosespecialclientes;
    }

    public void setIdproductosespecialclientes(Integer idproductosespecialclientes) {
        this.idproductosespecialclientes = idproductosespecialclientes;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public Productos getProductosidProductos() {
        return productosidProductos;
    }

    public void setProductosidProductos(Productos productosidProductos) {
        this.productosidProductos = productosidProductos;
    }

    public TipoClientes getTipoclientesidTipoClientes() {
        return tipoclientesidTipoClientes;
    }

    public void setTipoclientesidTipoClientes(TipoClientes tipoclientesidTipoClientes) {
        this.tipoclientesidTipoClientes = tipoclientesidTipoClientes;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idproductosespecialclientes != null ? idproductosespecialclientes.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProductosClientes)) {
            return false;
        }
        ProductosClientes other = (ProductosClientes) object;
        if ((this.idproductosespecialclientes == null && other.idproductosespecialclientes != null) || (this.idproductosespecialclientes != null && !this.idproductosespecialclientes.equals(other.idproductosespecialclientes))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades.ProductosClientes[ idproductosespecialclientes=" + idproductosespecialclientes + " ]";
    }

    public boolean getEliminado() {
        return eliminado;
    }

    public void setEliminado(boolean eliminado) {
        this.eliminado = eliminado;
    }
    
}
