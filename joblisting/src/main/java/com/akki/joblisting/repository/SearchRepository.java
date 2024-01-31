package com.akki.joblisting.repository;

import com.akki.joblisting.model.Post;

import java.util.List;

public interface SearchRepository {
    List<Post> findByText(String text);
}
