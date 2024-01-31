package com.akki.joblisting.controller;

import com.akki.joblisting.model.Post;
import com.akki.joblisting.repository.PostRepository;
import com.akki.joblisting.repository.SearchRepository;
import com.mongodb.client.AggregateIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.convert.MongoConverter;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class PostController {

    @Autowired
    MongoClient client;

    @Autowired
    MongoConverter converter;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private SearchRepository searchRepository;

    @ApiIgnore
    @RequestMapping("/")
    public void redirect(HttpServletResponse response) throws IOException {
        response.sendRedirect("/swagger-ui.html");
    }

    @GetMapping("/posts")
    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    @GetMapping("/posts/{text}")
    public List<Post> search(@PathVariable String text) {
        final List<Post> matchedPosts = new ArrayList<>();

        MongoDatabase database = client.getDatabase("akki");
        MongoCollection<Document> collection = database.getCollection("JobPost");
        AggregateIterable<Document> result = collection.aggregate(Arrays.asList(new Document("$search",
                        new Document("text",
                        new Document("query", text)
                        .append("path", Arrays.asList("techs", "desc", "profile")))),
                        new Document("$sort",
                        new Document("exp", -1L)),
                        new Document("$limit", 5L)));

        result.forEach(doc -> matchedPosts.add(converter.read(Post.class, doc)));

        return matchedPosts;
    }

    @PostMapping("/post")
    public Post addPost(@RequestBody Post post) {
        return postRepository.save(post);
    }

}
