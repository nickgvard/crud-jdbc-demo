package service;

import model.Post;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import repository.jdbc_impl.JDBCPostRepositoryImpl;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class PostServiceTest {

    @Mock
    private Post post;

    @Mock
    private JDBCPostRepositoryImpl mockPostRepository;

    @InjectMocks
    private PostService postService;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void whenReadPostById() {
        Post expected = post();
        when(mockPostRepository.getById(post.id())).thenReturn(expected);

        Post actual = postService.getById(post.id());

        verify(mockPostRepository, times(1)).getById(post.id());
        assertEquals(expected, actual);
    }

    @Test
    public void whenReadAllPosts() {
        List<Post> expected = Arrays.asList(post(), post());
        when(mockPostRepository.getAll()).thenReturn(expected);

        List<Post> actual = postService.getAll();

        verify(mockPostRepository, times(1)).getAll();
        assertEquals(expected, actual);
        assertEquals(expected.size(), actual.size());
    }

    @Test
    public void whenCreatPost() {
        Post expected = post();
        when(mockPostRepository.save(post)).thenReturn(expected);

        Post actual = postService.save(post);

        verify(mockPostRepository, times(1)).save(post);
        assertEquals(expected, actual);
    }

    @Test
    public void whenUpdatePost() {
        Post expected = post();
        when(mockPostRepository.update(post)).thenReturn(expected);

        Post actual = postService.update(post);

        verify(mockPostRepository, times(1)).update(post);
        assertEquals(expected, actual);
    }

    @Test
    public void whenDeletePost() {
        Post expected = post();
        when(mockPostRepository.deleteById(post)).thenReturn(expected);

        Post actual = postService.deleteById(post);

        verify(mockPostRepository, times(1)).deleteById(post);
        assertEquals(expected, actual);
    }

    private Post post() {
        return new Post(1, "Some content",
                new Timestamp(10000000L), new Timestamp(20000000L));
    }
}