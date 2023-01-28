package thePackmaster.cards.clawpack;

import com.megacrit.cardcrawl.actions.common.ObtainPotionAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.helpers.PotionHelper;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.potions.AbstractPotion;
import thePackmaster.potions.clawpack.AttackPotionButClaw;
import thePackmaster.potions.clawpack.ClawPowerPotion;
import thePackmaster.potions.clawpack.DrawClawsPotion;
import thePackmaster.potions.clawpack.GenerateClawsPotion;
import thePackmaster.util.Wiz;

import java.util.ArrayList;

import static thePackmaster.SpireAnniversary5Mod.CLAW;
import static thePackmaster.SpireAnniversary5Mod.makeID;

public class Alclawmize extends AbstractClawCard {
    public final static String ID = makeID("Alclawmize");

    ArrayList<String> potions = new ArrayList<>();

    public Alclawmize() {
        super(ID, 1, CardType.SKILL, CardRarity.RARE, CardTarget.SELF);
        exhaust = true;
        tags.add(CardTags.HEALING);
        tags.add(CLAW);

        potions.add(AttackPotionButClaw.POTION_ID);
        potions.add(ClawPowerPotion.POTION_ID);
        potions.add(DrawClawsPotion.POTION_ID);
        potions.add(GenerateClawsPotion.POTION_ID);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        String chosen = Wiz.getRandomItem(potions);
        AbstractPotion pot = PotionHelper.getPotion(chosen);

        this.addToBot(new ObtainPotionAction(pot));

    }

    public void upp() {
        upgradeBaseCost(0);
    }
}