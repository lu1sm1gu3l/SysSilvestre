/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package modelo.almAlmacen.entidad;

/**
 *
 * @author Luiggi
 */
public class CESalidaProductoDetalle {



  private long IdSalidaProductoDetalle;
  private long SalidaProducto;
  private long IdProducto;
  private int IdAlmacen;
  private int IdUnidadMedida;
  private float cantidad;
  private float cantidadBase;
  private float costo;
  private float Importe;
  private String Producto;
  private String Almacen;
  private String UnidadMedida;
  private String CodigoProducto;
  private float CantidadAnterior;
  private boolean salida;
    public int getIdAlmacen() {
        return IdAlmacen;
    }

    public void setIdAlmacen(int IdAlmacen) {
        this.IdAlmacen = IdAlmacen;
    }

    public long getIdSalidaProductoDetalle() {
        return IdSalidaProductoDetalle;
    }

    public void setIdSalidaProductoDetalle(long IdSalidaProductoDetalle) {
        this.IdSalidaProductoDetalle = IdSalidaProductoDetalle;
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

    public long getSalidaProducto() {
        return SalidaProducto;
    }

    public void setSalidaProducto(long SalidaProducto) {
        this.SalidaProducto = SalidaProducto;
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

    public String getProducto() {
        return Producto;
    }

    public void setProducto(String Producto) {
        this.Producto = Producto;
    }

    public String getUnidadMedida() {
        return UnidadMedida;
    }

    public void setUnidadMedida(String UnidadMedida) {
        this.UnidadMedida = UnidadMedida;
    }

    public String getCodigoProducto() {
        return CodigoProducto;
    }

    public void setCodigoProducto(String CodigoProducto) {
        this.CodigoProducto = CodigoProducto;
    }

    public float getCantidadAnterior() {
        return CantidadAnterior;
    }

    public void setCantidadAnterior(float CantidadAnterior) {
        this.CantidadAnterior = CantidadAnterior;
    }

    public float getCantidadBase() {
        return cantidadBase;
    }

    public void setCantidadBase(float cantidadBase) {
        this.cantidadBase = cantidadBase;
    }

    public boolean isSalida() {
        return salida;
    }

    public void setSalida(boolean salida) {
        this.salida = salida;
    }

  
 

}
