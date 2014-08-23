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
import EntidadesJPA.Depositos;
import EntidadesJPA.ChequesClientes;
import EntidadesJPA.DescripcionLiquidacion;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Ch470
 */
public class DescripcionLiquidacionJpaController implements Serializable {

    public DescripcionLiquidacionJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(DescripcionLiquidacion descripcionLiquidacion) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Liquidacion liquidacionIdliquidacion = descripcionLiquidacion.getLiquidacionIdliquidacion();
            if (liquidacionIdliquidacion != null) {
                liquidacionIdliquidacion = em.getReference(liquidacionIdliquidacion.getClass(), liquidacionIdliquidacion.getIdliquidacion());
                descripcionLiquidacion.setLiquidacionIdliquidacion(liquidacionIdliquidacion);
            }
            Depositos depositosidDepositos = descripcionLiquidacion.getDepositosidDepositos();
            if (depositosidDepositos != null) {
                depositosidDepositos = em.getReference(depositosidDepositos.getClass(), depositosidDepositos.getIdDepositos());
                descripcionLiquidacion.setDepositosidDepositos(depositosidDepositos);
            }
            ChequesClientes chequesClientesIdchequesClientes = descripcionLiquidacion.getChequesClientesIdchequesClientes();
            if (chequesClientesIdchequesClientes != null) {
                chequesClientesIdchequesClientes = em.getReference(chequesClientesIdchequesClientes.getClass(), chequesClientesIdchequesClientes.getIdchequesClientes());
                descripcionLiquidacion.setChequesClientesIdchequesClientes(chequesClientesIdchequesClientes);
            }
            em.persist(descripcionLiquidacion);
            if (liquidacionIdliquidacion != null) {
                liquidacionIdliquidacion.getDescripcionLiquidacionCollection().add(descripcionLiquidacion);
                liquidacionIdliquidacion = em.merge(liquidacionIdliquidacion);
            }
            if (depositosidDepositos != null) {
                depositosidDepositos.getDescripcionLiquidacionCollection().add(descripcionLiquidacion);
                depositosidDepositos = em.merge(depositosidDepositos);
            }
            if (chequesClientesIdchequesClientes != null) {
                chequesClientesIdchequesClientes.getDescripcionLiquidacionCollection().add(descripcionLiquidacion);
                chequesClientesIdchequesClientes = em.merge(chequesClientesIdchequesClientes);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(DescripcionLiquidacion descripcionLiquidacion) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            DescripcionLiquidacion persistentDescripcionLiquidacion = em.find(DescripcionLiquidacion.class, descripcionLiquidacion.getIdDescripcionLiquidacion());
            Liquidacion liquidacionIdliquidacionOld = persistentDescripcionLiquidacion.getLiquidacionIdliquidacion();
            Liquidacion liquidacionIdliquidacionNew = descripcionLiquidacion.getLiquidacionIdliquidacion();
            Depositos depositosidDepositosOld = persistentDescripcionLiquidacion.getDepositosidDepositos();
            Depositos depositosidDepositosNew = descripcionLiquidacion.getDepositosidDepositos();
            ChequesClientes chequesClientesIdchequesClientesOld = persistentDescripcionLiquidacion.getChequesClientesIdchequesClientes();
            ChequesClientes chequesClientesIdchequesClientesNew = descripcionLiquidacion.getChequesClientesIdchequesClientes();
            if (liquidacionIdliquidacionNew != null) {
                liquidacionIdliquidacionNew = em.getReference(liquidacionIdliquidacionNew.getClass(), liquidacionIdliquidacionNew.getIdliquidacion());
                descripcionLiquidacion.setLiquidacionIdliquidacion(liquidacionIdliquidacionNew);
            }
            if (depositosidDepositosNew != null) {
                depositosidDepositosNew = em.getReference(depositosidDepositosNew.getClass(), depositosidDepositosNew.getIdDepositos());
                descripcionLiquidacion.setDepositosidDepositos(depositosidDepositosNew);
            }
            if (chequesClientesIdchequesClientesNew != null) {
                chequesClientesIdchequesClientesNew = em.getReference(chequesClientesIdchequesClientesNew.getClass(), chequesClientesIdchequesClientesNew.getIdchequesClientes());
                descripcionLiquidacion.setChequesClientesIdchequesClientes(chequesClientesIdchequesClientesNew);
            }
            descripcionLiquidacion = em.merge(descripcionLiquidacion);
            if (liquidacionIdliquidacionOld != null && !liquidacionIdliquidacionOld.equals(liquidacionIdliquidacionNew)) {
                liquidacionIdliquidacionOld.getDescripcionLiquidacionCollection().remove(descripcionLiquidacion);
                liquidacionIdliquidacionOld = em.merge(liquidacionIdliquidacionOld);
            }
            if (liquidacionIdliquidacionNew != null && !liquidacionIdliquidacionNew.equals(liquidacionIdliquidacionOld)) {
                liquidacionIdliquidacionNew.getDescripcionLiquidacionCollection().add(descripcionLiquidacion);
                liquidacionIdliquidacionNew = em.merge(liquidacionIdliquidacionNew);
            }
            if (depositosidDepositosOld != null && !depositosidDepositosOld.equals(depositosidDepositosNew)) {
                depositosidDepositosOld.getDescripcionLiquidacionCollection().remove(descripcionLiquidacion);
                depositosidDepositosOld = em.merge(depositosidDepositosOld);
            }
            if (depositosidDepositosNew != null && !depositosidDepositosNew.equals(depositosidDepositosOld)) {
                depositosidDepositosNew.getDescripcionLiquidacionCollection().add(descripcionLiquidacion);
                depositosidDepositosNew = em.merge(depositosidDepositosNew);
            }
            if (chequesClientesIdchequesClientesOld != null && !chequesClientesIdchequesClientesOld.equals(chequesClientesIdchequesClientesNew)) {
                chequesClientesIdchequesClientesOld.getDescripcionLiquidacionCollection().remove(descripcionLiquidacion);
                chequesClientesIdchequesClientesOld = em.merge(chequesClientesIdchequesClientesOld);
            }
            if (chequesClientesIdchequesClientesNew != null && !chequesClientesIdchequesClientesNew.equals(chequesClientesIdchequesClientesOld)) {
                chequesClientesIdchequesClientesNew.getDescripcionLiquidacionCollection().add(descripcionLiquidacion);
                chequesClientesIdchequesClientesNew = em.merge(chequesClientesIdchequesClientesNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = descripcionLiquidacion.getIdDescripcionLiquidacion();
                if (findDescripcionLiquidacion(id) == null) {
                    throw new NonexistentEntityException("The descripcionLiquidacion with id " + id + " no longer exists.");
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
            DescripcionLiquidacion descripcionLiquidacion;
            try {
                descripcionLiquidacion = em.getReference(DescripcionLiquidacion.class, id);
                descripcionLiquidacion.getIdDescripcionLiquidacion();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The descripcionLiquidacion with id " + id + " no longer exists.", enfe);
            }
            Liquidacion liquidacionIdliquidacion = descripcionLiquidacion.getLiquidacionIdliquidacion();
            if (liquidacionIdliquidacion != null) {
                liquidacionIdliquidacion.getDescripcionLiquidacionCollection().remove(descripcionLiquidacion);
                liquidacionIdliquidacion = em.merge(liquidacionIdliquidacion);
            }
            Depositos depositosidDepositos = descripcionLiquidacion.getDepositosidDepositos();
            if (depositosidDepositos != null) {
                depositosidDepositos.getDescripcionLiquidacionCollection().remove(descripcionLiquidacion);
                depositosidDepositos = em.merge(depositosidDepositos);
            }
            ChequesClientes chequesClientesIdchequesClientes = descripcionLiquidacion.getChequesClientesIdchequesClientes();
            if (chequesClientesIdchequesClientes != null) {
                chequesClientesIdchequesClientes.getDescripcionLiquidacionCollection().remove(descripcionLiquidacion);
                chequesClientesIdchequesClientes = em.merge(chequesClientesIdchequesClientes);
            }
            em.remove(descripcionLiquidacion);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<DescripcionLiquidacion> findDescripcionLiquidacionEntities() {
        return findDescripcionLiquidacionEntities(true, -1, -1);
    }

    public List<DescripcionLiquidacion> findDescripcionLiquidacionEntities(int maxResults, int firstResult) {
        return findDescripcionLiquidacionEntities(false, maxResults, firstResult);
    }

    private List<DescripcionLiquidacion> findDescripcionLiquidacionEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(DescripcionLiquidacion.class));
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

    public DescripcionLiquidacion findDescripcionLiquidacion(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(DescripcionLiquidacion.class, id);
        } finally {
            em.close();
        }
    }

    public int getDescripcionLiquidacionCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<DescripcionLiquidacion> rt = cq.from(DescripcionLiquidacion.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
