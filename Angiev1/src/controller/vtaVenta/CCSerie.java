
package controller.vtaVenta;
import java.util.List;
import modelo.vtaVenta.datos.CDSerie;
import modelo.vtaVenta.entidad.CESerie;
import view.FrmSistemaMenu;

public class CCSerie
{     
    public static CESerie consultarSeriePorPuntoVenta(String pIp,int pIdTipoComprobante)
    {
       return CDSerie.consultarSeriePorPuntoVenta(pIp, pIdTipoComprobante,FrmSistemaMenu.getIdSucursal());
    }
    public static CESerie consultarImprimirSerie(int pIdPuntoVenta,int pIdTipoComprobante)
    {
       return CDSerie.consultarImprimirSerie(pIdPuntoVenta, pIdTipoComprobante);
    }
      public static CESerie consultarSeriePorPuntoCompra(String pIp,int pIdTipoComprobante)
    {
       return CDSerie.consultarSeriePorPuntoVenta(pIp, pIdTipoComprobante,FrmSistemaMenu.getIdSucursal());
    }
     public static CESerie consultarSeriePorIdPuntoVenta(int IdPunto,int pIdTipoComprobante)
    {
       return CDSerie.consultarSeriePorIdPuntoVenta(IdPunto, pIdTipoComprobante,FrmSistemaMenu.getIdSucursal());
    }


     public static List<CESerie> consultarSeriePorComp(int pIdTipoComprobante)
    {
       return CDSerie.consultarSeriePorComp(pIdTipoComprobante);
    }

     public static List<CESerie> consultarSerieComprobantePorIdPuntoVenta(int pIdPuntoVenta)
    {
         return CDSerie.consultarSerieComprobantePorIdPuntoVenta(pIdPuntoVenta);
     }

     public static boolean InsPuntoVentaComprobante(CESerie oCESerie)
    {
         return CDSerie.InsPuntoVentaComprobante(oCESerie);
     }
     public static boolean UPDPuntoVentaComprobante(CESerie oCESerie)
    {
         return CDSerie.UPDPuntoVentaComprobante(oCESerie);
     }

     public static boolean DELPuntoVentaComprobante(CESerie oCESerie)
    {
         return CDSerie.DELPuntoVentaComprobante(oCESerie);
     }

      public static int consultaExistePuntoComprobante(int pIdTipoComprobante,int pIdPuntoVenta)
    {
          return CDSerie.consultaExistePuntoComprobante(pIdTipoComprobante, pIdPuntoVenta);
      }
}
