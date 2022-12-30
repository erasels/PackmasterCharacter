/*
package thePackmaster.cards.demopack;

import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import thePackmaster.cards.AbstractPackmasterCard;
import thePackmaster.powers.LambdaPower;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.applyToSelf;

public class GreedIsGood extends AbstractPackmasterCard {
    public final static String ID = makeID("GreedIsGood");
    // intellij stuff power, self, uncommon, , , , , 3, 2

    public GreedIsGood() {
        super(ID, 1, CardType.POWER, CardRarity.UNCOMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = 3;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new LambdaPower(makeID("GreedIsGoodPower"), cardStrings.EXTENDED_DESCRIPTION[0], AbstractPower.PowerType.BUFF, false, p, magicNumber, false) {
            @Override
            public float atDamageGive(float damage, DamageInfo.DamageType type) {
                if (AbstractDungeon.player.hand.size() >= 7) {
                    return damage + amount;
                }
                return damage;
            }

            @Override
            public float modifyBlock(float blockAmount) {
                if (AbstractDungeon.player.hand.size() >= 7) {
                    return blockAmount + amount;
                }
                return blockAmount;
            }

            @Override
            public void updateDescription() {
                description = cardStrings.EXTENDED_DESCRIPTION[1] + amount + cardStrings.EXTENDED_DESCRIPTION[2] + amount + cardStrings.EXTENDED_DESCRIPTION[3];
            }
        });
    }

    public void upp() {
        upgradeMagicNumber(2);
    }
}

 */