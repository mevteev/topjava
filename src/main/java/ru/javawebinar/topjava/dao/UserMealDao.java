package ru.javawebinar.topjava.dao;

import ru.javawebinar.topjava.model.UserMeal;

import java.util.List;

/**
 * Created by mevte on 02.07.2016.
 */
public interface UserMealDao {

    public void addMeal(UserMeal meal);
    public void deleteMeal(Integer mealId);
    public void updateMeal(UserMeal meal);
    public List<UserMeal> getAllMeals();
    public UserMeal getMealById(Integer id);
}
