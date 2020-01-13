package akademia.databaseStorage.commons;

import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class CustomDataParser {

  public static Date fromStringToSqlDate(String date, String pattern) {
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern); //np.: yyyy-MM-dd

    java.util.Date parsedDate;
    try {
      parsedDate = simpleDateFormat.parse(date);
    } catch (ParseException e) {
      e.printStackTrace();
      return null;
    }
    return new Date(parsedDate.getTime());
  }

  public static String fromUtilDateToString(java.util.Date date, String pattern) {
    DateFormat formatter = new SimpleDateFormat(pattern);
    return formatter.format(date);
  }

}
