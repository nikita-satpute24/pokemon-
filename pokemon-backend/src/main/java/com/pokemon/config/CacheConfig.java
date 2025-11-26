package com.pokemon.config;

import java.util.concurrent.TimeUnit;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.github.benmanes.caffeine.cache.Caffeine;

@EnableCaching
@Configuration
public class CacheConfig 
{

    @Bean
    public Caffeine<Object, Object> caffeineConfig() {
        return Caffeine.newBuilder()
                .expireAfterWrite(10, TimeUnit.MINUTES)   
                .maximumSize(100);                        
    }
    
	@Bean
	public CacheManager cacheManger(Caffeine<Object,Object> caffeine)
	{
		CaffeineCacheManager cacheManager = new CaffeineCacheManager("pokemonCache");
		cacheManager.setCaffeine(caffeine);
		return cacheManager;
	}
}
