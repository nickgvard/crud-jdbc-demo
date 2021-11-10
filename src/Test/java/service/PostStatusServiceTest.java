package service;

import enums.PostStatus;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import repository.jdbc_impl.JDBCPostStatusRepositoryImpl;

import java.util.Collections;
import java.util.List;

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
    public void whenReadPostStatusACTIVE() {
        PostStatus expected = PostStatus.ACTIVE;
        when(statusRepository.read()).thenReturn(Collections.singletonList(PostStatus.ACTIVE));

        List<PostStatus> actual = statusService.read();

        assertEquals(expected, actual.get(0));
    }

    @Test
    public void whenReadPostStatusDELETED() {
        PostStatus expected = PostStatus.DELETED;
        when(statusRepository.read()).thenReturn(Collections.singletonList(PostStatus.DELETED));

        List<PostStatus> actual = statusService.read();

        assertEquals(expected, actual.get(0));
    }

    @Test
    public void whenReadPostStatusUNDER_REVIEW() {
        PostStatus expected = PostStatus.UNDER_REVIEW;
        when(statusRepository.read()).thenReturn(Collections.singletonList(PostStatus.UNDER_REVIEW));

        List<PostStatus> actual = statusService.read();

        assertEquals(expected, actual.get(0));
    }
}