package cz.muni.fi.pa165.blablacar.api.dto.comment;

import javax.validation.constraints.NotNull;
import java.util.Objects;

public class ChangeCommentContentDTO {

    @NotNull
    private Long commentId;

    @NotNull
    private String changedContent;

    public Long getCommentId() {
        return commentId;
    }

    public void setCommentId(Long commentId) {
        this.commentId = commentId;
    }

    public String getChangedContent() {
        return changedContent;
    }

    public void setChangedContent(String changedContent) {
        this.changedContent = changedContent;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ChangeCommentContentDTO)) return false;
        ChangeCommentContentDTO that = (ChangeCommentContentDTO) o;
        return Objects.equals(getCommentId(), that.getCommentId()) &&
                Objects.equals(getChangedContent(), that.getChangedContent());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getCommentId(), getChangedContent());
    }

    @Override
    public String toString() {
        return "ChangeCommentContentDTO{" +
                "commentId=" + commentId +
                ", changedContent='" + changedContent + '\'' +
                '}';
    }
}
