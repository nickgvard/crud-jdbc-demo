package service;

import model.Writer;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import repository.jdbc.JDBCWriterRepositoryImpl;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class WriterServiceTest {

    @InjectMocks
    private WriterService writerService;

    @Mock
    private JDBCWriterRepositoryImpl mockWriterRepository;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void whenReadWriterById() {
        Writer expected = writer();
        when(mockWriterRepository.getById(anyLong())).thenReturn(expected);

        Writer actual = writerService.getById(anyLong());

        verify(mockWriterRepository, times(1)).getById(any());
        assertEquals(expected, actual);
    }

    @Test
    public void whenReadAllWriter() {
        List<Writer> expected = Arrays.asList(writer(), writer());
        when(mockWriterRepository.getAll()).thenReturn(expected);

        List<Writer> actual = writerService.getAll();

        verify(mockWriterRepository, times(1)).getAll();
        assertEquals(expected, actual);
        assertEquals(expected.size(), actual.size());
    }

    @Test
    public void whenCreateWriter() {
        Writer expected = writer();
        when(mockWriterRepository.save(any())).thenReturn(expected);

        Writer actual = writerService.save(any());

        verify(mockWriterRepository, times(1)).save(any());
        assertEquals(expected, actual);
    }

    @Test
    public void whenUpdateWriter() {
        Writer expected = writer();
        when(mockWriterRepository.update(any())).thenReturn(expected);

        Writer actual = writerService.update(any());

        verify(mockWriterRepository).update(any());
        assertEquals(expected, actual);
    }

    @Test
    public void whenDeleteWriter() {
        Writer expected = writer();

        when(mockWriterRepository.deleteById(any())).thenReturn(expected);

        Writer actual = writerService.deleteById(any());

        verify(mockWriterRepository).deleteById(any());
        assertEquals(expected, actual);
    }

    private Writer writer() {
        return Writer.builder().firstName("Some last name").lastName("Some last name").build();
    }
}