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
import EntidadesJPA.Productos;
import EntidadesJPA.BodegaProductos;
import EntidadesJPA.InventarioProducto;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Ch470
 */
public class InventarioProductoJpaController implements Serializable {

    public InventarioProductoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(InventarioProducto inventarioProducto) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Productos productosidProductos = inventarioProducto.getProductosidProductos();
            if (productosidProductos != null) {
                productosidProductos = em.getReference(productosidProductos.getClass(), productosidProductos.getIdProductos());
                inventarioProducto.setProductosidProductos(productosidProductos);
            }
            BodegaProductos bodegaProductosidbodega = inventarioProducto.getBodegaProductosidbodega();
            if (bodegaProductosidbodega != null) {
                bodegaProductosidbodega = em.getReference(bodegaProductosidbodega.getClass(), bodegaProductosidbodega.getIdbodega());
                inventarioProducto.setBodegaProductosidbodega(bodegaProductosidbodega);
            }
            em.persist(inventarioProducto);
            if (productosidProductos != null) {
                productosidProductos.getInventarioProductoCollection().add(inventarioProducto);
                productosidProductos = em.merge(productosidProductos);
            }
            if (bodegaProductosidbodega != null) {
                bodegaProductosidbodega.getInventarioProductoCollection().add(inventarioProducto);
                bodegaProductosidbodega = em.merge(bodegaProductosidbodega);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(InventarioProducto inventarioProducto) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            InventarioProducto persistentInventarioProducto = em.find(InventarioProducto.class, inventarioProducto.getIdInventarioProducto());
            Productos productosidProductosOld = persistentInventarioProducto.getProductosidProductos();
            Productos productosidProductosNew = inventarioProducto.getProductosidProductos();
            BodegaProductos bodegaProductosidbodegaOld = persistentInventarioProducto.getBodegaProductosidbodega();
            BodegaProductos bodegaProductosidbodegaNew = inventarioProducto.getBodegaProductosidbodega();
            if (productosidProductosNew != null) {
                productosidProductosNew = em.getReference(productosidProductosNew.getClass(), productosidProductosNew.getIdProductos());
                inventarioProducto.setProductosidProductos(productosidProductosNew);
            }
            if (bodegaProductosidbodegaNew != null) {
                bodegaProductosidbodegaNew = em.getReference(bodegaProductosidbodegaNew.getClass(), bodegaProductosidbodegaNew.getIdbodega());
                inventarioProducto.setBodegaProductosidbodega(bodegaProductosidbodegaNew);
            }
            inventarioProducto = em.merge(inventarioProducto);
            if (productosidProductosOld != null && !productosidProductosOld.equals(productosidProductosNew)) {
                productosidProductosOld.getInventarioProductoCollection().remove(inventarioProducto);
                productosidProductosOld = em.merge(productosidProductosOld);
            }
            if (productosidProductosNew != null && !productosidProductosNew.equals(productosidProductosOld)) {
                productosidProductosNew.getInventarioProductoCollection().add(inventarioProducto);
                productosidProductosNew = em.merge(productosidProductosNew);
            }
            if (bodegaProductosidbodegaOld != null && !bodegaProductosidbodegaOld.equals(bodegaProductosidbodegaNew)) {
                bodegaProductosidbodegaOld.getInventarioProductoCollection().remove(inventarioProducto);
                bodegaProductosidbodegaOld = em.merge(bodegaProductosidbodegaOld);
            }
            if (bodegaProductosidbodegaNew != null && !bodegaProductosidbodegaNew.equals(bodegaProductosidbodegaOld)) {
                bodegaProductosidbodegaNew.getInventarioProductoCollection().add(inventarioProducto);
                bodegaProductosidbodegaNew = em.merge(bodegaProductosidbodegaNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = inventarioProducto.getIdInventarioProducto();
                if (findInventarioProducto(id) == null) {
                    throw new NonexistentEntityException("The inventarioProducto with id " + id + " no longer exists.");
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
            InventarioProducto inventarioProducto;
            try {
                inventarioProducto = em.getReference(InventarioProducto.class, id);
                inventarioProducto.getIdInventarioProducto();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The inventarioProducto with id " + id + " no longer exists.", enfe);
            }
            Productos productosidProductos = inventarioProducto.getProductosidProductos();
            if (productosidProductos != null) {
                productosidProductos.getInventarioProductoCollection().remove(inventarioProducto);
                productosidProductos = em.merge(productosidProductos);
            }
            BodegaProductos bodegaProductosidbodega = inventarioProducto.getBodegaProductosidbodega();
            if (bodegaProductosidbodega != null) {
                bodegaProductosidbodega.getInventarioProductoCollection().remove(inventarioProducto);
                bodegaProductosidbodega = em.merge(bodegaProductosidbodega);
            }
            em.remove(inventarioProducto);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<InventarioProducto> findInventarioProductoEntities() {
        return findInventarioProductoEntities(true, -1, -1);
    }

    public List<InventarioProducto> findInventarioProductoEntities(int maxResults, int firstResult) {
        return findInventarioProductoEntities(false, maxResults, firstResult);
    }

    private List<InventarioProducto> findInventarioProductoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(InventarioProducto.class));
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

    public InventarioProducto findInventarioProducto(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(InventarioProducto.class, id);
        } finally {
            em.close();
        }
    }

    public int getInventarioProductoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<InventarioProducto> rt = cq.from(InventarioProducto.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
