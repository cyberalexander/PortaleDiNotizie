package by.leonovich.notizieportale.domain;

import by.leonovich.notizieportale.domain.util.StatusEnum;
import org.hibernate.annotations.GeneratorType;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

/**
 * Created by alexanderleonovich on 19.05.15.
 * in 23:45:00
 */
@Entity
@Table(name = "T_CATEGORY")
public class Category extends CustomEntity implements Serializable{
    private static final long serialVersionUID = 7238251066050359830L;

    @Id
    @Column(name = "F_CATEGORY_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long categoryId;

    @Column(name = "F_CATEGORY_NAME")
    private String categoryName;

    /*@Column(name = "F_STATUS")
    @Enumerated(EnumType.STRING)
    private StatusEnum status;*/

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    private Set<News> news;

    public Category() {
    }

    public Category(String categoryName, StatusEnum status) {
        super();
        this.categoryName = categoryName;
        super.status = status;
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

    /*public StatusEnum getStatus() {
        return super.status;
    }

    public void setStatus(StatusEnum status) {
        super.status = status;
    }*/

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
        if (categoryId != null ? !categoryId.equals(category.categoryId) : category.categoryId != null) return false;
        if (categoryName != null ? !categoryName.equals(category.categoryName) : category.categoryName != null) return false;
        return true;
    }

    @Override
    public int hashCode() {
        int result = categoryId != null ? categoryId.hashCode() : 0;
        result = 31 * result + (categoryName != null ? categoryName.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Category{" +
                "categoryId=" + categoryId +
                ", categoryName='" + categoryName + '\'' +
                ", status=" + super.status +
                '}';
    }
}
