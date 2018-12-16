package cz.muni.fi.pa165.blablacar.springMvc.security;

import cz.muni.fi.pa165.blablacar.api.dto.UserDTO;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Objects;

@Component
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class UserSession {

    private Long userId;
    private UserDTO user;
    private boolean userIsLoggedIn = false;
    private Date lastLoggedIn;
    private Right right = Right.NO_RIGHT;


    public void logInUser(Long userId, Right right, UserDTO user) {
        setUser(user);
        setUserId(userId);
        setUserIsLoggedIn(true);
        setRight(right);
        setLastLoggedIn(new Date());
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public boolean isUserIsLoggedIn() {
        return userIsLoggedIn;
    }

    public void setUserIsLoggedIn(boolean userIsLoggedIn) {
        this.userIsLoggedIn = userIsLoggedIn;
    }

    public Date getLastLoggedIn() {
        return lastLoggedIn;
    }

    public void setLastLoggedIn(Date lastLoggedIn) {
        this.lastLoggedIn = lastLoggedIn;
    }

    public Right getRight() {
        return right;
    }

    public void setRight(Right right) {
        this.right = right;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserSession)) return false;
        UserSession that = (UserSession) o;
        return Objects.equals(getUserId(), that.getUserId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUserId());
    }
}
