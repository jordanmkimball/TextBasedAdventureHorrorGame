# TextBasedAdventureHorrorGame
This is a text based adventure/horror game that I created from scratch using Java. I created it in order to serve as another example of my coding abilities to future employers, and to hone my skills in object oriented programming.

## Playing the Game
Since this is a Java console application, it is best to open the project using intelliJ or Eclipse, build it, and then run the file titled "MainGame".

Play the game by typing inputs into the command line. You will start the game in a "Dusty Room", and can interact with the different objects in the room by entering commands into console. You can type 'help' to receive tips on how to format commands correctly, and see a detailed list of all possible commands, and what they do. Typing 'Keywords' will also print a list of all the different commands. I have also included this information below. 

### General Tips

1. All player inputs must start with a keyword.

2. All objects that can be interacted with in a room are in double "quotes".

3. When referencing an object you must type its name EXACTLY as it appears in the "quotation marks". For example if an object is named "Wooden Table" typing 'examine wooden table' will be recognized by the text parser while 'examine table' will not be recognized.

4. The text parser can only recognize one command at a time.

5. The text parser is case insensitive: 'TAKE BLACK CHAIR' will be recognized as will 'TaKe blACK Chair'.

6. It is ok to use filler words: 'look wooden table' will be recognized as will 'look at the glorious brown handcrafted wooden table that smells of rosemary'.

7. Many common synonyms of keywords are also recognized. Recognized synonyms are listed in parenthesis next to the keyword below.

### List of Keywords

**North** (n): The player attempts to move to a room North of their current location.

**East** (e): The player attempts to move to a room East of their current location.

**South** (s): The player attempts to move to a room South of their current location.

**West** (w): The player attempts to move to a room West of their current location.

**Go** (move/walk/run/jog/sprint): *Syntax = 'Go location'.* The location can be one of the cardinal directions (North/East/South/West) or a discovered room that is connected to the current room the player is in.

**Look** (glance): *Syntax = 'Look object' or 'look room' or 'look' or 'look north/east/south/west'.* 
Returns a brief description of the object that the player is looking at. If 'look room' or 'look' is entered the player will get a description of their current location. If 'look north/east/south/west' is entered the player will look in the specified direction.

**Examine** (investigate/inspect): *Syntax = 'examine object' or 'examine room' or 'examine'.*
The player investigates the referenced object. May include additional details about the object, as well as the players feelings about the object. When using 'examine room' or 'examine' the player examines the room they are currently in.

**Take** (get/pick up/obtain/acquire): *Syntax = 'take object'.* The player attempts to take an object.

**Use:** *Syntax = 'Use X on Y' where X is an Item in the players inventory, and Y is any item in the current room or the players inventory.* The player attempts to use an inventory item on another item.

**Open:** *Syntax = 'open object' or 'open door'.* The player attempts to open an object that can be opened. If the object is a door, the player attempts to open the door and walk through it.

**Close:** *Syntax = 'close object'.* The player attempts to close an object that can be opened and closed.

**Talk** (chat/converse/speak): *Syntax = 'Talk NPC'* The player starts a conversation with an NPC.

**Wait:** *Syntax = 'wait'.* The player waits for some time. Allows one in game turn to pass.

**Read:** *Syntax = 'read object'.* The player attempts to read something that can be read.

**Eat** (consume/devour): *Syntax = 'eat object'.* The player attempts to eat an edible object. Just because an object is edible doesn't mean your character will necessarily eat it.

**help:** Prints a list of instructions on how to play the game, as well as a list of all commands, their synonyms, and what they do.

**Keywords** (keyword/commands): Returns a list of all game keywords.

**\exit:** exits the game. Progress will NOT be saved.

### Walkthrough

**(Contains Spoliers)**

1. In the "Dusty Room" look at the "Wooden Table" and the "Buttons" on the table. Take the "Letter" and read it. 

2. Open the "Wooden Chest" and take the "Blue Key". Use the "Blue Key" on the "Blue Door" to unlock it. Head east through the "Blue Door" to reach the "Dark Room". 

3. You should now be in the "Dark Room". Examine the room when you first enter it. Then look at and examine the "Disturbing Message". Look at the other objects in the room, then head West back to the "Dusty Room".

4. You should hear a giggle come from the "Dark Room". Head East back to the "Dark Room". A "Creepy Doll" will have spawned in the room. 
Look at the Doll, and examine the Doll and note the descriptions. Take the "Creepy Doll".

5. You will hear a clicking sound come from the "Dusty Room". The "Red Door" in the "Dusty Room" will now be unlocked. Head West back to the "Dusty Room" then go South through the "Red Door".

6. You will now be in the "Kitchen". There are a large number of items to interact with in this room. The "Brown Mouse" will flee through the "Small Hole" no matter what action you take. Note that their is a shiny object looped around its tail. You will need that Item.

7. At some point you may hear scratching sounds come from your inventory. When this happens look at and examine the creepy doll, and note that its descriptions have changed. Read the letter again. There will be a new message written on it at the end. 

8. Take the "Knife" that is lying on the counter. Open the Cupboards and take the "Mouse Trap". Look north and look west. Note that their are lightswitches on both of these walls. If you look around the room or look at the "Small Hole" you may see the "Brown Mouse" peeking his head out of the "Small Hole". He does that every so often to check if you are still in the room. He will not come back into the room unless you are gone. 

9. At some point the lights will go out and you will be presented with a number of choices on how you would like to proceed. Search the North wall, and then select to search left of the door. You can also search the West Wall and turn the lightswitch on there.

10. Open the "Fridge" and look at and examine the "Slab of Meat". Use the "Knife" on the "Slab of Meat" to retrieve the "piece of meat".
At this point the lights might go out again. The first light switch you come accross will always fail to work, but the second light switch will work. 

11. Check your inventory and note that the "Creepy Doll" and "Knife" are now missing... suspicious. Use the "Piece of Meat" on the "Mouse Trap" to bait it, then use the "Mouse Trap" on the "Small Hole". The "Brown Mouse" will not come out of the "Small Hole" if you are in the room, so exit North back to the "Dusty Room".

12. Back in the "Dusty Room" you will receive a message that the room feels different from the last time you were here. Examine the wooden table, and note that the "Buttons" that were here at the beginning of the game have been removed. You will probably hear "giggling" coming from the "Dark Room" at this point. The "Blue Door" will be locked again, but you can unlock it with the "Blue Key". To receive the *Bad Ending* head back into the "Dark Room". To receive the *Good Ending* wait in the "Dusty Room" for a few turns until you hear a metallic snapping sound come from the "Kitchen". Head South back to the "Kitchen".

13. There will now be a dead "Brown Mouse" inside of the "Mouse Trap". Examine the "Brown Mouse", then take the "Yellow Key" from the "Brown Mouse". Use the "Yellow Key" on the "Yellow Door", and open it.

14. You will now be in the "Final Room". It will originally appear fairly empty, but if you wait for a few turns, the "Flat Man" will slide out of the "Metal Vent" and float down. Look at and examine him, then Talk to him.

15. He will ask you a few questions. It doesn't matter how you answer. You will receive a message that you have beaten the Game after you answer his questions. 

