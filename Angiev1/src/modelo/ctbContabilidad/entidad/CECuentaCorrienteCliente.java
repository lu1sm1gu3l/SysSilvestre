package modelo.ctbContabilidad.entidad;

import java.util.List;


public class CECuentaCorrienteCliente
{
    private int IdCuentaCorriente;
    private int IdCliente;
    private int IdMoneda;
    private float Saldo;
    private String Cliente;
    private String Moneda;
    private String Dni;
    private float TipoCambio;
    private List<CEMovimientoCuentaCliente> oLstMovimientoCuenta;
    private float SaldoEquivalenteSoles;
    public int getIdCliente()
    {
        return IdCliente;
    }

    public String getDni() {
        return Dni;
    }

    public void setDni(String Dni) {
        this.Dni = Dni;
    }

    public List<CEMovimientoCuentaCliente> getoLstMovimientoCuenta() {
        return oLstMovimientoCuenta;
    }

    public void setoLstMovimientoCuenta(List<CEMovimientoCuentaCliente> oLstMovimientoCuenta) {
        this.oLstMovimientoCuenta = oLstMovimientoCuenta;
    }

    public void setIdCliente(int IdCliente)
    {
        this.IdCliente = IdCliente;
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

    public String getCliente() {
        return Cliente;
    }

    public void setCliente(String Cliente) {
        this.Cliente = Cliente;
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
        return Cliente ;
    }

}
