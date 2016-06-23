/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package modelo.vtaVenta.entidad;

/**
 *
 * @author Luiggi
 */
public class CEPedidoDetalle {



  private long IdPedidoDetalle;
  private long IdPedido;
  private long  IdProducto;
  private String UnidadMedida;
  private String Producto;
  private String CodigoProducto;
  private String TipoDescuento;
  private int IdAlmacen;
  private int IdUnidadMedidaVenta;
  private float Precio;
  private float PrecioSinRedon;
  private float Cantidad;
  private float CantidadBaseAnte;
  private float DescuentoEnValores;
  private float Descuento;
  private float PrecioConDesc;
  private int IdTipoDescuento;
  private  float ImporteSinDescuento;
  private  float ImporteConDescuento;
  private int IdUltimoEstado;
  private String Almacen;
  private float Exonerado;
  private boolean sinoImpuesto;
  private float CostoUnitario;
  private int idAbm;
  private float eqivalenciaUMB;
  private boolean conPercepcion;
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



    public int getIdAlmacen() {
        return IdAlmacen;
    }

    public void setIdAlmacen(int IdAlmacen) {
        this.IdAlmacen = IdAlmacen;
    }

    public long getIdPedido() {
        return IdPedido;
    }

    public void setIdPedido(long IdPedido) {
        this.IdPedido = IdPedido;
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


    public long getIdPedidoDetalle() {
        return IdPedidoDetalle;
    }

    public void setIdPedidoDetalle(long IdPedidoDetalle) {
        this.IdPedidoDetalle = IdPedidoDetalle;
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

    public float getCostoUnitario() {
        return CostoUnitario;
    }

    public void setCostoUnitario(float CostoUnitario) {
        this.CostoUnitario = CostoUnitario;
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
    
    public String getAlmacen() {
        return Almacen;
    }
    public void setAlmacen(String Almacen) {
        this.Almacen = Almacen;
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

    public float getPrecioSinRedon() {
        return PrecioSinRedon;
    }

    public void setPrecioSinRedon(float PrecioSinRedon) {
        this.PrecioSinRedon = PrecioSinRedon;
    }

    public int getIdAbm() {
        return idAbm;
    }

    public void setIdAbm(int idAbm) {
        this.idAbm = idAbm;
    }

    public float getEqivalenciaUMB() {
        return eqivalenciaUMB;
    }

    public void setEqivalenciaUMB(float eqivalenciaUMB) {
        this.eqivalenciaUMB = eqivalenciaUMB;
    }


    public float getPrecioConDesc() {
        return PrecioConDesc;
    }

    public void setPrecioConDesc(float PrecioConDesc) {
        this.PrecioConDesc = PrecioConDesc;
    }

    public boolean isConPercepcion() {
        return conPercepcion;
    }

    public void setConPercepcion(boolean conPercepcion) {
        this.conPercepcion = conPercepcion;
    }

    public float getCantidadBaseAnte() {
        return CantidadBaseAnte;
    }

    public void setCantidadBaseAnte(float CantidadAnte) {
        this.CantidadBaseAnte = CantidadAnte;
    }


}
