package thePackmaster.cards.colorlesspack;

import basemod.patches.com.megacrit.cardcrawl.cards.AbstractCard.MultiCardPreview;
import com.evacipated.cardcrawl.mod.stslib.cards.interfaces.OnObtainCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.colorless.GoodInstincts;
import com.megacrit.cardcrawl.cards.colorless.SwiftStrike;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndObtainEffect;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.shuffleIn;

public class AdventurersSword extends AbstractColorlessPackCard {
    public final static String ID = makeID("AdventurersSword");
    // intellij stuff attack, enemy, uncommon, 11, 4, , , , 

    public AdventurersSword() {
        super(ID, 1, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        baseDamage = 11;
        cardsToPreview = new Triforce();
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.SLASH_HORIZONTAL);
        shuffleIn(new Triforce());
    }

    public void upp() {
        upgradeDamage(4);
    }
}