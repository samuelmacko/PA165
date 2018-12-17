package cz.muni.fi.pa165.blablacar.api.dto.comment;

import cz.muni.fi.pa165.blablacar.api.dto.DriveDTO;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.Objects;

public class CommentDTO {

    @NotNull
    private Long id;

    @NotNull
    @Size(min = 1, max = 4096)
    private String content;

    @NotNull
    private Long authorID;

    @NotNull
    private DriveDTO drive;

    @NotNull
    private Date createdDate;

    @NotNull
    private Date updateDate;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAuthorID() {
        return authorID;
    }

    public void setAuthorID(Long authorID) {
        this.authorID = authorID;
    }

    public DriveDTO getDrive() {
        return drive;
    }

    public void setDrive(DriveDTO drive) {
        this.drive = drive;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CommentDTO)) return false;
        CommentDTO that = (CommentDTO) o;
        return Objects.equals(getContent(), that.getContent()) &&
                Objects.equals(getAuthorID(), that.getAuthorID()) &&
                Objects.equals(getDrive(), that.getDrive()) &&
                Objects.equals(getCreatedDate(), that.getCreatedDate()) &&
                Objects.equals(getUpdateDate(), that.getUpdateDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getContent(), getAuthorID(), getDrive(), getCreatedDate(), getUpdateDate());
    }

    @Override
    public String toString() {
        return "CommentDTO{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", authorID=" + authorID +
                ", drive=" + drive +
                ", createdDate=" + createdDate +
                ", updateDate=" + updateDate +
                '}';
    }
}
