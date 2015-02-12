Brandon Choi jc440 </br>
Jeremy Fox jdf37</br>

Refactored cellsociety_team03

We refactored FireCellWorld and combined createFires(), createEmpties(), and createTrees() into one createStates() method. This removed a lot of duplicated code and replaced it with a second parameter that indicated which state it was making.

We chose to do this because we weighed our options between adding a second parameter and having completely separate methods. We concluded that having one method and a second parameter was more efficient and devoid of repeated code. Therefore, we ended up with one method, three constants that indicated which state was being created, and a few if statements within that method as well. 