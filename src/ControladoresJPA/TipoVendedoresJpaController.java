/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ControladoresJPA;

import ControladoresJPA.exceptions.IllegalOrphanException;
import ControladoresJPA.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import EntidadesJPA.Vendedores;
import java.util.ArrayList;
import java.util.Collection;
import EntidadesJPA.ProductosVendedores;
import EntidadesJPA.TipoVendedores;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Ch470
 */
public class TipoVendedoresJpaController implements Serializable {

    public TipoVendedoresJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(TipoVendedores tipoVendedores) {
        if (tipoVendedores.getVendedoresCollection() == null) {
            tipoVendedores.setVendedoresCollection(new ArrayList<Vendedores>());
        }
        if (tipoVendedores.getProductosvendedoresCollection() == null) {
            tipoVendedores.setProductosvendedoresCollection(new ArrayList<ProductosVendedores>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<Vendedores> attachedVendedoresCollection = new ArrayList<Vendedores>();
            for (Vendedores vendedoresCollectionVendedoresToAttach : tipoVendedores.getVendedoresCollection()) {
                vendedoresCollectionVendedoresToAttach = em.getReference(vendedoresCollectionVendedoresToAttach.getClass(), vendedoresCollectionVendedoresToAttach.getIdvendedores());
                attachedVendedoresCollection.add(vendedoresCollectionVendedoresToAttach);
            }
            tipoVendedores.setVendedoresCollection(attachedVendedoresCollection);
            Collection<ProductosVendedores> attachedProductosvendedoresCollection = new ArrayList<ProductosVendedores>();
            for (ProductosVendedores productosvendedoresCollectionProductosVendedoresToAttach : tipoVendedores.getProductosvendedoresCollection()) {
                productosvendedoresCollectionProductosVendedoresToAttach = em.getReference(productosvendedoresCollectionProductosVendedoresToAttach.getClass(), productosvendedoresCollectionProductosVendedoresToAttach.getIdlisitaproductosvendedores());
                attachedProductosvendedoresCollection.add(productosvendedoresCollectionProductosVendedoresToAttach);
            }
            tipoVendedores.setProductosvendedoresCollection(attachedProductosvendedoresCollection);
            em.persist(tipoVendedores);
            for (Vendedores vendedoresCollectionVendedores : tipoVendedores.getVendedoresCollection()) {
                TipoVendedores oldTipoVendedoresidTipoVendedoresOfVendedoresCollectionVendedores = vendedoresCollectionVendedores.getTipoVendedoresidTipoVendedores();
                vendedoresCollectionVendedores.setTipoVendedoresidTipoVendedores(tipoVendedores);
                vendedoresCollectionVendedores = em.merge(vendedoresCollectionVendedores);
                if (oldTipoVendedoresidTipoVendedoresOfVendedoresCollectionVendedores != null) {
                    oldTipoVendedoresidTipoVendedoresOfVendedoresCollectionVendedores.getVendedoresCollection().remove(vendedoresCollectionVendedores);
                    oldTipoVendedoresidTipoVendedoresOfVendedoresCollectionVendedores = em.merge(oldTipoVendedoresidTipoVendedoresOfVendedoresCollectionVendedores);
                }
            }
            for (ProductosVendedores productosvendedoresCollectionProductosVendedores : tipoVendedores.getProductosvendedoresCollection()) {
                TipoVendedores oldTipovendedoresidTipoVendedoresOfProductosvendedoresCollectionProductosVendedores = productosvendedoresCollectionProductosVendedores.getTipovendedoresidTipoVendedores();
                productosvendedoresCollectionProductosVendedores.setTipovendedoresidTipoVendedores(tipoVendedores);
                productosvendedoresCollectionProductosVendedores = em.merge(productosvendedoresCollectionProductosVendedores);
                if (oldTipovendedoresidTipoVendedoresOfProductosvendedoresCollectionProductosVendedores != null) {
                    oldTipovendedoresidTipoVendedoresOfProductosvendedoresCollectionProductosVendedores.getProductosvendedoresCollection().remove(productosvendedoresCollectionProductosVendedores);
                    oldTipovendedoresidTipoVendedoresOfProductosvendedoresCollectionProductosVendedores = em.merge(oldTipovendedoresidTipoVendedoresOfProductosvendedoresCollectionProductosVendedores);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(TipoVendedores tipoVendedores) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TipoVendedores persistentTipoVendedores = em.find(TipoVendedores.class, tipoVendedores.getIdTipoVendedores());
            Collection<Vendedores> vendedoresCollectionOld = persistentTipoVendedores.getVendedoresCollection();
            Collection<Vendedores> vendedoresCollectionNew = tipoVendedores.getVendedoresCollection();
            Collection<ProductosVendedores> productosvendedoresCollectionOld = persistentTipoVendedores.getProductosvendedoresCollection();
            Collection<ProductosVendedores> productosvendedoresCollectionNew = tipoVendedores.getProductosvendedoresCollection();
            List<String> illegalOrphanMessages = null;
            for (Vendedores vendedoresCollectionOldVendedores : vendedoresCollectionOld) {
                if (!vendedoresCollectionNew.contains(vendedoresCollectionOldVendedores)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Vendedores " + vendedoresCollectionOldVendedores + " since its tipoVendedoresidTipoVendedores field is not nullable.");
                }
            }
            for (ProductosVendedores productosvendedoresCollectionOldProductosVendedores : productosvendedoresCollectionOld) {
                if (!productosvendedoresCollectionNew.contains(productosvendedoresCollectionOldProductosVendedores)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain ProductosVendedores " + productosvendedoresCollectionOldProductosVendedores + " since its tipovendedoresidTipoVendedores field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Collection<Vendedores> attachedVendedoresCollectionNew = new ArrayList<Vendedores>();
            for (Vendedores vendedoresCollectionNewVendedoresToAttach : vendedoresCollectionNew) {
                vendedoresCollectionNewVendedoresToAttach = em.getReference(vendedoresCollectionNewVendedoresToAttach.getClass(), vendedoresCollectionNewVendedoresToAttach.getIdvendedores());
                attachedVendedoresCollectionNew.add(vendedoresCollectionNewVendedoresToAttach);
            }
            vendedoresCollectionNew = attachedVendedoresCollectionNew;
            tipoVendedores.setVendedoresCollection(vendedoresCollectionNew);
            Collection<ProductosVendedores> attachedProductosvendedoresCollectionNew = new ArrayList<ProductosVendedores>();
            for (ProductosVendedores productosvendedoresCollectionNewProductosVendedoresToAttach : productosvendedoresCollectionNew) {
                productosvendedoresCollectionNewProductosVendedoresToAttach = em.getReference(productosvendedoresCollectionNewProductosVendedoresToAttach.getClass(), productosvendedoresCollectionNewProductosVendedoresToAttach.getIdlisitaproductosvendedores());
                attachedProductosvendedoresCollectionNew.add(productosvendedoresCollectionNewProductosVendedoresToAttach);
            }
            productosvendedoresCollectionNew = attachedProductosvendedoresCollectionNew;
            tipoVendedores.setProductosvendedoresCollection(productosvendedoresCollectionNew);
            tipoVendedores = em.merge(tipoVendedores);
            for (Vendedores vendedoresCollectionNewVendedores : vendedoresCollectionNew) {
                if (!vendedoresCollectionOld.contains(vendedoresCollectionNewVendedores)) {
                    TipoVendedores oldTipoVendedoresidTipoVendedoresOfVendedoresCollectionNewVendedores = vendedoresCollectionNewVendedores.getTipoVendedoresidTipoVendedores();
                    vendedoresCollectionNewVendedores.setTipoVendedoresidTipoVendedores(tipoVendedores);
                    vendedoresCollectionNewVendedores = em.merge(vendedoresCollectionNewVendedores);
                    if (oldTipoVendedoresidTipoVendedoresOfVendedoresCollectionNewVendedores != null && !oldTipoVendedoresidTipoVendedoresOfVendedoresCollectionNewVendedores.equals(tipoVendedores)) {
                        oldTipoVendedoresidTipoVendedoresOfVendedoresCollectionNewVendedores.getVendedoresCollection().remove(vendedoresCollectionNewVendedores);
                        oldTipoVendedoresidTipoVendedoresOfVendedoresCollectionNewVendedores = em.merge(oldTipoVendedoresidTipoVendedoresOfVendedoresCollectionNewVendedores);
                    }
                }
            }
            for (ProductosVendedores productosvendedoresCollectionNewProductosVendedores : productosvendedoresCollectionNew) {
                if (!productosvendedoresCollectionOld.contains(productosvendedoresCollectionNewProductosVendedores)) {
                    TipoVendedores oldTipovendedoresidTipoVendedoresOfProductosvendedoresCollectionNewProductosVendedores = productosvendedoresCollectionNewProductosVendedores.getTipovendedoresidTipoVendedores();
                    productosvendedoresCollectionNewProductosVendedores.setTipovendedoresidTipoVendedores(tipoVendedores);
                    productosvendedoresCollectionNewProductosVendedores = em.merge(productosvendedoresCollectionNewProductosVendedores);
                    if (oldTipovendedoresidTipoVendedoresOfProductosvendedoresCollectionNewProductosVendedores != null && !oldTipovendedoresidTipoVendedoresOfProductosvendedoresCollectionNewProductosVendedores.equals(tipoVendedores)) {
                        oldTipovendedoresidTipoVendedoresOfProductosvendedoresCollectionNewProductosVendedores.getProductosvendedoresCollection().remove(productosvendedoresCollectionNewProductosVendedores);
                        oldTipovendedoresidTipoVendedoresOfProductosvendedoresCollectionNewProductosVendedores = em.merge(oldTipovendedoresidTipoVendedoresOfProductosvendedoresCollectionNewProductosVendedores);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = tipoVendedores.getIdTipoVendedores();
                if (findTipoVendedores(id) == null) {
                    throw new NonexistentEntityException("The tipoVendedores with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TipoVendedores tipoVendedores;
            try {
                tipoVendedores = em.getReference(TipoVendedores.class, id);
                tipoVendedores.getIdTipoVendedores();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The tipoVendedores with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Vendedores> vendedoresCollectionOrphanCheck = tipoVendedores.getVendedoresCollection();
            for (Vendedores vendedoresCollectionOrphanCheckVendedores : vendedoresCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This TipoVendedores (" + tipoVendedores + ") cannot be destroyed since the Vendedores " + vendedoresCollectionOrphanCheckVendedores + " in its vendedoresCollection field has a non-nullable tipoVendedoresidTipoVendedores field.");
            }
            Collection<ProductosVendedores> productosvendedoresCollectionOrphanCheck = tipoVendedores.getProductosvendedoresCollection();
            for (ProductosVendedores productosvendedoresCollectionOrphanCheckProductosVendedores : productosvendedoresCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This TipoVendedores (" + tipoVendedores + ") cannot be destroyed since the ProductosVendedores " + productosvendedoresCollectionOrphanCheckProductosVendedores + " in its productosvendedoresCollection field has a non-nullable tipovendedoresidTipoVendedores field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(tipoVendedores);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<TipoVendedores> findTipoVendedoresEntities() {
        return findTipoVendedoresEntities(true, -1, -1);
    }

    public List<TipoVendedores> findTipoVendedoresEntities(int maxResults, int firstResult) {
        return findTipoVendedoresEntities(false, maxResults, firstResult);
    }

    private List<TipoVendedores> findTipoVendedoresEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(TipoVendedores.class));
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

    public TipoVendedores findTipoVendedores(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(TipoVendedores.class, id);
        } finally {
            em.close();
        }
    }

    public int getTipoVendedoresCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<TipoVendedores> rt = cq.from(TipoVendedores.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
