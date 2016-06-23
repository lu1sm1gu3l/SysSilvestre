package modelo.grlGeneral.entidad;


public class CEAccesoSistema
{
    private long IdUsuarioSistema;
    private int IdUsuario;
    private String HoraEntrada;
    private String HoraSalida;

    public String getHoraSalida()
    {
        return HoraSalida;
    }
    public void setHoraSalida(String HoraSalida)
    {
        this.HoraSalida = HoraSalida;
    }

    /**
     * @return the IdUsuarioSistema
     */
    public long getIdUsuarioSistema() {
        return IdUsuarioSistema;
    }

    /**
     * @param IdUsuarioSistema the IdUsuarioSistema to set
     */
    public void setIdUsuarioSistema(long IdUsuarioSistema) {
        this.IdUsuarioSistema = IdUsuarioSistema;
    }

    /**
     * @return the IdUsuario
     */
    public int getIdUsuario() {
        return IdUsuario;
    }

    /**
     * @param IdUsuario the IdUsuario to set
     */
    public void setIdUsuario(int IdUsuario) {
        this.IdUsuario = IdUsuario;
    }

    /**
     * @return the HoraEntrada
     */
    public String getHoraEntrada() {
        return HoraEntrada;
    }

    /**
     * @param HoraEntrada the HoraEntrada to set
     */
    public void setHoraEntrada(String HoraEntrada) {
        this.HoraEntrada = HoraEntrada;
    }
}
