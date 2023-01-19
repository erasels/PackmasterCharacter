package thePackmaster.cards.grandopeningpack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.EmptyDeckShuffleAction;
import com.megacrit.cardcrawl.actions.common.ShuffleAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.combat.MindblastEffect;
import thePackmaster.cards.AbstractPackmasterCard;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class MessUp extends AbstractGrandOpeningCard {
    public final static String ID = makeID("MessUp");

    public MessUp() {
        super(ID, 1, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        this.baseDamage = this.damage = 0;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if(upgraded) {
            if (AbstractDungeon.player.discardPile.size() > 0) {
                addToBot((AbstractGameAction) new EmptyDeckShuffleAction());
                addToBot((AbstractGameAction) new ShuffleAction(AbstractDungeon.player.drawPile, false));
                addToBot((AbstractGameAction)new VFXAction((AbstractGameEffect)new MindblastEffect(p.dialogX, p.dialogY, p.flipHorizontal)));
                addToBot((AbstractGameAction)new DamageAction((AbstractCreature)m, new DamageInfo((AbstractCreature)p, this.damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.NONE));
            }
        } else {
            addToBot((AbstractGameAction)new VFXAction((AbstractGameEffect)new MindblastEffect(p.dialogX, p.dialogY, p.flipHorizontal)));
            addToBot((AbstractGameAction)new DamageAction((AbstractCreature)m, new DamageInfo((AbstractCreature)p, this.damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.NONE));
            if (AbstractDungeon.player.discardPile.size() > 0) {
                addToBot((AbstractGameAction) new EmptyDeckShuffleAction());
                addToBot((AbstractGameAction) new ShuffleAction(AbstractDungeon.player.drawPile, false));
            }
        }
        this.rawDescription = cardStrings.DESCRIPTION;
        initializeDescription();
    }

        public void applyPowers() {
            this.baseDamage = AbstractDungeon.player.drawPile.size();
            if(this.upgraded){
                this.baseDamage += AbstractDungeon.player.discardPile.size();
            }
            super.applyPowers();
            this.rawDescription = cardStrings.DESCRIPTION + cardStrings.EXTENDED_DESCRIPTION[0];
            initializeDescription();
        }

        public void calculateCardDamage(AbstractMonster mo) {
            super.calculateCardDamage(mo);
            this.rawDescription = cardStrings.DESCRIPTION + cardStrings.EXTENDED_DESCRIPTION[0];
            initializeDescription();
        }

    @Override
    public void upp() {
    }
}