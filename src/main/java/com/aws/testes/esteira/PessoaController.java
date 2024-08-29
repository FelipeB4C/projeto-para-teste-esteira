package com.aws.testes.esteira;

import jakarta.transaction.Transactional;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;

@RequestMapping("/pessoas")
@RestController
public class PessoaController {

    ArrayList listaPessoas = new ArrayList<>();

    @PostMapping("/create")
    public Mono<Pessoa> createPessoa(@RequestBody Pessoa pessoa){
        return Mono.just(pessoa)
                .doOnNext(p -> p.setTipoUsuario("Desenvolvedor"))
                .doOnNext(listaPessoas::add);
    }

    @GetMapping("/list")
    public Flux<ArrayList<Pessoa>> getPessoa(){
        return Flux.just(listaPessoas);
    }


}
