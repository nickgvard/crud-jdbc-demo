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

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * @author Nikita Gvardeev 03.11.2021
 * email gvardeev@po-korf.ru
 */
@RunWith(MockitoJUnitRunner.class)
public class PostServiceTest {

    @Mock
    private PostRepository mockPostRepository;

    @InjectMocks
    private PostService postService;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void whenCreatePost() {
        doNothing().when(mockPostRepository).createPost();

        postService.createPost();
        verify(mockPostRepository, times(1)).createPost();
    }

    @Test
    public void whenReadPostAndEqFields() {
        Post expected = post();
        when(mockPostRepository.post()).thenReturn(post());

        Post actual = postService.post();
        verify(mockPostRepository).post();

        assertEquals(expected.id(), actual.id());
        assertEquals(expected.content(), actual.content());
        assertEquals(expected.created(), actual.created());
        assertEquals(expected.updated(), actual.updated());
        assertEquals(expected.labels().size(), actual.labels().size());
    }

    @Test
    public void whenUpdatePost() {
        doNothing().when(mockPostRepository).updatePost();
        postService.updatePost();

        verify(mockPostRepository, times(1)).updatePost();
    }

    @Test
    public void whenDeletePost() {
        doNothing().when(mockPostRepository).deletePost();
        postService.deletePost();

        verify(mockPostRepository, times(1)).deletePost();
    }

    private Post post() {
        return new Post(1, "Some content",
                new Timestamp(10000000L), new Timestamp(20000000L),
                new ArrayList<>(Arrays.asList(
                        new Label(1, "Some name"),
                        new Label(1, "Some name"))));
    }
}