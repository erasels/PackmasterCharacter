package thePackmaster.cards.metapack;

import com.badlogic.gdx.graphics.Color;
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

import static thePackmaster.SpireAnniversary5Mod.MAGIC;
import static thePackmaster.SpireAnniversary5Mod.makeID;

public class EldritchBlast extends AbstractPackmasterCard {
    public final static String ID = makeID("EldritchBlast");

    private static final int DAMAGE = 5;
    private static final int DAMAGE_UPGRADE = 3;

    public EldritchBlast() {
        super(ID, 1, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        baseDamage = DAMAGE;
        magicNumber = baseMagicNumber = 1;
        tags.add(MAGIC);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.BLUNT_LIGHT));
        Wiz.atb(new AbstractGameAction() {
            @Override
            public void update() {
                CardGroup tmp = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
                tmp.group = (ArrayList<AbstractCard>) Wiz.p().hand.group.stream()
                        .filter(c -> Wiz.getLogicalCardCost(c) > 0 && c.hasTag(MAGIC))
                        .collect(Collectors.toList());
                for (int i = 0; i < magicNumber; i++) {
                    if(!tmp.isEmpty()) {
                        AbstractCard randomcard = tmp.getRandomCard(AbstractDungeon.cardRandomRng);

                        randomcard.setCostForTurn(Math.max(randomcard.costForTurn-magicNumber,0));
                        randomcard.superFlash(Color.GOLD.cpy());
                    }
                }
                isDone = true;
            }
        });
    }

    public void upp() {
            upgradeDamage(DAMAGE_UPGRADE);
    }
}
