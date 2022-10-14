package com.codevui.realworldapp.repository.criteria;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.codevui.realworldapp.entity.Article;
import com.codevui.realworldapp.model.article.dto.ArticleDTOFilter;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class ArticleCriteria {
    private final EntityManager em;

    public Map<String, Object> findAll(ArticleDTOFilter filter) {
        StringBuilder query = new StringBuilder(
                "select a from Article a inner join a.author au left join a.userFavorited uf where 1=1");
        Map<String, Object> params = new HashMap<String, Object>();

        if (filter.getTag() != null) {
            query.append(" and a.tagList like :tag");
            params.put("tag", "%" + filter.getTag() + "%");
        }
        if (filter.getAuthor() != null) {
            query.append(" and au.username = :author");
            params.put("author", filter.getAuthor());
        }
        if (filter.getFavorited() != null) {
            query.append(" and uf.username = :favorited");
            params.put("favorited", filter.getFavorited());
        }

        Query countTotalQuery = em.createQuery(query.toString().replace("select a", "select count(a.id)"));
        query.append(" order by a.id desc");
        TypedQuery<Article> tQuery = em.createQuery(query.toString(), Article.class);

        params.forEach((k, v) -> {
            tQuery.setParameter(k, v);
            countTotalQuery.setParameter(k, v);
        });

        tQuery.setFirstResult(filter.getOffset());
        tQuery.setMaxResults(filter.getLimit());

        long totalArtical = (long) countTotalQuery.getSingleResult();
        List<Article> listArticle = tQuery.getResultList();

        Map<String, Object> wrapper = new HashMap<String, Object>();
        wrapper.put("articlesCount", totalArtical);
        wrapper.put("listArticle", listArticle);
        return wrapper;
    }

    public Map<String, Object> getNewFeed(List<String> listUsername, ArticleDTOFilter filter) {

        StringBuilder query = new StringBuilder("select a from Article a where a.author.username in (:followers)");

        Query countTotalQuery = em.createQuery(query.toString().replace("select a", "select count(a.id)"));
        query.append(" order by a.createdAt DESC");
        TypedQuery<Article> tQuery = em.createQuery(query.toString(), Article.class);
        tQuery.setParameter("followers", listUsername);
        countTotalQuery.setParameter("followers", listUsername);

        tQuery.setFirstResult(filter.getOffset());
        tQuery.setMaxResults(filter.getLimit());

        long totalArtical = (long) countTotalQuery.getSingleResult();
        List<Article> listArticle = tQuery.getResultList();

        Map<String, Object> wrapper = new HashMap<String, Object>();
        wrapper.put("articlesCount", totalArtical);
        wrapper.put("listArticle", listArticle);
        return wrapper;
    }

}
