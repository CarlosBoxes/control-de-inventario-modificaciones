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
import EntidadesJPA.ProductosVendedores;
import EntidadesJPA.TipoVendedores;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Ch470
 */
public class ProductosVendedoresJpaController implements Serializable {

    public ProductosVendedoresJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(ProductosVendedores productosVendedores) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Productos productosidProductos = productosVendedores.getProductosidProductos();
            if (productosidProductos != null) {
                productosidProductos = em.getReference(productosidProductos.getClass(), productosidProductos.getIdProductos());
                productosVendedores.setProductosidProductos(productosidProductos);
            }
            TipoVendedores tipovendedoresidTipoVendedores = productosVendedores.getTipovendedoresidTipoVendedores();
            if (tipovendedoresidTipoVendedores != null) {
                tipovendedoresidTipoVendedores = em.getReference(tipovendedoresidTipoVendedores.getClass(), tipovendedoresidTipoVendedores.getIdTipoVendedores());
                productosVendedores.setTipovendedoresidTipoVendedores(tipovendedoresidTipoVendedores);
            }
            em.persist(productosVendedores);
            if (productosidProductos != null) {
                productosidProductos.getProductosVendedoresCollection().add(productosVendedores);
                productosidProductos = em.merge(productosidProductos);
            }
            if (tipovendedoresidTipoVendedores != null) {
                tipovendedoresidTipoVendedores.getProductosvendedoresCollection().add(productosVendedores);
                tipovendedoresidTipoVendedores = em.merge(tipovendedoresidTipoVendedores);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(ProductosVendedores productosVendedores) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ProductosVendedores persistentProductosVendedores = em.find(ProductosVendedores.class, productosVendedores.getIdlisitaproductosvendedores());
            Productos productosidProductosOld = persistentProductosVendedores.getProductosidProductos();
            Productos productosidProductosNew = productosVendedores.getProductosidProductos();
            TipoVendedores tipovendedoresidTipoVendedoresOld = persistentProductosVendedores.getTipovendedoresidTipoVendedores();
            TipoVendedores tipovendedoresidTipoVendedoresNew = productosVendedores.getTipovendedoresidTipoVendedores();
            if (productosidProductosNew != null) {
                productosidProductosNew = em.getReference(productosidProductosNew.getClass(), productosidProductosNew.getIdProductos());
                productosVendedores.setProductosidProductos(productosidProductosNew);
            }
            if (tipovendedoresidTipoVendedoresNew != null) {
                tipovendedoresidTipoVendedoresNew = em.getReference(tipovendedoresidTipoVendedoresNew.getClass(), tipovendedoresidTipoVendedoresNew.getIdTipoVendedores());
                productosVendedores.setTipovendedoresidTipoVendedores(tipovendedoresidTipoVendedoresNew);
            }
            productosVendedores = em.merge(productosVendedores);
            if (productosidProductosOld != null && !productosidProductosOld.equals(productosidProductosNew)) {
                productosidProductosOld.getProductosVendedoresCollection().remove(productosVendedores);
                productosidProductosOld = em.merge(productosidProductosOld);
            }
            if (productosidProductosNew != null && !productosidProductosNew.equals(productosidProductosOld)) {
                productosidProductosNew.getProductosVendedoresCollection().add(productosVendedores);
                productosidProductosNew = em.merge(productosidProductosNew);
            }
            if (tipovendedoresidTipoVendedoresOld != null && !tipovendedoresidTipoVendedoresOld.equals(tipovendedoresidTipoVendedoresNew)) {
                tipovendedoresidTipoVendedoresOld.getProductosvendedoresCollection().remove(productosVendedores);
                tipovendedoresidTipoVendedoresOld = em.merge(tipovendedoresidTipoVendedoresOld);
            }
            if (tipovendedoresidTipoVendedoresNew != null && !tipovendedoresidTipoVendedoresNew.equals(tipovendedoresidTipoVendedoresOld)) {
                tipovendedoresidTipoVendedoresNew.getProductosvendedoresCollection().add(productosVendedores);
                tipovendedoresidTipoVendedoresNew = em.merge(tipovendedoresidTipoVendedoresNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = productosVendedores.getIdlisitaproductosvendedores();
                if (findProductosVendedores(id) == null) {
                    throw new NonexistentEntityException("The productosVendedores with id " + id + " no longer exists.");
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
            ProductosVendedores productosVendedores;
            try {
                productosVendedores = em.getReference(ProductosVendedores.class, id);
                productosVendedores.getIdlisitaproductosvendedores();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The productosVendedores with id " + id + " no longer exists.", enfe);
            }
            Productos productosidProductos = productosVendedores.getProductosidProductos();
            if (productosidProductos != null) {
                productosidProductos.getProductosVendedoresCollection().remove(productosVendedores);
                productosidProductos = em.merge(productosidProductos);
            }
            TipoVendedores tipovendedoresidTipoVendedores = productosVendedores.getTipovendedoresidTipoVendedores();
            if (tipovendedoresidTipoVendedores != null) {
                tipovendedoresidTipoVendedores.getProductosvendedoresCollection().remove(productosVendedores);
                tipovendedoresidTipoVendedores = em.merge(tipovendedoresidTipoVendedores);
            }
            em.remove(productosVendedores);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<ProductosVendedores> findProductosVendedoresEntities() {
        return findProductosVendedoresEntities(true, -1, -1);
    }

    public List<ProductosVendedores> findProductosVendedoresEntities(int maxResults, int firstResult) {
        return findProductosVendedoresEntities(false, maxResults, firstResult);
    }

    private List<ProductosVendedores> findProductosVendedoresEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(ProductosVendedores.class));
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

    public ProductosVendedores findProductosVendedores(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(ProductosVendedores.class, id);
        } finally {
            em.close();
        }
    }

    public int getProductosVendedoresCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<ProductosVendedores> rt = cq.from(ProductosVendedores.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
