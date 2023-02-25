package guru.springframework.spring5recipeapp.services;

import guru.springframework.spring5recipeapp.commands.UnitOfMeasureCommand;
import guru.springframework.spring5recipeapp.converters.UnitOfMeasureToUnitOfMeasureCommand;
import guru.springframework.spring5recipeapp.domain.UnitOfMeasure;
import guru.springframework.spring5recipeapp.repositories.UnitOfMeasureRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UnitOfMeasureServiceImplTest {

    UnitOfMeasureToUnitOfMeasureCommand unitOfMeasureToUnitOfMeasureCommand = new UnitOfMeasureToUnitOfMeasureCommand();
    UnitOfMeasureService service;

    @Mock
    UnitOfMeasureRepository unitOfMeasureRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        service = new UnitOfMeasureServiceImpl(unitOfMeasureRepository, unitOfMeasureToUnitOfMeasureCommand);
    }

    @Test
    void listAllUoms() {

        Set<UnitOfMeasure> unitsOfMeasure = new HashSet<>();

        UnitOfMeasure unit1 = new UnitOfMeasure();
        unit1.setId(1L);
        unitsOfMeasure.add(unit1);

        UnitOfMeasure unit2 = new UnitOfMeasure();
        unit2.setId(2L);
        unitsOfMeasure.add(unit2);

        when(unitOfMeasureRepository.findAll()).thenReturn(unitsOfMeasure);

        Set<UnitOfMeasureCommand> uomsCommand = service.listAllUoms();

        assertEquals(2, uomsCommand.size());
        verify(unitOfMeasureRepository, times(1)).findAll();

    }
}