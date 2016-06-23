package controller.omeOperacionMonedaExtranjera;

import java.util.List;
import modelo.omeOperacionMonedaExtranjera.datos.CDCambioDia;
import modelo.omeOperacionMonedaExtranjera.entidad.CECambioDia;


public class CCCambioDia
{

       public static List<CECambioDia> consultarListaCambioDiasPorDescripcion(int pcns,String Fecha,int IdMoneda)
    {
       return CDCambioDia.consultarListaTipoCambioDia(pcns, Fecha, IdMoneda);
    }
       public static int INSUPDCambioDia(CECambioDia oCDUnidadMedidaVenta,int accionabm)
        {
           return CDCambioDia.INSUPDTipoCambioDia( oCDUnidadMedidaVenta,accionabm);
       }

        public static int DELCambioDia(long idCambioDia)
        {
           return CDCambioDia.DELCambioDia(idCambioDia);
       }

}
