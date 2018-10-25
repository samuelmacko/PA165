package cz.muni.fi.pa165.blablacar.persistence.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

/**
 * Class representing user
 *
 * @author Martin Geletka
 */

@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User author;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date createdDate;


    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date updateDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Comment)) return false;
        Comment comment = (Comment) o;
        return Objects.equals(getAuthor(), comment.getAuthor()) &&
                Objects.equals(getContent(), comment.getContent()) &&
                Objects.equals(getCreatedDate(), comment.getCreatedDate()) &&
                Objects.equals(getUpdateDate(), comment.getUpdateDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getAuthor(), getContent(), getCreatedDate(), getUpdateDate());
    }

    @ManyToOne
    private Drive drive;

    public Drive getDrive() {
        return drive;
    }

    public void setDrive(Drive drive) {
        this.drive = drive;
    }
}
