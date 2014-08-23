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
import EntidadesJPA.ProductosClientes;
import EntidadesJPA.TipoClientes;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Ch470
 */
public class ProductosClientesJpaController implements Serializable {

    public ProductosClientesJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(ProductosClientes productosClientes) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Productos productosidProductos = productosClientes.getProductosidProductos();
            if (productosidProductos != null) {
                productosidProductos = em.getReference(productosidProductos.getClass(), productosidProductos.getIdProductos());
                productosClientes.setProductosidProductos(productosidProductos);
            }
            TipoClientes tipoclientesidTipoClientes = productosClientes.getTipoclientesidTipoClientes();
            if (tipoclientesidTipoClientes != null) {
                tipoclientesidTipoClientes = em.getReference(tipoclientesidTipoClientes.getClass(), tipoclientesidTipoClientes.getIdTipoClientes());
                productosClientes.setTipoclientesidTipoClientes(tipoclientesidTipoClientes);
            }
            em.persist(productosClientes);
            if (productosidProductos != null) {
                productosidProductos.getProductosClientesCollection().add(productosClientes);
                productosidProductos = em.merge(productosidProductos);
            }
            if (tipoclientesidTipoClientes != null) {
                tipoclientesidTipoClientes.getProductosclientesCollection().add(productosClientes);
                tipoclientesidTipoClientes = em.merge(tipoclientesidTipoClientes);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(ProductosClientes productosClientes) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ProductosClientes persistentProductosClientes = em.find(ProductosClientes.class, productosClientes.getIdproductosespecialclientes());
            Productos productosidProductosOld = persistentProductosClientes.getProductosidProductos();
            Productos productosidProductosNew = productosClientes.getProductosidProductos();
            TipoClientes tipoclientesidTipoClientesOld = persistentProductosClientes.getTipoclientesidTipoClientes();
            TipoClientes tipoclientesidTipoClientesNew = productosClientes.getTipoclientesidTipoClientes();
            if (productosidProductosNew != null) {
                productosidProductosNew = em.getReference(productosidProductosNew.getClass(), productosidProductosNew.getIdProductos());
                productosClientes.setProductosidProductos(productosidProductosNew);
            }
            if (tipoclientesidTipoClientesNew != null) {
                tipoclientesidTipoClientesNew = em.getReference(tipoclientesidTipoClientesNew.getClass(), tipoclientesidTipoClientesNew.getIdTipoClientes());
                productosClientes.setTipoclientesidTipoClientes(tipoclientesidTipoClientesNew);
            }
            productosClientes = em.merge(productosClientes);
            if (productosidProductosOld != null && !productosidProductosOld.equals(productosidProductosNew)) {
                productosidProductosOld.getProductosClientesCollection().remove(productosClientes);
                productosidProductosOld = em.merge(productosidProductosOld);
            }
            if (productosidProductosNew != null && !productosidProductosNew.equals(productosidProductosOld)) {
                productosidProductosNew.getProductosClientesCollection().add(productosClientes);
                productosidProductosNew = em.merge(productosidProductosNew);
            }
            if (tipoclientesidTipoClientesOld != null && !tipoclientesidTipoClientesOld.equals(tipoclientesidTipoClientesNew)) {
                tipoclientesidTipoClientesOld.getProductosclientesCollection().remove(productosClientes);
                tipoclientesidTipoClientesOld = em.merge(tipoclientesidTipoClientesOld);
            }
            if (tipoclientesidTipoClientesNew != null && !tipoclientesidTipoClientesNew.equals(tipoclientesidTipoClientesOld)) {
                tipoclientesidTipoClientesNew.getProductosclientesCollection().add(productosClientes);
                tipoclientesidTipoClientesNew = em.merge(tipoclientesidTipoClientesNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = productosClientes.getIdproductosespecialclientes();
                if (findProductosClientes(id) == null) {
                    throw new NonexistentEntityException("The productosClientes with id " + id + " no longer exists.");
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
            ProductosClientes productosClientes;
            try {
                productosClientes = em.getReference(ProductosClientes.class, id);
                productosClientes.getIdproductosespecialclientes();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The productosClientes with id " + id + " no longer exists.", enfe);
            }
            Productos productosidProductos = productosClientes.getProductosidProductos();
            if (productosidProductos != null) {
                productosidProductos.getProductosClientesCollection().remove(productosClientes);
                productosidProductos = em.merge(productosidProductos);
            }
            TipoClientes tipoclientesidTipoClientes = productosClientes.getTipoclientesidTipoClientes();
            if (tipoclientesidTipoClientes != null) {
                tipoclientesidTipoClientes.getProductosclientesCollection().remove(productosClientes);
                tipoclientesidTipoClientes = em.merge(tipoclientesidTipoClientes);
            }
            em.remove(productosClientes);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<ProductosClientes> findProductosClientesEntities() {
        return findProductosClientesEntities(true, -1, -1);
    }

    public List<ProductosClientes> findProductosClientesEntities(int maxResults, int firstResult) {
        return findProductosClientesEntities(false, maxResults, firstResult);
    }

    private List<ProductosClientes> findProductosClientesEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(ProductosClientes.class));
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

    public ProductosClientes findProductosClientes(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(ProductosClientes.class, id);
        } finally {
            em.close();
        }
    }

    public int getProductosClientesCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<ProductosClientes> rt = cq.from(ProductosClientes.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
