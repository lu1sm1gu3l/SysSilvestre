package modelo.cmpCompra.entidad;


public class CEEgresoFinancieroDetalle
{
  private long IdEgresoFinancieroDetalle;
  private long IdEgresoFianciero;
  private int IdTipoPago;
  private float EgresoLiquido;
  private int IdInstituicionFinanciera;
  private int IdCuentaEmpresa;
  private String FechaCheque;
  private String CodigoAutorizacion;
  private String NumeroPago;
  private String NumeroCuenta;
  private int IdPuntoVenta;
  private String TipoPago;
  private String InstitucionFinanciera;
  private String Fecha;
  private String Usuario;
  private int IdUsuario;
  private float TipoCambio;
    public int getIdPuntoVenta() {
        return IdPuntoVenta;
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

    public long getIdEgresoFianciero() {
        return IdEgresoFianciero;
    }

    public void setIdEgresoFianciero(long IdEgresoFianciero) {
        this.IdEgresoFianciero = IdEgresoFianciero;
    }

    public long getIdEgresoFinancieroDetalle() {
        return IdEgresoFinancieroDetalle;
    }

    public void setIdEgresoFinancieroDetalle(long IdEgresoFinancieroDetalle) {
        this.IdEgresoFinancieroDetalle = IdEgresoFinancieroDetalle;
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

    public void setIdTipoPago(int IdTipoEgreso) {
        this.IdTipoPago = IdTipoEgreso;
    }



    public String getNumeroPago() {
        return NumeroPago;
    }

    public void setNumeroPago(String NumeroPago) {
        this.NumeroPago = NumeroPago;
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

    public String getTipoPago() {
        return TipoPago;
    }

    public void setTipoPago(String TipoPago) {
        this.TipoPago = TipoPago;
    }


    @Override
    public String toString() {
        return TipoPago;
    }

    /**
     * @return the EgresoLiquido
     */
    public float getEgresoLiquido() {
        return EgresoLiquido;
    }

    /**
     * @param EgresoLiquido the EgresoLiquido to set
     */
    public void setEgresoLiquido(float EgresoLiquido) {
        this.EgresoLiquido = EgresoLiquido;
    }

    public String getFecha() {
        return Fecha;
    }

    public void setFecha(String Fecha) {
        this.Fecha = Fecha;
    }

    public int getIdUsuario() {
        return IdUsuario;
    }

    public void setIdUsuario(int IdUsuario) {
        this.IdUsuario = IdUsuario;
    }

  

    public String getUsuario() {
        return Usuario;
    }

    public void setUsuario(String Usuario) {
        this.Usuario = Usuario;
    }

    public String getNumeroCuenta() {
        return NumeroCuenta;
    }

    public void setNumeroCuenta(String NumeroCuenta) {
        this.NumeroCuenta = NumeroCuenta;
    }
  
}
