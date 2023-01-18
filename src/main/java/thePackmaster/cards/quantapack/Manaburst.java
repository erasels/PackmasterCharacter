package thePackmaster.cards.quantapack;

import basemod.BaseMod;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.cards.AbstractPackmasterCard;
import thePackmaster.util.Wiz;

import java.util.ArrayList;
import java.util.stream.Collectors;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class Manaburst extends AbstractQuantaCard {
    public final static String ID = makeID("Manaburst");

    private static final int DAMAGE = 9;
    private static final int UPGRADE_DAMAGE = 3;
    private static final int CARD_AMOUNT = 1;
    private static final int UPGRADE_AMOUNT = 1;

    public Manaburst() {
        super(ID, 1, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        baseDamage = DAMAGE;
        this.magicNumber = this.baseMagicNumber = CARD_AMOUNT;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.BLUNT_LIGHT));
        Wiz.atb(new AbstractGameAction() {
            @Override
            public void update() {
                CardGroup tmp = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
                tmp.group = (ArrayList<AbstractCard>) Wiz.p().discardPile.group.stream()
                        .filter(c -> c.cost == 0 && c.type == CardType.ATTACK)
                        .collect(Collectors.toList());
                for (int i = 0; i < magicNumber; i++) {
                    if (!tmp.isEmpty() && Wiz.hand().size() < BaseMod.MAX_HAND_SIZE) {
                        AbstractCard tar = tmp.getRandomCard(AbstractDungeon.cardRandomRng);
                        tmp.removeCard(tar);
                        Wiz.p().discardPile.moveToHand(tar);
                    }
                }
                isDone = true;
            }
        });
    }

    public void upp() {
        upgradeDamage(UPGRADE_DAMAGE);
        upgradeMagicNumber(UPGRADE_AMOUNT);
    }
}
