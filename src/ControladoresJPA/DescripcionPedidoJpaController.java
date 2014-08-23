/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ControladoresJPA;

import ControladoresJPA.exceptions.NonexistentEntityException;
import EntidadesJPA.DescripcionPedido;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import EntidadesJPA.Productos;
import EntidadesJPA.Pedido;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Ch470
 */
public class DescripcionPedidoJpaController implements Serializable {

    public DescripcionPedidoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(DescripcionPedido descripcionPedido) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Productos productosidProductos = descripcionPedido.getProductosidProductos();
            if (productosidProductos != null) {
                productosidProductos = em.getReference(productosidProductos.getClass(), productosidProductos.getIdProductos());
                descripcionPedido.setProductosidProductos(productosidProductos);
            }
            Pedido pedidoIdpedido = descripcionPedido.getPedidoIdpedido();
            if (pedidoIdpedido != null) {
                pedidoIdpedido = em.getReference(pedidoIdpedido.getClass(), pedidoIdpedido.getIdpedido());
                descripcionPedido.setPedidoIdpedido(pedidoIdpedido);
            }
            em.persist(descripcionPedido);
            if (productosidProductos != null) {
                productosidProductos.getDescripcionPedidoCollection().add(descripcionPedido);
                productosidProductos = em.merge(productosidProductos);
            }
            if (pedidoIdpedido != null) {
                pedidoIdpedido.getDescripcionPedidoCollection().add(descripcionPedido);
                pedidoIdpedido = em.merge(pedidoIdpedido);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(DescripcionPedido descripcionPedido) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            DescripcionPedido persistentDescripcionPedido = em.find(DescripcionPedido.class, descripcionPedido.getIddescripcionPedido());
            Productos productosidProductosOld = persistentDescripcionPedido.getProductosidProductos();
            Productos productosidProductosNew = descripcionPedido.getProductosidProductos();
            Pedido pedidoIdpedidoOld = persistentDescripcionPedido.getPedidoIdpedido();
            Pedido pedidoIdpedidoNew = descripcionPedido.getPedidoIdpedido();
            if (productosidProductosNew != null) {
                productosidProductosNew = em.getReference(productosidProductosNew.getClass(), productosidProductosNew.getIdProductos());
                descripcionPedido.setProductosidProductos(productosidProductosNew);
            }
            if (pedidoIdpedidoNew != null) {
                pedidoIdpedidoNew = em.getReference(pedidoIdpedidoNew.getClass(), pedidoIdpedidoNew.getIdpedido());
                descripcionPedido.setPedidoIdpedido(pedidoIdpedidoNew);
            }
            descripcionPedido = em.merge(descripcionPedido);
            if (productosidProductosOld != null && !productosidProductosOld.equals(productosidProductosNew)) {
                productosidProductosOld.getDescripcionPedidoCollection().remove(descripcionPedido);
                productosidProductosOld = em.merge(productosidProductosOld);
            }
            if (productosidProductosNew != null && !productosidProductosNew.equals(productosidProductosOld)) {
                productosidProductosNew.getDescripcionPedidoCollection().add(descripcionPedido);
                productosidProductosNew = em.merge(productosidProductosNew);
            }
            if (pedidoIdpedidoOld != null && !pedidoIdpedidoOld.equals(pedidoIdpedidoNew)) {
                pedidoIdpedidoOld.getDescripcionPedidoCollection().remove(descripcionPedido);
                pedidoIdpedidoOld = em.merge(pedidoIdpedidoOld);
            }
            if (pedidoIdpedidoNew != null && !pedidoIdpedidoNew.equals(pedidoIdpedidoOld)) {
                pedidoIdpedidoNew.getDescripcionPedidoCollection().add(descripcionPedido);
                pedidoIdpedidoNew = em.merge(pedidoIdpedidoNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = descripcionPedido.getIddescripcionPedido();
                if (findDescripcionPedido(id) == null) {
                    throw new NonexistentEntityException("The descripcionPedido with id " + id + " no longer exists.");
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
            DescripcionPedido descripcionPedido;
            try {
                descripcionPedido = em.getReference(DescripcionPedido.class, id);
                descripcionPedido.getIddescripcionPedido();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The descripcionPedido with id " + id + " no longer exists.", enfe);
            }
            Productos productosidProductos = descripcionPedido.getProductosidProductos();
            if (productosidProductos != null) {
                productosidProductos.getDescripcionPedidoCollection().remove(descripcionPedido);
                productosidProductos = em.merge(productosidProductos);
            }
            Pedido pedidoIdpedido = descripcionPedido.getPedidoIdpedido();
            if (pedidoIdpedido != null) {
                pedidoIdpedido.getDescripcionPedidoCollection().remove(descripcionPedido);
                pedidoIdpedido = em.merge(pedidoIdpedido);
            }
            em.remove(descripcionPedido);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<DescripcionPedido> findDescripcionPedidoEntities() {
        return findDescripcionPedidoEntities(true, -1, -1);
    }

    public List<DescripcionPedido> findDescripcionPedidoEntities(int maxResults, int firstResult) {
        return findDescripcionPedidoEntities(false, maxResults, firstResult);
    }

    private List<DescripcionPedido> findDescripcionPedidoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(DescripcionPedido.class));
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

    public DescripcionPedido findDescripcionPedido(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(DescripcionPedido.class, id);
        } finally {
            em.close();
        }
    }

    public int getDescripcionPedidoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<DescripcionPedido> rt = cq.from(DescripcionPedido.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
