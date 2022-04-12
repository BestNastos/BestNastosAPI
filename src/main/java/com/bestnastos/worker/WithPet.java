package com.bestnastos.worker;

import com.bestnastos.PetBuilder;

public class WithPet extends BaseWith {

    protected PetBuilder petBuilder;

    public WithPet(User user){
        super(user);
    }

    public WithPet createPet(PetBuilder petBuilder){
        this.user.registerAction(new PetWorker(petBuilder));
        return this;
    }

    public User completeWith(){
        return this.user;
    }

//    public WithPet updatePet(){
//        return this;
//    }
//
//    public WithPet readPet(){
//        return this;
//    }
//
//    public WithPet deletePet(){
//        return this;
//    }
}
