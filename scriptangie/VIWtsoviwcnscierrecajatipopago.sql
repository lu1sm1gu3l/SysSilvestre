

DROP VIEW IF EXISTS tsoviwcnscierrecajatipopago;
CREATE VIEW tsoviwcnscierrecajatipopago AS 
 SELECT
  tsot07_cuadre_caja_tipo_pago.IdCuadreCaja,
  tsot07_cuadre_caja_tipo_pago.IdMoneda,
  vtat03_moneda.Descripcion as Moneda,
  tsot07_cuadre_caja_tipo_pago.IdTipoPago,
  ctbt06_tipo_pago.TipoPago,
  tsot07_cuadre_caja_tipo_pago.MontoTesoreria,
  tsot07_cuadre_caja_tipo_pago.MontoCuadre,
  tsot07_cuadre_caja_tipo_pago.MontoDiferencia
FROM
  tsot07_cuadre_caja_tipo_pago
  INNER JOIN vtat03_moneda
    ON tsot07_cuadre_caja_tipo_pago.IdMoneda = vtat03_moneda.IdMoneda
  INNER JOIN ctbt06_tipo_pago
    ON tsot07_cuadre_caja_tipo_pago.IdTipoPago = ctbt06_tipo_pago.IdTipoPago
  ;