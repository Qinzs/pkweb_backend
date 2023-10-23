package com.pkweb.backend1.Entity;

import com.pkweb.backend1.Entity.User;
import jakarta.persistence.*;

@Entity
@Table(name = "user_points")
public class UserPoints {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(nullable = false)
    private int points;

    // 无参构造函数
    public UserPoints() {
    }

    // 带参构造函数
    public UserPoints(com.pkweb.backend1.Entity.User user, int points) {
        this.user = user;
        this.points = points;
    }

    // Getter 和 Setter 方法

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public com.pkweb.backend1.Entity.User getUser() {
        return user;
    }

    public void setUser(com.pkweb.backend1.Entity.User user) {
        this.user = user;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }
}