CREATE OR REPLACE VIEW dbaynv1.almviwcnsproducto AS SELECT
  `almt05_producto`.`CodigoBarra` AS `Cod.Barra`,
  `almt05_producto`.`Codigo` AS `Cod.Producto`,
  `almt05_producto`.`Descripcion` AS `Producto`,
  `almt06_marca`.`Descripcion` AS `Marca`,
  `almt08_unidad_medida_venta`.`Abreviatura` AS `UMBAbr`,
  `almt08_unidad_medida_venta`.`Descripcion` AS `UMB`,
  `almt11_unidad_presentacion_venta`.`Descripcion` AS `Und.Prest.`,
  `almt01_rubro`.`Descripcion` AS `Rubro`,
  `almt02_categoria`.`Descripcion` AS `Categoria`,
  `almt03_familia`.`Descripcion` AS `Familia`,
  `almt04_subfamilia`.`Descripcion` AS `Subfamilia`,
  `almt05_producto`.`IdUnidadMedidaBase` AS `IdUnidadMedidaBase`,
  `almt05_producto`.`IdProducto` AS `IdProducto`,
  `almt05_producto`.`IdSubfamilia` AS `IdSubfamilia`,
  `almt05_producto`.`IdFamilia` AS `IdFamilia`,
  `almt05_producto`.`IdCategoria` AS `IdCategoria`,
  `almt05_producto`.`IdRubro` AS `IdRubro`,
  `almt05_producto`.`IdMarca` AS `IdMarca`,
  `almt05_producto`.`SiNoImpuesto` AS `SiNoImpuesto`,
  `almt05_producto`.`IdUnidadPresentacionVenta` AS `IdUnidadPresentacionVenta`,
  `almt05_producto`.`PrecioReferencia` AS `PrecioReferencia`,
  `almt05_producto`.`estado` AS `estado`,
  `almt05_producto`.`SiNoPercepcion` AS `SiNoPercepcion`
FROM
  (((((((`almt05_producto`
  JOIN `almt06_marca`
    ON ((`almt05_producto`.`IdMarca` = `almt06_marca`.`IdMarca`)))
  JOIN `almt04_subfamilia`
    ON ((`almt05_producto`.`IdSubfamilia` = `almt04_subfamilia`.`IdSubfamilia`)))
  JOIN `almt03_familia`
    ON ((`almt05_producto`.`IdFamilia` = `almt03_familia`.`IdFamilia`)))
  JOIN `almt02_categoria`
    ON ((`almt05_producto`.`IdCategoria` = `almt02_categoria`.`IdCategoria`)))
  JOIN `almt01_rubro`
    ON ((`almt05_producto`.`IdRubro` = `almt01_rubro`.`IdRubro`)))
  JOIN `almt08_unidad_medida_venta`
    ON ((`almt05_producto`.`IdUnidadMedidaBase` = `almt08_unidad_medida_venta`.`IdUnidadMedidaVenta`)))
  JOIN `almt11_unidad_presentacion_venta`
    ON ((`almt05_producto`.`IdUnidadPresentacionVenta` = `almt11_unidad_presentacion_venta`.`IdUnidadPresentacionVenta`)));