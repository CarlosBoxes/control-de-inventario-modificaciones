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
import EntidadesJPA.TipoClientes;
import EntidadesJPA.DescripcionClientes;
import java.util.ArrayList;
import java.util.Collection;
import EntidadesJPA.CuentaporCobrar;
import EntidadesJPA.Facturas;
import EntidadesJPA.ListaClientes;
import EntidadesJPA.CargosClientes;
import EntidadesJPA.ChequesClientes;
import EntidadesJPA.Clientes;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Ch470
 */
public class ClientesJpaController implements Serializable {

    public ClientesJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Clientes clientes) {
        if (clientes.getDescripcionclientesCollection() == null) {
            clientes.setDescripcionclientesCollection(new ArrayList<DescripcionClientes>());
        }
        if (clientes.getCuentaporcobrarCollection() == null) {
            clientes.setCuentaporcobrarCollection(new ArrayList<CuentaporCobrar>());
        }
        if (clientes.getFacturasCollection() == null) {
            clientes.setFacturasCollection(new ArrayList<Facturas>());
        }
        if (clientes.getListaclientesCollection() == null) {
            clientes.setListaclientesCollection(new ArrayList<ListaClientes>());
        }
        if (clientes.getCargosclientesCollection() == null) {
            clientes.setCargosclientesCollection(new ArrayList<CargosClientes>());
        }
        if (clientes.getChequesClientesCollection() == null) {
            clientes.setChequesClientesCollection(new ArrayList<ChequesClientes>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TipoClientes tipoClientesidTipoClientes = clientes.getTipoClientesidTipoClientes();
            if (tipoClientesidTipoClientes != null) {
                tipoClientesidTipoClientes = em.getReference(tipoClientesidTipoClientes.getClass(), tipoClientesidTipoClientes.getIdTipoClientes());
                clientes.setTipoClientesidTipoClientes(tipoClientesidTipoClientes);
            }
            Collection<DescripcionClientes> attachedDescripcionclientesCollection = new ArrayList<DescripcionClientes>();
            for (DescripcionClientes descripcionclientesCollectionDescripcionClientesToAttach : clientes.getDescripcionclientesCollection()) {
                descripcionclientesCollectionDescripcionClientesToAttach = em.getReference(descripcionclientesCollectionDescripcionClientesToAttach.getClass(), descripcionclientesCollectionDescripcionClientesToAttach.getIddescripcionclientes());
                attachedDescripcionclientesCollection.add(descripcionclientesCollectionDescripcionClientesToAttach);
            }
            clientes.setDescripcionclientesCollection(attachedDescripcionclientesCollection);
            Collection<CuentaporCobrar> attachedCuentaporcobrarCollection = new ArrayList<CuentaporCobrar>();
            for (CuentaporCobrar cuentaporcobrarCollectionCuentaporCobrarToAttach : clientes.getCuentaporcobrarCollection()) {
                cuentaporcobrarCollectionCuentaporCobrarToAttach = em.getReference(cuentaporcobrarCollectionCuentaporCobrarToAttach.getClass(), cuentaporcobrarCollectionCuentaporCobrarToAttach.getIdcuenta());
                attachedCuentaporcobrarCollection.add(cuentaporcobrarCollectionCuentaporCobrarToAttach);
            }
            clientes.setCuentaporcobrarCollection(attachedCuentaporcobrarCollection);
            Collection<Facturas> attachedFacturasCollection = new ArrayList<Facturas>();
            for (Facturas facturasCollectionFacturasToAttach : clientes.getFacturasCollection()) {
                facturasCollectionFacturasToAttach = em.getReference(facturasCollectionFacturasToAttach.getClass(), facturasCollectionFacturasToAttach.getIdFacturas());
                attachedFacturasCollection.add(facturasCollectionFacturasToAttach);
            }
            clientes.setFacturasCollection(attachedFacturasCollection);
            Collection<ListaClientes> attachedListaclientesCollection = new ArrayList<ListaClientes>();
            for (ListaClientes listaclientesCollectionListaClientesToAttach : clientes.getListaclientesCollection()) {
                listaclientesCollectionListaClientesToAttach = em.getReference(listaclientesCollectionListaClientesToAttach.getClass(), listaclientesCollectionListaClientesToAttach.getIdlistaclientes());
                attachedListaclientesCollection.add(listaclientesCollectionListaClientesToAttach);
            }
            clientes.setListaclientesCollection(attachedListaclientesCollection);
            Collection<CargosClientes> attachedCargosclientesCollection = new ArrayList<CargosClientes>();
            for (CargosClientes cargosclientesCollectionCargosClientesToAttach : clientes.getCargosclientesCollection()) {
                cargosclientesCollectionCargosClientesToAttach = em.getReference(cargosclientesCollectionCargosClientesToAttach.getClass(), cargosclientesCollectionCargosClientesToAttach.getIdcargosclientes());
                attachedCargosclientesCollection.add(cargosclientesCollectionCargosClientesToAttach);
            }
            clientes.setCargosclientesCollection(attachedCargosclientesCollection);
            Collection<ChequesClientes> attachedChequesClientesCollection = new ArrayList<ChequesClientes>();
            for (ChequesClientes chequesClientesCollectionChequesClientesToAttach : clientes.getChequesClientesCollection()) {
                chequesClientesCollectionChequesClientesToAttach = em.getReference(chequesClientesCollectionChequesClientesToAttach.getClass(), chequesClientesCollectionChequesClientesToAttach.getIdchequesClientes());
                attachedChequesClientesCollection.add(chequesClientesCollectionChequesClientesToAttach);
            }
            clientes.setChequesClientesCollection(attachedChequesClientesCollection);
            em.persist(clientes);
            if (tipoClientesidTipoClientes != null) {
                tipoClientesidTipoClientes.getClientesCollection().add(clientes);
                tipoClientesidTipoClientes = em.merge(tipoClientesidTipoClientes);
            }
            for (DescripcionClientes descripcionclientesCollectionDescripcionClientes : clientes.getDescripcionclientesCollection()) {
                Clientes oldClientesidClienteOfDescripcionclientesCollectionDescripcionClientes = descripcionclientesCollectionDescripcionClientes.getClientesidCliente();
                descripcionclientesCollectionDescripcionClientes.setClientesidCliente(clientes);
                descripcionclientesCollectionDescripcionClientes = em.merge(descripcionclientesCollectionDescripcionClientes);
                if (oldClientesidClienteOfDescripcionclientesCollectionDescripcionClientes != null) {
                    oldClientesidClienteOfDescripcionclientesCollectionDescripcionClientes.getDescripcionclientesCollection().remove(descripcionclientesCollectionDescripcionClientes);
                    oldClientesidClienteOfDescripcionclientesCollectionDescripcionClientes = em.merge(oldClientesidClienteOfDescripcionclientesCollectionDescripcionClientes);
                }
            }
            for (CuentaporCobrar cuentaporcobrarCollectionCuentaporCobrar : clientes.getCuentaporcobrarCollection()) {
                Clientes oldClientesidClienteOfCuentaporcobrarCollectionCuentaporCobrar = cuentaporcobrarCollectionCuentaporCobrar.getClientesidCliente();
                cuentaporcobrarCollectionCuentaporCobrar.setClientesidCliente(clientes);
                cuentaporcobrarCollectionCuentaporCobrar = em.merge(cuentaporcobrarCollectionCuentaporCobrar);
                if (oldClientesidClienteOfCuentaporcobrarCollectionCuentaporCobrar != null) {
                    oldClientesidClienteOfCuentaporcobrarCollectionCuentaporCobrar.getCuentaporcobrarCollection().remove(cuentaporcobrarCollectionCuentaporCobrar);
                    oldClientesidClienteOfCuentaporcobrarCollectionCuentaporCobrar = em.merge(oldClientesidClienteOfCuentaporcobrarCollectionCuentaporCobrar);
                }
            }
            for (Facturas facturasCollectionFacturas : clientes.getFacturasCollection()) {
                Clientes oldClientesidClienteOfFacturasCollectionFacturas = facturasCollectionFacturas.getClientesidCliente();
                facturasCollectionFacturas.setClientesidCliente(clientes);
                facturasCollectionFacturas = em.merge(facturasCollectionFacturas);
                if (oldClientesidClienteOfFacturasCollectionFacturas != null) {
                    oldClientesidClienteOfFacturasCollectionFacturas.getFacturasCollection().remove(facturasCollectionFacturas);
                    oldClientesidClienteOfFacturasCollectionFacturas = em.merge(oldClientesidClienteOfFacturasCollectionFacturas);
                }
            }
            for (ListaClientes listaclientesCollectionListaClientes : clientes.getListaclientesCollection()) {
                Clientes oldClientesidClienteOfListaclientesCollectionListaClientes = listaclientesCollectionListaClientes.getClientesidCliente();
                listaclientesCollectionListaClientes.setClientesidCliente(clientes);
                listaclientesCollectionListaClientes = em.merge(listaclientesCollectionListaClientes);
                if (oldClientesidClienteOfListaclientesCollectionListaClientes != null) {
                    oldClientesidClienteOfListaclientesCollectionListaClientes.getListaclientesCollection().remove(listaclientesCollectionListaClientes);
                    oldClientesidClienteOfListaclientesCollectionListaClientes = em.merge(oldClientesidClienteOfListaclientesCollectionListaClientes);
                }
            }
            for (CargosClientes cargosclientesCollectionCargosClientes : clientes.getCargosclientesCollection()) {
                Clientes oldClientesidClienteOfCargosclientesCollectionCargosClientes = cargosclientesCollectionCargosClientes.getClientesidCliente();
                cargosclientesCollectionCargosClientes.setClientesidCliente(clientes);
                cargosclientesCollectionCargosClientes = em.merge(cargosclientesCollectionCargosClientes);
                if (oldClientesidClienteOfCargosclientesCollectionCargosClientes != null) {
                    oldClientesidClienteOfCargosclientesCollectionCargosClientes.getCargosclientesCollection().remove(cargosclientesCollectionCargosClientes);
                    oldClientesidClienteOfCargosclientesCollectionCargosClientes = em.merge(oldClientesidClienteOfCargosclientesCollectionCargosClientes);
                }
            }
            for (ChequesClientes chequesClientesCollectionChequesClientes : clientes.getChequesClientesCollection()) {
                Clientes oldClientesidClienteOfChequesClientesCollectionChequesClientes = chequesClientesCollectionChequesClientes.getClientesidCliente();
                chequesClientesCollectionChequesClientes.setClientesidCliente(clientes);
                chequesClientesCollectionChequesClientes = em.merge(chequesClientesCollectionChequesClientes);
                if (oldClientesidClienteOfChequesClientesCollectionChequesClientes != null) {
                    oldClientesidClienteOfChequesClientesCollectionChequesClientes.getChequesClientesCollection().remove(chequesClientesCollectionChequesClientes);
                    oldClientesidClienteOfChequesClientesCollectionChequesClientes = em.merge(oldClientesidClienteOfChequesClientesCollectionChequesClientes);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Clientes clientes) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Clientes persistentClientes = em.find(Clientes.class, clientes.getIdCliente());
            TipoClientes tipoClientesidTipoClientesOld = persistentClientes.getTipoClientesidTipoClientes();
            TipoClientes tipoClientesidTipoClientesNew = clientes.getTipoClientesidTipoClientes();
            Collection<DescripcionClientes> descripcionclientesCollectionOld = persistentClientes.getDescripcionclientesCollection();
            Collection<DescripcionClientes> descripcionclientesCollectionNew = clientes.getDescripcionclientesCollection();
            Collection<CuentaporCobrar> cuentaporcobrarCollectionOld = persistentClientes.getCuentaporcobrarCollection();
            Collection<CuentaporCobrar> cuentaporcobrarCollectionNew = clientes.getCuentaporcobrarCollection();
            Collection<Facturas> facturasCollectionOld = persistentClientes.getFacturasCollection();
            Collection<Facturas> facturasCollectionNew = clientes.getFacturasCollection();
            Collection<ListaClientes> listaclientesCollectionOld = persistentClientes.getListaclientesCollection();
            Collection<ListaClientes> listaclientesCollectionNew = clientes.getListaclientesCollection();
            Collection<CargosClientes> cargosclientesCollectionOld = persistentClientes.getCargosclientesCollection();
            Collection<CargosClientes> cargosclientesCollectionNew = clientes.getCargosclientesCollection();
            Collection<ChequesClientes> chequesClientesCollectionOld = persistentClientes.getChequesClientesCollection();
            Collection<ChequesClientes> chequesClientesCollectionNew = clientes.getChequesClientesCollection();
            List<String> illegalOrphanMessages = null;
            for (DescripcionClientes descripcionclientesCollectionOldDescripcionClientes : descripcionclientesCollectionOld) {
                if (!descripcionclientesCollectionNew.contains(descripcionclientesCollectionOldDescripcionClientes)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain DescripcionClientes " + descripcionclientesCollectionOldDescripcionClientes + " since its clientesidCliente field is not nullable.");
                }
            }
            for (CuentaporCobrar cuentaporcobrarCollectionOldCuentaporCobrar : cuentaporcobrarCollectionOld) {
                if (!cuentaporcobrarCollectionNew.contains(cuentaporcobrarCollectionOldCuentaporCobrar)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain CuentaporCobrar " + cuentaporcobrarCollectionOldCuentaporCobrar + " since its clientesidCliente field is not nullable.");
                }
            }
            for (Facturas facturasCollectionOldFacturas : facturasCollectionOld) {
                if (!facturasCollectionNew.contains(facturasCollectionOldFacturas)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Facturas " + facturasCollectionOldFacturas + " since its clientesidCliente field is not nullable.");
                }
            }
            for (ListaClientes listaclientesCollectionOldListaClientes : listaclientesCollectionOld) {
                if (!listaclientesCollectionNew.contains(listaclientesCollectionOldListaClientes)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain ListaClientes " + listaclientesCollectionOldListaClientes + " since its clientesidCliente field is not nullable.");
                }
            }
            for (CargosClientes cargosclientesCollectionOldCargosClientes : cargosclientesCollectionOld) {
                if (!cargosclientesCollectionNew.contains(cargosclientesCollectionOldCargosClientes)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain CargosClientes " + cargosclientesCollectionOldCargosClientes + " since its clientesidCliente field is not nullable.");
                }
            }
            for (ChequesClientes chequesClientesCollectionOldChequesClientes : chequesClientesCollectionOld) {
                if (!chequesClientesCollectionNew.contains(chequesClientesCollectionOldChequesClientes)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain ChequesClientes " + chequesClientesCollectionOldChequesClientes + " since its clientesidCliente field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (tipoClientesidTipoClientesNew != null) {
                tipoClientesidTipoClientesNew = em.getReference(tipoClientesidTipoClientesNew.getClass(), tipoClientesidTipoClientesNew.getIdTipoClientes());
                clientes.setTipoClientesidTipoClientes(tipoClientesidTipoClientesNew);
            }
            Collection<DescripcionClientes> attachedDescripcionclientesCollectionNew = new ArrayList<DescripcionClientes>();
            for (DescripcionClientes descripcionclientesCollectionNewDescripcionClientesToAttach : descripcionclientesCollectionNew) {
                descripcionclientesCollectionNewDescripcionClientesToAttach = em.getReference(descripcionclientesCollectionNewDescripcionClientesToAttach.getClass(), descripcionclientesCollectionNewDescripcionClientesToAttach.getIddescripcionclientes());
                attachedDescripcionclientesCollectionNew.add(descripcionclientesCollectionNewDescripcionClientesToAttach);
            }
            descripcionclientesCollectionNew = attachedDescripcionclientesCollectionNew;
            clientes.setDescripcionclientesCollection(descripcionclientesCollectionNew);
            Collection<CuentaporCobrar> attachedCuentaporcobrarCollectionNew = new ArrayList<CuentaporCobrar>();
            for (CuentaporCobrar cuentaporcobrarCollectionNewCuentaporCobrarToAttach : cuentaporcobrarCollectionNew) {
                cuentaporcobrarCollectionNewCuentaporCobrarToAttach = em.getReference(cuentaporcobrarCollectionNewCuentaporCobrarToAttach.getClass(), cuentaporcobrarCollectionNewCuentaporCobrarToAttach.getIdcuenta());
                attachedCuentaporcobrarCollectionNew.add(cuentaporcobrarCollectionNewCuentaporCobrarToAttach);
            }
            cuentaporcobrarCollectionNew = attachedCuentaporcobrarCollectionNew;
            clientes.setCuentaporcobrarCollection(cuentaporcobrarCollectionNew);
            Collection<Facturas> attachedFacturasCollectionNew = new ArrayList<Facturas>();
            for (Facturas facturasCollectionNewFacturasToAttach : facturasCollectionNew) {
                facturasCollectionNewFacturasToAttach = em.getReference(facturasCollectionNewFacturasToAttach.getClass(), facturasCollectionNewFacturasToAttach.getIdFacturas());
                attachedFacturasCollectionNew.add(facturasCollectionNewFacturasToAttach);
            }
            facturasCollectionNew = attachedFacturasCollectionNew;
            clientes.setFacturasCollection(facturasCollectionNew);
            Collection<ListaClientes> attachedListaclientesCollectionNew = new ArrayList<ListaClientes>();
            for (ListaClientes listaclientesCollectionNewListaClientesToAttach : listaclientesCollectionNew) {
                listaclientesCollectionNewListaClientesToAttach = em.getReference(listaclientesCollectionNewListaClientesToAttach.getClass(), listaclientesCollectionNewListaClientesToAttach.getIdlistaclientes());
                attachedListaclientesCollectionNew.add(listaclientesCollectionNewListaClientesToAttach);
            }
            listaclientesCollectionNew = attachedListaclientesCollectionNew;
            clientes.setListaclientesCollection(listaclientesCollectionNew);
            Collection<CargosClientes> attachedCargosclientesCollectionNew = new ArrayList<CargosClientes>();
            for (CargosClientes cargosclientesCollectionNewCargosClientesToAttach : cargosclientesCollectionNew) {
                cargosclientesCollectionNewCargosClientesToAttach = em.getReference(cargosclientesCollectionNewCargosClientesToAttach.getClass(), cargosclientesCollectionNewCargosClientesToAttach.getIdcargosclientes());
                attachedCargosclientesCollectionNew.add(cargosclientesCollectionNewCargosClientesToAttach);
            }
            cargosclientesCollectionNew = attachedCargosclientesCollectionNew;
            clientes.setCargosclientesCollection(cargosclientesCollectionNew);
            Collection<ChequesClientes> attachedChequesClientesCollectionNew = new ArrayList<ChequesClientes>();
            for (ChequesClientes chequesClientesCollectionNewChequesClientesToAttach : chequesClientesCollectionNew) {
                chequesClientesCollectionNewChequesClientesToAttach = em.getReference(chequesClientesCollectionNewChequesClientesToAttach.getClass(), chequesClientesCollectionNewChequesClientesToAttach.getIdchequesClientes());
                attachedChequesClientesCollectionNew.add(chequesClientesCollectionNewChequesClientesToAttach);
            }
            chequesClientesCollectionNew = attachedChequesClientesCollectionNew;
            clientes.setChequesClientesCollection(chequesClientesCollectionNew);
            clientes = em.merge(clientes);
            if (tipoClientesidTipoClientesOld != null && !tipoClientesidTipoClientesOld.equals(tipoClientesidTipoClientesNew)) {
                tipoClientesidTipoClientesOld.getClientesCollection().remove(clientes);
                tipoClientesidTipoClientesOld = em.merge(tipoClientesidTipoClientesOld);
            }
            if (tipoClientesidTipoClientesNew != null && !tipoClientesidTipoClientesNew.equals(tipoClientesidTipoClientesOld)) {
                tipoClientesidTipoClientesNew.getClientesCollection().add(clientes);
                tipoClientesidTipoClientesNew = em.merge(tipoClientesidTipoClientesNew);
            }
            for (DescripcionClientes descripcionclientesCollectionNewDescripcionClientes : descripcionclientesCollectionNew) {
                if (!descripcionclientesCollectionOld.contains(descripcionclientesCollectionNewDescripcionClientes)) {
                    Clientes oldClientesidClienteOfDescripcionclientesCollectionNewDescripcionClientes = descripcionclientesCollectionNewDescripcionClientes.getClientesidCliente();
                    descripcionclientesCollectionNewDescripcionClientes.setClientesidCliente(clientes);
                    descripcionclientesCollectionNewDescripcionClientes = em.merge(descripcionclientesCollectionNewDescripcionClientes);
                    if (oldClientesidClienteOfDescripcionclientesCollectionNewDescripcionClientes != null && !oldClientesidClienteOfDescripcionclientesCollectionNewDescripcionClientes.equals(clientes)) {
                        oldClientesidClienteOfDescripcionclientesCollectionNewDescripcionClientes.getDescripcionclientesCollection().remove(descripcionclientesCollectionNewDescripcionClientes);
                        oldClientesidClienteOfDescripcionclientesCollectionNewDescripcionClientes = em.merge(oldClientesidClienteOfDescripcionclientesCollectionNewDescripcionClientes);
                    }
                }
            }
            for (CuentaporCobrar cuentaporcobrarCollectionNewCuentaporCobrar : cuentaporcobrarCollectionNew) {
                if (!cuentaporcobrarCollectionOld.contains(cuentaporcobrarCollectionNewCuentaporCobrar)) {
                    Clientes oldClientesidClienteOfCuentaporcobrarCollectionNewCuentaporCobrar = cuentaporcobrarCollectionNewCuentaporCobrar.getClientesidCliente();
                    cuentaporcobrarCollectionNewCuentaporCobrar.setClientesidCliente(clientes);
                    cuentaporcobrarCollectionNewCuentaporCobrar = em.merge(cuentaporcobrarCollectionNewCuentaporCobrar);
                    if (oldClientesidClienteOfCuentaporcobrarCollectionNewCuentaporCobrar != null && !oldClientesidClienteOfCuentaporcobrarCollectionNewCuentaporCobrar.equals(clientes)) {
                        oldClientesidClienteOfCuentaporcobrarCollectionNewCuentaporCobrar.getCuentaporcobrarCollection().remove(cuentaporcobrarCollectionNewCuentaporCobrar);
                        oldClientesidClienteOfCuentaporcobrarCollectionNewCuentaporCobrar = em.merge(oldClientesidClienteOfCuentaporcobrarCollectionNewCuentaporCobrar);
                    }
                }
            }
            for (Facturas facturasCollectionNewFacturas : facturasCollectionNew) {
                if (!facturasCollectionOld.contains(facturasCollectionNewFacturas)) {
                    Clientes oldClientesidClienteOfFacturasCollectionNewFacturas = facturasCollectionNewFacturas.getClientesidCliente();
                    facturasCollectionNewFacturas.setClientesidCliente(clientes);
                    facturasCollectionNewFacturas = em.merge(facturasCollectionNewFacturas);
                    if (oldClientesidClienteOfFacturasCollectionNewFacturas != null && !oldClientesidClienteOfFacturasCollectionNewFacturas.equals(clientes)) {
                        oldClientesidClienteOfFacturasCollectionNewFacturas.getFacturasCollection().remove(facturasCollectionNewFacturas);
                        oldClientesidClienteOfFacturasCollectionNewFacturas = em.merge(oldClientesidClienteOfFacturasCollectionNewFacturas);
                    }
                }
            }
            for (ListaClientes listaclientesCollectionNewListaClientes : listaclientesCollectionNew) {
                if (!listaclientesCollectionOld.contains(listaclientesCollectionNewListaClientes)) {
                    Clientes oldClientesidClienteOfListaclientesCollectionNewListaClientes = listaclientesCollectionNewListaClientes.getClientesidCliente();
                    listaclientesCollectionNewListaClientes.setClientesidCliente(clientes);
                    listaclientesCollectionNewListaClientes = em.merge(listaclientesCollectionNewListaClientes);
                    if (oldClientesidClienteOfListaclientesCollectionNewListaClientes != null && !oldClientesidClienteOfListaclientesCollectionNewListaClientes.equals(clientes)) {
                        oldClientesidClienteOfListaclientesCollectionNewListaClientes.getListaclientesCollection().remove(listaclientesCollectionNewListaClientes);
                        oldClientesidClienteOfListaclientesCollectionNewListaClientes = em.merge(oldClientesidClienteOfListaclientesCollectionNewListaClientes);
                    }
                }
            }
            for (CargosClientes cargosclientesCollectionNewCargosClientes : cargosclientesCollectionNew) {
                if (!cargosclientesCollectionOld.contains(cargosclientesCollectionNewCargosClientes)) {
                    Clientes oldClientesidClienteOfCargosclientesCollectionNewCargosClientes = cargosclientesCollectionNewCargosClientes.getClientesidCliente();
                    cargosclientesCollectionNewCargosClientes.setClientesidCliente(clientes);
                    cargosclientesCollectionNewCargosClientes = em.merge(cargosclientesCollectionNewCargosClientes);
                    if (oldClientesidClienteOfCargosclientesCollectionNewCargosClientes != null && !oldClientesidClienteOfCargosclientesCollectionNewCargosClientes.equals(clientes)) {
                        oldClientesidClienteOfCargosclientesCollectionNewCargosClientes.getCargosclientesCollection().remove(cargosclientesCollectionNewCargosClientes);
                        oldClientesidClienteOfCargosclientesCollectionNewCargosClientes = em.merge(oldClientesidClienteOfCargosclientesCollectionNewCargosClientes);
                    }
                }
            }
            for (ChequesClientes chequesClientesCollectionNewChequesClientes : chequesClientesCollectionNew) {
                if (!chequesClientesCollectionOld.contains(chequesClientesCollectionNewChequesClientes)) {
                    Clientes oldClientesidClienteOfChequesClientesCollectionNewChequesClientes = chequesClientesCollectionNewChequesClientes.getClientesidCliente();
                    chequesClientesCollectionNewChequesClientes.setClientesidCliente(clientes);
                    chequesClientesCollectionNewChequesClientes = em.merge(chequesClientesCollectionNewChequesClientes);
                    if (oldClientesidClienteOfChequesClientesCollectionNewChequesClientes != null && !oldClientesidClienteOfChequesClientesCollectionNewChequesClientes.equals(clientes)) {
                        oldClientesidClienteOfChequesClientesCollectionNewChequesClientes.getChequesClientesCollection().remove(chequesClientesCollectionNewChequesClientes);
                        oldClientesidClienteOfChequesClientesCollectionNewChequesClientes = em.merge(oldClientesidClienteOfChequesClientesCollectionNewChequesClientes);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = clientes.getIdCliente();
                if (findClientes(id) == null) {
                    throw new NonexistentEntityException("The clientes with id " + id + " no longer exists.");
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
            Clientes clientes;
            try {
                clientes = em.getReference(Clientes.class, id);
                clientes.getIdCliente();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The clientes with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<DescripcionClientes> descripcionclientesCollectionOrphanCheck = clientes.getDescripcionclientesCollection();
            for (DescripcionClientes descripcionclientesCollectionOrphanCheckDescripcionClientes : descripcionclientesCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Clientes (" + clientes + ") cannot be destroyed since the DescripcionClientes " + descripcionclientesCollectionOrphanCheckDescripcionClientes + " in its descripcionclientesCollection field has a non-nullable clientesidCliente field.");
            }
            Collection<CuentaporCobrar> cuentaporcobrarCollectionOrphanCheck = clientes.getCuentaporcobrarCollection();
            for (CuentaporCobrar cuentaporcobrarCollectionOrphanCheckCuentaporCobrar : cuentaporcobrarCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Clientes (" + clientes + ") cannot be destroyed since the CuentaporCobrar " + cuentaporcobrarCollectionOrphanCheckCuentaporCobrar + " in its cuentaporcobrarCollection field has a non-nullable clientesidCliente field.");
            }
            Collection<Facturas> facturasCollectionOrphanCheck = clientes.getFacturasCollection();
            for (Facturas facturasCollectionOrphanCheckFacturas : facturasCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Clientes (" + clientes + ") cannot be destroyed since the Facturas " + facturasCollectionOrphanCheckFacturas + " in its facturasCollection field has a non-nullable clientesidCliente field.");
            }
            Collection<ListaClientes> listaclientesCollectionOrphanCheck = clientes.getListaclientesCollection();
            for (ListaClientes listaclientesCollectionOrphanCheckListaClientes : listaclientesCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Clientes (" + clientes + ") cannot be destroyed since the ListaClientes " + listaclientesCollectionOrphanCheckListaClientes + " in its listaclientesCollection field has a non-nullable clientesidCliente field.");
            }
            Collection<CargosClientes> cargosclientesCollectionOrphanCheck = clientes.getCargosclientesCollection();
            for (CargosClientes cargosclientesCollectionOrphanCheckCargosClientes : cargosclientesCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Clientes (" + clientes + ") cannot be destroyed since the CargosClientes " + cargosclientesCollectionOrphanCheckCargosClientes + " in its cargosclientesCollection field has a non-nullable clientesidCliente field.");
            }
            Collection<ChequesClientes> chequesClientesCollectionOrphanCheck = clientes.getChequesClientesCollection();
            for (ChequesClientes chequesClientesCollectionOrphanCheckChequesClientes : chequesClientesCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Clientes (" + clientes + ") cannot be destroyed since the ChequesClientes " + chequesClientesCollectionOrphanCheckChequesClientes + " in its chequesClientesCollection field has a non-nullable clientesidCliente field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            TipoClientes tipoClientesidTipoClientes = clientes.getTipoClientesidTipoClientes();
            if (tipoClientesidTipoClientes != null) {
                tipoClientesidTipoClientes.getClientesCollection().remove(clientes);
                tipoClientesidTipoClientes = em.merge(tipoClientesidTipoClientes);
            }
            em.remove(clientes);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Clientes> findClientesEntities() {
        return findClientesEntities(true, -1, -1);
    }

    public List<Clientes> findClientesEntities(int maxResults, int firstResult) {
        return findClientesEntities(false, maxResults, firstResult);
    }

    private List<Clientes> findClientesEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Clientes.class));
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

    public Clientes findClientes(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Clientes.class, id);
        } finally {
            em.close();
        }
    }

    public int getClientesCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Clientes> rt = cq.from(Clientes.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
