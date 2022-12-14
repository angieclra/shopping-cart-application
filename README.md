# Treat Yourself :white_heart:

### A shopping cart app to make your shopping life easier! :sparkles:

In this **Shopping Cart Manager Application**:
- The display menu of the shopping cart manager will be shown along with the list of items
- Give the user the option to add items into the shopping cart
- Give the user the option to remove items from the shopping cart
- Print invoice for the shopping cart
- Calculate the total items in the shopping cart
- Calculate the total price of the whole shopping cart

This Java desktop application will be targeted to people for all ages, especially 
those who enjoy shopping. During this era, online shopping has become popular
and is becoming big part of people's life as it makes grocery shopping a lot easier. This shopping
cart project interest me since it is practical for our daily lives.

## User Stories
- As a user, I want to be able to view the menu for all the possible options of the application
- As a user, I want to be able to add items to the shopping cart
- As a user, I want to be able to remove items from the shopping cart
- As a user, I want to be able to see the total items in the shopping cart
- As a user, I want to be able to see the total price in the shopping cart
- As a user, I want to be able to see the invoice for my shopping cart
- As a user, I want to be able to save my current state of the shopping cart to file
- As a user, I want to be able to load my whole shopping cart from file

## Phase 4: Task 2
Added item event is working differently than what I am expecting. It is also printing when I call the 
method of ``addToCart`` in the main class, instead of only clicking the button. 

Mon Mar 28 17:21:35 PDT 2022
Item added to shopping cart

Mon Mar 28 17:21:35 PDT 2022
Item added to shopping cart

Mon Mar 28 17:21:36 PDT 2022
Item added to shopping cart

Mon Mar 28 17:21:36 PDT 2022
Item added to shopping cart

Mon Mar 28 17:21:36 PDT 2022
Item added to shopping cart

Mon Mar 28 17:21:36 PDT 2022
Item added to shopping cart

Mon Mar 28 17:21:39 PDT 2022
Item removed from shopping cart

Mon Mar 28 17:21:39 PDT 2022
Item removed from shopping cart

Mon Mar 28 17:21:39 PDT 2022
Item removed from shopping cart

Mon Mar 28 17:21:39 PDT 2022
Item removed from shopping cart

Mon Mar 28 17:21:39 PDT 2022
Item removed from shopping cart

Mon Mar 28 17:21:41 PDT 2022
Invoice of shopping cart printed

## Phase 4: Task 3
- If I have more time to work on my project, I would refactor my code in my ```ShoppingCartFrame``` class. Instead of 
making it in a single big class, I would split it to multiple classes, as it will be hard to debug. 
Breaking the code to more classes would make the code more testable, maintainable, and reusable.
- I would fix and refactor my design specifically for my methods that has the functionality of logging the event since it is not
working as to what I am expected to see (where it is logging the event when I click the button, but not the
method). I would refactor my design so the functionality is more specified. 