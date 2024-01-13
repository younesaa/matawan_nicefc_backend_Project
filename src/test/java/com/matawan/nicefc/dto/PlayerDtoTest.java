package com.matawan.nicefc.dto;

import com.matawan.nicefc.entity.Player;
import com.matawan.nicefc.entity.Team;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * {@code PlayerDtoTest} is a Test class for the PlayerDto class.
 * This class contains unit tests for methods in the PlayerDto class.
 */
public class PlayerDtoTest {
    private Validator validator;

    /**
     * Sets up the {@code Validator} instance before each test method.
     */
    @BeforeEach
    void setUp(){
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator =factory.getValidator();
    }

    /**
     * Tests the {@code PlayerDto} entity when there are no validation violations.
     * It checks if the entity behaves as expected when provided with valid data.
     */
    @Test
    void PlayerDtoTest_NoValidationViolation(){
        PlayerDto playerDto = new PlayerDto();
        playerDto.setName("younes");
        playerDto.setPosition("ST");

        assertEquals("younes",playerDto.getName());
        assertEquals("ST",playerDto.getPosition());
        Set<ConstraintViolation<PlayerDto>> violations = validator.validate(playerDto);
        assertTrue(violations.isEmpty());
    }

    /**
     * Tests the {@code PlayerDto} entity when there is a validation violation for the 'name' field.
     * It checks if the entity correctly handles the validation error message for an invalid 'name'.
     */
    @Test
    void PlayerDtoTest_withNameViolation(){

        PlayerDto playerDto = new PlayerDto();
        playerDto.setName("se");
        playerDto.setPosition("ST");

        TeamDto teamDto = new TeamDto();

        Set<ConstraintViolation<PlayerDto>> violations = validator.validate(playerDto);
        List<ConstraintViolation<PlayerDto>> violationList = new ArrayList<>(violations);
        assertEquals(violationList.get(0).getMessage(),"name size must be between 3 and 30 characters");
    }

    /**
     * Tests the {@code PlayerDto} entity when there is a validation violation for the 'position' field.
     * It checks if the entity correctly handles the validation error message for an invalid 'position'.
     */
    @Test
    void PlayerDtoTest_withPositionViolation(){
        PlayerDto playerDto = new PlayerDto();
        playerDto.setName("younes");
        playerDto.setPosition("S");

        Set<ConstraintViolation<PlayerDto>> violations = validator.validate(playerDto);
        List<ConstraintViolation<PlayerDto>> violationList = new ArrayList<>(violations);
        assertEquals(violationList.get(0).getMessage(),"position size must have 2 to 3 characters");
    }

    /**
     * Tests the {@code PlayerDto} entity when there is a validation violation for the 'position' and 'name' field.
     * It checks if the entity correctly handles the validation error messages for an invalid 'position' and 'name'.
     */
    @Test
    void PlayerDtoTest_withPositionAndNameViolation(){
        PlayerDto playerDto = new PlayerDto();

        Set<ConstraintViolation<PlayerDto>> violations = validator.validate(playerDto);
        List<ConstraintViolation<PlayerDto>> violationList = new ArrayList<>(violations);


        assertEquals(violationList.get(0).getMessage(),violationList.get(0).getPropertyPath() + " cannot be blank");
        assertEquals(violationList.get(1).getMessage(),violationList.get(1).getPropertyPath() + " cannot be blank");
    }
}
