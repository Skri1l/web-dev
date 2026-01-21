package com.skr1l.prototype;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;

import java.util.UUID;

public class RequestIdHolder {

    private final UUID id = UUID.randomUUID();

    public UUID getId() {
        return id;
    }

}
