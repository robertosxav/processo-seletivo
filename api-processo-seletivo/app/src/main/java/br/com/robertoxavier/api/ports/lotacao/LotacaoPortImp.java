package br.com.robertoxavier.api.ports.lotacao;

import br.com.robertoxavier.PageQuery;
import br.com.robertoxavier.PageResponse;
import br.com.robertoxavier.api.config.NotFoundException;
import br.com.robertoxavier.api.mappers.lotacao.LotacaoMapper;
import br.com.robertoxavier.api.mappers.pessoa.PessoaMapper;
import br.com.robertoxavier.data.entities.LotacaoEntity;
import br.com.robertoxavier.data.repositories.LotacaoRepository;
import br.com.robertoxavier.data.repositories.PessoaRepository;
import br.com.robertoxavier.model.LotacaoModel;
import br.com.robertoxavier.model.PessoaModel;
import br.com.robertoxavier.model.UnidadeModel;
import br.com.robertoxavier.ports.lotacao.LotacaoPort;
import br.com.robertoxavier.stories.unidade.UnidadeUseStory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

@Component
public class LotacaoPortImp implements LotacaoPort {

    private final LotacaoRepository lotacaoRepository;

    private final LotacaoMapper lotacaoMapper;

    private final UnidadeUseStory unidadeUseStory;

    private final PessoaRepository pessoaRepository;

    private final PessoaMapper pessoaMapper;


    public LotacaoPortImp(LotacaoRepository lotacaoRepository, LotacaoMapper lotacaoMapper, UnidadeUseStory unidadeUseStory, PessoaRepository pessoaRepository, PessoaMapper pessoaMapper) {
        this.lotacaoRepository = lotacaoRepository;
        this.lotacaoMapper = lotacaoMapper;
        this.unidadeUseStory = unidadeUseStory;
        this.pessoaRepository = pessoaRepository;
        this.pessoaMapper = pessoaMapper;
    }

    @Override
    public LotacaoModel buscarPorId(Long cidId) {

        return lotacaoMapper
                .lotacaoEntityToModel( lotacaoRepository.findById(cidId)
                        .orElseThrow(() -> new NotFoundException("Lotacao não encontrada")));
    }

    @Override
    public LotacaoModel criar(LotacaoModel lotacaoModel) {

        regrasNegocio(lotacaoModel);

        PessoaModel pessoaModelBd = pessoaMapper.pessoaEntityToModel(pessoaRepository.findById(lotacaoModel.getPesId())
                .orElseThrow(() -> new NotFoundException("Pessoa não encontrada")));

        UnidadeModel unidadeModel = unidadeUseStory
                .buscarPorId(lotacaoModel.getUnidId());
        lotacaoModel.setPessoaModel(pessoaModelBd);

        lotacaoModel.setUnidadeModel(unidadeModel);

        return lotacaoMapper.lotacaoEntityToModel(
                lotacaoRepository.save(
                        lotacaoMapper.lotacaoModelToEntity(lotacaoModel)
                )
        );
    }

    @Override
    public LotacaoModel atualizar(Long lotId, LotacaoModel lotacaoModel) {

        regrasNegocio(lotacaoModel);

        LotacaoModel lotacaoModelBanco = buscarPorId(lotId);

        lotacaoModelBanco.setLotDataLotacao(lotacaoModel.getLotDataLotacao());
        lotacaoModelBanco.setLotDataRemocao(lotacaoModel.getLotDataRemocao());
        lotacaoModelBanco.setLotPortaria(lotacaoModel.getLotPortaria());

        PessoaModel pessoaModelBanco = pessoaMapper.pessoaEntityToModel(pessoaRepository.findById(lotacaoModel.getPesId())
                .orElseThrow(() -> new NotFoundException("pessoa não encontrada")));

        UnidadeModel unidadeModelBanco = unidadeUseStory
                .buscarPorId(lotacaoModel.getUnidId());
        lotacaoModelBanco.setPessoaModel(pessoaModelBanco);
        lotacaoModelBanco.setUnidadeModel(unidadeModelBanco);

        return lotacaoMapper.lotacaoEntityToModel(
                lotacaoRepository.save(
                        lotacaoMapper.lotacaoModelToEntity(lotacaoModelBanco)
                )
        );
    }

    private void regrasNegocio(LotacaoModel lotacaoModel) {
        if(lotacaoModel.getLotDataLotacao() == null){
            throw new RuntimeException("É obrigatório informar a data de Lotação");
        }

        if(lotacaoModel.getLotPortaria().isBlank()){
            throw new RuntimeException("É obrigatório informar a Portaria");
        }

        if(lotacaoModel.getLotPortaria().length() > 100){
            throw new RuntimeException("Portaria deve ter no maximo 100 caracteres");
        }

        if(lotacaoModel.getPesId() == null){
            throw new RuntimeException("É obrigatório informar o id da pessoa");
        }

        if(lotacaoModel.getUnidId() == null){
            throw new RuntimeException("É obrigatório informar o id da unidade");
        }

    }

    @Override
    public PageResponse<LotacaoModel> listaLotacoesPaginado(PageQuery pageQuery) {
        Page<LotacaoEntity> page = lotacaoRepository.findAll(
                PageRequest.of(pageQuery.getPage(), pageQuery.getSizePage())
        );

        Page<LotacaoModel> lotacaoModelPage = page.map(lotacaoMapper::lotacaoEntityToModel);

        return new PageResponse<>(
                lotacaoModelPage.getNumber(),
                lotacaoModelPage.getTotalPages(),
                lotacaoModelPage.getTotalElements(),
                lotacaoModelPage.getSize(),
                lotacaoModelPage.getContent()
        );
    }

    @Override
    public void excluir(Long cidId) {
        LotacaoModel lotacaoModelBanco = buscarPorId(cidId);
        lotacaoRepository.delete(lotacaoMapper.lotacaoModelToEntity(lotacaoModelBanco));
    }

}
