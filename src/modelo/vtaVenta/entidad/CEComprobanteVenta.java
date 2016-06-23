package modelo.vtaVenta.entidad;

import java.util.List;

public class CEComprobanteVenta
{
  private long IdComprobanteVenta;
  private long IdComprobanteVentaRef;
  private int IdCondicion;
  private int UltimoIdEstado;
  private int UltimoIdEstadoComprobanteVenta;
  private int IdTipoComprobanteVenta;
  private String NumComprobante;
  private int IdEmpleado;
  private int IdUsuario;
  private long IdCliente;
  private int IdSucursal;
  private String Fecha;
  private String UltimaFecha;
  private long IdPedidoRef;
  private int IdMoneda;
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
  private float IGVActual;
  private float TipoCambio;
  private float MontoTotal;
  private float TotalPagar;
  private float MontoPercepcion;
  private float MontoExonerado;
  private float TasaPercepcion;
  private List<CEComprobanteVentaDetalle> oLstComprobanteDetalle;
  private CEFormaPago oCEFormaPago;
  private String Cliente;
  private String Empleado;
  private String Direccion;
  private String DNI;
  private String RUC="";
  private String TipoComprobante;
  private String NumPedido;
  private String Serie;
  private String Hora;


    public CEFormaPago getFormaPago() {
        return oCEFormaPago;
    }

    public void setFormaPago(CEFormaPago oCEIngresoFinaciero) {
        this.oCEFormaPago = oCEIngresoFinaciero;
    }

    public float getDescuentoEnSubtotal() {
        return DescuentoEnSubtotal;
    }

    public void setDescuentoEnSubtotal(float DescuentoEnSubtotal) {
        this.DescuentoEnSubtotal = DescuentoEnSubtotal;
    }

    public float getDescuentoEnValores() {
        return DescuentoEnValores;
    }

    public void setDescuentoEnValores(float DescuentoEnValores) {
        this.DescuentoEnValores = DescuentoEnValores;
    }

    public float getDescuentoTotal() {
        return DescuentoTotal;
    }

    public void setDescuentoTotal(float DescuentoTotal) {
        this.DescuentoTotal = DescuentoTotal;
    }

    public float getMontoExonerado() {
        return MontoExonerado;
    }

    public void setMontoExonerado(float MontoExonerado) {
        this.MontoExonerado = MontoExonerado;
    }

    public float getIGVActual() {
        return IGVActual;
    }

    public void setIGVActual(float IGVActual) {
        this.IGVActual = IGVActual;
    }

    public float getTasaPercepcion() {
        return TasaPercepcion;
    }

    public void setTasaPercepcion(float TasaPercepcion) {
        this.TasaPercepcion = TasaPercepcion;
    }

    public float getSubTotalNetoSinIGV() {
        return SubTotalNetoSinIGV;
    }

    public void setSubTotalNetoSinIGV(float SubTotalNetoSinIGV) {
        this.SubTotalNetoSinIGV = SubTotalNetoSinIGV;
    }

    public float getSubtotalBruto() {
        return SubtotalBruto;
    }

    public void setSubtotalBruto(float SubtotalBruto) {
        this.SubtotalBruto = SubtotalBruto;
    }

    public float getSubtotalNeto() {
        return SubtotalNeto;
    }

    public void setSubtotalNeto(float SubtotalNeto) {
        this.SubtotalNeto = SubtotalNeto;
    }

    /**
     * @return the IdComprobanteVenta
     */
    public long getIdComprobanteVenta() {
        return IdComprobanteVenta;
    }

    /**
     * @param IdComprobanteVenta the IdComprobanteVenta to set
     */
    public void setIdComprobanteVenta(long IdComprobanteVenta) {
        this.IdComprobanteVenta = IdComprobanteVenta;
    }

    /**
     * @return the IdComprobanteVentaRef
     */
    public long getIdComprobanteVentaRef() {
        return IdComprobanteVentaRef;
    }

    /**
     * @param IdComprobanteVentaRef the IdComprobanteVentaRef to set
     */
    public void setIdComprobanteVentaRef(long IdComprobanteVentaRef) {
        this.IdComprobanteVentaRef = IdComprobanteVentaRef;
    }

    /**
     * @return the IdCondicion
     */
    public int getIdCondicion() {
        return IdCondicion;
    }

    /**
     * @param IdCondicion the IdCondicion to set
     */
    public void setIdCondicion(int IdCondicion) {
        this.IdCondicion = IdCondicion;
    }

    /**
     * @return the UltimoIdEstado
     */
    public int getUltimoIdEstado() {
        return UltimoIdEstado;
    }

    /**
     * @param UltimoIdEstado the UltimoIdEstado to set
     */
    public void setUltimoIdEstado(int UltimoIdEstado) {
        this.UltimoIdEstado = UltimoIdEstado;
    }

    /**
     * @return the UltimoIdEstadoComprobanteVenta
     */
    public int getUltimoIdEstadoComprobanteVenta() {
        return UltimoIdEstadoComprobanteVenta;
    }

    /**
     * @param UltimoIdEstadoComprobanteVenta the UltimoIdEstadoComprobanteVenta to set
     */
    public void setUltimoIdEstadoComprobanteVenta(int UltimoIdEstadoComprobanteVenta) {
        this.UltimoIdEstadoComprobanteVenta = UltimoIdEstadoComprobanteVenta;
    }

    /**
     * @return the IdTipoComprobante
     */
    public int getIdTipoComprobanteVenta() {
        return IdTipoComprobanteVenta;
    }

    /**
     * @param IdTipoComprobante the IdTipoComprobante to set
     */
    public void setIdTipoComprobanteVenta(int IdTipoComprobante) {
        this.IdTipoComprobanteVenta = IdTipoComprobante;
    }

    /**
     * @return the NumComprobante
     */
    public String getNumComprobante() {
        return NumComprobante;
    }

    /**
     * @param NumComprobante the NumComprobante to set
     */
    public void setNumComprobante(String NumComprobante) {
        this.NumComprobante = NumComprobante;
    }

 
    public int getIdEmpleado() {
        return IdEmpleado;
    }
   public void setIdEmpleado(int IdEmpleado) {
        this.IdEmpleado = IdEmpleado;
    }
    public int getIdUsuario() {
        return IdUsuario;
    }

    public void setIdUsuario(int IdUsuario) {
        this.IdUsuario = IdUsuario;
    }

    public long getIdCliente() {
        return IdCliente;
    }


    public void setIdCliente(long IdCliente) {
        this.IdCliente = IdCliente;
    }

    public int getIdSucursal() {
        return IdSucursal;
    }

    public void setIdSucursal(int IdSucursal) {
        this.IdSucursal = IdSucursal;
    }

    public String getFecha() {
        return Fecha;
    }

    public void setFecha(String Fecha) {
        this.Fecha = Fecha;
    }

    public long getIdPedidoRef() {
        return IdPedidoRef;
    }

    public void setIdPedidoRef(long IdPedidoRef) {
        this.IdPedidoRef = IdPedidoRef;
    }

    public int getIdDescuento() {
        return IdDescuento;
    }

    public void setIdDescuento(int IdDescuento) {
        this.IdDescuento = IdDescuento;
    }

    /**
     * @return the IdMoneda
     */
    public int getIdMoneda() {
        return IdMoneda;
    }

    /**
     * @param IdMoneda the IdMoneda to set
     */
    public void setIdMoneda(int IdMoneda) {
        this.IdMoneda = IdMoneda;
    }

    /**
     * @return the SubTotalSinDescuento
     */
   
    /**
     * @return the Descuento
     */
    public float getDescuento() {
        return Descuento;
    }

    /**
     * @param Descuento the Descuento to set
     */
    public void setDescuento(float Descuento) {
        this.Descuento = Descuento;
    }

  
    /**
     * @return the IGV
     */
    public float getIGV() {
        return IGV;
    }

    /**
     * @param IGV the IGV to set
     */
    public void setIGV(float IGV) {
        this.IGV = IGV;
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


    public float getTipoCambio() {
        return TipoCambio;
    }

    public void setTipoCambio(float TipoCambio) {
        this.TipoCambio = TipoCambio;
    }


    public List<CEComprobanteVentaDetalle> getoLstComprobanteDetalle() {
        return oLstComprobanteDetalle;
    }

    /**
     * @param oLstComprobanteDetalle the oLstComprobanteDetalle to set
     */
    public void setoLstComprobanteDetalle(List<CEComprobanteVentaDetalle> oLstComprobanteDetalle) {
        this.oLstComprobanteDetalle = oLstComprobanteDetalle;
    }

    /**
     * @return the MontoTotal
     */
    public float getMontoTotal() {
        return MontoTotal;
    }

    /**
     * @param MontoTotal the MontoTotal to set
     */
    public void setMontoTotal(float MontoTotal) {
        this.MontoTotal = MontoTotal;
    }

    public String getUltimaFecha() {
        return UltimaFecha;
    }

    public void setUltimaFecha(String UltimaFecha) {
        this.UltimaFecha = UltimaFecha;
    }

    /**
     * @return the Cliente
     */
    public String getCliente() {
        return Cliente;
    }

    /**
     * @param Cliente the Cliente to set
     */
    public void setCliente(String Cliente) {
        this.Cliente = Cliente;
    }

    /**
     * @return the Empleado
     */
    public String getEmpleado() {
        return Empleado;
    }

    /**
     * @param Empleado the Empleado to set
     */
    public void setEmpleado(String Empleado) {
        this.Empleado = Empleado;
    }

    /**
     * @return the Direccion
     */
    public String getDireccion() {
        return Direccion;
    }

    /**
     * @param Direccion the Direccion to set
     */
    public void setDireccion(String Direccion) {
        this.Direccion = Direccion;
    }

    /**
     * @return the DNI
     */
    public String getDNI() {
        return DNI;
    }

    /**
     * @param DNI the DNI to set
     */
    public void setDNI(String DNI) {
        this.DNI = DNI;
    }

    /**
     * @return the TotalPagar
     */
    public float getTotalPagar()
    {
        return TotalPagar;
    }

    /**
     * @param TotalPagar the TotalPagar to set
     */
    public void setTotalPagar(float TotalPagar)
    {
        this.TotalPagar = TotalPagar;
    }

    public String getRUC() {
        
        if(RUC==null)
            return "";
        return RUC;
    }

    public void setRUC(String RUC) {
        this.RUC = RUC;
    }

    public String getTipoComprobante() {
        return TipoComprobante;
    }

    public void setTipoComprobante(String TipoComprobante) {
        this.TipoComprobante = TipoComprobante;
    }

    public String getNumPedido() {
        return NumPedido;
    }

    public void setNumPedido(String NumPedido) {
        this.NumPedido = NumPedido;
    }

    public float getMontoPercepcion() {
        return MontoPercepcion;
    }

    public void setMontoPercepcion(float MontoPercepcion) {
        this.MontoPercepcion = MontoPercepcion;
    }

    public String getHora() {
        return Hora;
    }

    public void setHora(String Hora) {
        this.Hora = Hora;
    }

    public String getSerie() {
        return Serie;
    }

    public void setSerie(String Serie) {
        this.Serie = Serie;
    }


}
