package cz.muni.fi.pa165.blablacar.persistence.dao;

import cz.muni.fi.pa165.blablacar.persistence.entity.Comment;
import cz.muni.fi.pa165.blablacar.persistence.entity.Drive;
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
    void addComment(Comment comment) throws IllegalArgumentException;

    /**
     * Removes comment from database
     *
     * @param comment to be removed
     * @throws IllegalArgumentException if comment is null
     */
    void removeComment(Comment comment) throws IllegalArgumentException;

    /**
     * Updates comment in database
     *
     * @param comment to be updated
     * @throws IllegalArgumentException if comment is null
     */
    void updateComment(Comment comment) throws IllegalArgumentException;

    /**
     * Finds comment in database by specific id
     *
     * @param id id of specific comment
     * @return Comment with specified id or null if id was not found
     * @throws IllegalArgumentException if id is null
     */
    Comment findCommentById(Long id) throws IllegalArgumentException;

    /**
     * Find all comments
     *
     * @return list of all comments in database
     */
    List<Comment> findAll();

    /**
     * Find all comments of given user
     *
     * @param author of comments
     * @return list of all comment of given user
     * @throws IllegalArgumentException if author or authors id is null
     */
    List<Comment> findAllCommentsOfUser(User author) throws IllegalArgumentException;

    /**
     * Find all comments of given authorId
     *
     * @param authorId of comments
     * @return list of all comment of given user
     * @throws IllegalArgumentException if author or authors id is null
     */
    List<Comment> findAllCommentsOfUserWithId(Long authorId) throws IllegalArgumentException;


    /**
     * Find all comments of given drive
     * @param drive to retrieve comments
     * @return list of all comment of given drive
     * @throws IllegalArgumentException
     */
    List<Comment> findAllCommentsOfDrive(Drive drive) throws IllegalArgumentException;

    /**
     * Find all comments of given driveId
     * @param driveId of drive
     * @return list of all comment of given drive
     * @throws IllegalArgumentException
     */
    List<Comment> findAllCommentsOfDriveWithId(Long driveId) throws IllegalArgumentException;


}
