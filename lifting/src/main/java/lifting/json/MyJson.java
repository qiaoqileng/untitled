package lifting.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jfinal.json.Json;

public class MyJson extends Json {
    @Override
    public String toJson(Object object) {
        // 优先使用对象级的属性 datePattern, 然后才是全局性的 defaultDatePattern
        String dp = datePattern != null ? datePattern : getDefaultDatePattern();
        if (dp == null) {
            return new Gson().toJson(object);
        } else {
            return new GsonBuilder().setDateFormat(dp).create().toJson(object);	// return JSON.toJSONString(object, SerializerFeature.WriteDateUseDateFormat);
        }
    }

    @Override
    public <T> T parse(String jsonString, Class<T> type) {
        String dp = datePattern != null ? datePattern : getDefaultDatePattern();
        if (dp == null) {
            return new Gson().fromJson(jsonString,type);
        } else {
            return new GsonBuilder().setDateFormat(dp).create().fromJson(jsonString,type);	// return JSON.toJSONString(object, SerializerFeature.WriteDateUseDateFormat);
        }
    }
}
