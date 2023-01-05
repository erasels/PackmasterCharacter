package thePackmaster.cards.strikepack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;
import thePackmaster.util.Wiz;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class PenaltyStrike extends AbstractStrikePackCard {
    public final static String ID = makeID("PenaltyStrike");

    private static final int ATTACK_DMG = 6;
    private static final int STR_LOSS = 1;
    private static final int UPGRADE_PLUS_STR_LOSS = 1;

    public PenaltyStrike() {
        super(ID, 1, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        this.damage = this.baseDamage = ATTACK_DMG;
        baseMagicNumber = magicNumber = STR_LOSS;
        this.tags.add(CardTags.STRIKE);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new DamageAction(m,
                new DamageInfo(p, this.damage, this.damageTypeForTurn),
                AbstractGameAction.AttackEffect.SLASH_HEAVY));
        Wiz.applyToEnemy(m, new StrengthPower(m, magicNumber * -1));
    }

    public void upp() {
        this.upgradeMagicNumber(UPGRADE_PLUS_STR_LOSS);
    }
}