package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.javawebinar.topjava.dao.UserMealDao;
import ru.javawebinar.topjava.dao.UserMealDaoMemory;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.model.UserMealWithExceed;
import ru.javawebinar.topjava.util.TimeUtil;
import ru.javawebinar.topjava.util.UserMealsUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by mevte on 28.06.2016.
 */
public class MealServlet extends HttpServlet {
    private static final Logger LOG = LoggerFactory.getLogger(MealServlet.class);

    private static final UserMealDao mealDao = new UserMealDaoMemory();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LOG.debug("redirect to mealList");

        String action = request.getParameter("action");

        if (action != null) {
            if (action.equalsIgnoreCase("delete")) {
                Integer mealId = Integer.parseInt(request.getParameter("mealId"));
                mealDao.deleteMeal(mealId);
            }

        }

        request.setAttribute("mealList", getMeals(mealDao.getAllMeals()));

        request.getRequestDispatcher("/mealList.jsp").forward(request, response);

    }


    protected List<UserMealWithExceed> getMeals(List<UserMeal> mealList) {
        return UserMealsUtil.getFilteredWithExceeded(mealList, LocalTime.of(0, 0), LocalTime.of(23, 59), 2000);
    }

}
