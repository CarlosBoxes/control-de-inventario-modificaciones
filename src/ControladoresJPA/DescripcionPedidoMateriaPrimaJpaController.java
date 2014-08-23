/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ControladoresJPA;

import ControladoresJPA.exceptions.NonexistentEntityException;
import EntidadesJPA.DescripcionPedidoMateriaPrima;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import EntidadesJPA.PedidoMateriaPrima;
import EntidadesJPA.MateriaPrima;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Ch470
 */
public class DescripcionPedidoMateriaPrimaJpaController implements Serializable {

    public DescripcionPedidoMateriaPrimaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(DescripcionPedidoMateriaPrima descripcionPedidoMateriaPrima) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            PedidoMateriaPrima pedidomateriaprimaidpedidoMP = descripcionPedidoMateriaPrima.getPedidomateriaprimaidpedidoMP();
            if (pedidomateriaprimaidpedidoMP != null) {
                pedidomateriaprimaidpedidoMP = em.getReference(pedidomateriaprimaidpedidoMP.getClass(), pedidomateriaprimaidpedidoMP.getIdpedidoMP());
                descripcionPedidoMateriaPrima.setPedidomateriaprimaidpedidoMP(pedidomateriaprimaidpedidoMP);
            }
            MateriaPrima materiaPrimaidmateriaPrima = descripcionPedidoMateriaPrima.getMateriaPrimaidmateriaPrima();
            if (materiaPrimaidmateriaPrima != null) {
                materiaPrimaidmateriaPrima = em.getReference(materiaPrimaidmateriaPrima.getClass(), materiaPrimaidmateriaPrima.getIdmateriaPrima());
                descripcionPedidoMateriaPrima.setMateriaPrimaidmateriaPrima(materiaPrimaidmateriaPrima);
            }
            em.persist(descripcionPedidoMateriaPrima);
            if (pedidomateriaprimaidpedidoMP != null) {
                pedidomateriaprimaidpedidoMP.getDescripcionPedidoMateriaPrimaCollection().add(descripcionPedidoMateriaPrima);
                pedidomateriaprimaidpedidoMP = em.merge(pedidomateriaprimaidpedidoMP);
            }
            if (materiaPrimaidmateriaPrima != null) {
                materiaPrimaidmateriaPrima.getDescripcionPedidoMateriaPrimaCollection().add(descripcionPedidoMateriaPrima);
                materiaPrimaidmateriaPrima = em.merge(materiaPrimaidmateriaPrima);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(DescripcionPedidoMateriaPrima descripcionPedidoMateriaPrima) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            DescripcionPedidoMateriaPrima persistentDescripcionPedidoMateriaPrima = em.find(DescripcionPedidoMateriaPrima.class, descripcionPedidoMateriaPrima.getIddescripcionMP());
            PedidoMateriaPrima pedidomateriaprimaidpedidoMPOld = persistentDescripcionPedidoMateriaPrima.getPedidomateriaprimaidpedidoMP();
            PedidoMateriaPrima pedidomateriaprimaidpedidoMPNew = descripcionPedidoMateriaPrima.getPedidomateriaprimaidpedidoMP();
            MateriaPrima materiaPrimaidmateriaPrimaOld = persistentDescripcionPedidoMateriaPrima.getMateriaPrimaidmateriaPrima();
            MateriaPrima materiaPrimaidmateriaPrimaNew = descripcionPedidoMateriaPrima.getMateriaPrimaidmateriaPrima();
            if (pedidomateriaprimaidpedidoMPNew != null) {
                pedidomateriaprimaidpedidoMPNew = em.getReference(pedidomateriaprimaidpedidoMPNew.getClass(), pedidomateriaprimaidpedidoMPNew.getIdpedidoMP());
                descripcionPedidoMateriaPrima.setPedidomateriaprimaidpedidoMP(pedidomateriaprimaidpedidoMPNew);
            }
            if (materiaPrimaidmateriaPrimaNew != null) {
                materiaPrimaidmateriaPrimaNew = em.getReference(materiaPrimaidmateriaPrimaNew.getClass(), materiaPrimaidmateriaPrimaNew.getIdmateriaPrima());
                descripcionPedidoMateriaPrima.setMateriaPrimaidmateriaPrima(materiaPrimaidmateriaPrimaNew);
            }
            descripcionPedidoMateriaPrima = em.merge(descripcionPedidoMateriaPrima);
            if (pedidomateriaprimaidpedidoMPOld != null && !pedidomateriaprimaidpedidoMPOld.equals(pedidomateriaprimaidpedidoMPNew)) {
                pedidomateriaprimaidpedidoMPOld.getDescripcionPedidoMateriaPrimaCollection().remove(descripcionPedidoMateriaPrima);
                pedidomateriaprimaidpedidoMPOld = em.merge(pedidomateriaprimaidpedidoMPOld);
            }
            if (pedidomateriaprimaidpedidoMPNew != null && !pedidomateriaprimaidpedidoMPNew.equals(pedidomateriaprimaidpedidoMPOld)) {
                pedidomateriaprimaidpedidoMPNew.getDescripcionPedidoMateriaPrimaCollection().add(descripcionPedidoMateriaPrima);
                pedidomateriaprimaidpedidoMPNew = em.merge(pedidomateriaprimaidpedidoMPNew);
            }
            if (materiaPrimaidmateriaPrimaOld != null && !materiaPrimaidmateriaPrimaOld.equals(materiaPrimaidmateriaPrimaNew)) {
                materiaPrimaidmateriaPrimaOld.getDescripcionPedidoMateriaPrimaCollection().remove(descripcionPedidoMateriaPrima);
                materiaPrimaidmateriaPrimaOld = em.merge(materiaPrimaidmateriaPrimaOld);
            }
            if (materiaPrimaidmateriaPrimaNew != null && !materiaPrimaidmateriaPrimaNew.equals(materiaPrimaidmateriaPrimaOld)) {
                materiaPrimaidmateriaPrimaNew.getDescripcionPedidoMateriaPrimaCollection().add(descripcionPedidoMateriaPrima);
                materiaPrimaidmateriaPrimaNew = em.merge(materiaPrimaidmateriaPrimaNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = descripcionPedidoMateriaPrima.getIddescripcionMP();
                if (findDescripcionPedidoMateriaPrima(id) == null) {
                    throw new NonexistentEntityException("The descripcionPedidoMateriaPrima with id " + id + " no longer exists.");
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
            DescripcionPedidoMateriaPrima descripcionPedidoMateriaPrima;
            try {
                descripcionPedidoMateriaPrima = em.getReference(DescripcionPedidoMateriaPrima.class, id);
                descripcionPedidoMateriaPrima.getIddescripcionMP();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The descripcionPedidoMateriaPrima with id " + id + " no longer exists.", enfe);
            }
            PedidoMateriaPrima pedidomateriaprimaidpedidoMP = descripcionPedidoMateriaPrima.getPedidomateriaprimaidpedidoMP();
            if (pedidomateriaprimaidpedidoMP != null) {
                pedidomateriaprimaidpedidoMP.getDescripcionPedidoMateriaPrimaCollection().remove(descripcionPedidoMateriaPrima);
                pedidomateriaprimaidpedidoMP = em.merge(pedidomateriaprimaidpedidoMP);
            }
            MateriaPrima materiaPrimaidmateriaPrima = descripcionPedidoMateriaPrima.getMateriaPrimaidmateriaPrima();
            if (materiaPrimaidmateriaPrima != null) {
                materiaPrimaidmateriaPrima.getDescripcionPedidoMateriaPrimaCollection().remove(descripcionPedidoMateriaPrima);
                materiaPrimaidmateriaPrima = em.merge(materiaPrimaidmateriaPrima);
            }
            em.remove(descripcionPedidoMateriaPrima);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<DescripcionPedidoMateriaPrima> findDescripcionPedidoMateriaPrimaEntities() {
        return findDescripcionPedidoMateriaPrimaEntities(true, -1, -1);
    }

    public List<DescripcionPedidoMateriaPrima> findDescripcionPedidoMateriaPrimaEntities(int maxResults, int firstResult) {
        return findDescripcionPedidoMateriaPrimaEntities(false, maxResults, firstResult);
    }

    private List<DescripcionPedidoMateriaPrima> findDescripcionPedidoMateriaPrimaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(DescripcionPedidoMateriaPrima.class));
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

    public DescripcionPedidoMateriaPrima findDescripcionPedidoMateriaPrima(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(DescripcionPedidoMateriaPrima.class, id);
        } finally {
            em.close();
        }
    }

    public int getDescripcionPedidoMateriaPrimaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<DescripcionPedidoMateriaPrima> rt = cq.from(DescripcionPedidoMateriaPrima.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
