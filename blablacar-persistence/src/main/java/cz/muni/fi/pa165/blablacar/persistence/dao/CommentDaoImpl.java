package cz.muni.fi.pa165.blablacar.persistence.dao;

import cz.muni.fi.pa165.blablacar.persistence.entity.Comment;
import cz.muni.fi.pa165.blablacar.persistence.entity.User;

import java.util.List;

public class CommentDaoImpl implements CommentDao {
    @Override
    public void addComment(Comment comment) throws IllegalArgumentException {

    }

    @Override
    public void removeComment(Comment comment) throws IllegalArgumentException {

    }

    @Override
    public void updateComment(Comment comment) throws IllegalArgumentException {

    }

    @Override
    public Comment findCommentById(Long id) throws IllegalArgumentException {
        return null;
    }

    @Override
    public List<Comment> findAll() {
        return null;
    }

    @Override
    public List<Comment> findAllCommentsOfUser(User author) {
        return null;
    }
}
