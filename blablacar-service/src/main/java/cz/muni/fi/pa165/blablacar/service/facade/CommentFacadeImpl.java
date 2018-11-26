package cz.muni.fi.pa165.blablacar.service.facade;

import cz.muni.fi.pa165.blablacar.api.dto.comment.CommentCreateDTO;
import cz.muni.fi.pa165.blablacar.api.dto.comment.CommentDTO;
import cz.muni.fi.pa165.blablacar.api.facade.CommentFacade;
import cz.muni.fi.pa165.blablacar.persistence.entity.Comment;
import cz.muni.fi.pa165.blablacar.persistence.entity.Drive;
import cz.muni.fi.pa165.blablacar.persistence.entity.User;
import cz.muni.fi.pa165.blablacar.service.BeanMappingService;
import cz.muni.fi.pa165.blablacar.service.CommentService;
import cz.muni.fi.pa165.blablacar.service.DriveService;
import cz.muni.fi.pa165.blablacar.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.List;

@Service
@Transactional
public class CommentFacadeImpl implements CommentFacade {
    private final static Logger log = LoggerFactory.getLogger(CommentFacadeImpl.class);

    @Inject
    private CommentService commentService;

    @Inject
    private DriveService driveService;

    @Inject
    private UserService userService;

    @Inject
    private BeanMappingService beanMappingService;

    @Override
    public Long createComment(CommentCreateDTO commentCreateDTO) {
        Comment mappedComment = beanMappingService.mapTo(commentCreateDTO, Comment.class);
        Drive drive = driveService.findDriveById(commentCreateDTO.getDriveId());
        mappedComment.setDrive(drive);
        drive.addComment(mappedComment);

        User user = userService.findUserById(commentCreateDTO.getAuthorId());
        mappedComment.setAuthor(user);

        Comment comment = commentService.createComment(mappedComment);

        log.debug("Comment with id(" + comment.getId() + ") has been created");

        return comment.getId();
    }


    @Override
    public void changeContent(Long commentId, String newText) {
        Comment comment = commentService.findById(commentId);
        comment.setContent(newText);
        commentService.updateComment(comment);

        log.debug("Comment text has been updated for comment id(" + comment.getId() + ")");
    }

    @Override
    public void deleteComment(Long commentId) {
        Comment comment = commentService.findById(commentId);
        commentService.deleteComment(comment);
        log.debug("Comment with id(" + commentId + ") has been deleted");
    }

    @Override
    public CommentDTO getComment(Long commentId) {
        return beanMappingService.mapTo(commentService.findById(commentId), CommentDTO.class);
    }

    @Override
    public List<CommentDTO> getAllComments() {
        List<Comment> comments = commentService.findAll();
        log.debug("All comment have been retrieved, amount of comments: " + comments.size());
        return beanMappingService.mapTo(comments, CommentDTO.class);
    }


    @Override
    public List<CommentDTO> getCommentsOfDrive(Long driveId) {
        List<Comment> comments = commentService.findAllCommentsOfDrive(driveId);
        log.debug("Comments with ride id(" + driveId + ") has been retrieved, amount of comments: " + comments.size());
        return beanMappingService.mapTo(comments, CommentDTO.class);
    }

    @Override
    public List<CommentDTO> getCommentsOfAuthor(Long userId) {
        List<Comment> comments = commentService.findAllCommentsOfUser(userId);
        log.debug("Comments with author id(" + userId + ") has been retrieved, amount of comments: " + comments.size());
        return beanMappingService.mapTo(comments, CommentDTO.class);
    }
}
