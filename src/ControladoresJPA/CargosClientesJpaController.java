/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ControladoresJPA;

import ControladoresJPA.exceptions.NonexistentEntityException;
import EntidadesJPA.CargosClientes;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import EntidadesJPA.Clientes;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Ch470
 */
public class CargosClientesJpaController implements Serializable {

    public CargosClientesJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(CargosClientes cargosClientes) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Clientes clientesidCliente = cargosClientes.getClientesidCliente();
            if (clientesidCliente != null) {
                clientesidCliente = em.getReference(clientesidCliente.getClass(), clientesidCliente.getIdCliente());
                cargosClientes.setClientesidCliente(clientesidCliente);
            }
            em.persist(cargosClientes);
            if (clientesidCliente != null) {
                clientesidCliente.getCargosclientesCollection().add(cargosClientes);
                clientesidCliente = em.merge(clientesidCliente);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(CargosClientes cargosClientes) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            CargosClientes persistentCargosClientes = em.find(CargosClientes.class, cargosClientes.getIdcargosclientes());
            Clientes clientesidClienteOld = persistentCargosClientes.getClientesidCliente();
            Clientes clientesidClienteNew = cargosClientes.getClientesidCliente();
            if (clientesidClienteNew != null) {
                clientesidClienteNew = em.getReference(clientesidClienteNew.getClass(), clientesidClienteNew.getIdCliente());
                cargosClientes.setClientesidCliente(clientesidClienteNew);
            }
            cargosClientes = em.merge(cargosClientes);
            if (clientesidClienteOld != null && !clientesidClienteOld.equals(clientesidClienteNew)) {
                clientesidClienteOld.getCargosclientesCollection().remove(cargosClientes);
                clientesidClienteOld = em.merge(clientesidClienteOld);
            }
            if (clientesidClienteNew != null && !clientesidClienteNew.equals(clientesidClienteOld)) {
                clientesidClienteNew.getCargosclientesCollection().add(cargosClientes);
                clientesidClienteNew = em.merge(clientesidClienteNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = cargosClientes.getIdcargosclientes();
                if (findCargosClientes(id) == null) {
                    throw new NonexistentEntityException("The cargosClientes with id " + id + " no longer exists.");
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
            CargosClientes cargosClientes;
            try {
                cargosClientes = em.getReference(CargosClientes.class, id);
                cargosClientes.getIdcargosclientes();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The cargosClientes with id " + id + " no longer exists.", enfe);
            }
            Clientes clientesidCliente = cargosClientes.getClientesidCliente();
            if (clientesidCliente != null) {
                clientesidCliente.getCargosclientesCollection().remove(cargosClientes);
                clientesidCliente = em.merge(clientesidCliente);
            }
            em.remove(cargosClientes);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<CargosClientes> findCargosClientesEntities() {
        return findCargosClientesEntities(true, -1, -1);
    }

    public List<CargosClientes> findCargosClientesEntities(int maxResults, int firstResult) {
        return findCargosClientesEntities(false, maxResults, firstResult);
    }

    private List<CargosClientes> findCargosClientesEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(CargosClientes.class));
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

    public CargosClientes findCargosClientes(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(CargosClientes.class, id);
        } finally {
            em.close();
        }
    }

    public int getCargosClientesCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<CargosClientes> rt = cq.from(CargosClientes.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
