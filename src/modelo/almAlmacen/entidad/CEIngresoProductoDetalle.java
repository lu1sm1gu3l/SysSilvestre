/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package modelo.almAlmacen.entidad;

/**
 *
 * @author Luiggi
 */
public class CEIngresoProductoDetalle {



  private long IdIngresoProductoDetalle;
  private long IngresoProducto;
  private long IdProducto;
  private int IdAlmacen;
  private int IdUnidadMedida;
  private float cantidad;
  private float costo;
  private float Importe;
  private String producto;
  private String UnidadMedida;
  private String Almacen;
  private String CodigoProducto;
  private boolean isSalida;
  private float cantidadAnterior;
  private long IdComprobanteCompraDetalle;
  private int IdGuiaRemisionRecibidoDetalle;

    public int getIdAlmacen() {
        return IdAlmacen;
    }

    public void setIdAlmacen(int IdAlmacen) {
        this.IdAlmacen = IdAlmacen;
    }

    public long getIdIngresoProductoDetalle() {
        return IdIngresoProductoDetalle;
    }

    public void setIdIngresoProductoDetalle(long IdIngresoProductoDetalle) {
        this.IdIngresoProductoDetalle = IdIngresoProductoDetalle;
    }

    public long getIdProducto() {
        return IdProducto;
    }

    public void setIdProducto(long IdProducto) {
        this.IdProducto = IdProducto;
    }

    public int getIdUnidadMedida() {
        return IdUnidadMedida;
    }

    public void setIdUnidadMedida(int IdUnidadMedida) {
        this.IdUnidadMedida = IdUnidadMedida;
    }

    public float getImporte() {
        return Importe;
    }

    public void setImporte(float Importe) {
        this.Importe = Importe;
    }

    public long getIngresoProducto() {
        return IngresoProducto;
    }

    public void setIngresoProducto(long IngresoProducto) {
        this.IngresoProducto = IngresoProducto;
    }

    public float getCantidad() {
        return cantidad;
    }

    public void setCantidad(float cantidad) {
        this.cantidad = cantidad;
    }

    public float getCosto() {
        return costo;
    }

    public void setCosto(float costo) {
        this.costo = costo;
    }

    public String getAlmacen() {
        return Almacen;
    }

    public void setAlmacen(String Almacen) {
        this.Almacen = Almacen;
    }

    public String getUnidadMedida() {
        return UnidadMedida;
    }

    public void setUnidadMedida(String UnidadMedida) {
        this.UnidadMedida = UnidadMedida;
    }

    public String getProducto() {
        return producto;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }

    public String getCodigoProducto() {
        return CodigoProducto;
    }

    public void setCodigoProducto(String CodigoProducto) {
        this.CodigoProducto = CodigoProducto;
    }


    public boolean isIsSalida() {
        return isSalida;
    }

    public void setIsSalida(boolean isSalida) {
        this.isSalida = isSalida;
    }

    public float getCantidadAnterior() {
        return cantidadAnterior;
    }

    public void setCantidadAnterior(float cantidadAnterior) {
        this.cantidadAnterior = cantidadAnterior;
    }

    public long getIdComprobanteCompraDetalle() {
        return IdComprobanteCompraDetalle;
    }

    public void setIdComprobanteCompraDetalle(long IdComprobanteCompraDetalle) {
        this.IdComprobanteCompraDetalle = IdComprobanteCompraDetalle;
    }

    public int getIdGuiaRemisionRecibidoDetalle() {
        return IdGuiaRemisionRecibidoDetalle;
    }

    public void setIdGuiaRemisionRecibidoDetalle(int IdGuiaRemisionRecibidoDetalle) {
        this.IdGuiaRemisionRecibidoDetalle = IdGuiaRemisionRecibidoDetalle;
    }


}
