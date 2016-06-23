package util.clases.grlGeneral;

import java.awt.Color;


public class ValidadorJTextField extends javax.swing.JPanel
{

    private boolean required;

    public ValidadorJTextField()
    {
        initComponents();
        LblIconValidador.setVisible(false);
        setRequired(false);
    }
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        TxtTexto = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        LblIconValidador = new javax.swing.JLabel();

        TxtTexto.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        TxtTexto.setPreferredSize(new java.awt.Dimension(4, 10));
        TxtTexto.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                TxtTextoFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                TxtTextoFocusLost(evt);
            }
        });

        jPanel1.setLayout(new javax.swing.BoxLayout(jPanel1, javax.swing.BoxLayout.LINE_AXIS));

        LblIconValidador.setIcon(new javax.swing.ImageIcon(getClass().getResource("/util/icono/alerta.png"))); // NOI18N
        jPanel1.add(LblIconValidador);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(TxtTexto, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(TxtTexto, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(layout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void TxtTextoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_TxtTextoFocusGained
        getTxtTexto().setBackground(new Color(255,255,204));
    }//GEN-LAST:event_TxtTextoFocusGained

    private void TxtTextoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_TxtTextoFocusLost
        getTxtTexto().setBackground(Color.WHITE);
    }//GEN-LAST:event_TxtTextoFocusLost

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel LblIconValidador;
    private javax.swing.JTextField TxtTexto;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
    public void typeDataStored(String typeDataStored)
    {
        Object oValidador=this.getTxtTexto().getDocument();

        if(oValidador instanceof VerificadorDeTxt_)
        {
            ((VerificadorDeTxt_)oValidador).setmStrTipoDeDato(typeDataStored);
        }
        else
        {
            this.getTxtTexto().setDocument(new VerificadorDeTxt_("",10, getTxtTexto()));
        }
    }
    public void maxLengthText(int max)
    {
        Object oValidador=this.getTxtTexto().getDocument();

        if(oValidador instanceof VerificadorDeTxt_)
        {
            ((VerificadorDeTxt_)oValidador).setNumeroDeCaracteres(max);
        }
        else
        {
            this.getTxtTexto().setDocument(new VerificadorDeTxt_("",max, getTxtTexto()));
        }
    }
    public void showIconRequired(boolean show)
    {
        getLblIconValidador().setVisible(show);

        repaint();
    }

    public boolean isRequired()
    {
        return required;
    }

    public void setRequired(boolean required)
    {
        this.required = required;
    }

    /**
     * @return the LblIconValidador
     */
    public javax.swing.JLabel getLblIconValidador() {
        return LblIconValidador;
    }

    /**
     * @param LblIconValidador the LblIconValidador to set
     */
    public void setLblIconValidador(javax.swing.JLabel LblIconValidador) {
        this.LblIconValidador = LblIconValidador;
    }

    /**
     * @return the TxtTexto
     */
    public javax.swing.JTextField getTxtTexto() {
        return TxtTexto;
    }

    /**
     * @param TxtTexto the TxtTexto to set
     */
    public void setTxtTexto(javax.swing.JTextField TxtTexto) {
        this.TxtTexto = TxtTexto;
    }




}
