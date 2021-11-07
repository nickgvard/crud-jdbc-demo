package service;

import model.entity.Label;
import model.entity.Post;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import repository.PostRepository;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * @author Nikita Gvardeev 03.11.2021
 * email gvardeev@po-korf.ru
 */
@RunWith(MockitoJUnitRunner.class)
public class PostServiceTest {

    @Mock
    private Post post;

    @Mock
    private PostRepository mockPostRepository;

    @InjectMocks
    private PostService postService;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void whenReadPostsAndEqFields() {
        List<Post> expected = Arrays.asList(post(), post());
        when(mockPostRepository.read()).thenReturn(Arrays.asList(post(), post()));

        List<Post> actual = postService.read();
        verify(mockPostRepository).read();

        assertEquals(expected.size(), actual.size());
        assertEquals(expected.get(0).id(), actual.get(0).id());
        assertEquals(expected.get(0).content(), actual.get(0).content());
        assertEquals(expected.get(0).created(), actual.get(0).created());
        assertEquals(expected.get(0).updated(), actual.get(0).updated());
        assertEquals(expected.get(0).labels().size(), actual.get(0).labels().size());
    }

    @Test
    public void whenUpdatePost() {
        doNothing().when(mockPostRepository).update(post);
        postService.update(post);

        verify(mockPostRepository, times(1)).update(post);
    }

    @Test
    public void whenDeletePost() {
        doNothing().when(mockPostRepository).remove(post);
        postService.delete(post);

        verify(mockPostRepository, times(1)).remove(post);
    }

    private Post post() {
        return new Post(1, "Some content",
                new Timestamp(10000000L), new Timestamp(20000000L),
                new ArrayList<>(Arrays.asList(
                        new Label(1, "Some name"),
                        new Label(1, "Some name"))));
    }
}