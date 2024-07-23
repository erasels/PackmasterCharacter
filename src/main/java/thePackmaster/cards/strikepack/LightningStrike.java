package thePackmaster.cards.strikepack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.defect.ChannelAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.Lightning;
import thePackmaster.util.Wiz;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class LightningStrike extends AbstractStrikePackCard {
    public final static String ID = makeID("LightningStrike");

    public LightningStrike() {
        super(ID, 2, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        baseDamage = 6;
        this.showEvokeValue = true;
        this.showEvokeOrbCount = 2;
        this.tags.add(CardTags.STRIKE);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.doDmg(m, damage, AbstractGameAction.AttackEffect.LIGHTNING);
        Wiz.doDmg(m, damage, AbstractGameAction.AttackEffect.LIGHTNING);
        AbstractDungeon.actionManager.addToBottom(new ChannelAction(new Lightning()));
        AbstractDungeon.actionManager.addToBottom(new ChannelAction(new Lightning()));
    }

    public void upp() {
        upgradeDamage(3);
    }
}