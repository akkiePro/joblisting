package com.akki.joblisting.repository;

import com.akki.joblisting.model.Post;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface PostRepository extends MongoRepository<Post, String> {

}
