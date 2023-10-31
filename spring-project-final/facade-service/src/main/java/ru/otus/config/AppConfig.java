package ru.otus.config;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
//import org.springframework.security.access.expression.method.DefaultMethodSecurityExpressionHandler;
//import org.springframework.security.access.expression.method.MethodSecurityExpressionHandler;
//import org.springframework.security.acls.AclPermissionEvaluator;
//import org.springframework.security.acls.domain.*;
//import org.springframework.security.acls.jdbc.BasicLookupStrategy;
//import org.springframework.security.acls.jdbc.JdbcMutableAclService;
//import org.springframework.security.acls.jdbc.LookupStrategy;
//import org.springframework.security.acls.model.PermissionGrantingStrategy;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.data.repository.query.SecurityEvaluationContextExtension;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.password.NoOpPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.provisioning.InMemoryUserDetailsManager;
//import ru.otus.repositories.LibraryUserAuthorityRepository;


@Configuration
@RequiredArgsConstructor
public class AppConfig {

    private static final Logger LOGGER = LoggerFactory.getLogger(AppConfig.class);


//    private final LibraryUserAuthorityRepository libraryUserAuthorityRepository;
//    private final DataSource dataSource;
//    private final LibraryUserRepository libraryUserRepository;
//
//
//    @Bean
//    public JdbcMutableAclService aclService() {
//        return new JdbcMutableAclService(dataSource, lookupStrategy(), aclCache());
//    }
//
//    @Bean
//    public PermissionGrantingStrategy permissionGrantingStrategy() {
//        return new DefaultPermissionGrantingStrategy(new ConsoleAuditLogger());
//    }
//
//    @Bean
//    public EhCacheBasedAclCache aclCache() {
//        return new EhCacheBasedAclCache(aclEhCacheFactoryBean().getObject(), permissionGrantingStrategy(), aclAuthorizationStrategy());
//    }
//
//    @Bean
//    public EhCacheFactoryBean aclEhCacheFactoryBean() {
//        EhCacheFactoryBean ehCacheFactoryBean = new EhCacheFactoryBean();
//        ehCacheFactoryBean.setCacheManager(aclCacheManager().getObject());
//        ehCacheFactoryBean.setCacheName("aclCache");
//        return ehCacheFactoryBean;
//    }
//
//    @Bean
//    public EhCacheManagerFactoryBean aclCacheManager() {
//        var cacheManagerFactoryBean = new EhCacheManagerFactoryBean();
//        cacheManagerFactoryBean.setShared(true);
//        return cacheManagerFactoryBean;
//    }
//
//    @Bean
//    @DependsOn("libraryUserAuthorityRepository")
//    public AclAuthorizationStrategy aclAuthorizationStrategy() {
//        SimpleGrantedAuthority[] authorities = libraryUserAuthorityRepository.findAll()
//                .stream()
//                .map(a -> new SimpleGrantedAuthority(a.getAuthority()))
//                .collect(Collectors.toList()).stream()
//                .toArray(SimpleGrantedAuthority[]::new);
//        var testLibraryAuthority = libraryUserAuthorityRepository.findAll();
//        LOGGER.info("Size from Repository " + testLibraryAuthority.size());
//        Arrays.stream(authorities).forEach(a -> LOGGER.info("Authority " + a.getAuthority()));
//        LOGGER.info("Authorities size " + authorities.length);
//        return new AclAuthorizationStrategyImpl(authorities);
//
//    }
//
//    @Bean
//    public MethodSecurityExpressionHandler defaultMethodSecurityExpressionHandler() {
//        DefaultMethodSecurityExpressionHandler expressionHandler = new DefaultMethodSecurityExpressionHandler();
//        AclPermissionEvaluator permissionEvaluator = new AclPermissionEvaluator(aclService());
//        expressionHandler.setPermissionEvaluator(permissionEvaluator);
//        return expressionHandler;
//    }
//
//    @Bean
//    public LookupStrategy lookupStrategy() {
//        return new BasicLookupStrategy(dataSource, aclCache(), aclAuthorizationStrategy(), new ConsoleAuditLogger());
//    }
//
//    @Bean
//    public SecurityEvaluationContextExtension securityEvaluationContextExtension() {
//        return new SecurityEvaluationContextExtension();
//    }



}
