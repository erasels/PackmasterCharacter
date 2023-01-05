package thePackmaster.cards.coresetpack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.cards.AbstractPackmasterCard;
import thePackmaster.packs.CoreSetPack;
import thePackmaster.util.Wiz;

import java.util.Objects;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.SpireAnniversary5Mod.packsByID;

public class Synergize extends AbstractPackmasterCard {
    public final static String ID = makeID("Synergize");
    // intellij stuff skill, self, basic, , ,  5, 3, ,

    public Synergize() {
        super(ID, 1, CardType.ATTACK, CardRarity.COMMON, CardTarget.SELF_AND_ENEMY);
        baseBlock = 7;
        baseDamage = 7;
        isMultiDamage = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
        addToBot(new AbstractGameAction() {
            @Override
            public void update() {
                isDone = true;
                if (AbstractDungeon.actionManager.cardsPlayedThisCombat.size() >= 2) {
                    AbstractCard c = AbstractDungeon.actionManager.cardsPlayedThisCombat.get(AbstractDungeon.actionManager.cardsPlayedThisCombat.size() - 2);
                    if (Wiz.getPackByCard(c) != null) {
                        if (!Objects.equals(Wiz.getPackByCard(c).packID, makeID("CoreSetPack"))) {
                            allDmg(AbstractGameAction.AttackEffect.LIGHTNING);
                        }
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