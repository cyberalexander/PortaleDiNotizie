package by.leonovich.notizieportale.domain;

import by.leonovich.notizieportale.domain.enums.StatusEnum;
import org.hibernate.annotations.*;
import org.hibernate.annotations.Cache;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Set;

/**
 * Created by alexanderleonovich on 19.05.15.
 * in 23:45:00
 */
@Entity
@Table(name = "T_CATEGORY")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region = "category")
public class Category extends CustomEntity{
    private static final long serialVersionUID = -9165585069001592520L;

    @Id
    @Column(name = "F_CATEGORY_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long categoryId;

    @Column(name = "F_CATEGORY_NAME")
    private String categoryName;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<News> news;

    public Category() {
        super();
    }

    public Category(String categoryName, StatusEnum status) {
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
        if (!(obj instanceof Category)) return false;
        Category category = (Category) obj;
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
