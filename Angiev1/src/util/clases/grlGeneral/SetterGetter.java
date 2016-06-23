package util.clases.grlGeneral;

public class SetterGetter
{
    private int mIntTipo;

    public String convertir(String mStrParametro)
    {
        /**
        * Si mIntTipo 1 set desde Vista o Datos;
        */
        if(getmIntTipo()==1)
        {
           if(mStrParametro.equals(""))
           {
               return null;
           }
           else
           {
               return mStrParametro;
           }
        }

        else
        {
             if(getmIntTipo()==2)
             {
              /**
                * Si mIntTipo 2 get desde Datos;
              **/
                   if(mStrParametro==null)
                   {
                       return null;
                   }
                   else
                   {
                       return mStrParametro;
                   }
             }
             else
             {
                if(getmIntTipo()==3)
                {
                  /**
                    * Si mIntTipo 3 get desde Vista;
                  **/
                   if(mStrParametro==null)
                   {
                       return "";
                   }
                   else
                   {
                       return mStrParametro;
                   }
                }
                return "";
             }
        }
    }

    public int getmIntTipo() {
        return mIntTipo;
    }

    public void setmIntTipo(int mIntTipo) {
        this.mIntTipo = mIntTipo;
    }

}
