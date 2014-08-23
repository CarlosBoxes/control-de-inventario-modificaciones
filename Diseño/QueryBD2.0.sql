USE `bdcc` ;
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

