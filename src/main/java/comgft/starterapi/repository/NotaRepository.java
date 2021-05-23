package comgft.starterapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import comgft.starterapi.model.Nota;
import comgft.starterapi.repository.query.NotaRepositoryQuery;

public interface NotaRepository extends JpaRepository<Nota, Long>, NotaRepositoryQuery {

}
