package modelo.vtaVenta.entidad;
import java.util.List;

public class CEPedido {

  private long IdPedido;
  private long IdCliente;
  private int IdSucursal;
  private String Fecha;  
  private String FechaStr;  
  private String Codigo;
  private int IdTipoCambio;
  private int IdCondicion;
  private int IdMoneda;
  private int IdTipoComprobante;
  private int IdTipoDescuento;
  private float IGV;
  private float ISC;
  private float DescuentoEnValores;
  private float Descuento;
  private int IdDescuento;
  private float SubTotalNetoSinIGV;
  private float SubtotalNeto;
  private float SubtotalBruto;
  private float DescuentoEnSubtotal;
  private float DescuentoTotal;
  private float MontoTotal;
  private float MontoPercepcion;
  private float TotalPagar;
  private float IGVActual;
  private float TasaPercepcion;
  private float MontoExonerado;
  private int IdUltimoEstado;
  private int IdEmpleado;
  private float TipoCambio;
  private int IdUsuario;
  private String NumComprobante;
  private String RUC;
  private boolean ConPercepcion;
  private List<CEPedidoDetalle> LstPedidoDetalle;

   private String Observacion;

    public String getFechaStr() {
        return FechaStr;
    }

    public void setFechaStr(String FechaStr) {
        this.FechaStr = FechaStr;
    }

    public int getIdUsuario() {
        return IdUsuario;
    }

    public void setIdUsuario(int IdUsuario) {
        this.IdUsuario = IdUsuario;
    }

    public String getFecha() {
        return Fecha;
    }

    public String getCodigo() {
        return Codigo;
    }

    public void setCodigo(String Codigo) {
        this.Codigo = Codigo;
    }

    public float getDescuentoEnSubtotal() {
        return DescuentoEnSubtotal;
    }

    public void setDescuentoEnSubtotal(float DescuentoEnSubtotal) {
        this.DescuentoEnSubtotal = DescuentoEnSubtotal;
    }

    public float getDescuentoTotal() {
        return DescuentoTotal;
    }

    public void setDescuentoTotal(float DescuentoTotal) {
        this.DescuentoTotal = DescuentoTotal;
    }

    public float getSubtotalBruto() {
        return SubtotalBruto;
    }

    public void setSubtotalBruto(float SubtotalBruto) {
        this.SubtotalBruto = SubtotalBruto;
    }

    public float getMontoTotal() {
        return MontoTotal;
    }

    public void setMontoTotal(float MontoTotal) {
        this.MontoTotal = MontoTotal;
    }

    public float getTipoCambio() {
        return TipoCambio;
    }

    public void setTipoCambio(float TipoCambio) {
        this.TipoCambio = TipoCambio;
    }

    public void setFecha(String fecha) {
        this.Fecha = fecha;
    }

    public float getDescuento() {
        return Descuento;
    }

    public void setDescuento(float Descuento) {
        this.Descuento = Descuento;
    }

    public int getIdUltimoEstado() {
        return IdUltimoEstado;
    }

    public int getIdMoneda() {
        return IdMoneda;
    }

    public void setIdMoneda(int id_moneda) {
        this.IdMoneda = id_moneda;
    }

    public void setIdUltimoEstado(int id_UltimoEstado) {
        this.IdUltimoEstado = id_UltimoEstado;
    }

    public long getIdCliente() {
        return IdCliente;
    }

    public void setIdCliente(long id_cliente) {
        this.IdCliente = id_cliente;
    }

    public int getIdCondicion() {
        return IdCondicion;
    }

    public void setIdCondicion(int id_condicion) {
        this.IdCondicion = id_condicion;
    }

    public long getIdPedido() {
        return IdPedido;
    }

    public float getSubtotalNetoSinIGV() {
        return SubTotalNetoSinIGV;
    }

    public void setSubTotalNetoSinIGV(float subtotal_sin_igv) {
        this.SubTotalNetoSinIGV = subtotal_sin_igv;
    }

    public float getTotalPagar() {
        return TotalPagar;
    }

    public void setTotalPagar(float TotalPagar) {
        this.TotalPagar = TotalPagar;
    }



    public void setIdPedido(long id_pedido) {
        this.IdPedido = id_pedido;
    }

    public int getIdEmpleado() {
        return IdEmpleado;
    }

    public void setIdEmpleado(int id_vendedor) {
        this.IdEmpleado = id_vendedor;
    }


    public int getIdSucursal() {
        return IdSucursal;
    }

    public void setIdSucursal(int id_sucursal) {
        this.IdSucursal = id_sucursal;
    }

    public int getIdTipoCambio() {
        return IdTipoCambio;
    }

    public void setIdTipoCambio(int id_tipo_cambio) {
        this.IdTipoCambio = id_tipo_cambio;
    }

    public int getIdTipoDescuento() {
        return IdTipoDescuento;
    }

    public void setIdTipoDescuento(int id_tipo_descuento) {
        this.IdTipoDescuento = id_tipo_descuento;
    }

    public int getIdTipoComprobante() {
        return IdTipoComprobante;
    }

    public void setIdTipoComprobante(int id_tipocomprobante) {
        this.IdTipoComprobante = id_tipocomprobante;
    }

    public float getIGV() {
        return IGV;
    }

    public void setIGV(float igv) {
        this.IGV = igv;
    }

    public float getDescuentoValores() {
        return DescuentoEnValores;
    }

    public void setDescuentoEnValores(float descuento) {
        this.DescuentoEnValores = descuento;
    }

    public List<CEPedidoDetalle> getLstPedidoDetalle() {
        return LstPedidoDetalle;
    }

 
    public void setLstPedidoDetalle(List<CEPedidoDetalle> LstPedidoDetalle) {
        this.LstPedidoDetalle = LstPedidoDetalle;
    }

    /**
     * @return the ISC
     */
    public float getISC() {
        return ISC;
    }

    /**
     * @param ISC the ISC to set
     */
    public void setISC(float ISC) {
        this.ISC = ISC;
    }

    /**
     * @return the Subtotal
     */
    public float getSubTotalNeto() {
        return SubtotalNeto;
    }

    /**
     * @param SubtotalNeto the Subtotal to set
     */
    public void setSubTotalNeto(float SubtotalNeto) {
        this.SubtotalNeto = SubtotalNeto;
    }

    /**
     * @return the IdDescuento
     */
    public int getIdDescuento() {
        return IdDescuento;
    }

    /**
     * @param IdDescuento the IdDescuento to set
     */
    public void setIdDescuento(int IdDescuento) {
        this.IdDescuento = IdDescuento;
    }

    /**
     * @return the Observacion
     */
    public String getObservacion() {
        return Observacion;
    }

    /**
     * @param Observacion the Observacion to set
     */
    public void setObservacion(String Observacion) {
        this.Observacion = Observacion;
    }

    /**
     * @return the IGVActual
     */
    public float getIGVActual() {
        return IGVActual;
    }

    /**
     * @param IGVActual the IGVActual to set
     */
    public void setIGVActual(float IGVActual) {
        this.IGVActual = IGVActual;
    }

    public String getNumComprobante() {
        return NumComprobante;
    }

    public void setNumComprobante(String NumComprobante) {
        this.NumComprobante = NumComprobante;
    }

    public String getRUC() {
        return RUC;
    }

    public void setRUC(String RUC) {
        this.RUC = RUC;
    }

    public float getMontoPercepcion() {
        return MontoPercepcion;
    }

    public void setMontoPercepcion(float MontoPercepcion) {
        this.MontoPercepcion = MontoPercepcion;
    }

    public boolean isConPercepcion() {
        return ConPercepcion;
    }

    public void setConPercepcion(boolean ConPercepcion) {
        this.ConPercepcion = ConPercepcion;
    }

    public float getMontoExonerado() {
        return MontoExonerado;
    }

    public void setMontoExonerado(float MontoExonerado) {
        this.MontoExonerado = MontoExonerado;
    }

    public float getTasaPercepcion() {
        return TasaPercepcion;
    }

    public void setTasaPercepcion(float TasaPercepcion) {
        this.TasaPercepcion = TasaPercepcion;
    }
   


}
