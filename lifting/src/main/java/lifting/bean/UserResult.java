package lifting.bean;

import lifting.bean.base.CommonResult;
import lifting.model.User;

public class UserResult extends CommonResult<User>{

    public UserResult(boolean error, User user, String errorMessage) {
        super(error, user, errorMessage);
    }
}
