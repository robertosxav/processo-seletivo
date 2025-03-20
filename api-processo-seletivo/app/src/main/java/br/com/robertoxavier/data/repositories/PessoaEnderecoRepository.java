package br.com.robertoxavier.data.repositories;

import br.com.robertoxavier.data.entities.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface PessoaEnderecoRepository extends JpaRepository<PessoaEnderecoEntity, PessoaEnderecoId> {

    @Query("select pe.endereco from PessoaEnderecoEntity pe where pe.pesEndId.pessoa = :pesId")
    Set<EnderecoEntity> listaEnderecosPessoa(Long pesId);

    @Query("select pe from PessoaEnderecoEntity pe " +
            "where pe.pesEndId.pessoa = :pesId " +
            "and pe.pesEndId.endereco = :endId")
    Optional<UnidadeEnderecoEntity> findByPessoaAndEndereco(Long pesId, Long endId);

    @Query("select pe from PessoaEnderecoEntity pe " +
            "where pe.pesEndId.pessoa = :pesId")
    Set<UnidadeEnderecoEntity> findByPessoaId(Long pesId);
}
