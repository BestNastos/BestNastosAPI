package com.bestnastos.worker;

public abstract class BaseWith {

    protected User user;

    public BaseWith(User user) {
        this.user = user;
    }

    public User completeWith() {
        return this.user;
    }

}
