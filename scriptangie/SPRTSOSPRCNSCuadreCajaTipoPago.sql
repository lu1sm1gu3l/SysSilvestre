
DROP PROCEDURE IF EXISTS TSOSPRCNSCuadreCajaTipoPago;
CREATE PROCEDURE TSOSPRCNSCuadreCajaTipoPago(IN pFecha VARCHAR(18), IN pIdPuntoVenta INT)
BEGIN

  DECLARE pIdCuadrecaja BIGINT;
  DECLARE pIdCuadrecajaTipoPago BIGINT;
   SET pIdCuadrecaja:=COALESCE((SELECT IdCuadreCaja FROM tsot01_cuadre_caja
                        WHERE date(FechaApertura)=pFecha AND IdPuntoVenta=pIdPuntoVenta),0);

  SET pIdCuadrecajaTipoPago:=COALESCE((SELECT IdCuadreCaja FROM tsot07_cuadre_caja_tipo_pago
                        WHERE IdCuadreCaja=pIdCuadrecaja
                        GROUP BY IdCuadreCaja),0);
         
  if  pIdCuadrecajaTipoPago =0 then 
   SELECT
            tif.Moneda,
            tif.IdMoneda,
            tifd.IdTipoPago,
            tifd.TipoPago,
            SUM(tifd.IngresoLiquido) as MontoCuadre,
            0.00 AS  MontoTesoreria ,
            0.00 AS MontoDiferencia,
            0 as IdCuadreaCaja
          FROM
             vtaviwcnsingresofinanciero tif,
             vtaviwcnsingresofinancierodetalle  tifd ,
              vtat08_comprobante_venta tcp

          WHERE
            date(tif.Fecha)=pFecha AND
            tif.IdPuntoVenta=pIdPuntoVenta AND
            tif.IdIngresoFinanciero=tifd.IdIngresoFinanciero  AND
            tif.IdComprobanteVenta=tcp.IdComprobanteVenta AND
            tcp.UltimoIdEstado!=4
            group by tifd.IdTipoPago,tif.IdMoneda 
            order by tif.IdMoneda,tifd.IdTipoPago
          ;
    ELSE
        SELECT
          tcc.Moneda,
          tcc.IdMoneda,
          tcc.IdTipoPago,
          tcc.TipoPago,
          tcc.MontoCuadre,
          tcc.MontoTesoreria,
          tcc.MontoDiferencia,
          tcc.IdCuadreCaja as IdCuadreaCaja
        
          FROM 
          tsoviwcnscierrecajatipopago tcc
          WHERE IdCuadreCaja=pIdCuadrecaja
          order by tcc.IdMoneda,tcc.IdTipoPago
          ;
    END IF;
END