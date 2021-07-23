package com.example.root.config;

import com.example.root.bean.Pet;
import com.example.root.converter.NickMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.format.FormatterRegistrar;
import org.springframework.format.FormatterRegistry;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.util.StringUtils;
import org.springframework.web.accept.HeaderContentNegotiationStrategy;
import org.springframework.web.accept.ParameterContentNegotiationStrategy;
import org.springframework.web.filter.HiddenHttpMethodFilter;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.util.UrlPathHelper;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author Nick
 * @Classname WebConfig
 * @Date 2021/7/19 16:19
 * @Description
 */
@Configuration(proxyBeanMethods = false)    //lite model
public class WebConfig {


    @Bean
    public WebMvcConfigurer webMvcConfigurer(){
        return new WebMvcConfigurer() {

            /**
             * 自定义内容协商策略-添加参数请求
             * @param configurer
             */
            @Override
            public void configureContentNegotiation(ContentNegotiationConfigurer configurer){

                //指定支持解析哪些参数对应的哪些媒体类型
                Map<String, MediaType> mediaTypes = new HashMap<>();
                mediaTypes.put("json",MediaType.APPLICATION_JSON);
                mediaTypes.put("xml",MediaType.APPLICATION_XML);
                mediaTypes.put("nick",MediaType.parseMediaType("application/nick"));
                //参数类型Map<String, MediaType> mediaTypes
                //基于参数类型的内容协商策略
                ParameterContentNegotiationStrategy parameterStrategy = new ParameterContentNegotiationStrategy(mediaTypes);
                //基于请求头的内容协商策略
                HeaderContentNegotiationStrategy headerStrategy = new HeaderContentNegotiationStrategy();
                configurer.strategies(Arrays.asList(parameterStrategy,headerStrategy));
            }
            //添加自定义messageConverter
            @Override
            public void extendMessageConverters(List<HttpMessageConverter<?>> converters){
                //添加进去
                converters.add(new NickMessageConverter());
            }
            //WebMvcConfigurer定制化SpringMVC的功能,开启矩阵变量的功能MatrixVariable矩阵变量
            @Override
            public void configurePathMatch(PathMatchConfigurer configurer) {
                UrlPathHelper urlPathHelper = new UrlPathHelper();
                // 表示不移除；后面的内容。矩阵变量功能就可以生效
                urlPathHelper.setRemoveSemicolonContent(false);
                configurer.setUrlPathHelper(urlPathHelper);
            }

            @Override
            public void addFormatters(FormatterRegistry registry){
                registry.addConverter(new Converter<String, Pet>(){

                    @Override
                    public Pet convert(String source) {
                        // 啊猫,3
                        if (StringUtils.hasLength(source)){
                            Pet pet = new Pet();
                            String[] split = source.split(",");
                            pet.setName((split[0]));
                            pet.setAge(Integer.parseInt(split[1]));
                            return pet;
                        }
                        return null;
                    }
                });
            }
        };
    }
}
