package view;

import controller.grlGeneral.CCUsuario;
import java.awt.Color;
import java.awt.Container;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import modelo.grlGeneral.entidad.CEUsuario;

public class FrmAccesoSistema extends javax.swing.JFrame
{
   
    
    public FrmAccesoSistema()
    {
        initComponents();
           Container con = this.getContentPane();
		con.setBackground( new Color(255,255,255));
                setIconImage (new ImageIcon(getClass().getResource("/util/icono/ayn.png")).getImage());
                jPanelImage1.setImage(new javax.swing.ImageIcon(getClass().getResource("/util/icono/acceso.jpg")).getImage());
      
    }

    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        TxtPsw = new javax.swing.JPasswordField();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        TxtUsuario = new javax.swing.JTextField();
        jPanelImage1 = new util.clases.grlGeneral.JPanelImage();
        jLabel3 = new javax.swing.JLabel();
        jToolBar1 = new javax.swing.JToolBar();
        BtnLogin = new javax.swing.JButton();
        BtnExit = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Acceso de Sistema");
        setBackground(new java.awt.Color(255, 255, 255));
        setResizable(false);

        TxtPsw.setNextFocusableComponent(BtnLogin);
        TxtPsw.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TxtPswActionPerformed(evt);
            }
        });
        TxtPsw.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                TxtPswKeyReleased(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Arial", 1, 12));
        jLabel2.setForeground(new java.awt.Color(153, 0, 0));
        jLabel2.setText("Password:");

        jLabel1.setFont(new java.awt.Font("Arial", 1, 12));
        jLabel1.setForeground(new java.awt.Color(153, 0, 0));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel1.setText("Usuario:");

        TxtUsuario.setNextFocusableComponent(TxtPsw);
        TxtUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TxtUsuarioActionPerformed(evt);
            }
        });
        TxtUsuario.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                TxtUsuarioKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanelImage1Layout = new javax.swing.GroupLayout(jPanelImage1);
        jPanelImage1.setLayout(jPanelImage1Layout);
        jPanelImage1Layout.setHorizontalGroup(
            jPanelImage1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 158, Short.MAX_VALUE)
        );
        jPanelImage1Layout.setVerticalGroup(
            jPanelImage1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 131, Short.MAX_VALUE)
        );

        jLabel3.setFont(new java.awt.Font("Verdana", 1, 18));
        jLabel3.setForeground(new java.awt.Color(0, 51, 153));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("ACCESO DE SISTEMA - Angie & Naidu");
        jLabel3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jToolBar1.setBackground(new java.awt.Color(255, 255, 255));
        jToolBar1.setFloatable(false);
        jToolBar1.setRollover(true);

        BtnLogin.setBackground(new java.awt.Color(255, 255, 255));
        BtnLogin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/util/icono/login.png"))); // NOI18N
        BtnLogin.setBorder(null);
        BtnLogin.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        BtnLogin.setNextFocusableComponent(BtnExit);
        BtnLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnLoginActionPerformed(evt);
            }
        });
        jToolBar1.add(BtnLogin);

        BtnExit.setBackground(new java.awt.Color(255, 255, 255));
        BtnExit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/util/icono/Exit Icon.png"))); // NOI18N
        BtnExit.setBorder(null);
        BtnExit.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        BtnExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnExitActionPerformed(evt);
            }
        });
        jToolBar1.add(BtnExit);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 424, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanelImage1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(TxtPsw, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(TxtUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                    .addComponent(jLabel2)
                                    .addGap(95, 95, 95)))
                            .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(TxtUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(5, 5, 5)
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(30, 30, 30)
                                .addComponent(TxtPsw, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(5, 5, 5)
                        .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, 73, Short.MAX_VALUE))
                    .addComponent(jPanelImage1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(12, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BtnExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnExitActionPerformed
    System.exit(0);
}//GEN-LAST:event_BtnExitActionPerformed

    private void BtnLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnLoginActionPerformed
    validarIngreso();
    }//GEN-LAST:event_BtnLoginActionPerformed
    private void validarIngreso()
    {
        Runnable miRunnable = new Runnable()
           {
            public void run()
            {
                try
                {

                    TxtUsuario.setEditable(false);
                    TxtPsw.setEditable(false);
                    BtnExit.setEnabled(false);
                    BtnLogin.setEnabled(false);

                    CEUsuario oCEUsuario=new CEUsuario();
                    oCEUsuario.setUsuario(TxtUsuario.getText());
                    oCEUsuario.setPassword(String.copyValueOf(TxtPsw.getPassword()));
                    CEUsuario oCEUsuarioT=CCUsuario.validarUsuario(oCEUsuario);

                    if(oCEUsuarioT.getEstado())
                    {
                        FrmSistemaMenu oFrmSistemaMenu=new FrmSistemaMenu(oCEUsuarioT);
                      //  FrmSistemaMenu oFrmSistemaMenu=new FrmSistemaMenu(oCEUsuarioT,"");
                        oFrmSistemaMenu.setVisible(true);
                        dispose();
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(null,"Usuario o Password Incorrecto");
                        TxtUsuario.setEditable(true);
                        TxtPsw.setEditable(true);
                        BtnExit.setEnabled(true);
                        BtnLogin.setEnabled(true);                        
                        TxtPsw.setText("");
                        TxtPsw.requestFocus();
                    }
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        };
        Thread hilo = new Thread(miRunnable);
        hilo.start();
    }
    private void TxtUsuarioKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TxtUsuarioKeyReleased
      if(evt.getKeyCode()==evt.VK_ENTER)
      {
        TxtPsw.requestFocus();
      }
    }//GEN-LAST:event_TxtUsuarioKeyReleased

    private void TxtPswKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TxtPswKeyReleased
     
    }//GEN-LAST:event_TxtPswKeyReleased

    private void TxtUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TxtUsuarioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TxtUsuarioActionPerformed

    private void TxtPswActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TxtPswActionPerformed
    validarIngreso();
    }//GEN-LAST:event_TxtPswActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnExit;
    private javax.swing.JButton BtnLogin;
    private javax.swing.JPasswordField TxtPsw;
    private javax.swing.JTextField TxtUsuario;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private util.clases.grlGeneral.JPanelImage jPanelImage1;
    private javax.swing.JToolBar jToolBar1;
    // End of variables declaration//GEN-END:variables

}
