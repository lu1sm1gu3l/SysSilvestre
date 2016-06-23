package controller.grlGeneral;

import modelo.grlGeneral.datos.CDAtencionSistema;
import view.FrmSistemaMenu;

public class CCAtencionSistema {

    public static int ConsultarAtencionSistema()
    {
        return CDAtencionSistema.ConsultarCondicionSistema();
    }

    public static int ConsultarSistema()
    {
        return CDAtencionSistema.ConsultaSistema();
    }
    public static boolean AperturarSistema()
    {
        return CDAtencionSistema.AperturaSistema(FrmSistemaMenu.oCEUsuario.getIdUsuario());
    }
     public static boolean CerrarSistema()
    {
        return CDAtencionSistema.CerrarSistema(FrmSistemaMenu.oCEUsuario.getIdUsuario());
    }
}
