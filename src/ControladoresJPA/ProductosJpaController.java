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
import EntidadesJPA.DescripcionPedido;
import java.util.ArrayList;
import java.util.Collection;
import EntidadesJPA.InventarioProducto;
import EntidadesJPA.DescripcionFactura;
import EntidadesJPA.DescripcionClientes;
import EntidadesJPA.ProductosVencidos;
import EntidadesJPA.DescripcionPedidoProveedores;
import EntidadesJPA.Productos;
import EntidadesJPA.ProductosVendedores;
import EntidadesJPA.ProductosClientes;
import EntidadesJPA.ProductosDefectuoso;
import EntidadesJPA.Traslado;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Ch470
 */
public class ProductosJpaController implements Serializable {

    public ProductosJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Productos productos) {
        if (productos.getDescripcionPedidoCollection() == null) {
            productos.setDescripcionPedidoCollection(new ArrayList<DescripcionPedido>());
        }
        if (productos.getInventarioProductoCollection() == null) {
            productos.setInventarioProductoCollection(new ArrayList<InventarioProducto>());
        }
        if (productos.getDescripcionFacturaCollection() == null) {
            productos.setDescripcionFacturaCollection(new ArrayList<DescripcionFactura>());
        }
        if (productos.getDescripcionClientesCollection() == null) {
            productos.setDescripcionClientesCollection(new ArrayList<DescripcionClientes>());
        }
        if (productos.getProductosVencidosCollection() == null) {
            productos.setProductosVencidosCollection(new ArrayList<ProductosVencidos>());
        }
        if (productos.getDescripcionPedidoProveedoresCollection() == null) {
            productos.setDescripcionPedidoProveedoresCollection(new ArrayList<DescripcionPedidoProveedores>());
        }
        if (productos.getProductosVendedoresCollection() == null) {
            productos.setProductosVendedoresCollection(new ArrayList<ProductosVendedores>());
        }
        if (productos.getProductosClientesCollection() == null) {
            productos.setProductosClientesCollection(new ArrayList<ProductosClientes>());
        }
        if (productos.getProductosDefectuosoCollection() == null) {
            productos.setProductosDefectuosoCollection(new ArrayList<ProductosDefectuoso>());
        }
//        if (productos.getTrasladoCollection() == null) {
  //          productos.setTrasladoCollection(new ArrayList<Traslado>());
    //    }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<DescripcionPedido> attachedDescripcionPedidoCollection = new ArrayList<DescripcionPedido>();
            for (DescripcionPedido descripcionPedidoCollectionDescripcionPedidoToAttach : productos.getDescripcionPedidoCollection()) {
                descripcionPedidoCollectionDescripcionPedidoToAttach = em.getReference(descripcionPedidoCollectionDescripcionPedidoToAttach.getClass(), descripcionPedidoCollectionDescripcionPedidoToAttach.getIddescripcionPedido());
                attachedDescripcionPedidoCollection.add(descripcionPedidoCollectionDescripcionPedidoToAttach);
            }
            productos.setDescripcionPedidoCollection(attachedDescripcionPedidoCollection);
            Collection<InventarioProducto> attachedInventarioProductoCollection = new ArrayList<InventarioProducto>();
            for (InventarioProducto inventarioProductoCollectionInventarioProductoToAttach : productos.getInventarioProductoCollection()) {
                inventarioProductoCollectionInventarioProductoToAttach = em.getReference(inventarioProductoCollectionInventarioProductoToAttach.getClass(), inventarioProductoCollectionInventarioProductoToAttach.getIdInventarioProducto());
                attachedInventarioProductoCollection.add(inventarioProductoCollectionInventarioProductoToAttach);
            }
            productos.setInventarioProductoCollection(attachedInventarioProductoCollection);
            Collection<DescripcionFactura> attachedDescripcionFacturaCollection = new ArrayList<DescripcionFactura>();
            for (DescripcionFactura descripcionFacturaCollectionDescripcionFacturaToAttach : productos.getDescripcionFacturaCollection()) {
                descripcionFacturaCollectionDescripcionFacturaToAttach = em.getReference(descripcionFacturaCollectionDescripcionFacturaToAttach.getClass(), descripcionFacturaCollectionDescripcionFacturaToAttach.getIddescripcionFactura());
                attachedDescripcionFacturaCollection.add(descripcionFacturaCollectionDescripcionFacturaToAttach);
            }
            productos.setDescripcionFacturaCollection(attachedDescripcionFacturaCollection);
            Collection<DescripcionClientes> attachedDescripcionClientesCollection = new ArrayList<DescripcionClientes>();
            for (DescripcionClientes descripcionClientesCollectionDescripcionClientesToAttach : productos.getDescripcionClientesCollection()) {
                descripcionClientesCollectionDescripcionClientesToAttach = em.getReference(descripcionClientesCollectionDescripcionClientesToAttach.getClass(), descripcionClientesCollectionDescripcionClientesToAttach.getIddescripcionclientes());
                attachedDescripcionClientesCollection.add(descripcionClientesCollectionDescripcionClientesToAttach);
            }
            productos.setDescripcionClientesCollection(attachedDescripcionClientesCollection);
            Collection<ProductosVencidos> attachedProductosVencidosCollection = new ArrayList<ProductosVencidos>();
            for (ProductosVencidos productosVencidosCollectionProductosVencidosToAttach : productos.getProductosVencidosCollection()) {
                productosVencidosCollectionProductosVencidosToAttach = em.getReference(productosVencidosCollectionProductosVencidosToAttach.getClass(), productosVencidosCollectionProductosVencidosToAttach.getIdProductosVencidos());
                attachedProductosVencidosCollection.add(productosVencidosCollectionProductosVencidosToAttach);
            }
            productos.setProductosVencidosCollection(attachedProductosVencidosCollection);
            Collection<DescripcionPedidoProveedores> attachedDescripcionPedidoProveedoresCollection = new ArrayList<DescripcionPedidoProveedores>();
            for (DescripcionPedidoProveedores descripcionPedidoProveedoresCollectionDescripcionPedidoProveedoresToAttach : productos.getDescripcionPedidoProveedoresCollection()) {
                descripcionPedidoProveedoresCollectionDescripcionPedidoProveedoresToAttach = em.getReference(descripcionPedidoProveedoresCollectionDescripcionPedidoProveedoresToAttach.getClass(), descripcionPedidoProveedoresCollectionDescripcionPedidoProveedoresToAttach.getIddescripcionP());
                attachedDescripcionPedidoProveedoresCollection.add(descripcionPedidoProveedoresCollectionDescripcionPedidoProveedoresToAttach);
            }
            productos.setDescripcionPedidoProveedoresCollection(attachedDescripcionPedidoProveedoresCollection);
            Collection<ProductosVendedores> attachedProductosVendedoresCollection = new ArrayList<ProductosVendedores>();
            for (ProductosVendedores productosVendedoresCollectionProductosVendedoresToAttach : productos.getProductosVendedoresCollection()) {
                productosVendedoresCollectionProductosVendedoresToAttach = em.getReference(productosVendedoresCollectionProductosVendedoresToAttach.getClass(), productosVendedoresCollectionProductosVendedoresToAttach.getIdlisitaproductosvendedores());
                attachedProductosVendedoresCollection.add(productosVendedoresCollectionProductosVendedoresToAttach);
            }
            productos.setProductosVendedoresCollection(attachedProductosVendedoresCollection);
            Collection<ProductosClientes> attachedProductosClientesCollection = new ArrayList<ProductosClientes>();
            for (ProductosClientes productosClientesCollectionProductosClientesToAttach : productos.getProductosClientesCollection()) {
                productosClientesCollectionProductosClientesToAttach = em.getReference(productosClientesCollectionProductosClientesToAttach.getClass(), productosClientesCollectionProductosClientesToAttach.getIdproductosespecialclientes());
                attachedProductosClientesCollection.add(productosClientesCollectionProductosClientesToAttach);
            }
            productos.setProductosClientesCollection(attachedProductosClientesCollection);
            Collection<ProductosDefectuoso> attachedProductosDefectuosoCollection = new ArrayList<ProductosDefectuoso>();
            for (ProductosDefectuoso productosDefectuosoCollectionProductosDefectuosoToAttach : productos.getProductosDefectuosoCollection()) {
                productosDefectuosoCollectionProductosDefectuosoToAttach = em.getReference(productosDefectuosoCollectionProductosDefectuosoToAttach.getClass(), productosDefectuosoCollectionProductosDefectuosoToAttach.getIdProductoDefectuoso());
                attachedProductosDefectuosoCollection.add(productosDefectuosoCollectionProductosDefectuosoToAttach);
            }
            productos.setProductosDefectuosoCollection(attachedProductosDefectuosoCollection);
            Collection<Traslado> attachedTrasladoCollection = new ArrayList<Traslado>();
            /*for (Traslado trasladoCollectionTrasladoToAttach : productos.getTrasladoCollection()) {
                trasladoCollectionTrasladoToAttach = em.getReference(trasladoCollectionTrasladoToAttach.getClass(), trasladoCollectionTrasladoToAttach.getIdtraslado());
                attachedTrasladoCollection.add(trasladoCollectionTrasladoToAttach);
            }
           // productos.setTrasladoCollection(attachedTrasladoCollection);*/
            em.persist(productos);
            for (DescripcionPedido descripcionPedidoCollectionDescripcionPedido : productos.getDescripcionPedidoCollection()) {
                Productos oldProductosidProductosOfDescripcionPedidoCollectionDescripcionPedido = descripcionPedidoCollectionDescripcionPedido.getProductosidProductos();
                descripcionPedidoCollectionDescripcionPedido.setProductosidProductos(productos);
                descripcionPedidoCollectionDescripcionPedido = em.merge(descripcionPedidoCollectionDescripcionPedido);
                if (oldProductosidProductosOfDescripcionPedidoCollectionDescripcionPedido != null) {
                    oldProductosidProductosOfDescripcionPedidoCollectionDescripcionPedido.getDescripcionPedidoCollection().remove(descripcionPedidoCollectionDescripcionPedido);
                    oldProductosidProductosOfDescripcionPedidoCollectionDescripcionPedido = em.merge(oldProductosidProductosOfDescripcionPedidoCollectionDescripcionPedido);
                }
            }
            for (InventarioProducto inventarioProductoCollectionInventarioProducto : productos.getInventarioProductoCollection()) {
                Productos oldProductosidProductosOfInventarioProductoCollectionInventarioProducto = inventarioProductoCollectionInventarioProducto.getProductosidProductos();
                inventarioProductoCollectionInventarioProducto.setProductosidProductos(productos);
                inventarioProductoCollectionInventarioProducto = em.merge(inventarioProductoCollectionInventarioProducto);
                if (oldProductosidProductosOfInventarioProductoCollectionInventarioProducto != null) {
                    oldProductosidProductosOfInventarioProductoCollectionInventarioProducto.getInventarioProductoCollection().remove(inventarioProductoCollectionInventarioProducto);
                    oldProductosidProductosOfInventarioProductoCollectionInventarioProducto = em.merge(oldProductosidProductosOfInventarioProductoCollectionInventarioProducto);
                }
            }
            for (DescripcionFactura descripcionFacturaCollectionDescripcionFactura : productos.getDescripcionFacturaCollection()) {
                Productos oldProductosidProductosOfDescripcionFacturaCollectionDescripcionFactura = descripcionFacturaCollectionDescripcionFactura.getProductosidProductos();
                descripcionFacturaCollectionDescripcionFactura.setProductosidProductos(productos);
                descripcionFacturaCollectionDescripcionFactura = em.merge(descripcionFacturaCollectionDescripcionFactura);
                if (oldProductosidProductosOfDescripcionFacturaCollectionDescripcionFactura != null) {
                    oldProductosidProductosOfDescripcionFacturaCollectionDescripcionFactura.getDescripcionFacturaCollection().remove(descripcionFacturaCollectionDescripcionFactura);
                    oldProductosidProductosOfDescripcionFacturaCollectionDescripcionFactura = em.merge(oldProductosidProductosOfDescripcionFacturaCollectionDescripcionFactura);
                }
            }
            for (DescripcionClientes descripcionClientesCollectionDescripcionClientes : productos.getDescripcionClientesCollection()) {
                Productos oldProductosidProductosOfDescripcionClientesCollectionDescripcionClientes = descripcionClientesCollectionDescripcionClientes.getProductosidProductos();
                descripcionClientesCollectionDescripcionClientes.setProductosidProductos(productos);
                descripcionClientesCollectionDescripcionClientes = em.merge(descripcionClientesCollectionDescripcionClientes);
                if (oldProductosidProductosOfDescripcionClientesCollectionDescripcionClientes != null) {
                    oldProductosidProductosOfDescripcionClientesCollectionDescripcionClientes.getDescripcionClientesCollection().remove(descripcionClientesCollectionDescripcionClientes);
                    oldProductosidProductosOfDescripcionClientesCollectionDescripcionClientes = em.merge(oldProductosidProductosOfDescripcionClientesCollectionDescripcionClientes);
                }
            }
            for (ProductosVencidos productosVencidosCollectionProductosVencidos : productos.getProductosVencidosCollection()) {
                Productos oldProductosidProductosOfProductosVencidosCollectionProductosVencidos = productosVencidosCollectionProductosVencidos.getProductosidProductos();
                productosVencidosCollectionProductosVencidos.setProductosidProductos(productos);
                productosVencidosCollectionProductosVencidos = em.merge(productosVencidosCollectionProductosVencidos);
                if (oldProductosidProductosOfProductosVencidosCollectionProductosVencidos != null) {
                    oldProductosidProductosOfProductosVencidosCollectionProductosVencidos.getProductosVencidosCollection().remove(productosVencidosCollectionProductosVencidos);
                    oldProductosidProductosOfProductosVencidosCollectionProductosVencidos = em.merge(oldProductosidProductosOfProductosVencidosCollectionProductosVencidos);
                }
            }
            for (DescripcionPedidoProveedores descripcionPedidoProveedoresCollectionDescripcionPedidoProveedores : productos.getDescripcionPedidoProveedoresCollection()) {
                Productos oldProductosidProductosOfDescripcionPedidoProveedoresCollectionDescripcionPedidoProveedores = descripcionPedidoProveedoresCollectionDescripcionPedidoProveedores.getProductosidProductos();
                descripcionPedidoProveedoresCollectionDescripcionPedidoProveedores.setProductosidProductos(productos);
                descripcionPedidoProveedoresCollectionDescripcionPedidoProveedores = em.merge(descripcionPedidoProveedoresCollectionDescripcionPedidoProveedores);
                if (oldProductosidProductosOfDescripcionPedidoProveedoresCollectionDescripcionPedidoProveedores != null) {
                    oldProductosidProductosOfDescripcionPedidoProveedoresCollectionDescripcionPedidoProveedores.getDescripcionPedidoProveedoresCollection().remove(descripcionPedidoProveedoresCollectionDescripcionPedidoProveedores);
                    oldProductosidProductosOfDescripcionPedidoProveedoresCollectionDescripcionPedidoProveedores = em.merge(oldProductosidProductosOfDescripcionPedidoProveedoresCollectionDescripcionPedidoProveedores);
                }
            }
            for (ProductosVendedores productosVendedoresCollectionProductosVendedores : productos.getProductosVendedoresCollection()) {
                Productos oldProductosidProductosOfProductosVendedoresCollectionProductosVendedores = productosVendedoresCollectionProductosVendedores.getProductosidProductos();
                productosVendedoresCollectionProductosVendedores.setProductosidProductos(productos);
                productosVendedoresCollectionProductosVendedores = em.merge(productosVendedoresCollectionProductosVendedores);
                if (oldProductosidProductosOfProductosVendedoresCollectionProductosVendedores != null) {
                    oldProductosidProductosOfProductosVendedoresCollectionProductosVendedores.getProductosVendedoresCollection().remove(productosVendedoresCollectionProductosVendedores);
                    oldProductosidProductosOfProductosVendedoresCollectionProductosVendedores = em.merge(oldProductosidProductosOfProductosVendedoresCollectionProductosVendedores);
                }
            }
            for (ProductosClientes productosClientesCollectionProductosClientes : productos.getProductosClientesCollection()) {
                Productos oldProductosidProductosOfProductosClientesCollectionProductosClientes = productosClientesCollectionProductosClientes.getProductosidProductos();
                productosClientesCollectionProductosClientes.setProductosidProductos(productos);
                productosClientesCollectionProductosClientes = em.merge(productosClientesCollectionProductosClientes);
                if (oldProductosidProductosOfProductosClientesCollectionProductosClientes != null) {
                    oldProductosidProductosOfProductosClientesCollectionProductosClientes.getProductosClientesCollection().remove(productosClientesCollectionProductosClientes);
                    oldProductosidProductosOfProductosClientesCollectionProductosClientes = em.merge(oldProductosidProductosOfProductosClientesCollectionProductosClientes);
                }
            }
            for (ProductosDefectuoso productosDefectuosoCollectionProductosDefectuoso : productos.getProductosDefectuosoCollection()) {
                Productos oldProductosidProductosOfProductosDefectuosoCollectionProductosDefectuoso = productosDefectuosoCollectionProductosDefectuoso.getProductosidProductos();
                productosDefectuosoCollectionProductosDefectuoso.setProductosidProductos(productos);
                productosDefectuosoCollectionProductosDefectuoso = em.merge(productosDefectuosoCollectionProductosDefectuoso);
                if (oldProductosidProductosOfProductosDefectuosoCollectionProductosDefectuoso != null) {
                    oldProductosidProductosOfProductosDefectuosoCollectionProductosDefectuoso.getProductosDefectuosoCollection().remove(productosDefectuosoCollectionProductosDefectuoso);
                    oldProductosidProductosOfProductosDefectuosoCollectionProductosDefectuoso = em.merge(oldProductosidProductosOfProductosDefectuosoCollectionProductosDefectuoso);
                }
            }
/*            for (Traslado trasladoCollectionTraslado : productos.getTrasladoCollection()) {
                Productos oldProductosidProductosOfTrasladoCollectionTraslado = trasladoCollectionTraslado.getProductosidProductos();
                trasladoCollectionTraslado.setProductosidProductos(productos);
                trasladoCollectionTraslado = em.merge(trasladoCollectionTraslado);
                if (oldProductosidProductosOfTrasladoCollectionTraslado != null) {
                    oldProductosidProductosOfTrasladoCollectionTraslado.getTrasladoCollection().remove(trasladoCollectionTraslado);
                    oldProductosidProductosOfTrasladoCollectionTraslado = em.merge(oldProductosidProductosOfTrasladoCollectionTraslado);
                }
            }*/
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Productos productos) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Productos persistentProductos = em.find(Productos.class, productos.getIdProductos());
            Collection<DescripcionPedido> descripcionPedidoCollectionOld = persistentProductos.getDescripcionPedidoCollection();
            Collection<DescripcionPedido> descripcionPedidoCollectionNew = productos.getDescripcionPedidoCollection();
            Collection<InventarioProducto> inventarioProductoCollectionOld = persistentProductos.getInventarioProductoCollection();
            Collection<InventarioProducto> inventarioProductoCollectionNew = productos.getInventarioProductoCollection();
            Collection<DescripcionFactura> descripcionFacturaCollectionOld = persistentProductos.getDescripcionFacturaCollection();
            Collection<DescripcionFactura> descripcionFacturaCollectionNew = productos.getDescripcionFacturaCollection();
            Collection<DescripcionClientes> descripcionClientesCollectionOld = persistentProductos.getDescripcionClientesCollection();
            Collection<DescripcionClientes> descripcionClientesCollectionNew = productos.getDescripcionClientesCollection();
            Collection<ProductosVencidos> productosVencidosCollectionOld = persistentProductos.getProductosVencidosCollection();
            Collection<ProductosVencidos> productosVencidosCollectionNew = productos.getProductosVencidosCollection();
            Collection<DescripcionPedidoProveedores> descripcionPedidoProveedoresCollectionOld = persistentProductos.getDescripcionPedidoProveedoresCollection();
            Collection<DescripcionPedidoProveedores> descripcionPedidoProveedoresCollectionNew = productos.getDescripcionPedidoProveedoresCollection();
            Collection<ProductosVendedores> productosVendedoresCollectionOld = persistentProductos.getProductosVendedoresCollection();
            Collection<ProductosVendedores> productosVendedoresCollectionNew = productos.getProductosVendedoresCollection();
            Collection<ProductosClientes> productosClientesCollectionOld = persistentProductos.getProductosClientesCollection();
            Collection<ProductosClientes> productosClientesCollectionNew = productos.getProductosClientesCollection();
            Collection<ProductosDefectuoso> productosDefectuosoCollectionOld = persistentProductos.getProductosDefectuosoCollection();
            Collection<ProductosDefectuoso> productosDefectuosoCollectionNew = productos.getProductosDefectuosoCollection();
//            Collection<Traslado> trasladoCollectionOld = persistentProductos.getTrasladoCollection();
  //          Collection<Traslado> trasladoCollectionNew = productos.getTrasladoCollection();
            List<String> illegalOrphanMessages = null;
            for (DescripcionPedido descripcionPedidoCollectionOldDescripcionPedido : descripcionPedidoCollectionOld) {
                if (!descripcionPedidoCollectionNew.contains(descripcionPedidoCollectionOldDescripcionPedido)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain DescripcionPedido " + descripcionPedidoCollectionOldDescripcionPedido + " since its productosidProductos field is not nullable.");
                }
            }
            for (InventarioProducto inventarioProductoCollectionOldInventarioProducto : inventarioProductoCollectionOld) {
                if (!inventarioProductoCollectionNew.contains(inventarioProductoCollectionOldInventarioProducto)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain InventarioProducto " + inventarioProductoCollectionOldInventarioProducto + " since its productosidProductos field is not nullable.");
                }
            }
            for (DescripcionFactura descripcionFacturaCollectionOldDescripcionFactura : descripcionFacturaCollectionOld) {
                if (!descripcionFacturaCollectionNew.contains(descripcionFacturaCollectionOldDescripcionFactura)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain DescripcionFactura " + descripcionFacturaCollectionOldDescripcionFactura + " since its productosidProductos field is not nullable.");
                }
            }
            for (DescripcionClientes descripcionClientesCollectionOldDescripcionClientes : descripcionClientesCollectionOld) {
                if (!descripcionClientesCollectionNew.contains(descripcionClientesCollectionOldDescripcionClientes)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain DescripcionClientes " + descripcionClientesCollectionOldDescripcionClientes + " since its productosidProductos field is not nullable.");
                }
            }
            for (ProductosVencidos productosVencidosCollectionOldProductosVencidos : productosVencidosCollectionOld) {
                if (!productosVencidosCollectionNew.contains(productosVencidosCollectionOldProductosVencidos)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain ProductosVencidos " + productosVencidosCollectionOldProductosVencidos + " since its productosidProductos field is not nullable.");
                }
            }
            for (DescripcionPedidoProveedores descripcionPedidoProveedoresCollectionOldDescripcionPedidoProveedores : descripcionPedidoProveedoresCollectionOld) {
                if (!descripcionPedidoProveedoresCollectionNew.contains(descripcionPedidoProveedoresCollectionOldDescripcionPedidoProveedores)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain DescripcionPedidoProveedores " + descripcionPedidoProveedoresCollectionOldDescripcionPedidoProveedores + " since its productosidProductos field is not nullable.");
                }
            }
            for (ProductosVendedores productosVendedoresCollectionOldProductosVendedores : productosVendedoresCollectionOld) {
                if (!productosVendedoresCollectionNew.contains(productosVendedoresCollectionOldProductosVendedores)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain ProductosVendedores " + productosVendedoresCollectionOldProductosVendedores + " since its productosidProductos field is not nullable.");
                }
            }
            for (ProductosClientes productosClientesCollectionOldProductosClientes : productosClientesCollectionOld) {
                if (!productosClientesCollectionNew.contains(productosClientesCollectionOldProductosClientes)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain ProductosClientes " + productosClientesCollectionOldProductosClientes + " since its productosidProductos field is not nullable.");
                }
            }
            for (ProductosDefectuoso productosDefectuosoCollectionOldProductosDefectuoso : productosDefectuosoCollectionOld) {
                if (!productosDefectuosoCollectionNew.contains(productosDefectuosoCollectionOldProductosDefectuoso)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain ProductosDefectuoso " + productosDefectuosoCollectionOldProductosDefectuoso + " since its productosidProductos field is not nullable.");
                }
            }
            /*for (Traslado trasladoCollectionOldTraslado : trasladoCollectionOld) {
                if (!trasladoCollectionNew.contains(trasladoCollectionOldTraslado)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Traslado " + trasladoCollectionOldTraslado + " since its productosidProductos field is not nullable.");
                }
            }*/
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Collection<DescripcionPedido> attachedDescripcionPedidoCollectionNew = new ArrayList<DescripcionPedido>();
            for (DescripcionPedido descripcionPedidoCollectionNewDescripcionPedidoToAttach : descripcionPedidoCollectionNew) {
                descripcionPedidoCollectionNewDescripcionPedidoToAttach = em.getReference(descripcionPedidoCollectionNewDescripcionPedidoToAttach.getClass(), descripcionPedidoCollectionNewDescripcionPedidoToAttach.getIddescripcionPedido());
                attachedDescripcionPedidoCollectionNew.add(descripcionPedidoCollectionNewDescripcionPedidoToAttach);
            }
            descripcionPedidoCollectionNew = attachedDescripcionPedidoCollectionNew;
            productos.setDescripcionPedidoCollection(descripcionPedidoCollectionNew);
            Collection<InventarioProducto> attachedInventarioProductoCollectionNew = new ArrayList<InventarioProducto>();
            for (InventarioProducto inventarioProductoCollectionNewInventarioProductoToAttach : inventarioProductoCollectionNew) {
                inventarioProductoCollectionNewInventarioProductoToAttach = em.getReference(inventarioProductoCollectionNewInventarioProductoToAttach.getClass(), inventarioProductoCollectionNewInventarioProductoToAttach.getIdInventarioProducto());
                attachedInventarioProductoCollectionNew.add(inventarioProductoCollectionNewInventarioProductoToAttach);
            }
            inventarioProductoCollectionNew = attachedInventarioProductoCollectionNew;
            productos.setInventarioProductoCollection(inventarioProductoCollectionNew);
            Collection<DescripcionFactura> attachedDescripcionFacturaCollectionNew = new ArrayList<DescripcionFactura>();
            for (DescripcionFactura descripcionFacturaCollectionNewDescripcionFacturaToAttach : descripcionFacturaCollectionNew) {
                descripcionFacturaCollectionNewDescripcionFacturaToAttach = em.getReference(descripcionFacturaCollectionNewDescripcionFacturaToAttach.getClass(), descripcionFacturaCollectionNewDescripcionFacturaToAttach.getIddescripcionFactura());
                attachedDescripcionFacturaCollectionNew.add(descripcionFacturaCollectionNewDescripcionFacturaToAttach);
            }
            descripcionFacturaCollectionNew = attachedDescripcionFacturaCollectionNew;
            productos.setDescripcionFacturaCollection(descripcionFacturaCollectionNew);
            Collection<DescripcionClientes> attachedDescripcionClientesCollectionNew = new ArrayList<DescripcionClientes>();
            for (DescripcionClientes descripcionClientesCollectionNewDescripcionClientesToAttach : descripcionClientesCollectionNew) {
                descripcionClientesCollectionNewDescripcionClientesToAttach = em.getReference(descripcionClientesCollectionNewDescripcionClientesToAttach.getClass(), descripcionClientesCollectionNewDescripcionClientesToAttach.getIddescripcionclientes());
                attachedDescripcionClientesCollectionNew.add(descripcionClientesCollectionNewDescripcionClientesToAttach);
            }
            descripcionClientesCollectionNew = attachedDescripcionClientesCollectionNew;
            productos.setDescripcionClientesCollection(descripcionClientesCollectionNew);
            Collection<ProductosVencidos> attachedProductosVencidosCollectionNew = new ArrayList<ProductosVencidos>();
            for (ProductosVencidos productosVencidosCollectionNewProductosVencidosToAttach : productosVencidosCollectionNew) {
                productosVencidosCollectionNewProductosVencidosToAttach = em.getReference(productosVencidosCollectionNewProductosVencidosToAttach.getClass(), productosVencidosCollectionNewProductosVencidosToAttach.getIdProductosVencidos());
                attachedProductosVencidosCollectionNew.add(productosVencidosCollectionNewProductosVencidosToAttach);
            }
            productosVencidosCollectionNew = attachedProductosVencidosCollectionNew;
            productos.setProductosVencidosCollection(productosVencidosCollectionNew);
            Collection<DescripcionPedidoProveedores> attachedDescripcionPedidoProveedoresCollectionNew = new ArrayList<DescripcionPedidoProveedores>();
            for (DescripcionPedidoProveedores descripcionPedidoProveedoresCollectionNewDescripcionPedidoProveedoresToAttach : descripcionPedidoProveedoresCollectionNew) {
                descripcionPedidoProveedoresCollectionNewDescripcionPedidoProveedoresToAttach = em.getReference(descripcionPedidoProveedoresCollectionNewDescripcionPedidoProveedoresToAttach.getClass(), descripcionPedidoProveedoresCollectionNewDescripcionPedidoProveedoresToAttach.getIddescripcionP());
                attachedDescripcionPedidoProveedoresCollectionNew.add(descripcionPedidoProveedoresCollectionNewDescripcionPedidoProveedoresToAttach);
            }
            descripcionPedidoProveedoresCollectionNew = attachedDescripcionPedidoProveedoresCollectionNew;
            productos.setDescripcionPedidoProveedoresCollection(descripcionPedidoProveedoresCollectionNew);
            Collection<ProductosVendedores> attachedProductosVendedoresCollectionNew = new ArrayList<ProductosVendedores>();
            for (ProductosVendedores productosVendedoresCollectionNewProductosVendedoresToAttach : productosVendedoresCollectionNew) {
                productosVendedoresCollectionNewProductosVendedoresToAttach = em.getReference(productosVendedoresCollectionNewProductosVendedoresToAttach.getClass(), productosVendedoresCollectionNewProductosVendedoresToAttach.getIdlisitaproductosvendedores());
                attachedProductosVendedoresCollectionNew.add(productosVendedoresCollectionNewProductosVendedoresToAttach);
            }
            productosVendedoresCollectionNew = attachedProductosVendedoresCollectionNew;
            productos.setProductosVendedoresCollection(productosVendedoresCollectionNew);
            Collection<ProductosClientes> attachedProductosClientesCollectionNew = new ArrayList<ProductosClientes>();
            for (ProductosClientes productosClientesCollectionNewProductosClientesToAttach : productosClientesCollectionNew) {
                productosClientesCollectionNewProductosClientesToAttach = em.getReference(productosClientesCollectionNewProductosClientesToAttach.getClass(), productosClientesCollectionNewProductosClientesToAttach.getIdproductosespecialclientes());
                attachedProductosClientesCollectionNew.add(productosClientesCollectionNewProductosClientesToAttach);
            }
            productosClientesCollectionNew = attachedProductosClientesCollectionNew;
            productos.setProductosClientesCollection(productosClientesCollectionNew);
            Collection<ProductosDefectuoso> attachedProductosDefectuosoCollectionNew = new ArrayList<ProductosDefectuoso>();
            for (ProductosDefectuoso productosDefectuosoCollectionNewProductosDefectuosoToAttach : productosDefectuosoCollectionNew) {
                productosDefectuosoCollectionNewProductosDefectuosoToAttach = em.getReference(productosDefectuosoCollectionNewProductosDefectuosoToAttach.getClass(), productosDefectuosoCollectionNewProductosDefectuosoToAttach.getIdProductoDefectuoso());
                attachedProductosDefectuosoCollectionNew.add(productosDefectuosoCollectionNewProductosDefectuosoToAttach);
            }
            productosDefectuosoCollectionNew = attachedProductosDefectuosoCollectionNew;
            productos.setProductosDefectuosoCollection(productosDefectuosoCollectionNew);
            Collection<Traslado> attachedTrasladoCollectionNew = new ArrayList<Traslado>();
            /*for (Traslado trasladoCollectionNewTrasladoToAttach : trasladoCollectionNew) {
                trasladoCollectionNewTrasladoToAttach = em.getReference(trasladoCollectionNewTrasladoToAttach.getClass(), trasladoCollectionNewTrasladoToAttach.getIdtraslado());
                attachedTrasladoCollectionNew.add(trasladoCollectionNewTrasladoToAttach);
            }
           // trasladoCollectionNew = attachedTrasladoCollectionNew;
           // productos.setTrasladoCollection(trasladoCollectionNew);*/
            productos = em.merge(productos);
            for (DescripcionPedido descripcionPedidoCollectionNewDescripcionPedido : descripcionPedidoCollectionNew) {
                if (!descripcionPedidoCollectionOld.contains(descripcionPedidoCollectionNewDescripcionPedido)) {
                    Productos oldProductosidProductosOfDescripcionPedidoCollectionNewDescripcionPedido = descripcionPedidoCollectionNewDescripcionPedido.getProductosidProductos();
                    descripcionPedidoCollectionNewDescripcionPedido.setProductosidProductos(productos);
                    descripcionPedidoCollectionNewDescripcionPedido = em.merge(descripcionPedidoCollectionNewDescripcionPedido);
                    if (oldProductosidProductosOfDescripcionPedidoCollectionNewDescripcionPedido != null && !oldProductosidProductosOfDescripcionPedidoCollectionNewDescripcionPedido.equals(productos)) {
                        oldProductosidProductosOfDescripcionPedidoCollectionNewDescripcionPedido.getDescripcionPedidoCollection().remove(descripcionPedidoCollectionNewDescripcionPedido);
                        oldProductosidProductosOfDescripcionPedidoCollectionNewDescripcionPedido = em.merge(oldProductosidProductosOfDescripcionPedidoCollectionNewDescripcionPedido);
                    }
                }
            }
            for (InventarioProducto inventarioProductoCollectionNewInventarioProducto : inventarioProductoCollectionNew) {
                if (!inventarioProductoCollectionOld.contains(inventarioProductoCollectionNewInventarioProducto)) {
                    Productos oldProductosidProductosOfInventarioProductoCollectionNewInventarioProducto = inventarioProductoCollectionNewInventarioProducto.getProductosidProductos();
                    inventarioProductoCollectionNewInventarioProducto.setProductosidProductos(productos);
                    inventarioProductoCollectionNewInventarioProducto = em.merge(inventarioProductoCollectionNewInventarioProducto);
                    if (oldProductosidProductosOfInventarioProductoCollectionNewInventarioProducto != null && !oldProductosidProductosOfInventarioProductoCollectionNewInventarioProducto.equals(productos)) {
                        oldProductosidProductosOfInventarioProductoCollectionNewInventarioProducto.getInventarioProductoCollection().remove(inventarioProductoCollectionNewInventarioProducto);
                        oldProductosidProductosOfInventarioProductoCollectionNewInventarioProducto = em.merge(oldProductosidProductosOfInventarioProductoCollectionNewInventarioProducto);
                    }
                }
            }
            for (DescripcionFactura descripcionFacturaCollectionNewDescripcionFactura : descripcionFacturaCollectionNew) {
                if (!descripcionFacturaCollectionOld.contains(descripcionFacturaCollectionNewDescripcionFactura)) {
                    Productos oldProductosidProductosOfDescripcionFacturaCollectionNewDescripcionFactura = descripcionFacturaCollectionNewDescripcionFactura.getProductosidProductos();
                    descripcionFacturaCollectionNewDescripcionFactura.setProductosidProductos(productos);
                    descripcionFacturaCollectionNewDescripcionFactura = em.merge(descripcionFacturaCollectionNewDescripcionFactura);
                    if (oldProductosidProductosOfDescripcionFacturaCollectionNewDescripcionFactura != null && !oldProductosidProductosOfDescripcionFacturaCollectionNewDescripcionFactura.equals(productos)) {
                        oldProductosidProductosOfDescripcionFacturaCollectionNewDescripcionFactura.getDescripcionFacturaCollection().remove(descripcionFacturaCollectionNewDescripcionFactura);
                        oldProductosidProductosOfDescripcionFacturaCollectionNewDescripcionFactura = em.merge(oldProductosidProductosOfDescripcionFacturaCollectionNewDescripcionFactura);
                    }
                }
            }
            for (DescripcionClientes descripcionClientesCollectionNewDescripcionClientes : descripcionClientesCollectionNew) {
                if (!descripcionClientesCollectionOld.contains(descripcionClientesCollectionNewDescripcionClientes)) {
                    Productos oldProductosidProductosOfDescripcionClientesCollectionNewDescripcionClientes = descripcionClientesCollectionNewDescripcionClientes.getProductosidProductos();
                    descripcionClientesCollectionNewDescripcionClientes.setProductosidProductos(productos);
                    descripcionClientesCollectionNewDescripcionClientes = em.merge(descripcionClientesCollectionNewDescripcionClientes);
                    if (oldProductosidProductosOfDescripcionClientesCollectionNewDescripcionClientes != null && !oldProductosidProductosOfDescripcionClientesCollectionNewDescripcionClientes.equals(productos)) {
                        oldProductosidProductosOfDescripcionClientesCollectionNewDescripcionClientes.getDescripcionClientesCollection().remove(descripcionClientesCollectionNewDescripcionClientes);
                        oldProductosidProductosOfDescripcionClientesCollectionNewDescripcionClientes = em.merge(oldProductosidProductosOfDescripcionClientesCollectionNewDescripcionClientes);
                    }
                }
            }
            for (ProductosVencidos productosVencidosCollectionNewProductosVencidos : productosVencidosCollectionNew) {
                if (!productosVencidosCollectionOld.contains(productosVencidosCollectionNewProductosVencidos)) {
                    Productos oldProductosidProductosOfProductosVencidosCollectionNewProductosVencidos = productosVencidosCollectionNewProductosVencidos.getProductosidProductos();
                    productosVencidosCollectionNewProductosVencidos.setProductosidProductos(productos);
                    productosVencidosCollectionNewProductosVencidos = em.merge(productosVencidosCollectionNewProductosVencidos);
                    if (oldProductosidProductosOfProductosVencidosCollectionNewProductosVencidos != null && !oldProductosidProductosOfProductosVencidosCollectionNewProductosVencidos.equals(productos)) {
                        oldProductosidProductosOfProductosVencidosCollectionNewProductosVencidos.getProductosVencidosCollection().remove(productosVencidosCollectionNewProductosVencidos);
                        oldProductosidProductosOfProductosVencidosCollectionNewProductosVencidos = em.merge(oldProductosidProductosOfProductosVencidosCollectionNewProductosVencidos);
                    }
                }
            }
            for (DescripcionPedidoProveedores descripcionPedidoProveedoresCollectionNewDescripcionPedidoProveedores : descripcionPedidoProveedoresCollectionNew) {
                if (!descripcionPedidoProveedoresCollectionOld.contains(descripcionPedidoProveedoresCollectionNewDescripcionPedidoProveedores)) {
                    Productos oldProductosidProductosOfDescripcionPedidoProveedoresCollectionNewDescripcionPedidoProveedores = descripcionPedidoProveedoresCollectionNewDescripcionPedidoProveedores.getProductosidProductos();
                    descripcionPedidoProveedoresCollectionNewDescripcionPedidoProveedores.setProductosidProductos(productos);
                    descripcionPedidoProveedoresCollectionNewDescripcionPedidoProveedores = em.merge(descripcionPedidoProveedoresCollectionNewDescripcionPedidoProveedores);
                    if (oldProductosidProductosOfDescripcionPedidoProveedoresCollectionNewDescripcionPedidoProveedores != null && !oldProductosidProductosOfDescripcionPedidoProveedoresCollectionNewDescripcionPedidoProveedores.equals(productos)) {
                        oldProductosidProductosOfDescripcionPedidoProveedoresCollectionNewDescripcionPedidoProveedores.getDescripcionPedidoProveedoresCollection().remove(descripcionPedidoProveedoresCollectionNewDescripcionPedidoProveedores);
                        oldProductosidProductosOfDescripcionPedidoProveedoresCollectionNewDescripcionPedidoProveedores = em.merge(oldProductosidProductosOfDescripcionPedidoProveedoresCollectionNewDescripcionPedidoProveedores);
                    }
                }
            }
            for (ProductosVendedores productosVendedoresCollectionNewProductosVendedores : productosVendedoresCollectionNew) {
                if (!productosVendedoresCollectionOld.contains(productosVendedoresCollectionNewProductosVendedores)) {
                    Productos oldProductosidProductosOfProductosVendedoresCollectionNewProductosVendedores = productosVendedoresCollectionNewProductosVendedores.getProductosidProductos();
                    productosVendedoresCollectionNewProductosVendedores.setProductosidProductos(productos);
                    productosVendedoresCollectionNewProductosVendedores = em.merge(productosVendedoresCollectionNewProductosVendedores);
                    if (oldProductosidProductosOfProductosVendedoresCollectionNewProductosVendedores != null && !oldProductosidProductosOfProductosVendedoresCollectionNewProductosVendedores.equals(productos)) {
                        oldProductosidProductosOfProductosVendedoresCollectionNewProductosVendedores.getProductosVendedoresCollection().remove(productosVendedoresCollectionNewProductosVendedores);
                        oldProductosidProductosOfProductosVendedoresCollectionNewProductosVendedores = em.merge(oldProductosidProductosOfProductosVendedoresCollectionNewProductosVendedores);
                    }
                }
            }
            for (ProductosClientes productosClientesCollectionNewProductosClientes : productosClientesCollectionNew) {
                if (!productosClientesCollectionOld.contains(productosClientesCollectionNewProductosClientes)) {
                    Productos oldProductosidProductosOfProductosClientesCollectionNewProductosClientes = productosClientesCollectionNewProductosClientes.getProductosidProductos();
                    productosClientesCollectionNewProductosClientes.setProductosidProductos(productos);
                    productosClientesCollectionNewProductosClientes = em.merge(productosClientesCollectionNewProductosClientes);
                    if (oldProductosidProductosOfProductosClientesCollectionNewProductosClientes != null && !oldProductosidProductosOfProductosClientesCollectionNewProductosClientes.equals(productos)) {
                        oldProductosidProductosOfProductosClientesCollectionNewProductosClientes.getProductosClientesCollection().remove(productosClientesCollectionNewProductosClientes);
                        oldProductosidProductosOfProductosClientesCollectionNewProductosClientes = em.merge(oldProductosidProductosOfProductosClientesCollectionNewProductosClientes);
                    }
                }
            }
            for (ProductosDefectuoso productosDefectuosoCollectionNewProductosDefectuoso : productosDefectuosoCollectionNew) {
                if (!productosDefectuosoCollectionOld.contains(productosDefectuosoCollectionNewProductosDefectuoso)) {
                    Productos oldProductosidProductosOfProductosDefectuosoCollectionNewProductosDefectuoso = productosDefectuosoCollectionNewProductosDefectuoso.getProductosidProductos();
                    productosDefectuosoCollectionNewProductosDefectuoso.setProductosidProductos(productos);
                    productosDefectuosoCollectionNewProductosDefectuoso = em.merge(productosDefectuosoCollectionNewProductosDefectuoso);
                    if (oldProductosidProductosOfProductosDefectuosoCollectionNewProductosDefectuoso != null && !oldProductosidProductosOfProductosDefectuosoCollectionNewProductosDefectuoso.equals(productos)) {
                        oldProductosidProductosOfProductosDefectuosoCollectionNewProductosDefectuoso.getProductosDefectuosoCollection().remove(productosDefectuosoCollectionNewProductosDefectuoso);
                        oldProductosidProductosOfProductosDefectuosoCollectionNewProductosDefectuoso = em.merge(oldProductosidProductosOfProductosDefectuosoCollectionNewProductosDefectuoso);
                    }
                }
            }
            /*for (Traslado trasladoCollectionNewTraslado : trasladoCollectionNew) {
                if (!trasladoCollectionOld.contains(trasladoCollectionNewTraslado)) {
                    Productos oldProductosidProductosOfTrasladoCollectionNewTraslado = trasladoCollectionNewTraslado.getProductosidProductos();
                    trasladoCollectionNewTraslado.setProductosidProductos(productos);
                    trasladoCollectionNewTraslado = em.merge(trasladoCollectionNewTraslado);
                    if (oldProductosidProductosOfTrasladoCollectionNewTraslado != null && !oldProductosidProductosOfTrasladoCollectionNewTraslado.equals(productos)) {
                        oldProductosidProductosOfTrasladoCollectionNewTraslado.getTrasladoCollection().remove(trasladoCollectionNewTraslado);
                        oldProductosidProductosOfTrasladoCollectionNewTraslado = em.merge(oldProductosidProductosOfTrasladoCollectionNewTraslado);
                    }
                }
            }*/
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = productos.getIdProductos();
                if (findProductos(id) == null) {
                    throw new NonexistentEntityException("The productos with id " + id + " no longer exists.");
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
            Productos productos;
            try {
                productos = em.getReference(Productos.class, id);
                productos.getIdProductos();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The productos with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<DescripcionPedido> descripcionPedidoCollectionOrphanCheck = productos.getDescripcionPedidoCollection();
            for (DescripcionPedido descripcionPedidoCollectionOrphanCheckDescripcionPedido : descripcionPedidoCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Productos (" + productos + ") cannot be destroyed since the DescripcionPedido " + descripcionPedidoCollectionOrphanCheckDescripcionPedido + " in its descripcionPedidoCollection field has a non-nullable productosidProductos field.");
            }
            Collection<InventarioProducto> inventarioProductoCollectionOrphanCheck = productos.getInventarioProductoCollection();
            for (InventarioProducto inventarioProductoCollectionOrphanCheckInventarioProducto : inventarioProductoCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Productos (" + productos + ") cannot be destroyed since the InventarioProducto " + inventarioProductoCollectionOrphanCheckInventarioProducto + " in its inventarioProductoCollection field has a non-nullable productosidProductos field.");
            }
            Collection<DescripcionFactura> descripcionFacturaCollectionOrphanCheck = productos.getDescripcionFacturaCollection();
            for (DescripcionFactura descripcionFacturaCollectionOrphanCheckDescripcionFactura : descripcionFacturaCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Productos (" + productos + ") cannot be destroyed since the DescripcionFactura " + descripcionFacturaCollectionOrphanCheckDescripcionFactura + " in its descripcionFacturaCollection field has a non-nullable productosidProductos field.");
            }
            Collection<DescripcionClientes> descripcionClientesCollectionOrphanCheck = productos.getDescripcionClientesCollection();
            for (DescripcionClientes descripcionClientesCollectionOrphanCheckDescripcionClientes : descripcionClientesCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Productos (" + productos + ") cannot be destroyed since the DescripcionClientes " + descripcionClientesCollectionOrphanCheckDescripcionClientes + " in its descripcionClientesCollection field has a non-nullable productosidProductos field.");
            }
            Collection<ProductosVencidos> productosVencidosCollectionOrphanCheck = productos.getProductosVencidosCollection();
            for (ProductosVencidos productosVencidosCollectionOrphanCheckProductosVencidos : productosVencidosCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Productos (" + productos + ") cannot be destroyed since the ProductosVencidos " + productosVencidosCollectionOrphanCheckProductosVencidos + " in its productosVencidosCollection field has a non-nullable productosidProductos field.");
            }
            Collection<DescripcionPedidoProveedores> descripcionPedidoProveedoresCollectionOrphanCheck = productos.getDescripcionPedidoProveedoresCollection();
            for (DescripcionPedidoProveedores descripcionPedidoProveedoresCollectionOrphanCheckDescripcionPedidoProveedores : descripcionPedidoProveedoresCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Productos (" + productos + ") cannot be destroyed since the DescripcionPedidoProveedores " + descripcionPedidoProveedoresCollectionOrphanCheckDescripcionPedidoProveedores + " in its descripcionPedidoProveedoresCollection field has a non-nullable productosidProductos field.");
            }
            Collection<ProductosVendedores> productosVendedoresCollectionOrphanCheck = productos.getProductosVendedoresCollection();
            for (ProductosVendedores productosVendedoresCollectionOrphanCheckProductosVendedores : productosVendedoresCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Productos (" + productos + ") cannot be destroyed since the ProductosVendedores " + productosVendedoresCollectionOrphanCheckProductosVendedores + " in its productosVendedoresCollection field has a non-nullable productosidProductos field.");
            }
            Collection<ProductosClientes> productosClientesCollectionOrphanCheck = productos.getProductosClientesCollection();
            for (ProductosClientes productosClientesCollectionOrphanCheckProductosClientes : productosClientesCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Productos (" + productos + ") cannot be destroyed since the ProductosClientes " + productosClientesCollectionOrphanCheckProductosClientes + " in its productosClientesCollection field has a non-nullable productosidProductos field.");
            }
            Collection<ProductosDefectuoso> productosDefectuosoCollectionOrphanCheck = productos.getProductosDefectuosoCollection();
            for (ProductosDefectuoso productosDefectuosoCollectionOrphanCheckProductosDefectuoso : productosDefectuosoCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Productos (" + productos + ") cannot be destroyed since the ProductosDefectuoso " + productosDefectuosoCollectionOrphanCheckProductosDefectuoso + " in its productosDefectuosoCollection field has a non-nullable productosidProductos field.");
            }
           /* Collection<Traslado> trasladoCollectionOrphanCheck = productos.getTrasladoCollection();
            for (Traslado trasladoCollectionOrphanCheckTraslado : trasladoCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Productos (" + productos + ") cannot be destroyed since the Traslado " + trasladoCollectionOrphanCheckTraslado + " in its trasladoCollection field has a non-nullable productosidProductos field.");
            }*/
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(productos);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Productos> findProductosEntities() {
        return findProductosEntities(true, -1, -1);
    }

    public List<Productos> findProductosEntities(int maxResults, int firstResult) {
        return findProductosEntities(false, maxResults, firstResult);
    }

    private List<Productos> findProductosEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Productos.class));
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

    public Productos findProductos(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Productos.class, id);
        } finally {
            em.close();
        }
    }

    public int getProductosCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Productos> rt = cq.from(Productos.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
