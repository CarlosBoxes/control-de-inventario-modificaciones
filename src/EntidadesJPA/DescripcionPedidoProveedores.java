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
@Table(name = "descripcion pedido proveedores")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DescripcionPedidoProveedores.findAll", query = "SELECT d FROM DescripcionPedidoProveedores d"),
    @NamedQuery(name = "DescripcionPedidoProveedores.findByIddescripcionP", query = "SELECT d FROM DescripcionPedidoProveedores d WHERE d.iddescripcionP = :iddescripcionP"),
    @NamedQuery(name = "DescripcionPedidoProveedores.findByCantidad", query = "SELECT d FROM DescripcionPedidoProveedores d WHERE d.cantidad = :cantidad"),
    @NamedQuery(name = "DescripcionPedidoProveedores.findByPrecioProducto", query = "SELECT d FROM DescripcionPedidoProveedores d WHERE d.precioProducto = :precioProducto")})
public class DescripcionPedidoProveedores implements Serializable {
    @Basic(optional = false)
    @Column(name = "cantidad")
    private float cantidad;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "iddescripcionP")
    private Integer iddescripcionP;
    @Basic(optional = false)
    @Column(name = "precioProducto")
    private float precioProducto;
    @JoinColumn(name = "Productos_idProductos", referencedColumnName = "idProductos")
    @ManyToOne(optional = false)
    private Productos productosidProductos;
    @JoinColumn(name = "pedido_proveedores_idpedido_proveedores", referencedColumnName = "idpedido_proveedores")
    @ManyToOne(optional = false)
    private PedidoProveedores pedidoProveedoresIdpedidoProveedores;

    public DescripcionPedidoProveedores() {
    }

    public DescripcionPedidoProveedores(Integer iddescripcionP) {
        this.iddescripcionP = iddescripcionP;
    }

    public DescripcionPedidoProveedores(Integer iddescripcionP, int cantidad, float precioProducto) {
        this.iddescripcionP = iddescripcionP;
        this.cantidad = cantidad;
        this.precioProducto = precioProducto;
    }

    public Integer getIddescripcionP() {
        return iddescripcionP;
    }

    public void setIddescripcionP(Integer iddescripcionP) {
        this.iddescripcionP = iddescripcionP;
    }

    public float getPrecioProducto() {
        return precioProducto;
    }

    public void setPrecioProducto(float precioProducto) {
        this.precioProducto = precioProducto;
    }

    public Productos getProductosidProductos() {
        return productosidProductos;
    }

    public void setProductosidProductos(Productos productosidProductos) {
        this.productosidProductos = productosidProductos;
    }

    public PedidoProveedores getPedidoProveedoresIdpedidoProveedores() {
        return pedidoProveedoresIdpedidoProveedores;
    }

    public void setPedidoProveedoresIdpedidoProveedores(PedidoProveedores pedidoProveedoresIdpedidoProveedores) {
        this.pedidoProveedoresIdpedidoProveedores = pedidoProveedoresIdpedidoProveedores;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iddescripcionP != null ? iddescripcionP.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DescripcionPedidoProveedores)) {
            return false;
        }
        DescripcionPedidoProveedores other = (DescripcionPedidoProveedores) object;
        if ((this.iddescripcionP == null && other.iddescripcionP != null) || (this.iddescripcionP != null && !this.iddescripcionP.equals(other.iddescripcionP))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades.DescripcionPedidoProveedores[ iddescripcionP=" + iddescripcionP + " ]";
    }

    public float getCantidad() {
        return cantidad;
    }

    public void setCantidad(float cantidad) {
        this.cantidad = cantidad;
    }
    
}
