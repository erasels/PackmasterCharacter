package thePackmaster.cards.strikepack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.actions.strikepack.WorkersStrikeAction;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class WorkersStrike extends AbstractStrikePackCard {
    public final static String ID = makeID("WorkersStrike");

    private static final int ATTACK_DMG = 6;
    private static final int STR_LOSS = 1;
    private static final int UPGRADE_PLUS_ATTACK_DMG = 3;

    public WorkersStrike() {
        super(ID, 1, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        this.damage = this.baseDamage = ATTACK_DMG;
        baseMagicNumber = magicNumber = 1;
        this.tags.add(CardTags.STRIKE);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new DamageAction(m,
                new DamageInfo(p, this.damage, this.damageTypeForTurn),
                AbstractGameAction.AttackEffect.SLASH_DIAGONAL));

        AbstractDungeon.actionManager.addToBottom(new WorkersStrikeAction());
    }

    public void upp() {
        this.upgradeDamage(UPGRADE_PLUS_ATTACK_DMG);
    }
}