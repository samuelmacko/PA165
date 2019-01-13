/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.blablacar.service;

import cz.muni.fi.pa165.blablacar.persistence.dao.CommentDao;
import cz.muni.fi.pa165.blablacar.persistence.entity.Comment;
import cz.muni.fi.pa165.blablacar.persistence.entity.Drive;
import cz.muni.fi.pa165.blablacar.persistence.entity.User;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

/**
 * @author Matus Sakala
 */
public class CommentServiceTest {

    @Mock
    private CommentDao commentDao;

    @Mock
    private TimeServiceImpl timeService;

    @Autowired
    @InjectMocks
    private CommentServiceImpl commentService;

    private Drive drive1;
    private Drive drive2;

    private User user1;
    private User user2;

    private Comment comment1;
    private Comment comment2;


    @BeforeClass
    void initMocks() {
        MockitoAnnotations.initMocks(this);
    }

    @BeforeMethod
    void prepareComment() {
        Calendar calendar = Calendar.getInstance();

        comment1 = new Comment();
        comment1.setId(3L);
        comment1.setContent("first comment");
        calendar.set(2018, Calendar.NOVEMBER, 25);
        comment1.setCreatedDate(calendar.getTime());

        drive1 = new Drive();
        drive1.setId(1L);
        drive1.addComment(comment1);
        comment1.setDrive(drive1);

        user1 = new User();
        user1.setId(8L);
        user1.setFirstName("Frodo");
        user1.setLastName("Baggins");
        user1.setLogin("FB");
        user1.addToComments(comment1);
        comment1.setAuthor(user1);

        comment2 = new Comment();
        comment2.setId(4L);
        comment2.setContent("another comment");
        calendar.set(2018, Calendar.NOVEMBER, 26);
        comment2.setCreatedDate(calendar.getTime());

        drive2 = new Drive();
        drive2.setId(2L);
        drive2.addComment(comment2);
        comment2.setDrive(drive2);

        user2 = new User();
        user2.setId(15L);
        user2.setFirstName("Mr");
        user2.setLastName("Underhill");
        user2.setLogin("MU");
        user2.addToComments(comment2);
        comment2.setAuthor(user2);
    }

    @AfterMethod
    void reset() {
        Mockito.reset(commentDao);
        Mockito.reset(timeService);
    }

    @Test
    void createCommentTest() {
        doNothing().when(commentDao).addComment(any());

        Calendar cal = Calendar.getInstance();
        cal.set(2018, Calendar.NOVEMBER, 26);
        when(timeService.getCurrentTime()).thenReturn(cal.getTime());

        Comment result = commentService.createComment(comment1);

        verify(timeService).getCurrentTime();
        verify(commentDao).addComment(comment1);

        assertThat(result).isEqualToComparingFieldByField(comment1);
    }

    @Test
    void createNullCommentTest() {
        assertThatThrownBy(() -> commentService.createComment(null))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void updateCommentTest() {
        comment1.setContent("changed text");

        doNothing().when(commentDao).updateComment(comment1);

        commentService.updateComment(comment1);

        verify(commentDao).updateComment(comment1);
    }

    @Test
    void updateNullCommentTest() {
        assertThatThrownBy(() -> commentService.updateComment(null))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void findAllTest() {
        List<Comment> allComments = new ArrayList<>();
        allComments.add(comment1);
        allComments.add(comment2);

        when(commentDao.findAll()).thenReturn(allComments);

        List<Comment> result = commentService.findAll();
        verify(commentDao).findAll();
        assertThat(result)
                .containsExactlyInAnyOrder(comment1, comment2);
    }

    @Test
    void findByIdTest() {
        when(commentDao.findCommentById(comment1.getId())).thenReturn(comment1);
        when(commentDao.findCommentById(comment2.getId())).thenReturn(comment2);

        Comment shouldBeComment1 = commentService.findById(comment1.getId());
        assertThat(shouldBeComment1)
                .isEqualToComparingFieldByField(comment1);

        Comment shouldBeComment2 = commentService.findById(comment2.getId());
        assertThat(shouldBeComment2)
                .isEqualToComparingFieldByFieldRecursively(comment2);
        verify(commentDao).findCommentById(comment1.getId());
        verify(commentDao).findCommentById(comment2.getId());
    }

    @Test
    void findByIdNotFoundTest() {
        when(commentDao.findCommentById(any())).thenReturn(null);

        Comment result = commentService.findById(-1L);
        verify(commentDao).findCommentById(-1L);
        assertThat(result).isNull();
    }

    @Test
    void findCommentsWithDriveTest() {
        when(commentDao.findAllCommentsOfDriveWithId(drive1.getId())).thenReturn(new ArrayList<>(drive1.getComments()));
        when(commentDao.findAllCommentsOfDriveWithId(drive2.getId())).thenReturn(new ArrayList<>(drive2.getComments()));

        List<Comment> ride1Comments = commentService.findAllCommentsOfDrive(drive1.getId());
        assertThat(ride1Comments)
                .containsExactlyInAnyOrder(comment1);

        List<Comment> ride2Comments = commentService.findAllCommentsOfDrive(drive2.getId());
        assertThat(ride2Comments)
                .containsExactlyInAnyOrder(comment2);
        verify(commentDao).findAllCommentsOfDriveWithId(drive1.getId());
        verify(commentDao).findAllCommentsOfDriveWithId(drive2.getId());
    }

    @Test
    void findMultipleCommentsOnDriveTest() {
        comment2.setDrive(drive1);
        drive1.addComment(comment2);
        when(commentDao.findAllCommentsOfDriveWithId(drive1.getId()))
                .thenReturn(new ArrayList<>(drive1.getComments()));

        List<Comment> drive1Comments = commentService.findAllCommentsOfDrive(drive1.getId());
        verify(commentDao).findAllCommentsOfDriveWithId(drive1.getId());
        assertThat(drive1Comments)
                .containsExactlyInAnyOrder(comment1, comment2);
    }

    @Test
    void findZeroCommentsOnDriveTest() {
        when(commentDao.findAllCommentsOfDriveWithId(drive1.getId())).thenReturn(new ArrayList<>());

        List<Comment> result = commentService.findAllCommentsOfDrive(drive1.getId());

        verify(commentDao).findAllCommentsOfDriveWithId(drive1.getId());
        assertThat(result)
                .isEmpty();
    }

    @Test
    void findCommentsOnNullDrive() {
        assertThatThrownBy(() -> commentService.findAllCommentsOfDrive(null))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void findCommentsWithAuthorTest() {
        when(commentDao.findAllCommentsOfUserWithId(user1.getId())).thenReturn(new ArrayList<>(user1.getComments()));
        when(commentDao.findAllCommentsOfUserWithId(user2.getId())).thenReturn(new ArrayList<>(user2.getComments()));

        List<Comment> firstComments = commentService.findAllCommentsOfUser(user1.getId());
        assertThat(firstComments)
                .containsExactlyInAnyOrder(comment1);

        List<Comment> secondComments = commentService.findAllCommentsOfUser(user2.getId());
        verify(commentDao).findAllCommentsOfUserWithId(user1.getId());
        verify(commentDao).findAllCommentsOfUserWithId(user2.getId());
        assertThat(secondComments)
                .containsExactlyInAnyOrder(comment2);
    }

    @Test
    void findCommentsWithAuthorMultipleCommentsTest() {
        comment2.setAuthor(user1);
        user1.addToComments(comment2);
        when(commentDao.findAllCommentsOfUserWithId(user1.getId())).thenReturn(new ArrayList<>(user1.getComments()));

        List<Comment> comments = commentService.findAllCommentsOfUser(user1.getId());
        verify(commentDao).findAllCommentsOfUserWithId(user1.getId());
        assertThat(comments)
                .containsExactlyInAnyOrder(comment1, comment2);
    }

    @Test
    void findCommentsWithAuthorNullTest() {
        assertThatThrownBy(() -> commentService.findAllCommentsOfUser(null))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void deleteNullCommentTest() {
        assertThatThrownBy(() -> commentService.deleteComment(null))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
