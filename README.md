# Inventory App Help

## General Navigation

### Main Inventory Screen

![Main Screen](/help/mainscreen.PNG)

1. This button will navigate you to the load inventory screen.
2. This button will navigate you to the export inventory screen.
3. This button will navigate you to the new inventory item screen.
4. This table displays all of the inventory items you have added or loaded.
5. If you have selected an inventory item from the table, this button will remove it.
6. If you have selected an inventory item from the table, this button will navigate you to the view item screen with that item        displayed.

----------------------------------------------------------------------------------------------------------------------------------

### Inventory New Item Screen

![New Screen](/help/newscreen.PNG)

1. This button will cancel making a new inventory item and navigate you back to the main inventory screen.
2. This will take the information you entered into the value, serial number, and name fields and create a new inventory item. It      will then navigate you back to the main inventory screen.
3. This is the field in which you enter the value of the new inventory item. The format should be 00.00.
4. This is the field in which you enter the serial number of the new inventory item. The format of a serial number is XXXXXXXXXX      and there can not be any duplicates.
5. This is the field in which you enter the name of the new inventory item. The name must be at least 2 characters and less than    257.

----------------------------------------------------------------------------------------------------------------------------------

### Inventory View Item Screen

![View Screen](/help/viewscreen.PNG)

1. This button will navigate you back to the main inventory screen without saving any changes made to the inventory item.
2. This button will save any changes you've made to the inventory item by editing the fields. The formatting for creating an          inventory item also apply to editing them. This means no duplicate serial numbers, etc.
3. This is the field which displays the selected inventory item's value. You can also change the item's value by editing it in        this field. The formatting for creating the value also applies to editing it.
4. This is the field which displays the selected inventory item's serial number. You can also change the item's serial number by      editing it in this field.
5. This is the field which displays the selected inventory item's name. You can also change the item's name by editing it in this    field.

----------------------------------------------------------------------------------------------------------------------------------

## Exporting and Loading Inventory Items

### Export Inventory Screen

![Export Screen](/help/exportscreen.PNG)

1. This button will navigate you back to the main inventory screen.
2. This button will take both the file name and file location information from the fields and attempt to create a json file with the desired location and name. If the fields are blank or the file name is invalid, it will display an error message.
3. This is the field in which you will enter the desired file name to create the json file.
4. This is the field in which you will enter the desired path for the file location. 

#### Getting the path for the file location

The easiest way to get the path for the file location is as follows:

1. Go to the desired file location in your file explorer. Let's say the desktop.
![File Explorer Exampple](/help/fileexplorer.PNG)
2. Shift + Right Click the file location and click "Copy as Path".
3. You can then paste this path into the file location field.

----------------------------------------------------------------------------------------------------------------------------------

### Load Inventory Screen

![Load Screen](/help/loadscreen.PNG)

1. This button will navigate you back to the main inventory screen.
2. This button will take both the file name and file location information from the fields and attempt to load the inventory items in the desired json file. If the fields are blank or the file name is invalid, it will display an error message.
3. This is the field in which you will enter the file name of the previously exported json file.
4. This is the field in which you will enter the path of the previously exported json file. 

The method for easily getting the file path in the previous section also works here.
