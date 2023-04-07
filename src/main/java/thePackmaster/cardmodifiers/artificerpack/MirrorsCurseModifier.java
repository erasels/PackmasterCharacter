package thePackmaster.cardmodifiers.artificerpack;

import basemod.abstracts.AbstractCardModifier;
import basemod.patches.com.megacrit.cardcrawl.cards.AbstractCard.MultiCardPreview;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.evacipated.cardcrawl.mod.stslib.util.extraicons.ExtraIcons;
import com.megacrit.cardcrawl.actions.utility.NewQueueCardAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.cards.artificerpack.MirrorsCurse;
import thePackmaster.util.TexLoader;
import thePackmaster.util.Wiz;

import java.util.ArrayList;

@AbstractCardModifier.SaveIgnore
public class MirrorsCurseModifier extends AbstractCardModifier {

    MirrorsCurse toPlayCard = null;
    private static final Texture ICON = TexLoader.getTexture(SpireAnniversary5Mod.makeImagePath("ui/MirrorsCurseIcon.png"));

    public MirrorsCurseModifier(MirrorsCurse original) {
        toPlayCard = (MirrorsCurse) original.makeStatEquivalentCopy();
    }

    public void onInitialApplication(AbstractCard card) {
        MultiCardPreview.add(card, toPlayCard);
        card.tags.add(SpireAnniversary5Mod.ISCARDMODIFIED);
    }

    @Override
    public void onUse(AbstractCard card, AbstractCreature target, UseCardAction action) {
        MirrorsCurse cpy = (MirrorsCurse) toPlayCard.makeSameInstanceOf();
        cpy.neighbors = getCardNeighbors(card);
        cpy.hasRightNeighbors = true;
        cpy.purgeOnUse = true;
        Wiz.atb(new NewQueueCardAction(cpy, true, false, true));
    }

    private ArrayList<AbstractCard> getCardNeighbors(AbstractCard c) {
        ArrayList<AbstractCard> neighbors = new ArrayList<>();
        if (Wiz.hand().contains(c)) {
            int index = Wiz.hand().group.indexOf(c);
            if (index > 0) {
                neighbors.add(Wiz.hand().group.get(index -1));
            }
            if (index < Wiz.hand().size() - 1) {
                neighbors.add(Wiz.hand().group.get(index + 1));
            }
        }
        return neighbors;
    }

    @Override
    public AbstractCardModifier makeCopy() {
        return new MirrorsCurseModifier(toPlayCard);
    }

    @Override
    public void onRender(AbstractCard card, SpriteBatch sb) {
        ExtraIcons.icon(ICON).render(card);
    }
}
