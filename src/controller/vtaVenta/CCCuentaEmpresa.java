package controller.vtaVenta;

import java.util.List;
import modelo.vtaVenta.datos.CDCuentaEmpresa;
import modelo.vtaVenta.entidad.CECuentaEmpresa;
public class CCCuentaEmpresa
{
    public static List<CECuentaEmpresa> consultarCuentaEmpresaPorInstitucionFinancieraMoneda(int pIdInstituicionFinanciera,int pIdMoneda)
    {
       return CDCuentaEmpresa.listarCuentaEmpresa(pIdInstituicionFinanciera, pIdMoneda);
    }
}
