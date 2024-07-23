# MtS Modding Anniversary 5: The Packmaster
## Preamble
A group-effort character mod. Upon choosing the class, a UI is revealed which selects, at random, 6 Booster Packs to be added to the character’s existing card library of 14 cards (4 starter cards, and one ‘standard booster pack’.  Those booster packs make up the entire card pool (74 cards) for the run. (This will probably change in the future)  
  
A contribution is a booster pack, of course you can make multiple. I'm sure many of you have ideas for archetypes but were too lazy to think up an entire character to fit it into, this is your chance to make a self-contained archetype of 10 cards.  

## Contribution
There's a few rules in place to make this character great despite the minds working on it at the same time.
The full rules, examples and explanations are in this google doc:
https://docs.google.com/document/d/1Gs004Gur4DfZ8vly8rFDjcnRojt6Ptsk6rH_GyZx_XY  
The sheet for maintaining information on your booster pack can be found here:  
https://docs.google.com/spreadsheets/d/1MoLN5qyNtclCqOPgAW9_poDS4HPhTGOmahWSIA1FgCg  
  
To make a contribution, you must have a GitHub account. 
For the specifics of how to fork this repo and then make a pull request, please look at this guide:
https://docs.github.com/en/get-started/quickstart/contributing-to-projects  
  
I recommend using the GitHub desktop client for this if you have no experience with Github  
https://desktop.github.com/  
  
## Any pull requests for additional packs should directed to this https://github.com/erasels/Packmaster-Expansion-Packs repository.  

## Adding skins
New skins will still be added to this repository, there are a few steps that need to be taken for that.
1) Making a new class that extends AbstractSkin in the skins/instances package  
2) Adding a registerSkin call to the SkinHanlder constructor (after the existing skins, order is important)
3) Adding the relevant skin files to the correct path at \resources\anniv5Resources\images\char\mainChar\skins\(texturePath as defined in constructor of your AbstractSkin sub-class)
4) Add the relevant strings in the Skinstrings.json as well. (first one is the name that is displayed, second one is the name of the artist)
