package controller.vtaVenta;

import java.util.List;
import modelo.vtaVenta.datos.CDProforma;
import modelo.vtaVenta.entidad.CEProforma;
import modelo.vtaVenta.entidad.CEProformaDetalle;
import modelo.vtaVenta.entidad.CEProformaMatriz;

public class CCProforma
{

    public static String InsProforma(CEProformaMatriz oCEProforma)
    {
        return CDProforma.InsProforma( oCEProforma);
    }
    public static int UPDProforma(CEProforma oCEProforma)
    {
        return CDProforma.UPDProforma(oCEProforma);
    }
    public static int AnularProforma(CEProforma oCEProforma)
    {
        return CDProforma.InsProformaEstado(oCEProforma);
    }
    public static int ImprimirProforma(CEProforma oCEProforma)
    {
        return CDProforma.InsProformaEstado(oCEProforma);
    }
    public static List<CEProformaMatriz> ListaProformasPendientesUltimoDia(int pIdSucursal)
    {
     return CDProforma.ListarProformasPendientes(1,pIdSucursal);
    }
    public static List<CEProformaMatriz> ListaProformasPendientesUltimoTresDias(int pIdSucursal)
    {
     return CDProforma.ListarProformasPendientes(2,pIdSucursal);
    }
    public static List<CEProformaMatriz> ListaProformasPendientesTodos(int pIdSucursal)
    {
     return CDProforma.ListarProformasPendientes(3,pIdSucursal);
    }
    public static CEProformaMatriz ConsultarProformasPorId(long id)
    {
        return CDProforma.ConsultarProformasPorId(id);
    }

     public static List<CEProformaMatriz> BuscarProformaPorCliente(long id)
    {
        return CDProforma.ListarProforma("",3,id,0,0,null);
    }
     public static List<CEProformaMatriz> BusqAvanzada(String pDato,int idestado,int idtipocomp,String pFechaFin)
    {
        return CDProforma.ListarProforma(pDato,4, 0,idestado,idtipocomp, pFechaFin);
    }

     public static List<CEProformaMatriz> BusqNum(String pNum)
    {
        return CDProforma.ListarProforma(pNum,2,0,0,0,null);
    }
    public static List<CEProformaDetalle> ConsultarProformaDetalle(long pIdProforma)
    {
        return CDProforma.ListarProformaDetalle(pIdProforma);
    }         
       public static float StockProductoPorProforma(long pIdProducto,int pIdALmacen)
    {
           return CDProforma.StockProductoPorProforma(pIdProducto, pIdALmacen);
    }
  
}
