package br.com.robertoxavier.data.repositories;

import br.com.robertoxavier.data.entities.EnderecoEntity;
import br.com.robertoxavier.model.EnderecoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface EnderecoRepository extends JpaRepository<EnderecoEntity,Long> {

    List<EnderecoEntity> findByCidadeCidId(Long cidId);
}
