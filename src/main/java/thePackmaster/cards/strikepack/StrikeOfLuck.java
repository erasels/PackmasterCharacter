package thePackmaster.cards.strikepack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.AttackDamageRandomEnemyAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class StrikeOfLuck extends AbstractStrikePackCard {
    public final static String ID = makeID("StrikeOfLuck");

    private static final int ATTACK_DMG = 6;
    private static final int UPGRADE_PLUS_ATTACK_DMG = 3;

    public StrikeOfLuck() {
        super(ID, 1, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ALL_ENEMY);
        this.damage = this.baseDamage = ATTACK_DMG;
        this.tags.add(CardTags.STRIKE);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {

        int hits;
        hits = AbstractDungeon.cardRandomRng.random(1,3);
        for (int i = 0; i < hits; i++) {
            addToBot(new AttackDamageRandomEnemyAction(this, AbstractGameAction.AttackEffect.SLASH_VERTICAL));
        }
    }

    public void upp() {
        this.upgradeDamage(UPGRADE_PLUS_ATTACK_DMG);
    }
}