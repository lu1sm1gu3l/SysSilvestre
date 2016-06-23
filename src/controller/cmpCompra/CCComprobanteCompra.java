package controller.cmpCompra;

import java.util.List;
import modelo.cmpCompra.datos.CDComprobanteCompra;
import modelo.cmpCompra.entidad.CEComprobanteCompra;
import modelo.cmpCompra.entidad.CEComprobanteCompraMatriz;
import modelo.cmpCompra.entidad.CEEgresoFinaciero;


public class CCComprobanteCompra
{

    public static boolean pagarCompra(CEEgresoFinaciero oCEFormaPAgoEgreso)
    {
         return CDComprobanteCompra.pagarCompra(oCEFormaPAgoEgreso);
    }

    public static CEComprobanteCompraMatriz InsComprobanteCompra(CEComprobanteCompraMatriz oCEComprobanteCompraMatriz)
    {
        return CDComprobanteCompra.InsComprobanteCompra( oCEComprobanteCompraMatriz);
    }
    public static CEComprobanteCompraMatriz ConsultarComprasPorId(long id)
    {
        return CDComprobanteCompra.ConsultarComprobanteComprasPorId(id,true);
    }
    public static CEComprobanteCompraMatriz ConsultarCompraSinDetallePorId(long id)
    {
        return CDComprobanteCompra.ConsultarComprobanteComprasPorId(id,false);
    }
    public static List<CEComprobanteCompraMatriz> BuscarComprobanteCompra(int pIdCns,String pDato,int idEstado)
    {
        return CDComprobanteCompra.BuscarComprobanteCompra(pIdCns, pDato, idEstado);
    }
    public static int AnularComprobanteCompra(CEComprobanteCompraMatriz oCEComprobanteCompraMatriz)
    {
        return CDComprobanteCompra.AnularComprobanteCompra(oCEComprobanteCompraMatriz);

    }


public static int UPDComprobanteCompra(CEComprobanteCompra oCEComprobanteCompra)
    {
        return CDComprobanteCompra.UPDComprobanteCompra(oCEComprobanteCompra,true);
    }

public static int UPDComprobanteCompraSinDet(CEComprobanteCompra oCEComprobanteCompra)
    {
        return CDComprobanteCompra.UPDComprobanteCompra(oCEComprobanteCompra,false);
    }


  public static CEComprobanteCompraMatriz ListarComprobanteComprasPorIdIngreso(long id)
    {
      return CDComprobanteCompra.ListarComprasPorIdIngreso(id,1);
    }

    public static CEComprobanteCompraMatriz ListarComprobanteComprasPorIdCompra(long id)
    {
      return CDComprobanteCompra.ListarComprasPorIdIngreso(id,2);
    }
  public static List<CEComprobanteCompraMatriz> BuscarOCPorProveedor(long id)
    {
        return CDComprobanteCompra.ListarComprobanteComprasPorCodigo("",3,id,0,0,0,null,false);
    }
     public static List<CEComprobanteCompraMatriz> BusqAvanzadaCompra(String pDato,int idestado, int idtipocomp,int idestadoinv,String pFechaFin,boolean pIsCompraDir)
    {
        return CDComprobanteCompra.ListarComprobanteComprasPorCodigo(pDato,4, 0,idestado,idtipocomp,idestadoinv,pFechaFin,pIsCompraDir);
    }


     public static List<CEComprobanteCompraMatriz> BuscarPorCodigo(String pDato)
    {
        return CDComprobanteCompra.ListarComprobanteComprasPorCodigo(pDato,2,0,0,0,0,null,false);
    }

     public static CEEgresoFinaciero ConsultarEgresoPorId(long id)
    {
      return CDComprobanteCompra.ConsultarEgresoPorId(id);
    }

      public static CEEgresoFinaciero ConsultarEgresoPorIdCompra(long id)
    {
      return CDComprobanteCompra.ConsultarEgresoPorId(id);
    }
}
