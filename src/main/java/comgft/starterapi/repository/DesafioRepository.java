package comgft.starterapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import comgft.starterapi.model.Desafio;
import comgft.starterapi.repository.query.DesafioRepositoryQuery;

public interface DesafioRepository extends JpaRepository<Desafio, Long>, DesafioRepositoryQuery {

}
