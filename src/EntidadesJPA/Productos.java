/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package EntidadesJPA;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Luis
 */
@Entity
@Table(name = "productos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Productos.findAll", query = "SELECT p FROM Productos p"),
    @NamedQuery(name = "Productos.findByIdProductos", query = "SELECT p FROM Productos p WHERE p.idProductos = :idProductos"),
    @NamedQuery(name = "Productos.findByNombre", query = "SELECT p FROM Productos p WHERE p.nombre = :nombre AND p.eliminado = false"),
    @NamedQuery(name = "Productos.findByExistente", query = "SELECT p FROM Productos p WHERE p.nombre = :nombre AND p.presentacion = :presentacion AND p.eliminado = false"),
    @NamedQuery(name = "Productos.findByNombreLike", query = "SELECT p FROM Productos p WHERE p.nombre LIKE :nombre AND p.eliminado = false"),
    @NamedQuery(name = "Productos.findByListaNombre", query = "SELECT p FROM Productos p WHERE p.eliminado = false"),
    @NamedQuery(name = "Productos.findByPresentacion", query = "SELECT p FROM Productos p WHERE p.presentacion = :presentacion"),
    @NamedQuery(name = "Productos.findByUnidadDeMedida", query = "SELECT p FROM Productos p WHERE p.unidadDeMedida = :unidadDeMedida"),
    @NamedQuery(name = "Productos.findByPrecioCosto", query = "SELECT p FROM Productos p WHERE p.precioCosto = :precioCosto"),
    @NamedQuery(name = "Productos.findByPrecioVenta", query = "SELECT p FROM Productos p WHERE p.precioVenta = :precioVenta"),
    @NamedQuery(name = "Productos.findByFechaDeVencimiento", query = "SELECT p FROM Productos p WHERE p.fechaDeVencimiento < :fechaDeVencimiento"),
    @NamedQuery(name = "Productos.findByDescripcion", query = "SELECT p FROM Productos p WHERE p.descripcion = :descripcion"),
    @NamedQuery(name = "Productos.findByCategoria", query = "SELECT p FROM Productos p WHERE p.categoria = :categoria"),
    @NamedQuery(name = "Productos.findByEliminado", query = "SELECT p FROM Productos p WHERE p.eliminado = :eliminado"),
    @NamedQuery(name = "Productos.findByCambio", query = "SELECT p FROM Productos p WHERE p.cambio = :cambio"),
    @NamedQuery(name = "Productos.findByDevoluciones", query = "SELECT p FROM Productos p WHERE p.devoluciones = :devoluciones")})
public class Productos implements Serializable {
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "productosidProductos")
    private Collection<Produccion> produccionCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "productosidProductos")
    //private Collection<Traslado> trasladoCollection;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idProductos")
    private Integer idProductos;
    @Basic(optional = false)
    @Column(name = "nombre")
    private String nombre;
    @Basic(optional = false)
    @Column(name = "presentacion")
    private String presentacion;
    @Basic(optional = false)
    @Column(name = "unidad_de_medida")
    private int unidadDeMedida;
    @Basic(optional = false)
    @Column(name = "precio_costo")
    private float precioCosto;
    @Basic(optional = false)
    @Column(name = "precio_venta")
    private float precioVenta;
    @Column(name = "fecha_de_vencimiento")
    @Temporal(TemporalType.DATE)
    private Date fechaDeVencimiento;
    @Column(name = "descripcion")
    private String descripcion;
    @Column(name = "categoria")
    private String categoria;
    @Basic(optional = false)
    @Column(name = "eliminado")
    private boolean eliminado;
    @Basic(optional = false)
    @Column(name = "cambio")
    private boolean cambio;
    @Basic(optional = false)
    @Column(name = "devoluciones")
    private float devoluciones;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "productosidProductos")
    private Collection<DescripcionPedido> descripcionPedidoCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "productosidProductos")
    private Collection<InventarioProducto> inventarioProductoCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "productosidProductos")
    private Collection<DescripcionFactura> descripcionFacturaCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "productosidProductos")
    private Collection<DescripcionClientes> descripcionClientesCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "productosidProductos")
    private Collection<ProductosVencidos> productosVencidosCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "productosidProductos")
    private Collection<DescripcionPedidoProveedores> descripcionPedidoProveedoresCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "productosidProductos")
    private Collection<ProductosVendedores> productosVendedoresCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "productosidProductos")
    private Collection<ProductosClientes> productosClientesCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "productosidProductos")
    private Collection<ProductosDefectuoso> productosDefectuosoCollection;

    public Productos() {
    }

    public Productos(Integer idProductos) {
        this.idProductos = idProductos;
    }

    public Productos(Integer idProductos, String nombre, String presentacion, int unidadDeMedida, float precioCosto, float precioVenta, boolean eliminado, boolean cambio, float devoluciones) {
        this.idProductos = idProductos;
        this.nombre = nombre;
        this.presentacion = presentacion;
        this.unidadDeMedida = unidadDeMedida;
        this.precioCosto = precioCosto;
        this.precioVenta = precioVenta;
        this.eliminado = eliminado;
        this.cambio = cambio;
        this.devoluciones = devoluciones;
    }

    public Integer getIdProductos() {
        return idProductos;
    }

    public void setIdProductos(Integer idProductos) {
        this.idProductos = idProductos;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPresentacion() {
        return presentacion;
    }

    public void setPresentacion(String presentacion) {
        this.presentacion = presentacion;
    }

    public int getUnidadDeMedida() {
        return unidadDeMedida;
    }

    public void setUnidadDeMedida(int unidadDeMedida) {
        this.unidadDeMedida = unidadDeMedida;
    }

    public float getPrecioCosto() {
        return precioCosto;
    }

    public void setPrecioCosto(float precioCosto) {
        this.precioCosto = precioCosto;
    }

    public float getPrecioVenta() {
        return precioVenta;
    }

    public void setPrecioVenta(float precioVenta) {
        this.precioVenta = precioVenta;
    }

    public Date getFechaDeVencimiento() {
        return fechaDeVencimiento;
    }

    public void setFechaDeVencimiento(Date fechaDeVencimiento) {
        this.fechaDeVencimiento = fechaDeVencimiento;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public boolean getEliminado() {
        return eliminado;
    }

    public void setEliminado(boolean eliminado) {
        this.eliminado = eliminado;
    }

    public boolean getCambio() {
        return cambio;
    }

    public void setCambio(boolean cambio) {
        this.cambio = cambio;
    }

    public float getDevoluciones() {
        return devoluciones;
    }

    public void setDevoluciones(float devoluciones) {
        this.devoluciones = devoluciones;
    }

    @XmlTransient
    public Collection<DescripcionPedido> getDescripcionPedidoCollection() {
        return descripcionPedidoCollection;
    }

    public void setDescripcionPedidoCollection(Collection<DescripcionPedido> descripcionPedidoCollection) {
        this.descripcionPedidoCollection = descripcionPedidoCollection;
    }

    @XmlTransient
    public Collection<InventarioProducto> getInventarioProductoCollection() {
        return inventarioProductoCollection;
    }

    public void setInventarioProductoCollection(Collection<InventarioProducto> inventarioProductoCollection) {
        this.inventarioProductoCollection = inventarioProductoCollection;
    }

    @XmlTransient
    public Collection<DescripcionFactura> getDescripcionFacturaCollection() {
        return descripcionFacturaCollection;
    }

    public void setDescripcionFacturaCollection(Collection<DescripcionFactura> descripcionFacturaCollection) {
        this.descripcionFacturaCollection = descripcionFacturaCollection;
    }

    @XmlTransient
    public Collection<DescripcionClientes> getDescripcionClientesCollection() {
        return descripcionClientesCollection;
    }

    public void setDescripcionClientesCollection(Collection<DescripcionClientes> descripcionClientesCollection) {
        this.descripcionClientesCollection = descripcionClientesCollection;
    }

    @XmlTransient
    public Collection<ProductosVencidos> getProductosVencidosCollection() {
        return productosVencidosCollection;
    }

    public void setProductosVencidosCollection(Collection<ProductosVencidos> productosVencidosCollection) {
        this.productosVencidosCollection = productosVencidosCollection;
    }

    @XmlTransient
    public Collection<DescripcionPedidoProveedores> getDescripcionPedidoProveedoresCollection() {
        return descripcionPedidoProveedoresCollection;
    }

    public void setDescripcionPedidoProveedoresCollection(Collection<DescripcionPedidoProveedores> descripcionPedidoProveedoresCollection) {
        this.descripcionPedidoProveedoresCollection = descripcionPedidoProveedoresCollection;
    }

    @XmlTransient
    public Collection<ProductosVendedores> getProductosVendedoresCollection() {
        return productosVendedoresCollection;
    }

    public void setProductosVendedoresCollection(Collection<ProductosVendedores> productosVendedoresCollection) {
        this.productosVendedoresCollection = productosVendedoresCollection;
    }

    @XmlTransient
    public Collection<ProductosClientes> getProductosClientesCollection() {
        return productosClientesCollection;
    }

    public void setProductosClientesCollection(Collection<ProductosClientes> productosClientesCollection) {
        this.productosClientesCollection = productosClientesCollection;
    }

    @XmlTransient
    public Collection<ProductosDefectuoso> getProductosDefectuosoCollection() {
        return productosDefectuosoCollection;
    }

    public void setProductosDefectuosoCollection(Collection<ProductosDefectuoso> productosDefectuosoCollection) {
        this.productosDefectuosoCollection = productosDefectuosoCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idProductos != null ? idProductos.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Productos)) {
            return false;
        }
        Productos other = (Productos) object;
        if ((this.idProductos == null && other.idProductos != null) || (this.idProductos != null && !this.idProductos.equals(other.idProductos))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "EntidadesJPA.Productos[ idProductos=" + idProductos + " ]";
    }

    //@XmlTransient
    //public Collection<Traslado> getTrasladoCollection() {
      //  return trasladoCollection;
    //}

    //public void setTrasladoCollection(Collection<Traslado> trasladoCollection) {
     //   this.trasladoCollection = trasladoCollection;
    //}

    @XmlTransient
    public Collection<Produccion> getProduccionCollection() {
        return produccionCollection;
    }

    public void setProduccionCollection(Collection<Produccion> produccionCollection) {
        this.produccionCollection = produccionCollection;
    }
    
}
