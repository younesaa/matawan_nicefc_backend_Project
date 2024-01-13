package com.matawan.nicefc.repository;

import com.matawan.nicefc.entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for managing Team entities in the data store.
 * Extends JpaRepository to inherit basic CRUD operations.
 */
@Repository
public interface TeamRepository extends JpaRepository<Team,Long> {

    /**
     * Checks whether a team with the specified name already exists in the data store.
     *
     * @param teamName The name of the team to check for existence.
     * @return {@code true} if a team with the specified name exists; otherwise, {@code false}.
     */
    public boolean existsByName(String teamName);

}
