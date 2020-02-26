import lombok.extern.slf4j.Slf4j;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 * @Author Alben Yuan
 * @Date 2019-04-23 17:35
 */
@Slf4j
public class DateFormatUtils {

    public static final String format = "yyyy-MM-dd'T'HH:mm:ssXXX";
    public static final String DATE_TEXT = "2001-07-04T12:08:56+05:30";

    public static void main(String[] args) throws ParseException {

        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        dateFormat.setTimeZone(TimeZone.getTimeZone(ZoneId.of("Asia/Kolkata")));
        Date date = dateFormat.parse(DATE_TEXT);

        System.out.println(DATE_TEXT);
        System.out.println(date);
        System.out.println(dateFormat.format(date));

    }

}
