package thePackmaster.cards.legacypack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;


import static thePackmaster.SpireAnniversary5Mod.makeID;

public class TerrorOfTheSeas extends AbstractLegacyCard {
    public final static String ID = makeID("TerrorOfTheSeas");

    private static final int VULNERABLE_VALUE = 3;
    private static final int UPGRADE_PLUS_VULNERABLE_VALUE = 2;
    public TerrorOfTheSeas() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.ALL_ENEMY);

        this.baseMagicNumber = this.magicNumber = VULNERABLE_VALUE;
        this.exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        for (AbstractMonster mo : AbstractDungeon.getCurrRoom().monsters.monsters) {
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(mo, p, new VulnerablePower(mo, this.magicNumber, false), this.magicNumber, true, AbstractGameAction.AttackEffect.NONE));
        }
    }

    public void upp() {
        this.upgradeMagicNumber(UPGRADE_PLUS_VULNERABLE_VALUE);
    }
}