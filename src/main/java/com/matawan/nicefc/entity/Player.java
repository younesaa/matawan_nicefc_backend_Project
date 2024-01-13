package com.matawan.nicefc.entity;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

/**
 * The {@code Player} class represents a player entity in the system.
 * It is annotated with {@code @Entity} to indicate that instances of this class
 * are entities that can be persisted to a data store.

 * The class includes validation annotations from Jakarta Bean Validation API to enforce
 * constraints on the fields. The {@code @NotEmpty} and {@code @Size} annotations
 * ensure that the 'name' and 'position' fields meet specific criteria.
 */
@Entity
@Data
@NoArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Player {

    /**
     * The unique identifier for the player. It is annotated with {@code @Id} to
     * mark it as the primary key and {@code @GeneratedValue} to specify its
     * generation strategy.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The name of the player. It must not be blank and should be between 3 and 30 characters.
     */
    @NotEmpty(message = "name cannot be blank")
    @Size(min = 3, max = 30, message = "name size must be between 3 and 30 characters")
    private String name;

    /**
     * The position of the player. It must not be blank and should have 2 to 3 characters.
     */
    @NotEmpty(message = "position cannot be blank")
    @Size(min = 2, max = 3, message = "position size must have 2 to 3 characters")
    private String position;

    /**
     * The team of the player. It must not be empty. player must be assigned to a team
     */
    @NotNull(message = "player need to be assigned to a team")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id")
    private Team team;
}
