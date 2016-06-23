/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package util.clases.vtaVenta;

import controller.almAlmacen.CCAlmacen;
import java.util.ArrayList;
import java.util.List;
import modelo.almAlmacen.entidad.CEAlmacen;
import modelo.almAlmacen.entidad.CESalidaProducto;
import modelo.almAlmacen.entidad.CESalidaProductoDetalle;
import modelo.vtaVenta.entidad.CEComprobanteVenta;
import modelo.vtaVenta.entidad.CEComprobanteVentaDetalle;
import view.FrmSistemaMenu;

/**
 *
 * @author Morales
 */
public class CLComprobanteVenta {
  ArrayList<CEAlmacen> LstAlmacen;
    public CLComprobanteVenta(){
    LstAlmacen=CCAlmacen.listarAlmacen();
    }

      public List<CESalidaProducto> getLstSalidaProducto(CEComprobanteVenta oCEComprobanteVenta,String TxtNumComp){


        List<CESalidaProducto> lstSalida=new ArrayList<CESalidaProducto>();
        List<CESalidaProductoDetalle> lstSalidaProductoDetalle ;
        CESalidaProductoDetalle oCESalidaProductoDetalle ;

        for(CEAlmacen oCEalmacen : LstAlmacen)
        {
            lstSalidaProductoDetalle = new ArrayList<CESalidaProductoDetalle>();
            for( CEComprobanteVentaDetalle oCEComprobanteDetalle : oCEComprobanteVenta.getoLstComprobanteDetalle())
                {

                    if(oCEComprobanteDetalle.getIdAlmacen()==oCEalmacen.getIdAlmacen()){
                    oCESalidaProductoDetalle= new CESalidaProductoDetalle();
                    oCESalidaProductoDetalle.setIdProducto(oCEComprobanteDetalle.getIdProducto());
                    oCESalidaProductoDetalle.setCantidad(oCEComprobanteDetalle.getCantidad());
                    oCESalidaProductoDetalle.setIdUnidadMedida(oCEComprobanteDetalle.getIdUnidadMedidaVenta());
                    lstSalidaProductoDetalle.add(oCESalidaProductoDetalle);
                    }
                }
                if(lstSalidaProductoDetalle.size()>0)
                {
                   CESalidaProducto oCESalidaProducto= ObtenerSalida(oCEComprobanteVenta, TxtNumComp, oCEalmacen.getIdAlmacen());
                   oCESalidaProducto.setLstSalidaDetalle(lstSalidaProductoDetalle);
                   lstSalida.add(oCESalidaProducto);
                }
          }

           
        return lstSalida;
            
}

   public CESalidaProducto ObtenerSalida(CEComprobanteVenta oCEComprobanteVenta,String TxtNumComp,int idAlmacen)
    {

        CESalidaProducto oCESalidaProducto= new CESalidaProducto();

       // oCESalidaProducto.setIdSalidaProducto(IdSalidaProducto);

        oCESalidaProducto.setIdTipoIngresoSalida(1);
        oCESalidaProducto.setNumeroDocumento(TxtNumComp);
        oCESalidaProducto.setIdSucursal(FrmSistemaMenu.getIdSucursal());
      //  oCESalidaProducto.setIdUltimoEstado(1);
        oCESalidaProducto.setIdAlmacen(idAlmacen);
         oCESalidaProducto.setIdCliente(oCEComprobanteVenta.getIdCliente());
        oCESalidaProducto.setIdEmpleado(FrmSistemaMenu.oCEUsuario.getIdEmpleado());
        oCESalidaProducto.setIdUsuario(FrmSistemaMenu.oCEUsuario.getIdUsuario());
        oCESalidaProducto.setIdPedido(oCEComprobanteVenta.getIdPedidoRef());
        oCESalidaProducto.setIdTipoComprobante(oCEComprobanteVenta.getIdTipoComprobanteVenta());

        return oCESalidaProducto;
    }


}
