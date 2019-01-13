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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Matus Sakala
 */
public class CommentIdConverter extends DozerConverter<Comment, Long> {
    @Inject
    private CommentService commentService;
    private final static Logger log = LoggerFactory.getLogger(CommentIdConverter.class);


    public CommentIdConverter() {
        super(Comment.class, Long.class);
    }

    @Override
    public Long convertTo(Comment comment, Long id) {
        return comment == null ? null : comment.getId();
    }

    @Override
    public Comment convertFrom(Long id, Comment comment) {
        if (id == null) {
            return null;
        }
        Comment foundDrive = null;
        try {
            foundDrive = commentService.findById(id);
        } catch (Exception ex) {
            log.debug("Cannot find drive with id" + id);
        }
        return foundDrive;
    }
}
