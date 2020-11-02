package com.gh.generator;

import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import org.springframework.util.StringUtils;

import java.util.Scanner;

public class GeneratorApplication {

	private static String DBURL = "jdbc:mysql://localhost:3306/blog?useUnicode=true&serverTimezone=GMT&useSSL=false&characterEncoding=utf8";
	private static String DBDRIVER = "com.mysql.cj.jdbc.Driver";
	private static String DBUSERNAME = "root";
	private static String DBPASSWORD = "root";

	/**
	 * <p>
	 * 读取控制台内容
	 * </p>
	 */
	public static String scanner(String tip) {
		Scanner scanner = new Scanner(System.in);
		StringBuilder help = new StringBuilder();
		help.append("请输入" + tip + "：");
		System.out.println(help.toString());
		if (scanner.hasNext()) {
			String ipt = scanner.next();
			if (!StringUtils.isEmpty(ipt)) {
				return ipt;
			}
		}
		throw new MybatisPlusException("请输入正确的" + tip + "！");
	}

	public static void main(String[] args) {
		AutoGenerator mpg = new AutoGenerator();

		GlobalConfig gc = new GlobalConfig();
		String projectPath = System.getProperty("user.dir");
		// 项目的绝对路径 例：D:\work\gh-blog\common\src\main\java
		gc.setOutputDir("D:\\work\\gh-blog\\common\\src\\main\\java");
		gc.setAuthor("gaohan");   // 作者
		gc.setOpen(false);      //生成代码后是否打开文件夹
		gc.setServiceName("%sService");  // 设置Service接口生成名称,这样生成接口前面就不会有 I
		mpg.setGlobalConfig(gc);

		DataSourceConfig dsc = new DataSourceConfig();
		dsc.setUrl(DBURL);
		dsc.setDriverName(DBDRIVER);
		dsc.setUsername(DBUSERNAME);
		dsc.setPassword(DBPASSWORD);
		mpg.setDataSource(dsc);

		// 包配置
		PackageConfig pc = new PackageConfig();
		pc.setModuleName(scanner("模块名称")); // 模块名称, 这里可以根据不同模块来写
		pc.setParent("com.gh.common"); // 父包名
		mpg.setPackageInfo(pc);

		// 策略配置
		StrategyConfig strategy = new StrategyConfig();
		strategy.setNaming(NamingStrategy.underline_to_camel);
		strategy.setColumnNaming(NamingStrategy.underline_to_camel);
		strategy.setEntityLombokModel(true);
		strategy.setInclude(scanner("表名"));  // 如果要生成多个,这里可以传入String[]
		mpg.setStrategy(strategy);
		mpg.execute();
	}

}
