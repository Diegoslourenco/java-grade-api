package comgft.starterapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import comgft.starterapi.model.Starter;
import comgft.starterapi.repository.query.StarterRepositoryQuery;

public interface StarterRepository extends JpaRepository<Starter, Long>, StarterRepositoryQuery {

}
