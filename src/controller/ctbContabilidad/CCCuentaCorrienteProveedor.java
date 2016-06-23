

package controller.ctbContabilidad;

import java.util.List;
import modelo.ctbContabilidad.datos.CDCuentaCorrienteProveedor;
import modelo.ctbContabilidad.entidad.CECuentaCorrienteProveedor;
import modelo.ctbContabilidad.entidad.CEMovimientoCuentaProveedor;

public class CCCuentaCorrienteProveedor {

    public static CECuentaCorrienteProveedor consultarSaldoCuentaCorrientePorProveedor(int IdProveedor,int IdMoneda)
    {
        return CDCuentaCorrienteProveedor.consultarCuentaCorrientePorProveedor(IdProveedor, IdMoneda);
    }
    public static CECuentaCorrienteProveedor consultarCuentaCorrientePorProveedorConDetalle(int IdProveedor,int IdMoneda)
    {
        return CDCuentaCorrienteProveedor.consultarCuentaCorrientePorProveedorConDetalle(IdProveedor, IdMoneda);
    }

    public static List<CECuentaCorrienteProveedor> listarCuentaCorriente()
    {
        return CDCuentaCorrienteProveedor.listarCuentaCorriente();
    }
       public static List<CECuentaCorrienteProveedor> listarCuentaCorrienteConFiltro(String parametro)
    {
        return CDCuentaCorrienteProveedor.listarCuentaCorrienteConFiltro(parametro);
    }
    public static boolean registrarDepositoCuentaCorriente(CEMovimientoCuentaProveedor oCEMovimientoCuenta)
    {
        return CDCuentaCorrienteProveedor.registrarDepositoEnCuentaCorriente(oCEMovimientoCuenta);
    }
     public static boolean registrarNuevaCuentaCorriente(CECuentaCorrienteProveedor oCECuentaCorriente)
    {
        return CDCuentaCorrienteProveedor.registrarNuevaCuentaCorriente(oCECuentaCorriente);
    }
}
