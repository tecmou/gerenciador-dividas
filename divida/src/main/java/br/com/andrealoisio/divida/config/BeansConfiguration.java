package br.com.andrealoisio.divida.config;

import br.com.andrealoisio.divida.repository.DevedorRepository;
import br.com.andrealoisio.divida.repository.DevedorRepositoryImplForJpa;
import br.com.andrealoisio.divida.repository.DividaRepository;
import br.com.andrealoisio.divida.repository.DividaRepositoryImplForJpa;
import br.com.andrealoisio.divida.utils.PojoConverter;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

@Configuration
public class BeansConfiguration {

    @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource
                = new ReloadableResourceBundleMessageSource();

        messageSource.setBasename("classpath:messages");
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }

    @Bean
    public LocalValidatorFactoryBean getValidator() {
        LocalValidatorFactoryBean bean = new LocalValidatorFactoryBean();
        bean.setValidationMessageSource(messageSource());
        return bean;
    }

    @Bean
    public DividaRepository dividaRepository() {
        return new DividaRepositoryImplForJpa();
    }

    @Bean
    public DevedorRepository devedorRepository() {
        return new DevedorRepositoryImplForJpa();
    }

    @Bean
    public PojoConverter pojoConverter() {
        return new PojoConverter();
    }
}
