package service;

import model.enums.PostStatus;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import repository.PostStatusRepository;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * @author Nikita Gvardeev 02.11.2021
 * email gvardeev@po-korf.ru
 */
@RunWith(MockitoJUnitRunner.class)
public class PostStatusServiceTest {

    @Mock
    private PostStatusRepository statusRepository;

    @InjectMocks
    private PostStatusService statusService;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void whenCreatePostStatus() {
        doNothing().when(statusRepository).createPostStatus();

        statusService.createPostStatus();
        verify(statusRepository).createPostStatus();
    }

    @Test
    public void whenReadPostStatusACTIVE() {
        PostStatus expected = PostStatus.ACTIVE;
        when(statusRepository.postStatus()).thenReturn(PostStatus.ACTIVE);

        PostStatus actual = statusService.postStatus();

        assertEquals(expected, actual);
    }

    @Test
    public void whenReadPostStatusDELETED() {
        PostStatus expected = PostStatus.DELETED;
        when(statusRepository.postStatus()).thenReturn(PostStatus.DELETED);

        PostStatus actual = statusService.postStatus();

        assertEquals(expected, actual);
    }

    @Test
    public void whenReadPostStatusUNDER_REVIEW() {
        PostStatus expected = PostStatus.UNDER_REVIEW;
        when(statusRepository.postStatus()).thenReturn(PostStatus.UNDER_REVIEW);

        PostStatus actual = statusService.postStatus();

        assertEquals(expected, actual);
    }

    @Test
    public void whenUpdatePostStatus() {
        doNothing().when(statusRepository).updatePostStatus();

        statusService.updatePostStatus();
        verify(statusRepository).updatePostStatus();
    }

    @Test
    public void whenDeletePostStatus() {
        doNothing().when(statusRepository).deletePostStatus();

        statusService.deletePostStatus();
        verify(statusRepository).deletePostStatus();
    }
}