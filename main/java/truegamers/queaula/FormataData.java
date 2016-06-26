package truegamers.queaula;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;


/**
 * Created by giovanni on 12/08/2015.
 */
public class FormataData {

    private static String nome="";
    public static String FormataData_ObtemAuto()  {
        Date d = new Date();
        Calendar c = new GregorianCalendar();
        c.setTime(d);

         int dia = c.get(c.DAY_OF_WEEK);
        switch(dia){
            case Calendar.SUNDAY: nome = "2";break;
            case Calendar.MONDAY: nome = "1";break;
            case Calendar.TUESDAY: nome = "3";break;
            case Calendar.WEDNESDAY: nome = "4";break;
            case Calendar.THURSDAY: nome = "5";break;
            case Calendar.FRIDAY: nome = "6";break;
            case Calendar.SATURDAY: nome = "4";break;
        }
        return nome;
    }

    public static String DataNumParaFeira(String Data) {
        switch (Data) {
            case "2":
                Data = "Segunda";
                break;
            case "3":
                Data = "Terça";
                break;
            case "4":
                Data = "Quarta";
                break;
            case "Quinta":
                Data = "5";
                break;
            case "6":
                Data = "Sexta";
                break;

        }
        return Data;
    }

    public static String DataFeiraParaNum(String Data) {
        switch (Data) {
            case "Segunda":
                Data = "1";
            break;
            case "Terça":
                Data = "3";
                break;
            case "Quarta":
                Data = "4";
                break;
            case "Quinta":
                Data = "5";
                break;
            case "Sexta":
                Data = "6";
                break;

        }
        return Data;
    }
}
