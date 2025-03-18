package br.com.robertoxavier.data.repositories;

import br.com.robertoxavier.data.entities.UnidadeEnderecoEntity;
import br.com.robertoxavier.data.entities.UnidadeEnderecoId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UnidadeEnderecoRepository extends JpaRepository<UnidadeEnderecoEntity, UnidadeEnderecoId> {
}
