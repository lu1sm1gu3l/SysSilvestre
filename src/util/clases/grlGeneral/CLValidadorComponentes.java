package util.clases.grlGeneral;

import java.awt.Color;
import java.util.ArrayList;

public class CLValidadorComponentes
{
    private ArrayList<ValidadorJTextField> oArrayValidadorComponentes=new ArrayList<ValidadorJTextField>();
    public ArrayList<ValidadorJTextField> getoArrayValidadorComponentes()
    {
        return oArrayValidadorComponentes;
    }
    public void setoArrayValidadorComponentes(ArrayList<ValidadorJTextField> oArrayValidadorComponentes)
    {
        this.oArrayValidadorComponentes = oArrayValidadorComponentes;
    }
    public void agregar(ValidadorJTextField oValidadorJTextField)
    {
        this.oArrayValidadorComponentes.add(oValidadorJTextField);
    }

    public void validar()
    {
        for(ValidadorJTextField oCLValidadorComponentes:oArrayValidadorComponentes)
        {
            if(oCLValidadorComponentes.isRequired())
            {
               if(oCLValidadorComponentes.getTxtTexto().getText().trim().equals(""))
               {
                   oCLValidadorComponentes.getLblIconValidador().setVisible(true);
                   oCLValidadorComponentes.getTxtTexto().setBackground((new Color(255,204,204)));
               }
               else
               {
                   oCLValidadorComponentes.getLblIconValidador().setVisible(false);
                   oCLValidadorComponentes.getTxtTexto().setBackground(Color.white);
               }
            }
            else
               {
                   oCLValidadorComponentes.getLblIconValidador().setVisible(false);
                   oCLValidadorComponentes.getTxtTexto().setBackground(Color.white);
               }
        }
    }
    
}
