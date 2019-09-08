package cn.ysy.oAuth2.server.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;

import javax.sql.DataSource;

@Configuration
@EnableAuthorizationServer//表明开启认证服务
public class AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter{

    @Bean
    @Primary//声明主配置为该数据源
    @ConfigurationProperties(prefix = "spring.datasource")//读取配置信息
    public DataSource dataSource(){
       return DataSourceBuilder.create().build();
    }

    @Bean
    public TokenStore tokenStore(){
       return new  JdbcTokenStore(dataSource());
    }

    @Bean
    public ClientDetailsService jdbcClientDeatails(){
        return new JdbcClientDetailsService(dataSource());
    }

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        //授权（基于授权码）
        //配置客户端
        clients.withClientDetails(jdbcClientDeatails());

        /*clients
                .inMemory()//客户端信息放置到内存中
                .withClient("client")//客户端id
                .secret(passwordEncoder.encode("secret"))//客户端安全码
                .authorizedGrantTypes("authorization_code")//授权模式，此处为授权码模式
                .scopes("app")//授权范围
                .redirectUris("https://www.baidu.com");//回调地址
                */
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.tokenStore(tokenStore());
    }
}
