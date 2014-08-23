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
@Table(name = "listaclientes")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ListaClientes.findAll", query = "SELECT l FROM ListaClientes l"),
    @NamedQuery(name = "ListaClientes.findByIdlistaclientes", query = "SELECT l FROM ListaClientes l WHERE l.idlistaclientes = :idlistaclientes")})
public class ListaClientes implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idlistaclientes")
    private Integer idlistaclientes;
    @JoinColumn(name = "vendedores_idvendedores", referencedColumnName = "idvendedores")
    @ManyToOne(optional = false)
    private Vendedores vendedoresIdvendedores;
    @JoinColumn(name = "clientes_idCliente", referencedColumnName = "idCliente")
    @ManyToOne(optional = false)
    private Clientes clientesidCliente;

    public ListaClientes() {
    }

    public ListaClientes(Integer idlistaclientes) {
        this.idlistaclientes = idlistaclientes;
    }

    public Integer getIdlistaclientes() {
        return idlistaclientes;
    }

    public void setIdlistaclientes(Integer idlistaclientes) {
        this.idlistaclientes = idlistaclientes;
    }

    public Vendedores getVendedoresIdvendedores() {
        return vendedoresIdvendedores;
    }

    public void setVendedoresIdvendedores(Vendedores vendedoresIdvendedores) {
        this.vendedoresIdvendedores = vendedoresIdvendedores;
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
        hash += (idlistaclientes != null ? idlistaclientes.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ListaClientes)) {
            return false;
        }
        ListaClientes other = (ListaClientes) object;
        if ((this.idlistaclientes == null && other.idlistaclientes != null) || (this.idlistaclientes != null && !this.idlistaclientes.equals(other.idlistaclientes))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades.ListaClientes[ idlistaclientes=" + idlistaclientes + " ]";
    }
    
}
