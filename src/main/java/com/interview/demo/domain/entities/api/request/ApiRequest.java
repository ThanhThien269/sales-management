package com.interview.demo.domain.entities.api.request;

import java.io.Serializable;
import java.util.Map;

public abstract class ApiRequest implements Serializable {
    public abstract Map<String, Object> toMap();
}
