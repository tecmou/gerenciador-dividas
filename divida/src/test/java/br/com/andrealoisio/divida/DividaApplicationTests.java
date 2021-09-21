package br.com.andrealoisio.divida;

import br.com.andrealoisio.divida.model.Divida;
import br.com.andrealoisio.divida.repository.DividaRepository;
import org.apache.catalina.Store;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.core.AutoConfigureCache;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDate;

@SpringBootTest
class DividaApplicationTests {

	@Autowired
	private DividaRepository repository;

	@Test
	void contextLoads() {
		Divida divida = new Divida();
		divida.setValor(new BigDecimal(101));
		divida.setDataLimite(LocalDate.now().plusDays(1));

		repository.save(divida);
		Iterable<Divida> iterable = repository.findAll();
		iterable.forEach(i -> System.out.println(i.getUuid().toString()));
	}

}
