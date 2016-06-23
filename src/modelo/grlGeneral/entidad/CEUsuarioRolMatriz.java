package modelo.grlGeneral.entidad;

public class CEUsuarioRolMatriz extends CEUsuarioRol
{
    private String usuario;
    private String rol;

    public String getUsuario() {
        return usuario;
    }
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public String toString()
    {
        return this.rol;
    }

}
