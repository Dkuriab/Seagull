package com.seagull.data.model

import com.seagull.data.model.buyinglist.Ingredient
import com.seagull.data.model.buyinglist.MeasureType

val listBreakfast = listOf(
    Recipe(
        0,
        "Oatmeal with bananas",
        "https://fitfoodiefinds.com/wp-content/uploads/2019/08/basic-oatmeal.jpg",
        "",
        "",
        1,
        "",
        "",
        1
    ),
    Recipe(
        1,
        "Yogurt",
        "https://i2.wp.com/www.vegrecipesofindia.com/wp-content/uploads/2017/09/thayir-vadai-curd-vada-recipe.jpg",
        "",
        "",
        1,
        "",
        "",
        1
    ),
    Recipe(
        2,
        "Lemon Poppyseed Crepes",
        "https://www.countrycleaver.com/wp-content/uploads/2016/03/IMG_8313.jpg",
        "",
        "",
        1,
        "",
        "",
        1
    ),
    Recipe(
        3,
        "New Year Oatmeal",
        "https://diethood.com/wp-content/uploads/2019/12/How-To-Make-Oatmeal-4.jpg",
        "",
        "",
        1,
        "",
        "",
        1
    ),
    Recipe(
        4,
        "Buckwheat porridge",
        "https://149366112.v2.pressablecdn.com/wp-content/uploads/2019/11/apple-cinnamon-buckwheat-porridge-03-683x1024-1200x750.jpg",
        "",
        "",
        1,
        "",
        "",
        1
    ),
    Recipe(
        5,
        "Buckwheat porridge with apples",
        "https://www.vibrantplate.com/wp-content/uploads/2019/10/Apple-Cinnamon-Buckwheat-Porridge-05-735x1103.jpg",
        "",
        "",
        1,
        "",
        "",
        1
    ),
    Recipe(
        6,
        "Easy baked eggs",
        "https://www.spoonforkbacon.com/wp-content/uploads/2020/07/easy-baked-eggs-recipe-card.jpg",
        "",
        "",
        1,
        "",
        "",
        1
    ),
    Recipe(
        7,
        "French toast",
        "https://food.fnr.sndimg.com/content/dam/images/food/fullset/2008/3/26/0/IE0309_French-Toast.jpg.rend.hgtvcom.616.462.suffix/1431730431340.jpeg",
        "",
        "",
        1,
        "",
        "",
        1
    )
)

val listIngredients = listOf(
    Ingredient(
        id = 1,
        name = "Potato",
        weight = 1.4F,
        type_of_ingredient = MeasureType.KILOGRAMS,
        recipe_id = 1
    ),
    Ingredient(
        id = 2,
        name = "Chicken",
        weight = 0.500F,
        type_of_ingredient = MeasureType.KILOGRAMS,
        recipe_id = 1
    ),
    Ingredient(
        id = 3,
        name = "Cheese",
        weight = 0.500F,
        type_of_ingredient = MeasureType.KILOGRAMS,
        recipe_id = 1
    ),
    Ingredient(
        id = 4,
        name = "Weed",
        weight = 0.500F,
        type_of_ingredient = MeasureType.KILOGRAMS,
        recipe_id = 1
    ),
    Ingredient(
        id = 5,
        name = "salt",
        weight = 1F,
        type_of_ingredient = MeasureType.ITEMS,
        recipe_id = 1
    )
)