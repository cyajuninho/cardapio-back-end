package com.example.cardapio.controller;

import com.example.cardapio.food.Food;
import com.example.cardapio.food.FoodRepository;
import com.example.cardapio.food.FoodRequestDTO;
import com.example.cardapio.food.FoodResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("food") // end-point
public class foodController {

    @Autowired
    private FoodRepository repository;

    // Envio de registro
    @CrossOrigin(origins="*", allowedHeaders = "*")
    @PostMapping
    public void saveFood(@RequestBody FoodRequestDTO data){
        Food foodData = new Food(data);
        repository.save(foodData);
        return;
    }

    // Retorno de todos os registros
    @GetMapping
    public List<FoodResponseDTO> getAll(){

        List<FoodResponseDTO> foodList = repository.findAll().stream().map(FoodResponseDTO::new).toList();
        return foodList;
    }

    // Deletar registro pelo ID
    @DeleteMapping("/delete/{id}")
    public void deleteFood(@PathVariable Long id) {

        // Verifica se o registro existe antes de deletar
        if (repository.existsById(id)) {
            repository.deleteById(id);
        }
    }
}
