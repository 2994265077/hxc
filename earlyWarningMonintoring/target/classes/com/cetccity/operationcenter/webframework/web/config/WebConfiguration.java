/**
 * Copyright (C), 2019, 中电科新型智慧城市研究院有限公司
 * FileName: WebConfiguration
 * Author:   YHY
 * Date:     2019/4/22 16:54
 * Description:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.cetccity.operationcenter.webframework.web.config;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.ser.std.DateSerializer;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.BufferedImageHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.ResourceHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.converter.xml.MappingJackson2XmlHttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

/**
 * 〈一句话功能简述〉<br> 
 * 〈配置图片转换器〉
 *
 * @author yhy
 * @create 2019/4/22
 * @since 1.0.0
 */
@Configuration
public class WebConfiguration implements WebMvcConfigurer {

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(bufferedImageHttpMessageConverter());
        converters.add(resourceHttpMessageConverter());
        converters.add(mappingJackson2HttpMessageConverter());
        converters.add(mappingJackson2XmlHttpMessageConverter());
    }

    @Bean
    public BufferedImageHttpMessageConverter bufferedImageHttpMessageConverter() {
        BufferedImageHttpMessageConverter messageConverter = new BufferedImageHttpMessageConverter();
        messageConverter.setDefaultContentType(MediaType.IMAGE_JPEG);
        return messageConverter;
    }

    @Bean
    public ResourceHttpMessageConverter resourceHttpMessageConverter() {
        ResourceHttpMessageConverter resourceHttpMessageConverter = new ResourceHttpMessageConverter();
        resourceHttpMessageConverter.setDefaultCharset(Charset.forName("utf-8"));
        return resourceHttpMessageConverter;
    }

    @Bean
    public MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter() {
        MappingJackson2HttpMessageConverter messageConverter = new MappingJackson2HttpMessageConverter();
        messageConverter.setObjectMapper(customerObjectMapper());
        return messageConverter;
    }

    @Bean
    public MappingJackson2XmlHttpMessageConverter mappingJackson2XmlHttpMessageConverter() {
        MappingJackson2XmlHttpMessageConverter messageConverter = new MappingJackson2XmlHttpMessageConverter();
        ObjectMapper objectMapper = customerXmlMapper();
        messageConverter.setObjectMapper(objectMapper);
        return messageConverter;
    }

    @Bean("objectMapper")
    public ObjectMapper customerObjectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
//        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        configMapper(objectMapper);
        return  objectMapper;
    }
    @Bean("xmlMapper")
    public XmlMapper customerXmlMapper() {
        XmlMapper xmlMapper = new XmlMapper();
        // 不序列化空值
        xmlMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        configMapper(xmlMapper);
        return  xmlMapper;
    }

    private void configMapper(ObjectMapper objectMapper) {
        // 美化输出
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        // 反序列化时，数据中没有指定字段的值直接忽略
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        JavaTimeModule javaTimeModule = new JavaTimeModule();
        javaTimeModule.addSerializer(LocalDate.class, new LocalDateSerializer(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        javaTimeModule.addSerializer(LocalTime.class, new LocalTimeSerializer(DateTimeFormatter.ofPattern("HH:mm:ss")));
        javaTimeModule.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        javaTimeModule.addSerializer(Date.class, new DateSerializer(false, new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")));
        javaTimeModule.addDeserializer(LocalDate.class, new LocalDateDeserializer(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        javaTimeModule.addDeserializer(LocalTime.class, new LocalTimeDeserializer(DateTimeFormatter.ofPattern("HH:mm:ss")));
        javaTimeModule.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        objectMapper.registerModule(javaTimeModule);
    }

}