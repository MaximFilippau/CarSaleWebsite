package com.filippau.tc.config;

import org.springframework.security.crypto.password.PasswordEncoder;

public class DumpPasswordEncoder implements PasswordEncoder {

    @Override
    public String encode(CharSequence charSequence) {
        return String.format("secret: '%s'", charSequence);
    }

    @Override
    public boolean matches(CharSequence charSequence, String s) {
        return false;
    }
}
