package thePackmaster.relics;

import basemod.abstracts.CustomSavable;
import com.evacipated.cardcrawl.mod.stslib.patches.CenterGridCardSelectScreen;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.random.Random;
import com.megacrit.cardcrawl.rewards.RewardItem;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.packs.AbstractCardPack;

import java.util.ArrayList;
import java.util.Collections;

import static thePackmaster.SpireAnniversary5Mod.*;

public class PMBoosterBox extends AbstractPackmasterRelic implements CustomSavable<ArrayList<String>> {

    public static final String ID = makeID("PMBoosterBox");

    int numPicked = 0;
    private boolean cardSelected = true;
    private String myPackOne = "";
    private String myPackTwo = "";
    private String myPackThree = "";
    public ArrayList<String> myPacks = new ArrayList<>();


    public PMBoosterBox() {
        super(ID, RelicTier.RARE, LandingSound.FLAT, null, true);
    }

    @Override
    public void onEquip() {
        openScreen();
    }

    @Override
    public ArrayList<String> onSave() {
        ArrayList<String> myList = new ArrayList<>();
        myList.add(myPackOne);
        myList.add(myPackTwo);
        myList.add(myPackThree);
        //  myList.add(myPackFour);
        return myList;
    }

    @Override
    public void onLoad(ArrayList<String> abstractCards) {
        myPackOne = abstractCards.get(0);
        myPackTwo = abstractCards.get(1);
        myPackThree = abstractCards.get(2);
        if (AbstractDungeon.player != null) {
            setDescriptionAfterLoading();
        }
        makeIDArray();
    }

    public void makeIDArray() {
        myPacks.clear();
        myPacks.add(myPackOne);
        myPacks.add(myPackTwo);
        myPacks.add(myPackThree);
    }

    private void openScreen() {
        this.cardSelected = false;
        if (AbstractDungeon.isScreenUp) {
            AbstractDungeon.dynamicBanner.hide();
            AbstractDungeon.overlayMenu.cancelButton.hide();
            AbstractDungeon.previousScreen = AbstractDungeon.screen;
        }

        AbstractDungeon.getCurrRoom().phase = AbstractRoom.RoomPhase.INCOMPLETE;
        CenterGridCardSelectScreen.centerGridSelect = true;
        CardGroup group = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
        ArrayList<AbstractCardPack> packs = new ArrayList<>(SpireAnniversary5Mod.allPacks);
        if (myPackOne != null) {
            if (!myPackOne.isEmpty()) {
                packs.remove(packsByID.get(myPackOne));
            }
        }
        if (myPackTwo != null) {
            if (!myPackTwo.isEmpty()) {
                packs.remove(packsByID.get(myPackTwo));
            }
        }
        Collections.shuffle(packs, new Random(Settings.seed + 43).random);
        for (AbstractCardPack p : packs) {
            group.addToTop(p.previewPackCard);
            if (group.size() >= 3) {
                break;
            }
        }

        AbstractDungeon.gridSelectScreen.open(group, 1, DESCRIPTIONS[2], false, false, false, false);
    }

    @Override
    public void update() {
        super.update();
        if (!this.cardSelected && AbstractDungeon.gridSelectScreen.selectedCards.size() == 1) {
            this.cardSelected = true;
            CenterGridCardSelectScreen.centerGridSelect = false;

            numPicked++;
            switch (numPicked) {
                case 1:
                    myPackOne = AbstractDungeon.gridSelectScreen.selectedCards.get(0).cardID;
                    break;
                case 2:
                    myPackTwo = AbstractDungeon.gridSelectScreen.selectedCards.get(0).cardID;
                    break;
                case 3:
                    myPackThree = AbstractDungeon.gridSelectScreen.selectedCards.get(0).cardID;
                    break;
            }
            AbstractDungeon.gridSelectScreen.selectedCards.clear();

            if (numPicked == 3) {
                AbstractDungeon.getCurrRoom().phase = AbstractRoom.RoomPhase.COMPLETE;
                setDescriptionAfterLoading();
                makeIDArray();
            } else {
                openScreen();
            }
        }
    }

    private void setDescriptionAfterLoading() {
        this.description = getUpdatedDescription();
        tips.clear();
        tips.add(new PowerTip(name, description));
        initializeTips();
    }

    @Override
    public String getUpdatedDescription() {
        String packs = "";
        if (myPackOne != "" && myPackOne != null) {
            packs = packs + DESCRIPTIONS[1] + SpireAnniversary5Mod.packsByID.get(myPackOne).name;
        }
        if (myPackTwo != "" && myPackTwo != null) {
            packs = packs + ", " + SpireAnniversary5Mod.packsByID.get(myPackTwo).name;
        }
        if (myPackThree != "" && myPackThree != null) {
            packs = packs + ", " + SpireAnniversary5Mod.packsByID.get(myPackThree).name;
        }
        return DESCRIPTIONS[0] + packs;
    }

}
