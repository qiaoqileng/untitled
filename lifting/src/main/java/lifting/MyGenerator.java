package lifting;

import javax.sql.DataSource;

import com.jfinal.kit.PathKit;
import com.jfinal.plugin.activerecord.dialect.MysqlDialect;
import com.jfinal.plugin.activerecord.generator.Generator;
import com.jfinal.plugin.c3p0.C3p0Plugin;

public class MyGenerator {
	
	public static void main(String[] args) {
		// base model 所使用的包名
		String baseModelPkg = "lifting.basemodel";
		// base model 文件保存路径
		String baseModelDir = "F:\\work\\web\\demo\\lifing\\src\\lifting\\basemodel";
		 
		// model 所使用的包名
		String modelPkg = "lifting.model";
		// model 文件保存路径
		String modelDir = "F:\\work\\web\\demo\\lifing\\src\\lifting\\model";
		 
		Generator gernerator = new Generator(getDataSource(), baseModelPkg, baseModelDir,modelPkg, modelDir);
		gernerator.setDialect(new MysqlDialect()); 
		gernerator.generate();
	}
	
	public static DataSource getDataSource() {
		C3p0Plugin c3p0Plugin = new C3p0Plugin("jdbc:mysql://localhost:3306/lifting?3useUnicode=true&characterEncoding=utf8",
                "root", "qq493365437");
		c3p0Plugin.setDriverClass("com.mysql.jdbc.Driver");
		c3p0Plugin.start();
		return c3p0Plugin.getDataSource();
	}

}
