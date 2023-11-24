package thePackmaster.cards.creativitypack;

import basemod.helpers.CardModifierManager;
import basemod.patches.com.megacrit.cardcrawl.cards.AbstractCard.MultiCardPreview;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.tempCards.Safety;
import com.megacrit.cardcrawl.cards.tempCards.Smite;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.actions.FlexibleDiscoveryAction;
import thePackmaster.cardmodifiers.creativitypack.DrawCardModifier;

import java.util.ArrayList;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class Souvenirs extends AbstractCreativityCard {

    public final static String ID = makeID(Souvenirs.class.getSimpleName());

    public Souvenirs() {
        super(ID, 1, CardType.SKILL, CardRarity.COMMON, CardTarget.NONE);
        Smite smite = new Smite();
        Safety safety = new Safety();
        CardModifierManager.addModifier(smite, new DrawCardModifier());
        CardModifierManager.addModifier(safety, new DrawCardModifier());
        MultiCardPreview.add(this, smite, safety);
    }

    @Override
    public void upp() {
        ArrayList<AbstractCard> previewCards = MultiCardPreview.multiCardPreview.get(this);
        if (previewCards != null) {
            for (AbstractCard c : MultiCardPreview.multiCardPreview.get(this)) {
                c.upgrade();
            }
        }
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        ArrayList<AbstractCard> list = new ArrayList<>();
        Smite smite = new Smite();
        Safety safety = new Safety();
        CardModifierManager.addModifier(smite, new DrawCardModifier());
        CardModifierManager.addModifier(safety, new DrawCardModifier());
        if (this.upgraded) {
            smite.upgrade();
            safety.upgrade();
        }
        list.add(smite);
        list.add(safety);
        addToBot(new FlexibleDiscoveryAction(list, false));
    }
}
