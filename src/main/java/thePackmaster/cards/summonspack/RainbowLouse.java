package thePackmaster.cards.summonspack;

import com.evacipated.cardcrawl.mod.stslib.patches.FlavorText;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.defect.ChannelAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import thePackmaster.cards.AbstractPackmasterCard;
import thePackmaster.orbs.summonspack.Louse;
import thePackmaster.patches.arcanapack.AnimatedCardsPatch;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.cards.summonspack.FlavorConstants.FLAVOR_BOX_COLOR;
import static thePackmaster.cards.summonspack.FlavorConstants.FLAVOR_TEXT_COLOR;
import static thePackmaster.util.Wiz.adp;
import static thePackmaster.util.Wiz.atb;

public class RainbowLouse extends AbstractPackmasterCard {
    public final static String ID = makeID(RainbowLouse.class.getSimpleName());
    private static final int COST = 2;
    private static final CardType TYPE = CardType.SKILL;
    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;

    private static final int MAGIC = 3;

    public RainbowLouse() {
        super(ID, COST, TYPE, RARITY, TARGET);
        exhaust = true;
        FlavorText.AbstractCardFlavorFields.boxColor.set(this, FLAVOR_BOX_COLOR);
        FlavorText.AbstractCardFlavorFields.textColor.set(this, FLAVOR_TEXT_COLOR);
        AnimatedCardsPatch.loadFrames(this, 36, 2f/36f);
        baseMagicNumber = magicNumber = MAGIC;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new ChannelAction(new Louse()));
        if (!upgraded)
            return;
        atb(new AbstractGameAction() {
            @Override
            public void update() {
                for (AbstractOrb orb : adp().orbs)
                    if (orb instanceof Louse)
                        for (int i = 0; i < magicNumber; i++)
                            ((Louse) orb).passiveEffect();
                isDone = true;
            }
        });
    }

    @Override
    public void upp() {
        uDesc();
    }
}
