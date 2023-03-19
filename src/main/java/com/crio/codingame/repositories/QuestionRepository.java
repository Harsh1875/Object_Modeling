package com.crio.codingame.repositories;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import com.crio.codingame.entities.Level;
import com.crio.codingame.entities.Question;

public class QuestionRepository implements IQuestionRepository {

    private final Map<String,Question> questionMap;
    private Integer autoIncrement = 0;

    public QuestionRepository(){
        questionMap = new HashMap<String,Question>();
    }

    public QuestionRepository(Map<String, Question> questionMap) {
        this.questionMap = questionMap;
        this.autoIncrement = questionMap.size();
    }

    @Override
    public Question save(Question entity) {
        if( entity.getId() == null ){
            autoIncrement++;
            Question q = new Question(Integer.toString(autoIncrement),entity.getTitle(),entity.getLevel(),entity.getScore());
            questionMap.put(q.getId(),q);
            return q;
        }
        questionMap.put(entity.getId(),entity);
        return entity;
    }

    @Override
    public List<Question> findAll() {
        List<Question> allQuestions = new ArrayList<>();
        for (Map.Entry<String, Question> q : questionMap.entrySet()) {
            allQuestions.add(q.getValue());
        }
        return allQuestions;
    };

    @Override
    public Optional<Question> findById(String id) {
        return Optional.ofNullable(questionMap.get(id));
    }

    @Override
    public boolean existsById(String id) {
        // TODO Auto-generated method stub
        return questionMap.containsKey(id);
    }

    @Override
    public void delete(Question entity) {
        // TODO Auto-generated method stub
        questionMap.remove(entity.getId(), entity);
    }

    @Override
    public void deleteById(String id) {
        // TODO Auto-generated method stub
        questionMap.remove(id);
    }

    @Override
    public long count() {
        // TODO Auto-generated method stub
        return questionMap.size();
    }

    @Override
    public List<Question> findAllQuestionLevelWise(Level level) {
        List<Question> allLevel = questionMap.values().stream().collect(Collectors.toList());

        return allLevel.stream().filter(obj -> level == obj.getLevel()).collect(Collectors.toList());
    }
    
}
