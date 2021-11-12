package service;

import model.Writer;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import repository.jdbc_impl.JDBCWriterRepositoryImpl;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class WriterServiceTest {

    @InjectMocks
    private WriterService writerService;

    @Mock
    private JDBCWriterRepositoryImpl mockWriterRepository;

    @Mock
    private Writer writer;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void whenReadWriterById() {
        Writer expected = writer();
        when(mockWriterRepository.getById(writer.id())).thenReturn(expected);

        Writer actual = writerService.getById(writer.id());

        verify(mockWriterRepository, times(1)).getById(writer.id());
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
        when(mockWriterRepository.save(writer)).thenReturn(expected);

        Writer actual = writerService.save(writer);

        verify(mockWriterRepository, times(1)).save(writer);
        assertEquals(expected, actual);
    }

    @Test
    public void whenUpdateWriter() {
        Writer expected = writer();
        when(mockWriterRepository.update(writer)).thenReturn(expected);

        Writer actual = writerService.update(writer);

        verify(mockWriterRepository).update(writer);
        assertEquals(expected, actual);
    }

    @Test
    public void whenDeleteWriter() {
        Writer expected = writer();

        when(mockWriterRepository.deleteById(writer)).thenReturn(expected);

        Writer actual = writerService.deleteById(writer);

        verify(mockWriterRepository).deleteById(writer);
        assertEquals(expected, actual);
    }

    private Writer writer() {
        return new Writer("Some first name", "Some last name");
    }
}