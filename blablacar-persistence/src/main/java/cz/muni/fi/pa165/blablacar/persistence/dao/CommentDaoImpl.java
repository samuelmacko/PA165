package cz.muni.fi.pa165.blablacar.persistence.dao;

import com.google.common.base.Preconditions;
import cz.muni.fi.pa165.blablacar.persistence.entity.Comment;
import cz.muni.fi.pa165.blablacar.persistence.entity.Drive;
import cz.muni.fi.pa165.blablacar.persistence.entity.User;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Transactional
public class CommentDaoImpl implements CommentDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void addComment(Comment comment) throws IllegalArgumentException {
        Preconditions.checkArgument(comment != null, "Can not to add null comment");
        em.persist(comment);
    }

    @Override
    public void removeComment(Comment comment) throws IllegalArgumentException {
        Preconditions.checkArgument(comment != null, "Can not to remove null comment");
        em.remove(comment);
    }

    @Override
    public void updateComment(Comment comment) throws IllegalArgumentException {
        Preconditions.checkArgument(comment != null, "Can not to update null comment");
        em.merge(comment);
    }

    @Override
    public Comment findCommentById(Long id) throws IllegalArgumentException {
        Preconditions.checkArgument(id != null, "Can not to update null comment");
        return em.find(Comment.class, id);
    }

    @Override
    public List<Comment> findAll() {
        return em.createQuery("SELECT c FROM Comment c", Comment.class).getResultList();
    }

    @Override
    public List<Comment> findAllCommentsOfUser(User author) throws IllegalArgumentException {
        Preconditions.checkArgument(author != null, "Can not find comments for null user");
        return findAllCommentsOfUserWithId(author.getId());
    }

    @Override
    public List<Comment> findAllCommentsOfUserWithId(Long authorId) throws IllegalArgumentException {
        Preconditions.checkArgument(authorId != null, "Can not find comments for user with null id");
        return em.createQuery("SELECT c FROM Comment c WHERE c.author.id = :id", Comment.class).setParameter("id", authorId).getResultList();
    }

    @Override
    public List<Comment> findAllCommentsOfDrive(Drive drive) throws IllegalArgumentException {
        Preconditions.checkArgument(drive != null, "Can not find comments for null drive");
        return findAllCommentsOfDriveWithId(drive.getId());
    }

    @Override
    public List<Comment> findAllCommentsOfDriveWithId(Long driveId) throws IllegalArgumentException {
        Preconditions.checkArgument(driveId != null, "Can not find comments for user with null id");
        return em.createQuery("SELECT c FROM Comment c WHERE c.drive.id = :id", Comment.class).setParameter("id", driveId).getResultList();
    }
}
