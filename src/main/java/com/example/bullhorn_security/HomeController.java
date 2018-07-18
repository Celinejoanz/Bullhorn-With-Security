package com.example.bullhorn_security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
public class HomeController {

    @Autowired
    TweetRepository tweetRepository;

    @RequestMapping("/login")
    public String home(Model model){
        model.addAttribute("tweets", tweetRepository.findAll());
        return "login";
    }

    @RequestMapping("/home")
    public String listTweets(Model model){
        model.addAttribute("tweets", tweetRepository.findAll());
        return "index";
    }

    @RequestMapping("/logout")
    public String admin(Model model){
        model.addAttribute("tweets", tweetRepository.findAll());
        return "redirect:/login";
    }

    @GetMapping("/add")
    public String tweetform(Model model){
        model.addAttribute("tweet", new Tweet());
        return "tweetform";
    }

    @PostMapping("/process")
    public String processtweetform(@Valid Tweet tweet, BindingResult result){
        if (result.hasErrors()){
            return "tweetform";
        }
        tweetRepository.save(tweet);
        return "redirect:/home";
    }

    @RequestMapping("/detail/{id}")
    public String showTweet(@PathVariable("id") long id, Model model){
        model.addAttribute("tweet", tweetRepository.findById(id).get());
        return "show";
    }
    @RequestMapping("/update/{id}")
    public String updateTweet(@PathVariable("id") long id, Model model){
        model.addAttribute("tweet", tweetRepository.findById(id));
        return "tweetform";
    }
    @RequestMapping("/delete/{id}")
    public String delTweet(@PathVariable("id") long id){
        tweetRepository.deleteById(id);
        return "redirect:/home";
    }
}
