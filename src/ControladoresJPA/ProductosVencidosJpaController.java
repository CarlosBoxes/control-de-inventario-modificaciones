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
import EntidadesJPA.ProductosVencidos;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Ch470
 */
public class ProductosVencidosJpaController implements Serializable {

    public ProductosVencidosJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(ProductosVencidos productosVencidos) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Productos productosidProductos = productosVencidos.getProductosidProductos();
            if (productosidProductos != null) {
                productosidProductos = em.getReference(productosidProductos.getClass(), productosidProductos.getIdProductos());
                productosVencidos.setProductosidProductos(productosidProductos);
            }
            em.persist(productosVencidos);
            if (productosidProductos != null) {
                productosidProductos.getProductosVencidosCollection().add(productosVencidos);
                productosidProductos = em.merge(productosidProductos);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(ProductosVencidos productosVencidos) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ProductosVencidos persistentProductosVencidos = em.find(ProductosVencidos.class, productosVencidos.getIdProductosVencidos());
            Productos productosidProductosOld = persistentProductosVencidos.getProductosidProductos();
            Productos productosidProductosNew = productosVencidos.getProductosidProductos();
            if (productosidProductosNew != null) {
                productosidProductosNew = em.getReference(productosidProductosNew.getClass(), productosidProductosNew.getIdProductos());
                productosVencidos.setProductosidProductos(productosidProductosNew);
            }
            productosVencidos = em.merge(productosVencidos);
            if (productosidProductosOld != null && !productosidProductosOld.equals(productosidProductosNew)) {
                productosidProductosOld.getProductosVencidosCollection().remove(productosVencidos);
                productosidProductosOld = em.merge(productosidProductosOld);
            }
            if (productosidProductosNew != null && !productosidProductosNew.equals(productosidProductosOld)) {
                productosidProductosNew.getProductosVencidosCollection().add(productosVencidos);
                productosidProductosNew = em.merge(productosidProductosNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = productosVencidos.getIdProductosVencidos();
                if (findProductosVencidos(id) == null) {
                    throw new NonexistentEntityException("The productosVencidos with id " + id + " no longer exists.");
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
            ProductosVencidos productosVencidos;
            try {
                productosVencidos = em.getReference(ProductosVencidos.class, id);
                productosVencidos.getIdProductosVencidos();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The productosVencidos with id " + id + " no longer exists.", enfe);
            }
            Productos productosidProductos = productosVencidos.getProductosidProductos();
            if (productosidProductos != null) {
                productosidProductos.getProductosVencidosCollection().remove(productosVencidos);
                productosidProductos = em.merge(productosidProductos);
            }
            em.remove(productosVencidos);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<ProductosVencidos> findProductosVencidosEntities() {
        return findProductosVencidosEntities(true, -1, -1);
    }

    public List<ProductosVencidos> findProductosVencidosEntities(int maxResults, int firstResult) {
        return findProductosVencidosEntities(false, maxResults, firstResult);
    }

    private List<ProductosVencidos> findProductosVencidosEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(ProductosVencidos.class));
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

    public ProductosVencidos findProductosVencidos(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(ProductosVencidos.class, id);
        } finally {
            em.close();
        }
    }

    public int getProductosVencidosCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<ProductosVencidos> rt = cq.from(ProductosVencidos.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
