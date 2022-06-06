package com.example.tourplanner.viewmodel;

import com.example.tourplanner.view.validation.InputValidation;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
@TestInstance(TestInstance.Lifecycle.PER_CLASS)

public class IntInputValidationTest {
    StringProperty rating;
    InputValidation inputValidation;

    @BeforeAll
    public void setup() {
       rating = new SimpleStringProperty();
       inputValidation  = new InputValidation();
    }

    @Test
    public void inputValidationTestAssertFalse(){
        this.rating.set("45");
        assertFalse( inputValidation.validateInt(rating));
    }

    @Test
    public void inputValidationTestAssertTrue(){
        rating.set("4");
        assertTrue( inputValidation.validateInt(rating));
    }





}
