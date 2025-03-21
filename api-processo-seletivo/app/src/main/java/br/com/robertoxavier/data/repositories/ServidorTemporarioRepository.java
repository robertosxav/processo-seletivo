package br.com.robertoxavier.data.repositories;

import br.com.robertoxavier.data.entities.ServidorTemporarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServidorTemporarioRepository extends JpaRepository<ServidorTemporarioEntity,Long> {
}
