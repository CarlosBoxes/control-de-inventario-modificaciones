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
import EntidadesJPA.MateriaPrima;
import EntidadesJPA.BodegaProduccion;
import EntidadesJPA.InventarioMateriaPrima;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Ch470
 */
public class InventarioMateriaPrimaJpaController implements Serializable {

    public InventarioMateriaPrimaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(InventarioMateriaPrima inventarioMateriaPrima) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            MateriaPrima materiaPrimaidmateriaPrima = inventarioMateriaPrima.getMateriaPrimaidmateriaPrima();
            if (materiaPrimaidmateriaPrima != null) {
                materiaPrimaidmateriaPrima = em.getReference(materiaPrimaidmateriaPrima.getClass(), materiaPrimaidmateriaPrima.getIdmateriaPrima());
                inventarioMateriaPrima.setMateriaPrimaidmateriaPrima(materiaPrimaidmateriaPrima);
            }
            BodegaProduccion bodegaProduccionidbodegaProduccion = inventarioMateriaPrima.getBodegaProduccionidbodegaProduccion();
            if (bodegaProduccionidbodegaProduccion != null) {
                bodegaProduccionidbodegaProduccion = em.getReference(bodegaProduccionidbodegaProduccion.getClass(), bodegaProduccionidbodegaProduccion.getIdbodegaProduccion());
                inventarioMateriaPrima.setBodegaProduccionidbodegaProduccion(bodegaProduccionidbodegaProduccion);
            }
            em.persist(inventarioMateriaPrima);
            if (materiaPrimaidmateriaPrima != null) {
                materiaPrimaidmateriaPrima.getInventarioMateriaPrimaCollection().add(inventarioMateriaPrima);
                materiaPrimaidmateriaPrima = em.merge(materiaPrimaidmateriaPrima);
            }
            if (bodegaProduccionidbodegaProduccion != null) {
                bodegaProduccionidbodegaProduccion.getInventarioMateriaPrimaCollection().add(inventarioMateriaPrima);
                bodegaProduccionidbodegaProduccion = em.merge(bodegaProduccionidbodegaProduccion);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(InventarioMateriaPrima inventarioMateriaPrima) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            InventarioMateriaPrima persistentInventarioMateriaPrima = em.find(InventarioMateriaPrima.class, inventarioMateriaPrima.getIdInventarioMateriaPrima());
            MateriaPrima materiaPrimaidmateriaPrimaOld = persistentInventarioMateriaPrima.getMateriaPrimaidmateriaPrima();
            MateriaPrima materiaPrimaidmateriaPrimaNew = inventarioMateriaPrima.getMateriaPrimaidmateriaPrima();
            BodegaProduccion bodegaProduccionidbodegaProduccionOld = persistentInventarioMateriaPrima.getBodegaProduccionidbodegaProduccion();
            BodegaProduccion bodegaProduccionidbodegaProduccionNew = inventarioMateriaPrima.getBodegaProduccionidbodegaProduccion();
            if (materiaPrimaidmateriaPrimaNew != null) {
                materiaPrimaidmateriaPrimaNew = em.getReference(materiaPrimaidmateriaPrimaNew.getClass(), materiaPrimaidmateriaPrimaNew.getIdmateriaPrima());
                inventarioMateriaPrima.setMateriaPrimaidmateriaPrima(materiaPrimaidmateriaPrimaNew);
            }
            if (bodegaProduccionidbodegaProduccionNew != null) {
                bodegaProduccionidbodegaProduccionNew = em.getReference(bodegaProduccionidbodegaProduccionNew.getClass(), bodegaProduccionidbodegaProduccionNew.getIdbodegaProduccion());
                inventarioMateriaPrima.setBodegaProduccionidbodegaProduccion(bodegaProduccionidbodegaProduccionNew);
            }
            inventarioMateriaPrima = em.merge(inventarioMateriaPrima);
            if (materiaPrimaidmateriaPrimaOld != null && !materiaPrimaidmateriaPrimaOld.equals(materiaPrimaidmateriaPrimaNew)) {
                materiaPrimaidmateriaPrimaOld.getInventarioMateriaPrimaCollection().remove(inventarioMateriaPrima);
                materiaPrimaidmateriaPrimaOld = em.merge(materiaPrimaidmateriaPrimaOld);
            }
            if (materiaPrimaidmateriaPrimaNew != null && !materiaPrimaidmateriaPrimaNew.equals(materiaPrimaidmateriaPrimaOld)) {
                materiaPrimaidmateriaPrimaNew.getInventarioMateriaPrimaCollection().add(inventarioMateriaPrima);
                materiaPrimaidmateriaPrimaNew = em.merge(materiaPrimaidmateriaPrimaNew);
            }
            if (bodegaProduccionidbodegaProduccionOld != null && !bodegaProduccionidbodegaProduccionOld.equals(bodegaProduccionidbodegaProduccionNew)) {
                bodegaProduccionidbodegaProduccionOld.getInventarioMateriaPrimaCollection().remove(inventarioMateriaPrima);
                bodegaProduccionidbodegaProduccionOld = em.merge(bodegaProduccionidbodegaProduccionOld);
            }
            if (bodegaProduccionidbodegaProduccionNew != null && !bodegaProduccionidbodegaProduccionNew.equals(bodegaProduccionidbodegaProduccionOld)) {
                bodegaProduccionidbodegaProduccionNew.getInventarioMateriaPrimaCollection().add(inventarioMateriaPrima);
                bodegaProduccionidbodegaProduccionNew = em.merge(bodegaProduccionidbodegaProduccionNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = inventarioMateriaPrima.getIdInventarioMateriaPrima();
                if (findInventarioMateriaPrima(id) == null) {
                    throw new NonexistentEntityException("The inventarioMateriaPrima with id " + id + " no longer exists.");
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
            InventarioMateriaPrima inventarioMateriaPrima;
            try {
                inventarioMateriaPrima = em.getReference(InventarioMateriaPrima.class, id);
                inventarioMateriaPrima.getIdInventarioMateriaPrima();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The inventarioMateriaPrima with id " + id + " no longer exists.", enfe);
            }
            MateriaPrima materiaPrimaidmateriaPrima = inventarioMateriaPrima.getMateriaPrimaidmateriaPrima();
            if (materiaPrimaidmateriaPrima != null) {
                materiaPrimaidmateriaPrima.getInventarioMateriaPrimaCollection().remove(inventarioMateriaPrima);
                materiaPrimaidmateriaPrima = em.merge(materiaPrimaidmateriaPrima);
            }
            BodegaProduccion bodegaProduccionidbodegaProduccion = inventarioMateriaPrima.getBodegaProduccionidbodegaProduccion();
            if (bodegaProduccionidbodegaProduccion != null) {
                bodegaProduccionidbodegaProduccion.getInventarioMateriaPrimaCollection().remove(inventarioMateriaPrima);
                bodegaProduccionidbodegaProduccion = em.merge(bodegaProduccionidbodegaProduccion);
            }
            em.remove(inventarioMateriaPrima);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<InventarioMateriaPrima> findInventarioMateriaPrimaEntities() {
        return findInventarioMateriaPrimaEntities(true, -1, -1);
    }

    public List<InventarioMateriaPrima> findInventarioMateriaPrimaEntities(int maxResults, int firstResult) {
        return findInventarioMateriaPrimaEntities(false, maxResults, firstResult);
    }

    private List<InventarioMateriaPrima> findInventarioMateriaPrimaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(InventarioMateriaPrima.class));
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

    public InventarioMateriaPrima findInventarioMateriaPrima(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(InventarioMateriaPrima.class, id);
        } finally {
            em.close();
        }
    }

    public int getInventarioMateriaPrimaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<InventarioMateriaPrima> rt = cq.from(InventarioMateriaPrima.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
