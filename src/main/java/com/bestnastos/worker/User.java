package com.bestnastos.worker;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class User {

//    protected String username;
//    protected String password;
    public WorkUnit workUnit;
    protected Worker worker;
    protected List<Worker> workers = new ArrayList<>();//entity
    protected List<Worker> methodWorkersToDelete = new ArrayList<>();

    public User(){
        workUnit = new WorkUnit();
    }

    public void setClassLevel(boolean isClassLevel) {//todo do i need it?
        this.workUnit.setClassLevel(isClassLevel);
    }
    public WithPet withPet() {
        return new WithPet(this);
    }

    public void doSetup() {
        if (!workUnit.getSetupList().isEmpty()) {
            try {
                workUnit.doSetup();
            } catch (Exception ex) {
                workUnit.getSetupList().clear();
                throw new RuntimeException(ex);
            }
        }
    }

    public void doCleanup() {
        if (!workUnit.getCleanupDeque().isEmpty()) {
            try {
                workUnit.doCleanup();
            } catch (Exception ex) {
                workUnit.getCleanupDeque().clear();
                throw new RuntimeException(ex);
            }
        }
        workers.clear();
    }

    User registerAction(Worker worker, ActionItems... actions) {
        List<ActionItems> actionList = new ArrayList<ActionItems>(Arrays.asList(actions));
        if (!actionList.isEmpty()) {
            actionList.stream().forEach(action -> action.registerAction(worker, this.workUnit));//TODO if?
        } else {
            //if action is not specified then do create / delete, etc:
            workUnit.register(worker);
        }

        if (!this.workUnit.isClassLevel()) {
            methodWorkersToDelete.add(worker);
        }
        workers.add(worker);
        this.worker = worker;
        return this;
    }

    public void deleteUsedWorkers() {
        this.workers = workers.stream().filter(element ->
                !this.methodWorkersToDelete.contains(element)).collect(Collectors.toList());
    }
}
