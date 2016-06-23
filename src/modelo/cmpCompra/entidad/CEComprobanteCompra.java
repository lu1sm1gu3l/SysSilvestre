package modelo.cmpCompra.entidad;
import java.util.Date;
import java.util.List;

public class CEComprobanteCompra
{
  private long IdComprobanteCompra;
  private long IdComprobanteCompraRef;
  private long IdOrdenCompra;
  private int IdCondicion;
  private int UltimoIdEstado;
  private String UltimaFecha;
  private int IdTipoComprobanteCompra;
  private String NumComprobante;
  private int IdEmpleado;
  private int IdUsuario;
  private int IdProveedor;
  private int IdSucursal;
  private String Fecha;
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
  private float SaldoPagar;
  private List<CEComprobanteCompraDetalle> oLstComprobanteDetalle;
  private CEEgresoFinaciero oCEFormaPago;
  private String Proveedor;
  private String Empleado;
  private String Direccion;
  private String RUC;
  private String Observacion;
  private int IdPuntoVenta;
  private int IdTipoDescuento;
  private boolean CompraDirecta;
  private boolean conPercepcion;
  private float MontoPercepcion;
  private Date date;
  private int IdGuiaRemision;


    public CEEgresoFinaciero getFormaPago() {
        return oCEFormaPago;
    }

    public void setFormaPago(CEEgresoFinaciero oCEIngresoFinaciero) {
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

    public float getIGVActual() {
        return IGVActual;
    }

    public void setIGVActual(float IGVActual) {
        this.IGVActual = IGVActual;
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

    public float getSaldoPagar() {
        return SaldoPagar;
    }

    public void setSaldoPagar(float SaldoPagar) {
        this.SaldoPagar = SaldoPagar;
    }

    /**
     * @return the IdComprobanteCompra
     */
    public long getIdComprobanteCompra() {
        return IdComprobanteCompra;
    }

    /**
     * @param IdComprobanteCompra the IdComprobanteCompra to set
     */
    public void setIdComprobanteCompra(long IdComprobanteCompra) {
        this.IdComprobanteCompra = IdComprobanteCompra;
    }

    /**
     * @return the IdComprobanteCompraRef
     */
    public long getIdComprobanteCompraRef() {
        return IdComprobanteCompraRef;
    }

    /**
     * @param IdComprobanteCompraRef the IdComprobanteCompraRef to set
     */
    public void setIdComprobanteCompraRef(long IdComprobanteCompraRef) {
        this.IdComprobanteCompraRef = IdComprobanteCompraRef;
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

    public String getUltimaFecha() {
        return UltimaFecha;
    }

    public void setUltimaFecha(String UltimaFecha) {
        this.UltimaFecha = UltimaFecha;
    }

    
    public int getIdTipoComprobanteCompra() {
        return IdTipoComprobanteCompra;
    }

    /**
     * @param IdTipoComprobante the IdTipoComprobante to set
     */
    public void setIdTipoComprobanteCompra(int IdTipoComprobante) {
        this.IdTipoComprobanteCompra = IdTipoComprobante;
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

    public int getIdProveedor() {
        return IdProveedor;
    }


    public void setIdProveedor(int IdProveedor) {
        this.IdProveedor = IdProveedor;
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


    public List<CEComprobanteCompraDetalle> getoLstComprobanteDetalle() {
        return oLstComprobanteDetalle;
    }

    /**
     * @param oLstComprobanteDetalle the oLstComprobanteDetalle to set
     */
    public void setoLstComprobanteDetalle(List<CEComprobanteCompraDetalle> oLstComprobanteDetalle) {
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

    /**
     * @return the Proveedor
     */
    public String getProveedor() {
        return Proveedor;
    }

    /**
     * @param Proveedor the Proveedor to set
     */
    public void setProveedor(String Proveedor) {
        this.Proveedor = Proveedor;
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
    public String getRUC() {
        return RUC;
    }

    /**
     * @param DNI the DNI to set
     */
    public void setRUC(String DNI) {
        this.RUC = DNI;
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

    public int getIdPuntoVenta() {
        return IdPuntoVenta;
    }

    public void setIdPuntoVenta(int IdPuntoVenta) {
        this.IdPuntoVenta = IdPuntoVenta;
    }

    public int getIdTipoDescuento() {
        return IdTipoDescuento;
    }

    public void setIdTipoDescuento(int TipoDescuento) {
        this.IdTipoDescuento = TipoDescuento;
    }

    public boolean isCompraDirecta() {
        return CompraDirecta;
    }

    public void setCompraDirecta(boolean CompraDirecta) {
        this.CompraDirecta = CompraDirecta;
    }

    public long getIdOrdenCompra() {
        return IdOrdenCompra;
    }

    public void setIdOrdenCompra(long IdOrdenCompra) {
        this.IdOrdenCompra = IdOrdenCompra;
    }

    public String getObservacion() {
        return Observacion;
    }

    public void setObservacion(String Observacion) {
        this.Observacion = Observacion;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
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

    public int getIdGuiaRemision() {
        return IdGuiaRemision;
    }

    public void setIdGuiaRemision(int IdGuiaRemision) {
        this.IdGuiaRemision = IdGuiaRemision;
    }

   
  


}
