package thePackmaster.cards.weaponspack;


import basemod.helpers.TooltipInfo;
import com.megacrit.cardcrawl.actions.watcher.ChooseOneAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import java.util.ArrayList;
import java.util.List;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class Sword extends AbstractWeaponsPackCard {

    public static final String ID = makeID("Sword");
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final int COST = 1;
    private static final int WEAPON_DURABILITY = 3;
    private static final int UPGRADE_PLUS_WEAPON_DURABILITY = 1;
    public static final String[] EXTENDED_DESCRIPTION = cardStrings.EXTENDED_DESCRIPTION;

    public Sword() {
        super(ID, COST, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);

        this.baseMagicNumber = this.magicNumber = WEAPON_DURABILITY;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        ArrayList<AbstractCard> choices = new ArrayList<>();
        choices.add(new SwordOfFire());
        choices.add(new SwordOfWisdom());
        choices.add(new SwordOfChaos());
        if (this.upgraded) {
            for (AbstractCard c : choices) {
                c.upgrade();
            }
        }
        for (AbstractCard c : choices) {
            c.baseMagicNumber = this.baseMagicNumber;
            c.magicNumber = this.magicNumber;
            c.isMagicNumberModified = this.isMagicNumberModified;
        }
        addToBot(new ChooseOneAction(choices));
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeMagicNumber(UPGRADE_PLUS_WEAPON_DURABILITY);
        }
    }

    @Override
    public List<TooltipInfo> getCustomTooltips() {
        List<TooltipInfo> tooltips = new ArrayList<>();
        tooltips.add(new TooltipInfo(EXTENDED_DESCRIPTION[0], EXTENDED_DESCRIPTION[1]));
        return tooltips;
    }

}
