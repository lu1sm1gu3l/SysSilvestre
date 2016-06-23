import com.sun.java.swing.plaf.windows.WindowsLookAndFeel;
import java.awt.Color;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import view.FrmAccesoSistema;


public class ClasePrincipal
{
    public static void main(String[] args)
    {

        try 
        {
            UIManager.put("ComboBox.disabledForeground", Color.BLACK);
            UIManager.put("ComboBox.disabledBackground", new Color(236, 233, 216));
            UIManager.put("CheckBox.disabledForeground", Color.BLACK);
            UIManager.put("CheckBox.disabledBackground", new Color(236, 233, 216));

           FrmAccesoSistema oFramePrincipal = new FrmAccesoSistema();
            UIManager.setLookAndFeel(new WindowsLookAndFeel());
            oFramePrincipal.setLocationRelativeTo(null);
            oFramePrincipal.setVisible(true);
        }
        catch (UnsupportedLookAndFeelException ex)
        {
            Logger.getLogger(ClasePrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }

//
//       double alto=14;
//       double altoi=alto;
//       double ancho=22.5;

//       double width=72*(ancho/2.54);
//       double heigh=72*(alto/2.54);
//
//       double saltocm=(heigh/72)*2.54;
//       for(int i=0;i<20;i++)
//       {
//
//           System.out.println("Alto: "+alto);
//           alto=alto+14;
//
//           System.out.println("salto px: "+heigh);
//            heigh=heigh+(72*(altoi/2.54));
//
//
//           System.out.println("salto cm: "+saltocm);
//           System.out.println("*****************");
//           saltocm=(heigh/72)*2.54;
//
//       }


    }




    
}

