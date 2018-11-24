package cz.muni.fi.pa165.blablacar.api.dto;

import java.util.Objects;

public class ChangeCommentTextDTO {

    private Long commentId;
    private String newcontent;

    public Long getCommentId() {
        return commentId;
    }

    public void setCommentId(Long commentId) {
        this.commentId = commentId;
    }

    public String getNewcontent() {
        return newcontent;
    }

    public void setNewcontent(String newcontent) {
        this.newcontent = newcontent;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ChangeCommentTextDTO)) return false;
        ChangeCommentTextDTO that = (ChangeCommentTextDTO) o;
        return Objects.equals(getCommentId(), that.getCommentId()) &&
                Objects.equals(getNewcontent(), that.getNewcontent());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getCommentId(), getNewcontent());
    }

    @Override
    public String toString() {
        return "ChangeCommentTextDTO{" +
                "commentId=" + commentId +
                ", newcontent='" + newcontent + '\'' +
                '}';
    }
}
