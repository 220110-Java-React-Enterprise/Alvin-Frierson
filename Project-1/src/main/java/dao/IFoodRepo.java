package dao;

import models.Food;

import java.util.List;

// This is an interface for our Food CRUD
public interface IFoodRepo {

    // Method for create
    public void createFood(Food food);

    // Method for read
    public List<Food> readFood(Food food);

    // Method for update
    public void updateFood(Food food);

    // Method for delete
    public void deleteFood(Food food);
}
