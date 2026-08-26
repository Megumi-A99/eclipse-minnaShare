package model;

import dao.ArticleDAO;

public class PostLogic {
    public boolean execute(Article article) {
        ArticleDAO dao = new ArticleDAO();
        return dao.insert(article);
    }
}