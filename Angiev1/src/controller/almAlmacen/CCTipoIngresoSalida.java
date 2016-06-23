package controller.almAlmacen;

import java.util.ArrayList;
import java.util.List;
import modelo.almAlmacen.datos.CDTipoIngresoSalida;
import modelo.almAlmacen.entidad.CETipoIngresoSalida;


public class CCTipoIngresoSalida
{

    public static ArrayList<CETipoIngresoSalida>  consultarListaTipoIngresosProductos()
      {
       return CDTipoIngresoSalida.consultarListaTipoIngresoSalida(1);
   }
   public static ArrayList<CETipoIngresoSalida>  consultarListaTipoSalidasProductos()
      {
       return CDTipoIngresoSalida.consultarListaTipoIngresoSalida(2);
   }

   public static ArrayList<CETipoIngresoSalida>  consultarTodoTipoIngresosProductos()
      {
       return CDTipoIngresoSalida.consultarListaTipoIngresoSalida(3);
   }
 public static ArrayList<CETipoIngresoSalida>  consultarTipoIngresosProductosMasTodos()
      {
       return CDTipoIngresoSalida.consultarListaTipoIngresoSalida(4);
   }
  public static ArrayList<CETipoIngresoSalida>  consultarTipoSalidasProductosMasTodos()
      {
       return CDTipoIngresoSalida.consultarListaTipoIngresoSalida(5);
   }

  public static ArrayList<CETipoIngresoSalida>  consultarTodoTipoSalidaProductos()
      {
       return CDTipoIngresoSalida.consultarListaTipoIngresoSalida(6);
   }

}
