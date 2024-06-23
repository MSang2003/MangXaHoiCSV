/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nms.controllers;

import com.nms.pojo.Invitations;
import com.nms.pojo.Posts;
import com.nms.pojo.Surveyoptions;
import com.nms.pojo.Surveys;
import com.nms.pojo.Users;
import com.nms.services.PostService;
import com.nms.services.StatsService;
import com.nms.services.UsersSevice;
import java.security.Principal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Admin
 */
@CrossOrigin(origins = {"*"})
@RestController
@RequestMapping("/api")
public class ApiPostController {

    @Autowired
    private PostService postService;

    @Autowired
    private UsersSevice userService;

    @Autowired
    private StatsService statsService;

    @GetMapping(path = "/posts/")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Map<String, Object>> getPosts(@RequestParam Map<String, String> params, Principal p) {
        Boolean isProfile = Boolean.valueOf(params.get("isProfile"));

        System.out.println(isProfile);
        Integer pageNumber = Integer.valueOf(params.get("pageNumber"));
        Users u = this.userService.getUserByUsername(p.getName());
        Map<String, Object> posts = this.postService.getPosts(u.getUserID(), pageNumber, isProfile);
        return ResponseEntity.ok(posts);
    }

    @DeleteMapping("/post/{postID}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(Model model, @PathVariable(value = "postID") int id) {
        this.postService.deletePostById(id);
    }

    @PostMapping(path = "/posts/", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<String> createPost(@RequestParam Map<String, Object> params, @RequestPart MultipartFile[] file, Principal principal) {
        String postType = (String) params.get("postType");

        Posts p = new Posts();
        p.setContent((String) params.get("content"));
        p.setPostType((String) params.get("postType"));
        p.setIsCommentLocked(Boolean.FALSE);
        p.setCreatedAt(new Date());
        p.setUpdatedAt(new Date());
        if (file.length > 0) {
            p.setImageFile(file[0]);
        }

        String username = principal.getName();
        Users user = userService.getUserByUsername(username);
        p.setUserID(user);

        if (null == postType) {
            return ResponseEntity.badRequest().body("Invalid post type");
        } else {
            switch (postType) {
                case "Status":
                    this.postService.createOrUpdateStatusPost(p);
                    break;
                case "Invitation":
                    Invitations invitation = new Invitations();

                    invitation.setEventDetails((String) params.get("enventDetails"));
                    invitation.setCreatedAt(new Date());
                    invitation.setUpdatedAt(new Date());
                    invitation.setPostID(p);
                    break;
                default:
                    return ResponseEntity.badRequest().body("Invalid post type");
            }
        }

        return ResponseEntity.ok("Post created successfully");
    }

    @PostMapping(path = "/posts/create_survey/", consumes = {
        MediaType.APPLICATION_JSON_VALUE,})
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<String> createSurveyPost(@RequestBody Map<String, Object> params, Principal principal) {
        String postType = (String) params.get("postType");

        Posts p = new Posts();
        p.setContent((String) params.get("content"));
        p.setPostType(postType);
        p.setCreatedAt(new Date());
        p.setUpdatedAt(new Date());

        String username = principal.getName();
        Users user = userService.getUserByUsername(username);
        p.setUserID(user);

        if (postType == null) {
            return ResponseEntity.badRequest().body("Invalid post type");
        } else {
            Surveys survey = new Surveys();
            survey.setQuestion((String) params.get("question"));
            survey.setCreatedAt(new Date());
            survey.setUpdatedAt(new Date());
            survey.setPostID(p);

            List<Surveyoptions> listOption = new ArrayList<>();

            List<String> surveyOptions = (List<String>) params.get("surveyOptions");
            if (surveyOptions != null) {
                for (String optionText : surveyOptions) {
                    Surveyoptions option = new Surveyoptions();
                    option.setOptionText(optionText);
                    option.setSurveyID(survey);
                    listOption.add(option);
                }
            }

            this.postService.createInvitationPost(p, survey, listOption);
            return ResponseEntity.ok("Post created successfully");
        }
    }

    @PatchMapping(path = "/posts/{id}", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<String> updatePost(@RequestBody Map<String, String> params, @PathVariable(value = "id") int id, Principal principal) {

        Posts p = this.postService.getPostById(id);

        Users u = this.userService.getUserByUsername(principal.getName());

        if (Objects.equals(p.getUserID().getUserID(), u.getUserID())) {
            String content = params.get("content");
            if (content != null) {
                p.setContent(content);
            }

            Boolean isCommentLocked = Boolean.parseBoolean(params.get("isCommentLocked"));

            if (isCommentLocked != null) {
                p.setIsCommentLocked(isCommentLocked);
            }
            p.setUpdatedAt(new Date());

            this.postService.createOrUpdateStatusPost(p);

            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized to update this post");
    }

    @GetMapping(path = "/posts/{surveyID}/stats_survey/")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<List<Map<String, Object>>> getStatsSurvey(@PathVariable(value = "surveyID") int surveyID, Principal p) {
        List<Map<String, Object>> data = this.statsService.statsSurveyResponses(surveyID);
        return ResponseEntity.ok(data);
    }
}
