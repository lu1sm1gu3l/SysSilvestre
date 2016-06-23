/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package util.clases.grlGeneral;

import javax.swing.JOptionPane;

/**
 *
 * @author Joel
 */
public class CLMgs
{
    public static void mgsTransaccion(int acc)
    {
        String adic = "";
        switch(acc)
        {
            case CLABM.INSERT :
                        adic = " AGREGADO ";
                break;

            case CLABM.DELETE :
                        adic = " ELIMINADO ";
                break;

            case CLABM.UPDATE :
                        adic = " ACTUALIZADO ";
                break;
            default:
                    adic = "SIN ACCION ";
                    break;
        }

        JOptionPane.showMessageDialog(null, "Sus datos se han"+adic+"correctamente!");
    }

    public static void mgsNOTransaccion(int acc)
    {
        String adic = "";
        switch(acc)
        {
            case CLABM.INSERT :
                        adic = " AGREGAR ";
                break;

            case CLABM.DELETE :
                        adic = " ELIMINAR ";
                break;

            case CLABM.UPDATE :
                        adic = " ACTUALIZAR ";
                break;
            default:
                    adic = "SIN ACCION ";
                    break;
        }

        JOptionPane.showMessageDialog(null, "Error al "+adic+"los datos!", "Error", JOptionPane.ERROR_MESSAGE);
    }

     public static int mgsConfirmacion(int acc)
    {
        String adic = "";
        switch(acc)
        {
            case CLABM.INSERT :
                        adic = " AGREGAR ";
                break;

            case CLABM.DELETE :
                        adic = " ELIMINAR ";
                break;

            case CLABM.UPDATE :
                        adic = " ACTUALIZAR ";
                break;
            default:
                    adic = "SIN ACCION ";
                    break;
        }
       return JOptionPane.showConfirmDialog(null,"Desea"+adic+"los datos?","Aviso"
                                            ,JOptionPane.YES_NO_OPTION);
    }

    public static void mgsSinDatos()
    {
      JOptionPane.showMessageDialog(null, "Verificar datos!");
    }

    public static int OK()
    {
        return JOptionPane.OK_OPTION;
    }
}
