package ru.geekbrains.lesson1.task2;

import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Корзина
 * @param <T> Еда
 */
public class Cart <T extends Food>{

    /**
     * Товары в магазине
     */
    private final ArrayList<T> foodstuffs;
    private final UMarket market;
    private final Class<T> clazz;

    public Cart(Class<T> clazz, UMarket market)
    {
        this.clazz = clazz;
        this.market = market;
        foodstuffs = new ArrayList<>();
    }

    public Collection<T> getFoodstuffs() {
        return foodstuffs;
    }

    /**
     * Распечатать список продуктов в корзине
     */
    public void printFoodstuffs(){
        AtomicInteger index = new AtomicInteger(1);
        foodstuffs.forEach(food -> {
            System.out.printf("[%d] %s (Белки: %s Жиры: %s Углеводы: %s)\n",
                    index.getAndIncrement(), food.getName(),
                    food.getProteins() ? "Да" : "Нет",
                    food.getFats() ? "Да" : "Нет",
                    food.getCarbohydrates() ? "Да" : "Нет");
        });
    }

    /**
     * Балансировка корзины
     */
    public void cardBalancing()
    {
        //boolean proteins = false;
        //boolean fats = false;
        //boolean carbohydrates = false;
        boolean[] pfc = new boolean[]{false,false,false};

        foodstuffs.forEach(food -> {
            if (!pfc[0] && food.getProteins())
                pfc[0] = true;
            else
            if (!pfc[1] && food.getFats())
                pfc[1] = true;
            else
            if (!pfc[2] && food.getCarbohydrates())
                pfc[2] = true;
        });
        /*for (var food : foodstuffs)
        {
            if (!proteins && food.getProteins())
                proteins = true;
            else
            if (!fats && food.getFats())
                fats = true;
            else
            if (!carbohydrates && food.getCarbohydrates())
                carbohydrates = true;
            if (proteins && fats && carbohydrates)
                break;
        }*/

        if (pfc[0] && pfc[1] && pfc[2])
        {
            System.out.println("Корзина уже сбалансирована по БЖУ.");
            return;
        }

        market.getThings(Food.class).forEach(thing -> {
            if (!pfc[0] && thing.getProteins())
            {
                pfc[0] = true;
                foodstuffs.add((T)thing);
            }
            else if (!pfc[1] && thing.getFats())
            {
                pfc[1] = true;
                foodstuffs.add((T)thing);
            }
            else if (!pfc[2] && thing.getCarbohydrates())
            {
                pfc[2] = true;
                foodstuffs.add((T)thing);
            }
        });

        /*for (var thing : market.getThings(Food.class))
        {
            if (!proteins && thing.getProteins())
            {
                proteins = true;
                foodstuffs.add((T)thing);
            }
            else if (!fats && thing.getFats())
            {
                fats = true;
                foodstuffs.add((T)thing);
            }
            else if (!carbohydrates && thing.getCarbohydrates())
            {
                carbohydrates = true;
                foodstuffs.add((T)thing);
            }
            if (proteins && fats && carbohydrates)
                break;
        }*/

        if (pfc[0] && pfc[1] && pfc[2])
            System.out.println("Корзина сбалансирована по БЖУ.");
        else
            System.out.println("Невозможно сбалансировать корзину по БЖУ.");

    }

}
