package lifting.config;

import com.jfinal.json.FastJsonFactory;
import com.jfinal.json.JacksonFactory;
import lifting.control.AttachController;
import lifting.control.HelloController;
import lifting.control.ProductController;
import lifting.control.UserController;
import lifting.json.MyJsonFactory;
import lifting.model._MappingKit;

import com.jfinal.config.Constants;
import com.jfinal.config.Handlers;
import com.jfinal.config.Interceptors;
import com.jfinal.config.JFinalConfig;
import com.jfinal.config.Plugins;
import com.jfinal.config.Routes;
import com.jfinal.kit.PathKit;
import com.jfinal.kit.PropKit;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.activerecord.CaseInsensitiveContainerFactory;
import com.jfinal.plugin.activerecord.dialect.MysqlDialect;
import com.jfinal.plugin.c3p0.C3p0Plugin;
import com.jfinal.render.ViewType;
import com.jfinal.template.Engine;

public class Config extends JFinalConfig{

	@Override
	public void configConstant(Constants me) {
		me.setDevMode(true);
		me.setViewType(ViewType.JSP);
//		me.setJsonFactory(new MyJsonFactory());
		PropKit.use("config.properties");
	}

	@Override
	public void configRoute(Routes me) {
		me.add("/hello", HelloController.class);
		me.add("/user",UserController.class);
		me.add("/attach",AttachController.class);
		me.add("/product", ProductController.class);
	}

	@Override
	public void configEngine(Engine me) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void configPlugin(Plugins me) {
		C3p0Plugin cp = new C3p0Plugin(PropKit.get("jdbcUrl"),PropKit.get("user"), PropKit.get("password"));
        // 配置Mysql驱动
        cp.setDriverClass("com.mysql.jdbc.Driver");
        me.add(cp);
        ActiveRecordPlugin arp = new ActiveRecordPlugin(cp);
        arp.setBaseSqlTemplatePath(PathKit.getRootClassPath());
        arp.addSqlTemplate("all.sql");
        me.add(arp);
        // 配置Mysql方言
        arp.setDialect(new MysqlDialect());
        // 配置属性名(字段名)大小写不敏感容器工厂
        arp.setContainerFactory(new CaseInsensitiveContainerFactory());
        _MappingKit.mapping(arp);
	}

	@Override	
	public void configInterceptor(Interceptors me) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void configHandler(Handlers me) {
		// TODO Auto-generated method stub
		
	}

}
