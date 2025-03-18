package br.com.robertoxavier.api.ports.endereco;

import br.com.robertoxavier.PageQuery;
import br.com.robertoxavier.PageResponse;
import br.com.robertoxavier.api.mappers.endereco.EnderecoMapper;
import br.com.robertoxavier.data.entities.CidadeEntity;
import br.com.robertoxavier.data.entities.EnderecoEntity;
import br.com.robertoxavier.data.repositories.EnderecoRepository;
import br.com.robertoxavier.model.CidadeModel;
import br.com.robertoxavier.model.EnderecoModel;
import br.com.robertoxavier.ports.endereco.EnderecoPort;
import br.com.robertoxavier.stories.cidade.CidadeUseStory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class EnderecoPortImp implements EnderecoPort {

    private final EnderecoRepository enderecoRepository;

    private final EnderecoMapper enderecoMapper;

    private CidadeUseStory cidadeUseStory;

    public EnderecoPortImp(EnderecoRepository enderecoRepository,
                           EnderecoMapper enderecoMapper,
                           CidadeUseStory cidadeUseStory) {
        this.enderecoRepository = enderecoRepository;
        this.enderecoMapper = enderecoMapper;
        this.cidadeUseStory = cidadeUseStory;
    }


    @Override
    public EnderecoModel criar(EnderecoModel enderecoModel) {
        System.out.println("entrou no portImp");
        System.out.println(enderecoModel.getCidade().getCidId());

        if(enderecoModel.getCidade().getCidId()== null){
            cidadeUseStory.criar(enderecoModel.getCidade());
        }else{
            cidadeUseStory.atualizar(enderecoModel.getCidade().getCidId()
                    ,enderecoModel.getCidade());
        }
        return enderecoMapper.enderecoEntityToModel(
                enderecoRepository.save(
                        enderecoMapper.enderecoModelToEntity(enderecoModel)
                )
        );
    }

    @Override
    public EnderecoModel buscarPorId(Long cidId) {
        return enderecoMapper
                .enderecoEntityToModel( enderecoRepository.findById(cidId)
                        .orElseThrow(() -> new RuntimeException("Endereco não encontrado")));
    }

    @Override
    public EnderecoModel atualizar(Long cidId, EnderecoModel EnderecoModel) {
        return null;
    }

    @Override
    public PageResponse<EnderecoModel> listaEnderecosPaginado(PageQuery pageQuery) {
        Page<EnderecoEntity> page = enderecoRepository.findAll(
                PageRequest.of(pageQuery.getPage(), pageQuery.getSizePage())
        );

        Page<EnderecoModel> enderecoModelPage = page.map(enderecoMapper::enderecoEntityToModel);

        return new PageResponse<>(
                enderecoModelPage.getNumber(),
                enderecoModelPage.getTotalPages(),
                enderecoModelPage.getTotalElements(),
                enderecoModelPage.getSize(),
                enderecoModelPage.getContent()
        );
    }
}
