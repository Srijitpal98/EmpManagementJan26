package com.srijit.empmanagement.models;

import java.util.Date;

public abstract class BaseModel {
    private long id;
    private Date createdAt;
    private Date lastUpdatedAt;
    private String createdBy;
    private String lastUpdatedBy;
    private State state;
}
