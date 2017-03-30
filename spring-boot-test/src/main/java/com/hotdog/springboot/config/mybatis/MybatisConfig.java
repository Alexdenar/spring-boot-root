package com.hotdog.springboot.config.mybatis;

import com.github.pagehelper.PageHelper;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * Created by hotdog on 2017/3/29.
 * 初始化mybatis配置
 */
@Configuration
//指定扫描的mapper接口所在的包
@MapperScan("com.hotdog.springboot.mapper")
public class MybatisConfig {

    private static final String TYPE_ALIASES_PACKAGE = "com.hotdog.springboot.model";
    private static final String MAPPER_LOCATION = "classpath:/mybatis/*.xml";

    @Bean
    @Autowired
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
        final SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        //mybatis.typeAliasesPackage：指定domain类的基包，即指定其在*Mapper.xml文件中可以使用简名来代替全类名（看后边的UserMapper.xml介绍）
        sqlSessionFactoryBean.setTypeAliasesPackage(TYPE_ALIASES_PACKAGE);
        /* mybatis.mapperLocations：指定*Mapper.xml的位置
        如果不加会报org.apache.ibatis.binding.BindingException: Invalid bound statement (not found): com.blog.mapper.MessageMapper.findMessageInfo异常
        因为找不到*Mapper.xml，也就无法映射mapper中的接口方法。 */
        sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(MAPPER_LOCATION));

        // 分页插件
        PageHelper pageHelper = new PageHelper();
        Properties properties = new Properties();
        properties.setProperty("reasonable", "true");
        properties.setProperty("supportMethodsArguments", "true");
        properties.setProperty("returnPageInfo", "check");
        properties.setProperty("params", "count=countSql");
        pageHelper.setProperties(properties);
        //添加插件
        sqlSessionFactoryBean.setPlugins(new Interceptor[]{pageHelper});


        return sqlSessionFactoryBean.getObject();
    }
}
