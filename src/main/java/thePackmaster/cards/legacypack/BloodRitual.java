package thePackmaster.cards.legacypack;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.RitualPower;


import static thePackmaster.SpireAnniversary5Mod.makeID;

public class BloodRitual extends AbstractLegacyCard {
    public final static String ID = makeID("BloodRitual");

    public BloodRitual() {
        super(ID, 0, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);

        this.magicNumber = this.baseMagicNumber = 12;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new LoseHPAction(p,p,magicNumber));
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p,p,new RitualPower(p,1,true),1));
    }

    public void upp() {
        this.upgradeMagicNumber(-6);
    }
}