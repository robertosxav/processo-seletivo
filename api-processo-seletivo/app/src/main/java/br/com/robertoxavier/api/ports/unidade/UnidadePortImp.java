package br.com.robertoxavier.api.ports.unidade;

import br.com.robertoxavier.PageQuery;
import br.com.robertoxavier.PageResponse;
import br.com.robertoxavier.api.mappers.endereco.EnderecoMapper;
import br.com.robertoxavier.api.mappers.unidade.UnidadeMapper;
import br.com.robertoxavier.data.entities.EnderecoEntity;
import br.com.robertoxavier.data.entities.UnidadeEnderecoEntity;
import br.com.robertoxavier.data.entities.UnidadeEnderecoId;
import br.com.robertoxavier.data.entities.UnidadeEntity;
import br.com.robertoxavier.data.repositories.EnderecoRepository;
import br.com.robertoxavier.data.repositories.UnidadeEnderecoRepository;
import br.com.robertoxavier.data.repositories.UnidadeRepository;
import br.com.robertoxavier.model.EnderecoModel;
import br.com.robertoxavier.model.UnidadeModel;
import br.com.robertoxavier.ports.unidade.UnidadePort;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

@Component
public class UnidadePortImp implements UnidadePort {

    private final UnidadeRepository unidadeRepository;

    private final UnidadeMapper unidadeMapper;

    private final EnderecoRepository enderecoRepository;

    private final EnderecoMapper enderecoMapper;

    private final UnidadeEnderecoRepository unidadeEnderecoRepository;

    public UnidadePortImp(UnidadeRepository unidadeRepository,
                          UnidadeMapper unidadeMapper,
                          EnderecoRepository enderecoRepository,
                          EnderecoMapper enderecoMapper,
                          UnidadeEnderecoRepository unidadeEnderecoRepository) {
        this.unidadeRepository = unidadeRepository;
        this.unidadeMapper = unidadeMapper;
        this.enderecoRepository = enderecoRepository;
        this.enderecoMapper = enderecoMapper;
        this.unidadeEnderecoRepository = unidadeEnderecoRepository;
    }

    @Override
    public UnidadeModel buscarPorId(Long cidId) {

        return unidadeMapper
                .unidadeEntityToModel( unidadeRepository.findById(cidId)
                        .orElseThrow(() -> new RuntimeException("Unidade não encontrada")));
    }

    @Override
    public UnidadeModel criar(UnidadeModel unidadeModel) {

        if(unidadeModel.getUnidSigla().isBlank() || unidadeModel.getUnidSigla().length()>20){
            throw new RuntimeException("Sigla da unidade não pode ser vazio e deve ter no máximo 20 caracteres");
        }

        if(unidadeModel.getUnidNome().isBlank() || unidadeModel.getUnidNome().length() >200){
            throw new RuntimeException("Nome da unidade não pode ser vazio e deve ter no máximo 200 caracteres");
        }

        UnidadeModel unidadeModelBanco =  unidadeMapper.unidadeEntityToModel(
                unidadeRepository.save(
                        unidadeMapper.unidadeModelToEntity(unidadeModel)
                )
        );

        unidadeModel.getEnderecoIdList().forEach(e->{
            EnderecoModel enderecoModelBanco= enderecoMapper
                    .enderecoEntityToModel(enderecoRepository.findById(e)
                    .orElseThrow(() -> new RuntimeException("Endereco não encontrado")));

            UnidadeEnderecoId unidadeEnderecoId = new UnidadeEnderecoId();
            unidadeEnderecoId.setUnidade(unidadeModelBanco.getUnidId());
            unidadeEnderecoId.setEndereco(enderecoModelBanco.getEndId());

            UnidadeEnderecoEntity unidadeEnderecoEntity = new UnidadeEnderecoEntity();
            unidadeEnderecoEntity.setUnidEndId(unidadeEnderecoId);
            unidadeEnderecoEntity.setUnidade(unidadeMapper.unidadeModelToEntity(unidadeModelBanco));
            unidadeEnderecoEntity.setEndereco(enderecoMapper.enderecoModelToEntity(enderecoModelBanco));

            unidadeEnderecoRepository.save(unidadeEnderecoEntity);
        });
        return null;

    }

    @Override
    public UnidadeModel atualizar(Long cidId, UnidadeModel unidadeModel) {

        if(unidadeModel.getUnidSigla().isBlank() || unidadeModel.getUnidSigla().length()>20){
            throw new RuntimeException("Sigla da unidade não pode ser vazio e deve ter no máximo 20 caracteres");
        }

        if(unidadeModel.getUnidNome().isBlank() || unidadeModel.getUnidNome().length() >200){
            throw new RuntimeException("Nome da unidade não pode ser vazio e deve ter no máximo 200 caracteres");
        }

        UnidadeModel unidadeModelBanco = buscarPorId(cidId);

        unidadeModelBanco.setUnidNome(unidadeModel.getUnidNome());
        unidadeModelBanco.setUnidSigla(unidadeModel.getUnidSigla());

        return unidadeMapper.unidadeEntityToModel(
                unidadeRepository.save(
                        unidadeMapper.unidadeModelToEntity(unidadeModelBanco)
                )
        );
    }

    @Override
    public PageResponse<UnidadeModel> listaUnidadesPaginado(PageQuery pageQuery) {
        Page<UnidadeEntity> page = unidadeRepository.findAll(
                PageRequest.of(pageQuery.getPage(), pageQuery.getSizePage())
        );

        Page<UnidadeModel> unidadeModelPage = page.map(unidadeMapper::unidadeEntityToModel);

        return new PageResponse<>(
                unidadeModelPage.getNumber(),
                unidadeModelPage.getTotalPages(),
                unidadeModelPage.getTotalElements(),
                unidadeModelPage.getSize(),
                unidadeModelPage.getContent()
        );
    }

    @Override
    public void excluir(Long cidId) {
        UnidadeModel unidadeModelBanco = buscarPorId(cidId);
        unidadeRepository.delete(unidadeMapper.unidadeModelToEntity(unidadeModelBanco));
    }

}
