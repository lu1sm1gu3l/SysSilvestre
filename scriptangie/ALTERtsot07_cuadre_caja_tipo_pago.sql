
TRUNCATE tsot07_cuadre_caja_tipo_pago;
TRUNCATE tsot02_cuadre_caja_detalle;
TRUNCATE tsot01_cuadre_caja;

ALTER TABLE tsot07_cuadre_caja_tipo_pago MODIFY COLUMN IdTipoPago INTEGER DEFAULT NULL,
 DROP COLUMN IdTipoComprobante,
 DROP PRIMARY KEY,
 ADD PRIMARY KEY  USING BTREE(IdCuadreCaja, IdMoneda, IdTipoPago);
