package com.nvfredy.shortlinks.repository;

import com.nvfredy.shortlinks.model.Link;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LinkRepository extends JpaRepository<Link, Long> {

    Optional<Link> findByShortLink(String shortLink);
    Link findByLongLink(String longLink);
}
