package thePackmaster.cards.upgradespack;

import com.evacipated.cardcrawl.mod.stslib.actions.common.MultiGroupSelectAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.SpireAnniversary5Mod;

public class SetUpShop extends AbstractBlacksmithCard {

    public final static String ID = SpireAnniversary5Mod.makeID("SetUpShop");
    private static final String SCREEN_MSG = CardCrawlGame.languagePack.getUIString(SpireAnniversary5Mod.makeID("SetUpShopScreen")).TEXT[0];

    public SetUpShop() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = 2;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {

        addToBot(new MultiGroupSelectAction(SCREEN_MSG,(list,map) ->
        {
            for (AbstractCard c : list) {
                if (c.canUpgrade()) c.upgrade();
                CardGroup group = map.get(c);
                group.moveToDeck(c,false);
            }
        }, magicNumber, CardGroup.CardGroupType.DRAW_PILE, CardGroup.CardGroupType.DISCARD_PILE));
    }

    public void upp() {
        upgradeMagicNumber(1);
    }
}
