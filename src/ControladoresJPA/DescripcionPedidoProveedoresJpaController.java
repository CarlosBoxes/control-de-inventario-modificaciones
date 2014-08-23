/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ControladoresJPA;

import ControladoresJPA.exceptions.NonexistentEntityException;
import EntidadesJPA.DescripcionPedidoProveedores;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import EntidadesJPA.Productos;
import EntidadesJPA.PedidoProveedores;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Ch470
 */
public class DescripcionPedidoProveedoresJpaController implements Serializable {

    public DescripcionPedidoProveedoresJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(DescripcionPedidoProveedores descripcionPedidoProveedores) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Productos productosidProductos = descripcionPedidoProveedores.getProductosidProductos();
            if (productosidProductos != null) {
                productosidProductos = em.getReference(productosidProductos.getClass(), productosidProductos.getIdProductos());
                descripcionPedidoProveedores.setProductosidProductos(productosidProductos);
            }
            PedidoProveedores pedidoProveedoresIdpedidoProveedores = descripcionPedidoProveedores.getPedidoProveedoresIdpedidoProveedores();
            if (pedidoProveedoresIdpedidoProveedores != null) {
                pedidoProveedoresIdpedidoProveedores = em.getReference(pedidoProveedoresIdpedidoProveedores.getClass(), pedidoProveedoresIdpedidoProveedores.getIdpedidoProveedores());
                descripcionPedidoProveedores.setPedidoProveedoresIdpedidoProveedores(pedidoProveedoresIdpedidoProveedores);
            }
            em.persist(descripcionPedidoProveedores);
            if (productosidProductos != null) {
                productosidProductos.getDescripcionPedidoProveedoresCollection().add(descripcionPedidoProveedores);
                productosidProductos = em.merge(productosidProductos);
            }
            if (pedidoProveedoresIdpedidoProveedores != null) {
                pedidoProveedoresIdpedidoProveedores.getDescripcionPedidoProveedoresCollection().add(descripcionPedidoProveedores);
                pedidoProveedoresIdpedidoProveedores = em.merge(pedidoProveedoresIdpedidoProveedores);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(DescripcionPedidoProveedores descripcionPedidoProveedores) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            DescripcionPedidoProveedores persistentDescripcionPedidoProveedores = em.find(DescripcionPedidoProveedores.class, descripcionPedidoProveedores.getIddescripcionP());
            Productos productosidProductosOld = persistentDescripcionPedidoProveedores.getProductosidProductos();
            Productos productosidProductosNew = descripcionPedidoProveedores.getProductosidProductos();
            PedidoProveedores pedidoProveedoresIdpedidoProveedoresOld = persistentDescripcionPedidoProveedores.getPedidoProveedoresIdpedidoProveedores();
            PedidoProveedores pedidoProveedoresIdpedidoProveedoresNew = descripcionPedidoProveedores.getPedidoProveedoresIdpedidoProveedores();
            if (productosidProductosNew != null) {
                productosidProductosNew = em.getReference(productosidProductosNew.getClass(), productosidProductosNew.getIdProductos());
                descripcionPedidoProveedores.setProductosidProductos(productosidProductosNew);
            }
            if (pedidoProveedoresIdpedidoProveedoresNew != null) {
                pedidoProveedoresIdpedidoProveedoresNew = em.getReference(pedidoProveedoresIdpedidoProveedoresNew.getClass(), pedidoProveedoresIdpedidoProveedoresNew.getIdpedidoProveedores());
                descripcionPedidoProveedores.setPedidoProveedoresIdpedidoProveedores(pedidoProveedoresIdpedidoProveedoresNew);
            }
            descripcionPedidoProveedores = em.merge(descripcionPedidoProveedores);
            if (productosidProductosOld != null && !productosidProductosOld.equals(productosidProductosNew)) {
                productosidProductosOld.getDescripcionPedidoProveedoresCollection().remove(descripcionPedidoProveedores);
                productosidProductosOld = em.merge(productosidProductosOld);
            }
            if (productosidProductosNew != null && !productosidProductosNew.equals(productosidProductosOld)) {
                productosidProductosNew.getDescripcionPedidoProveedoresCollection().add(descripcionPedidoProveedores);
                productosidProductosNew = em.merge(productosidProductosNew);
            }
            if (pedidoProveedoresIdpedidoProveedoresOld != null && !pedidoProveedoresIdpedidoProveedoresOld.equals(pedidoProveedoresIdpedidoProveedoresNew)) {
                pedidoProveedoresIdpedidoProveedoresOld.getDescripcionPedidoProveedoresCollection().remove(descripcionPedidoProveedores);
                pedidoProveedoresIdpedidoProveedoresOld = em.merge(pedidoProveedoresIdpedidoProveedoresOld);
            }
            if (pedidoProveedoresIdpedidoProveedoresNew != null && !pedidoProveedoresIdpedidoProveedoresNew.equals(pedidoProveedoresIdpedidoProveedoresOld)) {
                pedidoProveedoresIdpedidoProveedoresNew.getDescripcionPedidoProveedoresCollection().add(descripcionPedidoProveedores);
                pedidoProveedoresIdpedidoProveedoresNew = em.merge(pedidoProveedoresIdpedidoProveedoresNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = descripcionPedidoProveedores.getIddescripcionP();
                if (findDescripcionPedidoProveedores(id) == null) {
                    throw new NonexistentEntityException("The descripcionPedidoProveedores with id " + id + " no longer exists.");
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
            DescripcionPedidoProveedores descripcionPedidoProveedores;
            try {
                descripcionPedidoProveedores = em.getReference(DescripcionPedidoProveedores.class, id);
                descripcionPedidoProveedores.getIddescripcionP();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The descripcionPedidoProveedores with id " + id + " no longer exists.", enfe);
            }
            Productos productosidProductos = descripcionPedidoProveedores.getProductosidProductos();
            if (productosidProductos != null) {
                productosidProductos.getDescripcionPedidoProveedoresCollection().remove(descripcionPedidoProveedores);
                productosidProductos = em.merge(productosidProductos);
            }
            PedidoProveedores pedidoProveedoresIdpedidoProveedores = descripcionPedidoProveedores.getPedidoProveedoresIdpedidoProveedores();
            if (pedidoProveedoresIdpedidoProveedores != null) {
                pedidoProveedoresIdpedidoProveedores.getDescripcionPedidoProveedoresCollection().remove(descripcionPedidoProveedores);
                pedidoProveedoresIdpedidoProveedores = em.merge(pedidoProveedoresIdpedidoProveedores);
            }
            em.remove(descripcionPedidoProveedores);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<DescripcionPedidoProveedores> findDescripcionPedidoProveedoresEntities() {
        return findDescripcionPedidoProveedoresEntities(true, -1, -1);
    }

    public List<DescripcionPedidoProveedores> findDescripcionPedidoProveedoresEntities(int maxResults, int firstResult) {
        return findDescripcionPedidoProveedoresEntities(false, maxResults, firstResult);
    }

    private List<DescripcionPedidoProveedores> findDescripcionPedidoProveedoresEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(DescripcionPedidoProveedores.class));
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

    public DescripcionPedidoProveedores findDescripcionPedidoProveedores(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(DescripcionPedidoProveedores.class, id);
        } finally {
            em.close();
        }
    }

    public int getDescripcionPedidoProveedoresCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<DescripcionPedidoProveedores> rt = cq.from(DescripcionPedidoProveedores.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
