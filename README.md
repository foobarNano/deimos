# deimos
Deimos Pharmaceutics - A GUI business application in Java

#### Application structure diagram:
```mermaid
graph RL
    A["Database\n*(Does not handle security or data integrity!)*"] --> B["Controller\n*(Handles basic data integrity, but not security)*"]
    B --> C["GUI\n*(Handles advanced data security and user input)*"]

    class A,B,C largeBox;
```