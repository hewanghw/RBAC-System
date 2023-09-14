//package com.hw.config.oauth2;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
//import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
//import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
//import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
//import org.springframework.security.oauth2.provider.code.AuthorizationCodeServices;
//import org.springframework.security.oauth2.provider.token.TokenStore;
//
//@EnableAuthorizationServer
//@Configuration
//public class OAuth2AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {
//
//    private final BCryptPasswordEncoder passwordEncoder;
//    private final AuthenticationManager authenticationManager;
//    private final TokenStore tokenStore;
//    private final AuthorizationCodeServices authorizationCodeServices;
//
//
//    public OAuth2AuthorizationServerConfig(BCryptPasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, TokenStore tokenStore, AuthorizationCodeServices authorizationCodeServices) {
//        this.passwordEncoder = passwordEncoder;
//        this.authenticationManager = authenticationManager;
//        this.tokenStore = tokenStore;
//        this.authorizationCodeServices = authorizationCodeServices;
//    }
//
//    /**
//     * 配置被允许访问此认证服务器的客户端详细信息
//     * 1.内存管理
//     * 2.数据库管理方式
//     * @param clients
//     * @throws Exception
//     */
//    @Override
//    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
//        clients.inMemory()
//                //客户端名称
//                .withClient("test-pc")
//                //客户端密码
//                .secret(passwordEncoder.encode("123456"))
//                //资源id,商品资源
//                .resourceIds("authority")
//                //授权类型, 可同时支持多种授权类型
//                .authorizedGrantTypes("authorization_code", "password", "implicit","client_credentials","refresh_token")
//                //授权范围标识，哪部分资源可访问（all是标识，不是代表所有）
//                .scopes("all")
//                // false 跳转到授权页面手动点击授权，true 不用手动授权，直接响应授权码
//                .autoApprove(false)
//                .redirectUris("http://www.baidu.com/");//客户端回调地址
//
//    }
//
//    @Override
//    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
//        endpoints.authenticationManager(authenticationManager)
//                .tokenStore(tokenStore)
//                .authorizationCodeServices(authorizationCodeServices);
//
//    }
//}
