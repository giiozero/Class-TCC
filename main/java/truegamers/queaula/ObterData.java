package truegamers.queaula;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;


/**
 * Created by giovanni on 12/08/2015.
 */
public class ObterData {

    private static String nome="";
    public static String ObterData()  {
        Date d = new Date();
        Calendar c = new GregorianCalendar();
        c.setTime(d);

         int dia = c.get(c.DAY_OF_WEEK);
        switch(dia){
            case Calendar.SUNDAY: nome = "Domingo";break;
            case Calendar.MONDAY: nome = "Segunda";break;
            case Calendar.TUESDAY: nome = "Terça";break;
            case Calendar.WEDNESDAY: nome = "Quarta";break;
            case Calendar.THURSDAY: nome = "Quinta";break;
            case Calendar.FRIDAY: nome = "Sexta";break;
            case Calendar.SATURDAY: nome = "Sabado";break;
        }
        return nome;
    }


}
