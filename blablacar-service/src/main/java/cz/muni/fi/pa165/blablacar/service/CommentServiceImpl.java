package cz.muni.fi.pa165.blablacar.service;

import cz.muni.fi.pa165.blablacar.persistence.dao.CommentDao;
import cz.muni.fi.pa165.blablacar.persistence.entity.Comment;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.Date;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    @Inject
    private CommentDao commentDao;

    @Inject
    private TimeService timeService;

    @Override
    public Comment createComment(Comment comment) throws IllegalArgumentException {
        if (comment == null) {
            throw new IllegalArgumentException("Comment cannot be null.");
        }

        Date now = timeService.getCurrentTime();
        comment.setCreatedDate(now);
        comment.setUpdateDate(now);
        commentDao.addComment(comment);
        return comment;
    }

    @Override
    public void updateComment(Comment comment) throws IllegalArgumentException {
        if (comment == null) {
            throw new IllegalArgumentException("Comment cannot be null.");
        }

        comment.setUpdateDate(new Date());
        commentDao.updateComment(comment);
    }

    @Override
    public void deleteComment(Comment comment) throws IllegalArgumentException {
        if (comment == null) {
            throw new IllegalArgumentException("Comment cannot be null.");
        }
        commentDao.removeComment(comment);
    }

    @Override
    public Comment findById(Long id) throws IllegalArgumentException {
        if (id == null) {
            throw new IllegalArgumentException("Comment find comment with null id.");
        }
        return commentDao.findCommentById(id);
    }

    @Override
    public List<Comment> findAll() {
        return commentDao.findAll();
    }

    @Override
    public List<Comment> findAllCommentsOfDrive(Long id) throws IllegalArgumentException {
        if (id == null) throw new IllegalArgumentException(CommentServiceImpl.class
                + "Find all comments of drive: drive id is null");
        return commentDao.findAllCommentsOfDriveWithId(id);
    }

    @Override
    public List<Comment> findAllCommentsOfUser(Long id) throws IllegalArgumentException {
        if (id == null) throw new IllegalArgumentException(CommentServiceImpl.class
                + "Find all comments of user: user id is null");
        return commentDao.findAllCommentsOfUserWithId(id);
    }
}
