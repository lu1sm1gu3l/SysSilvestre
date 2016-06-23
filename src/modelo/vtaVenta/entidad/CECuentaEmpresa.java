package modelo.vtaVenta.entidad;


public class CECuentaEmpresa
{
    private int IdCuentaEmpresa;
    private int IdMoneda;
    private String NumeroCuenta;
    private int IdInstitucionFinanciera;

    
    public int getIdCuentaEmpresa() {
        return IdCuentaEmpresa;
    }


    public void setIdCuentaEmpresa(int IdCuentaEmpresa) {
        this.IdCuentaEmpresa = IdCuentaEmpresa;
    }

    public int getIdMoneda() {
        return IdMoneda;
    }

    
    public void setIdMoneda(int IdMoneda) {
        this.IdMoneda = IdMoneda;
    }

   
    public String getNumeroCuenta() {
        return NumeroCuenta;
    }

    public void setNumeroCuenta(String NumeroCuenta) {
        this.NumeroCuenta = NumeroCuenta;
    }

  
    public int getIdInstitucionFinanciera() {
        return IdInstitucionFinanciera;
    }

    public void setIdInstitucionFinanciera(int IdInstitucionFinanciera) {
        this.IdInstitucionFinanciera = IdInstitucionFinanciera;
    }
    public String toString()
    {
        return this.NumeroCuenta;
    }
}
