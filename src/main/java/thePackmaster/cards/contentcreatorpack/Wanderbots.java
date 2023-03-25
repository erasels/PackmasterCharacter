package thePackmaster.cards.contentcreatorpack;

import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.actions.defect.ChannelAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.cardmodifiers.witchesstrikepack.InscribedMod;
import thePackmaster.orbs.contentcreatorpack.Wanderbot;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.atb;

public class Wanderbots extends AbstractContentCard {
    public final static String ID = makeID("Wanderbots");

    public Wanderbots() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        baseBlock = 6;
        this.showEvokeValue = true;
        this.showEvokeOrbCount = 1;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
        atb(new ChannelAction(new Wanderbot()));
    }

    public void upp() {
        CardModifierManager.addModifier(this, new InscribedMod(true, false));
    }
}