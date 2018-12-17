/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.blablacar.service.config;

import cz.muni.fi.pa165.blablacar.persistence.entity.Comment;
import cz.muni.fi.pa165.blablacar.service.CommentService;
import javax.inject.Inject;
import org.dozer.DozerConverter;

/**
 *
 * @author Matus Sakala
 */
public class CommentIdConverter extends DozerConverter<Comment, Long> {
    @Inject
    private CommentService commentService;

    public CommentIdConverter() {
        super(Comment.class, Long.class);
    }

    @Override
    public Long convertTo(Comment comment, Long id) {
        return comment == null ? null : comment.getId();
    }

    @Override
    public Comment convertFrom(Long id, Comment comment) {
        return id == null ? null : commentService.findById(id);
    }
}
