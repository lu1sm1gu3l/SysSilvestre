package modelo.grlGeneral.entidad;
public class CEAccion

{
        public int idAccion;
        public String descripcion;
        public String abreviatura;

    public CEAccion() {
    }

    public CEAccion(int idAccion, String descripcion, String abreviatura) {
        this.idAccion = idAccion;
        this.descripcion = descripcion;
        this.abreviatura = abreviatura;
    }



    public String getAbreviatura() {
        return abreviatura;
    }

    public void setAbreviatura(String abreviatura) {
        this.abreviatura = abreviatura;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getIdAccion() {
        return idAccion;
    }

    public void setIdAccion(int idAccion) {
        this.idAccion = idAccion;
    }
    public String toString()
    {
        return descripcion;
    }


}
