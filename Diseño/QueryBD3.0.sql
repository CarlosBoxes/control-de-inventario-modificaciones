SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

DROP SCHEMA IF EXISTS `bdcc` ;
CREATE SCHEMA IF NOT EXISTS `bdcc` DEFAULT CHARACTER SET utf8 ;
USE `bdcc` ;

-- -----------------------------------------------------
-- Table `bdcc`.`usuario`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `bdcc`.`usuario` (
  `idUsuario` INT(11) NOT NULL AUTO_INCREMENT ,
  `Nombre` VARCHAR(45) CHARACTER SET 'utf8' COLLATE 'utf8_bin' NOT NULL ,
  `Contraseña` VARCHAR(2000) CHARACTER SET 'utf8' COLLATE 'utf8_bin' NOT NULL ,
  `Puesto` VARCHAR(30) CHARACTER SET 'utf8' COLLATE 'utf8_bin' NOT NULL ,
  `eliminado` TINYINT(1) NOT NULL ,
  PRIMARY KEY (`idUsuario`) )
ENGINE = InnoDB
AUTO_INCREMENT = 3
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_bin;


-- -----------------------------------------------------
-- Table `bdcc`.`permisos`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `bdcc`.`permisos` (
  `idPermisos` INT(11) NOT NULL AUTO_INCREMENT ,
  `Nombre` VARCHAR(30) NOT NULL ,
  PRIMARY KEY (`idPermisos`) )
ENGINE = InnoDB
AUTO_INCREMENT = 7
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `bdcc`.`asignacion de permisos`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `bdcc`.`asignacion de permisos` (
  `idAsignacionDePermisos` INT(11) NOT NULL AUTO_INCREMENT ,
  `permisos_idPermisos` INT(11) NULL DEFAULT NULL ,
  `Usuario_idUsuario` INT(11) NULL DEFAULT NULL ,
  PRIMARY KEY (`idAsignacionDePermisos`) ,
  INDEX `fk_Usuario_has_permisos_permisos1_idx` (`permisos_idPermisos` ASC) ,
  INDEX `fk_asignacion de permisos_Usuario1_idx` (`Usuario_idUsuario` ASC) ,
  CONSTRAINT `fk_asignacion de permisos_Usuario1`
    FOREIGN KEY (`Usuario_idUsuario` )
    REFERENCES `bdcc`.`usuario` (`idUsuario` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Usuario_has_permisos_permisos1`
    FOREIGN KEY (`permisos_idPermisos` )
    REFERENCES `bdcc`.`permisos` (`idPermisos` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 12
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `bdcc`.`bancos`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `bdcc`.`bancos` (
  `idbancos` INT(11) NOT NULL AUTO_INCREMENT ,
  `nombre` VARCHAR(45) NOT NULL ,
  `telefono` VARCHAR(8) NOT NULL ,
  `eliminado` TINYINT(1) NOT NULL ,
  PRIMARY KEY (`idbancos`) )
ENGINE = InnoDB
AUTO_INCREMENT = 13
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `bdcc`.`bodegaproduccion`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `bdcc`.`bodegaproduccion` (
  `idbodegaProduccion` INT(11) NOT NULL AUTO_INCREMENT ,
  `nombre` VARCHAR(45) NOT NULL ,
  `eliminado` TINYINT(1) NOT NULL ,
  PRIMARY KEY (`idbodegaProduccion`) )
ENGINE = InnoDB
AUTO_INCREMENT = 8
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `bdcc`.`bodegaproductos`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `bdcc`.`bodegaproductos` (
  `idbodega` INT(11) NOT NULL AUTO_INCREMENT ,
  `nombre` VARCHAR(45) NOT NULL ,
  `eliminado` TINYINT(1) NOT NULL ,
  PRIMARY KEY (`idbodega`) )
ENGINE = InnoDB
AUTO_INCREMENT = 6
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `bdcc`.`tipo clientes`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `bdcc`.`tipo clientes` (
  `idTipo_Clientes` INT(11) NOT NULL AUTO_INCREMENT ,
  `nombre` VARCHAR(25) NOT NULL ,
  `eliminado` TINYINT(1) NOT NULL ,
  `listadeproductos` TINYINT(1) NOT NULL ,
  PRIMARY KEY (`idTipo_Clientes`) )
ENGINE = InnoDB
AUTO_INCREMENT = 4
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `bdcc`.`clientes`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `bdcc`.`clientes` (
  `idCliente` INT(11) NOT NULL AUTO_INCREMENT ,
  `nombre` VARCHAR(70) NOT NULL ,
  `direccion` VARCHAR(100) NOT NULL ,
  `nit` VARCHAR(12) NOT NULL ,
  `Telefono_Celular` VARCHAR(8) NULL DEFAULT NULL ,
  `Telefono_Casa` VARCHAR(8) NULL DEFAULT NULL ,
  `Tipo_Clientes_idTipo_Clientes` INT(11) NOT NULL ,
  `eliminado` TINYINT(1) NOT NULL ,
  `Saldo` FLOAT NOT NULL ,
  PRIMARY KEY (`idCliente`) ,
  INDEX `fk_Clientes_Tipo Clientes1_idx` (`Tipo_Clientes_idTipo_Clientes` ASC) ,
  CONSTRAINT `fk_Clientes_Tipo Clientes1`
    FOREIGN KEY (`Tipo_Clientes_idTipo_Clientes` )
    REFERENCES `bdcc`.`tipo clientes` (`idTipo_Clientes` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 48
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `bdcc`.`cargosclientes`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `bdcc`.`cargosclientes` (
  `Idcargosclientes` INT(11) NOT NULL AUTO_INCREMENT ,
  `fecha` DATE NOT NULL ,
  `Total` FLOAT NOT NULL ,
  `Clientes_idCliente` INT(11) NOT NULL ,
  PRIMARY KEY (`Idcargosclientes`) ,
  INDEX `fk_cargosclientes_Clientes1_idx` (`Clientes_idCliente` ASC) ,
  CONSTRAINT `fk_cargosclientes_Clientes1`
    FOREIGN KEY (`Clientes_idCliente` )
    REFERENCES `bdcc`.`clientes` (`idCliente` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `bdcc`.`cheques clientes`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `bdcc`.`cheques clientes` (
  `idcheques_clientes` INT(11) NOT NULL AUTO_INCREMENT ,
  `numero` INT(11) NOT NULL ,
  `monto` FLOAT NOT NULL ,
  `fecha` DATE NOT NULL ,
  `bancos_idbancos` INT(11) NOT NULL ,
  `Clientes_idCliente` INT(11) NOT NULL ,
  `eliminado` TINYINT(1) NOT NULL ,
  `usado` TINYINT(1) NOT NULL ,
  PRIMARY KEY (`idcheques_clientes`) ,
  INDEX `fk_cheques clientes_bancos1_idx` (`bancos_idbancos` ASC) ,
  INDEX `fk_cheques clientes_Clientes1_idx` (`Clientes_idCliente` ASC) ,
  CONSTRAINT `fk_cheques clientes_bancos1`
    FOREIGN KEY (`bancos_idbancos` )
    REFERENCES `bdcc`.`bancos` (`idbancos` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_cheques clientes_Clientes1`
    FOREIGN KEY (`Clientes_idCliente` )
    REFERENCES `bdcc`.`clientes` (`idCliente` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 2
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `bdcc`.`proveedores`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `bdcc`.`proveedores` (
  `idProveedores` INT(11) NOT NULL AUTO_INCREMENT ,
  `nombre` VARCHAR(70) NOT NULL ,
  `nit` VARCHAR(12) NOT NULL ,
  `direccion` VARCHAR(100) NULL DEFAULT NULL ,
  `telefono1` VARCHAR(8) NULL DEFAULT NULL ,
  `telefono2` VARCHAR(8) NULL DEFAULT NULL ,
  `Saldo` FLOAT NOT NULL ,
  `eliminado` TINYINT(1) NOT NULL ,
  PRIMARY KEY (`idProveedores`) )
ENGINE = InnoDB
AUTO_INCREMENT = 14
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `bdcc`.`cheques_proveedores`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `bdcc`.`cheques_proveedores` (
  `idcheques_proveedores` INT(11) NOT NULL AUTO_INCREMENT ,
  `numero` INT(11) NOT NULL ,
  `monto` FLOAT NOT NULL ,
  `fecha` DATE NOT NULL ,
  `bancos_idbancos` INT(11) NOT NULL ,
  `Proveedores_idProveedores` INT(11) NOT NULL ,
  `eliminado` TINYINT(1) NOT NULL ,
  PRIMARY KEY (`idcheques_proveedores`) ,
  INDEX `fk_cheques proveedores_bancos1_idx` (`bancos_idbancos` ASC) ,
  INDEX `fk_cheques proveedores_Proveedores1_idx` (`Proveedores_idProveedores` ASC) ,
  CONSTRAINT `fk_cheques proveedores_bancos1`
    FOREIGN KEY (`bancos_idbancos` )
    REFERENCES `bdcc`.`bancos` (`idbancos` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_cheques proveedores_Proveedores1`
    FOREIGN KEY (`Proveedores_idProveedores` )
    REFERENCES `bdcc`.`proveedores` (`idProveedores` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `bdcc`.`depositos`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `bdcc`.`depositos` (
  `idDepositos` INT(11) NOT NULL AUTO_INCREMENT ,
  `monto` FLOAT NOT NULL ,
  `numero_de_boleta` VARCHAR(10) NOT NULL ,
  `bancos_idbancos` INT(11) NOT NULL ,
  `eliminado` TINYINT(1) NOT NULL ,
  `usado` TINYINT(1) NOT NULL ,
  PRIMARY KEY (`idDepositos`) ,
  INDEX `fk_Depositos_bancos1_idx` (`bancos_idbancos` ASC) ,
  CONSTRAINT `fk_Depositos_bancos1`
    FOREIGN KEY (`bancos_idbancos` )
    REFERENCES `bdcc`.`bancos` (`idbancos` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 33
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `bdcc`.`cuentaporcobrar`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `bdcc`.`cuentaporcobrar` (
  `idcuenta` INT(11) NOT NULL AUTO_INCREMENT ,
  `Efectivo` FLOAT NULL DEFAULT NULL ,
  `cheques_clientes_idcheques_clientes` INT(11) NULL DEFAULT NULL ,
  `Depositos_idDepositos` INT(11) NULL DEFAULT NULL ,
  `Clientes_idCliente` INT(11) NOT NULL ,
  `Fecha` DATE NOT NULL ,
  PRIMARY KEY (`idcuenta`) ,
  INDEX `fk_cuentaporcobrar_cheques clientes1_idx` (`cheques_clientes_idcheques_clientes` ASC) ,
  INDEX `fk_cuentaporcobrar_Depositos1_idx` (`Depositos_idDepositos` ASC) ,
  INDEX `fk_cuentaporcobrar_Clientes1_idx` (`Clientes_idCliente` ASC) ,
  CONSTRAINT `fk_cuentaporcobrar_cheques clientes1`
    FOREIGN KEY (`cheques_clientes_idcheques_clientes` )
    REFERENCES `bdcc`.`cheques clientes` (`idcheques_clientes` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_cuentaporcobrar_Clientes1`
    FOREIGN KEY (`Clientes_idCliente` )
    REFERENCES `bdcc`.`clientes` (`idCliente` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_cuentaporcobrar_Depositos1`
    FOREIGN KEY (`Depositos_idDepositos` )
    REFERENCES `bdcc`.`depositos` (`idDepositos` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `bdcc`.`facturas`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `bdcc`.`facturas` (
  `idFacturas` INT(11) NOT NULL AUTO_INCREMENT ,
  `serie` VARCHAR(1) NOT NULL ,
  `numero` INT(11) NOT NULL ,
  `total` FLOAT NOT NULL ,
  `Clientes_idCliente` INT(11) NOT NULL ,
  PRIMARY KEY (`idFacturas`) ,
  INDEX `fk_Facturas_Clientes1_idx` (`Clientes_idCliente` ASC) ,
  CONSTRAINT `fk_Facturas_Clientes1`
    FOREIGN KEY (`Clientes_idCliente` )
    REFERENCES `bdcc`.`clientes` (`idCliente` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `bdcc`.`descripcion factura`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `bdcc`.`descripcion factura` (
  `iddescripcion Factura` INT(11) NOT NULL AUTO_INCREMENT ,
  `Descripcion` VARCHAR(90) NOT NULL ,
  `Cantidad` INT(11) NOT NULL ,
  `SubTotal` FLOAT NOT NULL ,
  `Facturas_idFacturas` INT(11) NOT NULL ,
  PRIMARY KEY (`iddescripcion Factura`) ,
  INDEX `fk_descripcion Factura_Facturas1_idx` (`Facturas_idFacturas` ASC) ,
  CONSTRAINT `fk_descripcion Factura_Facturas1`
    FOREIGN KEY (`Facturas_idFacturas` )
    REFERENCES `bdcc`.`facturas` (`idFacturas` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `bdcc`.`tipo vendedores`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `bdcc`.`tipo vendedores` (
  `idTipo_Vendedores` INT(11) NOT NULL AUTO_INCREMENT ,
  `nombre` VARCHAR(25) NOT NULL ,
  `eliminado` TINYINT(1) NOT NULL ,
  `listadeproductos` TINYINT(1) NOT NULL ,
  PRIMARY KEY (`idTipo_Vendedores`) )
ENGINE = InnoDB
AUTO_INCREMENT = 3
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `bdcc`.`vendedores`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `bdcc`.`vendedores` (
  `idvendedores` INT(11) NOT NULL AUTO_INCREMENT ,
  `nombre` VARCHAR(45) NOT NULL ,
  `Apellido` VARCHAR(45) NULL DEFAULT NULL ,
  `Telefono_Celular` VARCHAR(8) NULL DEFAULT NULL ,
  `Telefono_Casa` VARCHAR(8) NULL DEFAULT NULL ,
  `saldo_Anterior` FLOAT NULL DEFAULT NULL ,
  `eliminado` TINYINT(1) NOT NULL ,
  `DPI` VARCHAR(45) NOT NULL ,
  `Tipo_Vendedores_idTipo_Vendedores` INT(11) NOT NULL ,
  PRIMARY KEY (`idvendedores`) ,
  INDEX `fk_vendedores_Tipo Vendedores1_idx` (`Tipo_Vendedores_idTipo_Vendedores` ASC) ,
  CONSTRAINT `fk_vendedores_Tipo Vendedores1`
    FOREIGN KEY (`Tipo_Vendedores_idTipo_Vendedores` )
    REFERENCES `bdcc`.`tipo vendedores` (`idTipo_Vendedores` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 14
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `bdcc`.`pedido`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `bdcc`.`pedido` (
  `idpedido` INT(11) NOT NULL AUTO_INCREMENT ,
  `fecha` DATE NOT NULL ,
  `aplicado` TINYINT(1) NULL DEFAULT NULL ,
  `descuento` FLOAT NULL DEFAULT NULL ,
  `subtotal` FLOAT NOT NULL ,
  `total` FLOAT NOT NULL ,
  `liquidado` TINYINT(1) NULL DEFAULT NULL ,
  `vendedores_idvendedores` INT(11) NOT NULL ,
  `observaciones` VARCHAR(400) NULL DEFAULT NULL ,
  `contraseñas` FLOAT NOT NULL ,
  `ISR` FLOAT NOT NULL ,
  PRIMARY KEY (`idpedido`) ,
  INDEX `fk_pedido_vendedores1_idx` (`vendedores_idvendedores` ASC) ,
  CONSTRAINT `fk_pedido_vendedores1`
    FOREIGN KEY (`vendedores_idvendedores` )
    REFERENCES `bdcc`.`vendedores` (`idvendedores` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 37
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `bdcc`.`liquidacion`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `bdcc`.`liquidacion` (
  `idliquidacion` INT(11) NOT NULL AUTO_INCREMENT ,
  `total` FLOAT NOT NULL ,
  `subtotal` FLOAT NOT NULL ,
  `pedido_idpedido` INT(11) NOT NULL ,
  PRIMARY KEY (`idliquidacion`) ,
  INDEX `fk_liquidacion_pedido1_idx` (`pedido_idpedido` ASC) ,
  CONSTRAINT `fk_liquidacion_pedido1`
    FOREIGN KEY (`pedido_idpedido` )
    REFERENCES `bdcc`.`pedido` (`idpedido` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 27
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `bdcc`.`descripcion liquidacion`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `bdcc`.`descripcion liquidacion` (
  `idDescripcionLiquidacion` INT(11) NOT NULL AUTO_INCREMENT ,
  `liquidacion_idliquidacion` INT(11) NOT NULL ,
  `Depositos_idDepositos` INT(11) NULL DEFAULT NULL ,
  `cheques_clientes_idcheques_clientes` INT(11) NULL DEFAULT NULL ,
  PRIMARY KEY (`idDescripcionLiquidacion`) ,
  INDEX `fk_liquidacion_has_Depositos_Depositos1_idx` (`Depositos_idDepositos` ASC) ,
  INDEX `fk_liquidacion_has_Depositos_liquidacion1_idx` (`liquidacion_idliquidacion` ASC) ,
  INDEX `fk_liquidacion_has_Depositos_cheques clientes1_idx` (`cheques_clientes_idcheques_clientes` ASC) ,
  CONSTRAINT `fk_liquidacion_has_Depositos_cheques clientes1`
    FOREIGN KEY (`cheques_clientes_idcheques_clientes` )
    REFERENCES `bdcc`.`cheques clientes` (`idcheques_clientes` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_liquidacion_has_Depositos_Depositos1`
    FOREIGN KEY (`Depositos_idDepositos` )
    REFERENCES `bdcc`.`depositos` (`idDepositos` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_liquidacion_has_Depositos_liquidacion1`
    FOREIGN KEY (`liquidacion_idliquidacion` )
    REFERENCES `bdcc`.`liquidacion` (`idliquidacion` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 36
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `bdcc`.`materiaprima`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `bdcc`.`materiaprima` (
  `idmateriaPrima` INT(11) NOT NULL AUTO_INCREMENT ,
  `nombre` VARCHAR(45) NOT NULL ,
  `presentacion` VARCHAR(45) NOT NULL ,
  `eliminado` TINYINT(1) NOT NULL ,
  `precio` FLOAT NOT NULL ,
  PRIMARY KEY (`idmateriaPrima`) )
ENGINE = InnoDB
AUTO_INCREMENT = 42
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `bdcc`.`pedido materia prima`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `bdcc`.`pedido materia prima` (
  `idpedidoMP` INT(11) NOT NULL AUTO_INCREMENT ,
  `fecha` DATE NOT NULL ,
  `aplicado` TINYINT(1) NULL DEFAULT NULL ,
  `total` FLOAT NOT NULL ,
  `Proveedores_idProveedores` INT(11) NOT NULL ,
  PRIMARY KEY (`idpedidoMP`) ,
  INDEX `fk_pedido materia prima_Proveedores1_idx` (`Proveedores_idProveedores` ASC) ,
  CONSTRAINT `fk_pedido materia prima_Proveedores1`
    FOREIGN KEY (`Proveedores_idProveedores` )
    REFERENCES `bdcc`.`proveedores` (`idProveedores` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 2
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `bdcc`.`descripcion pedido materia prima`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `bdcc`.`descripcion pedido materia prima` (
  `iddescripcionMP` INT(11) NOT NULL AUTO_INCREMENT ,
  `cantidad` FLOAT NOT NULL ,
  `precioMateriaPrima` FLOAT NOT NULL ,
  `subTotal` FLOAT NOT NULL ,
  `materiaPrima_idmateriaPrima` INT(11) NOT NULL ,
  `pedido_materia_prima_idpedidoMP` INT(11) NOT NULL ,
  PRIMARY KEY (`iddescripcionMP`) ,
  INDEX `fk_descripcion pedido materia prima_materiaPrima1_idx` (`materiaPrima_idmateriaPrima` ASC) ,
  INDEX `fk_descripcion pedido materia prima_pedido materia prima1_idx` (`pedido_materia_prima_idpedidoMP` ASC) ,
  CONSTRAINT `fk_descripcion pedido materia prima_materiaPrima1`
    FOREIGN KEY (`materiaPrima_idmateriaPrima` )
    REFERENCES `bdcc`.`materiaprima` (`idmateriaPrima` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_descripcion pedido materia prima_pedido materia prima1`
    FOREIGN KEY (`pedido_materia_prima_idpedidoMP` )
    REFERENCES `bdcc`.`pedido materia prima` (`idpedidoMP` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 2
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `bdcc`.`pedido proveedores`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `bdcc`.`pedido proveedores` (
  `idpedido_proveedores` INT(11) NOT NULL AUTO_INCREMENT ,
  `fecha` DATE NOT NULL ,
  `aplicado` TINYINT(1) NULL DEFAULT NULL ,
  `NoFactura` VARCHAR(15) NULL DEFAULT NULL ,
  `total` FLOAT NOT NULL ,
  `FechaVencimiento` DATE NULL DEFAULT NULL ,
  `Saldo` FLOAT NOT NULL ,
  `almacenado` TINYINT(1) NULL DEFAULT NULL ,
  `Proveedores_idProveedores` INT(11) NOT NULL ,
  PRIMARY KEY (`idpedido_proveedores`) ,
  INDEX `fk_pedido proveedores_Proveedores1_idx` (`Proveedores_idProveedores` ASC) ,
  CONSTRAINT `fk_pedido proveedores_Proveedores1`
    FOREIGN KEY (`Proveedores_idProveedores` )
    REFERENCES `bdcc`.`proveedores` (`idProveedores` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 27
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `bdcc`.`productos`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `bdcc`.`productos` (
  `idProductos` INT(11) NOT NULL AUTO_INCREMENT ,
  `nombre` VARCHAR(70) NOT NULL ,
  `presentacion` VARCHAR(70) NOT NULL ,
  `unidad_de_medida` INT(11) NOT NULL ,
  `precio_costo` FLOAT NOT NULL ,
  `precio_venta` FLOAT NOT NULL ,
  `fecha_de_vencimiento` DATE NULL DEFAULT NULL ,
  `descripcion` VARCHAR(300) NULL DEFAULT NULL ,
  `categoria` VARCHAR(50) NULL DEFAULT NULL ,
  `eliminado` TINYINT(1) NOT NULL ,
  `cambio` TINYINT(1) NOT NULL ,
  `devoluciones` FLOAT NOT NULL ,
  PRIMARY KEY (`idProductos`) )
ENGINE = InnoDB
AUTO_INCREMENT = 116
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `bdcc`.`descripcion pedido proveedores`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `bdcc`.`descripcion pedido proveedores` (
  `iddescripcionP` INT(11) NOT NULL AUTO_INCREMENT ,
  `cantidad` INT(11) NOT NULL ,
  `precioProducto` FLOAT NOT NULL ,
  `Productos_idProductos` INT(11) NOT NULL ,
  `pedido_proveedores_idpedido_proveedores` INT(11) NOT NULL ,
  PRIMARY KEY (`iddescripcionP`) ,
  INDEX `fk_descripcion pedido proveedores_Productos1_idx` (`Productos_idProductos` ASC) ,
  INDEX `fk_descripcion pedido proveedores_pedido proveedores1_idx` (`pedido_proveedores_idpedido_proveedores` ASC) ,
  CONSTRAINT `fk_descripcion pedido proveedores_pedido proveedores1`
    FOREIGN KEY (`pedido_proveedores_idpedido_proveedores` )
    REFERENCES `bdcc`.`pedido proveedores` (`idpedido_proveedores` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_descripcion pedido proveedores_Productos1`
    FOREIGN KEY (`Productos_idProductos` )
    REFERENCES `bdcc`.`productos` (`idProductos` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 90
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `bdcc`.`descripcionpedido`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `bdcc`.`descripcionpedido` (
  `iddescripcionPedido` INT(11) NOT NULL AUTO_INCREMENT ,
  `Cantidad` INT(11) NOT NULL ,
  `Facturado` INT(11) NOT NULL ,
  `subTotal` FLOAT NOT NULL ,
  `descuento` FLOAT NOT NULL ,
  `Productos_idProductos` INT(11) NULL DEFAULT NULL ,
  `pedido_idpedido` INT(11) NULL DEFAULT NULL ,
  PRIMARY KEY (`iddescripcionPedido`) ,
  INDEX `fk_descripcionPedido_Productos1_idx` (`Productos_idProductos` ASC) ,
  INDEX `fk_descripcionPedido_pedido1_idx` (`pedido_idpedido` ASC) ,
  CONSTRAINT `fk_descripcionPedido_pedido1`
    FOREIGN KEY (`pedido_idpedido` )
    REFERENCES `bdcc`.`pedido` (`idpedido` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_descripcionPedido_Productos1`
    FOREIGN KEY (`Productos_idProductos` )
    REFERENCES `bdcc`.`productos` (`idProductos` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 306
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `bdcc`.`inventario materia prima`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `bdcc`.`inventario materia prima` (
  `idInventarioMateriaPrima` INT(11) NOT NULL AUTO_INCREMENT ,
  `cantidad` FLOAT NOT NULL ,
  `materiaPrima_idmateriaPrima` INT(11) NOT NULL ,
  `bodegaProduccion_idbodegaProduccion` INT(11) NOT NULL ,
  PRIMARY KEY (`idInventarioMateriaPrima`) ,
  INDEX `fk_inventario materia prima_bodegaProduccion1_idx` (`bodegaProduccion_idbodegaProduccion` ASC) ,
  INDEX `fk_inventario materia prima_materiaPrima1` (`materiaPrima_idmateriaPrima` ASC) ,
  CONSTRAINT `fk_inventario materia prima_bodegaProduccion1`
    FOREIGN KEY (`bodegaProduccion_idbodegaProduccion` )
    REFERENCES `bdcc`.`bodegaproduccion` (`idbodegaProduccion` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_inventario materia prima_materiaPrima1`
    FOREIGN KEY (`materiaPrima_idmateriaPrima` )
    REFERENCES `bdcc`.`materiaprima` (`idmateriaPrima` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 45
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `bdcc`.`inventario producto`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `bdcc`.`inventario producto` (
  `idInventarioProducto` INT(11) NOT NULL AUTO_INCREMENT ,
  `cantidad` INT(11) NOT NULL ,
  `Productos_idProductos` INT(11) NOT NULL ,
  `bodegaProductos_idbodega` INT(11) NOT NULL ,
  PRIMARY KEY (`idInventarioProducto`) ,
  INDEX `fk_Productos_has_bodegaProductos_bodegaProductos1_idx` (`bodegaProductos_idbodega` ASC) ,
  INDEX `fk_Productos_has_bodegaProductos_Productos1_idx` (`Productos_idProductos` ASC) ,
  CONSTRAINT `fk_Productos_has_bodegaProductos_bodegaProductos1`
    FOREIGN KEY (`bodegaProductos_idbodega` )
    REFERENCES `bdcc`.`bodegaproductos` (`idbodega` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Productos_has_bodegaProductos_Productos1`
    FOREIGN KEY (`Productos_idProductos` )
    REFERENCES `bdcc`.`productos` (`idProductos` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 116
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `bdcc`.`productoacambiar`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `bdcc`.`productoacambiar` (
  `idproductoacambiar` INT(11) NOT NULL AUTO_INCREMENT ,
  `Producto` VARCHAR(90) NOT NULL ,
  `Cantidad` INT(11) NOT NULL ,
  `liquidacion_idliquidacion` INT(11) NOT NULL ,
  `precio` FLOAT NOT NULL ,
  PRIMARY KEY (`idproductoacambiar`) ,
  INDEX `fk_productoacambiar_liquidacion1_idx` (`liquidacion_idliquidacion` ASC) ,
  CONSTRAINT `fk_productoacambiar_liquidacion1`
    FOREIGN KEY (`liquidacion_idliquidacion` )
    REFERENCES `bdcc`.`liquidacion` (`idliquidacion` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 10
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `bdcc`.`productos defectuoso`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `bdcc`.`productos defectuoso` (
  `idProductoDefectuoso` INT(11) NOT NULL AUTO_INCREMENT ,
  `Productos_idProductos` INT(11) NOT NULL ,
  `cantidad` INT(11) NOT NULL ,
  `descripcion` VARCHAR(300) NULL DEFAULT NULL ,
  `vendedores_idvendedores` INT(11) NOT NULL ,
  PRIMARY KEY (`idProductoDefectuoso`) ,
  INDEX `fk_productos defectuoso_Productos1_idx` (`Productos_idProductos` ASC) ,
  INDEX `fk_productos defectuoso_vendedores1_idx` (`vendedores_idvendedores` ASC) ,
  CONSTRAINT `fk_productos defectuoso_Productos1`
    FOREIGN KEY (`Productos_idProductos` )
    REFERENCES `bdcc`.`productos` (`idProductos` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_productos defectuoso_vendedores1`
    FOREIGN KEY (`vendedores_idvendedores` )
    REFERENCES `bdcc`.`vendedores` (`idvendedores` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `bdcc`.`productos vencidos`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `bdcc`.`productos vencidos` (
  `idProductosVencidos` INT(11) NOT NULL AUTO_INCREMENT ,
  `Productos_idProductos` INT(11) NOT NULL ,
  `cantidad` INT(11) NOT NULL ,
  PRIMARY KEY (`idProductosVencidos`) ,
  INDEX `fk_productos vencidos_Productos1_idx` (`Productos_idProductos` ASC) ,
  CONSTRAINT `fk_productos vencidos_Productos1`
    FOREIGN KEY (`Productos_idProductos` )
    REFERENCES `bdcc`.`productos` (`idProductos` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `bdcc`.`productosvendedores`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `bdcc`.`productosvendedores` (
  `idlisitaproductosvendedores` INT NOT NULL AUTO_INCREMENT ,
  `precioespecial` FLOAT NOT NULL ,
  `tipovendedores_idTipo_Vendedores` INT(11) NOT NULL ,
  `productos_idProductos` INT(11) NOT NULL ,
  `eliminado` FLOAT NOT NULL ,
  PRIMARY KEY (`idlisitaproductosvendedores`) ,
  INDEX `fk_lisitaproductosvendedores_tipo vendedores1_idx` (`tipovendedores_idTipo_Vendedores` ASC) ,
  INDEX `fk_productosespecialvendedores_productos1_idx` (`productos_idProductos` ASC) ,
  CONSTRAINT `fk_lisitaproductosvendedores_tipo vendedores1`
    FOREIGN KEY (`tipovendedores_idTipo_Vendedores` )
    REFERENCES `bdcc`.`tipo vendedores` (`idTipo_Vendedores` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_productosespecialvendedores_productos1`
    FOREIGN KEY (`productos_idProductos` )
    REFERENCES `bdcc`.`productos` (`idProductos` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `bdcc`.`productosclientes`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `bdcc`.`productosclientes` (
  `idproductosespecialclientes` INT NOT NULL AUTO_INCREMENT ,
  `precio` FLOAT NOT NULL ,
  `eliminado` FLOAT NOT NULL ,
  `tipoclientes_idTipo_Clientes` INT(11) NOT NULL ,
  `productos_idProductos` INT(11) NOT NULL ,
  PRIMARY KEY (`idproductosespecialclientes`) ,
  INDEX `fk_productosespecialclientes_tipo clientes1_idx` (`tipoclientes_idTipo_Clientes` ASC) ,
  INDEX `fk_productosespecialclientes_productos1_idx` (`productos_idProductos` ASC) ,
  CONSTRAINT `fk_productosespecialclientes_tipo clientes1`
    FOREIGN KEY (`tipoclientes_idTipo_Clientes` )
    REFERENCES `bdcc`.`tipo clientes` (`idTipo_Clientes` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_productosespecialclientes_productos1`
    FOREIGN KEY (`productos_idProductos` )
    REFERENCES `bdcc`.`productos` (`idProductos` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `bdcc`.`listaclientes`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `bdcc`.`listaclientes` (
  `idlistaclientes` INT NOT NULL AUTO_INCREMENT ,
  `clientes_idCliente` INT(11) NOT NULL ,
  `vendedores_idvendedores` INT(11) NOT NULL ,
  PRIMARY KEY (`idlistaclientes`) ,
  INDEX `fk_listaclientes_clientes1_idx` (`clientes_idCliente` ASC) ,
  INDEX `fk_listaclientes_vendedores1_idx` (`vendedores_idvendedores` ASC) ,
  CONSTRAINT `fk_listaclientes_clientes1`
    FOREIGN KEY (`clientes_idCliente` )
    REFERENCES `bdcc`.`clientes` (`idCliente` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_listaclientes_vendedores1`
    FOREIGN KEY (`vendedores_idvendedores` )
    REFERENCES `bdcc`.`vendedores` (`idvendedores` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `bdcc`.`descripcionclientes`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `bdcc`.`descripcionclientes` (
  `iddescripcionclientes` INT NOT NULL AUTO_INCREMENT ,
  `productos_idProductos` INT(11) NOT NULL ,
  `pedido_idpedido` INT(11) NOT NULL ,
  `clientes_idCliente` INT(11) NOT NULL ,
  `cantidad` INT NOT NULL ,
  `total` FLOAT NOT NULL ,
  PRIMARY KEY (`iddescripcionclientes`) ,
  INDEX `fk_descripcionclientes_productos1_idx` (`productos_idProductos` ASC) ,
  INDEX `fk_descripcionclientes_pedido1_idx` (`pedido_idpedido` ASC) ,
  INDEX `fk_descripcionclientes_clientes1_idx` (`clientes_idCliente` ASC) ,
  CONSTRAINT `fk_descripcionclientes_productos1`
    FOREIGN KEY (`productos_idProductos` )
    REFERENCES `bdcc`.`productos` (`idProductos` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_descripcionclientes_pedido1`
    FOREIGN KEY (`pedido_idpedido` )
    REFERENCES `bdcc`.`pedido` (`idpedido` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_descripcionclientes_clientes1`
    FOREIGN KEY (`clientes_idCliente` )
    REFERENCES `bdcc`.`clientes` (`idCliente` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `bdcc`.`produccion`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `bdcc`.`produccion` (
  `idproduccion` INT NOT NULL AUTO_INCREMENT ,
  `cantidad` INT NOT NULL ,
  `fecha` DATE NOT NULL ,
  `productos_idProductos` INT(11) NOT NULL ,
  PRIMARY KEY (`idproduccion`) ,
  INDEX `fk_produccion_productos1_idx` (`productos_idProductos` ASC) ,
  CONSTRAINT `fk_produccion_productos1`
    FOREIGN KEY (`productos_idProductos` )
    REFERENCES `bdcc`.`productos` (`idProductos` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

USE `bdcc` ;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
