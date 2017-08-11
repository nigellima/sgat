package jsf.util;

import java.util.Calendar;
import java.util.Date;


public class DateUtils {
    public static Date ingnoreTime(Date date) {
        Calendar calendar = Calendar.getInstance();

        calendar.setTime( date );
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        return calendar.getTime();
    }
    
    public static Date ingnoreDate(Date date, Date defaultDate){
        Calendar calendar = Calendar.getInstance();
        Calendar aux = Calendar.getInstance();
        
        calendar.setTime(date);
        aux.setTime(defaultDate);
        
        calendar.set(aux.getTime().getYear(), aux.getTime().getMonth(), aux.getTime().getDate());
        
        return calendar.getTime();
    }
    
    public static String monthNumberToMonthName(int month)
    {
        switch (month)
        {
           case 1:
               return "janeiro";
           case 2:
               return "fevereiro";
           case 3:
               return "março";
           case 4:
               return "abril";
           case 5:
               return "maio";
           case 6:
               return "junho";
           case 7:
               return "julho";
           case 8:
               return "agosto";
           case 9:
               return "setembro";
           case 10:
               return "outubro";
           case 11:
               return "novembro";
           case 12:
               return "dezembro";
           default:
               return "null";
       }
   }
    
    public static String dateFormatBR(Date dt)
    {
        Calendar cal = Calendar.getInstance();
        cal.setTime(dt);
        int year = cal.get(Calendar.YEAR);
        int month = 1 + cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);
        
        return day+"/"+month+"/"+year;
    }
    
    public static String dateFormatMysql(Date dt)
    {
        Calendar cal = Calendar.getInstance();
        cal.setTime(dt);
        int year = cal.get(Calendar.YEAR);
        int month = 1 + cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);
        
        return year+"-"+((month<10)?"0":"") + month+"-"+((day<10)?"0":"") + day;
    }
    
    public static int[] getSeparatedDateNumbers(Date dt)
    {
        int numbers[] = new int[3];
        Calendar cal = Calendar.getInstance();
        cal.setTime(dt);
        numbers[2] = cal.get(Calendar.YEAR);
        numbers[1] = 1 + cal.get(Calendar.MONTH);
        numbers[0] = cal.get(Calendar.DAY_OF_MONTH);
        
        return numbers;
    }
}
