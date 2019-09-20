package com.manage.auto;

import com.manage.framework.AppConstants;
import com.manage.util.StringUtil;
import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import java.io.*;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;

/**
 * 自动构建类
 * 1.修改定义模块的名称（类名）
 * 2.修改表名
 * 3.将author 改为自己的
 * 4.修改 description 这个模块的说明
 * 5.packageName 模块的包名
 * 注意 ：如果模块不同需要修改模板中的包名！！！
 * @author zhangfeng
 * @create 2018-11-11 16:00
 **/
public class ProjectAutoUtil {
    //定义模块
    private static	String beanModel = "AppLog";
    private static	String tablename = "t_app_log";
    private static	String model = StringUtil.lowerFirst(beanModel);
    private static String author = "wzx";
    private static String description = "应用日志信息";
    private static String datetime = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss").format(new Date());
    //获取当前项目路径
    private static String projectPath = System.getProperty("user.dir");
    private static Properties props = getProperties();

    // 模块的包名
    private static String packageUrl = "src/main/java/com/manage/cochain/";
    private static String packageName = "com.manage.cochain" ;

    public static List<Map<String,String>> loadTable() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            // 获取数据库连接
            String connectionUrl =  props.getProperty("db.master.url");
            String user =  props.getProperty("db.master.user");
            String password =  props.getProperty("db.master.password");
            Connection connection = DriverManager.getConnection(connectionUrl, user,password);
            Statement st = connection.createStatement();
            String sql = "SELECT * FROM "+tablename;
            ResultSet rs = st.executeQuery(sql);
            ResultSetMetaData rsmd = rs.getMetaData();
            List<Map<String,String>> maps = new ArrayList<>();
            for( int i=1; i<=rsmd.getColumnCount(); i++ ){
                String field = rsmd.getColumnName(i);
                int type =  rsmd.getColumnType(i) ;
                String strtype = JDBCTypesUtils.jdbcTypeToJavaType(type).getName();
                Map<String,String> map = new HashMap<>();
                //数据库原有的名字
                map.put("column", field);
                //小写名字
                map.put("name", Tool.lineToHump(field));
                //大写的名字，服务于set get
                map.put("hname", Tool.HlineToHump(field));
                //字段对应的java类型
                map.put("type", strtype);
                //字段对应的java类型
                map.put("dtype", JDBCTypesUtils.getJdbcName(type));
                //没有包的类型名字
                map.put("ctype", strtype.substring(strtype.lastIndexOf(".")+1));
                maps.add(map);
            }
            rs.close();
            st.close();
            connection.close();
            return maps;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

        }
        return null;
    }



    public static void loadTemplate(List<Map<String,String>> maps,String packagename,String filename,String template)  {
        try {
            Configuration cfg = new Configuration(Configuration.VERSION_2_3_21);
            // 指定模板文件从何处加载的数据源，这里设置成一个文件目录。
            cfg.setDirectoryForTemplateLoading(new File(projectPath+"/templates"));
            // 指定模板如何检索数据模型
            cfg.setObjectWrapper(new DefaultObjectWrapper(Configuration.VERSION_2_3_21));

            // 创建根哈希表
            Map root = new HashMap();
            // 在根中放入字符串"user"
            root.put("tablename", tablename);
            root.put("author", author);
            root.put("beanModel", beanModel);
            root.put("model", model);
            root.put("basePath", "${basePath}");
            root.put("datetime", datetime);
            root.put("description", description);
            root.put("packageName", packageName);
            root.put("fields",maps);
            root.put("kuohao", new AddKuohu());
            root.put("listtag", new ListTag());
            root.put("Include", new IncludeTagMethod());


            Template temp = cfg.getTemplate(template);
            File rootPath = new File(projectPath,packagename);
            if(!rootPath.exists()) {
                rootPath.mkdirs();
            }
            File pojo = new File(rootPath,filename);
            Writer out = new OutputStreamWriter(new FileOutputStream(pojo),"utf-8");
            temp.process(root, out);
            out.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    /**
     * 获取配置文件
     *
     * @return 配置Props
     */
    private static Properties getProperties() {
        // 读取配置文件
        Resource resource = new ClassPathResource("/config/application.properties");
        Properties props = new Properties();
        try {
            props = PropertiesLoaderUtils.loadProperties(resource);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return props;
    }

    public static void main(String[] args) throws IOException {

        List<Map<String, String>> maps = loadTable() ;

        String pojo = packageUrl+ "entity/po";
        loadTemplate(maps,pojo,beanModel+"DO.java","do.tml");

        //vo
        String vo = packageUrl+ "entity/vo";
        loadTemplate(maps,vo,beanModel+"VO.java","vo.tml");
        //dto
        String dto = packageUrl+ "entity/dto";
        loadTemplate(maps,dto,beanModel+"DTO.java","dto.tml");

        String mapper = packageUrl+ "mapper";
        loadTemplate(maps,mapper,beanModel+"Mapper.java","mapper.tml");
        String mapperxml = "src/main/resources/sqlMapperXml";
        loadTemplate(maps,mapperxml,beanModel+"Mapper.xml","mapperxml.tml");

        //service
        String service = packageUrl+ "service";
        loadTemplate(maps,service,"I"+beanModel+"Service.java","service.tml");

        String serviceimpl = packageUrl+ "service/impl";
        loadTemplate(maps,serviceimpl,beanModel+"ServiceImpl.java","serviceImpl.tml");

        //controller
        String controller = packageUrl+ "controller";
        loadTemplate(maps,controller,beanModel+"Controller.java","controller.tml");

    }
}