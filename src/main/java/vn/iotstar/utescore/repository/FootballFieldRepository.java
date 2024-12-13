package vn.iotstar.utescore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import vn.iotstar.utescore.entity.FootballField;

@Repository
public interface FootballFieldRepository extends JpaRepository<FootballField, Long> {

}
