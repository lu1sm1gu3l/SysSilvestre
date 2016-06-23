/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package util.clases.grlGeneral;

import com.toedter.calendar.JDateChooser;
import controller.grlGeneral.CCVinculacionRolComponenteAccion;
import java.awt.Component;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import modelo.grlGeneral.entidad.CEVinculacionRolComponenteAccionMatriz;
import view.FrmSistemaMenu;

/**
 *
 * @author Luiggi
 */
public class CLBotonesABM {

  private JButton btnNuevo;
  private JButton btnGuardar;
  private JButton btnEliminar;
  private JButton btnEditar;
  private JButton btnCancelar;
  private JButton btnExportar;
  List<CEVinculacionRolComponenteAccionMatriz> oListaPermisosAccion;

public void setBotones(JButton btnNuevo   ,JButton btnGuardar,
                           JButton btnEliminar,JButton btnEditar,
                           JButton btnCancelar,JButton btnExportar,JDialog oDialogo)
    {
        this.btnNuevo    = btnNuevo;
        this.btnEliminar = btnEliminar;
        this.btnEditar   = btnEditar;
        this.btnCancelar = btnCancelar;
        this.btnExportar = btnExportar;
        this.btnGuardar  = btnGuardar;
        setPermisoDeAcciones(oDialogo);
    }
    public void controlBoton(boolean btnNue,boolean btnGua,boolean btnEli,
                             boolean btnEdi,boolean btnCan,boolean btnExp)
    {
          if(btnNuevo!=null){
          btnNuevo.setEnabled(btnNue);
          }
          if(btnGuardar!=null){
          btnGuardar.setEnabled(btnGua);
          }
          if(btnEliminar!=null){
          btnEliminar.setEnabled(btnEli);
          }
          if(btnEditar!=null){
          btnEditar.setEnabled(btnEdi);
          }
          if(btnNuevo!=null){
          btnNuevo.setEnabled(btnCan);
          }
          if(btnExportar!=null){
          btnExportar.setEnabled(btnExp);}

    }


     private void setPermisoDeAcciones(JDialog oDialogo)
    {
          oListaPermisosAccion=CCVinculacionRolComponenteAccion.PermisosPorAccion(oDialogo.getTitle(),FrmSistemaMenu.oCEUsuario.getIdRol());
          HAbilitarPermisoDeAcciones(oDialogo);
    }
    private void HabilitarAccion(JButton oJButon)
    {
        String NombreAccion="";

        for (CEVinculacionRolComponenteAccionMatriz oPermisosAccion : oListaPermisosAccion) {
            
             NombreAccion=oJButon.getText().toUpperCase();
                if(oPermisosAccion.getAccion().toUpperCase().equals(NombreAccion))
                {
                    oJButon.setVisible(true);
                }
                NombreAccion=VerificadorDeTxt.convertString(oJButon.getToolTipText()).toUpperCase();
                if(oPermisosAccion.getAccion().toUpperCase().equals(NombreAccion))
                {
                    oJButon.setVisible(true);
                }
        }

    }
    private void HAbilitarPermisoDeAcciones(JDialog oDialogo)
    {
           Component[] lstComp= oDialogo.getRootPane().getContentPane().getComponents();

          for (Component oCmpDeDialogo : lstComp) {
                     if (oCmpDeDialogo instanceof JPanel)
                    {
                       if(!(oCmpDeDialogo instanceof JDateChooser)){
                        JPanel oJPanel=(JPanel)oCmpDeDialogo;

                       Component[] lstCompDePanel= oJPanel.getComponents();

                                for (Component oCompDePanel : lstCompDePanel) {

                                        if(oCompDePanel instanceof JButton)
                                            {
                                                JButton oJButon=(JButton)oCompDePanel;
                                                   if(!(VerificadorDeTxt.convertString(oJButon.getAccessibleContext().getAccessibleName())).equals("0"))
                                                      {
                                                       oJButon.setVisible(false);
                                                       HabilitarAccion(oJButon); 
                                                       }
                                                                                                       
                                            }
                                    }
                        }
                    }

                    else if (oCmpDeDialogo instanceof JButton)
                    {
                        JButton oJButon=(JButton)oCmpDeDialogo;
                            if(!(VerificadorDeTxt.convertString(oJButon.getAccessibleContext().getAccessibleName())).equals("0"))
                            {
                            oJButon.setVisible(false);
                            HabilitarAccion(oJButon);
                            }
                            
                            
                    }


                }

           }
}
