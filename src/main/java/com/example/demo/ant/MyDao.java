package com.example.demo.ant;

import java.io.Serializable;
import java.util.List;

public interface MyDao <T> extends Serializable {
    public List<T> findLoginId(String login_id);
}
