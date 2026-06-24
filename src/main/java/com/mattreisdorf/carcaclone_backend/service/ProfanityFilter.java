package com.mattreisdorf.carcaclone_backend.service;

import java.io.IOException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.springframework.core.io.Resource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.annotation.PostConstruct;

// DISCLAIMER
// I don't condone anything in the profanities.json
// List is taken from zacanger's "profane-words" npm package
// Github link is here: https://github.com/zacanger/profane-words/tree/master

@Component
public class ProfanityFilter {

  private Pattern pattern;

  @Value("classpath:profanities.json")
  private Resource profanitiesResource;

  @PostConstruct
  public void init() throws IOException {
    ObjectMapper mapper = new ObjectMapper();
    // readValue will return a List<String> from your JSON array
    List<String> banned = mapper.readValue(
        profanitiesResource.getInputStream(),
        new TypeReference<List<String>>() {
        });

    String joined = banned.stream()
        .map(Pattern::quote)
        .collect(Collectors.joining("|"));
    // String regex = "\\b(" + joined + ")\\b";
    // String regex = "(" + joined + ")";
    String regex = "(?i)(?<![A-Za-z])(" + joined + ")(?:\\1)*(?![A-Za-z])";
    pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
  }

  public String filter(String input) {
    if (input == null || input.isEmpty())
      return input;

    Matcher m = pattern.matcher(input);
    StringBuffer sb = new StringBuffer();
    while (m.find()) {
      String word = m.group();
      m.appendReplacement(sb, "*".repeat(word.length()));
    }
    m.appendTail(sb);
    return sb.toString();
  }
}
