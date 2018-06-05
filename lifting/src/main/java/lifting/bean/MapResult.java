package lifting.bean;

import lifting.bean.base.CommonResult;

import java.util.Map;

public class MapResult extends CommonResult<Map>{

    public MapResult(boolean error, Map map, String errorMessage) {
        super(error, map, errorMessage);
    }
}
