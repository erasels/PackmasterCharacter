package thePackmaster.cards.downfallpack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.defect.ChannelAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.cards.AbstractPackmasterCard;
import thePackmaster.orbs.downfallpack.Ghostflame;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.*;

public class GhostflameStrike extends AbstractDownfallCard {
    public final static String ID = makeID("GhostflameStrike");

    public GhostflameStrike() {
        super(ID, 1, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        baseDamage = 7;
        baseMagicNumber = magicNumber = 1;
        this.showEvokeValue = true;
        this.showEvokeOrbCount = 1;
        tags.add(CardTags.STRIKE);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.FIRE);

        for (int i = 0; i < magicNumber; i++) {
            atb(new ChannelAction(new Ghostflame()));
        }
      }

    public void upp() {
        upgradeDamage(3);
    }
}