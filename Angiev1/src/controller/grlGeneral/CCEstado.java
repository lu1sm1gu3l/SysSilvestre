/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package controller.grlGeneral;

import java.util.ArrayList;
import modelo.grlGeneral.datos.CDEstado;
import modelo.grlGeneral.entidad.CEEstado;

/**
 *
 * @author pc25
 */
public class CCEstado {

  public static ArrayList<CEEstado> ListadoEstado(int pOpcion)
  {
    return CDEstado.ListadoEstado(pOpcion);// opcion 0: registros mas un reg adicional (todos) ; opcion 1: registros
  }

  public static ArrayList<CEEstado> ListadoEstadonventario()
  {
    return CDEstado.ListadoEstado(5);// opcion 0: registros mas un reg adicional (todos) ; opcion 1: registros
  }

  public static ArrayList<CEEstado> ListadoEstadoContableMasTodos()
  {
    return CDEstado.ListadoEstado(7);// opcion 0: registros mas un reg adicional (todos) ; opcion 1: registros
  }


  public static ArrayList<CEEstado> ListadoEstadoGuiaRemision()
  {
    return CDEstado.ListadoEstado(8);// opcion 0: registros mas un reg adicional (todos) ; opcion 1: registros
  }

  public static ArrayList<CEEstado> ListadoEstadProforma()
  {
    return CDEstado.ListadoEstado(9);// opcion 0: registros mas un reg adicional (todos) ; opcion 1: registros
  }
  public static ArrayList<CEEstado> ListadoEstadProformaMasTodos()
  {
    return CDEstado.ListadoEstado(11);// opcion 0: registros mas un reg adicional (todos) ; opcion 1: registros
  }
  public static  CEEstado consultarUltimoestadoPedido(long pIdPedido)
  {
      return  CDEstado.consultarUltimoEstado(pIdPedido, 1);
  }


  public static  CEEstado consultarUltimoestadoCompra_contable(long pId)
  {
      return  CDEstado.consultarUltimoEstado(pId, 2);
  }
  public static  CEEstado consultarUltimoestadoCompra_inventario(long pIdOC)
  {
      return  CDEstado.consultarUltimoEstado(pIdOC, 3);
  }

  public static  CEEstado consultarUltimoestadoIngreso(long pIdIngreso)
  {
      return  CDEstado.consultarUltimoEstado(pIdIngreso, 4);
  }
  public static  CEEstado consultarUltimoestadoSalida(long pIdIngreso)
  {
      return  CDEstado.consultarUltimoEstado(pIdIngreso, 5);
  }

  public static  CEEstado consultarUltimoestadoNCProv(long pIdNotaCredito)
  {
      return  CDEstado.consultarUltimoEstado(pIdNotaCredito, 6);
  }
  public static  CEEstado consultarUltimoestadoGuiaRemRecib(int pId)
  {
      return  CDEstado.consultarUltimoEstado(pId, 8);
  }
  public static ArrayList<CEEstado> ListadoEstadoCompras_Mas_Todos()
  {
    return CDEstado.ListadoEstado(8);// opcion 0: registros mas un reg adicional (todos) ; opcion 1: registros
  }
   public static  CEEstado consultarUltimoestadoProforma(int pIdProforma)
  {
      return  CDEstado.consultarUltimoEstado(pIdProforma, 7);
  }
    public static  CEEstado consultarUltimoestadoOrdenCompra(long pId)
  {
      return  CDEstado.consultarUltimoEstado(pId, 9);
  }
}
