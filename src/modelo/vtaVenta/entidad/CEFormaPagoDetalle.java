package modelo.vtaVenta.entidad;


public class CEFormaPagoDetalle
{
  private long IdFormaPagoDetalle;
  private long IdFormaPago;
  private int IdTipoPago;
  private float Monto;
  private float Vuelto;
  private float IngresoLiquido;
  private int IdInstituicionFinanciera;
  private int IdCuentaEmpresa;
  private String FechaCheque;
  private String CodigoAutorizacion;
  private String NumeroPago;
  private int IdPuntoVenta;
  private String FormaDePago;
  private String InstitucionFinanciera;
  private float TipoCambio;
    public int getIdPuntoVenta() {
        return IdPuntoVenta;
    }

    public String getFormaDePago() {
        return FormaDePago;
    }

    public void setFormaDePago(String FormaDePago) {
        this.FormaDePago = FormaDePago;
    }

    public String getInstitucionFinanciera() {
        return InstitucionFinanciera;
    }

    public void setInstitucionFinanciera(String InstitucionFinanciera) {
        this.InstitucionFinanciera = InstitucionFinanciera;
    }

    /**
     * @param IdPuntoVenta the IdPuntoVenta to set
     */
    public void setIdPuntoVenta(int IdPuntoVenta) {
        this.IdPuntoVenta = IdPuntoVenta;
    }

    public String getCodigoAutorizacion() {
        return CodigoAutorizacion;
    }

    public void setCodigoAutorizacion(String CodigoAutorizacion) {
        this.CodigoAutorizacion = CodigoAutorizacion;
    }

    public String getFechaCheque() {
        return FechaCheque;
    }

    public void setFechaCheque(String FechaCheque) {
        this.FechaCheque = FechaCheque;
    }

    public int getIdCuentaEmpresa() {
        return IdCuentaEmpresa;
    }

    public void setIdCuentaEmpresa(int IdCuentaEmpresa) {
        this.IdCuentaEmpresa = IdCuentaEmpresa;
    }

    public long getIdFormaPago() {
        return IdFormaPago;
    }

    public void setIdFormaPago(long IdIngresoFinanciero) {
        this.IdFormaPago = IdIngresoFinanciero;
    }

    public long getIdFormaPagoDetalle() {
        return IdFormaPagoDetalle;
    }

    public void setIdFormaPagoDetalle(long IdIngresoFinancieroDetalle) {
        this.IdFormaPagoDetalle = IdIngresoFinancieroDetalle;
    }

    public int getIdInstituicionFinanciera() {
        return IdInstituicionFinanciera;
    }

    public void setIdInstituicionFinanciera(int IdInstituicionFinanciera) {
        this.IdInstituicionFinanciera = IdInstituicionFinanciera;
    }

    public int getIdTipoPago() {
        return IdTipoPago;
    }

    public void setIdTipoPago(int IdTipoIngreso) {
        this.IdTipoPago = IdTipoIngreso;
    }

    public float getMonto() {
        return Monto;
    }

    public void setMonto(float Monto) {
        this.Monto = Monto;
    }

    public String getNumeroPago() {
        return NumeroPago;
    }

    public void setNumeroPago(String NumeroPago) {
        this.NumeroPago = NumeroPago;
    }

    public float getVuelto() {
        return Vuelto;
    }

    public void setVuelto(float Vuelto) {
        this.Vuelto = Vuelto;
    }

    /**
     * @return the TipoCambio
     */
    public float getTipoCambio() {
        return TipoCambio;
    }

    /**
     * @param TipoCambio the TipoCambio to set
     */
    public void setTipoCambio(float TipoCambio) {
        this.TipoCambio = TipoCambio;
    }

    @Override
    public String toString() {
        return FormaDePago;
    }

    /**
     * @return the IngresoLiquido
     */
    public float getIngresoLiquido() {
        return IngresoLiquido;
    }

    /**
     * @param IngresoLiquido the IngresoLiquido to set
     */
    public void setIngresoLiquido(float IngresoLiquido) {
        this.IngresoLiquido = IngresoLiquido;
    }
  
}
