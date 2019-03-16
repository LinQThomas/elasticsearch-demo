package com.jdkhome.basic.controller;

import com.jdkhome.basic.common.response.ApiResponse;
import com.jdkhome.basic.pojo.ArticleDTO;
import com.jdkhome.basic.service.ArticleEsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: w
 * CreatedTime: 2018/9/5 下午2:42
 * Description:
 */
@RestController
@RequestMapping("/api/elasticsearch")
public class ElasticsearchController {


    @Autowired
    private ArticleEsService articleEsService;

    @RequestMapping(value = "/article_list", method = RequestMethod.POST)
    public ApiResponse apiArticleList(@RequestParam(value = "summary", required = false) String summary) {

        List<ArticleDTO> allArticle = articleEsService.getAllArticle(summary);

        return ApiResponse.successResponse(allArticle);
    }

    @RequestMapping(value = "/article_add", method = RequestMethod.POST)
    public ApiResponse apiAddArticle() {


        List<ArticleDTO> list = List.of(
                new ArticleDTO("冬瓜", "这是冬瓜"),
                new ArticleDTO("西瓜", "这是西瓜"),
                new ArticleDTO("南瓜", "这是南瓜"),
                new ArticleDTO("苦瓜", "这是苦瓜"),
                new ArticleDTO("西瓜霜", "这是西瓜霜"),
                new ArticleDTO("苦瓜炒饭", "这是苦瓜炒饭"));

        list.stream().forEach(articleDTO -> {
            articleEsService.addArticle(articleDTO.getSummary(), articleDTO.getContent());

        });

        return ApiResponse.successResponse(null);
    }

    @RequestMapping(value = "/article_delete", method = RequestMethod.POST)
    public ApiResponse apiDeleteArticle() {

        articleEsService.deleteArticle();

        return ApiResponse.successResponse(null);
    }

}
