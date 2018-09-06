package com.vicky.fsblog.mapper.provider;

import com.vicky.fsblog.entity.dto.form.ArticleSearchForm;
import org.apache.ibatis.jdbc.SQL;

/**
 * Article 动态 SQL 提供者
 *
 * @author James
 */
public class ArticleSqlProvider {

  public String getArticleByCondition(ArticleSearchForm form) {
    return new SQL() {{
      SELECT("article.id,title,introduction,article.gmt_create AS gmtCreate,article.gmt_modified AS gmtModified");
      FROM("article");
      if (form.getName() != null) {
        WHERE("article.title LIKE CONCAT ('%','" + form.getName() + "','%')");
      }
    }}.toString();
  }
}
