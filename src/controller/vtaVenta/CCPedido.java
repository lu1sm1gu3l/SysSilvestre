package controller.vtaVenta;

import java.util.List;
import modelo.vtaVenta.datos.CDPedido;
import modelo.vtaVenta.entidad.CEPedido;
import modelo.vtaVenta.entidad.CEPedidoDetalle;
import modelo.vtaVenta.entidad.CEPedidoMatriz;

public class CCPedido
{

    public static String InsPedido(CEPedidoMatriz oCEPedido,boolean confecha)
    {
        return CDPedido.InsPedido( oCEPedido,confecha);
    }
    public static int UPDPedido(CEPedido oCEPedido)
    {
        return CDPedido.UPDPedido(oCEPedido);
    }
    public static int AnularPedido(CEPedidoMatriz oCEPedido)
    {
        return CDPedido.AnularPedido(oCEPedido);
    }
    public static List<CEPedidoMatriz> ListaPedidosPendientesUltimoDia(int pIdSucursal)
    {
     return CDPedido.ListarPedidosPendientes(1,pIdSucursal);
    }
    public static List<CEPedidoMatriz> ListaPedidosPendientesUltimoTresDias(int pIdSucursal)
    {
     return CDPedido.ListarPedidosPendientes(2,pIdSucursal);
    }
    public static List<CEPedidoMatriz> ListaPedidosPendientesTodos(int pIdSucursal)
    {
     return CDPedido.ListarPedidosPendientes(3,pIdSucursal);
    }
    public static CEPedidoMatriz ConsultarPedidosPorId(long id)
    {
        return CDPedido.ConsultarPedidosPorId(id);
    }

     public static List<CEPedidoMatriz> BuscarPedidoPorCliente(long id)
    {
        return CDPedido.ListarPedido("",3,id,0,0,null);
    }
     public static List<CEPedidoMatriz> BusqAvanzada(String pDato,int idestado,int idtipocomp,String pFechaFin)
    {
        return CDPedido.ListarPedido(pDato,4, 0,idestado,idtipocomp,pFechaFin);
    }

     public static List<CEPedidoMatriz> BusqNum(String pNum)
    {
        return CDPedido.ListarPedido(pNum,2,0,0,0,null);
    }
    public static List<CEPedidoDetalle> ConsultarPedidoDetalle(long pIdPedido)
    {
        return CDPedido.ListarPedidoDetalle(pIdPedido);
    }         
       public static float StockProductoPorPedido(long pIdProducto,int pIdALmacen)
    {
           return CDPedido.StockProductoPorPedido(pIdProducto, pIdALmacen);
    }
  
}
