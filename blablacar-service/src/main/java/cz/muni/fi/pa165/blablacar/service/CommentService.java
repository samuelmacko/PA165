package cz.muni.fi.pa165.blablacar.service;

import cz.muni.fi.pa165.blablacar.persistence.entity.Comment;

import java.util.List;

public interface CommentService {
    Comment createComment(Comment comment) throws IllegalArgumentException;
    void updateComment(Comment comment) throws IllegalArgumentException;
    void deleteComment(Comment comment) throws IllegalArgumentException;
    Comment findById(Long id) throws IllegalArgumentException;
    List<Comment> findAll();
    List<Comment> findAllCommentsOfDrive(Long id) throws IllegalArgumentException;
    List<Comment> findAllCommentsOfUser(Long id) throws IllegalArgumentException;
}
