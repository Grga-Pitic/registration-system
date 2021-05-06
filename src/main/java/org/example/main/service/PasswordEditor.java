package org.example.main.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.beans.PropertyEditorSupport;

@Component
public class PasswordEditor extends PropertyEditorSupport {

    private PasswordEncoder encoder;

    @Autowired
    public PasswordEditor(PasswordEncoder encoder) {
        this.encoder = encoder;
    }

    @Override
    public String getAsText() {
        return super.getAsText();
    }

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        super.setValue(encoder.encode(text));
    }
}
