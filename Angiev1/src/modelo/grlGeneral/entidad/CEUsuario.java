package modelo.grlGeneral.entidad;
import java.awt.Image;
import java.util.List;

public class CEUsuario
{
    private int IdUsuario;
    private String NombreCompleto;
    private String Usuario;
    private String password;
    private List<CEComponenteAccion> oListComponenteAccion;
    private Image imagen;
    private int IdEmpleado;
    private int IdRol;
    private String Rol;
    private String IngresoSistema;
    private String Sucursal;
    private int IdSucursal;
    private String Terminal;
    private String ip;
    private boolean estado=false;


    public int getIdUsuario()
    {
        return IdUsuario;
    }


    public void setIdUsuario(int IdUsuario)
    {
        this.IdUsuario = IdUsuario;
    }


     public boolean getEstado()
    {
        return estado;
    }


    public void setEstado(boolean estadop)
    {
        this.estado= estadop;
    }

    public String getPassword()
    {
        return password;
    }    
    public void setPassword(String password)
    {
        this.password = password;
    }

    public List<CEComponenteAccion> getoListComponenteAccion()
    {
        return oListComponenteAccion;
    }


    public void setoListComponenteAccion(List<CEComponenteAccion> oListComponenteAccion)
    {
        this.oListComponenteAccion = oListComponenteAccion;
    }

    public String getUsuario() {
        return Usuario;
    }

    public void setUsuario(String Usuario) {
        this.Usuario = Usuario;
    }

    public Image getImagen()
    {
        return imagen;
    }

    public void setImagen(Image imagen)
    {
        this.imagen = imagen;
    }

    public int getIdEmpleado()
    {
        return IdEmpleado;
    }

    public void setIdEmpleado(int IdSujeto)
    {
        this.IdEmpleado = IdSujeto;
    }


    public String toString()
    {
        return this.getUsuario();
    }

    /**
     * @return the NombreCompleto
     */
    public String getNombreCompleto() {
        return NombreCompleto;
    }

    /**
     * @param NombreCompleto the NombreCompleto to set
     */
    public void setNombreCompleto(String NombreCompleto) {
        this.NombreCompleto = NombreCompleto;
    }

    /**
     * @return the IdSucursal
     */
    public int getIdSucursal() {
        return IdSucursal;
    }

    /**
     * @param IdSucursal the IdSucursal to set
     */
    public void setIdSucursal(int IdSucursal) {
        this.IdSucursal = IdSucursal;
    }

    /**
     * @return the Sucursal
     */
    public String getSucursal() {
        return Sucursal;
    }

    /**
     * @param Sucursal the Sucursal to set
     */
    public void setSucursal(String Sucursal) {
        this.Sucursal = Sucursal;
    }

    /**
     * @return the IngresoSistema
     */
    public String getIngresoSistema() {
        return IngresoSistema;
    }

    /**
     * @param IngresoSistema the IngresoSistema to set
     */
    public void setIngresoSistema(String IngresoSistema) {
        this.IngresoSistema = IngresoSistema;
    }

    /**
     * @return the ip
     */
    public String getIp() {
        return ip;
    }

    /**
     * @param ip the ip to set
     */
    public void setIp(String ip) {
        this.ip = ip;
    }

    /**
     * @return the Terminal
     */
    public String getTerminal() {
        return Terminal;
    }

    /**
     * @param Terminal the Terminal to set
     */
    public void setTerminal(String Terminal) {
        this.Terminal = Terminal;
    }

    public int getIdRol() {
        return IdRol;
    }

    public void setIdRol(int IdRol) {
        this.IdRol = IdRol;
    }

    public String getRol() {
        return Rol;
    }

    public void setRol(String Rol) {
        this.Rol = Rol;
    }

}
