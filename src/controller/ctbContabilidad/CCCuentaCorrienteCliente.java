

package controller.ctbContabilidad;

import java.util.List;
import modelo.ctbContabilidad.datos.CDCuentaCorrienteCliente;
import modelo.ctbContabilidad.entidad.CECuentaCorrienteCliente;
import modelo.ctbContabilidad.entidad.CEMovimientoCuentaCliente;

public class CCCuentaCorrienteCliente {

    public static CECuentaCorrienteCliente consultarSaldoCuentaCorrientePorCliente(int IdCliente,int IdMoneda)
    {
        return CDCuentaCorrienteCliente.consultarCuentaCorrientePorCliente(IdCliente, IdMoneda);
    }
    public static CECuentaCorrienteCliente consultarCuentaCorrientePorClienteConDetalle(int IdCliente,int IdMoneda)
    {
        return CDCuentaCorrienteCliente.consultarCuentaCorrientePorClienteConDetalle(IdCliente, IdMoneda);
    }

    public static List<CECuentaCorrienteCliente> listarCuentaCorriente()
    {
        return CDCuentaCorrienteCliente.listarCuentaCorriente();
    }
       public static List<CECuentaCorrienteCliente> listarCuentaCorrienteConFiltro(String parametro)
    {
        return CDCuentaCorrienteCliente.listarCuentaCorrienteConFiltro(parametro);
    }
    public static boolean registrarDepositoCuentaCorriente(CEMovimientoCuentaCliente oCEMovimientoCuenta)
    {
        return CDCuentaCorrienteCliente.registrarDepositoEnCuentaCorriente(oCEMovimientoCuenta);
    }
     public static boolean registrarNuevaCuentaCorriente(CECuentaCorrienteCliente oCECuentaCorriente)
    {
        return CDCuentaCorrienteCliente.registrarNuevaCuentaCorriente(oCECuentaCorriente);
    }
}
