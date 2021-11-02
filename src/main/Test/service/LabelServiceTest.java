package service;

import model.entity.Label;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
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
    private LabelService labelService;

    @Mock
    private LabelRepository labelRepository;

    private Label label;

    @Before
    public void setUp() {
        label = new Label(1, "someNameLabel");
    }

    @Test
    public void testCreateLabelService() {
        labelService.createLabelService();
    }

    @Test
    public void testReadLabelService() {
        Label expected = new Label(1, "someNameLabel");
        when(labelRepository.readLabelRepository()).thenReturn(label);

        assertEquals(expected.name(), label.name());
    }

    @Test
    public void testReadLabelServiceNull() {
        when(labelRepository.readLabelRepository()).thenReturn(null);
        when(labelService.readLabelService()).thenReturn(null);
        assertNull(labelService.readLabelService());
    }
}