package com.aws.testes.esteira;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import java.util.ArrayList;

import static org.springframework.web.reactive.function.BodyInserters.fromValue;

@ExtendWith(MockitoExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PessoaControllerTest {

    @InjectMocks
    private PessoaController pessoaController;

    private WebTestClient webTestClient;

    @BeforeEach
    void setUp() {
        webTestClient = WebTestClient.bindToController(pessoaController).build();

    }

    @Test
    void testCreatePessoa() {
        Pessoa pessoa = new Pessoa();
        pessoa.setNome("João");

        webTestClient.post()
                .uri("/pessoas/create")
                .contentType(MediaType.APPLICATION_JSON)
                .body(fromValue(pessoa))
                .exchange()
                .expectStatus().isOk()
                .expectBody(Pessoa.class)
                .value(response -> {
                    assert response.getNome().equals("João");
                    assert response.getTipoUsuario().equals("Desenvolvedor");
                });
    }


}

