/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package util.clases.grlGeneral;

import java.awt.Component;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

/**
 *
 * @author Morales
 */
public class CLHabilitarControles {


    public void HabilitarControles(JMenuBar menubar,String NombreComp)
    {
        Component[] lstComponentes= menubar.getComponents();
        for (Component ocomponent : lstComponentes) {

            if(ocomponent instanceof JMenu)
            {
                JMenu oJMenu=(JMenu)ocomponent;

              //  System.out.println(oJMenu.getText());

                Component[] lstMnItem= oJMenu.getMenuComponents();
                for (Component oCmpMnItem : lstMnItem) {
                     if (oCmpMnItem instanceof JMenu)
                    {
                        JMenu oJSubMenu=(JMenu)oCmpMnItem;

                       Component[] lstsubItem= oJSubMenu.getMenuComponents();

                                for (Component oCmpSubItem : lstsubItem) {

                                        if(oCmpSubItem instanceof JMenuItem)
                                            {
                                                JMenuItem oJItem=(JMenuItem)oCmpSubItem;

                                                String nameCompItem=oJItem.getText().toUpperCase();
                                       //        System.out.println("******" +oJItem.getText());
                                                if(nameCompItem.equals(NombreComp.toUpperCase()))
                                                {

                                                    oJItem.setVisible(true);
                                                    return;
                                                }

                                            }
                                    }
                         }

                            else if (oCmpMnItem instanceof JMenuItem)
                            {
                                JMenuItem oJMenuItem=(JMenuItem)oCmpMnItem;
                                String nameComp=oJMenuItem.getText().toUpperCase();

                                    if(nameComp.equals(NombreComp.toUpperCase()))
                                    {
                                   // System.out.println("--" +oJMenuItem.getText());
                                    oJMenuItem.setVisible(true);
                                    return;
                                    }

                              }

                    
                }

            }
        }
    }

    public void DeshabilitarControles(JMenuBar menubar)
    {
        Component[] lstComponentes= menubar.getComponents();
        for (Component ocomponent : lstComponentes) {

            if(ocomponent instanceof JMenu)
            {
                JMenu oJMenu=(JMenu)ocomponent;

              //  System.out.println(oJMenu.getText());

                Component[] lstMnItem= oJMenu.getMenuComponents();
                for (Component oCmpMnItem : lstMnItem) {
                     if (oCmpMnItem instanceof JMenu)
                    {
                        JMenu oJSubMenu=(JMenu)oCmpMnItem;

                       Component[] lstsubItem= oJSubMenu.getMenuComponents();

                                for (Component oCmpSubItem : lstsubItem) {

                                        if(oCmpSubItem instanceof JMenuItem)
                                            {
                                                JMenuItem oJItem=(JMenuItem)oCmpSubItem;
                                                    oJItem.setVisible(false);
                                            }
                                    }
                         }

                            else if (oCmpMnItem instanceof JMenuItem)
                            {
                                JMenuItem oJMenuItem=(JMenuItem)oCmpMnItem;
                                    oJMenuItem.setVisible(false);
                              }


                }

            }
        }
    }
}
