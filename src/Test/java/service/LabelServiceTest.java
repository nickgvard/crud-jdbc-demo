package service;

import model.entity.Label;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import repository.LabelRepository;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

/**
 * @author Nikita Gvardeev 02.11.2021
 * email gvardeev@po-korf.ru
 */
@RunWith(MockitoJUnitRunner.class)
public class LabelServiceTest {

    @Mock
    private LabelRepository mockLabelRepository;

    @InjectMocks
    private LabelService labelService;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void whenCreateLabelTest() {
        doNothing().when(mockLabelRepository).createLabel();

        labelService.createLabel();
        verify(mockLabelRepository, times(1)).createLabel();
    }

    @Test
    public void whenReadLabelAndEqFields() {
        Label expected = new Label(1, "Some name label");
        when(mockLabelRepository.label()).thenReturn(new Label(1, "Some name label"));

        Label actual = labelService.label();
        verify(mockLabelRepository, times(1)).label();

        assertEquals(expected.id(), actual.id());
        assertEquals(expected.name(), actual.name());
    }

    @Test
    public void whenUpdateLabel() {
        doNothing().when(mockLabelRepository).updateLabel();
        labelService.updateLabel();

        verify(mockLabelRepository, times(1)).updateLabel();
    }

    @Test
    public void whenDeleteLabel() {
        doNothing().when(mockLabelRepository).deleteLabel();
        labelService.deleteLabel();

        verify(mockLabelRepository, times(1)).deleteLabel();
   }
}