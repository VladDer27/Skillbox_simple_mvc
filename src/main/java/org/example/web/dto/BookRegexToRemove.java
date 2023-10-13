package org.example.web.dto;

import javax.validation.constraints.Pattern;

public class BookRegexToRemove {
    @Pattern(regexp = "(\\w+.)+")
    private String regex;

    public String getRegex() {
        return regex;
    }

    public void setRegex(String regex) {
        this.regex = regex;
    }
}
