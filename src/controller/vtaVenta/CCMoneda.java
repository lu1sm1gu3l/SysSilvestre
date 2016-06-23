package controller.vtaVenta;

import java.util.ArrayList;
import modelo.vtaVenta.datos.CDMoneda;
import modelo.vtaVenta.entidad.CEMoneda;


public class CCMoneda{


    public static ArrayList<CEMoneda> listaAll() {
      return CDMoneda.getAll(1);
    }
     public static ArrayList<CEMoneda> listaMonedaOperacionCambioDia() {
      return CDMoneda.getAll(2);
    }
      public static ArrayList<CEMoneda> listarMonedaConTipoCambio()
    {
          return CDMoneda.getMonedaConTipoCambio(3);
      }







    
}
