package com.example.tourplanner.viewmodel;

import com.example.tourplanner.view.validation.InputValidation;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class IntInputValidationTest {


    @Test
    public void inputValidationTestAssertFalse(){
        StringProperty rating = new SimpleStringProperty();
        rating.set("45");

        InputValidation inputValidation  = new InputValidation();

        assertFalse( inputValidation.validateInt(rating));

    }



    @Test
    public void inputValidationTestAssertTrue(){
        StringProperty rating = new SimpleStringProperty();
        rating.set("4");

        InputValidation inputValidation  = new InputValidation();

        assertTrue( inputValidation.validateInt(rating));

    }





}
