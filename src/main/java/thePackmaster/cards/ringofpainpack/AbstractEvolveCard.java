package thePackmaster.cards.ringofpainpack;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import thePackmaster.ThePackmaster;
import thePackmaster.cards.AbstractPackmasterCard;
import thePackmaster.util.TexLoader;

import static thePackmaster.SpireAnniversary5Mod.makeCardPath;
import static thePackmaster.util.Wiz.atb;

public abstract class AbstractEvolveCard extends AbstractPackmasterCard {

    public static final int MAX_UPGRADES = 3;

    public AbstractEvolveCard(final String cardID, final int cost, final CardType type, final CardRarity rarity, final CardTarget target) {
        super(cardID, cost, type, rarity, target, ThePackmaster.Enums.PACKMASTER_RAINBOW);
    }

    @Override
    public boolean canUpgrade() {
        return timesUpgraded < MAX_UPGRADES;
    }

    @Override
    public void upgrade() {
        if (canUpgrade()) {
            ++this.timesUpgraded;
            this.upgraded = true;
            this.name = cardStrings.NAME + "+" + this.timesUpgraded;
            upp();
            changeArtAndText();
        }
    }

    protected void evolve() {
        AbstractEvolveCard c = this;
        atb(new AbstractGameAction() {
            @Override
            public void update() {
                c.upgrade();
                c.superFlash();
                c.applyPowers();
                this.isDone = true;
            }
        });
    }

    private void changeArtAndText() {
        if (timesUpgraded >= MAX_UPGRADES) {
            uDesc();
            timesUpgraded = MAX_UPGRADES;
            name = cardStrings.EXTENDED_DESCRIPTION[0];
            this.initializeTitle();
            String[] str = this.cardID.split(":");
            String newArtPath = str[1] + "Evolved" + ".png";
            loadCardImage(makeCardPath(newArtPath));
            uDesc();
        }
    }

    @Override
    protected Texture getPortraitImage() {
        if (timesUpgraded >= MAX_UPGRADES) {
            String[] str = this.cardID.split(":");
            String newArtPath = str[1] + "Evolved" + "_p.png";
            return TexLoader.getTexture(makeCardPath(newArtPath));
        } else {
            return super.getPortraitImage();
        }
    }
}
