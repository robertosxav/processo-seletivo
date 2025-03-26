package br.com.robertoxavier.data.repositories;

import br.com.robertoxavier.data.entities.ServidorEfetivoEntity;
import br.com.robertoxavier.data.entities.vo.ServidoresUnidadeVo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServidorEfetivoRepository extends JpaRepository<ServidorEfetivoEntity,Long> {
    @Query("select new br.com.robertoxavier.data.entities.vo.ServidoresUnidadeVo(se.pessoa.pesNome," +
            " u.unidNome, se.pessoa.pesDataNascimento,se.pessoa.pesId) " +
            "from ServidorEfetivoEntity se " +
            "inner join LotacaoEntity l on l.pessoa.pesId = se.pessoa.pesId " +
            "inner join l.unidade u on u.unidId = l.unidade.unidId " +
            "where u.unidId = :unidId")
    Page<ServidoresUnidadeVo> buscarServidoreLotadosUnidade(Long unidId, Pageable pageable);
}
