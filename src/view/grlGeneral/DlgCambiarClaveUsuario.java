package view.grlGeneral;


import combobox.ArrayListComboBoxModel;
import controller.grlGeneral.CCUsuario;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JRootPane;
import javax.swing.KeyStroke;
import modelo.grlGeneral.entidad.CEUsuario;
import util.clases.grlGeneral.DialogoPadre;
import view.FrmSistemaMenu;

public class DlgCambiarClaveUsuario extends DialogoPadre implements ActionListener
{

    private int operacion=0;
    private int IdUsuario=0;   
    public DlgCambiarClaveUsuario(java.awt.Frame parent, boolean modal)
    {
        super(parent, modal);
        initComponents();
        setObjetoUsuario(FrmSistemaMenu.oCEUsuario);
    }


   
    
   private static final String ACTION_CLOSE = "ACTION_CLOSE";

    protected JRootPane createRootPane()
    {
        JRootPane rootPane = new JRootPane();
        KeyStroke esc = KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0);
        rootPane.registerKeyboardAction(this, ACTION_CLOSE, esc, JComponent.WHEN_IN_FOCUSED_WINDOW);
        return rootPane;
    }

 

    public void limpiarCmb(JComboBox oCombo)
    {
       construirModeloCombo(oCombo, new ArrayList());
    }
    public void construirModeloCombo(JComboBox oBox, ArrayList oLista)
    {
               ArrayListComboBoxModel model = new ArrayListComboBoxModel( oLista ) ;
               oBox.setModel(model);
    }

   private void limpiarCajas()
    {
        JpfPasswordNuevo.setText("");
        JpfPasswordConf.setText("");
        JpfPasswordAnterior.setText("");
    }
   
    public void Guardar()
    {
         try
            {
                int res = JOptionPane.showConfirmDialog(this, "¿Esta seguro de modificar los datos?", "Dialogo de Confirmación", JOptionPane.YES_NO_OPTION);
                if (res == JOptionPane.YES_OPTION)
                {
                     CEUsuario oCEUsuario=getObjetoUsuario();
                     if(CCUsuario.ejecutarProcedimientoBM(4,oCEUsuario))
                        {
                            JOptionPane.showMessageDialog(null,"Operacion Exitosa");
                            limpiarCajas();
                            operacion=0;
                        }
                        else
                        {
                            JOptionPane.showMessageDialog(null,"No se pudo completar la operacion");
                        }
                }
             
         }
            catch(Exception e)
                {
                   JOptionPane.showMessageDialog(null,"Escriba correctamente los datos");
                }
    }
   
    public void nuevo()
    {
        operacion=1;
        limpiarCajas();
        IdUsuario=0;
    }
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        LblTitulo = new javax.swing.JLabel();
        PnlUsuario_Datos = new javax.swing.JPanel();
        LblPassword = new javax.swing.JLabel();
        JpfPasswordNuevo = new javax.swing.JPasswordField();
        LblUsername = new javax.swing.JLabel();
        TxtUsername = new javax.swing.JTextField();
        JpfPasswordConf = new javax.swing.JPasswordField();
        LblPassword2 = new javax.swing.JLabel();
        jCheckBox5 = new javax.swing.JCheckBox();
        JpfPasswordAnterior = new javax.swing.JPasswordField();
        LblPassword1 = new javax.swing.JLabel();
        SptDePanel = new javax.swing.JSeparator();
        BtnGuardar = new javax.swing.JButton();
        BtnSalir = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Mantenimiento_Documento");
        setResizable(false);

        LblTitulo.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        LblTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        LblTitulo.setText("Cambiar Clave de Acceso");
        LblTitulo.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        PnlUsuario_Datos.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        LblPassword.setFont(new java.awt.Font("Arial", 1, 12));
        LblPassword.setForeground(new java.awt.Color(153, 0, 0));
        LblPassword.setText("Password Nuevo:");

        LblUsername.setFont(new java.awt.Font("Arial", 1, 12));
        LblUsername.setForeground(new java.awt.Color(153, 0, 0));
        LblUsername.setText("Username:");

        TxtUsername.setEditable(false);

        LblPassword2.setFont(new java.awt.Font("Arial", 1, 12));
        LblPassword2.setForeground(new java.awt.Color(153, 0, 0));
        LblPassword2.setText("Confirmar Password:");

        jCheckBox5.setFont(new java.awt.Font("Arial", 1, 11));
        jCheckBox5.setText("Mostrar Caracteres");
        jCheckBox5.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jCheckBox5StateChanged(evt);
            }
        });
        jCheckBox5.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jCheckBox5ItemStateChanged(evt);
            }
        });
        jCheckBox5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox5ActionPerformed(evt);
            }
        });

        LblPassword1.setFont(new java.awt.Font("Arial", 1, 12));
        LblPassword1.setForeground(new java.awt.Color(153, 0, 0));
        LblPassword1.setText("Password Anterior:");

        javax.swing.GroupLayout PnlUsuario_DatosLayout = new javax.swing.GroupLayout(PnlUsuario_Datos);
        PnlUsuario_Datos.setLayout(PnlUsuario_DatosLayout);
        PnlUsuario_DatosLayout.setHorizontalGroup(
            PnlUsuario_DatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PnlUsuario_DatosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PnlUsuario_DatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(LblPassword2)
                    .addComponent(LblPassword)
                    .addComponent(LblUsername)
                    .addComponent(LblPassword1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PnlUsuario_DatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PnlUsuario_DatosLayout.createSequentialGroup()
                        .addGroup(PnlUsuario_DatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(TxtUsername, javax.swing.GroupLayout.DEFAULT_SIZE, 230, Short.MAX_VALUE)
                            .addComponent(JpfPasswordNuevo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 230, Short.MAX_VALUE)
                            .addComponent(JpfPasswordConf, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 230, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBox5)
                        .addContainerGap())
                    .addGroup(PnlUsuario_DatosLayout.createSequentialGroup()
                        .addComponent(JpfPasswordAnterior, javax.swing.GroupLayout.DEFAULT_SIZE, 226, Short.MAX_VALUE)
                        .addGap(145, 145, 145))))
        );
        PnlUsuario_DatosLayout.setVerticalGroup(
            PnlUsuario_DatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PnlUsuario_DatosLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(PnlUsuario_DatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(LblUsername)
                    .addComponent(TxtUsername, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(PnlUsuario_DatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(LblPassword1, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(JpfPasswordAnterior, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 9, Short.MAX_VALUE)
                .addGroup(PnlUsuario_DatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PnlUsuario_DatosLayout.createSequentialGroup()
                        .addComponent(LblPassword)
                        .addGap(7, 7, 7))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PnlUsuario_DatosLayout.createSequentialGroup()
                        .addComponent(JpfPasswordNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addGroup(PnlUsuario_DatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(JpfPasswordConf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(LblPassword2)
                    .addComponent(jCheckBox5))
                .addContainerGap())
        );

        BtnGuardar.setFont(new java.awt.Font("Arial", 1, 11));
        BtnGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/util/icono/Guardar.png"))); // NOI18N
        BtnGuardar.setText("Guardar");
        BtnGuardar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        BtnGuardar.setMargin(new java.awt.Insets(0, 0, 0, 0));
        BtnGuardar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        BtnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnGuardarActionPerformed(evt);
            }
        });
        BtnGuardar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnGuardarKeyPressed(evt);
            }
        });

        BtnSalir.setFont(new java.awt.Font("Tahoma", 1, 12));
        BtnSalir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/util/icono/Salir.png"))); // NOI18N
        BtnSalir.setText("Salir");
        BtnSalir.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        BtnSalir.setMargin(new java.awt.Insets(0, 0, 0, 0));
        BtnSalir.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        BtnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSalirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(BtnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(BtnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(PnlUsuario_Datos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 23, Short.MAX_VALUE)
                .addComponent(SptDePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(LblTitulo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 543, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(LblTitulo)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(122, 122, 122)
                        .addComponent(SptDePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(PnlUsuario_Datos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(BtnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

   private boolean verificarCajasDeTexto()
    {                       

            if(String.copyValueOf(JpfPasswordAnterior.getPassword()).equals(""))
                {
                 JOptionPane.showMessageDialog(null,"Ingresar Password Anterior");
                 JpfPasswordAnterior.requestFocus();
                 return false;
                }

            if(String.copyValueOf(JpfPasswordNuevo.getPassword()).equals(""))
                {
                 JOptionPane.showMessageDialog(null,"Ingresar Password Nuevo");
                 JpfPasswordNuevo.requestFocus();
                 return false;
                }

            CEUsuario oCEUsuario=CCUsuario.verificarClave(FrmSistemaMenu.oCEUsuario.getIdUsuario(),JpfPasswordAnterior.getText());
            if(oCEUsuario==null)
                {
                JOptionPane.showMessageDialog(null,"El password Anterior es incorrecto");
                return false;
                }

            if(!String.copyValueOf(JpfPasswordConf.getPassword()).equals(String.copyValueOf(JpfPasswordNuevo.getPassword())))
                {
                JOptionPane.showMessageDialog(null,"El password no coincide");
                return false;
                }
           
        return true;
  }
    
    private void BtnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnGuardarActionPerformed
        if(verificarCajasDeTexto())
        {
        Guardar();
        }
    }//GEN-LAST:event_BtnGuardarActionPerformed

    private void BtnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSalirActionPerformed
       dispose();
    }//GEN-LAST:event_BtnSalirActionPerformed
    private void BtnGuardarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnGuardarKeyPressed
        if(evt.getKeyCode()==evt.VK_ENTER)
        {
            Guardar();
        }
    }//GEN-LAST:event_BtnGuardarKeyPressed

    private void jCheckBox5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCheckBox5ActionPerformed

    private void jCheckBox5StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jCheckBox5StateChanged


    }//GEN-LAST:event_jCheckBox5StateChanged

    private void jCheckBox5ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jCheckBox5ItemStateChanged
     if(evt.getStateChange()==evt.SELECTED)
     {
         JpfPasswordNuevo.setEchoChar((char)0);
         JpfPasswordConf.setEchoChar((char)0);
     }
    else
     {
         JpfPasswordNuevo.setEchoChar('*');
         JpfPasswordConf.setEchoChar('*');
     }
    }//GEN-LAST:event_jCheckBox5ItemStateChanged
  private void setObjetoUsuario(CEUsuario oCEUsuario)
    {
        TxtUsername.setText(oCEUsuario.getUsuario()+"");
      //  JpfPasswordNuevo.setText(oCEUsuario.getPassword()+"");
       // JpfPasswordConf.setText(oCEUsuario.getPassword()+"");
        IdUsuario=oCEUsuario.getIdUsuario();
    }
 
    private CEUsuario getObjetoUsuario()
    {
        CEUsuario oCUsuario=new CEUsuario();
        oCUsuario.setUsuario(TxtUsername.getText().trim());
        oCUsuario.setPassword(String.copyValueOf(JpfPasswordNuevo.getPassword()).trim());
        oCUsuario.setIdUsuario(IdUsuario);
        return oCUsuario;
    }

   
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnGuardar;
    private javax.swing.JButton BtnSalir;
    private javax.swing.JPasswordField JpfPasswordAnterior;
    private javax.swing.JPasswordField JpfPasswordConf;
    private javax.swing.JPasswordField JpfPasswordNuevo;
    private javax.swing.JLabel LblPassword;
    private javax.swing.JLabel LblPassword1;
    private javax.swing.JLabel LblPassword2;
    private javax.swing.JLabel LblTitulo;
    private javax.swing.JLabel LblUsername;
    private javax.swing.JPanel PnlUsuario_Datos;
    private javax.swing.JSeparator SptDePanel;
    private javax.swing.JTextField TxtUsername;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JCheckBox jCheckBox5;
    // End of variables declaration//GEN-END:variables

    public void actionPerformed(ActionEvent e)
    {
      if(operacion==0||operacion==3)
      {
      if(ACTION_CLOSE.equals(e.getActionCommand()))
        {
            dispose();
        }
      }
      else
      {
          operacion=0;
      }
    }
    private boolean verificarJpfUsername()
    {
        return JpfPasswordNuevo.getText().equals("");
    }

}

