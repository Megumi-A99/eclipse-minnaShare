package model;

import java.util.List;

import dao.ArticleDAO;

public class ArticleListLogic {
	public List<Article> execute(String category) {
		ArticleDAO dao = new ArticleDAO();
        List<Article> articleList = dao.findByCategory(category);
        return articleList;
	}

}
