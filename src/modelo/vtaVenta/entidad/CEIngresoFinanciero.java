package modelo.vtaVenta.entidad;

import java.util.List;

public class CEIngresoFinanciero
{
    private int IdFormaPago;
    private long IdIngresoFinanciero;
    private int IdConceptoIngreso;
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
    private float VueltoTotal;
    private List<CEIngresoFinancieroDetalle> oLstIngresoFinancieroDetalle;
    private int IdPuntoVenta;
    private int IdTipoComprobanteVenta;
    private int IdUsuario;
    private int IdEmpleado;
    private int IdSucursal;

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

    public List<CEIngresoFinancieroDetalle> getoLstIngresoFinancieroDetalle() {
        return oLstIngresoFinancieroDetalle;
    }

    public void setoLstIngresoFinancieroDetalle(List<CEIngresoFinancieroDetalle> oLstIngresoFinancieroDetalle) {
        this.oLstIngresoFinancieroDetalle = oLstIngresoFinancieroDetalle;
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

    public int getIdConceptoIngreso() {
        return IdConceptoIngreso;
    }

    public void setIdConceptoIngreso(int IdConceptoIngreso) {
        this.IdConceptoIngreso = IdConceptoIngreso;
    }

    public long getIdIngresoFinanciero() {
        return IdIngresoFinanciero;
    }

    public void setIdIngresoFinanciero(long IdIngresoFinanciero) {
        this.IdIngresoFinanciero = IdIngresoFinanciero;
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
    
}
