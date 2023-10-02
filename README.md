# CS501 📱

## Git Commit convention
1. Make a branch before you start working on your code. <br>
``` git checkout -b <new branch name> ```
   > ex) ```feature/geo-quiz-landscape```
2. After you are done with implementing each programming assignment, create a PR to ```dev```.
   > Don't forget to pull from ```dev``` and rebase before creating a PR!<br>
   1. Switch out to dev. ```git switch dev```
   2. Pull from dev. ```git pull```
   3. Switch back to you current working branch. ```git switch <your branch name>```
   4. Rebase dev to your branch. ```git rebase dev```

   ⭐ you can see all the branches with ```git branch``` ⭐ <br><br>
      
   > You might also have to fast-forward your current working branch to dev in order to prevent merge conflicts.
  
   > The PR title should be something similar to your branch name. Something that is easy to understand! <br>
  ```feat: implement land scape mode```
   
3. Review the PR.
   > ```dev``` is a protected branch. We need your reviews and approval in order to merge.
   > read your peer's code and add comments to them if needed!
   > It would be great if you switch out to their branch and test it with the simulator running.
   
   ```git fetch --all``` will fetch all the remote branches.<br>
   
4. We merge them to ```dev```.
   > When we are done reviewing and there are no conflicts we will merge to dev. <br>
   > ⚠️MERGING SHOULD BE DONE BY THE PERSON WHO IMPLEMENTED THE CODE!

5. After we are done with each week's assignment I will merge ```dev``` to ```main```.
   > ⭐ So remember to pull before starting any work!!
   

## Working on the same programming assignment

#### (For programming assignments that are a bit bigger than the programming assignments that are in the textbook) <br>
Since it is ideal to work on it together...
1. Make a branch from ```dev```.
   > The name of the branch could be something like ```feature/<name of the programming assignment>```. <br>
   > ex) ```feature/flash-card```.
2. Then each of us can make another branch coming out from ```feature/<name of the programming assignment>``` instead of ```dev```.
   > This branch name could be something like ```<your name>/<name of the programming assignment>-<what you are working on>```. <br>
   > ex) ```youngjin/flash-card-login```
3. After you are done implementing your code we can make PR to ```<your name>/<name of the programming assignment>-<what you are working on>```.
4. Then merge ```feature/<name of the programming assignment>``` back to ```dev```.

## Useful stuff
⚠️ If there is an error doing ```pull``` try ```git config --global pull.rebase true```. <br>
(I use rebase for default when pulling.)

⭐ we usually don't use past tense for git commit, branches, PR.
  
|branch prefix| use|
|--|--|
|hotfix|	for quickly fixing critical issues usually with a temporary solution|
|fix|	for fixing a bug|
|feature|	for adding, removing or modifying a feature|
|WIP|	for a work in progress|
