package com.akki.joblisting.repository;

import com.akki.joblisting.model.Post;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SearchRepositoryImpl implements SearchRepository {
    @Override
    public List<Post> findByText(String text) {
        final List<Post> matchedPosts = new ArrayList<>();

        return matchedPosts;
    }
}
