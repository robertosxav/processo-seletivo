package br.com.robertoxavier.data.repositories;

import br.com.robertoxavier.data.entities.EnderecoEntity;
import br.com.robertoxavier.data.entities.UnidadeEnderecoEntity;
import br.com.robertoxavier.data.entities.UnidadeEnderecoId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface UnidadeEnderecoRepository extends JpaRepository<UnidadeEnderecoEntity, UnidadeEnderecoId> {

    @Query("select ue.endereco from UnidadeEnderecoEntity ue where ue.unidEndId.unidade = :unidId")
    Set<EnderecoEntity> listaENderecosUnidade(Long unidId);

    @Query("select ue from UnidadeEnderecoEntity ue " +
            "where ue.unidEndId.unidade = :unidId " +
            "and ue.unidEndId.endereco = :endId")
    Optional<UnidadeEnderecoEntity> findByUnidadeAndEndereco(Long unidId, Long endId);

    @Query("select ue from UnidadeEnderecoEntity ue " +
            "where ue.unidEndId.unidade = :unidId")
    Set<UnidadeEnderecoEntity> findByUnidadeId(Long unidId);
}
