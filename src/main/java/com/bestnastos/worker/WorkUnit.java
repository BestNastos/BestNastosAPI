package com.bestnastos.worker;

import io.qameta.allure.Step;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class WorkUnit {

    protected boolean isClassLevel;

    private List<Act> classSetupItems = new LinkedList<Act>();
    private List<Act> methodSetupItems = new LinkedList<Act>();
    private Deque<Act> classCleanupItems = new ArrayDeque<Act>();
    private Deque<Act> methodCleanupItems = new ArrayDeque<Act>();

    public void setClassLevel(boolean isClassLevel) {//todo do i need it?
        this.isClassLevel = isClassLevel;
    }

    public Deque<Act> getCleanupDeque() {
        return isClassLevel ? classCleanupItems : methodCleanupItems;
    }

    public void registerSetup(Act action) {
        getSetupList().add(action);
    }

    public void registerCleanup(Act action) {
        getCleanupDeque().push(action);
    }

    @Step("Register action")
    public void register(Worker worker) {
        if (worker instanceof CRUD) {
            registerSetup(((CRUD) worker)::create);
            registerCleanup(((CRUD) worker)::delete);
            return;
        } /*else if (worker instanceof IApplyOnce) {}*/
    }

    public void doSetup() {
        //TODO
        getSetupList().stream().forEach(element -> {
            try {
                element.apply();
            } catch (Exception ex) {
                getSetupList().clear();
                throw new RuntimeException(ex);
            }
        });
        getSetupList().clear();
    }

    @Step("Do Cleanup Action")
    public void doCleanup() {
        getCleanupDeque().stream().forEach(action -> {
            try {
                action.apply();
            } catch (Exception ex) {
                getCleanupDeque().clear();
                throw new RuntimeException(ex);
            }
        });
        getCleanupDeque().clear();
    }

    public boolean isClassLevel() {
        return isClassLevel;
    }


    public List<Act> getSetupList() {
        return isClassLevel ? classSetupItems : methodSetupItems;
    }
}
