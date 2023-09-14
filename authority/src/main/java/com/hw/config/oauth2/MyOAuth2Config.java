//package com.hw.config.oauth2;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.redis.connection.RedisConnectionFactory;
//import org.springframework.security.oauth2.provider.code.AuthorizationCodeServices;
//import org.springframework.security.oauth2.provider.code.JdbcAuthorizationCodeServices;
//import org.springframework.security.oauth2.provider.token.TokenStore;
//import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;
//
//import javax.sql.DataSource;
//
//@Configuration
//public class MyOAuth2Config {
//    private final RedisConnectionFactory redisConnectionFactory;
//    private final DataSource dataSource;
//
//    public MyOAuth2Config(RedisConnectionFactory redisConnectionFactory, DataSource dataSource) {
//        this.redisConnectionFactory = redisConnectionFactory;
//        this.dataSource = dataSource;
//    }
//
//    /**
//     * Redis令牌管理
//     */
//    @Bean
//    public TokenStore redisTokenStore(){
//        return  new RedisTokenStore(redisConnectionFactory);
//    }
//
//    /**
//     * jdbc管理授权码管理
//     */
//    @Bean
//    public AuthorizationCodeServices jdbcAuthorizationCodeServices(){
//        //使用JDBC方式保存授权码到 oauth_code中
//        return new JdbcAuthorizationCodeServices(dataSource);
//    }
//
//
//}
