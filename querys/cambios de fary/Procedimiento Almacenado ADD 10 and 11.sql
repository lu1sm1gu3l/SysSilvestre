DROP PROCEDURE IF EXISTS dbaynv1.ALMSPRCNSProducto;
CREATE DEFINER = 'root'@'%'
PROCEDURE dbaynv1.ALMSPRCNSProducto(
  pIdCns INT,
  pIdMarca INT,
  pIdSubfamilia INT,
  pIdProducto bigint,
  pParametro varchar(500))
BEGIN
  if pIdCns=1
  THEN
    select t05.IdProducto,
    t05.Descripcion,t05.Codigo,t05.SiNoImpuesto,t05.IdUnidadMedidaBase,t05.IdUnidadPresentacionVenta from almt05_producto t05 
      where t05.IdMarca=pIdMarca and t05.IdSubfamilia=pIdSubfamilia  and alm.estado=true ;
    else 
    if pIdCns=2
    THEN
            select IdUnidadBase,CantidadBase,IdUnidadPedido,CantidadPedido,t05.IdEquivalenciaUnidad
              from almt12_equivalencia_unidad t05 where t05.IdProducto=pIdProducto
             
              ;          
        else
        if pIdCns=3
        THEN
        select alm.IdProducto,
               alm.`Cod.Barra`,
               alm.`Cod.Producto`,   
               alm.Producto,
               alm.Marca,
               alm.UMBAbr,
               alm.`Und.Prest.`,
               alm.Subfamilia,
               alm.Familia,
               alm.Categoria,
               alm.Rubro,
               alm.SiNoImpuesto,
               alm.IdMarca,
               alm.IdSubfamilia,
               alm.IdFamilia,
               alm.IdCategoria,
               alm.IdRubro,
               alm.IdUnidadMedidaBase,
               alm.IdUnidadPresentacionVenta,
               alm.`PrecioReferencia`,
               alm.SiNoPercepcion
        from almviwcnsproducto alm
          where alm.estado=true
          order by alm.Producto asc;
          ELSE
          if pIdCns=4
        THEN
        select alm.IdProducto,
               alm.`Cod.Barra`,
               alm.`Cod.Producto`,
               alm.Producto,
               alm.Marca,
               alm.UMBAbr,
               alm.`Und.Prest.`,
               alm.Subfamilia,
               alm.Familia,
               alm.Categoria,
               alm.Rubro,
               alm.SiNoImpuesto,
               alm.IdMarca,
               alm.IdSubfamilia,
               alm.IdFamilia,
               alm.IdCategoria,
               alm.IdRubro,
               alm.IdUnidadMedidaBase,
               alm.IdUnidadPresentacionVenta,
               alm.`PrecioReferencia`,
               alm.SiNoPercepcion
        from almviwcnsproducto alm
          where alm.`Cod.Producto`like concat('%',pParametro,'%') and  alm.estado=true
          order by alm.Producto asc;
              ELSE
          if pIdCns=5
        THEN
        select alm.IdProducto,
               alm.`Cod.Barra`,
               alm.`Cod.Producto`,         
               alm.Producto,
               alm.Marca,
               alm.UMBAbr,
               alm.`Und.Prest.`,
               alm.Subfamilia,
               alm.Familia,
               alm.Categoria,
               alm.Rubro,
               alm.SiNoImpuesto,
               alm.IdMarca,
               alm.IdSubfamilia,
               alm.IdFamilia,
               alm.IdCategoria,
               alm.IdRubro,
               alm.IdUnidadMedidaBase,
               alm.IdUnidadPresentacionVenta,
               alm.`PrecioReferencia`,
               alm.SiNoPercepcion
        from almviwcnsproducto alm
          where alm.Marca like concat('%',pParametro,'%') and alm.estado=true
          order by alm.Producto asc;
                ELSE
          if pIdCns=6
        THEN
        select alm.IdProducto,
               alm.`Cod.Barra`,
               alm.`Cod.Producto`,
               alm.Producto,
               alm.Marca,
               alm.UMBAbr,
               alm.`Und.Prest.`,
               alm.Subfamilia,
               alm.Familia,
               alm.Categoria,
               alm.Rubro,
               alm.SiNoImpuesto,
               alm.IdMarca,
               alm.IdSubfamilia,
               alm.IdFamilia,
               alm.IdCategoria,
               alm.IdRubro,
               alm.IdUnidadMedidaBase,
               alm.IdUnidadPresentacionVenta,
               alm.`PrecioReferencia`,
               alm.SiNoPercepcion
        from almviwcnsproducto alm
          where alm.Producto like concat('%',pParametro,'%') and  alm.estado=true
          order by alm.Producto asc;
             ELSE
          if pIdCns=7
        THEN
        select alm.IdProducto,
               alm.`Cod.Barra`,
               alm.`Cod.Producto`,
               alm.Producto,
               alm.Marca,
               umv.Abreviatura,
               alm.`Und.Prest.`,
               alm.Subfamilia,
               alm.Familia,
               alm.Categoria,
               alm.Rubro,
               alm.SiNoImpuesto,
               alm.IdMarca,
               alm.IdSubfamilia,
               alm.IdFamilia,
               alm.IdCategoria,
               alm.IdRubro,
               alm.IdUnidadMedidaBase,
               umv.IdUnidadMedidaVenta,
               ump.PrecioReferencia,
               alm.SiNoPercepcion
        from almviwcnsproducto alm,almt15_unidad_medida_producto ump,almt08_unidad_medida_venta umv
          where alm.Producto like concat('%',pParametro,'%') and ump.IdProducto=alm.IdProducto and umv.IdUnidadMedidaVenta=ump.IdUnidadMedidaVenta
                  and  alm.estado=true
          order by alm.Producto,umv.Abreviatura asc;
              ELSE
          if pIdCns=8
        THEN
        select alm.IdProducto,
               alm.`Cod.Barra`,
               alm.`Cod.Producto`,              
               alm.Producto,
               alm.Marca,
               umv.Abreviatura,
               alm.`Und.Prest.`,
               alm.Subfamilia,
               alm.Familia,
               alm.Categoria,
               alm.Rubro,
               alm.SiNoImpuesto,
               alm.IdMarca,
               alm.IdSubfamilia,
               alm.IdFamilia,
               alm.IdCategoria,
               alm.IdRubro,
               alm.IdUnidadMedidaBase,
               alm.IdUnidadPresentacionVenta,
               ump.PrecioReferencia,
               alm.SiNoPercepcion
        from almviwcnsproducto alm,almt15_unidad_medida_producto ump,almt08_unidad_medida_venta umv
          where alm.`Cod.Producto`like concat('%',pParametro,'%') 
          and ump.IdProducto=alm.IdProducto and umv.IdUnidadMedidaVenta=ump.IdUnidadMedidaVenta and  alm.estado=true
          order by alm.Producto,umv.Abreviatura asc;
              ELSE
          if pIdCns=9
        THEN
        select alm.IdProducto,
               alm.`Cod.Barra`,
               alm.`Cod.Producto`,              
               alm.Producto,
               alm.Marca,
               umv.Abreviatura,
               alm.`Und.Prest.`,
               alm.Subfamilia,
               alm.Familia,
               alm.Categoria,
               alm.Rubro,
               alm.SiNoImpuesto,
               alm.IdMarca,
               alm.IdSubfamilia,
               alm.IdFamilia,
               alm.IdCategoria,
               alm.IdRubro,
               alm.IdUnidadMedidaBase,
               alm.IdUnidadPresentacionVenta,
               ump.PrecioReferencia,
               alm.SiNoPercepcion
        from almviwcnsproducto alm,almt15_unidad_medida_producto ump,almt08_unidad_medida_venta umv
          where alm.Marca like concat('%',pParametro,'%') and ump.IdProducto=alm.IdProducto 
          and umv.IdUnidadMedidaVenta=ump.IdUnidadMedidaVenta and  alm.estado=true
          order by alm.Producto,umv.Abreviatura asc;
      ELSE
            IF 
              pIdCns=10
              THEN
              select alm.IdProducto,
               alm.`Cod.Barra`,
               alm.`Cod.Producto`,           
               alm.Producto,
               alm.Marca,
               umv.Abreviatura,
               alm.`Und.Prest.`,
               alm.Subfamilia,
               alm.Familia,
               alm.Categoria,
               alm.Rubro,
               alm.SiNoImpuesto,
               alm.IdMarca,
               alm.IdSubfamilia,
               alm.IdFamilia,
               alm.IdCategoria,
               alm.IdRubro,
               alm.IdUnidadMedidaBase,
               alm.IdUnidadPresentacionVenta,
               ump.PrecioReferencia,
               alm.SiNoPercepcion
        from almviwcnsproducto alm,almt15_unidad_medida_producto ump,almt08_unidad_medida_venta umv
          where alm.`Cod.Barra`like concat('%',pParametro,'%') 
          and ump.IdProducto=alm.IdProducto and umv.IdUnidadMedidaVenta=ump.IdUnidadMedidaVenta and  alm.estado=true
          order by alm.Producto,umv.Abreviatura asc;
              Else
              IF 
                pIdCns=11
                THEN
                select alm.IdProducto,
               alm.`Cod.Barra`,
               alm.`Cod.Producto`,             
               alm.Producto,
               alm.Marca,
               alm.UMBAbr,
               alm.`Und.Prest.`,
               alm.Subfamilia,
               alm.Familia,
               alm.Categoria,
               alm.Rubro,
               alm.SiNoImpuesto,
               alm.IdMarca,
               alm.IdSubfamilia,
               alm.IdFamilia,
               alm.IdCategoria,
               alm.IdRubro,
               alm.IdUnidadMedidaBase,
               alm.IdUnidadPresentacionVenta,
               alm.`PrecioReferencia`,
               alm.SiNoPercepcion
        from almviwcnsproducto alm
          where alm.`Cod.Barra`like concat('%',pParametro,'%') and  alm.estado=true
          order by alm.Producto asc;

      END IF;               
      END IF;
      END IF;
      END IF;
      END IF;
      END IF;
      END IF;
      END IF;
      END IF;
   END IF;
 END IF;
END