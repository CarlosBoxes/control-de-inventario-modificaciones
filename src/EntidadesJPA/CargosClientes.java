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
 * @author Luis
 */
@Entity
@Table(name = "cargosclientes")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Cargosclientes.findAll", query = "SELECT c FROM CargosClientes c"),
    @NamedQuery(name = "Cargosclientes.findByIdcargosclientes", query = "SELECT c FROM CargosClientes c WHERE c.idcargosclientes = :idcargosclientes"),
    @NamedQuery(name = "Cargosclientes.findByFecha", query = "SELECT c FROM CargosClientes c WHERE c.fecha = :fecha"),
    @NamedQuery(name = "Cargosclientes.findByTotal", query = "SELECT c FROM CargosClientes c WHERE c.total = :total")})
public class CargosClientes implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "Idcargosclientes")
    private Integer idcargosclientes;
    @Basic(optional = false)
    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @Basic(optional = false)
    @Column(name = "Total")
    private float total;
    @JoinColumn(name = "Clientes_idCliente", referencedColumnName = "idCliente")
    @ManyToOne(optional = false)
    private Clientes clientesidCliente;

    public CargosClientes() {
    }

    public CargosClientes(Integer idcargosclientes) {
        this.idcargosclientes = idcargosclientes;
    }

    public CargosClientes(Integer idcargosclientes, Date fecha, float total) {
        this.idcargosclientes = idcargosclientes;
        this.fecha = fecha;
        this.total = total;
    }

    public Integer getIdcargosclientes() {
        return idcargosclientes;
    }

    public void setIdcargosclientes(Integer idcargosclientes) {
        this.idcargosclientes = idcargosclientes;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idcargosclientes != null ? idcargosclientes.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CargosClientes)) {
            return false;
        }
        CargosClientes other = (CargosClientes) object;
        if ((this.idcargosclientes == null && other.idcargosclientes != null) || (this.idcargosclientes != null && !this.idcargosclientes.equals(other.idcargosclientes))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades.CargosClientes[ idcargosclientes=" + idcargosclientes + " ]";
    }
    
}
