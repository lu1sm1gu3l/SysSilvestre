/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package util.clases.grlGeneral;

import view.FrmAccesoSistema;
import view.FrmSistemaMenu;

/**
 *
 * @author Katya
 */
public class CLRedondear {


    public static double Redondear(float numero,int digitos)
    {     int cifras=(int) Math.pow(10,digitos);
                   return Math.rint(numero*cifras)/cifras;
    }

    public static double RedondearDouble(double numero,int digitos)
    {     int cifras=(int) Math.pow(10,digitos);
                   return Math.rint(numero*cifras)/cifras;
    }
    public static String RedondearString(float numero,int digitos)
    {
      int cifras=(int) Math.pow(10,digitos);
          double numRedond=(Math.rint(numero*cifras)/cifras);

          String numstrg=Double.toString(numRedond);
          String numint=numstrg.substring(0,numstrg.indexOf('.'));
         String numdec=numstrg.substring(numstrg.indexOf('.')+1,numstrg.length());
         String decimal=numdec;
         for (int i = numdec.length(); i < digitos; i++) {

            decimal=decimal+0;

        }
                   return numint +"."+ decimal;
    }

    public static String RedondearStringDouble(double numero,int digitos)
    {
      int cifras=(int) Math.pow(10,digitos);
          double numRedond=(Math.rint(numero*cifras)/cifras);

          String numstrg=Double.toString(numRedond);
          String numint=numstrg.substring(0,numstrg.indexOf('.'));
         String numdec=numstrg.substring(numstrg.indexOf('.')+1,numstrg.length());
         String decimal=numdec;
         for (int i = numdec.length(); i < digitos; i++) {

            decimal=decimal+0;

        }
                   return numint +"."+ decimal;
    }
    
//    public static String RedondearEnteroInmeditoSup(float numero)
//    {
//            int cifras=(int) Math.pow(10,1);
//          double numRedond=(Math.rint(numero*cifras)/cifras);
//
//          String numstrg=Double.toString(numRedond);
//          String numint=numstrg.substring(0,numstrg.indexOf('.'));
//         String numdec=numstrg.substring(numstrg.indexOf('.')+1,numstrg.length());
//         String decimal=numdec;
//         for (int i = numdec.length(); i < 2; i++) {
//
//            decimal=decimal+0;
//
//        }
//                   return numint +"."+ decimal;
//    }
     public static String RedondearEnteroInmeditoSup(float numero)
    {
            String numstrg=RedondearString(numero,2);
            String numeroTruncado=numstrg.substring(0,numstrg.length()-1);
            String numParteDecimal=numstrg.substring(numstrg.length()-1,numstrg.length());
            if(!numParteDecimal.equals("0"))
            {
             double numTruncado=Double.parseDouble(numeroTruncado);
             numTruncado=numTruncado+Double.parseDouble("0.1");
             return RedondearString((float)numTruncado,2);
            }



                   return numeroTruncado+"0";//numParteEntera+"."+decimal;
    }


//    public static float RedondearEnteroInmeditoSup(double  numero)
//    {     return (float)Math.ceil(numero);
//    }
    public static int getNumberOfDecimalPlace(float value) {
    
        if (Math.round(value) == value) return 0;
        final String s = Float.toString(value);
        final int index = s.indexOf('.');
        if (index < 0) {
           return 0;
        }
        return s.length() - 1 - index;
    }

    public  static String FormatearDosDigitos( float Numero)
    {


           String numstrg=Float.toString(Numero);
          String numint=numstrg.substring(0,numstrg.indexOf('.'));
         String numdec=numstrg.substring(numstrg.indexOf('.')+1,numstrg.length());
         String decimal=numdec;
         for (int i = numdec.length(); i < 2; i++) {

            decimal=decimal+0;

        }
                   return numint +"."+ decimal;

    }

}
