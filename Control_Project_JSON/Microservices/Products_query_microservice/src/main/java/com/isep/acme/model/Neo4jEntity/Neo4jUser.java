package com.isep.acme.model.Neo4jEntity;

import com.isep.acme.model.Role;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Profile;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Profile("neo4jProfile")
@Node
public class Neo4jUser implements UserDetails {
    @Id
    @GeneratedValue
    private Long userId;

    private String username;

    private String password;

    private String fullName;


    private Set<Role> authorities = new HashSet<>();

    private String nif;


    private String morada;

/*    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Review> review = new ArrayList<Review>(); */

    protected Neo4jUser() {
    }

    public Neo4jUser(final String username, final String password) {
        this.username = username;
        this.password = password;
    }


    public Neo4jUser(final String username, final String password, final String fullName, final String nif, final String morada) {
        this.username = username;
        this.password = password;
        this.fullName = fullName;
        setNif(nif);
        this.morada = morada;
    }

    public Neo4jUser(final Long userId, final String username, final String password, final String fullName, final String nif, final String morada) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.fullName = fullName;
        setNif(nif);
        this.morada = morada;
    }

    public void addAuthority(Role r) {
        authorities.add(r);
    }

    public void setNif(String nif) {
        if (nif.length() != 9) {
            throw new IllegalArgumentException("NIF must be 9 characters.");
        }
        this.nif = nif;
    }



    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Neo4jUser{");
        sb.append("userId=").append(userId);
        sb.append(", username='").append(username).append('\'');
        sb.append(", password='").append(password).append('\'');
        sb.append(", fullName='").append(fullName).append('\'');
        sb.append(", authorities=").append(authorities);
        sb.append(", nif='").append(nif).append('\'');
        sb.append(", morada='").append(morada).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
