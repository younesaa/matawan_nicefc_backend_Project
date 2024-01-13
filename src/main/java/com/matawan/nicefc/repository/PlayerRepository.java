package com.matawan.nicefc.repository;

import com.matawan.nicefc.entity.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for managing Player entities in the data store.
 * Extends JpaRepository to inherit basic CRUD operations.
 */
@Repository
public interface PlayerRepository extends JpaRepository<Player,Long> {
}
