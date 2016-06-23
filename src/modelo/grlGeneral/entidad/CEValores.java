/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package modelo.grlGeneral.entidad;

/**
 *
 * @author Luiggi
 */
public class CEValores
{

    private int mIntIdValores;
    private int mIntCodigo;
    private String mStrDescripcion;
    private String mStrAbreviatura;

    public int getmIntCodigo() {
        return mIntCodigo;
    }

    public void setmIntCodigo(int mIntCodigo) {
        this.mIntCodigo = mIntCodigo;
    }

    public int getmIntIdValores() {
        return mIntIdValores;
    }

    public void setmIntIdValores(int mIntIdValores) {
        this.mIntIdValores = mIntIdValores;
    }

    public String getmStrAbreviatura() {
        return mStrAbreviatura;
    }

    public void setmStrAbreviatura(String mStrAbreviatura) {
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
