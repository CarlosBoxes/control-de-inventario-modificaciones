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
import EntidadesJPA.Productos;
import EntidadesJPA.ProductosDefectuoso;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Ch470
 */
public class ProductosDefectuosoJpaController implements Serializable {

    public ProductosDefectuosoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(ProductosDefectuoso productosDefectuoso) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Vendedores vendedoresIdvendedores = productosDefectuoso.getVendedoresIdvendedores();
            if (vendedoresIdvendedores != null) {
                vendedoresIdvendedores = em.getReference(vendedoresIdvendedores.getClass(), vendedoresIdvendedores.getIdvendedores());
                productosDefectuoso.setVendedoresIdvendedores(vendedoresIdvendedores);
            }
            Productos productosidProductos = productosDefectuoso.getProductosidProductos();
            if (productosidProductos != null) {
                productosidProductos = em.getReference(productosidProductos.getClass(), productosidProductos.getIdProductos());
                productosDefectuoso.setProductosidProductos(productosidProductos);
            }
            em.persist(productosDefectuoso);
            if (vendedoresIdvendedores != null) {
                vendedoresIdvendedores.getProductosDefectuosoCollection().add(productosDefectuoso);
                vendedoresIdvendedores = em.merge(vendedoresIdvendedores);
            }
            if (productosidProductos != null) {
                productosidProductos.getProductosDefectuosoCollection().add(productosDefectuoso);
                productosidProductos = em.merge(productosidProductos);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(ProductosDefectuoso productosDefectuoso) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ProductosDefectuoso persistentProductosDefectuoso = em.find(ProductosDefectuoso.class, productosDefectuoso.getIdProductoDefectuoso());
            Vendedores vendedoresIdvendedoresOld = persistentProductosDefectuoso.getVendedoresIdvendedores();
            Vendedores vendedoresIdvendedoresNew = productosDefectuoso.getVendedoresIdvendedores();
            Productos productosidProductosOld = persistentProductosDefectuoso.getProductosidProductos();
            Productos productosidProductosNew = productosDefectuoso.getProductosidProductos();
            if (vendedoresIdvendedoresNew != null) {
                vendedoresIdvendedoresNew = em.getReference(vendedoresIdvendedoresNew.getClass(), vendedoresIdvendedoresNew.getIdvendedores());
                productosDefectuoso.setVendedoresIdvendedores(vendedoresIdvendedoresNew);
            }
            if (productosidProductosNew != null) {
                productosidProductosNew = em.getReference(productosidProductosNew.getClass(), productosidProductosNew.getIdProductos());
                productosDefectuoso.setProductosidProductos(productosidProductosNew);
            }
            productosDefectuoso = em.merge(productosDefectuoso);
            if (vendedoresIdvendedoresOld != null && !vendedoresIdvendedoresOld.equals(vendedoresIdvendedoresNew)) {
                vendedoresIdvendedoresOld.getProductosDefectuosoCollection().remove(productosDefectuoso);
                vendedoresIdvendedoresOld = em.merge(vendedoresIdvendedoresOld);
            }
            if (vendedoresIdvendedoresNew != null && !vendedoresIdvendedoresNew.equals(vendedoresIdvendedoresOld)) {
                vendedoresIdvendedoresNew.getProductosDefectuosoCollection().add(productosDefectuoso);
                vendedoresIdvendedoresNew = em.merge(vendedoresIdvendedoresNew);
            }
            if (productosidProductosOld != null && !productosidProductosOld.equals(productosidProductosNew)) {
                productosidProductosOld.getProductosDefectuosoCollection().remove(productosDefectuoso);
                productosidProductosOld = em.merge(productosidProductosOld);
            }
            if (productosidProductosNew != null && !productosidProductosNew.equals(productosidProductosOld)) {
                productosidProductosNew.getProductosDefectuosoCollection().add(productosDefectuoso);
                productosidProductosNew = em.merge(productosidProductosNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = productosDefectuoso.getIdProductoDefectuoso();
                if (findProductosDefectuoso(id) == null) {
                    throw new NonexistentEntityException("The productosDefectuoso with id " + id + " no longer exists.");
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
            ProductosDefectuoso productosDefectuoso;
            try {
                productosDefectuoso = em.getReference(ProductosDefectuoso.class, id);
                productosDefectuoso.getIdProductoDefectuoso();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The productosDefectuoso with id " + id + " no longer exists.", enfe);
            }
            Vendedores vendedoresIdvendedores = productosDefectuoso.getVendedoresIdvendedores();
            if (vendedoresIdvendedores != null) {
                vendedoresIdvendedores.getProductosDefectuosoCollection().remove(productosDefectuoso);
                vendedoresIdvendedores = em.merge(vendedoresIdvendedores);
            }
            Productos productosidProductos = productosDefectuoso.getProductosidProductos();
            if (productosidProductos != null) {
                productosidProductos.getProductosDefectuosoCollection().remove(productosDefectuoso);
                productosidProductos = em.merge(productosidProductos);
            }
            em.remove(productosDefectuoso);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<ProductosDefectuoso> findProductosDefectuosoEntities() {
        return findProductosDefectuosoEntities(true, -1, -1);
    }

    public List<ProductosDefectuoso> findProductosDefectuosoEntities(int maxResults, int firstResult) {
        return findProductosDefectuosoEntities(false, maxResults, firstResult);
    }

    private List<ProductosDefectuoso> findProductosDefectuosoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(ProductosDefectuoso.class));
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

    public ProductosDefectuoso findProductosDefectuoso(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(ProductosDefectuoso.class, id);
        } finally {
            em.close();
        }
    }

    public int getProductosDefectuosoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<ProductosDefectuoso> rt = cq.from(ProductosDefectuoso.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
