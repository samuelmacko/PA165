package cz.muni.fi.pa165.blablacar.api.dto;

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
    private DriveDTO driveID;

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

    public DriveDTO getDriveID() {
        return driveID;
    }

    public void setDriveID(DriveDTO driveID) {
        this.driveID = driveID;
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
                Objects.equals(getDriveID(), that.getDriveID()) &&
                Objects.equals(getCreatedDate(), that.getCreatedDate()) &&
                Objects.equals(getUpdateDate(), that.getUpdateDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getContent(), getAuthorID(), getDriveID(), getCreatedDate(), getUpdateDate());
    }

    @Override
    public String toString() {
        return "CommentDTO{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", authorID=" + authorID +
                ", driveID=" + driveID +
                ", createdDate=" + createdDate +
                ", updateDate=" + updateDate +
                '}';
    }
}
