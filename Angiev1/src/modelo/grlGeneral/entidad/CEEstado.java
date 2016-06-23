

package modelo.grlGeneral.entidad;


public class CEEstado {

    private int mIntIdEstado;
    private String mStrDescripcion;
    private String mStrAbreviatura;

    public int getmIntIdEstado()
    {
        return mIntIdEstado;
    }

    public void setmIntIdEstado(int mIntIdEstado)
    {
        this.mIntIdEstado = mIntIdEstado;
    }

    public String getmStrAbreviatura()
    {
        return mStrAbreviatura;
    }

    public void setmStrAbreviatura(String mStrAbreviatura)
    {
        this.mStrAbreviatura = mStrAbreviatura;
    }

    public String getmStrDescripcion() {
        return mStrDescripcion;
    }

    public void setmStrDescripcion(String mStrDescripcion) {
        this.mStrDescripcion = mStrDescripcion;
    }

    @Override
    public String toString() {
        return this.mStrDescripcion;
    }



}
