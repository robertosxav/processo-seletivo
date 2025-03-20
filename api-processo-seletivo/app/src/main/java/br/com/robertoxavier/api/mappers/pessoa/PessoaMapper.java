package br.com.robertoxavier.api.mappers.pessoa;

import br.com.robertoxavier.api.mappers.cidade.CidadeMapper;
import br.com.robertoxavier.api.mappers.endereco.EnderecoMapper;
import br.com.robertoxavier.data.entities.EnderecoEntity;
import br.com.robertoxavier.data.entities.PessoaEntity;
import br.com.robertoxavier.data.entities.UnidadeEntity;
import br.com.robertoxavier.dto.endereco.EnderecoResponse;
import br.com.robertoxavier.dto.pessoa.PessoaRequest;
import br.com.robertoxavier.dto.pessoa.PessoaResponse;
import br.com.robertoxavier.dto.unidade.UnidadeResponse;
import br.com.robertoxavier.model.EnderecoModel;
import br.com.robertoxavier.model.PessoaModel;
import br.com.robertoxavier.model.UnidadeModel;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class PessoaMapper {

    private final EnderecoMapper enderecoMapper;
    private final CidadeMapper cidadeMapper;

    public PessoaMapper(EnderecoMapper enderecoMapper, CidadeMapper cidadeMapper) {
        this.enderecoMapper = enderecoMapper;
        this.cidadeMapper = cidadeMapper;
    }


    public PessoaResponse pessoaModelToResponse(PessoaModel pessoaModel) {
        if (pessoaModel == null) {
            return null;
        }

        Set<EnderecoResponse> enderecoResponseSet = new HashSet<>();


        for (EnderecoModel enderecoModel : pessoaModel.getEnderecoList()) {
            enderecoResponseSet.add(enderecoMapper.enderecoModelToResponse(enderecoModel));
        }

        return new PessoaResponse(
                pessoaModel.getPesId(),
                pessoaModel.getPesNome(),
                pessoaModel.getPesDataNascimento(),
                pessoaModel.getPesSexo(),
                pessoaModel.getPesMae(),
                pessoaModel.getPesPai(),
                enderecoResponseSet
        );
    }

    public PessoaModel pessoaRequestToModel(PessoaRequest pessoaRequest) {
        if (pessoaRequest == null) {
            return null;
        }

        return new PessoaModel(
                pessoaRequest.pesNome(),
                pessoaRequest.pesDataNascimento(),
                pessoaRequest.enderecoIdList(),
                pessoaRequest.pesSexo(),
                pessoaRequest.pesMae(),
                pessoaRequest.pesPai()
        );
    }

    public PessoaModel pessoaEntityToModel(PessoaEntity pessoaEntity) {
        if(pessoaEntity == null){
            return null;
        }

        Set<EnderecoModel> enderecoModelSet = new HashSet<>();

        if(pessoaEntity.getEnderecoList() != null){
            enderecoModelSet = pessoaEntity.getEnderecoList().stream()
                    .map(enderecoMapper::enderecoEntityToModel)
                    .collect(Collectors.toSet());

        }

        return new PessoaModel(
                pessoaEntity.getPesId(),
                pessoaEntity.getPesNome(),
                pessoaEntity.getPesDataNascimento(),
                enderecoModelSet,
                pessoaEntity.getPesSexo(),
                pessoaEntity.getPesMae(),
                pessoaEntity.getPesPai()

        );
    }

    public PessoaEntity pessoaModelToEntity(PessoaModel pessoaModel) {
        if(pessoaModel == null){
            return null;
        }
        Set<EnderecoEntity> enderecoEntitySet = new HashSet<>();

        if(pessoaModel.getEnderecoList() != null) {
            enderecoEntitySet = pessoaModel.getEnderecoList().stream()
                    .map(enderecoMapper::enderecoModelToEntity)
                    .collect(Collectors.toSet());
        }
        return new PessoaEntity(
                pessoaModel.getPesId(),
                pessoaModel.getPesNome(),
                pessoaModel.getPesDataNascimento(),
                pessoaModel.getPesSexo(),
                pessoaModel.getPesMae(),
                pessoaModel.getPesPai(),
                enderecoEntitySet
        );
    }

    private EnderecoModel enderecoEntityToModel(EnderecoEntity enderecoEntity) {
        if (enderecoEntity == null) {
            return null;
        }

        return new EnderecoModel(
                enderecoEntity.getEndId(),
                enderecoEntity.getEndTipoLogradouro(),
                enderecoEntity.getEndLogradouro(),
                enderecoEntity.getEndNumero(),
                enderecoEntity.getEndBairro(),
                cidadeMapper.cidadeEntityToModel(enderecoEntity.getCidade())
        );
    }

    public List<PessoaModel> pessoaEntityListToPessoaModelList(List<PessoaEntity>pessoaEntityList){
        if (pessoaEntityList == null) {
            return null;
        }

        return pessoaEntityList.stream()
                .map(this::pessoaEntityToModel)
                .collect(Collectors.toList());
    }

    public List<PessoaResponse> pessoaModelListToPessoaResponseList(List<PessoaModel>pessoaModelList){
        if (pessoaModelList == null) {
            return null;
        }

        return pessoaModelList.stream()
                .map(this::pessoaModelToResponse)
                .collect(Collectors.toList());
    }
}
