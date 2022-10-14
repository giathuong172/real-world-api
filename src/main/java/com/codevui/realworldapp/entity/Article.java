package com.codevui.realworldapp.entity;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "article_tbl")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String slug;
    private String title;
    private String description;
    private String body;

    @Type(type = "text")
    private String tagList;
    private Date createdAt;
    private Date updatedAt;
    private int favoriteCount;

    public List<String> getTagList() {
        return Arrays.asList(this.tagList.split(";"));
    }

    public void setTagList(List<String> tagList) {
        StringBuilder str = new StringBuilder();
        for (String tag : tagList) {
            str.append(tag).append(";");
        }
        this.tagList = str.substring(0, str.length() - 1).toString();
    }

    @ManyToOne
    @JoinColumn(name = "author_id")
    private User author;

    @ManyToMany(mappedBy = "articleFavorited")
    private Set<User> userFavorited;

    @OneToMany(mappedBy = "article")
    private List<Comment> comments;
}
