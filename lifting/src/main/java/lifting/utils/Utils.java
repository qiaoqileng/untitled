package lifting.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Utils {
	public static final String DEFAULT_TIME = "yyyy-MM-dd HH:mm:ss";

	public static boolean emptyList(List list){
		return list == null || list.size() == 0;
	}

	public static String getCurrTime(){
		SimpleDateFormat format = new SimpleDateFormat(DEFAULT_TIME);
		return format.format(new Date());
	}
}
