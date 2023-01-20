package thePackmaster.cards.legacypack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import thePackmaster.actions.legacypack.StickAction;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class Stick extends AbstractLegacyCard {
    public final static String ID = makeID("Stick");

    public static final int DAMAGE_UP = 1;
    public static final int UPGRADE_PLUS_DAMAGE_UP = 1;
    public Stick() {
        super(ID, 0, CardType.SKILL, CardRarity.SPECIAL, CardTarget.ALL_ENEMY, CardColor.COLORLESS);

        this.baseMagicNumber = this.magicNumber = DAMAGE_UP;
        exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new StickAction(this.magicNumber));
    }

    public void upp() {
        this.upgradeMagicNumber(UPGRADE_PLUS_DAMAGE_UP);
    }
}