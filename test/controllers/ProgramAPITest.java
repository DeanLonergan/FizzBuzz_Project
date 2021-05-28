package controllers;

import models.Word;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProgramAPITest {

    private ProgramAPI pgmAPI;

    @BeforeEach
    void setUp() {
        pgmAPI.addWords(new Word("Test (Replace 5)", 5));
        pgmAPI.addWords(new Word("Test (Replace 10)", 10));
        pgmAPI.addWords(new Word("Test (Replace 15)", 15));
        pgmAPI.setSize(100);
    }

    @Test
    void addWords() {
    }

    @Test
    void setSize() {
    }

    @Test
    void fizzBuzz() {
    }
}