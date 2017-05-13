package com.crmutil.config;

import com.crmutil.plugin.PagePlugin;
import com.crmutil.util.Page;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import javax.sql.DataSource;

/**
 * description: Mybatis配置
 *
 * @author: zmj
 * @create: 2017/5/7
 */
@Configuration
@EnableTransactionManagement
public class MybatisConfig {

    @Value("${spring.datasource.type}")
    private Class<? extends DataSource> dataSourceType;

    @Bean(name = "dataSource", destroyMethod = "close", initMethod = "init")
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource getDataSource() throws Exception {
        return DataSourceBuilder.create().type(dataSourceType).build();
    }

    @Bean
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(getDataSource());
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        Resource[] resources = resolver.getResources("classpath:mybatis/mapper/*.xml");

        Class<?>[] typeAliases = {Page.class};
        sqlSessionFactoryBean.setTypeAliases(typeAliases);

        sqlSessionFactoryBean.setTypeAliasesPackage("com.crmutil.entity");

        sqlSessionFactoryBean.setMapperLocations(resources);
        Interceptor[] interceptors = {new PagePlugin()};
        sqlSessionFactoryBean.setPlugins(interceptors);//分页插件

        return sqlSessionFactoryBean.getObject();
    }

    @Bean
    public SqlSessionTemplate sqlSessionTemplate() throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory());
    }

    @Bean
    public DataSourceTransactionManager dataSourceTransactionManager() throws Exception {
        return new DataSourceTransactionManager(getDataSource());
    }
}