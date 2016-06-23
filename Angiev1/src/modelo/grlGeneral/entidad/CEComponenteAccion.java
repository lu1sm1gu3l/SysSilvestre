package modelo.grlGeneral.entidad;

public class CEComponenteAccion
{
    private int IdComponenteAccion;
    private int IdComponente;
    private int IdAccion;
    private boolean isEnabled;
    private String Descripcion;
    private String Nombre;
    private String Componente;

    
    public int getIdComponenteAccion()
    {
        return IdComponenteAccion;
    }

    public void setIdComponenteAccion(int IdComponenteAccion)
    {
        this.IdComponenteAccion = IdComponenteAccion;
    }

    public int getIdComponente()
    {
        return IdComponente;
    }


    public void setIdComponente(int IdComponente)
    {
        this.IdComponente = IdComponente;
    }

    public int getIdAccion()
    {
        return IdAccion;
    }


    public void setIdAccion(int IdAccion)
    {
        this.IdAccion = IdAccion;
    }

    /**
     * @return the isEnabled
     */
    public boolean isIsEnabled() {
        return isEnabled;
    }

    /**
     * @param isEnabled the isEnabled to set
     */
    public void setIsEnabled(boolean isEnabled) {
        this.isEnabled = isEnabled;
    }

    /**
     * @return the Descripcion
     */
    public String getDescripcion() {
        return Descripcion;
    }

    /**
     * @param Descripcion the Descripcion to set
     */
    public void setDescripcion(String Descripcion) {
        this.Descripcion = Descripcion;
    }

    /**
     * @return the Nombre
     */
    public String getNombre() {
        return Nombre;
    }

    /**
     * @param Nombre the Nombre to set
     */
    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public String getComponente() {
        return Componente;
    }

    public void setComponente(String Componente) {
        this.Componente = Componente;
    }


}
