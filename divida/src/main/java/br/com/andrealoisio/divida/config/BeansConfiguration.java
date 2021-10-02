package br.com.andrealoisio.divida.config;

import br.com.andrealoisio.divida.repository.DevedorRepository;
import br.com.andrealoisio.divida.repository.DevedorRepositoryImplForJpa;
import br.com.andrealoisio.divida.repository.DividaRepository;
import br.com.andrealoisio.divida.repository.DividaRepositoryImplForJpa;
import br.com.andrealoisio.divida.utils.PojoConverter;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeansConfiguration {
    @Bean
    public DividaRepository dividaRepository() {
        return new DividaRepositoryImplForJpa();
    }

    @Bean
    public DevedorRepository devedorRepository() {
        return new DevedorRepositoryImplForJpa();
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    public PojoConverter pojoConverter() {
        return new PojoConverter();
    }
}
