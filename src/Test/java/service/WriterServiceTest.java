package service;

import model.entity.Label;
import model.entity.Post;
import model.entity.Writer;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import repository.WriterRepository;
import java.sql.Timestamp;
import java.util.Collections;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * @author Nikita Gvardeev 02.11.2021
 * email gvardeev@po-korf.ru
 */
@RunWith(MockitoJUnitRunner.class)
public class WriterServiceTest {

    @InjectMocks
    private WriterService writerService;

    @Mock
    private WriterRepository mockWriterRepository;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void whenCreateWriter() {
        doNothing().when(mockWriterRepository).createWriter();

        writerService.createWriter();
        verify(mockWriterRepository, times(1)).createWriter();
    }

    @Test
    public void whenReadWriterWithoutPostAndEqFields() {
        Writer expected = writer("withoutPost");
        when(mockWriterRepository.writer()).thenReturn(writer("withoutPost"));

        Writer actual = writerService.writer();
        verify(mockWriterRepository, times(1)).writer();

        assertEquals(expected.id(), actual.id());
        assertEquals(expected.firstName(), actual.firstName());
        assertEquals(expected.lastName(), actual.lastName());
        assertEquals(expected.posts().size() == 0, actual.posts().size() == 0);
    }

    @Test
    public void whenReadWriterWithPostAndEqFields() {
        Writer expected = writer("withPost");
        when(mockWriterRepository.writer()).thenReturn(writer("withPost"));

        Writer actual = writerService.writer();
        verify(mockWriterRepository, times(1)).writer();

        assertEquals(expected.id(), actual.id());
        assertEquals(expected.firstName(), actual.firstName());
        assertEquals(expected.lastName(), actual.lastName());
        assertEquals(expected.posts().size() != 0, actual.posts().size() != 0);
    }

    @Test
    public void whenUpdateWriter() {
        doNothing().when(mockWriterRepository).updateWriter();
        writerService.updateWriter();

        verify(mockWriterRepository).updateWriter();
    }

    @Test
    public void whenDeleteWriter() {
        doNothing().when(mockWriterRepository).deleteWriter();
        writerService.deleteWriter();

        verify(mockWriterRepository).deleteWriter();
    }

    private Writer writer(String typeWriter) {
        switch (typeWriter) {
            case "withPost":
                return new Writer(1L, "Some first name", "Some last name",
                        Collections.singletonList(new Post(1, "Some content", new Timestamp(100000L), new Timestamp(200000L),
                                Collections.singletonList(new Label(1L, "Some label name")))));
            case "withoutPost":
                return new Writer(1L, "Some first name", "Some last name");
            default: throw new RuntimeException("Illegal argument");
        }
    }
}