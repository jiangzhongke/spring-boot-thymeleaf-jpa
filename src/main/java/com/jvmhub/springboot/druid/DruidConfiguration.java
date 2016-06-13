package com.jvmhub.springboot.druid;


import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.boot.context.embedded.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.sql.SQLException;

@Configuration
public class DruidConfiguration {
   
	 @Bean
	    public ServletRegistrationBean druidServlet() {
	        ServletRegistrationBean reg = new ServletRegistrationBean();
	        reg.setServlet(new StatViewServlet());
	        reg.addUrlMappings("/druid/*");
	        //reg.addInitParameter("allow", "127.0.0.1");
	        //reg.addInitParameter("deny","");
	        reg.addInitParameter("loginUsername", "admin");
	        reg.addInitParameter("loginPassword", "admin");
	        return reg;
	    }

	    @Bean
	    public FilterRegistrationBean filterRegistrationBean() {
	        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
	        filterRegistrationBean.setFilter(new WebStatFilter());
	        filterRegistrationBean.addUrlPatterns("/*");
	        filterRegistrationBean.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
	        return filterRegistrationBean;
	    }
	    

    @Bean
    public DataSource druidDataSource(@Value("${spring.datasource.driver-class-name}") String driver,
                                      @Value("${spring.datasource.url}") String url,
                                      @Value("${spring.datasource.username}") String username,
                                      @Value("${spring.datasource.password}") String password,
                                      @Value("${spring.datasource.initialsize}") int initialSize,
                                      @Value("${spring.datasource.max-wait}") int maxwait,
                                      @Value("${spring.datasource.max-active}") int maxactive,
                                      @Value("${spring.datasource.test-on-borrow}") boolean testonborrow
    									) {
        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setDriverClassName(driver);
        druidDataSource.setUrl(url);
        druidDataSource.setUsername(username);
        druidDataSource.setPassword(password);
        druidDataSource.setMaxWait(maxwait);
        druidDataSource.setMaxActive(maxactive);
        druidDataSource.setInitialSize(initialSize);
        druidDataSource.setTestOnBorrow(testonborrow);
        
        try {
            druidDataSource.setFilters("stat, wall");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return druidDataSource;
    }
}