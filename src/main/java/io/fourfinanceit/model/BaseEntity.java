package io.fourfinanceit.model;

import javax.persistence.*;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

@MappedSuperclass
class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    public BaseEntity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (isNull(o) || getClass() != o.getClass()) return false;

        BaseEntity that = (BaseEntity) o;

        return nonNull(id) ? id.equals(that.id) : isNull(that.id);
    }

    @Override
    public int hashCode() {
        return nonNull(id) ? id.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "BaseEntity{" +
                "id=" + id +
                '}';
    }
}
