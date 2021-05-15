package comgft.starterapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import comgft.starterapi.model.Starter;

public interface StarterRepository extends JpaRepository<Starter, Long> {

}
