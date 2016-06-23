package modelo.cmpCompra.entidad;

import java.util.Date;
import java.util.List;

public class CEEgresoFinaciero
{
    private long IdEgresoFianciero;
    private int IdConceptoPago;
    private String NumDoc;
    private long IdProveedor;
    private int IdMoneda;
    private float TipoCambio;
    private long IdComprobanteCompra;
    private String Fecha;
    private Date Date;
    private String Observacion;
    private float IGVActual;
    private List<CEEgresoFinancieroDetalle> LstEgresoFinancieroDetalle;
    private int IdPuntoCompra;
    private int IdTipoComprobanteCompra;
    private int IdUsuario;
    private int IdEmpleado;
    private int IdSucursal;
    private float MontoPago;
    private float MontoTotal;
    private float EgresoTotalLiquido;
    private float MontoCuentaCorriente;
    private float MontoCuentaCorrienteAnte;
    private int IdPuntoVenta;
    private String Proveedor;
    private String RUC;
    private String Direccion;
    private String Empleado;
    private String Sucursal;
    private String UltimoEstado;
    private String TipoComprobante;
    private String Moneda;
    private int UltimoIdEstado;
    private float SaldoPagar;
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

    public int getIdTipoComprobanteCompra() {
        return IdTipoComprobanteCompra;
    }

    public void setIdTipoComprobanteCompra(int IdTipoComprobanteCompra) {
        this.IdTipoComprobanteCompra = IdTipoComprobanteCompra;
    }

    public int getIdPuntoCompra() {
        return IdPuntoCompra;
    }

    /**
     * @param IdPuntoCompra the IdPuntoCompra to set
     */
    public void setIdPuntoCompra(int IdPuntoCompra) {
        this.IdPuntoCompra = IdPuntoCompra;
    }

  

    public String getFecha() {
        return Fecha;
    }

    public void setFecha(String Fecha) {
        this.Fecha = Fecha;
    }

   

    public float getIGVActual() {
        return IGVActual;
    }

    public void setIGVActual(float IGVActual) {
        this.IGVActual = IGVActual;
    }

  

    public long getIdProveedor() {
        return IdProveedor;
    }

    public void setIdProveedor(long IdProveedor) {
        this.IdProveedor = IdProveedor;
    }

    public long getIdComprobanteCompra() {
        return IdComprobanteCompra;
    }

    public void setIdComprobanteCompra(long IdComprobanteCompra) {
        this.IdComprobanteCompra = IdComprobanteCompra;
    }

    public int getIdConceptoPago() {
        return IdConceptoPago;
    }

    public void setIdConceptoPago(int IdConceptoIngreso) {
        this.IdConceptoPago = IdConceptoIngreso;
    }

    

    public int getIdMoneda() {
        return IdMoneda;
    }

    public void setIdMoneda(int IdMoneda) {
        this.IdMoneda = IdMoneda;
    }

    public String getNumDoc() {
        return NumDoc;
    }

    public void setNumDoc(String NumDoc) {
        this.NumDoc = NumDoc;
    }

    public String getObservacion() {
        return Observacion;
    }

    public void setObservacion(String Observacion) {
        this.Observacion = Observacion;
    }

  

    public float getTipoCambio() {
        return TipoCambio;
    }

    public void setTipoCambio(float TipoCambio) {
        this.TipoCambio = TipoCambio;
    }

 

    /**
     * @return the IdSucursal
     */
    public int getIdSucursal() {
        return IdSucursal;
    }

    /**
     * @param IdSucursal the IdSucursal to set
     */
    public void setIdSucursal(int IdSucursal) {
        this.IdSucursal = IdSucursal;
    }

    public long getIdEgresoFianciero() {
        return IdEgresoFianciero;
    }

    public void setIdEgresoFianciero(long IdEgresoFianciero) {
        this.IdEgresoFianciero = IdEgresoFianciero;
    }

    /**
     * @return the IdFormaPago
     */


    /**
     * @return the MontoSinCuentaCorriente
     */


    public float getEgresoTotalLiquido() {
        return EgresoTotalLiquido;
    }

    public void setEgresoTotalLiquido(float EgresoTotalLiquido) {
        this.EgresoTotalLiquido = EgresoTotalLiquido;
    }

    public float getMontoCuentaCorriente() {
        return MontoCuentaCorriente;
    }

    public void setMontoCuentaCorriente(float MontoCuentaCorriente) {
        this.MontoCuentaCorriente = MontoCuentaCorriente;
    }


    public int getIdPuntoVenta() {
        return IdPuntoVenta;
    }

    public void setIdPuntoVenta(int IdPuntoVenta) {
        this.IdPuntoVenta = IdPuntoVenta;
    }

    public String getDireccion() {
        return Direccion;
    }

    public void setDireccion(String Direccion) {
        this.Direccion = Direccion;
    }

    public String getEmpleado() {
        return Empleado;
    }

    public void setEmpleado(String Empleado) {
        this.Empleado = Empleado;
    }

    public List<CEEgresoFinancieroDetalle> getLstEgresoFinancieroDetalle() {
        return LstEgresoFinancieroDetalle;
    }

    public void setLstEgresoFinancieroDetalle(List<CEEgresoFinancieroDetalle> LstEgresoFinancieroDetalle) {
        this.LstEgresoFinancieroDetalle = LstEgresoFinancieroDetalle;
    }



    public String getProveedor() {
        return Proveedor;
    }

    public void setProveedor(String Proveedor) {
        this.Proveedor = Proveedor;
    }

    public String getRUC() {
        return RUC;
    }

    public void setRUC(String RUC) {
        this.RUC = RUC;
    }

    public String getSucursal() {
        return Sucursal;
    }

    public void setSucursal(String Sucursal) {
        this.Sucursal = Sucursal;
    }

    public String getUltimoEstado() {
        return UltimoEstado;
    }

    public void setUltimoEstado(String UltimoEstado) {
        this.UltimoEstado = UltimoEstado;
    }

    public int getUltimoIdEstado() {
        return UltimoIdEstado;
    }

    public void setUltimoIdEstado(int UltimoIdEstado) {
        this.UltimoIdEstado = UltimoIdEstado;
    }

    public float getSaldoPagar() {
        return SaldoPagar;
    }

    public void setSaldoPagar(float SaldoPagar) {
        this.SaldoPagar = SaldoPagar;
    }

    public String getTipoComprobante() {
        return TipoComprobante;
    }

    public void setTipoComprobante(String TipoComprobante) {
        this.TipoComprobante = TipoComprobante;
    }

    public String getMoneda() {
        return Moneda;
    }

    public void setMoneda(String Moneda) {
        this.Moneda = Moneda;
    }

    public float getMontoPago() {
        return MontoPago;
    }

    public void setMontoPago(float MontoPago) {
        this.MontoPago = MontoPago;
    }

    public float getMontoTotal() {
        return MontoTotal;
    }

    public void setMontoTotal(float MontoTotal) {
        this.MontoTotal = MontoTotal;
    }

    public Date getDate() {
        return Date;
    }

    public void setDate(Date Date) {
        this.Date = Date;
    }

    public float getMontoCuentaCorrienteAnte() {
        return MontoCuentaCorrienteAnte;
    }

    public void setMontoCuentaCorrienteAnte(float MontoCuentaCorrienteAnte) {
        this.MontoCuentaCorrienteAnte = MontoCuentaCorrienteAnte;
    }
    
}
