package controller.cmpCompra;

import java.util.List;
import modelo.cmpCompra.datos.CDOrdenCompra;
import modelo.cmpCompra.entidad.CEOrdenCompra;
import modelo.cmpCompra.entidad.CEOrdenCompraDetalle;
import modelo.cmpCompra.entidad.CEOrdenCompraMatriz;

public class CCOrdenCompra
{

    public static CEOrdenCompraMatriz InsOrdenCompra(CEOrdenCompraMatriz oCEOrdenCompra)
    {
        return CDOrdenCompra.InsOrdenCompra( oCEOrdenCompra);
    }
    public static int UPDOrdenCompra(CEOrdenCompra oCEOrdenCompra)
    {
        return CDOrdenCompra.UPDOrdenCompra(oCEOrdenCompra);
    }
    public static int AnularOrdenCompra(CEOrdenCompra oCEOrdenCompra)
    {
        return CDOrdenCompra.AnularOrdenCompra(oCEOrdenCompra);
    }
    public static int AprobarOrdenCompra(CEOrdenCompra oCEOrdenCompra)
    {
        return CDOrdenCompra.AprobarOrdenCompra(oCEOrdenCompra);
    }
    
    
    
   public static CEOrdenCompraMatriz ConsultarOrdenComprasPorId(long id)
    {
        return CDOrdenCompra.ConsultarOrdenComprasPorId(id);
    }

    public static List<CEOrdenCompraDetalle> ConsultarOrdenCompraDetalle(long pIdOrdenCompra)
    {
        return CDOrdenCompra.ListarOrdenCompraDetalle(pIdOrdenCompra);
    }
 
     public static List<CEOrdenCompraMatriz> BuscarOCPorProveedor(long id)
    {
        return CDOrdenCompra.ListarOrdenComprasPorCodigo("",3,id,0,0,null);
    }
     public static List<CEOrdenCompraMatriz> BusqAvanzadaOC(String pDato,int idestado, int idtipocomp,String FechaFin)
    {
        return CDOrdenCompra.ListarOrdenComprasPorCodigo(pDato,4, 0,idestado,idtipocomp,FechaFin);
    }

     public static List<CEOrdenCompraMatriz> BuscarOCPorCodigo(String pDato)
    {
        return CDOrdenCompra.ListarOrdenComprasPorCodigo(pDato,2,0,0,0,null);
    }

}
