package cz.muni.fi.pa165.blablacar.api.facade;

import cz.muni.fi.pa165.blablacar.api.dto.comment.CommentCreateDTO;
import cz.muni.fi.pa165.blablacar.api.dto.comment.CommentDTO;

import java.util.List;

/**
 * @author Martin Geletka
 */


public interface CommentFacade {
    /**
     * @param commentCreateDTO parameters will be used in creation of comment
     * @return id of created comment
     */
    Long createComment(CommentCreateDTO commentCreateDTO);

    /**
     * Sets new content for existing comment
     *
     * @param commentId of comment which content will be changed
     * @param newContent content to set
     */
    void changeContent(Long commentId, String newContent);

    /**
     * Deletes comment
     *
     * @param commentId of comment which will be deleted
     */
    void deleteComment(Long commentId);

    /**
     * Retrieves comment
     *
     * @param commentId of comment which will be retrieved
     * @return found comment or null if none found
     */
    CommentDTO getComment(Long commentId);

    /**
     * Retrieves all comments
     *
     * @return list of comments
     */
    List<CommentDTO> getAllComments();

    /**
     * Retrieves comments for specific drive
     *
     * @param driveId of drive which comments will be retrieved
     * @return list of comments
     */
    List<CommentDTO> getCommentsOfDrive(Long driveId);

    /**
     * Retrieved comments for specific user
     *
     * @param userId of author
     * @return list of comments
     */
    List<CommentDTO> getCommentsOfAuthor(Long userId);
}