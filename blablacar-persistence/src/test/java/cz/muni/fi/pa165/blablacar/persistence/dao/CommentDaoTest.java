/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.blablacar.persistence.dao;

import cz.muni.fi.pa165.blablacar.persistence.PersistenceSampleApplicationContext;
import cz.muni.fi.pa165.blablacar.persistence.entity.City;
import cz.muni.fi.pa165.blablacar.persistence.entity.Comment;
import cz.muni.fi.pa165.blablacar.persistence.entity.Drive;
import cz.muni.fi.pa165.blablacar.persistence.entity.User;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import org.assertj.core.api.Assertions;


/**
 * Tests for Comment
 * @author Matus Sakala
 */

@ContextConfiguration(classes = PersistenceSampleApplicationContext.class)
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@Transactional
public class CommentDaoTest extends AbstractTestNGSpringContextTests{
    @PersistenceContext
    private EntityManager em;

    @Autowired
    private CommentDao commentDao;
    
    private Comment comment1, comment2;
    private User user1, user2;
    
    @BeforeMethod
    void setup() {
        user1 = new User();
        user1.setFirstName("Rick");
        user1.setLastName("Sanchez");
        user1.setLogin("Rick101");
        
        user2 = new User();
        user2.setFirstName("Morty");
        user2.setLastName("Smith");
        user2.setLogin("Moorty");
        LocalDate date = LocalDate.now();
        
        
        comment1 = new Comment();
        comment1.setAuthor(user1);
        comment1.setContent("Awesome!");
        comment1.setCreatedDate(Date.valueOf(date));
        comment1.setUpdateDate(Date.valueOf(LocalDate.now()));
        
        comment2 = new Comment();
        comment2.setAuthor(user2);
        comment2.setContent("I was scared..");
        comment2.setCreatedDate(Date.valueOf(date));
        comment2.setUpdateDate(Date.valueOf(LocalDate.now()));
        
        user1.setComments(new ArrayList<Comment>(Arrays.asList(comment1)));
        em.persist(user1);
        em.persist(user2);

    }
    
    @Test
    void testAddNullComment() {
        Assertions.assertThatThrownBy(() -> commentDao.addComment(null)).isInstanceOf(IllegalArgumentException.class);
    }
    
    @Test
    void testAddComment() {
        commentDao.addComment(comment1);
        
        List<Comment> resultList = em.createQuery("select c from Comment c", Comment.class).getResultList();
        assertThat(resultList).containsExactlyInAnyOrder(comment1);
    }
    
    @Test
    void testFindAll() {
        commentDao.addComment(comment1);
        commentDao.addComment(comment2);
        Assert.assertEquals(commentDao.findAll().size(), 2);
        assertThat(commentDao.findAll()).containsExactlyInAnyOrder(comment1, comment2);
    }
    
    @Test
    void testFindCityByNullId(){
        Assertions.assertThatThrownBy(() -> commentDao.findCommentById(null)).isInstanceOf(IllegalArgumentException.class);
    }
    
    @Test
    void testFindCityById() {
        commentDao.addComment(comment1);
        commentDao.addComment(comment2);
        Comment found = commentDao.findCommentById(comment1.getId());
        Assert.assertEquals(found.getId(), comment1.getId());
        Assert.assertEquals(found.getAuthor(), comment1.getAuthor());
        Assert.assertEquals(found.getContent(), comment1.getContent());
        Assert.assertEquals(found.getCreatedDate(), comment1.getCreatedDate());
        Assert.assertEquals(found.getUpdateDate(), comment1.getUpdateDate());
    }
    
    @Test
    void testUpdateNullComment() {
        Assertions.assertThatThrownBy(() -> commentDao.updateComment(null)).isInstanceOf(IllegalArgumentException.class);
    }
    
    @Test
    void testUpdateComment() {
        commentDao.addComment(comment1);

        String updatedText = comment1.getContent() + " Great!";
        comment1.setContent(updatedText);

        commentDao.updateComment(comment1);

        Comment comment = commentDao.findCommentById(comment1.getId());

        assertThat(comment).isNotNull().isEqualTo(comment1);
    }
    
    @Test
    void testDeleteNullComment(){
        Assertions.assertThatThrownBy(() -> commentDao.removeComment(null)).isInstanceOf(IllegalArgumentException.class);
    }
    
    @Test
    void testDeleteNonExtingComment(){
        commentDao.addComment(comment1);
        commentDao.removeComment(comment2);
        assertThat(commentDao.findAll()).containsExactlyInAnyOrder(comment1);
    }
    
    @Test
    void testDeleteComment() {
        commentDao.addComment(comment1);
        commentDao.addComment(comment2);
        commentDao.removeComment(comment1);
        List<Comment> comments = commentDao.findAll();
        assertThat(comments).containsExactlyInAnyOrder(comment2);
    }
    
    @Test
    void testFindAllOfNullUser() {
        Assertions.assertThatThrownBy(() -> commentDao.findAllCommentsOfUser(null)).isInstanceOf(IllegalArgumentException.class);
    }
    
    @Test
    void testFindAllOfUser() {
        commentDao.addComment(comment1);
        assertThat(commentDao.findAllCommentsOfUser(user1)).containsExactlyInAnyOrder(comment1);
    }
    
    @Test
    void testFindAllOfNonExistingUser(){
        User u1 = new User();
        u1.setFirstName("Not");
        u1.setLastName("In");
        u1.setLogin("Database");
        Assertions.assertThatThrownBy(() -> commentDao.findAllCommentsOfUser(u1)).isInstanceOf(IllegalArgumentException.class);
        
    }
}
