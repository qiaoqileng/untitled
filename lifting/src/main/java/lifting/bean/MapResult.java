package lifting.bean;

import com.jfinal.template.expr.ast.Map;
import lifting.bean.base.CommonResult;

public class MapResult extends CommonResult<Map>{

    public MapResult(boolean error, Map map, String errorMessage) {
        super(error, map, errorMessage);
    }
}
