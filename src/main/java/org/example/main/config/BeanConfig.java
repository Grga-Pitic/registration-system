package org.example.main.config;

import org.example.main.service.PasswordEditor;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.beans.PropertyEditor;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

@Configuration
public class BeanConfig {

    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DateFormat getDateFormatter() {
        return new SimpleDateFormat("yyyy-MM-dd");
    }

    @Bean(name = "passwordEditorBean")
    public PropertyEditor getPasswordEditor() {
        return new PasswordEditor(getPasswordEncoder());
    }

    @Bean(name = "dateEditorBean")
    public PropertyEditor getDateEditor() {
        return new CustomDateEditor(getDateFormatter(), false);
    }

}
