package modelo.vtaVenta.entidad;


public class CEIngresoFinancieroDetalle
{
  private long IdIngresoFinancieroDetalle;
  private long IdIngresoFinanciero;
  private int IdTipoIngreso;
  private float Monto;
  private float Vuelto;
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

    public long getIdIngresoFinanciero() {
        return IdIngresoFinanciero;
    }

    public void setIdIngresoFinanciero(long IdIngresoFinanciero) {
        this.IdIngresoFinanciero = IdIngresoFinanciero;
    }

    public long getIdIngresoFinancieroDetalle() {
        return IdIngresoFinancieroDetalle;
    }

    public void setIdIngresoFinancieroDetalle(long IdIngresoFinancieroDetalle) {
        this.IdIngresoFinancieroDetalle = IdIngresoFinancieroDetalle;
    }

    public int getIdInstituicionFinanciera() {
        return IdInstituicionFinanciera;
    }

    public void setIdInstituicionFinanciera(int IdInstituicionFinanciera) {
        this.IdInstituicionFinanciera = IdInstituicionFinanciera;
    }

    public int getIdTipoIngreso() {
        return IdTipoIngreso;
    }

    public void setIdTipoIngreso(int IdTipoIngreso) {
        this.IdTipoIngreso = IdTipoIngreso;
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
  
}
