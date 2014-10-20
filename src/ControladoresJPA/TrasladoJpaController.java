/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ControladoresJPA;

import ControladoresJPA.exceptions.NonexistentEntityException;
import ControladoresJPA.exceptions.PreexistingEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import EntidadesJPA.Productos;
import EntidadesJPA.Traslado;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Ch470
 */
public class TrasladoJpaController implements Serializable {

    public TrasladoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Traslado traslado) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Productos productosidProductos = traslado.getProductosidProductos();
            if (productosidProductos != null) {
                productosidProductos = em.getReference(productosidProductos.getClass(), productosidProductos.getIdProductos());
                traslado.setProductosidProductos(productosidProductos);
            }
            em.persist(traslado);
            if (productosidProductos != null) {
                //productosidProductos.getTrasladoCollection().add(traslado);
                productosidProductos = em.merge(productosidProductos);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findTraslado(traslado.getIdtraslado()) != null) {
                throw new PreexistingEntityException("Traslado " + traslado + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Traslado traslado) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Traslado persistentTraslado = em.find(Traslado.class, traslado.getIdtraslado());
            Productos productosidProductosOld = persistentTraslado.getProductosidProductos();
            Productos productosidProductosNew = traslado.getProductosidProductos();
            if (productosidProductosNew != null) {
                productosidProductosNew = em.getReference(productosidProductosNew.getClass(), productosidProductosNew.getIdProductos());
                traslado.setProductosidProductos(productosidProductosNew);
            }
            traslado = em.merge(traslado);
            if (productosidProductosOld != null && !productosidProductosOld.equals(productosidProductosNew)) {
                //productosidProductosOld.getTrasladoCollection().remove(traslado);
                productosidProductosOld = em.merge(productosidProductosOld);
            }
            if (productosidProductosNew != null && !productosidProductosNew.equals(productosidProductosOld)) {
                //productosidProductosNew.getTrasladoCollection().add(traslado);
                productosidProductosNew = em.merge(productosidProductosNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = traslado.getIdtraslado();
                if (findTraslado(id) == null) {
                    throw new NonexistentEntityException("The traslado with id " + id + " no longer exists.");
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
            Traslado traslado;
            try {
                traslado = em.getReference(Traslado.class, id);
                traslado.getIdtraslado();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The traslado with id " + id + " no longer exists.", enfe);
            }
            Productos productosidProductos = traslado.getProductosidProductos();
            if (productosidProductos != null) {
                //productosidProductos.getTrasladoCollection().remove(traslado);
                productosidProductos = em.merge(productosidProductos);
            }
            em.remove(traslado);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Traslado> findTrasladoEntities() {
        return findTrasladoEntities(true, -1, -1);
    }

    public List<Traslado> findTrasladoEntities(int maxResults, int firstResult) {
        return findTrasladoEntities(false, maxResults, firstResult);
    }

    private List<Traslado> findTrasladoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Traslado.class));
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

    public Traslado findTraslado(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Traslado.class, id);
        } finally {
            em.close();
        }
    }

    public int getTrasladoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Traslado> rt = cq.from(Traslado.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
