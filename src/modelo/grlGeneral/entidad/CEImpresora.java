/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package modelo.grlGeneral.entidad;

import java.util.ArrayList;
import java.util.List;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.swing.JComboBox;

/**
 *
 * @author Morales
 */
public class CEImpresora
{
        private int Indice;
        private String Impresora;

    public String getImpresora() {
        return Impresora;
    }

    public void setImpresora(String Impresora) {
        this.Impresora = Impresora;
    }

    public int getIndice() {
        return Indice;
    }

    public void setIndice(int Indice) {
        this.Indice = Indice;
    }
public ArrayList<CEImpresora> ListarImpresorasConNiguno()
    {

        ArrayList<CEImpresora>  lista = new ArrayList<CEImpresora> ();
        PrintRequestAttributeSet aset = new HashPrintRequestAttributeSet();
        PrintService[] services= PrintServiceLookup.lookupPrintServices(null, aset);
        CEImpresora oCEImpresora= new CEImpresora();
        oCEImpresora.setIndice(-1);
        oCEImpresora.setImpresora("NINGUNO");
        lista.add(oCEImpresora);
        
       for(int i=0;i<services.length;i++)
        {
          oCEImpresora= new CEImpresora();
          oCEImpresora.setIndice(i);
          oCEImpresora.setImpresora(services[i].getName());
          lista.add(oCEImpresora);
        }


        return lista;
    }

public ArrayList<CEImpresora> ListarImpresorasSinNinguno()
    {

        ArrayList<CEImpresora>  lista = new ArrayList<CEImpresora> ();
        PrintRequestAttributeSet aset = new HashPrintRequestAttributeSet();
        PrintService[] services= PrintServiceLookup.lookupPrintServices(null, aset);
        CEImpresora oCEImpresora;

       for(int i=0;i<services.length;i++)
        {
          oCEImpresora= new CEImpresora();
          oCEImpresora.setIndice(i);
          oCEImpresora.setImpresora(services[i].getName());
          lista.add(oCEImpresora);
        }


        return lista;
    }

public CEImpresora buscarImpresora(int pindice)
    {

        for (int i = 0; i < ListarImpresorasConNiguno().size(); i++) {
            CEImpresora oCEImpresora = ListarImpresorasConNiguno().get(i);
            if(pindice==oCEImpresora.getIndice())
            {
                return oCEImpresora;
            }

        }

        return null;

}

public int buscarIndiceImpresora(String NombreImpresora)
    {
    List<CEImpresora> lista=ListarImpresorasSinNinguno();
        for (int i = 0; i < lista.size(); i++) {
            CEImpresora oCEImpresora = lista.get(i);
            if(NombreImpresora.equals(oCEImpresora.getImpresora()))
            {
                return i;
            }

        }

        return -1;

}

public int buscarIndiceImpresoraConNiguno(String NombreImpresora)
    {
    List<CEImpresora> lista=ListarImpresorasConNiguno();
        for (int i = 0; i < lista.size(); i++) {
            CEImpresora oCEImpresora = lista.get(i);
            if(NombreImpresora.equals(oCEImpresora.getImpresora()))
            {
                return i;
            }

        }

        return -1;

}

public int buscarIndiceImpresoraDesdeCbx(String NombreImpresora,JComboBox CbxImpresora)
    {

        for (int i = 0; i < CbxImpresora.getModel().getSize(); i++) {
            CEImpresora oCEImpresora=(CEImpresora)CbxImpresora.getModel().getElementAt(i);
            if(NombreImpresora.equals(oCEImpresora.getImpresora()))
            {
                return i;
            }

        }

        return -1;

}


    @Override
    public String toString() {
        return  Impresora ;
    }


}
