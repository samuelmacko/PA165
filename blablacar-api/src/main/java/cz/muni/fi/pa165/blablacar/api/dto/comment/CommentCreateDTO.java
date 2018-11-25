package cz.muni.fi.pa165.blablacar.api.dto.comment;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Objects;

public class CommentCreateDTO {

    @NotNull
    @Size(min = 1, max = 4096)
    private String content;

    @NotNull
    private Long authorId;
    @NotNull
    private Long driveId;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }

    public Long getDriveId() {
        return driveId;
    }

    public void setDriveId(Long driveId) {
        this.driveId = driveId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CommentCreateDTO)) return false;
        CommentCreateDTO that = (CommentCreateDTO) o;
        return Objects.equals(getContent(), that.getContent()) &&
                Objects.equals(getAuthorId(), that.getAuthorId()) &&
                Objects.equals(getDriveId(), that.getDriveId());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getContent(), getAuthorId(), getDriveId());
    }

    @Override
    public String toString() {
        return "CommentCreateDTO{" +
                "content='" + content + '\'' +
                ", authorId=" + authorId +
                ", driveId=" + driveId +
                '}';
    }
}
