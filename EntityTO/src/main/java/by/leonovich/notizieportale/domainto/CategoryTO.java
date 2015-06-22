package by.leonovich.notizieportale.domainto;

import by.leonovich.notizieportale.domain.News;
import by.leonovich.notizieportale.domain.enums.StatusEnum;

import java.io.Serializable;
import java.util.Set;

/**
 * Created by alexanderleonovich on 19.06.15.
 */
public class CategoryTO extends CustomTobject implements Serializable {
    private static final long serialVersionUID = -6549255257131636141L;

    private Long categoryId;
    private String categoryName;

    private Set<News> news;

    public CategoryTO() {
        super();
    }

    public CategoryTO(String categoryName, StatusEnum status) {
        super(status);
        this.categoryName = categoryName;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Set<News> getNews() {
        return news;
    }

    public void setNews(Set<News> newses) {
        this.news = newses;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof CategoryTO)) return false;
        CategoryTO category = (CategoryTO) obj;
        if (categoryName != null ? !categoryName.equals(category.categoryName) : category.categoryName != null) return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 37;
        int result = 17;
        result = prime * result + (categoryName == null ? 0 : categoryName.hashCode());
        return result;
    }

    @Override
    public String toString() {
        return "Category{" +
                "categoryId=" + categoryId +
                ", categoryName='" + categoryName + '\'' +
                ", status=" + status +
                '}';
    }
}
