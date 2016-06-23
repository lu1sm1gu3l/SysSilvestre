package modelo.vtaVenta.entidad;

public class CEProformaMatriz extends CEProforma
{
    private String Cliente;
    private String Sucursal;
    private String TipoComprobante;
    private String Moneda;
    private String DNI;
    private String Direccion;
    private String Condicion;
    private String TipoDescuento;
    private String Empleado;
    private String UltimoEstado;
    public String getCliente()
    {
        return Cliente;
    }

    public void setCliente(String Cliente)
    {
        this.Cliente = Cliente;
    }

    public String getSucursal()
    {
        return Sucursal;
    }

    public String getUltimoEstado() {
        return UltimoEstado;
    }

    public void setUltimoEstado(String UltimoEstado) {
        this.UltimoEstado = UltimoEstado;
    }

    public void setSucursal(String Sucursal)
    {
        this.Sucursal = Sucursal;
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
     * @return the Condicion
     */
    public String getCondicion() {
        return Condicion;
    }

    /**
     * @param Condicion the Condicion to set
     */
    public void setCondicion(String Condicion) {
        this.Condicion = Condicion;
    }

    /**
     * @return the TipoDescuento
     */
    public String getTipoDescuento() {
        return TipoDescuento;
    }

    /**
     * @param TipoDescuento the TipoDescuento to set
     */
    public void setTipoDescuento(String TipoDescuento) {
        this.TipoDescuento = TipoDescuento;
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
    public String toString()
    {
        return this.Cliente;
    }
}
