package br.com.robertoxavier.api.mappers.pessoa;

import br.com.robertoxavier.data.entities.PessoaEntity;
import br.com.robertoxavier.dto.pessoa.PessoaRequest;
import br.com.robertoxavier.dto.pessoa.PessoaResponse;
import br.com.robertoxavier.model.PessoaModel;
import org.springframework.stereotype.Component;

@Component
public class PessoaMapper {

    public PessoaResponse pessoaModelToResponse(PessoaModel pessoaModel) {
        if (pessoaModel == null) {
            return null;
        }

        return new PessoaResponse(
                pessoaModel.getPesId(),
                pessoaModel.getPesNome(),
                pessoaModel.getPesDataNascimento(),
                pessoaModel.getPesSexo(),
                pessoaModel.getPesMae(),
                pessoaModel.getPesPai()
        );
    }

    public PessoaModel pessoaRequestToModel(PessoaRequest pessoaRequest) {
        if (pessoaRequest == null) {
            return null;
        }

        return new PessoaModel(
                pessoaRequest.pesNome(),
                pessoaRequest.pesDataNascimento(),
                pessoaRequest.pesSexo(),
                pessoaRequest.pesMae(),
                pessoaRequest.pesPai()
        );
    }

    public PessoaEntity pessoaModelToEntity(PessoaModel pessoaModel) {
        if(pessoaModel == null){
            return null;
        }
        return new PessoaEntity(
                pessoaModel.getPesNome(),
                pessoaModel.getPesDataNascimento(),
                pessoaModel.getPesSexo(),
                pessoaModel.getPesMae(),
                pessoaModel.getPesPai()
        );
    }

    public PessoaModel pessoaEntityToModel(PessoaEntity pessoaEntity) {
        if(pessoaEntity == null){
            return null;
        }
        return new PessoaModel(
                pessoaEntity.getPesNome(),
                pessoaEntity.getPesDataNascimento(),
                pessoaEntity.getPesSexo(),
                pessoaEntity.getPesMae(),
                pessoaEntity.getPesPai()
        );
    }

}
