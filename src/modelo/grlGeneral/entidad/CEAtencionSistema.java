package modelo.grlGeneral.entidad;

public class CEAtencionSistema
{
    private int IdAtencionSistema;
    private String FechaApertura;
    private int IdUsuarioApertura;
    private String FechaSalida;

    /**
     * @return the IdAtencionSistema
     */
    public int getIdAtencionSistema() {
        return IdAtencionSistema;
    }

    /**
     * @param IdAtencionSistema the IdAtencionSistema to set
     */
    public void setIdAtencionSistema(int IdAtencionSistema) {
        this.IdAtencionSistema = IdAtencionSistema;
    }

    /**
     * @return the FechaApertura
     */
    public String getFechaApertura() {
        return FechaApertura;
    }

    /**
     * @param FechaApertura the FechaApertura to set
     */
    public void setFechaApertura(String FechaApertura) {
        this.FechaApertura = FechaApertura;
    }

    /**
     * @return the IdUsuarioApertura
     */
    public int getIdUsuarioApertura() {
        return IdUsuarioApertura;
    }

    /**
     * @param IdUsuarioApertura the IdUsuarioApertura to set
     */
    public void setIdUsuarioApertura(int IdUsuarioApertura) {
        this.IdUsuarioApertura = IdUsuarioApertura;
    }

    /**
     * @return the FechaSalida
     */
    public String getFechaSalida() {
        return FechaSalida;
    }

    /**
     * @param FechaSalida the FechaSalida to set
     */
    public void setFechaSalida(String FechaSalida) {
        this.FechaSalida = FechaSalida;
    }
}
