package thePackmaster.cards.pixiepack;

import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.LightningEffect;
import thePackmaster.actions.pixiepack.EnchantmentAction;
import thePackmaster.packs.PixiePack;
import thePackmaster.powers.pixiepack.FetchPower;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class Fetch extends AbstractPixieCard {
    public final static String ID = makeID("Fetch");

    private static final int baseMag = 1;

    public Fetch() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        this.baseMagicNumber = this.magicNumber = baseMag;
        this.isEthereal = true;
        this.exhaust = true;
    }

    @Override
    public void upp() {
        this.isEthereal = false;
        this.exhaust = false;
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        addToBot(new DrawCardAction(magicNumber));
        addToBot(new ApplyPowerAction(abstractPlayer, abstractPlayer, new FetchPower(abstractPlayer, damage)));
    }
}
