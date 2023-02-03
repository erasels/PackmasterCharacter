package thePackmaster.cards.strikepack;

import basemod.helpers.CardModifierManager;
import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.actions.madsciencepack.SimpleAddModifierAction;
import thePackmaster.cardmodifiers.strikepack.AddStrikeTagModifier;
import thePackmaster.util.Wiz;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class Backstrike extends AbstractStrikePackCard {
    public final static String ID = makeID("Backstrike");

    public Backstrike() {
        super(ID, 0, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);

        baseDamage = 6;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.doDmg(m, damage, AbstractGameAction.AttackEffect.SMASH);

        Wiz.atb(new AbstractGameAction() {
            @Override
            public void update() {

                this.isDone = true;
                for (AbstractCard c: p.hand.group
                ) {
                    if (c.type == CardType.ATTACK && !c.hasTag(CardTags.STRIKE)) {
                        CardModifierManager.addModifier(c, new AddStrikeTagModifier());
                    }
                }
            }
        });
    }

    public void upp() {
        upgradeDamage(3);
    }
}