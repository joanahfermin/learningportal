package com.kuyajon.learningportal.controllers.course;

import com.kuyajon.learningportal.dto.course.QuestionDTO;
import com.kuyajon.learningportal.model.course.AnswerChoice;
import com.kuyajon.learningportal.model.course.Question;
import com.kuyajon.learningportal.model.course.Test;
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

    @GetMapping
    public List<QuestionDTO> getAllQuestions() {
        // List<Question> questions = courseService.getAllQuestion();
        // List<QuestionDTO> result = new ArrayList<QuestionDTO>();

        // for (Question question : questions){
        // QuestionDTO questionDTO = convertToDTO(question);
        // result.add(questionDTO);
        // }
        // return result;
        return null;
    }

    @GetMapping("/{id}")
    public Optional<QuestionDTO> getQuestionById(@PathVariable Long id) {
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
    public List<QuestionDTO> getAllQuestionsByTestId(@PathVariable Long testId) {
        List<Question> questions = courseService.getQuestionsByTestId(testId);
        List<QuestionDTO> result = new ArrayList<QuestionDTO>();

        if (questions.isEmpty()) {
            throw new IllegalArgumentException("Question ID must not be null");
        }

        for (Question question : questions) {
            QuestionDTO questionDTO = convertToDTO(question);
            result.add(questionDTO);
        }
        return result;
    }

    @PostMapping("/test/{testId}")
    public QuestionDTO createQuestion(@RequestBody QuestionDTO questionDTO) {
        Optional<Test> testOptional = courseService.getTestByID(questionDTO.getTestId());

        Test retrievedTest = testOptional.get();
        Question question = new Question();
        question.setId(questionDTO.getId());
        question.setAnswer(AnswerChoice.valueOf(questionDTO.getAnswer()));
        question.setChoiceA(questionDTO.getChoiceA());
        question.setChoiceB(questionDTO.getChoiceB());
        question.setChoiceC(questionDTO.getChoiceC());
        question.setChoiceD(questionDTO.getChoiceD());
        question.setQuestionText(questionDTO.getQuestionText());
        question.setSolution(questionDTO.getSolution());
        question.setTest(retrievedTest);
        Question savedQuestion = courseService.saveOrUpdateQuestion(question);

        return convertToDTO(savedQuestion);
    }

    @PutMapping("/{id}")
    public QuestionDTO updateQuestion(@PathVariable Long id, @RequestBody QuestionDTO questionDTO) {
        Optional<Question> questionOptional = courseService.getQuestionByID(id);

        if (questionOptional.isPresent()) {
            Question question = questionOptional.get();
            question.setAnswer(AnswerChoice.valueOf(questionDTO.getAnswer()));
            question.setChoiceA(questionDTO.getChoiceA());
            question.setChoiceB(questionDTO.getChoiceB());
            question.setChoiceC(questionDTO.getChoiceC());
            question.setChoiceD(questionDTO.getChoiceD());
            question.setQuestionText(questionDTO.getQuestionText());
            question.setSolution(questionDTO.getSolution());
            question = courseService.saveOrUpdateQuestion(question);
            return convertToDTO(question);
        } else {
            throw new IllegalArgumentException("Test ID must not be null");
        }
    }

    @DeleteMapping("/{id}")
    public void deleteQuestion(@PathVariable Long id) {
        Optional<Question> questionOptional = courseService.getQuestionByID(id);
        if (questionOptional.isPresent()) {
            courseService.deleteQuestionById(id);
        } else {
            throw new IllegalArgumentException("No question ID found.");
        }
    }

    private QuestionDTO convertToDTO(Question question) {
        QuestionDTO questionDTO = new QuestionDTO();
        questionDTO.setId(question.getId());
        questionDTO.setAnswer(String.valueOf(question.getAnswer()));
        questionDTO.setChoiceA(question.getChoiceA());
        questionDTO.setChoiceB(question.getChoiceB());
        questionDTO.setChoiceC(question.getChoiceC());
        questionDTO.setChoiceD(question.getChoiceD());
        questionDTO.setQuestionText(question.getQuestionText());
        questionDTO.setSolution(question.getSolution());
        // questionDTO.setTestId(question.getTest().getId());

        if (question.getTest() != null && question.getTest().getId() != null) {
            questionDTO.setTestId(question.getTest().getId());
        } else {
            questionDTO.setTestId(null);
        }
        return questionDTO;
    }

}
