package service;

import model.entity.Label;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;
/**
 * @author Nikita Gvardeev 02.11.2021
 * email gvardeev@po-korf.ru
 */
@RunWith(MockitoJUnitRunner.class)
public class LabelServiceTest {

    @Mock
    private LabelService labelService;

    @Test
    public void createLabel() {
        doNothing().when(labelService).createLabel();
        labelService.createLabel();
        verify(labelService, times(1)).createLabel();
    }

    @Test
    public void label() {
        Label expected = new Label(1, "someNameLabel");
        when(labelService.label()).thenReturn(new Label(1, "someNameLabel"));

        assertEquals(expected.name(), labelService.label().name());
    }

    @Test
    public void updateLabel() {
        doNothing().when(labelService).updateLabel();
        labelService.updateLabel();
        verify(labelService, times(1)).updateLabel();
    }

    @Test
    public void deleteLabel() {
        doNothing().when(labelService).deleteLabel();
        labelService.deleteLabel();
        verify(labelService, times(1)).deleteLabel();
    }
}