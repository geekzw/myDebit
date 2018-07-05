package com.gzw.debit.core.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gzw.debit.common.utils.SpringContextUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.cache.interceptor.SimpleKeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.lang.reflect.Method;
import java.util.Map;

/**
 * auth:gujian
 * time:2018/7/5
 * email:gujian@maihaoche.com
 * describe:
 */
@Configuration
@EnableCaching
public class RedisTemplateConfig extends CachingConfigurerSupport {

    @Autowired
    private RedisConnectionFactory redisConnectionFactory;


    /**
     * 在使用@Cacheable时，如果不指定key，则使用找个默认的key生成器生成的key
     *
     * @return
     *
     * @author 单红宇(CSDN CATOOP)
     * @create 2017年3月11日
     */
    @Override
    public KeyGenerator keyGenerator() {
        return new SimpleKeyGenerator() {

            /**
             * 对参数进行拼接后MD5
             */
            @Override
            public Object generate(Object target, Method method, Object... params) {
                StringBuilder sb = new StringBuilder();
                sb.append(target.getClass().getName());
                sb.append(".").append(method.getName());

                StringBuilder paramsSb = new StringBuilder();
                for (Object param : params) {
                    // 如果不指定，默认生成包含到键值中
                    if (param != null) {
                        paramsSb.append(param.toString());
                    }
                }

                if (paramsSb.length() > 0) {
                    sb.append("_").append(paramsSb);
                }
                return sb.toString();
            }

        };

    }

    /**
     * redisTemplate 序列化使用的jdkSerializeable, 存储二进制字节码, 所以自定义序列化类
     * @return
     */
    @Bean
    public RedisTemplate<String, Object> redisTemplate() {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory);

        // 使用Jackson2JsonRedisSerialize 替换默认序列化
        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        objectMapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);

        jackson2JsonRedisSerializer.setObjectMapper(objectMapper);

        // 设置value的序列化规则和 key的序列化规则
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(jackson2JsonRedisSerializer);
        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }



//springboot2.0之前的配置
//    @Bean
//    @Override
//    public CacheManager cacheManager() {
//        RedisCacheManager redisCacheManager = new RedisCacheManager(redisTemplate());
//        //默认的过期时间,会被每个缓存名自己的过期时间覆盖
//        redisCacheManager.setDefaultExpiration(3600);
//        /**
//         * 启动时加载远程缓存; 不开启:每次第一次查询即使缓存中已经有旧的缓存,也不会读取到;
//         * 开启后如果缓存中已有缓存,第一次查询就会从缓存中读取
//         */
//        redisCacheManager.setLoadRemoteCachesOnStartup(true);
//        //开启后,key会携带上cacheName作为前缀
//        redisCacheManager.setUsePrefix(true);
//        /**
//         * 设置cacheNames,也可以在构造函数中设置,此处我使用在yml配置的cacheNames即可
//         * 需要注意的是,显而易见,此处的RedisCacheManager还未注入yml中的cacheNames;
//         * 所以如果使用redisCacheManager.getCacheNames()取出的将是空的;
//         * 但是,如果使用setExpires()方法,设置好对应的cacheName和过期时间,还是能够生效的
//         */
////        redisCacheManager.setCacheNames(Arrays.asList(cacheNames));
////        Collection<String> cacheNames = redisCacheManager.getCacheNames();
//
//        //使用自定义的属性类,根据yml配置,生成缓存名和过期时间对应的map
//        Map<String, Long> expires = customRedisCacheExpireProperties.generateExpireMap();
//        //设置每个缓存对应的过期时间
//        redisCacheManager.setExpires(expires);
//        //给缓存管理器设置上缓存名s
//        redisCacheManager.setCacheNames(customRedisCacheExpireProperties.getCacheNames());
//
//
//        return redisCacheManager;
//    }

    //缓存管理器
    @Bean
    public CacheManager cacheManager() {
        RedisCacheManager.RedisCacheManagerBuilder builder = RedisCacheManager
                .RedisCacheManagerBuilder
                .fromConnectionFactory(redisConnectionFactory);
        return builder.build();
    }


}
