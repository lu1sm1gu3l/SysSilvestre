package modelo.ctbContabilidad.entidad;

import java.util.List;


public class CECuentaCorrienteProveedor
{
    private int IdCuentaCorriente;
    private int IdProveedor;
    private int IdMoneda;
    private float Saldo;
    private String Proveedor;
    private String Moneda;
    private String Dni;
    private float TipoCambio;
    private List<CEMovimientoCuentaProveedor> oLstMovimientoCuenta;
    private float SaldoEquivalenteSoles;
    public int getIdProveedor()
    {
        return IdProveedor;
    }

    public String getDni() {
        return Dni;
    }

    public void setDni(String Dni) {
        this.Dni = Dni;
    }

    public List<CEMovimientoCuentaProveedor> getoLstMovimientoCuenta() {
        return oLstMovimientoCuenta;
    }

    public void setoLstMovimientoCuenta(List<CEMovimientoCuentaProveedor> oLstMovimientoCuenta) {
        this.oLstMovimientoCuenta = oLstMovimientoCuenta;
    }

    public void setIdProveedor(int IdProveedor)
    {
        this.IdProveedor = IdProveedor;
    }

    public int getIdCuentaCorriente()
    {
        return IdCuentaCorriente;
    }

    public void setIdCuentaCorriente(int IdCuentaCorriente)
    {
        this.IdCuentaCorriente = IdCuentaCorriente;
    }

    public int getIdMoneda()
    {
        return IdMoneda;
    }

    public void setIdMoneda(int IdMoneda)
    {
        this.IdMoneda = IdMoneda;
    }

    public float getSaldo()
    {
        return Saldo;
    }

    public void setSaldo(float saldo)
    {
        this.Saldo = saldo;
    }

    public String getProveedor() {
        return Proveedor;
    }

    public void setProveedor(String Proveedor) {
        this.Proveedor = Proveedor;
    }

 
    public String getMoneda() {
        return Moneda;
    }

    public void setMoneda(String Moneda) {
        this.Moneda = Moneda;
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

    /**
     * @return the SaldoEquivalenteSoles
     */
    public float getSaldoEquivalenteSoles() {
        return SaldoEquivalenteSoles;
    }

    /**
     * @param SaldoEquivalenteSoles the SaldoEquivalenteSoles to set
     */
    public void setSaldoEquivalenteSoles(float SaldoEquivalenteSoles) {
        this.SaldoEquivalenteSoles = SaldoEquivalenteSoles;
    }


    @Override
    public String toString() {
        return Proveedor ;
    }

}
