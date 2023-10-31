package br.com.felipearruda.vendas;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;

@Dev
public class MinhaConfiguration {

    @Bean(name = "applicationName")
    public String applicationName() {
        return "Sistema de vendas";
    }

    @Bean
    public CommandLineRunner commandLineRunner() {
        return args -> {
            System.out.println("RODANDO CONFIGURAÇÕES NO AMBIENTE DE DESENVOLVIMENTO");
        };
    }

}
