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
    public void changeContent(Long commentId, String newContent) {

    }

    @Override
    public void deleteComment(Long commentId) {

    }

    @Override
    public CommentDTO getComment(Long commentId) {
        return null;
    }

    @Override
    public List<CommentDTO> getAllComments() {
        return null;
    }

    @Override
    public List<CommentDTO> getCommentsOfDrive(Long driveId) {
        return null;
    }

    @Override
    public List<CommentDTO> getCommentsOfAuthor(Long userId) {
        return null;
    }
}
