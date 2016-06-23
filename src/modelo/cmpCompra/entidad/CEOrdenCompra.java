package modelo.cmpCompra.entidad;
import java.util.List;

public class CEOrdenCompra {




  private long IdOrdenCompra;
  private int IdProveedor;
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
  private float TotalPagar;
  private float IGVActual;
  private float MontoPercepcion;
  private boolean conPercepcion;
  private int IdUltimoEstado;
  private int IdEmpleado;
  private float TipoCambio;
  private int IdUsuario;
  private List<CEOrdenCompraDetalle> LstOrdenCompraDetalle;

   private String Observacion;

    public long getIdOrdenCompra() {
        return IdOrdenCompra;
    }

    public void setIdOrdenCompra(long IdOrdenCompra) {
        this.IdOrdenCompra = IdOrdenCompra;
    }

    public int getIdProveedor() {
        return IdProveedor;
    }

    public void setIdProveedor(int IdProveedor) {
        this.IdProveedor = IdProveedor;
    }

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

    public float getSubtotalNeto() {
        return SubtotalNeto;
    }

    public void setSubtotalNeto(float SubtotalNeto) {
        this.SubtotalNeto = SubtotalNeto;
    }


    public int getIdCondicion() {
        return IdCondicion;
    }

    public void setIdCondicion(int id_condicion) {
        this.IdCondicion = id_condicion;
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

    public List<CEOrdenCompraDetalle> getLstOrdenCompraDetalle() {
        return LstOrdenCompraDetalle;
    }

    public void setLstOrdenCompraDetalle(List<CEOrdenCompraDetalle> LstOrdenCompraDetalle) {
        this.LstOrdenCompraDetalle = LstOrdenCompraDetalle;
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

    public float getMontoPercepcion() {
        return MontoPercepcion;
    }

    public void setMontoPercepcion(float MontoPercepcion) {
        this.MontoPercepcion = MontoPercepcion;
    }

    public boolean isConPercepcion() {
        return conPercepcion;
    }

    public void setConPercepcion(boolean conPercepcion) {
        this.conPercepcion = conPercepcion;
    }

 




}
