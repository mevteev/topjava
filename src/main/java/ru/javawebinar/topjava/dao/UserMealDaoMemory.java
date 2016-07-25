package ru.javawebinar.topjava.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.javawebinar.topjava.model.UserMeal;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

/**
 * Created by mevte on 02.07.2016.
 */
public class UserMealDaoMemory implements UserMealDao {

    protected static List<UserMeal> list;

    protected static Integer maxId = 0;

    private static final Logger LOG = LoggerFactory.getLogger(UserMealDaoMemory.class);

    static {
        list =

                Arrays.asList(
                new UserMeal(maxId++, LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 500),
                new UserMeal(maxId++, LocalDateTime.of(2015, Month.MAY, 30, 13, 0), "Обед", 1000),
                new UserMeal(maxId++, LocalDateTime.of(2015, Month.MAY, 30, 20, 0), "Ужин", 500),
                new UserMeal(maxId++, LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Завтрак", 1000),
                new UserMeal(maxId++, LocalDateTime.of(2015, Month.MAY, 31, 13, 0), "Обед", 500),
                new UserMeal(maxId++, LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Ужин", 510)
        );

    }

    @Override
    synchronized public void addMeal(UserMeal meal) {
        meal.setId(getNextId());
        list.add(meal);
    }

    @Override
    public void deleteMeal(Integer mealId) {
        list.remove(getIndexById(mealId));
    }

    @Override
    synchronized public void updateMeal(UserMeal meal) {
        UserMeal um = getMealById(meal.getId());
        if (um != null) {
            meal.setId(um.getId());
            list.remove(um);
            list.add(meal);
        }

    }

    @Override
    public List<UserMeal> getAllMeals() {
        return list;
    }

    @Override
    public UserMeal getMealById(Integer id) {


        Optional<UserMeal> um = list.stream().filter(list -> list.getId().equals(id)).findFirst();

        if (um.isPresent()) {
            LOG.info(um.get().getDescription());
            return um.get();
        } else {
            return null;
        }

    }

    synchronized protected Integer getNextId() {
        return ++maxId;
    }

    protected Integer getIndexById(Integer id) {
        //Optional<UserMeal> um = list.stream().filter(list -> list.getId().equals(id)).findFirst();

        int[] indices = IntStream.range(0, list.size())
                .filter(i -> list.get(i).getId().equals(id))
                .toArray();

        if (indices.length > 0) {
            return indices[0];
        } else {
            return -1;
        }
    }
}
