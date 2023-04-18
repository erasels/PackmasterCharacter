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
  
The cut-off point for PRs that will be included in the first release will be 18th of January 2023. Although, PRs made after that point will still be merged and maintained, so don't be discouraged if you're late.  
  
To make a contribution, you must have a GitHub account. 
For the specifics of how to fork this repo and then make a pull request, please look at this guide:
https://docs.github.com/en/get-started/quickstart/contributing-to-projects  
  
I recommend using the GitHub desktop client for this if you have no experience with Github  
https://desktop.github.com/  

## Technical
### How to make a Pack
First, navigate to the project on your system and copy the example-pom.txt and rename the copy to pom.xml. Adjust it to the correct steam path if necessary. Do this before you load the project in intellij to ward off some issues.  
**DO NOT DELETE THE EXAMPLE POM!**

Once you've got the project up and running, navigate to the **packs** package.  
Create a new class that extends from AbstractCardPack. You can copy the basic structure from an existing pack like the DownfallPack. Simply change the ID line to use the ID you want to use for you pack, example:  
`public static final String ID = SpireAnniversary5Mod.makeID("ExamplePack"); `
and define the cards your pack has in `getCards()` once you've made them.  
Strings are done in a per-pack basis. So create a directory with the same name as your pack's ID with no capitalization in `resources/localization/eng`. Everything other than the *Cardstrings.json* and *UIstrings.json* is optional.  
In your *UIstrings.json* the first entry should be the strings for your card pack preview card.  
```
"${ModID}:ExamplePack": {  
  "TEXT": [  
    "Example Name",  
    "Example Description",  
    "Pack Author Name"  
  ]  
}  
```
  
Then it's time make your cards!  
To create a relic for your pack, simply create a class that extends AbstractPackmasterRelic and give the pack it can show up for with the second constructor (the longer one). Example:  
```
   public ExampleRelic() {
      super(ID, RelicTier.COMMON, LandingSound.FLAT, ExamplePack.ID);
   }  
```
  
  
To test your cards simply use the Custom Draft toggle in the character select screen and make sure your pack is selected.

### Notes
* A Secondary magic number is already defined in case you need it.
* If you make patches/powers/actions/or similar often-used classes, please do so in your own sub-folder/package.
* We have a utility class called Wiz, you can make use of it to get access to general utility methods (like accessing the player quicker than using AbstractDungeon.player)
* We have our own AbstractCard Class, **AbstractPackmasterCard** please make use of it for your cards
* To get a card's Pack, you can call getParent on a PackmasterCard or use the method in the Wiz class getPackByCard
* We have some lists and maps to get card packs, these are allPacks, packsByID, cardParentMap
* If you want to add your own CardBack for your Pack preview card, you can do so by adding it to the `images/512` and `1024` with the png file having the same name as your pack
* Additional Credits can be added to a pack where assets or inspiration have been taken from elsewhere. This can simply be done by passing a credits string to the super method of the pack's constructor
* AbstractPackMasterOrb exists to manage pack orbs with non standard effect timing for interaction purposes.
* Additionally this file contains getPackLimitedOrb(boolean useCardRng) to pull a random orb from what would be available in your packs.

### Reminders and veto ruling
Please look at the Google Docs to get a full refresher of the rules for your contribution. Specifically the complexity cap is important.
Here is a TL;DR:
* 10 cards per pack, token cards are allowed but please be reasonable (3 token cards max, no exceptions other than the few made already)
* No duplicate cards, if a card exists in another pack, it may not be in another.
* No additional UI, please use powers/orbs/stances if you need them
* Most of your cards should have less than 6 lines of text.
* Don't outsource complexity to single-use Keywords
* Artwork needs to be included for all cards in the pack
* You may add relics that only spawn if your pack is selected, however these must interact with the pack's mechanic, they should not be generic effects

And finally, as the person merging all PRs other reviewers or I (Gk/erasels) may ask you to make changes to your Pack in case some cards lie outside the power curve or skirt the rules too much.
Please don't be discouraged if your Pack isn't instantly merged.

### Ratings and tags
After you've finished your pack, please add ratings and tags for it to the files `ratings.txt` and `tags.txt` (both in the folder `resources/anniv5resources/summaries`).

Ratings:
* Intended to give a rough sense of what a pack is and isn't good at
* Five categories: offense, defense, support (including things like card draw and energy generation), frontload (how good the pack is at quickly having an impact in combat), and scaling.
* On a scale of 1-5, where the ratings describe how the pack compares in that category:
  * 1: One of the weakest (the bottom 5-10%)
  * 2: Below average
  * 3: Average (the middle 30-40%)
  * 4: Above average
  * 5: One of the strongest (the top 5-10%)
* Don't worry too much about the exact values, these are easy to change later based on play experience and feedback
* Each entry in the file is the ID of the pack followed by ratings in the five categories, in the order listed, separated by tabs

Tags:
* Intended to give a rough sense of what synergies a pack might have
* Used for specific mechanics that are used across many packs; the description for your pack should cover anything unique to your pack
* Available tags and when to add them:
  * Strength: multiple cards that gain Strength (more than just one strength rare or uncommon) 
  * Exhaust: multiple cards that benefit from exhausting or most of the pack enabling exhaust
  * Discard: multiple cards that benefit from discard or most of the pack enabling discard
  * Debuff: substantial number of cards that add debuffs or having a debuff that's core to the pack
  * Orbs: any amount of orb generating cards
  * Stances: any amount of stance entering cards
  * Attacks: the design of the pack is focused on attacks and attack synergy
  * Tokens: substantial number of cards that create temporary cards or interact with them
  * None: if no other tags apply
* Each entry in the file is the ID of the pack, followed by a tab, followed by a comma-separated list of tags

### HATS

Packmaster can equip different cosmetic hats, one for every pack. Each one is locked until you've won a run with its respective pack in your pool.

This does not add any mandatory work for packs. There are three ways to get a hat added to your pack:

1) Art it yourself, using the new Hat template PSD (PackmasterHatTemplate.psd, included in this repo).  Add a png export of your hat into the resources/hats folder, with the naming convention of <PackID>Hat.png, and it will automatically be detected and used.

2) Write up a simple description to AlexMdle of your requested hat.

3) Do nothing, in which case a hat will be generated for your pack by AlexMdle.

Hats also come with a display name and flavor text, which are plucked from the UIStrings.json of your respective pack, with the ID "<yourID>Hat".  See the DownfallPack UIStrings for examples.

Optionally, hats can hide the character's hair, for cases like Helmets.  This is done by adding "hatHidesHair = true" to your pack initialization. See the Bellord pack initialization file for an example.

### Rippable Cards

To make a card rippable by default, add the Rippable Cardmodifier to your card's constructor:
`CardModifierManager.addModifier(this, new RippableModifier());`

If you want to make a card rippable via an effect, use `new RippableModifier(false)`.

To give your card an `onRip` effect, implement `OnRipInterface` and fill in the required method.

Cards can be automatically ripped, skipping the the modifier requirement by using `RipCardsInHandAction` and passing in
a list of the cards you want ripped.