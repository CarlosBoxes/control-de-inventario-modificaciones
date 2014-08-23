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
import EntidadesJPA.Clientes;
import EntidadesJPA.DescripcionClientes;
import EntidadesJPA.Pedido;
import EntidadesJPA.Productos;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Ch470
 */
public class DescripcionClientesJpaController implements Serializable {

    public DescripcionClientesJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(DescripcionClientes descripcionClientes) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Clientes clientesidCliente = descripcionClientes.getClientesidCliente();
            if (clientesidCliente != null) {
                clientesidCliente = em.getReference(clientesidCliente.getClass(), clientesidCliente.getIdCliente());
                descripcionClientes.setClientesidCliente(clientesidCliente);
            }
            Pedido pedidoIdpedido = descripcionClientes.getPedidoIdpedido();
            if (pedidoIdpedido != null) {
                pedidoIdpedido = em.getReference(pedidoIdpedido.getClass(), pedidoIdpedido.getIdpedido());
                descripcionClientes.setPedidoIdpedido(pedidoIdpedido);
            }
            Productos productosidProductos = descripcionClientes.getProductosidProductos();
            if (productosidProductos != null) {
                productosidProductos = em.getReference(productosidProductos.getClass(), productosidProductos.getIdProductos());
                descripcionClientes.setProductosidProductos(productosidProductos);
            }
            em.persist(descripcionClientes);
            if (clientesidCliente != null) {
                clientesidCliente.getDescripcionclientesCollection().add(descripcionClientes);
                clientesidCliente = em.merge(clientesidCliente);
            }
            if (pedidoIdpedido != null) {
                pedidoIdpedido.getDescripcionClientesCollection().add(descripcionClientes);
                pedidoIdpedido = em.merge(pedidoIdpedido);
            }
            if (productosidProductos != null) {
                productosidProductos.getDescripcionClientesCollection().add(descripcionClientes);
                productosidProductos = em.merge(productosidProductos);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(DescripcionClientes descripcionClientes) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            DescripcionClientes persistentDescripcionClientes = em.find(DescripcionClientes.class, descripcionClientes.getIddescripcionclientes());
            Clientes clientesidClienteOld = persistentDescripcionClientes.getClientesidCliente();
            Clientes clientesidClienteNew = descripcionClientes.getClientesidCliente();
            Pedido pedidoIdpedidoOld = persistentDescripcionClientes.getPedidoIdpedido();
            Pedido pedidoIdpedidoNew = descripcionClientes.getPedidoIdpedido();
            Productos productosidProductosOld = persistentDescripcionClientes.getProductosidProductos();
            Productos productosidProductosNew = descripcionClientes.getProductosidProductos();
            if (clientesidClienteNew != null) {
                clientesidClienteNew = em.getReference(clientesidClienteNew.getClass(), clientesidClienteNew.getIdCliente());
                descripcionClientes.setClientesidCliente(clientesidClienteNew);
            }
            if (pedidoIdpedidoNew != null) {
                pedidoIdpedidoNew = em.getReference(pedidoIdpedidoNew.getClass(), pedidoIdpedidoNew.getIdpedido());
                descripcionClientes.setPedidoIdpedido(pedidoIdpedidoNew);
            }
            if (productosidProductosNew != null) {
                productosidProductosNew = em.getReference(productosidProductosNew.getClass(), productosidProductosNew.getIdProductos());
                descripcionClientes.setProductosidProductos(productosidProductosNew);
            }
            descripcionClientes = em.merge(descripcionClientes);
            if (clientesidClienteOld != null && !clientesidClienteOld.equals(clientesidClienteNew)) {
                clientesidClienteOld.getDescripcionclientesCollection().remove(descripcionClientes);
                clientesidClienteOld = em.merge(clientesidClienteOld);
            }
            if (clientesidClienteNew != null && !clientesidClienteNew.equals(clientesidClienteOld)) {
                clientesidClienteNew.getDescripcionclientesCollection().add(descripcionClientes);
                clientesidClienteNew = em.merge(clientesidClienteNew);
            }
            if (pedidoIdpedidoOld != null && !pedidoIdpedidoOld.equals(pedidoIdpedidoNew)) {
                pedidoIdpedidoOld.getDescripcionClientesCollection().remove(descripcionClientes);
                pedidoIdpedidoOld = em.merge(pedidoIdpedidoOld);
            }
            if (pedidoIdpedidoNew != null && !pedidoIdpedidoNew.equals(pedidoIdpedidoOld)) {
                pedidoIdpedidoNew.getDescripcionClientesCollection().add(descripcionClientes);
                pedidoIdpedidoNew = em.merge(pedidoIdpedidoNew);
            }
            if (productosidProductosOld != null && !productosidProductosOld.equals(productosidProductosNew)) {
                productosidProductosOld.getDescripcionClientesCollection().remove(descripcionClientes);
                productosidProductosOld = em.merge(productosidProductosOld);
            }
            if (productosidProductosNew != null && !productosidProductosNew.equals(productosidProductosOld)) {
                productosidProductosNew.getDescripcionClientesCollection().add(descripcionClientes);
                productosidProductosNew = em.merge(productosidProductosNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = descripcionClientes.getIddescripcionclientes();
                if (findDescripcionClientes(id) == null) {
                    throw new NonexistentEntityException("The descripcionClientes with id " + id + " no longer exists.");
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
            DescripcionClientes descripcionClientes;
            try {
                descripcionClientes = em.getReference(DescripcionClientes.class, id);
                descripcionClientes.getIddescripcionclientes();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The descripcionClientes with id " + id + " no longer exists.", enfe);
            }
            Clientes clientesidCliente = descripcionClientes.getClientesidCliente();
            if (clientesidCliente != null) {
                clientesidCliente.getDescripcionclientesCollection().remove(descripcionClientes);
                clientesidCliente = em.merge(clientesidCliente);
            }
            Pedido pedidoIdpedido = descripcionClientes.getPedidoIdpedido();
            if (pedidoIdpedido != null) {
                pedidoIdpedido.getDescripcionClientesCollection().remove(descripcionClientes);
                pedidoIdpedido = em.merge(pedidoIdpedido);
            }
            Productos productosidProductos = descripcionClientes.getProductosidProductos();
            if (productosidProductos != null) {
                productosidProductos.getDescripcionClientesCollection().remove(descripcionClientes);
                productosidProductos = em.merge(productosidProductos);
            }
            em.remove(descripcionClientes);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<DescripcionClientes> findDescripcionClientesEntities() {
        return findDescripcionClientesEntities(true, -1, -1);
    }

    public List<DescripcionClientes> findDescripcionClientesEntities(int maxResults, int firstResult) {
        return findDescripcionClientesEntities(false, maxResults, firstResult);
    }

    private List<DescripcionClientes> findDescripcionClientesEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(DescripcionClientes.class));
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

    public DescripcionClientes findDescripcionClientes(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(DescripcionClientes.class, id);
        } finally {
            em.close();
        }
    }

    public int getDescripcionClientesCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<DescripcionClientes> rt = cq.from(DescripcionClientes.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
