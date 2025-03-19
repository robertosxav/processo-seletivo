package br.com.robertoxavier.api.config;

import br.com.robertoxavier.data.repositories.ServidorEfetivoRepository;
import br.com.robertoxavier.ports.cidade.CidadePort;
import br.com.robertoxavier.ports.endereco.EnderecoPort;
import br.com.robertoxavier.ports.lotacao.LotacaoPort;
import br.com.robertoxavier.ports.servidor.ServidorEfetivoPort;
import br.com.robertoxavier.ports.unidade.UnidadePort;
import br.com.robertoxavier.stories.cidade.CidadeUseStory;
import br.com.robertoxavier.stories.endereco.EnderecoUseStory;
import br.com.robertoxavier.stories.lotacao.LotacaoUseStory;
import br.com.robertoxavier.stories.servidor.ServidorEfetivoUseStory;
import br.com.robertoxavier.stories.unidade.UnidadeUseStory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    @Bean
    public CidadeUseStory cidadeUseStory(CidadePort cidadePort) {
        return new CidadeUseStory (cidadePort);
    }

    @Bean
    public EnderecoUseStory enderecoUseStory(EnderecoPort enderecoPort) {
        return new EnderecoUseStory (enderecoPort);
    }

    @Bean
    public UnidadeUseStory unidadeUseStory(UnidadePort unidadePort) {
        return new UnidadeUseStory (unidadePort);
    }

    @Bean
    public LotacaoUseStory lotacaoUseStory(LotacaoPort lotacaoPort) {
        return new LotacaoUseStory (lotacaoPort);
    }

    @Bean
    public ServidorEfetivoUseStory servidorEfetivoUseStory(ServidorEfetivoPort servidorEfetivoPort) {
        return new ServidorEfetivoUseStory (servidorEfetivoPort) ;
    }
}
