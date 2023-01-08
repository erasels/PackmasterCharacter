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
                if (AbstractDungeon.actionManager.cardsPlayedThisCombat.size() >= 2) {
                    AbstractCard c = AbstractDungeon.actionManager.cardsPlayedThisCombat.get(AbstractDungeon.actionManager.cardsPlayedThisCombat.size() - 2);
                    String parentID = SpireAnniversary5Mod.cardParentMap.get(c.cardID);
                    if (!CoreSetPack.ID.equals(parentID)) {
                        blck();
                    }
                }
            }
        });
    }

    public void upp() {
        upgradeBlock(2);
        upgradeDamage(2);
    }
}