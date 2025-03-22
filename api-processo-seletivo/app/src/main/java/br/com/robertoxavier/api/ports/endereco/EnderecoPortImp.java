package br.com.robertoxavier.api.ports.endereco;

import br.com.robertoxavier.PageQuery;
import br.com.robertoxavier.PageResponse;
import br.com.robertoxavier.api.mappers.endereco.EnderecoMapper;
import br.com.robertoxavier.data.entities.EnderecoEntity;
import br.com.robertoxavier.data.repositories.EnderecoRepository;
import br.com.robertoxavier.model.CidadeModel;
import br.com.robertoxavier.model.EnderecoModel;
import br.com.robertoxavier.ports.endereco.EnderecoPort;
import br.com.robertoxavier.stories.cidade.CidadeUseStory;
import jakarta.transaction.Transactional;
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


    @Transactional
    @Override
    public EnderecoModel criar(EnderecoModel enderecoModel) {

        if (enderecoModel.getCidade().getCidId() == null) {
            enderecoModel.setCidade(cidadeUseStory.criar(enderecoModel.getCidade()));
        } else {
            cidadeUseStory.atualizar(enderecoModel.getCidade().getCidId(), enderecoModel.getCidade());
        }

        return enderecoMapper.enderecoEntityToModel(
                enderecoRepository.saveAndFlush(
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
    public EnderecoModel atualizar(Long cidId, EnderecoModel enderecoModel) {
        EnderecoModel enderecoModelBanco = buscarPorId(cidId);

        CidadeModel cidadeModelBanco = null;

        if(enderecoModel.getCidade().getCidId()== null){
             cidadeModelBanco = cidadeUseStory.criar(enderecoModel.getCidade());
        }else{
            cidadeModelBanco= cidadeUseStory.atualizar(enderecoModel.getCidade().getCidId()
                    ,enderecoModel.getCidade());
        }

        enderecoModelBanco.setEndTipoLogradouro(enderecoModel.getEndTipoLogradouro());
        enderecoModelBanco.setEndLogradouro(enderecoModel.getEndLogradouro());
        enderecoModelBanco.setEndNumero(enderecoModel.getEndNumero());
        enderecoModelBanco.setEndBairro(enderecoModel.getEndBairro());
        enderecoModelBanco.setCidade(cidadeModelBanco);

        return enderecoMapper.enderecoEntityToModel(
                enderecoRepository.save(
                        enderecoMapper.enderecoModelToEntity(enderecoModelBanco)
                )
        );
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

    @Override
    public void excluir(Long endId) {
        try{
        EnderecoEntity enderecoEntity  = enderecoMapper
                .enderecoModelToEntity(buscarPorId(endId));
        enderecoRepository.delete(enderecoEntity);
        }catch (Exception e){
            throw new RuntimeException("Não foi possível excluir o endereco pois o mesmo está ligado a pessoas e/ou unidades");
        }
    }
}
