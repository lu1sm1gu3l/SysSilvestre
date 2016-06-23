package controller.grlGeneral;

import java.util.List;
import modelo.cmrComercial.entidad.CEEmpleado;
import modelo.grlGeneral.datos.CDUsuario;
import modelo.grlGeneral.entidad.CEUsuario;
import modelo.vtaVenta.entidad.CEPuntoVenta;

public class CCUsuario
{
    public static CEUsuario validarUsuario(CEUsuario oCEUsuario)
    {
        return CDUsuario.validarUsuario(oCEUsuario);
    }
    public static CEPuntoVenta consultarTerminal(CEUsuario oCEUsuario)
    {
        return CDUsuario.consultarTerminal(oCEUsuario);
    }
     public List<CEUsuario> buscarUsuario(String datoUsuario)
    {
        CDUsuario oCDUsuario = new CDUsuario();
        return oCDUsuario.buscarUsuario(1,datoUsuario);
    }

    public  List<CEEmpleado>buscarSujeto(String datoUsuario)
    {
       CDUsuario oUsuarioCD=new CDUsuario();
       return oUsuarioCD.buscarSujeto(2, datoUsuario);
    }

    public static List<CEUsuario > listaUsuario()
    {
        return  CDUsuario.Listado();
    }

    public static boolean  ejecutarProcedimientoAlta(CEUsuario oCEUsuario)
    {
        return CDUsuario.ABM(oCEUsuario, 1);
    }

    public static boolean ejecutarProcedimientoBM(int pintOperacion,CEUsuario poComponente)
    {
        return CDUsuario.ABM(poComponente, pintOperacion);
    }

    public static CEUsuario verificarClave(int pIdUsuario,String pPassw)
      {
        return CDUsuario.VerificarClave(pIdUsuario,pPassw);
    }

}
