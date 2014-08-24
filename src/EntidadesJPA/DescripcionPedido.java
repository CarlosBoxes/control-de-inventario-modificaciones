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
@Table(name = "descripcionpedido")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Descripcionpedido.findAll", query = "SELECT d FROM DescripcionPedido d"),
    @NamedQuery(name = "Descripcionpedido.findByIddescripcionPedido", query = "SELECT d FROM DescripcionPedido d WHERE d.iddescripcionPedido = :iddescripcionPedido"),
    @NamedQuery(name = "Descripcionpedido.findByCantidad", query = "SELECT d FROM DescripcionPedido d WHERE d.cantidad = :cantidad"),
    @NamedQuery(name = "Descripcionpedido.findByDescripcion", query = "SELECT d FROM DescripcionPedido d WHERE d.pedidoIdpedido = :idpedido AND d.productosidProductos = :idproducto AND d.precio = :precioPro"),
    @NamedQuery(name = "Descripcionpedido.findByPedido", query = "SELECT d FROM DescripcionPedido d WHERE d.pedidoIdpedido = :idpedido"),
    @NamedQuery(name = "Descripcionpedido.findByFacturado", query = "SELECT d FROM DescripcionPedido d WHERE d.facturado = :facturado"),
    @NamedQuery(name = "Descripcionpedido.findBySubTotal", query = "SELECT d FROM DescripcionPedido d WHERE d.subTotal = :subTotal"),
    @NamedQuery(name = "Descripcionpedido.findByPrecio", query = "SELECT d FROM DescripcionPedido d WHERE d.precio = :precio")})
public class DescripcionPedido implements Serializable {
    @Basic(optional = false)
    @Column(name = "Cantidad")
    private float cantidad;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "iddescripcionPedido")
    private Integer iddescripcionPedido;
    @Basic(optional = false)
    @Column(name = "Facturado")
    private int facturado;
    @Basic(optional = false)
    @Column(name = "subTotal")
    private float subTotal;
    @Basic(optional = false)
    @Column(name = "precio")
    private float precio;
    @JoinColumn(name = "Productos_idProductos", referencedColumnName = "idProductos")
    @ManyToOne(optional = false)
    private Productos productosidProductos;
    @JoinColumn(name = "pedido_idpedido", referencedColumnName = "idpedido")
    @ManyToOne(optional = false)
    private Pedido pedidoIdpedido;

    public DescripcionPedido() {
    }

    public DescripcionPedido(Integer iddescripcionPedido) {
        this.iddescripcionPedido = iddescripcionPedido;
    }

    public DescripcionPedido(Integer iddescripcionPedido, int cantidad, int facturado, float subTotal, float precio) {
        this.iddescripcionPedido = iddescripcionPedido;
        this.cantidad = cantidad;
        this.facturado = facturado;
        this.subTotal = subTotal;
        this.precio = precio;
    }

    public Integer getIddescripcionPedido() {
        return iddescripcionPedido;
    }

    public void setIddescripcionPedido(Integer iddescripcionPedido) {
        this.iddescripcionPedido = iddescripcionPedido;
    }

    public int getFacturado() {
        return facturado;
    }

    public void setFacturado(int facturado) {
        this.facturado = facturado;
    }

    public float getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(float subTotal) {
        this.subTotal = subTotal;
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

    public Pedido getPedidoIdpedido() {
        return pedidoIdpedido;
    }

    public void setPedidoIdpedido(Pedido pedidoIdpedido) {
        this.pedidoIdpedido = pedidoIdpedido;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iddescripcionPedido != null ? iddescripcionPedido.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DescripcionPedido)) {
            return false;
        }
        DescripcionPedido other = (DescripcionPedido) object;
        if ((this.iddescripcionPedido == null && other.iddescripcionPedido != null) || (this.iddescripcionPedido != null && !this.iddescripcionPedido.equals(other.iddescripcionPedido))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "EntidadesJPA.Descripcionpedido[ iddescripcionPedido=" + iddescripcionPedido + " ]";
    }

    public float getCantidad() {
        return cantidad;
    }

    public void setCantidad(float cantidad) {
        this.cantidad = cantidad;
    }
    
}
