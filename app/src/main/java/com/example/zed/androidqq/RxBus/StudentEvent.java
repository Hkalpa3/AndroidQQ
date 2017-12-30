package com.example.zed.androidqq.RxBus;

/**
 * Created by zed on 2017/12/26.
 */

public class StudentEvent {
    private String id;
    private String name;

    public StudentEvent(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
}
