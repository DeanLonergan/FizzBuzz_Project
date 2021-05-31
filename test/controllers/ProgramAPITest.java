package controllers;

import models.Word;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProgramAPITest {
    private final ProgramAPI pgmAPI = new ProgramAPI();

    @BeforeEach
    void setUp() {
        pgmAPI.addWord(new Word("Test (Replace 5)", 5));
        pgmAPI.addWord(new Word("Test (Replace 10)", 10));
        pgmAPI.addWord(new Word("Test (Replace 15)", 15));
        pgmAPI.setFizzBuzzLength(150);
    }

    @AfterEach
    public void tearDown() throws Exception {
        //TODO
    }

    @Test
    void setFizzBuzzLength() {
        assertEquals(150, pgmAPI.getFizzBuzzLength());
        pgmAPI.setFizzBuzzLength(100);
        assertEquals(100, pgmAPI.getFizzBuzzLength());
    }

    @Test
    void addWord() {
        //TODO
    }

    @Test
    void fizzBuzz() {
        //TODO
    }
}