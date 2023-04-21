package hw;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

public class GeneratorCode {

    private static final String [] tables = {"sys_user","sys_role","sys_permission","sys_department"};  //待映射的表

    public static void main(String[] args) {

        //1.代码生产器
        AutoGenerator ag = new AutoGenerator();

        //2.全局配置
        GlobalConfig globalConfig = new GlobalConfig();
        globalConfig.setOutputDir("E:\\code-generation") // 输出路径
                .setOpen(false) // 是否打开输出目录
                .setFileOverride(true)  // 文件覆盖
                .setIdType(IdType.AUTO) // 主键策略
                .setDateType(DateType.ONLY_DATE) // 日期类型
                .setAuthor("hw");  //作者
        ag.setGlobalConfig(globalConfig);

        //3.数据源配置
        DataSourceConfig dsConfig  = new DataSourceConfig();
        dsConfig.setDbType(DbType.MYSQL)  // 数据库类型
                .setDriverName("com.mysql.cj.jdbc.Driver") //驱动
                .setUrl("jdbc:mysql://localhost:3306/db_authority_system?useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC")  //连接地址
                .setUsername("root")  //用户名
                .setPassword("ROOT"); //密码
        ag.setDataSource(dsConfig);

        //4.包配置
        PackageConfig pkConfig = new PackageConfig();
        pkConfig.setParent("com.hw") //顶级包结构
                .setMapper("mapper")    //数据访问层
                .setService("service")   //业务逻辑层
                .setController("controller") //控制器
                .setEntity("entity")   //实体类
                .setXml("mapperXml");  //mapper映射文件
        ag.setPackageInfo(pkConfig);

        //5.策略配置
        StrategyConfig stConfig = new StrategyConfig();
        stConfig.setNaming(NamingStrategy.underline_to_camel) // 表映射到实体的命名策略
                .setColumnNaming(NamingStrategy.underline_to_camel) // 列映射到字段的命名策略
                .setTablePrefix("sys_")
                .setInclude(tables)  // 待映射的表
                .setEntityLombokModel(true); //支持Lombok
        ag.setStrategy(stConfig);

        //6.生成代码
        ag.execute();
    }
}
