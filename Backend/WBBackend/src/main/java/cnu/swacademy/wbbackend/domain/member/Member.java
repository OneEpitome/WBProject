package cnu.swacademy.wbbackend.domain.member;

import cnu.swacademy.wbbackend.domain.review.Review;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter @Setter @EqualsAndHashCode(of = {"id"})
@NoArgsConstructor
@Entity(name = "member")
@Table(name="member")
public class Member {
    @Id
    @Column(name = "id", nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @Column(name = "password", nullable = false, unique = true)
    private String password;

    @Column(name = "nickname", nullable = false, unique = true)
    private String nickname;

    @Column(name = "authority", nullable = false)
    private String authority;

    @JsonManagedReference
    @OneToMany(mappedBy = "writer", fetch = FetchType.EAGER, orphanRemoval = true)
    private List<Review> reviewList;

    public Member(String username, String password, String nickname, String authority) {
        this.username = username;
        this.password = password;
        this.nickname = nickname;
        this.authority = authority;
    }
}
