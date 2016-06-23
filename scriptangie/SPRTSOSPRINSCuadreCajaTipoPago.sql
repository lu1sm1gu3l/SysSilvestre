
DROP PROCEDURE IF EXISTS TSOSPRINSCuadreCajaTipoPago;
CREATE PROCEDURE TSOSPRINSCuadreCajaTipoPago(
  pIdCuadreCaja BIGINT,
  pIdMoneda TINYINT,
  pIdTipoPago INT,
  pMontoTesoreria DECIMAL(10,2),
  pMontoCuadre DECIMAL(10,2),
  pMontoDiferencia DECIMAL(10,2))
BEGIN

 
  INSERT INTO tsot07_cuadre_caja_tipo_pago
    (IdCuadreCaja, IdMoneda, IdTipoPago,
    MontoTesoreria, MontoCuadre, MontoDiferencia) 
    VALUES(pIdCuadreCaja, pIdMoneda, pIdTipoPago,
    pMontoTesoreria, pMontoCuadre, pMontoDiferencia) 
    ;
    
END