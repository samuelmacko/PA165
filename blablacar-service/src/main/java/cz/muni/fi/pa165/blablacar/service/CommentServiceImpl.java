package cz.muni.fi.pa165.blablacar.service;

import cz.muni.fi.pa165.blablacar.persistence.dao.CommentDao;
import cz.muni.fi.pa165.blablacar.persistence.entity.Comment;

import javax.inject.Inject;
import java.util.Date;
import java.util.List;

public class CommentServiceImpl implements CommentService {

    @Inject
    private CommentDao commentDao;

    @Override
    public Comment createComment(Comment comment) throws IllegalArgumentException {
        if (comment == null) {
            throw new IllegalArgumentException("Comment cannot be null.");
        }

        Date now = new Date();
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
        return commentDao.findAllCommentsOfDriveWithId(id);
    }

    @Override
    public List<Comment> findAllCommentsOfUser(Long id) throws IllegalArgumentException {
        return commentDao.findAllCommentsOfUserWithId(id);
    }
}
