package cz.muni.fi.pa165.blablacar.service.facade;

import cz.muni.fi.pa165.blablacar.api.dto.CommentCreateDTO;
import cz.muni.fi.pa165.blablacar.api.dto.CommentDTO;
import cz.muni.fi.pa165.blablacar.api.facade.CommentFacade;

import java.util.List;

public class CommentFacadeImpl implements CommentFacade {
    @Override
    public Long createComment(CommentCreateDTO commentCreateDTO) {
        return null;
    }

    @Override
    public void changeText(Long commentId, String newText) {

    }

    @Override
    public void deleteComment(Long commentId) {

    }

    @Override
    public CommentDTO getCommentWithId(Long commentId) {
        return null;
    }

    @Override
    public List<CommentDTO> getAllComments() {
        return null;
    }

    @Override
    public List<CommentDTO> getCommentsWithRide(Long rideId) {
        return null;
    }

    @Override
    public List<CommentDTO> getCommentsWithAuthor(Long userId) {
        return null;
    }
}
