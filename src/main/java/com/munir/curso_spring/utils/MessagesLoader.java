/*
 * package com.munir.curso_spring.utils;
 * 
 * import org.springframework.context.annotation.Configuration; import
 * org.springframework.context.annotation.PropertySource; import
 * org.springframework.core.env.Environment;
 * 
 * @Configuration
 * 
 * @PropertySource("messages.properties") public class MessagesLoader {
 * 
 * private static Environment env;
 * 
 * MessagesLoader(Environment env) {
 * 
 * MessagesLoader.env = env; }
 * 
 * public static String getMessage(String key) {
 * 
 * return env.getProperty(key); } }
 */