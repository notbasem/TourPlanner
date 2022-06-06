package com.example.tourplanner.view.validation;

import javafx.beans.property.StringProperty;

public class InputValidation {

    public boolean validateInt(StringProperty stringProperty) {

        int ratingInt = Integer.parseInt(stringProperty.getValue());
        if (ratingInt <= 5 && ratingInt >= 1) {
            return true;
        }
        return false;
    }


}
