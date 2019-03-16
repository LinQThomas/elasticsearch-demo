package com.jdkhome.basic.service;

import com.jdkhome.basic.pojo.ArticleDTO;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: w
 * CreatedTime: 2018/9/7 下午2:12
 * Description:
 */
public interface ArticleEsService {

    List<ArticleDTO> getAllArticle(String summary);

    void addArticle(String summary,String content);

    void deleteArticle();
}
