package lifting.bean;

import lifting.bean.base.CommonResult;

public class StringResult extends CommonResult<String>{

    public StringResult(boolean error, String results, String errorMessage) {
        super(error, results, errorMessage);
    }
}
