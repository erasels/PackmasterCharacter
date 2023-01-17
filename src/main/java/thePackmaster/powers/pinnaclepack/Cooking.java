package thePackmaster.powers.pinnaclepack;

import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.powers.AbstractPower;
import thePackmaster.cards.pinnaclepack.FishyCroquettesSpecialColourless;
import thePackmaster.cards.pinnaclepack.FriendshipCroquettesSpecialColourless;
import thePackmaster.cards.pinnaclepack.MeatyCroquettesSpecialColourless;
import thePackmaster.cards.pinnaclepack.MysteryCroquettesSpecialColourless;
import thePackmaster.powers.AbstractPackmasterPower;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class Cooking extends AbstractPackmasterPower {

    public static final String POWER_ID = makeID("Cooking");
    public static final String NAME = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).NAME;
    public static final String[] DESCRIPTIONS = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).DESCRIPTIONS;
    private static int CookingOffset;

    public Cooking(AbstractCreature owner, int amount) {
        super(POWER_ID,NAME, AbstractPower.PowerType.BUFF,false,owner,amount);
        this.ID = "Cooking" + CookingOffset;
        CookingOffset++;
        this.owner = owner;
        updateDescription();
    }

    public void updateDescription() {
        description = DESCRIPTIONS[0];
    }

    public void atEndOfTurn(boolean isPlayer) {
        int turncounter = this.amount;
        if (turncounter > 4){
            this.amount = 4;
            turncounter = this.amount;
        }
        switch (turncounter){
            case 4:
                FriendshipCroquettesSpecialColourless First = new FriendshipCroquettesSpecialColourless();
                addToBot(new MakeTempCardInHandAction(First));
                break;
            case 3:
                FishyCroquettesSpecialColourless Second = new FishyCroquettesSpecialColourless();
                addToBot(new MakeTempCardInHandAction(Second));
                break;
            case 2:
                MeatyCroquettesSpecialColourless Third = new MeatyCroquettesSpecialColourless();
                addToBot(new MakeTempCardInHandAction(Third));
                break;
            case 1:
                MysteryCroquettesSpecialColourless Fourth = new MysteryCroquettesSpecialColourless();
                addToBot(new MakeTempCardInHandAction(Fourth));
                break;
            default:
                break;
        }
        addToBot(new ReducePowerAction(this.owner, this.owner, this, 1));
        if (this.amount <= 0){
            addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, this));
        }
    }

}
