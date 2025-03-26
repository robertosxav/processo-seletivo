package br.com.robertoxavier.data.repositories;

import br.com.robertoxavier.data.entities.FotoEntity;
import br.com.robertoxavier.data.entities.PessoaEnderecoEntity;
import br.com.robertoxavier.data.entities.PessoaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface FotoRepository extends JpaRepository<FotoEntity,Long> {

    @Query("select f from FotoEntity f " +
            "where f.pessoa.pesId = :pesId")
    Set<FotoEntity> findByPessoaId(Long pesId);

    @Query("select f.fpBucket from FotoEntity f " +
            "where f.pessoa.pesId = :pesId")
    Set<String> listaBuckets(Long pesId);
}
