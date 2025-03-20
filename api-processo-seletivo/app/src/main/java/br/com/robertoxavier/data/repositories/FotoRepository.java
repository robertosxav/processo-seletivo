package br.com.robertoxavier.data.repositories;

import br.com.robertoxavier.data.entities.FotoEntity;
import br.com.robertoxavier.data.entities.PessoaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FotoRepository extends JpaRepository<FotoEntity,Long> {
}
