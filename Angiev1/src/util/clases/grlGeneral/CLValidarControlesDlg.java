package util.clases.grlGeneral;

import com.toedter.calendar.JDateChooser;
import java.awt.Component;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class CLValidarControlesDlg
{
boolean isVacio=true;
private JDialog oDialogo;

ArrayList<JLabel> olistaLabel ;

 Component[] listaComponente  ;

 public void SetListCompnente(Component[] plistaComponente,JDialog pDialog)
    {
     listaComponente=null;
     listaComponente=plistaComponente;
     oDialogo=pDialog;
     olistaLabel= new ArrayList<JLabel>();
      JLabel olabel;
        for (Component ocomponent : listaComponente) {
               olabel = new JLabel();
               olabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/util/icono/alerta.png")));
                olabel.setBounds(ocomponent.getX()+ocomponent.getWidth()+3, ocomponent.getY()+3, 15, 15);
                olabel.setVisible(false);
                olistaLabel.add(olabel);
                oDialogo.add(olabel);
        }
 }
 public void SetListCompnente(Component[] plistaComponente)
    {
     listaComponente=null;
     listaComponente=plistaComponente;
     olistaLabel= new ArrayList<JLabel>();
      JLabel olabel;
        for (Component ocomponent : listaComponente) {
               olabel = new JLabel();
               olabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/util/icono/alerta.png")));
                olabel.setBounds(ocomponent.getX()+ocomponent.getWidth()+3, ocomponent.getY()+3, 15, 15);
                olabel.setVisible(false);
                olistaLabel.add(olabel);
                oDialogo.add(olabel);
        }
 }
    public void validarContorles()
    {
        isVacio=true;
        int i=0;
        for (Component ocomponent : listaComponente) {

            if(ocomponent instanceof JTextField)
            {
                JTextField otext= (JTextField)ocomponent;
                if(otext.getText().isEmpty()){
                olistaLabel.get(i).setVisible(true);
                isVacio=false;}
                 else
                {
               olistaLabel.get(i).setVisible(false);
                }

            }
            if(ocomponent instanceof JComboBox)
            {
                JComboBox oCmb= (JComboBox)ocomponent;
                String text=oCmb.getSelectedItem().toString().trim();
                if(text.equals("")){
                olistaLabel.get(i).setVisible(true);
                isVacio=false;}
                 else
                {
                olistaLabel.get(i).setVisible(false);
                }

            }

            if(ocomponent instanceof JDateChooser)
            {
                JDateChooser oDateChooser= (JDateChooser)ocomponent;
                Date text=oDateChooser.getDate();
                if(text==null){
                olistaLabel.get(i).setVisible(true);
                isVacio=false;}
                 else
                {
                olistaLabel.get(i).setVisible(false);
                }

            }
            if(ocomponent instanceof JFormattedTextField)
            {
                JFormattedTextField oFormato= (JFormattedTextField)ocomponent;
                String text=oFormato.getText().trim();

                if(text.equals(":")){
                olistaLabel.get(i).setVisible(true);
                isVacio=false;}
                 else
                {
                olistaLabel.get(i).setVisible(false);
                }

            }
           i++;
                
        }

    }

    public void ReiniciarCajas()
    {
        int i=0;
        for (Component ocomponent : listaComponente) {
            olistaLabel.get(i).setVisible(false);
            i++;
        }
        isVacio=true;
    }

    public boolean  getisVacio()
    {
        return isVacio;
    }



}
