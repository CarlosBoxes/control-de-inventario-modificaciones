/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package EntidadesJPA;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Luis
 */
@Entity
@Table(name = "facturas")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Facturas.findAll", query = "SELECT f FROM Facturas f"),
    @NamedQuery(name = "Facturas.findByIdFacturas", query = "SELECT f FROM Facturas f WHERE f.idFacturas = :idFacturas"),
    @NamedQuery(name = "Facturas.findBySerie", query = "SELECT f FROM Facturas f WHERE f.serie = :serie"),
    @NamedQuery(name = "Facturas.findByNumero", query = "SELECT f FROM Facturas f WHERE f.numero = :numero"),
    @NamedQuery(name = "Facturas.findByTotal", query = "SELECT f FROM Facturas f WHERE f.total = :total")})
public class Facturas implements Serializable {
    @JoinColumn(name = "Pedido_idPedido", referencedColumnName = "idpedido")
    @ManyToOne(optional = false)
    private Pedido pedidoidPedido;
    @Basic(optional = false)
    @Column(name = "anulada")
    private boolean anulada;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "facturasidFacturas")
    private Collection<DescripcionFactura> descripcionfacturaCollection;
    @Basic(optional = false)
    @Column(name = "cantidadletras")
    private String cantidadletras;
    @JoinColumn(name = "Vendedor_idVendedor", referencedColumnName = "idvendedores")
    @ManyToOne(optional = false)
    private Vendedores vendedoridVendedor;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idFacturas")
    private Integer idFacturas;
    @Basic(optional = false)
    @Column(name = "serie")
    private String serie;
    @Basic(optional = false)
    @Column(name = "numero")
    private int numero;
    @Basic(optional = false)
    @Column(name = "total")
    private float total;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "facturasidFacturas")
    private Collection<DescripcionFactura> descripcionFacturaCollection;
    @JoinColumn(name = "Clientes_idCliente", referencedColumnName = "idCliente")
    @ManyToOne(optional = false)
    private Clientes clientesidCliente;

    public Facturas() {
    }

    public Facturas(Integer idFacturas) {
        this.idFacturas = idFacturas;
    }

    public Facturas(Integer idFacturas, String serie, int numero, float total) {
        this.idFacturas = idFacturas;
        this.serie = serie;
        this.numero = numero;
        this.total = total;
    }

    public Integer getIdFacturas() {
        return idFacturas;
    }

    public void setIdFacturas(Integer idFacturas) {
        this.idFacturas = idFacturas;
    }

    public String getSerie() {
        return serie;
    }

    public void setSerie(String serie) {
        this.serie = serie;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    @XmlTransient
    public Collection<DescripcionFactura> getDescripcionFacturaCollection() {
        return descripcionFacturaCollection;
    }

    public void setDescripcionFacturaCollection(Collection<DescripcionFactura> descripcionFacturaCollection) {
        this.descripcionFacturaCollection = descripcionFacturaCollection;
    }

    public Clientes getClientesidCliente() {
        return clientesidCliente;
    }

    public void setClientesidCliente(Clientes clientesidCliente) {
        this.clientesidCliente = clientesidCliente;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idFacturas != null ? idFacturas.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Facturas)) {
            return false;
        }
        Facturas other = (Facturas) object;
        if ((this.idFacturas == null && other.idFacturas != null) || (this.idFacturas != null && !this.idFacturas.equals(other.idFacturas))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades.Facturas[ idFacturas=" + idFacturas + " ]";
    }

    public String getCantidadletras() {
        return cantidadletras;
    }

    public void setCantidadletras(String cantidadletras) {
        this.cantidadletras = cantidadletras;
    }

    public Vendedores getVendedoridVendedor() {
        return vendedoridVendedor;
    }

    public void setVendedoridVendedor(Vendedores vendedoridVendedor) {
        this.vendedoridVendedor = vendedoridVendedor;
    }

    public boolean getAnulada() {
        return anulada;
    }

    public void setAnulada(boolean anulada) {
        this.anulada = anulada;
    }    

    public Pedido getPedidoidPedido() {
        return pedidoidPedido;
    }

    public void setPedidoidPedido(Pedido pedidoidPedido) {
        this.pedidoidPedido = pedidoidPedido;
    }
}
