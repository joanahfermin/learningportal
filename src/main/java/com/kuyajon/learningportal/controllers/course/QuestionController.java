package com.kuyajon.learningportal.controllers.course;

import com.kuyajon.learningportal.dto.course.QuestionDTO;
import com.kuyajon.learningportal.model.course.Question;
import com.kuyajon.learningportal.repository.course.QuestionRepository;
import com.kuyajon.learningportal.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/question")
@CrossOrigin(origins = "*")
public class QuestionController {
    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private CourseService courseService;

    //getAllQuestions - done
    //getQuestionById - done
    //getAllQuestionsByTestId - done
    //createQuestion
    //updateQuestion
    //deleteQuestion

    @GetMapping
    public List<QuestionDTO> getAllQuestions (){
        List<Question> questions = courseService.getAllQuestion();
        List<QuestionDTO> result = new ArrayList<QuestionDTO>();

        for (Question question : questions){
            QuestionDTO questionDTO = convertToDTO(question);
            result.add(questionDTO);
        }
        return result;
    }

    @GetMapping("/{id}")
    public Optional<QuestionDTO> getQuestionById (@PathVariable Long id){
        Optional<Question> optionalQuestion = courseService.getQuestionByID(id);

        if (optionalQuestion.isPresent()) {
            Question question = optionalQuestion.get();
            QuestionDTO questionDTO = convertToDTO(question);
            return Optional.of(questionDTO);
        } else {
            throw new IllegalArgumentException("Question ID must not be null");
        }
    }

    @GetMapping("/test/{testId}")
    public List<QuestionDTO> getAllQuestionsByTestId (@PathVariable Long testId){
        List<Question> questions = courseService.getQuestionsByTestId(testId);
        List<QuestionDTO> result = new ArrayList<QuestionDTO>();

        if (questions.isEmpty()) {
            throw new IllegalArgumentException("Question ID must not be null");
        }

        for (Question question : questions){
            QuestionDTO questionDTO = convertToDTO(question);
            result.add(questionDTO);
        }
        return result;
    }

    private QuestionDTO convertToDTO(Question question){
        QuestionDTO questionDTO = new QuestionDTO();
        questionDTO.setId(question.getId());
        questionDTO.setAnswer(String.valueOf(question.getAnswer()));
        questionDTO.setChoiceA(question.getChoiceA());
        questionDTO.setChoiceB(question.getChoiceB());
        questionDTO.setChoiceC(question.getChoiceC());
        questionDTO.setChoiceD(question.getChoiceD());
        questionDTO.setQuestionTest(question.getQuestionText());
        questionDTO.setSolution(question.getSolution());
        questionDTO.setTestId(question.getId());
        return questionDTO;
    }

}
