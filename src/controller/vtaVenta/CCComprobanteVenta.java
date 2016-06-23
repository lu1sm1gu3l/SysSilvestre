package controller.vtaVenta;


import java.util.List;
import modelo.vtaVenta.datos.CDComprobanteVenta;
import modelo.vtaVenta.entidad.CEComprobanteVenta;
import modelo.vtaVenta.entidad.CEComprobanteVentaDetalle;
import modelo.vtaVenta.entidad.CEComprobanteVentaMatriz;

public class CCComprobanteVenta
{
    public static String RegistrarComprobanteVenta(CEComprobanteVenta oCEComprobanteVenta)
    {
       return CDComprobanteVenta.InsComprobanteVenta(oCEComprobanteVenta);
    }
    public static CEComprobanteVentaMatriz ListarComprobanteVentaPorId(long pIdComprobante)
    {
        return CDComprobanteVenta.ListarComprobanteVentaPorId(pIdComprobante,1);
    }
    public static CEComprobanteVentaMatriz ListarComprobanteVentaPorId_Y_Pedido(long pIdComprobante)
    {
        return CDComprobanteVenta.ListarComprobanteVentaPorId(pIdComprobante,2);
    }
    public static List<CEComprobanteVentaMatriz> BuscarAvanzadoComprobanteVenta(String pNum_Fecha,int idEstado,int idTipoComp,String FechaFin,boolean condecuento)
    {
        return CDComprobanteVenta.BuscarComprobanteVenta(1, pNum_Fecha, idEstado,idTipoComp,0,FechaFin,condecuento);
    }
    public static List<CEComprobanteVentaMatriz> BuscarComprobantePorCliente(long id_cliente)
    {
        return CDComprobanteVenta.BuscarComprobanteVenta(2, null, 0,0,id_cliente,null,false);
    }
   
    public static List<CEComprobanteVentaMatriz> BuscarComprobantePorNum(String pDato)
    {
        return CDComprobanteVenta.BuscarComprobanteVenta(3, pDato, 0,0,0,"",false);
    }

        public static List<CEComprobanteVentaDetalle> BuscarAvanzadoComprobanteVentaDet(String pNum_Fecha,int idEstado,int idTipoComp,String FechaFin,boolean condecuento)
    {
        return CDComprobanteVenta.BuscarComprobanteVentaDetalle(1, pNum_Fecha, idEstado,idTipoComp,0,FechaFin,condecuento);
    }
    public static List<CEComprobanteVentaDetalle> BuscarComprobantePorClienteDet(long id_cliente)
    {
        return CDComprobanteVenta.BuscarComprobanteVentaDetalle(2, null, 0,0,id_cliente,null,false);
    }

    public static List<CEComprobanteVentaDetalle> BuscarComprobantePorNumDet(String pDato)
    {
        return CDComprobanteVenta.BuscarComprobanteVentaDetalle(3, pDato, 0,0,0,"",false);
    }

    public static List<CEComprobanteVentaMatriz> ConsultarAuditoria(long id)
    {
        return CDComprobanteVenta.ConsultarAuditoria(id);
    }
    public static int AnularComprobanteVenta(CEComprobanteVentaMatriz oCEComprobanteVentaMatriz)
    {
        return CDComprobanteVenta.AnularComprobanteVenta(oCEComprobanteVentaMatriz);

    }

}
