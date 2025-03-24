package br.com.robertoxavier.data.repositories;

import br.com.robertoxavier.data.entities.ServidorEfetivoEntity;
import br.com.robertoxavier.data.entities.vo.ServidoresUnidadeVo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServidorEfetivoRepository extends JpaRepository<ServidorEfetivoEntity,Long> {
    @Query("select new br.com.robertoxavier.data.entities.vo.ServidoresUnidadeVo(se.pessoa.pesNome, u.unidNome) " +
            "from ServidorEfetivoEntity se " +
            "inner join LotacaoEntity l on l.pessoa.pesId = se.pessoa.pesId " +
            "inner join l.unidade u " +
            "where l.unidade.unidId = :unidId")
    List<ServidoresUnidadeVo> buscarServidoreLotadosUnidade(Long unidId);
}
