package lifting.control;

import com.jfinal.core.Controller;
import lifting.bean.MapResult;
import lifting.bean.StringResult;
import lifting.model.Product;

public class ProductController extends Controller{
	public void index(){
		renderText("Hello JFinal World."+getPara());
	}

	public void getById(){
		try {
			String id = getPara("productId");
			renderJson(new MapResult(false, Product.dao.getProductById(Long.parseLong(id)),""));
		}catch (Exception e){
			renderJson(new StringResult(true,e.getMessage(),e.getMessage()));
		}
	}

	public void getList(){
		Product.dao.paginate()
	}
}
