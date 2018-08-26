# MVC_Chef_Game_Application
A Java client game application based on MVC and other design patterns like Observer, Decorator, Iterator, and Factory.

## Overall Interface
<img width="1440" alt="overall2" src="https://user-images.githubusercontent.com/33140156/39090782-d2a113f2-45b5-11e8-824a-1ea04e8b7612.png">

## Middle Panel

### "Intro" button: show some introduction of the game
<img width="1440" alt="intro_button" src="https://user-images.githubusercontent.com/33140156/39091130-e86981be-45bb-11e8-9a20-32e46e9bf332.png">

### Initially, each position is gray which means there is no plate on it.
<img width="1440" alt="overall" src="https://user-images.githubusercontent.com/33140156/39090802-5cd82272-45b6-11e8-9684-d0ffa4f787df.png">


### When we click "rotate" button, there should be random colorful plates placed on the belt. 
Random means: (User can create customized sushi on the right panel)
1. random plate(blue, yellow, gold, red) Each kind of plate has specific price. The price of gold plate is changeable.
2. random ingredient
3. random position
4. random sushi type
5. random chef type and chef name: chef can create Sashimi, Nigiri, or Roll. 
6. random customer: customers would consume sushi on the plates randomly. (No cutomer GUI in this project)

### When we click on each plate, there should be the information of the plate
<b>The alert window includes the following information</b>
1. The type of the sushi: Sashimi, Nigiri, or Roll
2. If the sushi is roll, the ingredients it has
3. The name of the chef who created this sushi
4. The age of the plate: the rotation times of the plate
<img width="1440" alt="plate_info" src="https://user-images.githubusercontent.com/33140156/39091122-c82594ec-45bb-11e8-8feb-f56306666e34.png">

## Left Panel

### "Balance" button: show the balance of each chef from highest to lowest.
<b>Balance = 100(initial blance) + price of consumed plates - cost of ingredients(create sushi will cost some money of chef)</b>

<img width="479" alt="balance" src="https://user-images.githubusercontent.com/33140156/39090909-f6cb1b4a-45b7-11e8-8a40-be7f450ddd45.png">

### "Spoiled Amount" button: show the spoiled amount of sushi from lowest to highest
<b> Sushi could be spoiled after several rotations</b>

<img width="480" alt="spoiled_amount" src="https://user-images.githubusercontent.com/33140156/39090955-ee04dd2e-45b8-11e8-983c-573ea33e60a9.png">

### "Consumed Amount" button: show the consumed amount of sushi from highest to lowest
<b> Random customers will consume sushi created from different chef</b>
<img width="479" alt="cosumed_amount" src="https://user-images.githubusercontent.com/33140156/39090969-27f232ac-45b9-11e8-8d21-b6f3e091acf9.png">

## Right Panel: User can create sushi plate by himself
<b> 3 types of sushi in total: Sashimi, Nigiri, and Roll</b>
Note: 1. Sashimi and Nigiri could have only one type of ingredient from five choices
      2. Roll could have up to 8 types of ingredients from 8 choices. Each ingredient can be added up to 1.5 oz
      3. Player can only create one type of sushi once and must choose the plate type and position.      

### Nigiri and Sashimi Making
<b>If the user choose one kind of sushi (Sashimi, Nigiri, or ROll), the other two are set disabled.</b>
<img width="478" alt="sushi_make1" src="https://user-images.githubusercontent.com/33140156/39091013-af6fe062-45b9-11e8-9049-98626abd111a.png">

### Roll Making
<b>To create roll, the player should customize the types and amount of the ingredients</b>
<img width="478" alt="roll_make" src="https://user-images.githubusercontent.com/33140156/39091049-6653d32e-45ba-11e8-9963-988d3ea206fb.png">

### Clear Choice
<b>The player could clear the choice and recreate the sushi he/she wants</b>
<img width="479" alt="clear_init" src="https://user-images.githubusercontent.com/33140156/39091073-e2ff34a4-45ba-11e8-8969-74a1fcb96db4.png">

### Gold Plate Price
<b>If the player choose the gold plate, a price chooser appears. As a chef, he/she should cutomize the price of the plate</b>

<img width="478" alt="gold_plate_price_show" src="https://user-images.githubusercontent.com/33140156/39091081-1564b658-45bb-11e8-81a7-2335c63f5993.png">

