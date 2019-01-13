/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.blablacar.service.facade;

import cz.muni.fi.pa165.blablacar.api.dto.comment.CommentCreateDTO;
import cz.muni.fi.pa165.blablacar.api.dto.comment.CommentDTO;
import cz.muni.fi.pa165.blablacar.persistence.entity.Comment;
import cz.muni.fi.pa165.blablacar.persistence.entity.Drive;
import cz.muni.fi.pa165.blablacar.persistence.entity.User;
import cz.muni.fi.pa165.blablacar.service.*;
import cz.muni.fi.pa165.blablacar.service.config.ServiceConfiguration;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.testng.Assert.assertEquals;

/**
 * @author Matus Sakala
 */
@ContextConfiguration(classes = ServiceConfiguration.class)
public class CommentFacadeTest {

    @Mock
    private BeanMappingService beanMappingService;

    @Mock
    private TimeService timeService;

    @Mock
    private CommentService commentService;

    @Mock
    private DriveService driveService;

    @Mock
    private UserService userService;

    @Autowired
    @InjectMocks
    private CommentFacadeImpl commentFacade;


    private Comment comment;
    private CommentDTO commentDTO;
    private CommentCreateDTO commentCreateDTO;
    private List<Comment> comments;
    private List<CommentDTO> commentsDTO;

    private User mockedUser = new User();

    private Drive mockedDrive = new Drive();

    @BeforeClass
    void initMocks() {
        MockitoAnnotations.initMocks(this);
    }

    @BeforeMethod
    void initDTO() {
        Calendar cal = Calendar.getInstance();
        cal.set(2018, Calendar.NOVEMBER, 26);
        when(timeService.getCurrentTime()).thenReturn(cal.getTime());

        mockedUser.setFirstName("frodo");
        mockedUser.setLastName("baggins");
        mockedUser.setLogin("fb");
        mockedUser.setId(6L);

        mockedDrive.setId(6L);
        mockedDrive.setPrice(BigDecimal.ONE);
        mockedDrive.setDate(new Date());
        mockedDrive.setCapacity(5);

        commentCreateDTO = new CommentCreateDTO();
        commentCreateDTO.setContent("Very negative comment");
        commentCreateDTO.setAuthorId(mockedUser.getId());
        commentCreateDTO.setDriveId(mockedDrive.getId());

        comment = new Comment();
        comment.setAuthor(mockedUser);
        comment.setContent("Very negative comment");
        comment.setDrive(mockedDrive);
        when(beanMappingService.mapTo(commentCreateDTO, Comment.class)).thenReturn(comment);

        comments = new ArrayList<>();
        comments.add(comment);
        commentsDTO = new ArrayList<>();
        commentsDTO.add(commentDTO);

    }

    @AfterMethod
    void reset() {
        Mockito.reset(commentService);
    }

    @Test
    void createCommentTest() {
        when(commentService.createComment(comment)).thenReturn(comment);
        when(driveService.findDriveById(mockedDrive.getId())).thenReturn(mockedDrive);
        when(userService.findUserById(mockedUser.getId())).thenReturn(mockedUser);
        Long createdId = commentFacade.createComment(commentCreateDTO);
        verify(commentService).createComment(comment);
        assertThat(createdId).isEqualTo(comment.getId());
    }

    @Test
    void changeCommentTest() {
        when(commentService.findById(comment.getId())).thenReturn(comment);
        commentFacade.changeContent(comment.getId(), "Very positive comment");
        assertEquals("Very positive comment", comment.getContent());
        verify(commentService).updateComment(comment);
    }

    @Test
    void deleteCommentTest() {
        when(commentService.findById(comment.getId())).thenReturn(comment);
        commentFacade.deleteComment(comment.getId());
        verify(commentService).deleteComment(comment);
    }

    @Test
    void getCommentTest() {
        when(commentService.findById(comment.getId())).thenReturn(comment);
        when(beanMappingService.mapTo(comment, CommentDTO.class)).thenReturn(commentDTO);
        commentFacade.getComment(comment.getId());
        verify(commentService).findById(comment.getId());
    }

    @Test
    void getAllCommentsTest() {
        when(commentService.findAll()).thenReturn(comments);
        when(beanMappingService.mapTo(comments, CommentDTO.class)).thenReturn(commentsDTO);
        commentFacade.getAllComments();
        verify(commentService).findAll();
    }

    @Test
    void getCommentsOfDrive() {
        when(commentService.findAllCommentsOfDrive(mockedDrive.getId())).thenReturn(comments);
        when(beanMappingService.mapTo(comments, CommentDTO.class)).thenReturn(commentsDTO);
        commentFacade.getCommentsOfDrive(mockedDrive.getId());
        verify(commentService).findAllCommentsOfDrive(mockedDrive.getId());
    }

    @Test
    void getCommentsOfAuthor() {
        when(commentService.findAllCommentsOfUser(mockedUser.getId())).thenReturn(comments);
        when(beanMappingService.mapTo(comments, CommentDTO.class)).thenReturn(commentsDTO);
        commentFacade.getCommentsOfAuthor(mockedUser.getId());
        verify(commentService).findAllCommentsOfUser(mockedUser.getId());
    }


}
