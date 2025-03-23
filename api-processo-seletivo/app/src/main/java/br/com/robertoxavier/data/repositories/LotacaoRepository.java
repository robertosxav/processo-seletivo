package br.com.robertoxavier.data.repositories;

import br.com.robertoxavier.data.entities.LotacaoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LotacaoRepository extends JpaRepository<LotacaoEntity,Long> {

    @Query("SELECT l FROM LotacaoEntity l WHERE l.unidade.unidId = :unidId")
    LotacaoEntity finByUnidadeUnidId(Long unidId);

    @Query("SELECT l FROM LotacaoEntity l WHERE l.pessoa.pesId = :pesId")
    LotacaoEntity finByPessoaPesId(Long pesId);
}
