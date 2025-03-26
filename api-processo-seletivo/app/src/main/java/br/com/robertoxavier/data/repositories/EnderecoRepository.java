package br.com.robertoxavier.data.repositories;


import br.com.robertoxavier.data.entities.EnderecoEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface EnderecoRepository extends JpaRepository<EnderecoEntity,Long> {

    List<EnderecoEntity> findByCidadeCidId(Long cidId);

    @Query("select e from EnderecoEntity e " +
            "inner join UnidadeEnderecoEntity ue on ue.endereco.endId = e.endId " +
            "inner join LotacaoEntity l on l.unidade.unidId = ue.unidade.unidId " +
            "inner join PessoaEntity p on pesId = l.pessoa.pesId "+
            "where lower(p.pesNome) like lower(CONCAT('%', :nome, '%'))"

    )
    Page<EnderecoEntity> listaEnderecosFuncPorParteNome(String nome,Pageable pageable);
}
