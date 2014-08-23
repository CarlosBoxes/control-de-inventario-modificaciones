/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ControladoresJPA;

import ControladoresJPA.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import EntidadesJPA.Vendedores;
import EntidadesJPA.Clientes;
import EntidadesJPA.ListaClientes;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Ch470
 */
public class ListaClientesJpaController implements Serializable {

    public ListaClientesJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(ListaClientes listaClientes) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Vendedores vendedoresIdvendedores = listaClientes.getVendedoresIdvendedores();
            if (vendedoresIdvendedores != null) {
                vendedoresIdvendedores = em.getReference(vendedoresIdvendedores.getClass(), vendedoresIdvendedores.getIdvendedores());
                listaClientes.setVendedoresIdvendedores(vendedoresIdvendedores);
            }
            Clientes clientesidCliente = listaClientes.getClientesidCliente();
            if (clientesidCliente != null) {
                clientesidCliente = em.getReference(clientesidCliente.getClass(), clientesidCliente.getIdCliente());
                listaClientes.setClientesidCliente(clientesidCliente);
            }
            em.persist(listaClientes);
            if (vendedoresIdvendedores != null) {
                vendedoresIdvendedores.getListaclientesCollection().add(listaClientes);
                vendedoresIdvendedores = em.merge(vendedoresIdvendedores);
            }
            if (clientesidCliente != null) {
                clientesidCliente.getListaclientesCollection().add(listaClientes);
                clientesidCliente = em.merge(clientesidCliente);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(ListaClientes listaClientes) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ListaClientes persistentListaClientes = em.find(ListaClientes.class, listaClientes.getIdlistaclientes());
            Vendedores vendedoresIdvendedoresOld = persistentListaClientes.getVendedoresIdvendedores();
            Vendedores vendedoresIdvendedoresNew = listaClientes.getVendedoresIdvendedores();
            Clientes clientesidClienteOld = persistentListaClientes.getClientesidCliente();
            Clientes clientesidClienteNew = listaClientes.getClientesidCliente();
            if (vendedoresIdvendedoresNew != null) {
                vendedoresIdvendedoresNew = em.getReference(vendedoresIdvendedoresNew.getClass(), vendedoresIdvendedoresNew.getIdvendedores());
                listaClientes.setVendedoresIdvendedores(vendedoresIdvendedoresNew);
            }
            if (clientesidClienteNew != null) {
                clientesidClienteNew = em.getReference(clientesidClienteNew.getClass(), clientesidClienteNew.getIdCliente());
                listaClientes.setClientesidCliente(clientesidClienteNew);
            }
            listaClientes = em.merge(listaClientes);
            if (vendedoresIdvendedoresOld != null && !vendedoresIdvendedoresOld.equals(vendedoresIdvendedoresNew)) {
                vendedoresIdvendedoresOld.getListaclientesCollection().remove(listaClientes);
                vendedoresIdvendedoresOld = em.merge(vendedoresIdvendedoresOld);
            }
            if (vendedoresIdvendedoresNew != null && !vendedoresIdvendedoresNew.equals(vendedoresIdvendedoresOld)) {
                vendedoresIdvendedoresNew.getListaclientesCollection().add(listaClientes);
                vendedoresIdvendedoresNew = em.merge(vendedoresIdvendedoresNew);
            }
            if (clientesidClienteOld != null && !clientesidClienteOld.equals(clientesidClienteNew)) {
                clientesidClienteOld.getListaclientesCollection().remove(listaClientes);
                clientesidClienteOld = em.merge(clientesidClienteOld);
            }
            if (clientesidClienteNew != null && !clientesidClienteNew.equals(clientesidClienteOld)) {
                clientesidClienteNew.getListaclientesCollection().add(listaClientes);
                clientesidClienteNew = em.merge(clientesidClienteNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = listaClientes.getIdlistaclientes();
                if (findListaClientes(id) == null) {
                    throw new NonexistentEntityException("The listaClientes with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ListaClientes listaClientes;
            try {
                listaClientes = em.getReference(ListaClientes.class, id);
                listaClientes.getIdlistaclientes();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The listaClientes with id " + id + " no longer exists.", enfe);
            }
            Vendedores vendedoresIdvendedores = listaClientes.getVendedoresIdvendedores();
            if (vendedoresIdvendedores != null) {
                vendedoresIdvendedores.getListaclientesCollection().remove(listaClientes);
                vendedoresIdvendedores = em.merge(vendedoresIdvendedores);
            }
            Clientes clientesidCliente = listaClientes.getClientesidCliente();
            if (clientesidCliente != null) {
                clientesidCliente.getListaclientesCollection().remove(listaClientes);
                clientesidCliente = em.merge(clientesidCliente);
            }
            em.remove(listaClientes);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<ListaClientes> findListaClientesEntities() {
        return findListaClientesEntities(true, -1, -1);
    }

    public List<ListaClientes> findListaClientesEntities(int maxResults, int firstResult) {
        return findListaClientesEntities(false, maxResults, firstResult);
    }

    private List<ListaClientes> findListaClientesEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(ListaClientes.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public ListaClientes findListaClientes(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(ListaClientes.class, id);
        } finally {
            em.close();
        }
    }

    public int getListaClientesCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<ListaClientes> rt = cq.from(ListaClientes.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
