
package util.clases.grlGeneral;
import java.awt.Toolkit;
import javax.swing.JTextField;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

public class VerificadorDeTxt extends PlainDocument
{
    private JTextField editor;
    private String mStrTipoDeDato;
    private int numeroDeCaracteres;
    public VerificadorDeTxt(String mStrTipoDeDato,int numeroCaracteres,JTextField editor)
    {
        this.mStrTipoDeDato = mStrTipoDeDato;
        this.numeroDeCaracteres = numeroCaracteres;
        this.editor=editor;
    }   
    public String getmStrTipoDeDato()
    {
        return mStrTipoDeDato;
    }
    public void setmStrTipoDeDato(String mStrTipoDeDato)
    {
        this.mStrTipoDeDato = mStrTipoDeDato;
    }
  public void insertString(int arg0, String arg1, AttributeSet arg2) throws BadLocationException
    {
        int size= arg1.length();
        if (this.mStrTipoDeDato.equals("String"))
        {

            if(size>1)
                {
                  if (Character.isLetter(arg1.charAt(arg0))||Character.isSpaceChar(arg1.charAt(arg0))||(arg1.charAt(arg0)+"").equals("'")||(arg1.charAt(arg0)+"").equals("@")||(arg1.charAt(arg0)+"").equals("."))
                  {
                    if(size+this.editor.getText().length()<=this.numeroDeCaracteres)
                     {
                     super.insertString(arg0, arg1.toUpperCase(), arg2);
                     }
                    else
                    {
                        Toolkit.getDefaultToolkit().beep();
                    }
                  return;
                  }
                   else
                    {
                        Toolkit.getDefaultToolkit().beep();
                    }
                  return;
                }
            else
                {
                 for (int i=0;i<size;i++)
                    {
                     if(size+this.editor.getText().length()<=this.numeroDeCaracteres)
                     {
                        if (Character.isLetter(arg1.charAt(i))||Character.isSpaceChar(arg1.charAt(i))||(arg1.charAt(i)+"").equals("'")||(arg1.charAt(i)+"").equals("@")||(arg1.charAt(i)+"").equals("."))
                        {
                            super.insertString(arg0, arg1.toUpperCase(), arg2);
                        }
                        else
                        {
                        Toolkit.getDefaultToolkit().beep();
                        }
                        return;
                    }
                    else
                    {
                        Toolkit.getDefaultToolkit().beep();
                    }
                    return;
                    }
                }
        }
        else
        {
            if (this.mStrTipoDeDato.equals("Numero"))
            {
                if(size>1)
                {
                  if (Character.isDigit(arg1.charAt(arg0))||arg1.charAt(arg0)=='.')
                  {
                          if(arg1.charAt(arg0)=='.')
                          {
                              if(!editor.getText().contains("."))
                              {
                               super.insertString(arg0, arg1, arg2);
                               return;
                              }
                              return;
                          }
                          else
                          {
                             super.insertString(arg0, arg1, arg2);
                             return;
                          }
                  }
                  else
                  {
                   Toolkit.getDefaultToolkit().beep();
                  }
                  return;
                }
                else
                {
                   
                       for (int i=0;i<size;i++)
                        {
                            if (Character.isDigit(arg1.charAt(i))||arg1.charAt(i)=='.')
                              {
                                      if(arg1.charAt(i)=='.')
                                      {
                                          if(!editor.getText().contains("."))
                                          {
                                           super.insertString(arg0, arg1, arg2);
                                           return;
                                          }
                                          return;
                                      }
                                      else
                                      {
                                         super.insertString(arg0, arg1, arg2);
                                         return;
                                      }
                              }
                              else
                              {
                               Toolkit.getDefaultToolkit().beep();
                              }
                              return;
                        }
                    return;
                   
                }
            }
        }
  }
    public int getNumeroDeCaracteres()
    {
     return numeroDeCaracteres;
    }
    public void setNumeroDeCaracteres(int numeroDeCaracteres)
    {
     this.numeroDeCaracteres = numeroDeCaracteres;
    }
 public static float convertFloat(Object txt)
  {
     try{

         if(txt==null)
             return 0;

         if(txt.toString().trim().isEmpty())
             return 0;
         
        return Float.parseFloat(txt.toString());
      }
    catch(Exception e)
     {
      return 0;
    }
 }

 public static long convertLong(Object txt)
  {
     try{

         if(txt==null)
             return 0;

         if(txt.toString().trim().isEmpty())
             return 0;

        return Long.parseLong(txt.toString());
      }
    catch(Exception e)
     {
      return 0;
    }
 }

 public static int convertInteger(Object txt)
  {
     try{

         if(txt==null)
             return 0;

         if(txt.toString().trim().isEmpty())
             return 0;

        return Integer.parseInt(txt.toString());
      }
    catch(Exception e)
     {
      return 0;
    }
 }
public static boolean convertBoolean(Object txt)
  {
     try{

         if(txt==null)
             return false;

         if(txt.toString().trim().isEmpty())
             return false;

        return Boolean.parseBoolean(txt.toString());
      }
    catch(Exception e)
     {
      return false;
    }
 }
 public static float convertInt(Object txt)
  {
     try{
         if(txt==null)
             return 0;
         if(txt.toString().trim().isEmpty())
             return 0;
        return Integer.parseInt(txt.toString());
      }
    catch(Exception e)
     {
      return 0;
    }
 }

 public static String convertString(Object txt)
  {
     try{

         if(txt.toString().trim().isEmpty())
             return "";

         if(txt==null)
             return "";

        return txt.toString();
      }
    catch(Exception e)
     {
      return "";
    }
 }
 
}
