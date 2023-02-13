package ro.softwarelabz.smartdriving.user.domain;


import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

@Data
@Entity
@Table(name = "users")
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(unique = true)
    private String sub;
    @Column(name ="first_name")
    private String firstName;
    @Column(name ="last_name")
    private String lastName;
    @Column(unique = true)
    private String email;
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateAt;

    public static final class Fields {
        public static final String SUB = "sub";
        public static final String FIRST_NAME = "given_name";
        public static final String LAST_NAME = "family_name";
        public static final String EMAIL = "email";
        public static final String EMAIL_VERIFIED = "email_verified";
        public static final String CLAIMS = "claims";
    }

    public static User from(Map<String, Object> claims) {
        var user = new User();

        if (claims.containsKey(Fields.SUB)) {
            user.setSub((String) claims.get(Fields.SUB));
        }

        if (claims.containsKey(Fields.FIRST_NAME)) {
            user.setFirstName((String) claims.get(Fields.FIRST_NAME));
        }

        if (claims.containsKey(Fields.LAST_NAME)) {
            user.setLastName((String) claims.get(Fields.LAST_NAME));
        }

        if (claims.containsKey(Fields.EMAIL)) {
            user.setEmail((String) claims.get(Fields.EMAIL));
        }

        return user;
    }
}


