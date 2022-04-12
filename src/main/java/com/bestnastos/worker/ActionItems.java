package com.bestnastos.worker;

public enum ActionItems {

    CREATE() {
        @Override
        public void registerAction(Worker worker, WorkUnit workUnit) {
            workUnit.registerSetup(((CRUD) worker)::create);
        }
    },

    DELETE() {
        @Override
        public void registerAction(Worker worker, WorkUnit workUnit) {
            workUnit.registerSetup(((CRUD) worker)::delete);
        }
    };


    public abstract void registerAction(Worker worker, WorkUnit workUnit);
}
