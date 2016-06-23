/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package modelo.cmpCompra.entidad;

/**
 *
 * @author Luiggi
 */
public class CEOrdenCompraDetalle {



  private long IdOrdenCompraDetalle;
  private long IdOrdenCompra;
  private long  IdProducto;
  private String UnidadMedida;
  private String Producto;
  private String CodigoProducto;
  private String TipoDescuento;
  private int IdUnidadMedidaVenta;
  private float Precio;
  private float PrecioConDesc;
  private float Cantidad;
  private float DescuentoEnValores;
  private float Descuento;
  private int IdTipoDescuento;
  private  float ImporteSinDescuento;
  private  float ImporteSinDescuentoConIgv;
  private  float ImporteConDescuento;
  private int IdUltimoEstado;
  private float Exonerado;
  private boolean sinoImpuesto;
  private int IdAbm;

    public long getIdOrdenCompra() {
        return IdOrdenCompra;
    }

    public void setIdOrdenCompra(long IdOrdenCompra) {
        this.IdOrdenCompra = IdOrdenCompra;
    }

    public long getIdOrdenCompraDetalle() {
        return IdOrdenCompraDetalle;
    }

    public void setIdOrdenCompraDetalle(long IdOrdenCompraDetalle) {
        this.IdOrdenCompraDetalle = IdOrdenCompraDetalle;
    }
    public float getCantidad() {
        return Cantidad;
    }

    public void setCantidad(float cantidad) {
        this.Cantidad = cantidad;
    }

    public String getTipoDescuento() {
        return TipoDescuento;
    }

    public void setTipoDescuento(String TipoDescuento) {
        this.TipoDescuento = TipoDescuento;
    }

    public int getIdUnidadMedidaVenta() {
        return IdUnidadMedidaVenta;
    }

    public void setIdUnidadMedidaVenta(int Idquivalencia) {
        this.IdUnidadMedidaVenta = Idquivalencia;
    }




    public String getUnidadMedida() {
        return UnidadMedida;
    }



    public float getDescuentoEnValores() {
        return DescuentoEnValores;
    }

    public void setDescuentoEnValores(float descuentoEnValores) {
        this.DescuentoEnValores = descuentoEnValores;
    }

    public float getDescuento() {
        return Descuento;
    }

    public void setDescuento(float Descuento) {
        this.Descuento = Descuento;
    }

    public float getPrecio() {
        return Precio;
    }

    public void setPrecio(float precio) {
        this.Precio = precio;
    }


    public long getIdProducto() {
        return IdProducto;
    }

    public void setIdProducto(long IdProducto) {
        this.IdProducto = IdProducto;
    }

    public int getIdTipoDescuento() {
        return IdTipoDescuento;
    }

    public void setIdTipoDescuento(int IdTipoDescuento) {
        this.IdTipoDescuento = IdTipoDescuento;
    }

    public int getIdUltimoEstado() {
        return IdUltimoEstado;
    }

    public void setIdUltimoEstado(int IdUltimoEstado) {
        this.IdUltimoEstado = IdUltimoEstado;
    }

    public String getProducto() {
        return Producto;
    }

    public void setProducto(String producto) {
        this.Producto = producto;
    }
    public float getImporteConDescuento() {
        return ImporteConDescuento;
    }

    public void setImporteConDescuento(float SubTotalConDescuento) {
        this.ImporteConDescuento = SubTotalConDescuento;
    }

   

    public float getImportelSinDescuento() {
        return ImporteSinDescuento;
    }

    public void setImporteSinDescuento(float SubtotalSinDescuento) {
        this.ImporteSinDescuento = SubtotalSinDescuento;
    }



    public void setUnidadMedida(String unidadMedia) {
        this.UnidadMedida = unidadMedia;
    }

    public String getCodigoProducto() {
        return CodigoProducto;
    }

    public void setCodigoProducto(String CodigoProducto) {
        this.CodigoProducto = CodigoProducto;
    }

    @Override
    public String toString() {
        return ""+CodigoProducto;
    }
    

    public float getExonerado() {
        return Exonerado;
    }

    public void setExonerado(float Exonerado) {
        this.Exonerado = Exonerado;
    }

    public boolean isSinoImpuesto() {
        return sinoImpuesto;
    }

    public void setSinoImpuesto(boolean sinoImpuesto) {
        this.sinoImpuesto = sinoImpuesto;
    }

    public float getPrecioConDesc() {
        return PrecioConDesc;
    }

    public void setPrecioConDesc(float PrecioConDesc) {
        this.PrecioConDesc = PrecioConDesc;
    }


    public int getIdAbm() {
        return IdAbm;
    }

    public void setIdAbm(int IdAbm) {
        this.IdAbm = IdAbm;
    }

    public float getImporteSinDescuentoConIgv() {
        return ImporteSinDescuentoConIgv;
    }

    public void setImporteSinDescuentoConIgv(float ImporteSinDescuentoConIgv) {
        this.ImporteSinDescuentoConIgv = ImporteSinDescuentoConIgv;
    }




}
