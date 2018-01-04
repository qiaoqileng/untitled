package lifting.control;

import java.util.List;

import lifting.model.User;

import com.jfinal.core.Controller;

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
		user.save();
		list();
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
}
