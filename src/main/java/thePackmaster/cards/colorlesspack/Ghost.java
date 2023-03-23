package thePackmaster.cards.colorlesspack;

import basemod.helpers.CardModifierManager;
import com.badlogic.gdx.math.MathUtils;
import com.evacipated.cardcrawl.mod.stslib.cards.interfaces.StartupCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.FireballEffect;
import com.megacrit.cardcrawl.vfx.combat.GhostIgniteEffect;
import thePackmaster.cardmodifiers.colorlesspack.IsGhostModifier;
import thePackmaster.powers.colorlesspack.GhostPower;
import thePackmaster.util.Wiz;

import java.util.ArrayList;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.*;

public class Ghost extends AbstractColorlessPackCard implements StartupCard {
    public final static String ID = makeID("Ghost");
    // intellij stuff skill, enemy, uncommon, , , , , 12, 4

    public Ghost() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.ENEMY);
        baseMagicNumber = magicNumber = 10;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new SFXAction(scream(), MathUtils.random(0.8F, 1.2F)));
        AbstractDungeon.actionManager.addToBottom(new VFXAction(new FireballEffect(this.hb.cX, this.hb.cY, m.hb.cX, m.hb.cY), 0.5F));
        applyToEnemy(m, new GhostPower(m, magicNumber));
    }

    private static String scream() {
        int roll = MathUtils.random(1);
        if (roll == 0) {
            return "VO_NEMESIS_2A";
        } else {
            return "VO_NEMESIS_2B";
        }
    }

    @Override
    public boolean atBattleStartPreDraw() {
        att(new AbstractGameAction() {
            @Override
            public void update() {
                isDone = true;
                int index = AbstractDungeon.player.drawPile.group.indexOf(Ghost.this);
                AbstractDungeon.player.drawPile.removeCard(Ghost.this);
                ArrayList<AbstractCard> possibilities = new ArrayList<>();
                AbstractDungeon.player.drawPile.group.forEach(q -> {
                    if (!q.cardID.equals(Ghost.ID) && !q.cardID.equals(ThePrism.ID)) { // Sometime hook this later than other startups; not a bug or anything but it'll prevent easy guesses
                        possibilities.add(q);
                    }
                });
                AbstractCard disguise = Wiz.getRandomItem(possibilities, AbstractDungeon.cardRandomRng).makeStatEquivalentCopy();
                CardModifierManager.addModifier(disguise, new IsGhostModifier(Ghost.this));
                AbstractDungeon.player.drawPile.group.add(index, disguise);
            }
        });
        return true;
    }

    public void upp() {
        upgradeMagicNumber(3);
    }
}