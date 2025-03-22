package br.com.robertoxavier.api.ports.unidade;

import br.com.robertoxavier.PageQuery;
import br.com.robertoxavier.PageResponse;
import br.com.robertoxavier.api.mappers.endereco.EnderecoMapper;
import br.com.robertoxavier.api.mappers.unidade.UnidadeMapper;
import br.com.robertoxavier.data.entities.*;
import br.com.robertoxavier.data.repositories.EnderecoRepository;
import br.com.robertoxavier.data.repositories.LotacaoRepository;
import br.com.robertoxavier.data.repositories.UnidadeEnderecoRepository;
import br.com.robertoxavier.data.repositories.UnidadeRepository;
import br.com.robertoxavier.model.EnderecoModel;
import br.com.robertoxavier.model.UnidadeModel;
import br.com.robertoxavier.ports.unidade.UnidadePort;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Component
public class UnidadePortImp implements UnidadePort {

    private final UnidadeRepository unidadeRepository;

    private final UnidadeMapper unidadeMapper;

    private final EnderecoRepository enderecoRepository;

    private final EnderecoMapper enderecoMapper;

    private final UnidadeEnderecoRepository unidadeEnderecoRepository;

    private final LotacaoRepository lotacaoRepository;

    public UnidadePortImp(UnidadeRepository unidadeRepository,
                          UnidadeMapper unidadeMapper,
                          EnderecoRepository enderecoRepository,
                          EnderecoMapper enderecoMapper,
                          UnidadeEnderecoRepository unidadeEnderecoRepository, LotacaoRepository lotacaoRepository) {
        this.unidadeRepository = unidadeRepository;
        this.unidadeMapper = unidadeMapper;
        this.enderecoRepository = enderecoRepository;
        this.enderecoMapper = enderecoMapper;
        this.unidadeEnderecoRepository = unidadeEnderecoRepository;
        this.lotacaoRepository = lotacaoRepository;
    }

    @Override
    public UnidadeModel buscarPorId(Long endId) {
        UnidadeModel unidadeModel = unidadeMapper
                .unidadeEntityToModel( unidadeRepository.findById(endId)
                        .orElseThrow(() -> new RuntimeException("Unidade não encontrada")));
        Set<EnderecoEntity> enderecoEntityList = unidadeEnderecoRepository
                .listaENderecosUnidade(unidadeModel.getUnidId());
        unidadeModel.setEnderecoList(enderecoMapper.enderecoEntityListToEnderecoModelList(
                enderecoEntityList));
        return unidadeModel;
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
        Set<EnderecoEntity> enderecoEntityList = new HashSet<>();
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
            enderecoEntityList.add(unidadeEnderecoRepository.save(unidadeEnderecoEntity).getEndereco());
        });

        unidadeModelBanco.setEnderecoList(enderecoMapper
                .enderecoEntityListToEnderecoModelList(enderecoEntityList));

        return unidadeModelBanco;

    }

    @Override
    public UnidadeModel atualizar(Long unidId, UnidadeModel unidadeModel) {

        if (unidadeModel.getUnidSigla().isBlank() || unidadeModel.getUnidSigla().length() > 20) {
            throw new RuntimeException("Sigla da unidade não pode ser vazio e deve ter no máximo 20 caracteres");
        }

        if (unidadeModel.getUnidNome().isBlank() || unidadeModel.getUnidNome().length() > 200) {
            throw new RuntimeException("Nome da unidade não pode ser vazio e deve ter no máximo 200 caracteres");
        }

        UnidadeModel unidadeModelBanco = unidadeRepository.findById(unidId)
                .map(unidadeEntity -> unidadeMapper.unidadeEntityToModel(unidadeEntity))
                .orElseThrow(() -> new RuntimeException("Unidade não encontrada"));

        unidadeModelBanco.setUnidSigla(unidadeModel.getUnidSigla());
        unidadeModelBanco.setUnidNome(unidadeModel.getUnidNome());

        unidadeRepository.save(unidadeMapper.unidadeModelToEntity(unidadeModelBanco));

        Set<Long> enderecoIdsAtualizados = new HashSet<>(unidadeModel.getEnderecoIdList());


        Set<UnidadeEnderecoEntity> unidadeEnderecosExistentes = unidadeEnderecoRepository
                .findByUnidadeId(unidadeModelBanco.getUnidId());

        unidadeEnderecosExistentes.forEach(unidadeEnderecoEntity -> {
            Long enderecoId = unidadeEnderecoEntity.getEndereco().getEndId();
            if (!enderecoIdsAtualizados.contains(enderecoId)) {
                unidadeEnderecoRepository.delete(unidadeEnderecoEntity);
            }
        });

        Set<EnderecoEntity> enderecoEntityList = new HashSet<>();

        unidadeModel.getEnderecoIdList().forEach(e -> {

            EnderecoModel enderecoModelBanco = enderecoRepository.findById(e)
                    .map(enderecoEntity -> enderecoMapper.enderecoEntityToModel(enderecoEntity))
                    .orElseThrow(() -> new RuntimeException("Endereço não encontrado"));

            Optional<UnidadeEnderecoEntity> unidadeEnderecoExistente = unidadeEnderecoRepository.findByUnidadeAndEndereco(
                    unidadeModelBanco.getUnidId(), enderecoModelBanco.getEndId());

            if (unidadeEnderecoExistente.isPresent()) {
                UnidadeEnderecoEntity unidadeEnderecoEntity = unidadeEnderecoExistente.get();
                unidadeEnderecoEntity.setUnidade(unidadeMapper.unidadeModelToEntity(unidadeModelBanco));
                unidadeEnderecoEntity.setEndereco(enderecoMapper.enderecoModelToEntity(enderecoModelBanco));

                unidadeEnderecoRepository.save(unidadeEnderecoEntity);
            } else {
                UnidadeEnderecoId unidadeEnderecoId = new UnidadeEnderecoId();
                unidadeEnderecoId.setUnidade(unidadeModelBanco.getUnidId());
                unidadeEnderecoId.setEndereco(enderecoModelBanco.getEndId());

                UnidadeEnderecoEntity unidadeEnderecoEntity = new UnidadeEnderecoEntity();
                unidadeEnderecoEntity.setUnidEndId(unidadeEnderecoId);
                unidadeEnderecoEntity.setUnidade(unidadeMapper.unidadeModelToEntity(unidadeModelBanco));
                unidadeEnderecoEntity.setEndereco(enderecoMapper.enderecoModelToEntity(enderecoModelBanco));

                unidadeEnderecoRepository.save(unidadeEnderecoEntity);
            }

            enderecoEntityList.add(enderecoMapper.enderecoModelToEntity(enderecoModelBanco));
        });

        unidadeModelBanco.setEnderecoList(enderecoMapper.enderecoEntityListToEnderecoModelList(enderecoEntityList));

        return unidadeModelBanco;
    }

    @Override
    public PageResponse<UnidadeModel> listaUnidadesPaginado(PageQuery pageQuery) {
        Page<UnidadeEntity> page = unidadeRepository.findAll(
                PageRequest.of(pageQuery.getPage(), pageQuery.getSizePage())
        );
        page.getContent().forEach((u)->{
            Set<EnderecoEntity> enderecoEntityList = unidadeEnderecoRepository
                    .listaENderecosUnidade(u.getUnidId());
            u.setEnderecoList(
                    enderecoEntityList);
        });
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
    @Transactional
    public void excluir(Long unidId) {

        LotacaoEntity lotacaoEntity = lotacaoRepository.finByUnidadeUnidId(unidId);

        if(lotacaoEntity!= null){
            throw new RuntimeException("Não é possivel exluir a unidade pois a mesma possui lotaoes ligadas a ela");
        }
        UnidadeModel unidadeModelBanco = buscarPorId(unidId);
        Set<UnidadeEnderecoEntity> listaUnidadesEnderecos = unidadeEnderecoRepository
                .findByUnidadeId(unidId);

        listaUnidadesEnderecos.forEach(unidadeEnderecoRepository::delete);
        unidadeRepository.delete(unidadeMapper.unidadeModelToEntity(unidadeModelBanco));
    }

}
