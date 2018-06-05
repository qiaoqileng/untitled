package lifting.json;

import com.google.gson.Gson;
import com.jfinal.json.IJsonFactory;
import com.jfinal.json.Json;

public class MyJsonFactory implements IJsonFactory {
    @Override
    public Json getJson() {
        return new MyJson();
    }
}
