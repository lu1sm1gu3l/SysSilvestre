/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * DialagoCerrarKey.java
 *
 * Created on 10-sep-2011, 16:20:19
 */

package util.clases.grlGeneral;

import java.awt.Color;
import java.awt.Container;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.JComponent;
import javax.swing.JRootPane;
import javax.swing.KeyStroke;

/**
 *
 * @author Elizabeth Manrique
 */
public class DialogoPadre extends javax.swing.JDialog implements ActionListener
{

    /** Creates new form DialagoCerrarKey */
    public DialogoPadre(java.awt.Frame parent, boolean modal)
    {
        super(parent, modal);
     //   setIconImage (new ImageIcon(getClass().getResource("/util/icono/ayn.png")).getImage());
        Container con = this.getContentPane();
	//con.setBackground( new Color(204,226,230));
       // con.setBackground( new Color(190,215,225));
    //    con.setBackground( new Color(240,240,240));
     //   con.setBackground( new Color(255,204,204));
   //     con.setBackground( new Color(198,236,198));
   //     con.setBackground( new Color(255,255,255));        
        initComponents();
    }



    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
    private static final String ACTION_CLOSE = "ACTION_CLOSE";

    protected JRootPane createRootPane()
    {
        JRootPane rootPane = new JRootPane();

        KeyStroke esc = KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0);
        rootPane.registerKeyboardAction(this,ACTION_CLOSE, esc, JComponent.WHEN_IN_FOCUSED_WINDOW);
        return rootPane;
    }
    public void actionPerformed(ActionEvent e)
    {
    dispose();
    }

}
