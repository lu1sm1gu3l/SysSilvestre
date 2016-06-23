package modelo.vtaVenta.entidad;

import java.util.List;

public class CEFormaPago
{
    private int IdFormaPago;
    private int IdConceptoPago;
    private String NumDoc;
    private long IdCliente;
    private int IdMoneda;
    private float TipoCambio;
    private long IdComprobanteVenta;
    private String Fecha;
    private String Observacion;
    private float SubTotalNeto;
    private float SubTotalNetoSinIGV;
    private float IGVActual;
    private float IGV;
    private float ISC;
    private float MontoTotal;
    private float MontoPercepcion;
    private float VueltoTotal;
    private List<CEFormaPagoDetalle> LstFormaPagoDetalle;
    private int IdPuntoVenta;
    private int IdTipoComprobanteVenta;
    private int IdUsuario;
    private int IdEmpleado;
    private int IdSucursal;
    private float MontoSinCuentaCorriente;
    private float PagoTotal;
    private float PagoTotalSinCuentaCorriente;
    private float IngresoTotalLiquido;
    private float IngresoTotalLiquidoSinCuentaCorriente;

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

    public int getIdTipoComprobanteVenta() {
        return IdTipoComprobanteVenta;
    }

    public void setIdTipoComprobanteVenta(int IdTipoComprobanteVenta) {
        this.IdTipoComprobanteVenta = IdTipoComprobanteVenta;
    }

    public int getIdPuntoVenta() {
        return IdPuntoVenta;
    }

    /**
     * @param IdPuntoVenta the IdPuntoVenta to set
     */
    public void setIdPuntoVenta(int IdPuntoVenta) {
        this.IdPuntoVenta = IdPuntoVenta;
    }

    public List<CEFormaPagoDetalle> getoLstFormaPagoDetalle() {
        return LstFormaPagoDetalle;
    }

    public void setoLstFormaPagoDetalle(List<CEFormaPagoDetalle> oLstIngresoFinancieroDetalle) {
        this.LstFormaPagoDetalle = oLstIngresoFinancieroDetalle;
    }

    public String getFecha() {
        return Fecha;
    }

    public void setFecha(String Fecha) {
        this.Fecha = Fecha;
    }

    public float getIGV() {
        return IGV;
    }

    public void setIGV(float IGV) {
        this.IGV = IGV;
    }

    public float getIGVActual() {
        return IGVActual;
    }

    public void setIGVActual(float IGVActual) {
        this.IGVActual = IGVActual;
    }

    public float getISC() {
        return ISC;
    }

    public void setISC(float ISC) {
        this.ISC = ISC;
    }

    public long getIdCliente() {
        return IdCliente;
    }

    public void setIdCliente(long IdCliente) {
        this.IdCliente = IdCliente;
    }

    public long getIdComprobanteVenta() {
        return IdComprobanteVenta;
    }

    public void setIdComprobanteVenta(long IdComprobanteVenta) {
        this.IdComprobanteVenta = IdComprobanteVenta;
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

    public float getMontoTotal() {
        return MontoTotal;
    }

    public void setMontoTotal(float MontoTotal) {
        this.MontoTotal = MontoTotal;
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

    public float getSubTotalNeto() {
        return SubTotalNeto;
    }

    public void setSubTotalNeto(float SubTotalNeto) {
        this.SubTotalNeto = SubTotalNeto;
    }

    public float getSubTotalNetoSinIGV() {
        return SubTotalNetoSinIGV;
    }

    public void setSubTotalNetoSinIGV(float SubTotalNetoSinIGV) {
        this.SubTotalNetoSinIGV = SubTotalNetoSinIGV;
    }

    public float getTipoCambio() {
        return TipoCambio;
    }

    public void setTipoCambio(float TipoCambio) {
        this.TipoCambio = TipoCambio;
    }

    public float getVueltoTotal() {
        return VueltoTotal;
    }

    public void setVueltoTotal(float VueltoTotal) {
        this.VueltoTotal = VueltoTotal;
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

    /**
     * @return the IdFormaPago
     */
    public int getIdFormaPago() {
        return IdFormaPago;
    }

    /**
     * @param IdFormaPago the IdFormaPago to set
     */
    public void setIdFormaPago(int IdFormaPago) {
        this.IdFormaPago = IdFormaPago;
    }

    /**
     * @return the MontoSinCuentaCorriente
     */
    public float getMontoSinCuentaCorriente() {
        return MontoSinCuentaCorriente;
    }

    /**
     * @param MontoSinCuentaCorriente the MontoSinCuentaCorriente to set
     */
    public void setMontoSinCuentaCorriente(float MontoSinCuentaCorriente) {
        this.MontoSinCuentaCorriente = MontoSinCuentaCorriente;
    }

    /**
     * @return the IngresoTotalLiquido
     */
    public float getIngresoTotalLiquido() {
        return IngresoTotalLiquido;
    }

    /**
     * @param IngresoTotalLiquido the IngresoTotalLiquido to set
     */
    public void setIngresoTotalLiquido(float IngresoTotalLiquido) {
        this.IngresoTotalLiquido = IngresoTotalLiquido;
    }

    /**
     * @return the IngresoTotalLiquidoSinCuentaCorriente
     */
    public float getIngresoTotalLiquidoSinCuentaCorriente() {
        return IngresoTotalLiquidoSinCuentaCorriente;
    }

    /**
     * @param IngresoTotalLiquidoSinCuentaCorriente the IngresoTotalLiquidoSinCuentaCorriente to set
     */
    public void setIngresoTotalLiquidoSinCuentaCorriente(float IngresoTotalLiquidoSinCuentaCorriente) {
        this.IngresoTotalLiquidoSinCuentaCorriente = IngresoTotalLiquidoSinCuentaCorriente;
    }

    /**
     * @return the PagoTotal
     */
    public float getPagoTotal() {
        return PagoTotal;
    }

    /**
     * @param PagoTotal the PagoTotal to set
     */
    public void setPagoTotal(float PagoTotal) {
        this.PagoTotal = PagoTotal;
    }

    /**
     * @return the PagoTotalSinCuentaCorriente
     */
    public float getPagoTotalSinCuentaCorriente() {
        return PagoTotalSinCuentaCorriente;
    }

    /**
     * @param PagoTotalSinCuentaCorriente the PagoTotalSinCuentaCorriente to set
     */
    public void setPagoTotalSinCuentaCorriente(float PagoTotalSinCuentaCorriente) {
        this.PagoTotalSinCuentaCorriente = PagoTotalSinCuentaCorriente;
    }

    public float getMontoPercepcion() {
        return MontoPercepcion;
    }

    public void setMontoPercepcion(float MontoPercepcion) {
        this.MontoPercepcion = MontoPercepcion;
    }
    
}
