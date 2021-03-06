package pl.coderslab.socialmedia.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
public class User {

    @Id @GeneratedValue (strategy = GenerationType.AUTO)
    @Getter @Setter
    private Long id;

    @Getter @Setter
    @NotNull
    @Column (unique = true)
    private String username;

    @Getter @Setter
    @Column (unique = true)
    private String email;

    @Getter @Setter
    @NotNull
    private String firstName;

    @Getter @Setter
    @NotNull
    private String password;

    @Getter @Setter
    @ManyToMany (mappedBy = "members")
    private List<Group> groups;

    @Getter @Setter
    @OneToMany (mappedBy = "author")
    private List<Tweet> tweets;

    @Getter @Setter
    @OneToMany (mappedBy = "author")
    private List<Comment> comments;

    @Getter @Setter
    @OneToMany
    private List<User> following;

    @Getter @Setter
    @OneToMany
    private List<User> followers;

    @Getter @Setter
    private String avatarPath;

    @OneToMany (cascade = CascadeType.MERGE, mappedBy = "sender")
    private List<Message> sent;

    public void addToSent(Message message){
        sent.add(message);
    }

    @OneToMany (cascade = CascadeType.MERGE, mappedBy = "reciever")
    private List<Message> recieved;

    @Getter @Setter
    @Temporal(TemporalType.TIMESTAMP)
    private Date registered;

    @Getter @Setter
    private boolean active;

    @ManyToMany (fetch = FetchType.EAGER)
    @Getter @Setter
    private Set<Role> roles;

    @OneToMany
    @Getter @Setter
    private List<Tweet> likedTweets;

    public User() {
        this.comments=new ArrayList<>();
        this.tweets =new ArrayList<>();
        this.groups=new ArrayList<>();
        this.followers=new ArrayList<>();
        this.following=new ArrayList<>();
        this.sent=new ArrayList<>();
        this.recieved=new ArrayList<>();
        this.registered=new Date();
        this.avatarPath="avatars/anonymous.png";
        this.active=true;
    }
}
