package ru.appline.logic;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class PetModel implements Serializable {
    private static final PetModel instance = new PetModel();

    private final Map<Integer, Pet> model;

    public PetModel() {
        model = new HashMap<>();
    }

    public static PetModel getInstance() {
        return instance;
    }

    public void add(Pet pet, int id) {
        model.put(id, pet);
    }

    public Pet getFromList(int id) {
        return model.get(id);
    }

    public Map<Integer, Pet> getAll() {
        return model;
    }

    public void changePet(int id, String name, String type, int age) {
        Pet pet = model.get(id);
        pet.setName(name);
        pet.setType(type);
        pet.setAge(age);
    }

    public void deletePet(int id) {
        model.remove(id);
    }
}
