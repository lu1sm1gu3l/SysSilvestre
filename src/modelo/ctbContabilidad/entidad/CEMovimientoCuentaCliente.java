package modelo.ctbContabilidad.entidad;

public class CEMovimientoCuentaCliente
{
    private int IdMovimientoCuenta;
    private int IdCuentaCorriente;
    private long IdComprobanteVentaRef;
    private int IdTipoMovimiento;
    private int IdTipoTransaccion;
    private String TipoMovimiento;
    private String TipoTransaccion;
    private String Fecha;
    private String NumComprobanteVenta;
    private float Monto;
    private int IdEmpleado;
    private int IdUsuario;
    private int IdCliente;
    private int IdMoneda;
    private float TipoCambio;

    public long getIdComprobanteVentaRef() {
        return IdComprobanteVentaRef;
    }

    public void setIdComprobanteVentaRef(long IdComprobanteVentaRef) {
        this.IdComprobanteVentaRef = IdComprobanteVentaRef;
    }

    public int getIdCuentaCorriente() {
        return IdCuentaCorriente;
    }

    public void setIdCuentaCorriente(int IdCuentaCorriente) {
        this.IdCuentaCorriente = IdCuentaCorriente;
    }

    public int getIdMovimientoCuenta() {
        return IdMovimientoCuenta;
    }

    public void setIdMovimientoCuenta(int IdMovimientoCuenta) {
        this.IdMovimientoCuenta = IdMovimientoCuenta;
    }

    public int getIdTipoMovimiento() {
        return IdTipoMovimiento;
    }

    public void setIdTipoMovimiento(int IdTipoMovimiento) {
        this.IdTipoMovimiento = IdTipoMovimiento;
    }

    public int getIdTipoTransaccion() {
        return IdTipoTransaccion;
    }

    public void setIdTipoTransaccion(int IdTipoTransaccion) {
        this.IdTipoTransaccion = IdTipoTransaccion;
    }

    public String getTipoMovimiento() {
        return TipoMovimiento;
    }

    public void setTipoMovimiento(String TipoMovimiento) {
        this.TipoMovimiento = TipoMovimiento;
    }

    public String getTipoTransaccion() {
        return TipoTransaccion;
    }

    public void setTipoTransaccion(String TipoTransaccion) {
        this.TipoTransaccion = TipoTransaccion;
    }

    /**
     * @return the Fecha
     */
    public String getFecha() {
        return Fecha;
    }

    /**
     * @param Fecha the Fecha to set
     */
    public void setFecha(String Fecha) {
        this.Fecha = Fecha;
    }

    /**
     * @return the NumComprobanteVenta
     */
    public String getNumComprobanteVenta() {
        return NumComprobanteVenta;
    }

    /**
     * @param NumComprobanteVenta the NumComprobanteVenta to set
     */
    public void setNumComprobanteVenta(String NumComprobanteVenta) {
        this.NumComprobanteVenta = NumComprobanteVenta;
    }

    /**
     * @return the Monto
     */
    public float getMonto() {
        return Monto;
    }

    /**
     * @param Monto the Monto to set
     */
    public void setMonto(float Monto) {
        this.Monto = Monto;
    }

    /**
     * @return the IdUsuario
     */
    public int getIdUsuario() {
        return IdUsuario;
    }

    /**
     * @param IdUsuario the IdUsuario to set
     */
    public void setIdUsuario(int IdUsuario) {
        this.IdUsuario = IdUsuario;
    }

    /**
     * @return the IdEmpleado
     */
    public int getIdEmpleado() {
        return IdEmpleado;
    }

    /**
     * @param IdEmpleado the IdEmpleado to set
     */
    public void setIdEmpleado(int IdEmpleado) {
        this.IdEmpleado = IdEmpleado;
    }

    /**
     * @return the IdCliente
     */
    public int getIdCliente() {
        return IdCliente;
    }

    /**
     * @param IdCliente the IdCliente to set
     */
    public void setIdCliente(int IdCliente) {
        this.IdCliente = IdCliente;
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


}
