package lifting.control;

import java.sql.SQLException;
import java.util.List;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.IAtom;
import com.taobao.api.ApiException;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.internal.util.StringUtils;
import com.taobao.api.request.TbkItemGetRequest;
import com.taobao.api.request.TimeGetRequest;
import com.taobao.api.response.TbkItemGetResponse;
import com.taobao.api.response.TimeGetResponse;
import lifting.bean.StringResult;
import lifting.bean.UserResult;
import lifting.model.User;

import com.jfinal.core.Controller;
import lifting.utils.Utils;

public class UserController extends Controller{
	

	public void getUserInfo(){
		String json = "json demo";
		renderJsp("user_list.jsp");
	}
	
	public void list(){
		List<User> users = User.dao.find("select * from user");
		setAttr("users", users);
		System.out.println(users.toString());
		render("user_list.jsp");
	}
	
	public void form(){
		render("user_form.jsp");
	}
	
	public void submit() {
		User user = getModel(User.class,"user");
		String name = user.getName();
		String sql = User.dao.getSql("user.join_us");
		Db.tx(new IAtom() {
			@Override
			public boolean run() throws SQLException {
				int result = Db.update(sql,
						user.getQq(),
						user.getEmail(),
						user.getShopUrl(),
						user.getShopAddress(),
						user.getShopBrand(),
						user.getShopTaobaoUrl(),
						user.getShopCreateTime(),
						user.getShopType(),
						user.getShopStyle(),
						user.getName());
				renderJson(new StringResult(result==0,"success",""));
				return false;
			}
		});
//		user.save();
//		list();
	}

	public void login(){
		User user = getModel(User.class,"user");
		String openId = user.getOpenId();
		if (StringUtils.isEmpty(openId)){
			renderJson(new StringResult(true,"failed","第三方凭证失效"));
		} else {
			String sql = User.dao.getSql("user.find_by_open_id");
			List<User> users = User.dao.find(sql, openId);
			user.setLastLoginTime(Utils.getCurrTime());
			boolean result = false;
			if (!Utils.emptyList(users)){
				result = user.update();
			} else {
				result = user.save();
			}
			if (result){
				//  2018/3/14 设置session
				createToken();
				renderJson(new UserResult(false,user,"登陆成功"));
			} else {
				renderJson(new StringResult(true,"failed","登陆异常"));
			}
		}
	}

	public void edit() {
		String value = getPara();
		User user = User.dao.findById(value);
		setAttr("user",user);
		form();
	}
	
	public void delete() {
		String value = getPara();
		boolean success = User.dao.deleteById(value);
		if(success){
			list();
		} else {
			renderText("delete failed！");
		}
	}

	public void taobao(){
		DefaultTaobaoClient client = new DefaultTaobaoClient("http://gw.api.taobao.com/router/rest", "23577685", "79f17f8f67dc1fc3b5d01294edab5066");
		TbkItemGetRequest req = new TbkItemGetRequest();
		req.setFields("num_iid,title,pict_url,small_images,reserve_price,zk_final_price,user_type,provcity,item_url,seller_id,volume,nick");
		req.setQ("女装");
		req.setCat("16,18");
		req.setItemloc("杭州");
//		req.setSort("tk_rate_des");
//		req.setIsTmall(false);
//		req.setIsOverseas(false);
//		req.setStartPrice(10L);
//		req.setEndPrice(10L);
//		req.setStartTkRate(123L);
//		req.setEndTkRate(123L);
//		req.setPlatform(1L);
//		req.setPageNo(123L);
//		req.setPageSize(20L);
		TbkItemGetResponse response = null;
		try {
			response = client.execute(req);
		} catch (ApiException e) {
			e.printStackTrace();
		}
		if (response!=null&&response.isSuccess()) {
			renderText(response.getBody());
		} else {
			renderText("error");
		}
	}
}
