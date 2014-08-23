/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ControladoresJPA;

import ControladoresJPA.exceptions.IllegalOrphanException;
import ControladoresJPA.exceptions.NonexistentEntityException;
import EntidadesJPA.Bancos;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import EntidadesJPA.ChequesProveedores;
import java.util.ArrayList;
import java.util.Collection;
import EntidadesJPA.Depositos;
import EntidadesJPA.ChequesClientes;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Ch470
 */
public class BancosJpaController implements Serializable {

    public BancosJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Bancos bancos) {
        if (bancos.getChequesProveedoresCollection() == null) {
            bancos.setChequesProveedoresCollection(new ArrayList<ChequesProveedores>());
        }
        if (bancos.getDepositosCollection() == null) {
            bancos.setDepositosCollection(new ArrayList<Depositos>());
        }
        if (bancos.getChequesClientesCollection() == null) {
            bancos.setChequesClientesCollection(new ArrayList<ChequesClientes>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<ChequesProveedores> attachedChequesProveedoresCollection = new ArrayList<ChequesProveedores>();
            for (ChequesProveedores chequesProveedoresCollectionChequesProveedoresToAttach : bancos.getChequesProveedoresCollection()) {
                chequesProveedoresCollectionChequesProveedoresToAttach = em.getReference(chequesProveedoresCollectionChequesProveedoresToAttach.getClass(), chequesProveedoresCollectionChequesProveedoresToAttach.getIdchequesProveedores());
                attachedChequesProveedoresCollection.add(chequesProveedoresCollectionChequesProveedoresToAttach);
            }
            bancos.setChequesProveedoresCollection(attachedChequesProveedoresCollection);
            Collection<Depositos> attachedDepositosCollection = new ArrayList<Depositos>();
            for (Depositos depositosCollectionDepositosToAttach : bancos.getDepositosCollection()) {
                depositosCollectionDepositosToAttach = em.getReference(depositosCollectionDepositosToAttach.getClass(), depositosCollectionDepositosToAttach.getIdDepositos());
                attachedDepositosCollection.add(depositosCollectionDepositosToAttach);
            }
            bancos.setDepositosCollection(attachedDepositosCollection);
            Collection<ChequesClientes> attachedChequesClientesCollection = new ArrayList<ChequesClientes>();
            for (ChequesClientes chequesClientesCollectionChequesClientesToAttach : bancos.getChequesClientesCollection()) {
                chequesClientesCollectionChequesClientesToAttach = em.getReference(chequesClientesCollectionChequesClientesToAttach.getClass(), chequesClientesCollectionChequesClientesToAttach.getIdchequesClientes());
                attachedChequesClientesCollection.add(chequesClientesCollectionChequesClientesToAttach);
            }
            bancos.setChequesClientesCollection(attachedChequesClientesCollection);
            em.persist(bancos);
            for (ChequesProveedores chequesProveedoresCollectionChequesProveedores : bancos.getChequesProveedoresCollection()) {
                Bancos oldBancosIdbancosOfChequesProveedoresCollectionChequesProveedores = chequesProveedoresCollectionChequesProveedores.getBancosIdbancos();
                chequesProveedoresCollectionChequesProveedores.setBancosIdbancos(bancos);
                chequesProveedoresCollectionChequesProveedores = em.merge(chequesProveedoresCollectionChequesProveedores);
                if (oldBancosIdbancosOfChequesProveedoresCollectionChequesProveedores != null) {
                    oldBancosIdbancosOfChequesProveedoresCollectionChequesProveedores.getChequesProveedoresCollection().remove(chequesProveedoresCollectionChequesProveedores);
                    oldBancosIdbancosOfChequesProveedoresCollectionChequesProveedores = em.merge(oldBancosIdbancosOfChequesProveedoresCollectionChequesProveedores);
                }
            }
            for (Depositos depositosCollectionDepositos : bancos.getDepositosCollection()) {
                Bancos oldBancosIdbancosOfDepositosCollectionDepositos = depositosCollectionDepositos.getBancosIdbancos();
                depositosCollectionDepositos.setBancosIdbancos(bancos);
                depositosCollectionDepositos = em.merge(depositosCollectionDepositos);
                if (oldBancosIdbancosOfDepositosCollectionDepositos != null) {
                    oldBancosIdbancosOfDepositosCollectionDepositos.getDepositosCollection().remove(depositosCollectionDepositos);
                    oldBancosIdbancosOfDepositosCollectionDepositos = em.merge(oldBancosIdbancosOfDepositosCollectionDepositos);
                }
            }
            for (ChequesClientes chequesClientesCollectionChequesClientes : bancos.getChequesClientesCollection()) {
                Bancos oldBancosIdbancosOfChequesClientesCollectionChequesClientes = chequesClientesCollectionChequesClientes.getBancosIdbancos();
                chequesClientesCollectionChequesClientes.setBancosIdbancos(bancos);
                chequesClientesCollectionChequesClientes = em.merge(chequesClientesCollectionChequesClientes);
                if (oldBancosIdbancosOfChequesClientesCollectionChequesClientes != null) {
                    oldBancosIdbancosOfChequesClientesCollectionChequesClientes.getChequesClientesCollection().remove(chequesClientesCollectionChequesClientes);
                    oldBancosIdbancosOfChequesClientesCollectionChequesClientes = em.merge(oldBancosIdbancosOfChequesClientesCollectionChequesClientes);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Bancos bancos) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Bancos persistentBancos = em.find(Bancos.class, bancos.getIdbancos());
            Collection<ChequesProveedores> chequesProveedoresCollectionOld = persistentBancos.getChequesProveedoresCollection();
            Collection<ChequesProveedores> chequesProveedoresCollectionNew = bancos.getChequesProveedoresCollection();
            Collection<Depositos> depositosCollectionOld = persistentBancos.getDepositosCollection();
            Collection<Depositos> depositosCollectionNew = bancos.getDepositosCollection();
            Collection<ChequesClientes> chequesClientesCollectionOld = persistentBancos.getChequesClientesCollection();
            Collection<ChequesClientes> chequesClientesCollectionNew = bancos.getChequesClientesCollection();
            List<String> illegalOrphanMessages = null;
            for (ChequesProveedores chequesProveedoresCollectionOldChequesProveedores : chequesProveedoresCollectionOld) {
                if (!chequesProveedoresCollectionNew.contains(chequesProveedoresCollectionOldChequesProveedores)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain ChequesProveedores " + chequesProveedoresCollectionOldChequesProveedores + " since its bancosIdbancos field is not nullable.");
                }
            }
            for (Depositos depositosCollectionOldDepositos : depositosCollectionOld) {
                if (!depositosCollectionNew.contains(depositosCollectionOldDepositos)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Depositos " + depositosCollectionOldDepositos + " since its bancosIdbancos field is not nullable.");
                }
            }
            for (ChequesClientes chequesClientesCollectionOldChequesClientes : chequesClientesCollectionOld) {
                if (!chequesClientesCollectionNew.contains(chequesClientesCollectionOldChequesClientes)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain ChequesClientes " + chequesClientesCollectionOldChequesClientes + " since its bancosIdbancos field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Collection<ChequesProveedores> attachedChequesProveedoresCollectionNew = new ArrayList<ChequesProveedores>();
            for (ChequesProveedores chequesProveedoresCollectionNewChequesProveedoresToAttach : chequesProveedoresCollectionNew) {
                chequesProveedoresCollectionNewChequesProveedoresToAttach = em.getReference(chequesProveedoresCollectionNewChequesProveedoresToAttach.getClass(), chequesProveedoresCollectionNewChequesProveedoresToAttach.getIdchequesProveedores());
                attachedChequesProveedoresCollectionNew.add(chequesProveedoresCollectionNewChequesProveedoresToAttach);
            }
            chequesProveedoresCollectionNew = attachedChequesProveedoresCollectionNew;
            bancos.setChequesProveedoresCollection(chequesProveedoresCollectionNew);
            Collection<Depositos> attachedDepositosCollectionNew = new ArrayList<Depositos>();
            for (Depositos depositosCollectionNewDepositosToAttach : depositosCollectionNew) {
                depositosCollectionNewDepositosToAttach = em.getReference(depositosCollectionNewDepositosToAttach.getClass(), depositosCollectionNewDepositosToAttach.getIdDepositos());
                attachedDepositosCollectionNew.add(depositosCollectionNewDepositosToAttach);
            }
            depositosCollectionNew = attachedDepositosCollectionNew;
            bancos.setDepositosCollection(depositosCollectionNew);
            Collection<ChequesClientes> attachedChequesClientesCollectionNew = new ArrayList<ChequesClientes>();
            for (ChequesClientes chequesClientesCollectionNewChequesClientesToAttach : chequesClientesCollectionNew) {
                chequesClientesCollectionNewChequesClientesToAttach = em.getReference(chequesClientesCollectionNewChequesClientesToAttach.getClass(), chequesClientesCollectionNewChequesClientesToAttach.getIdchequesClientes());
                attachedChequesClientesCollectionNew.add(chequesClientesCollectionNewChequesClientesToAttach);
            }
            chequesClientesCollectionNew = attachedChequesClientesCollectionNew;
            bancos.setChequesClientesCollection(chequesClientesCollectionNew);
            bancos = em.merge(bancos);
            for (ChequesProveedores chequesProveedoresCollectionNewChequesProveedores : chequesProveedoresCollectionNew) {
                if (!chequesProveedoresCollectionOld.contains(chequesProveedoresCollectionNewChequesProveedores)) {
                    Bancos oldBancosIdbancosOfChequesProveedoresCollectionNewChequesProveedores = chequesProveedoresCollectionNewChequesProveedores.getBancosIdbancos();
                    chequesProveedoresCollectionNewChequesProveedores.setBancosIdbancos(bancos);
                    chequesProveedoresCollectionNewChequesProveedores = em.merge(chequesProveedoresCollectionNewChequesProveedores);
                    if (oldBancosIdbancosOfChequesProveedoresCollectionNewChequesProveedores != null && !oldBancosIdbancosOfChequesProveedoresCollectionNewChequesProveedores.equals(bancos)) {
                        oldBancosIdbancosOfChequesProveedoresCollectionNewChequesProveedores.getChequesProveedoresCollection().remove(chequesProveedoresCollectionNewChequesProveedores);
                        oldBancosIdbancosOfChequesProveedoresCollectionNewChequesProveedores = em.merge(oldBancosIdbancosOfChequesProveedoresCollectionNewChequesProveedores);
                    }
                }
            }
            for (Depositos depositosCollectionNewDepositos : depositosCollectionNew) {
                if (!depositosCollectionOld.contains(depositosCollectionNewDepositos)) {
                    Bancos oldBancosIdbancosOfDepositosCollectionNewDepositos = depositosCollectionNewDepositos.getBancosIdbancos();
                    depositosCollectionNewDepositos.setBancosIdbancos(bancos);
                    depositosCollectionNewDepositos = em.merge(depositosCollectionNewDepositos);
                    if (oldBancosIdbancosOfDepositosCollectionNewDepositos != null && !oldBancosIdbancosOfDepositosCollectionNewDepositos.equals(bancos)) {
                        oldBancosIdbancosOfDepositosCollectionNewDepositos.getDepositosCollection().remove(depositosCollectionNewDepositos);
                        oldBancosIdbancosOfDepositosCollectionNewDepositos = em.merge(oldBancosIdbancosOfDepositosCollectionNewDepositos);
                    }
                }
            }
            for (ChequesClientes chequesClientesCollectionNewChequesClientes : chequesClientesCollectionNew) {
                if (!chequesClientesCollectionOld.contains(chequesClientesCollectionNewChequesClientes)) {
                    Bancos oldBancosIdbancosOfChequesClientesCollectionNewChequesClientes = chequesClientesCollectionNewChequesClientes.getBancosIdbancos();
                    chequesClientesCollectionNewChequesClientes.setBancosIdbancos(bancos);
                    chequesClientesCollectionNewChequesClientes = em.merge(chequesClientesCollectionNewChequesClientes);
                    if (oldBancosIdbancosOfChequesClientesCollectionNewChequesClientes != null && !oldBancosIdbancosOfChequesClientesCollectionNewChequesClientes.equals(bancos)) {
                        oldBancosIdbancosOfChequesClientesCollectionNewChequesClientes.getChequesClientesCollection().remove(chequesClientesCollectionNewChequesClientes);
                        oldBancosIdbancosOfChequesClientesCollectionNewChequesClientes = em.merge(oldBancosIdbancosOfChequesClientesCollectionNewChequesClientes);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = bancos.getIdbancos();
                if (findBancos(id) == null) {
                    throw new NonexistentEntityException("The bancos with id " + id + " no longer exists.");
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
            Bancos bancos;
            try {
                bancos = em.getReference(Bancos.class, id);
                bancos.getIdbancos();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The bancos with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<ChequesProveedores> chequesProveedoresCollectionOrphanCheck = bancos.getChequesProveedoresCollection();
            for (ChequesProveedores chequesProveedoresCollectionOrphanCheckChequesProveedores : chequesProveedoresCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Bancos (" + bancos + ") cannot be destroyed since the ChequesProveedores " + chequesProveedoresCollectionOrphanCheckChequesProveedores + " in its chequesProveedoresCollection field has a non-nullable bancosIdbancos field.");
            }
            Collection<Depositos> depositosCollectionOrphanCheck = bancos.getDepositosCollection();
            for (Depositos depositosCollectionOrphanCheckDepositos : depositosCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Bancos (" + bancos + ") cannot be destroyed since the Depositos " + depositosCollectionOrphanCheckDepositos + " in its depositosCollection field has a non-nullable bancosIdbancos field.");
            }
            Collection<ChequesClientes> chequesClientesCollectionOrphanCheck = bancos.getChequesClientesCollection();
            for (ChequesClientes chequesClientesCollectionOrphanCheckChequesClientes : chequesClientesCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Bancos (" + bancos + ") cannot be destroyed since the ChequesClientes " + chequesClientesCollectionOrphanCheckChequesClientes + " in its chequesClientesCollection field has a non-nullable bancosIdbancos field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(bancos);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Bancos> findBancosEntities() {
        return findBancosEntities(true, -1, -1);
    }

    public List<Bancos> findBancosEntities(int maxResults, int firstResult) {
        return findBancosEntities(false, maxResults, firstResult);
    }

    private List<Bancos> findBancosEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Bancos.class));
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

    public Bancos findBancos(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Bancos.class, id);
        } finally {
            em.close();
        }
    }

    public int getBancosCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Bancos> rt = cq.from(Bancos.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
