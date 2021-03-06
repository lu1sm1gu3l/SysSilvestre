package view.cmrComercial;
import controller.cmrComercial.CCProveedor;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JRootPane;
import javax.swing.KeyStroke;
import modelo.cmrComercial.entidad.CEProveedor;
import util.clases.grlGeneral.CLValidarControles;
import util.clases.grlGeneral.DialogoPadre;
import util.clases.grlGeneral.VerificadorDeTxt;

public class dlgRegistroProveedor extends DialogoPadre implements ActionListener{
private CEProveedor oCEProveedor;
    private int OperacionAMB=0;
    private CLValidarControles ovalidar;

    /** Creates new form JDHabitacion */
    public dlgRegistroProveedor(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }
       public dlgRegistroProveedor(java.awt.Frame parent, boolean modal,CEProveedor pCEProveedor,int TipoEmpleado) {
        super(parent, modal);
        initComponents();
        this.setLocationRelativeTo(null);
        ovalidar=new CLValidarControles();
        
      ovalidar.SetListCompnente(new Component[]{TxtRazonSocial}, PnlFormulario);
        oCEProveedor=pCEProveedor;
        if(pCEProveedor!=null)
        {
        llenarFormulario();  
        }
             editartextos(true,true);
        
        TxtRazonSocial.requestFocus();
      
    }
    public void setABM(int p)
    {
    OperacionAMB=p;
    }
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        BtbgPersona = new javax.swing.ButtonGroup();
        btnGrSexo = new javax.swing.JFileChooser();
        buttonGroup1 = new javax.swing.ButtonGroup();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        PnlFormulario = new javax.swing.JPanel();
        LblApellidoPaterno2 = new javax.swing.JLabel();
        DNI1 = new javax.swing.JLabel();
        LblDireccion = new javax.swing.JLabel();
        LblTelefono = new javax.swing.JLabel();
        LblCelular = new javax.swing.JLabel();
        LblCorreoElectronico = new javax.swing.JLabel();
        TxtFax = new javax.swing.JTextField();
        TxtTelefono = new javax.swing.JTextField();
        TxtCorreoElectronico = new TextField.JTxtNinguno();
        TxtRUC = new javax.swing.JTextField();
        TxtDireccion = new TextField.JTxtNinguno();
        TxtRazonSocial = new TextField.JTxtNinguno();
        PnlABM = new javax.swing.JPanel();
        BtnGuardar = new BotonesABM.BtnGuardar();
        btnCancelar1 = new BotonesABM.BtnCancelar();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                formKeyReleased(evt);
            }
        });

        jTabbedPane1.setFont(new java.awt.Font("Tahoma", 1, 12));

        PnlFormulario.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        LblApellidoPaterno2.setFont(new java.awt.Font("Verdana", 1, 12));
        LblApellidoPaterno2.setText("Razón Social :");

        DNI1.setFont(new java.awt.Font("Verdana", 1, 12));
        DNI1.setText("RUC :");

        LblDireccion.setFont(new java.awt.Font("Verdana", 1, 12));
        LblDireccion.setText("Direccion:");

        LblTelefono.setFont(new java.awt.Font("Verdana", 1, 12));
        LblTelefono.setText("Telefono:");

        LblCelular.setFont(new java.awt.Font("Verdana", 1, 12));
        LblCelular.setText("Fax:");

        LblCorreoElectronico.setFont(new java.awt.Font("Verdana", 1, 12));
        LblCorreoElectronico.setText("Correo Electronico:");

        TxtFax.setDocument(new VerificadorDeTxt("Numero",12,TxtFax));
        TxtFax.setFont(new java.awt.Font("Verdana", 0, 12));
        TxtFax.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TxtFaxActionPerformed(evt);
            }
        });
        TxtFax.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TxtFaxKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                TxtFaxKeyReleased(evt);
            }
        });

        TxtTelefono.setDocument(new VerificadorDeTxt("Numero",12,TxtTelefono));
        TxtTelefono.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TxtTelefonoKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                TxtTelefonoKeyReleased(evt);
            }
        });

        TxtCorreoElectronico.setText("jTxtNinguno1");
        TxtCorreoElectronico.setTamanio(100);
        TxtCorreoElectronico.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TxtCorreoElectronicoKeyPressed(evt);
            }
        });

        TxtRUC.setDocument(new VerificadorDeTxt("Numero",8,TxtRUC));
        TxtRUC.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TxtRUCKeyPressed(evt);
            }
        });

        TxtDireccion.setTamanio(100);
        TxtDireccion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TxtDireccionKeyPressed(evt);
            }
        });

        TxtRazonSocial.setTamanio(250);
        TxtRazonSocial.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TxtRazonSocialKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout PnlFormularioLayout = new javax.swing.GroupLayout(PnlFormulario);
        PnlFormulario.setLayout(PnlFormularioLayout);
        PnlFormularioLayout.setHorizontalGroup(
            PnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PnlFormularioLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PnlFormularioLayout.createSequentialGroup()
                        .addGroup(PnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(LblTelefono)
                            .addComponent(LblCorreoElectronico)
                            .addComponent(LblDireccion)
                            .addComponent(LblCelular))
                        .addGap(10, 10, 10)
                        .addGroup(PnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(TxtFax, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(TxtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(TxtDireccion, javax.swing.GroupLayout.DEFAULT_SIZE, 418, Short.MAX_VALUE)
                            .addComponent(TxtCorreoElectronico, javax.swing.GroupLayout.DEFAULT_SIZE, 418, Short.MAX_VALUE)))
                    .addGroup(PnlFormularioLayout.createSequentialGroup()
                        .addGroup(PnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(DNI1)
                            .addComponent(LblApellidoPaterno2))
                        .addGap(44, 44, 44)
                        .addGroup(PnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(TxtRazonSocial, javax.swing.GroupLayout.DEFAULT_SIZE, 418, Short.MAX_VALUE)
                            .addGroup(PnlFormularioLayout.createSequentialGroup()
                                .addComponent(TxtRUC, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 285, Short.MAX_VALUE)))))
                .addContainerGap())
        );
        PnlFormularioLayout.setVerticalGroup(
            PnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PnlFormularioLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(LblApellidoPaterno2)
                    .addComponent(TxtRazonSocial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(DNI1)
                    .addComponent(TxtRUC, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(PnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(LblDireccion)
                    .addComponent(TxtDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(16, 16, 16)
                .addGroup(PnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(LblTelefono)
                    .addComponent(TxtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PnlFormularioLayout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addComponent(LblCelular)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(PnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(LblCorreoElectronico)
                            .addComponent(TxtCorreoElectronico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(TxtFax, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(22, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("DATOS PERSONALES", PnlFormulario);

        PnlABM.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        BtnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnGuardarActionPerformed(evt);
            }
        });
        BtnGuardar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnGuardarKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                BtnGuardarKeyReleased(evt);
            }
        });

        btnCancelar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelar1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout PnlABMLayout = new javax.swing.GroupLayout(PnlABM);
        PnlABM.setLayout(PnlABMLayout);
        PnlABMLayout.setHorizontalGroup(
            PnlABMLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PnlABMLayout.createSequentialGroup()
                .addComponent(BtnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnCancelar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        PnlABMLayout.setVerticalGroup(
            PnlABMLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(BtnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(btnCancelar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(PnlABM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 582, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(PnlABM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.getAccessibleContext().setAccessibleName("");

        pack();
    }// </editor-fold>//GEN-END:initComponents

   
    private void TxtTelefonoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TxtTelefonoKeyReleased

    }//GEN-LAST:event_TxtTelefonoKeyReleased

    private void BtnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnGuardarActionPerformed


  eventoGuardar();
        

    }//GEN-LAST:event_BtnGuardarActionPerformed

    private String ProvNuevo="";
    public String getProvNuevo()
    {
        return ProvNuevo;
    }
    private void eventoGuardar()
    {

          ovalidar.validarContorles();
        if(ovalidar.getisVacio()){

            guardar();
            } else{
            JOptionPane.showMessageDialog(null,"Verificar Datos");
            TxtRazonSocial.requestFocus();
            }
    }
    private void btnCancelar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelar1ActionPerformed
        dispose();
    }//GEN-LAST:event_btnCancelar1ActionPerformed

    private void BtnGuardarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnGuardarKeyReleased
      if(evt.getKeyCode()==evt.VK_UP||evt.getKeyCode()==evt.VK_LEFT||evt.getKeyCode()==evt.VK_DOWN||evt.getKeyCode()==evt.VK_RIGHT) {
          TxtCorreoElectronico.requestFocus();
      }
    
    }//GEN-LAST:event_BtnGuardarKeyReleased

    private void formKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyReleased
       
    }//GEN-LAST:event_formKeyReleased

    private void TxtRUCKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TxtRUCKeyPressed
          if(evt.getKeyCode()==evt.VK_DOWN||evt.getKeyCode()==evt.VK_ENTER) {
            TxtDireccion.requestFocus();
        }
        if(evt.getKeyCode()==evt.VK_UP) {
            TxtRazonSocial.requestFocus();
        }
    }//GEN-LAST:event_TxtRUCKeyPressed

    private void TxtTelefonoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TxtTelefonoKeyPressed
        if(evt.getKeyCode()==evt.VK_DOWN||evt.getKeyCode()==evt.VK_ENTER) {
            TxtFax.requestFocus();
        }
     if(evt.getKeyCode()==evt.VK_UP) {
            TxtDireccion.requestFocus();
        }
    }//GEN-LAST:event_TxtTelefonoKeyPressed

    private void BtnGuardarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnGuardarKeyPressed
          if(evt.getKeyCode()==evt.VK_ENTER)
      {
            eventoGuardar();
      }
    }//GEN-LAST:event_BtnGuardarKeyPressed

    private void TxtFaxKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TxtFaxKeyPressed
        if(evt.getKeyCode()==evt.VK_DOWN||evt.getKeyCode()==evt.VK_ENTER) {
            TxtCorreoElectronico.requestFocus();
        }
     if(evt.getKeyCode()==evt.VK_UP) {
            TxtTelefono.requestFocus();
        }
}//GEN-LAST:event_TxtFaxKeyPressed

    private void TxtFaxKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TxtFaxKeyReleased
        // TODO add your handling code here:
}//GEN-LAST:event_TxtFaxKeyReleased

    private void TxtCorreoElectronicoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TxtCorreoElectronicoKeyPressed
        if(evt.getKeyCode()==evt.VK_DOWN||evt.getKeyCode()==evt.VK_ENTER) {
            BtnGuardar.requestFocus();
        }
        if(evt.getKeyCode()==evt.VK_UP) {
            TxtFax.requestFocus();
        }
}//GEN-LAST:event_TxtCorreoElectronicoKeyPressed

    private void TxtFaxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TxtFaxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TxtFaxActionPerformed

    private void TxtDireccionKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TxtDireccionKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TxtDireccionKeyPressed

    private void TxtRazonSocialKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TxtRazonSocialKeyPressed

        if(evt.getKeyCode()==evt.VK_DOWN||evt.getKeyCode()==evt.VK_ENTER) {

            TxtRUC.requestFocus();
        }
      
    }//GEN-LAST:event_TxtRazonSocialKeyPressed
    private void editartextos(boolean pNat,boolean pJur)
    {
        TxtRazonSocial.setEditable(pJur);
        TxtRUC.setEditable(pNat);
        TxtTelefono.setEditable(pJur);
        TxtFax.setEditable(pJur);
        TxtDireccion.setEditable(pJur);
        TxtCorreoElectronico.setEditable(pJur);
        
    }
    private void  guardar()
    {
    CEProveedor oCEProveedorAux= new CEProveedor();
    oCEProveedorAux.setCorreo(TxtCorreoElectronico.getText());
    oCEProveedorAux.setRUC(TxtRUC.getText());
    oCEProveedorAux.setDireccion(TxtDireccion.getText());
    oCEProveedorAux.setRazonSocial(TxtRazonSocial.getText());
    oCEProveedorAux.setTelefono(TxtTelefono.getText());
    oCEProveedorAux.setFax(TxtFax.getText());
    
       
      

    
    oCEProveedorAux.setIdSucursal(1);

    ArrayList<CEProveedor> olista=new ArrayList<CEProveedor>();
        //Al momento de guardar un dato
       if(OperacionAMB==1)
        { int resul=JOptionPane.showConfirmDialog(this, "¿Esta seguro de agregar? ", "Dialogo de Confirmación", JOptionPane.YES_NO_OPTION);
            if (resul==JOptionPane.YES_OPTION) {
               olista.add(oCEProveedorAux);
                resul=CCProveedor.INSProveedor(olista);
                if(resul!=0)
                {
                    JOptionPane.showMessageDialog(null,"Operacion Exitosa");                    
                   OperacionAMB=0;
                   ProvNuevo=TxtRazonSocial.getText();
                   dispose();
                }
                else
                {
                    JOptionPane.showMessageDialog(null, "Operación Fallida");
                }

            }
        }
        else
        {  //Al momento de editar
            if(OperacionAMB==2)
            {
                int resul=JOptionPane.showConfirmDialog(this, "¿Esta seguro de modificar? ", "Dialogo de Confirmación", JOptionPane.YES_NO_OPTION);
                if (resul==JOptionPane.YES_OPTION) {
                oCEProveedorAux.setIdProveedor(oCEProveedor.getIdProveedor());
                olista.add(oCEProveedorAux);
                resul=CCProveedor.UPDProveedor(olista);
                  if (resul!=0) {
                      JOptionPane.showMessageDialog(null,"Operacion Exitosa");
                      OperacionAMB=0;
                      dispose();
                    }
                else
                   {
                   JOptionPane.showMessageDialog(null, "No se pudo completar la operación...!");
                   }


                }

            }
        }

    }
    private  void llenarFormulario()
         {
        TxtRazonSocial.setText(oCEProveedor.getRazonSocial());
        TxtTelefono.setText(oCEProveedor.getTelefono());
        TxtRUC.setText(oCEProveedor.getRUC());
        TxtCorreoElectronico.setText(oCEProveedor.getCorreo());
        TxtFax.setText(oCEProveedor.getFax());
        TxtDireccion.setText(oCEProveedor.getDireccion());



    }                     
  

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup BtbgPersona;
    private BotonesABM.BtnGuardar BtnGuardar;
    private javax.swing.JLabel DNI1;
    private javax.swing.JLabel LblApellidoPaterno2;
    private javax.swing.JLabel LblCelular;
    private javax.swing.JLabel LblCorreoElectronico;
    private javax.swing.JLabel LblDireccion;
    private javax.swing.JLabel LblTelefono;
    private javax.swing.JPanel PnlABM;
    private javax.swing.JPanel PnlFormulario;
    private TextField.JTxtNinguno TxtCorreoElectronico;
    private TextField.JTxtNinguno TxtDireccion;
    private javax.swing.JTextField TxtFax;
    private javax.swing.JTextField TxtRUC;
    private TextField.JTxtNinguno TxtRazonSocial;
    private javax.swing.JTextField TxtTelefono;
    private BotonesABM.BtnCancelar btnCancelar1;
    private javax.swing.JFileChooser btnGrSexo;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JTabbedPane jTabbedPane1;
    // End of variables declaration//GEN-END:variables

    private static final String ACTION_CLOSE = "ACTION_CLOSE";
    private static final String ACTION_GUARDAR = "ACTION_NUEVO";
    protected JRootPane createRootPane()
    {
        JRootPane rootPane = new JRootPane();

        KeyStroke esc = KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0);
        KeyStroke nuevo = KeyStroke.getKeyStroke(KeyEvent.VK_G,java.awt.event.InputEvent.CTRL_DOWN_MASK );
        rootPane.registerKeyboardAction(this,ACTION_CLOSE, esc, JComponent.WHEN_IN_FOCUSED_WINDOW);
        rootPane.registerKeyboardAction(this,ACTION_GUARDAR, nuevo, JComponent.WHEN_IN_FOCUSED_WINDOW);
        return rootPane;
    }
    public void actionPerformed(ActionEvent e)
    {
        if(e.getActionCommand().equals(ACTION_CLOSE))
        {
            dispose();
        }
 else{
         if(e.getActionCommand().equals(ACTION_GUARDAR))
        {
            eventoGuardar();
        }
    }
    }
}
