package com.matawan.nicefc.dto;

import com.matawan.nicefc.entity.Team;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * {@code TeamDtoTest} is a Test class for the TeamDto class.
 * This class contains unit tests for methods in the TeamDto class.
 */
public class TeamDtoTest {
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
     * Tests the {@code TeamDto} entity when there are no validation violations.
     * It checks if the entity behaves as expected when provided with valid data.
     */
    @Test
    void TeamDtoTest_NoValidationViolation(){
        TeamDto teamDto = new TeamDto();
        teamDto.setName("nice");
        teamDto.setAcronym("NFC");
        teamDto.setBudget(BigDecimal.valueOf(100000));
        assertEquals("nice",teamDto.getName());
        assertEquals("NFC",teamDto.getAcronym());
        assertEquals(BigDecimal.valueOf(100000),teamDto.getBudget());
        Set<ConstraintViolation<TeamDto>> violations = validator.validate(teamDto);
        assertTrue(violations.isEmpty());
    }

    /**
     * Tests the {@code TeamDto} entity when there is a validation violation for the 'name' field.
     * It checks if the entity correctly handles the validation error message for an invalid 'name'.
     */
    @Test
    void TeamDtoTest_withNameViolation(){

        TeamDto teamDto = new TeamDto();
        teamDto.setName("ni");
        teamDto.setAcronym("NFC");
        teamDto.setBudget(BigDecimal.valueOf(100000));

        Set<ConstraintViolation<TeamDto>> violations = validator.validate(teamDto);
        List<ConstraintViolation<TeamDto>> violationList = new ArrayList<>(violations);
        assertEquals(violationList.get(0).getMessage(),"name size must be between 3 and 30 characters");
    }

    /**
     * Tests the {@code TeamDto} entity when there is a validation violation for the 'acronym' field.
     * It checks if the entity correctly handles the validation error message for an invalid 'acronym'.
     */
    @Test
    void TeamDtoTest_withAcronymViolation(){

        TeamDto team = new TeamDto();
        team.setName("nice");
        team.setAcronym("NC");
        team.setBudget(BigDecimal.valueOf(100000));

        Set<ConstraintViolation<TeamDto>> violations = validator.validate(team);
        List<ConstraintViolation<TeamDto>> violationList = new ArrayList<>(violations);
        assertEquals(violationList.get(0).getMessage(),"acronym size must be between 3 and 6 characters");
    }

    /**
     * Tests the {@code TeamDto} entity when there is a validation violation for the 'budget' field.
     * It checks if the entity correctly handles the validation error message for an invalid 'budget'.
     */
    @Test
    void TeamDtoTest_withBudgetViolation(){
        TeamDto team = new TeamDto();
        team.setName("nice");
        team.setAcronym("NFC");

        Set<ConstraintViolation<TeamDto>> violations = validator.validate(team);
        List<ConstraintViolation<TeamDto>> violationList = new ArrayList<>(violations);
        assertEquals(violationList.get(0).getMessage(),"budget cannot be null");
    }

    /**
     * Tests the {@code teamDto} entity when there is a validation violation for the 'acronym' and 'name' field.
     * It checks if the entity correctly handles the validation error messages for an invalid 'acronym' and 'name'.
     */
    @Test
    void TeamDtoTest_withAcronymAndNameViolation(){
        TeamDto team = new TeamDto();
        team.setBudget(BigDecimal.valueOf(100000));

        Set<ConstraintViolation<TeamDto>> violations = validator.validate(team);
        List<ConstraintViolation<TeamDto>> violationList = new ArrayList<>(violations);
        assertEquals(violationList.get(0).getMessage(),violationList.get(0).getPropertyPath() + " cannot be blank");
        assertEquals(violationList.get(1).getMessage(),violationList.get(1).getPropertyPath() + " cannot be blank");
    }
}
