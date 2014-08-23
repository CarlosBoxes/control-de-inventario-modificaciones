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
import EntidadesJPA.Bancos;
import EntidadesJPA.DescripcionLiquidacion;
import java.util.ArrayList;
import java.util.Collection;
import EntidadesJPA.CuentaporCobrar;
import EntidadesJPA.Depositos;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Ch470
 */
public class DepositosJpaController implements Serializable {

    public DepositosJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Depositos depositos) {
        if (depositos.getDescripcionLiquidacionCollection() == null) {
            depositos.setDescripcionLiquidacionCollection(new ArrayList<DescripcionLiquidacion>());
        }
        if (depositos.getCuentaporcobrarCollection() == null) {
            depositos.setCuentaporcobrarCollection(new ArrayList<CuentaporCobrar>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Bancos bancosIdbancos = depositos.getBancosIdbancos();
            if (bancosIdbancos != null) {
                bancosIdbancos = em.getReference(bancosIdbancos.getClass(), bancosIdbancos.getIdbancos());
                depositos.setBancosIdbancos(bancosIdbancos);
            }
            Collection<DescripcionLiquidacion> attachedDescripcionLiquidacionCollection = new ArrayList<DescripcionLiquidacion>();
            for (DescripcionLiquidacion descripcionLiquidacionCollectionDescripcionLiquidacionToAttach : depositos.getDescripcionLiquidacionCollection()) {
                descripcionLiquidacionCollectionDescripcionLiquidacionToAttach = em.getReference(descripcionLiquidacionCollectionDescripcionLiquidacionToAttach.getClass(), descripcionLiquidacionCollectionDescripcionLiquidacionToAttach.getIdDescripcionLiquidacion());
                attachedDescripcionLiquidacionCollection.add(descripcionLiquidacionCollectionDescripcionLiquidacionToAttach);
            }
            depositos.setDescripcionLiquidacionCollection(attachedDescripcionLiquidacionCollection);
            Collection<CuentaporCobrar> attachedCuentaporcobrarCollection = new ArrayList<CuentaporCobrar>();
            for (CuentaporCobrar cuentaporcobrarCollectionCuentaporCobrarToAttach : depositos.getCuentaporcobrarCollection()) {
                cuentaporcobrarCollectionCuentaporCobrarToAttach = em.getReference(cuentaporcobrarCollectionCuentaporCobrarToAttach.getClass(), cuentaporcobrarCollectionCuentaporCobrarToAttach.getIdcuenta());
                attachedCuentaporcobrarCollection.add(cuentaporcobrarCollectionCuentaporCobrarToAttach);
            }
            depositos.setCuentaporcobrarCollection(attachedCuentaporcobrarCollection);
            em.persist(depositos);
            if (bancosIdbancos != null) {
                bancosIdbancos.getDepositosCollection().add(depositos);
                bancosIdbancos = em.merge(bancosIdbancos);
            }
            for (DescripcionLiquidacion descripcionLiquidacionCollectionDescripcionLiquidacion : depositos.getDescripcionLiquidacionCollection()) {
                Depositos oldDepositosidDepositosOfDescripcionLiquidacionCollectionDescripcionLiquidacion = descripcionLiquidacionCollectionDescripcionLiquidacion.getDepositosidDepositos();
                descripcionLiquidacionCollectionDescripcionLiquidacion.setDepositosidDepositos(depositos);
                descripcionLiquidacionCollectionDescripcionLiquidacion = em.merge(descripcionLiquidacionCollectionDescripcionLiquidacion);
                if (oldDepositosidDepositosOfDescripcionLiquidacionCollectionDescripcionLiquidacion != null) {
                    oldDepositosidDepositosOfDescripcionLiquidacionCollectionDescripcionLiquidacion.getDescripcionLiquidacionCollection().remove(descripcionLiquidacionCollectionDescripcionLiquidacion);
                    oldDepositosidDepositosOfDescripcionLiquidacionCollectionDescripcionLiquidacion = em.merge(oldDepositosidDepositosOfDescripcionLiquidacionCollectionDescripcionLiquidacion);
                }
            }
            for (CuentaporCobrar cuentaporcobrarCollectionCuentaporCobrar : depositos.getCuentaporcobrarCollection()) {
                Depositos oldDepositosidDepositosOfCuentaporcobrarCollectionCuentaporCobrar = cuentaporcobrarCollectionCuentaporCobrar.getDepositosidDepositos();
                cuentaporcobrarCollectionCuentaporCobrar.setDepositosidDepositos(depositos);
                cuentaporcobrarCollectionCuentaporCobrar = em.merge(cuentaporcobrarCollectionCuentaporCobrar);
                if (oldDepositosidDepositosOfCuentaporcobrarCollectionCuentaporCobrar != null) {
                    oldDepositosidDepositosOfCuentaporcobrarCollectionCuentaporCobrar.getCuentaporcobrarCollection().remove(cuentaporcobrarCollectionCuentaporCobrar);
                    oldDepositosidDepositosOfCuentaporcobrarCollectionCuentaporCobrar = em.merge(oldDepositosidDepositosOfCuentaporcobrarCollectionCuentaporCobrar);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Depositos depositos) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Depositos persistentDepositos = em.find(Depositos.class, depositos.getIdDepositos());
            Bancos bancosIdbancosOld = persistentDepositos.getBancosIdbancos();
            Bancos bancosIdbancosNew = depositos.getBancosIdbancos();
            Collection<DescripcionLiquidacion> descripcionLiquidacionCollectionOld = persistentDepositos.getDescripcionLiquidacionCollection();
            Collection<DescripcionLiquidacion> descripcionLiquidacionCollectionNew = depositos.getDescripcionLiquidacionCollection();
            Collection<CuentaporCobrar> cuentaporcobrarCollectionOld = persistentDepositos.getCuentaporcobrarCollection();
            Collection<CuentaporCobrar> cuentaporcobrarCollectionNew = depositos.getCuentaporcobrarCollection();
            if (bancosIdbancosNew != null) {
                bancosIdbancosNew = em.getReference(bancosIdbancosNew.getClass(), bancosIdbancosNew.getIdbancos());
                depositos.setBancosIdbancos(bancosIdbancosNew);
            }
            Collection<DescripcionLiquidacion> attachedDescripcionLiquidacionCollectionNew = new ArrayList<DescripcionLiquidacion>();
            for (DescripcionLiquidacion descripcionLiquidacionCollectionNewDescripcionLiquidacionToAttach : descripcionLiquidacionCollectionNew) {
                descripcionLiquidacionCollectionNewDescripcionLiquidacionToAttach = em.getReference(descripcionLiquidacionCollectionNewDescripcionLiquidacionToAttach.getClass(), descripcionLiquidacionCollectionNewDescripcionLiquidacionToAttach.getIdDescripcionLiquidacion());
                attachedDescripcionLiquidacionCollectionNew.add(descripcionLiquidacionCollectionNewDescripcionLiquidacionToAttach);
            }
            descripcionLiquidacionCollectionNew = attachedDescripcionLiquidacionCollectionNew;
            depositos.setDescripcionLiquidacionCollection(descripcionLiquidacionCollectionNew);
            Collection<CuentaporCobrar> attachedCuentaporcobrarCollectionNew = new ArrayList<CuentaporCobrar>();
            for (CuentaporCobrar cuentaporcobrarCollectionNewCuentaporCobrarToAttach : cuentaporcobrarCollectionNew) {
                cuentaporcobrarCollectionNewCuentaporCobrarToAttach = em.getReference(cuentaporcobrarCollectionNewCuentaporCobrarToAttach.getClass(), cuentaporcobrarCollectionNewCuentaporCobrarToAttach.getIdcuenta());
                attachedCuentaporcobrarCollectionNew.add(cuentaporcobrarCollectionNewCuentaporCobrarToAttach);
            }
            cuentaporcobrarCollectionNew = attachedCuentaporcobrarCollectionNew;
            depositos.setCuentaporcobrarCollection(cuentaporcobrarCollectionNew);
            depositos = em.merge(depositos);
            if (bancosIdbancosOld != null && !bancosIdbancosOld.equals(bancosIdbancosNew)) {
                bancosIdbancosOld.getDepositosCollection().remove(depositos);
                bancosIdbancosOld = em.merge(bancosIdbancosOld);
            }
            if (bancosIdbancosNew != null && !bancosIdbancosNew.equals(bancosIdbancosOld)) {
                bancosIdbancosNew.getDepositosCollection().add(depositos);
                bancosIdbancosNew = em.merge(bancosIdbancosNew);
            }
            for (DescripcionLiquidacion descripcionLiquidacionCollectionOldDescripcionLiquidacion : descripcionLiquidacionCollectionOld) {
                if (!descripcionLiquidacionCollectionNew.contains(descripcionLiquidacionCollectionOldDescripcionLiquidacion)) {
                    descripcionLiquidacionCollectionOldDescripcionLiquidacion.setDepositosidDepositos(null);
                    descripcionLiquidacionCollectionOldDescripcionLiquidacion = em.merge(descripcionLiquidacionCollectionOldDescripcionLiquidacion);
                }
            }
            for (DescripcionLiquidacion descripcionLiquidacionCollectionNewDescripcionLiquidacion : descripcionLiquidacionCollectionNew) {
                if (!descripcionLiquidacionCollectionOld.contains(descripcionLiquidacionCollectionNewDescripcionLiquidacion)) {
                    Depositos oldDepositosidDepositosOfDescripcionLiquidacionCollectionNewDescripcionLiquidacion = descripcionLiquidacionCollectionNewDescripcionLiquidacion.getDepositosidDepositos();
                    descripcionLiquidacionCollectionNewDescripcionLiquidacion.setDepositosidDepositos(depositos);
                    descripcionLiquidacionCollectionNewDescripcionLiquidacion = em.merge(descripcionLiquidacionCollectionNewDescripcionLiquidacion);
                    if (oldDepositosidDepositosOfDescripcionLiquidacionCollectionNewDescripcionLiquidacion != null && !oldDepositosidDepositosOfDescripcionLiquidacionCollectionNewDescripcionLiquidacion.equals(depositos)) {
                        oldDepositosidDepositosOfDescripcionLiquidacionCollectionNewDescripcionLiquidacion.getDescripcionLiquidacionCollection().remove(descripcionLiquidacionCollectionNewDescripcionLiquidacion);
                        oldDepositosidDepositosOfDescripcionLiquidacionCollectionNewDescripcionLiquidacion = em.merge(oldDepositosidDepositosOfDescripcionLiquidacionCollectionNewDescripcionLiquidacion);
                    }
                }
            }
            for (CuentaporCobrar cuentaporcobrarCollectionOldCuentaporCobrar : cuentaporcobrarCollectionOld) {
                if (!cuentaporcobrarCollectionNew.contains(cuentaporcobrarCollectionOldCuentaporCobrar)) {
                    cuentaporcobrarCollectionOldCuentaporCobrar.setDepositosidDepositos(null);
                    cuentaporcobrarCollectionOldCuentaporCobrar = em.merge(cuentaporcobrarCollectionOldCuentaporCobrar);
                }
            }
            for (CuentaporCobrar cuentaporcobrarCollectionNewCuentaporCobrar : cuentaporcobrarCollectionNew) {
                if (!cuentaporcobrarCollectionOld.contains(cuentaporcobrarCollectionNewCuentaporCobrar)) {
                    Depositos oldDepositosidDepositosOfCuentaporcobrarCollectionNewCuentaporCobrar = cuentaporcobrarCollectionNewCuentaporCobrar.getDepositosidDepositos();
                    cuentaporcobrarCollectionNewCuentaporCobrar.setDepositosidDepositos(depositos);
                    cuentaporcobrarCollectionNewCuentaporCobrar = em.merge(cuentaporcobrarCollectionNewCuentaporCobrar);
                    if (oldDepositosidDepositosOfCuentaporcobrarCollectionNewCuentaporCobrar != null && !oldDepositosidDepositosOfCuentaporcobrarCollectionNewCuentaporCobrar.equals(depositos)) {
                        oldDepositosidDepositosOfCuentaporcobrarCollectionNewCuentaporCobrar.getCuentaporcobrarCollection().remove(cuentaporcobrarCollectionNewCuentaporCobrar);
                        oldDepositosidDepositosOfCuentaporcobrarCollectionNewCuentaporCobrar = em.merge(oldDepositosidDepositosOfCuentaporcobrarCollectionNewCuentaporCobrar);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = depositos.getIdDepositos();
                if (findDepositos(id) == null) {
                    throw new NonexistentEntityException("The depositos with id " + id + " no longer exists.");
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
            Depositos depositos;
            try {
                depositos = em.getReference(Depositos.class, id);
                depositos.getIdDepositos();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The depositos with id " + id + " no longer exists.", enfe);
            }
            Bancos bancosIdbancos = depositos.getBancosIdbancos();
            if (bancosIdbancos != null) {
                bancosIdbancos.getDepositosCollection().remove(depositos);
                bancosIdbancos = em.merge(bancosIdbancos);
            }
            Collection<DescripcionLiquidacion> descripcionLiquidacionCollection = depositos.getDescripcionLiquidacionCollection();
            for (DescripcionLiquidacion descripcionLiquidacionCollectionDescripcionLiquidacion : descripcionLiquidacionCollection) {
                descripcionLiquidacionCollectionDescripcionLiquidacion.setDepositosidDepositos(null);
                descripcionLiquidacionCollectionDescripcionLiquidacion = em.merge(descripcionLiquidacionCollectionDescripcionLiquidacion);
            }
            Collection<CuentaporCobrar> cuentaporcobrarCollection = depositos.getCuentaporcobrarCollection();
            for (CuentaporCobrar cuentaporcobrarCollectionCuentaporCobrar : cuentaporcobrarCollection) {
                cuentaporcobrarCollectionCuentaporCobrar.setDepositosidDepositos(null);
                cuentaporcobrarCollectionCuentaporCobrar = em.merge(cuentaporcobrarCollectionCuentaporCobrar);
            }
            em.remove(depositos);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Depositos> findDepositosEntities() {
        return findDepositosEntities(true, -1, -1);
    }

    public List<Depositos> findDepositosEntities(int maxResults, int firstResult) {
        return findDepositosEntities(false, maxResults, firstResult);
    }

    private List<Depositos> findDepositosEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Depositos.class));
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

    public Depositos findDepositos(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Depositos.class, id);
        } finally {
            em.close();
        }
    }

    public int getDepositosCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Depositos> rt = cq.from(Depositos.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
