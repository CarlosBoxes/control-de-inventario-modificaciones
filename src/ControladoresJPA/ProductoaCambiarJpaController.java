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
import EntidadesJPA.Liquidacion;
import EntidadesJPA.ProductoaCambiar;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Ch470
 */
public class ProductoaCambiarJpaController implements Serializable {

    public ProductoaCambiarJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(ProductoaCambiar productoaCambiar) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Liquidacion liquidacionIdliquidacion = productoaCambiar.getLiquidacionIdliquidacion();
            if (liquidacionIdliquidacion != null) {
                liquidacionIdliquidacion = em.getReference(liquidacionIdliquidacion.getClass(), liquidacionIdliquidacion.getIdliquidacion());
                productoaCambiar.setLiquidacionIdliquidacion(liquidacionIdliquidacion);
            }
            em.persist(productoaCambiar);
            if (liquidacionIdliquidacion != null) {
                liquidacionIdliquidacion.getProductoacambiarCollection().add(productoaCambiar);
                liquidacionIdliquidacion = em.merge(liquidacionIdliquidacion);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(ProductoaCambiar productoaCambiar) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ProductoaCambiar persistentProductoaCambiar = em.find(ProductoaCambiar.class, productoaCambiar.getIdproductoacambiar());
            Liquidacion liquidacionIdliquidacionOld = persistentProductoaCambiar.getLiquidacionIdliquidacion();
            Liquidacion liquidacionIdliquidacionNew = productoaCambiar.getLiquidacionIdliquidacion();
            if (liquidacionIdliquidacionNew != null) {
                liquidacionIdliquidacionNew = em.getReference(liquidacionIdliquidacionNew.getClass(), liquidacionIdliquidacionNew.getIdliquidacion());
                productoaCambiar.setLiquidacionIdliquidacion(liquidacionIdliquidacionNew);
            }
            productoaCambiar = em.merge(productoaCambiar);
            if (liquidacionIdliquidacionOld != null && !liquidacionIdliquidacionOld.equals(liquidacionIdliquidacionNew)) {
                liquidacionIdliquidacionOld.getProductoacambiarCollection().remove(productoaCambiar);
                liquidacionIdliquidacionOld = em.merge(liquidacionIdliquidacionOld);
            }
            if (liquidacionIdliquidacionNew != null && !liquidacionIdliquidacionNew.equals(liquidacionIdliquidacionOld)) {
                liquidacionIdliquidacionNew.getProductoacambiarCollection().add(productoaCambiar);
                liquidacionIdliquidacionNew = em.merge(liquidacionIdliquidacionNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = productoaCambiar.getIdproductoacambiar();
                if (findProductoaCambiar(id) == null) {
                    throw new NonexistentEntityException("The productoaCambiar with id " + id + " no longer exists.");
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
            ProductoaCambiar productoaCambiar;
            try {
                productoaCambiar = em.getReference(ProductoaCambiar.class, id);
                productoaCambiar.getIdproductoacambiar();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The productoaCambiar with id " + id + " no longer exists.", enfe);
            }
            Liquidacion liquidacionIdliquidacion = productoaCambiar.getLiquidacionIdliquidacion();
            if (liquidacionIdliquidacion != null) {
                liquidacionIdliquidacion.getProductoacambiarCollection().remove(productoaCambiar);
                liquidacionIdliquidacion = em.merge(liquidacionIdliquidacion);
            }
            em.remove(productoaCambiar);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<ProductoaCambiar> findProductoaCambiarEntities() {
        return findProductoaCambiarEntities(true, -1, -1);
    }

    public List<ProductoaCambiar> findProductoaCambiarEntities(int maxResults, int firstResult) {
        return findProductoaCambiarEntities(false, maxResults, firstResult);
    }

    private List<ProductoaCambiar> findProductoaCambiarEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(ProductoaCambiar.class));
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

    public ProductoaCambiar findProductoaCambiar(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(ProductoaCambiar.class, id);
        } finally {
            em.close();
        }
    }

    public int getProductoaCambiarCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<ProductoaCambiar> rt = cq.from(ProductoaCambiar.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
