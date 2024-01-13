package com.matawan.nicefc.entity;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.codehaus.jackson.annotate.JsonIgnore;

import java.math.BigDecimal;
import java.util.List;
/**
 * The {@code Team} class represents a Team entity in the system.
 * It is annotated with {@code @Entity} to indicate that instances of this class
 * are entities that can be persisted to a data store.

 * The class includes validation annotations from Jakarta Bean Validation API to enforce
 * constraints on the fields. The {@code @NotNull},{@code @NotEmpty} and {@code @Size} annotations
 * ensure that the 'budget', 'name' and 'acronym' fields meet specific criteria.
 */
@Entity
@Data
@NoArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Team {

    /**
     * The unique identifier for the Team. It is annotated with {@code @Id} to
     * mark it as the primary key and {@code @GeneratedValue} to specify its
     * generation strategy.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The name of the Team. It must not be blank and should be between 3 and 30 characters.
     */
    @NotEmpty(message = "name cannot be blank")
    @Size(min = 3, max = 30, message = "name size must be between 3 and 30 characters")
    private String name;

    /**
     * The Acronym of the Team. It must not be blank and should have 3 to 6 characters.
     */
    @NotEmpty(message = "acronym cannot be blank")
    @Size(min = 3, max = 6, message = "acronym size must be between 3 and 6 characters")
    private String acronym;

    /**
     * The player List of the Team. It can be empty
     */
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "team")
    private List<Player> players;

    /**
     * The budget of the Team. It must not be null
     */
    @NotNull(message = "budget cannot be null")
    private BigDecimal budget;
}
