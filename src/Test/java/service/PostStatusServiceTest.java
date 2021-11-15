package service;

import enums.PostStatus;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import repository.jdbc.JDBCPostStatusRepositoryImpl;

import java.util.*;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class PostStatusServiceTest {

    @Mock
    private JDBCPostStatusRepositoryImpl statusRepository;

    @InjectMocks
    private PostStatusService statusService;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void whenReadAllPostStatus() {
        List<PostStatus> expected = Arrays.asList(PostStatus.ACTIVE, PostStatus.UNDER_REVIEW, PostStatus.DELETED);

        when(statusRepository.getAll()).thenReturn(expected);

        List<PostStatus> actual = statusRepository.getAll();
        assertEquals(expected, actual);
    }

    @Test
    public void whenReadPostStatusACTIVE() {
        PostStatus expected = PostStatus.ACTIVE;
        when(statusRepository.getById(PostStatus.ACTIVE.getStatusId())).thenReturn(expected);

        PostStatus actual = statusService.getById(PostStatus.ACTIVE.getStatusId());

        assertEquals(expected, actual);
    }

    @Test
    public void whenReadPostStatusDELETED() {
        PostStatus expected = PostStatus.DELETED;
        when(statusRepository.getById(PostStatus.DELETED.getStatusId())).thenReturn(expected);

        PostStatus actual = statusService.getById(PostStatus.DELETED.getStatusId());

        assertEquals(expected, actual);
    }

    @Test
    public void whenReadPostStatusUNDER_REVIEW() {
        PostStatus expected = PostStatus.UNDER_REVIEW;
        when(statusRepository.getById(PostStatus.UNDER_REVIEW.getStatusId())).thenReturn(expected);

        PostStatus actual = statusService.getById(PostStatus.UNDER_REVIEW.getStatusId());

        assertEquals(expected, actual);
    }
}