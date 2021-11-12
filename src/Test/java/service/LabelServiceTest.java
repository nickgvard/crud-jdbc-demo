package service;

import model.Label;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import repository.jdbc_impl.JDBCLabelRepositoryImpl;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class LabelServiceTest {

    @Mock
    private Label label;

    @Mock
    private JDBCLabelRepositoryImpl mockLabelRepository;

    @InjectMocks
    private LabelService labelService;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void whenReadLabelById() {
        Label expected = label();
        when(mockLabelRepository.getById(label.id())).thenReturn(expected);

        Label actual = labelService.getById(label.id());

        verify(mockLabelRepository, times(1)).getById(label.id());
        assertEquals(expected, actual);
    }

    @Test
    public void whenReadAllLabel() {
        List<Label> expected = Arrays.asList(label(), label());
        when(mockLabelRepository.getAll()).thenReturn(expected);

        List<Label> actual = labelService.getAll();

        verify(mockLabelRepository, times(1)).getAll();
        assertEquals(expected.size(), actual.size());
    }

    @Test
    public void whenCreateLabel() {
        Label expected = label();

        when(mockLabelRepository.save(label)).thenReturn(expected);

        Label actual = labelService.save(label);

        verify(mockLabelRepository, times(1)).save(label);
        assertEquals(expected, actual);
    }

    @Test
    public void whenUpdateLabel() {
        Label expected = label();

        when(mockLabelRepository.update(label)).thenReturn(expected);

        Label actual = labelService.update(label);

        verify(mockLabelRepository, times(1)).update(label);
        assertEquals(expected, actual);
    }

    @Test
    public void whenDeleteLabel() {
        Label expected = label();

        when(mockLabelRepository.deleteById(label)).thenReturn(expected);

        Label actual = labelService.deleteById(label);

        verify(mockLabelRepository, times(1)).deleteById(label);
        assertEquals(expected, actual);
    }

    private Label label() {
        return new Label(1, "Some name label");
    }
}