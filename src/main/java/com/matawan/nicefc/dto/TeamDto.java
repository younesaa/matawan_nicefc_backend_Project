package com.matawan.nicefc.dto;

import com.matawan.nicefc.entity.Player;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;
@Data
@NoArgsConstructor
public class TeamDto {

    /**
     * The name of the TeamDto. It must not be blank and should be between 3 and 30 characters.
     */
    @NotEmpty(message = "name cannot be blank")
    @Size(min = 3,max = 30,message = "name size must be between 3 and 30 characters")
    private String name;

    /**
     * The Acronym of the TeamDto. It must not be blank and should have 3 to 6 characters.
     */
    @NotEmpty(message = "acronym cannot be blank")
    @Size(min = 3,max = 6,message = "acronym size must be between 3 and 6 characters")
    private String acronym;

    /**
     * The playerDto List of the TeamDto. It can be empty
     */
    private List<PlayerDto> players;

    /**
     * The budget of the TeamDto. It must not be null
     */
    @NotNull(message = "budget cannot be null")
    private BigDecimal budget;
}
