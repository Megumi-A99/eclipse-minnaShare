package model;

import dao.ArticleDAO;

public class ArticleDetailLogic {
    public Article execute(int articleId) {
        ArticleDAO dao = new ArticleDAO();
        return dao.findById(articleId); // DAOの検索結果をそのままServletに返す
    }
}