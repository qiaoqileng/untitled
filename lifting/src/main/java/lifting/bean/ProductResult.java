package lifting.bean;

import lifting.bean.base.CommonResult;
import lifting.model.Product;

public class ProductResult extends CommonResult<Product>{

    public ProductResult(boolean error, Product results, String errorMessage) {
        super(error, results, errorMessage);
    }
}
