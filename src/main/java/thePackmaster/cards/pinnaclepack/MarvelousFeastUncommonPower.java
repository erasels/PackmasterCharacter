package thePackmaster.cards.pinnaclepack;

import basemod.patches.com.megacrit.cardcrawl.cards.AbstractCard.MultiCardPreview;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.powers.pinnaclepack.Cooking;
import thePackmaster.powers.pinnaclepack.Cooking_;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class MarvelousFeastUncommonPower extends AbstractPinnacleCard {

    public final static String ID = makeID("MarvelousFeastUncommonPower");

    public MarvelousFeastUncommonPower() {
        super(ID, 2, AbstractCard.CardType.POWER, CardRarity.UNCOMMON, AbstractCard.CardTarget.SELF);
        this.isInnate = true;
        MultiCardPreview.add(this, new FriendshipCroquettesSpecialColourless(), new FishyCroquettesSpecialColourless(), new MeatyCroquettesSpecialColourless(), new MysteryCroquettesSpecialColourless());
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        if (this.upgraded){
            addToBot(new ApplyPowerAction(p, p, new Cooking_(p, 4)));
        }
        else {
            addToBot(new ApplyPowerAction(p, p, new Cooking(p, 4)));
        }
    }

    @Override
    public void upp() {
        MultiCardPreview.multiCardPreview.get(this).forEach(AbstractCard::upgrade);
    }

}
