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
import repository.jdbc_impl.JDBCWriterRepositoryImpl;
import java.sql.Timestamp;
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
    public void whenCreateWriter() {
        doNothing().when(mockWriterRepository).save(writer);

        writerService.create(writer);
        verify(mockWriterRepository, times(1)).save(writer);
    }

    @Test
    public void whenReadWriterWithoutPostAndEqFields() {
        List<Writer> expected = Collections.singletonList(writer("withoutPost"));
        when(mockWriterRepository.read()).thenReturn(Collections.singletonList(writer("withoutPost")));

        List<Writer> actual = writerService.read();
        verify(mockWriterRepository, times(1)).read();

        assertEquals(expected.get(0).id(), actual.get(0).id());
        assertEquals(expected.get(0).firstName(), actual.get(0).firstName());
        assertEquals(expected.get(0).lastName(), actual.get(0).lastName());
        assertEquals(expected.get(0).posts().size() == 0, actual.get(0).posts().size() == 0);
    }

    @Test
    public void whenReadWriterWithPostAndEqFields() {
        List<Writer> expected = Collections.singletonList(writer("withPost"));
        when(mockWriterRepository.read()).thenReturn(Collections.singletonList(writer("withPost")));

        List<Writer> actual = writerService.read();
        verify(mockWriterRepository, times(1)).read();

        assertEquals(expected.get(0).id(), actual.get(0).id());
        assertEquals(expected.get(0).firstName(), actual.get(0).firstName());
        assertEquals(expected.get(0).lastName(), actual.get(0).lastName());
        assertEquals(expected.get(0).posts().size() != 0, actual.get(0).posts().size() != 0);
    }

    @Test
    public void whenUpdateWriter() {
        doNothing().when(mockWriterRepository).update(writer);
        writerService.update(writer);

        verify(mockWriterRepository).update(writer);
    }

    @Test
    public void whenDeleteWriter() {
        doNothing().when(mockWriterRepository).deleteById(writer);
        writerService.delete(writer);

        verify(mockWriterRepository).deleteById(writer);
    }

    private Writer writer(String typeWriter) {
        switch (typeWriter) {
            case "withPost":
                return new Writer(1L, "Some first name", "Some last name",
                        Collections.singletonList(new Post(1, "Some content", new Timestamp(100000L), new Timestamp(200000L),
                                Collections.singletonList(new Label(1L, "Some label name")))));
            case "withoutPost":
                return new Writer("Some first name", "Some last name");
            default: throw new RuntimeException("Illegal argument");
        }
    }
}