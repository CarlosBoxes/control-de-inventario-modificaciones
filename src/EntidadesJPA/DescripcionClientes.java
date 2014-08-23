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
@Table(name = "descripcionclientes")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Descripcionclientes.findAll", query = "SELECT d FROM DescripcionClientes d"),
    @NamedQuery(name = "Descripcionclientes.findByIddescripcionclientes", query = "SELECT d FROM DescripcionClientes d WHERE d.iddescripcionclientes = :iddescripcionclientes"),
    @NamedQuery(name = "Descripcionclientes.findByCantidad", query = "SELECT d FROM DescripcionClientes d WHERE d.cantidad = :cantidad"),
    @NamedQuery(name = "Descripcionclientes.findByTotal", query = "SELECT d FROM DescripcionClientes d WHERE d.total = :total")})
public class DescripcionClientes implements Serializable {
    @Basic(optional = false)
    @Column(name = "precio")
    private float precio;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "iddescripcionclientes")
    private Integer iddescripcionclientes;
    @Basic(optional = false)
    @Column(name = "cantidad")
    private int cantidad;
    @Basic(optional = false)
    @Column(name = "total")
    private float total;
    @JoinColumn(name = "clientes_idCliente", referencedColumnName = "idCliente")
    @ManyToOne(optional = false)
    private Clientes clientesidCliente;
    @JoinColumn(name = "pedido_idpedido", referencedColumnName = "idpedido")
    @ManyToOne(optional = false)
    private Pedido pedidoIdpedido;
    @JoinColumn(name = "productos_idProductos", referencedColumnName = "idProductos")
    @ManyToOne(optional = false)
    private Productos productosidProductos;

    public DescripcionClientes() {
    }

    public DescripcionClientes(Integer iddescripcionclientes) {
        this.iddescripcionclientes = iddescripcionclientes;
    }

    public DescripcionClientes(Integer iddescripcionclientes, int cantidad, float total) {
        this.iddescripcionclientes = iddescripcionclientes;
        this.cantidad = cantidad;
        this.total = total;
    }

    public Integer getIddescripcionclientes() {
        return iddescripcionclientes;
    }

    public void setIddescripcionclientes(Integer iddescripcionclientes) {
        this.iddescripcionclientes = iddescripcionclientes;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public Clientes getClientesidCliente() {
        return clientesidCliente;
    }

    public void setClientesidCliente(Clientes clientesidCliente) {
        this.clientesidCliente = clientesidCliente;
    }

    public Pedido getPedidoIdpedido() {
        return pedidoIdpedido;
    }

    public void setPedidoIdpedido(Pedido pedidoIdpedido) {
        this.pedidoIdpedido = pedidoIdpedido;
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
        hash += (iddescripcionclientes != null ? iddescripcionclientes.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DescripcionClientes)) {
            return false;
        }
        DescripcionClientes other = (DescripcionClientes) object;
        if ((this.iddescripcionclientes == null && other.iddescripcionclientes != null) || (this.iddescripcionclientes != null && !this.iddescripcionclientes.equals(other.iddescripcionclientes))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades.DescripcionClientes[ iddescripcionclientes=" + iddescripcionclientes + " ]";
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }
    
}
