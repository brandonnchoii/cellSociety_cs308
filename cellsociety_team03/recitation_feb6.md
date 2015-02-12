# Discussion

In our code, there were duplicated "step" methods across two different CellWorld types. This is obvious duplicated code, and it would be easy to just use the same method for both worlds. The obvious method of refactoring here was to create a superclass that the cellworlds share. In this case, these cellworlds were both of the state-type class. Thus, it made sense to create a StateCellWorld to contain the shared method. 

An alternative would have been to combine the two classes into one class, but since there is such vastly different logic across the two classes, that wasn't really an option. 