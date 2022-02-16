package com.nvfredy.shortlinks.model;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "links")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Link {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String longLink;
    private String shortLink;
    private LocalDateTime createdAt;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Link link = (Link) o;
        return id != null && Objects.equals(id, link.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
