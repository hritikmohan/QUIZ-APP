package com.myquiz.quizapp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myquiz.quizapp.Question;
import com.myquiz.quizapp.dao.QuestionDao;

@Service
public class QuestionService {

    @Autowired
    QuestionDao questionDao;

    public List<Question> getAllQuestions() {
        
        return questionDao.findAll();
    }

    public List<Question> getQuestionsByCategory(String category) {
        
        return questionDao.findByCategory(category);
    }

    public String addQuestion(Question question) {
        
        questionDao.save(question);
        return "success";
    }

    // public Question updateQuestion(Question newQuestion, Integer id) {
    //     return questionDao.findById(id)
    //             .map(existingQuestion -> {
    //                 existingQuestion.setQuestionTitle(newQuestion.getQuestionTitle());
    //                 existingQuestion.setOption1(newQuestion.getOption1());
    //                 existingQuestion.setOption2(newQuestion.getOption2());
    //                 existingQuestion.setOption3(newQuestion.getOption3());
    //                 existingQuestion.setOption4(newQuestion.getOption4());
    //                 existingQuestion.setRightAnswer(newQuestion.getRightAnswer());
    //                 existingQuestion.setDifficultylevel(newQuestion.getDifficultylevel());
    //                 existingQuestion.setCategory(newQuestion.getCategory());
    //                 return questionDao.save(existingQuestion);
    //             })
    //             .orElseGet(() -> {
    //                 newQuestion.setId(id);
    //                 return questionDao.save(newQuestion);
    //             });
    // }

    public Question updateQuestion(Question newQuestion, Integer id) {
        Optional<Question> optionalQuestion = questionDao.findById(id);

        if (optionalQuestion.isPresent()) {
            Question existingQuestion = optionalQuestion.get();
            existingQuestion.setQuestionTitle(newQuestion.getQuestionTitle());
            existingQuestion.setOption1(newQuestion.getOption1());
            existingQuestion.setOption2(newQuestion.getOption2());
            existingQuestion.setOption3(newQuestion.getOption3());
            existingQuestion.setOption4(newQuestion.getOption4());
            existingQuestion.setRightAnswer(newQuestion.getRightAnswer());
            existingQuestion.setDifficultylevel(newQuestion.getDifficultylevel());
            existingQuestion.setCategory(newQuestion.getCategory());

            return questionDao.save(existingQuestion);
        } else {
            // If question with the specified id does not exist
            throw new IllegalArgumentException("Question not found with id: " + id);
        }
    }
}