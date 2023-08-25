package com.pixelthump.seshtypelib.repository;
import com.pixelthump.seshtypelib.repository.model.State;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;
import java.util.Optional;

@NoRepositoryBean
public interface StateRepository extends JpaRepository<State, String> {

    State findBySeshCode(String seshCode);

    boolean existsBySeshCode(String seshCode);

    Optional<State> findBySeshCodeAndActive(String seshCode, Boolean active);

    List<State> findByActive(Boolean active);

}
