package cz.muni.fi.pa165.blablacar.persistence.dao;

import cz.muni.fi.pa165.blablacar.persistence.entity.Comment;
import cz.muni.fi.pa165.blablacar.persistence.entity.User;

import java.util.List;

/**
 * CommentDao interface
 *
 * @author Martin Geletka
 */
public interface CommentDao {
    /**
     * Adds comment to database
     *
     * @param comment to be added
     * @throws IllegalArgumentException if comment is null
     */
    public void addComment(Comment comment) throws IllegalArgumentException;

    /**
     * Removes comment from database
     *
     * @param comment to be removed
     * @throws IllegalArgumentException if comment is null
     */
    public void removeComment(Comment comment) throws IllegalArgumentException;

    /**
     * Updates comment in database
     *
     * @param comment to be updated
     * @throws IllegalArgumentException if comment is null
     */
    public void updateComment(Comment comment) throws IllegalArgumentException;

    /**
     * Finds comment in database by specific id
     *
     * @param id id of specific comment
     * @return Comment with specified id or null if id was not found
     * @throws IllegalArgumentException if id is null
     */
    public Comment findCommentById(Long id) throws IllegalArgumentException;

    /**
     * Find all comments
     *
     * @return list of all comments in database
     */
    public List<Comment> findAll();

    /**
     * Find all comments of given user
     * @param author of comments
     * @return list of all comment of given user
     */
    public List<Comment> findAllCommentsOfUser(User author);

}
