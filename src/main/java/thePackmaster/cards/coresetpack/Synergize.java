package thePackmaster.cards.coresetpack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.cards.AbstractPackmasterCard;
import thePackmaster.packs.CoreSetPack;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class Synergize extends AbstractPackmasterCard {
    public final static String ID = makeID("Synergize");
    // intellij stuff skill, self, basic, , ,  5, 3, ,

    public Synergize() {
        super(ID, 1, CardType.ATTACK, CardRarity.COMMON, CardTarget.ALL_ENEMY);
        baseBlock = 7;
        baseDamage = 7;
        isMultiDamage = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        allDmg(AbstractGameAction.AttackEffect.LIGHTNING);
        addToBot(new AbstractGameAction() {
            @Override
            public void update() {
                isDone = true;
                if (otherPacksInHandCheck()) {
                    blck();
                }
            }
        });
    }

    @Override
    public void triggerOnGlowCheck() {
        this.glowColor = AbstractCard.BLUE_BORDER_GLOW_COLOR.cpy();
        if (this.otherPacksInHandCheck()) {
            this.glowColor = AbstractCard.GOLD_BORDER_GLOW_COLOR.cpy();
        }
    }

    private boolean otherPacksInHandCheck() {
        long otherPacksInHand = AbstractDungeon.player.hand.group.stream()
                .map(c -> SpireAnniversary5Mod.cardParentMap.getOrDefault(c.cardID, null))
                .filter(s -> s != null && !s.equals(CoreSetPack.ID))
                .distinct()
                .count();
        return otherPacksInHand >= 2;
    }

    @Override
    public void upp() {
        upgradeBlock(2);
        upgradeDamage(2);
    }
}