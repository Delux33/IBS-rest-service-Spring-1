package ru.appline.controller;

import org.springframework.web.bind.annotation.*;
import ru.appline.logic.Pet;
import ru.appline.logic.PetModel;

import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@RestController
public class Controller {
    private static final PetModel petModel = PetModel.getInstance();
    private static final AtomicInteger newId = new AtomicInteger(1);

    @PostMapping(value = "/createPet", consumes = "application/json")
    public String createPet(@RequestBody Pet pet) {

        if (petModel.getAll().isEmpty()) {
            petModel.add(pet, newId.getAndIncrement());
            return "Поздравляю, вы создали своего первого домашнего питомца!";
        } else {
            petModel.add(pet, newId.getAndIncrement());
            return "Поздравляю, вы создали домашнего питомца!";
        }
    }

    @GetMapping(value = "/getAll", produces = "application/json")
    public Map<Integer, Pet> getAll() {
        return petModel.getAll();
    }

    @GetMapping(value = "/getPet", consumes = "application/json", produces = "application/json")
    public Pet getPet(@RequestBody Map<String, Integer> id) {
        return petModel.getFromList(id.get("id"));
    }

    @PutMapping(value = "/changePet", consumes = "application/json")
    public String changePet(@RequestBody Map<String, String> pet) {
        int requestId = Integer.parseInt(pet.get("id"));

        if (petModel.getAll().containsKey(requestId)) {
            String name = pet.get("name");
            String type = pet.get("type");
            int age = Integer.parseInt(pet.get("age"));

            petModel.changePet(requestId, name, type, age);

            return "Питомец успешно изменен!";
        } else {
            return "Питомца с таким ID не существует!";
        }
    }

    @DeleteMapping(value = "/deletePet", consumes = "application/json")
    public String deletePet(@RequestBody Map<String, Integer> id) {
        int requestId = id.get("id");

        if (petModel.getAll().containsKey(requestId)) {
            petModel.deletePet(requestId);

            return "Питомец успешно удален!";
        } else {
            return "Питомца с таким ID не существует!";
        }
    }
}
