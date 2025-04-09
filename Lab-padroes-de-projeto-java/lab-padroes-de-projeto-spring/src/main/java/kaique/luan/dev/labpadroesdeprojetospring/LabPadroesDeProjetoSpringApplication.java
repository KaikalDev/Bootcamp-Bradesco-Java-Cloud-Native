package kaique.luan.dev.labpadroesdeprojetospring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * Projeto Spring Boot gerado via Spring Initializer.
 * Os seguintes módulos foram selecionados:
 * - Spring Data JPA
 * - Spring Web
 * - H2 Database
 * - OpenFeign
 *
 * @author KaikalDev
 */

@EnableFeignClients
@SpringBootApplication
public class LabPadroesDeProjetoSpringApplication {

    public static void main(String[] args) {
        SpringApplication.run(LabPadroesDeProjetoSpringApplication.class, args);
    }

}
