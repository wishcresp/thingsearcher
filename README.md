# ThingSearcher
A 'data-centric' JavaFX application built for an interview. The application searches the data for the closest matching thing (Searchable) based on the number of matching attributes.

Attributes, values and searchables are dynamically loaded into the system with text files and rendered in the interface.

![Application interface](img/interface.png?raw=true)

## Import data file structure
The data files need to be correctly formatted to be imported into the application.

#### Attribute Model File Structure
```
<ATTR>,<Question/Context that is displayed in the view>
```
![Attributes text file formatting](img/attributes.PNG?raw=true)

Each line in the file represents a new attribute to be imported. The first token is the attribute name, the second is a message displayed next to the values. There must only be two tokens per line with a comma delimiter.

#### Data File Structure
```
<NAME>,<ATTR>:<VALUE>,...,<ATTR>:<VALUE>
```

![Data text file formatting](img/data.PNG?raw=true)

Each line represents a thing with a number of attributes that can be searched. The first token is the name of the thing. It can be followed by any number of attribute value pairs separated by comma's. The attribute value pairs are delimited with colons. The attribute name must exactly match the name in the Attribute model text file.

If a line has bad formatting, file loading will continue but the attribute or searchable will be skipped.

## Program Structure

#### View
* ThingSearcher.fxml - View components are linked to the controller with id's

#### Controller
* Controller.java - Links the model to the view, dynamically populates the view with attributes and values

#### Model

* Model.java - Stores the loaded data, save/load file methods and main search function
* Searchable/SearchableImpl.java - Models something that can be searched; has a name and many attributes.
* Attribute.java - Models an attribute; has a name and many values
* SearchValue.java - Used during a search query; has an attribute name and value
* Loader.java - File loading/saving class managed by Model

![Model class diagram](img/model.PNG?raw=true)


