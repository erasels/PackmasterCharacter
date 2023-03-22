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

public class AdventurersSword extends AbstractColorlessPackCard implements OnObtainCard {
    public final static String ID = makeID("AdventurersSword");
    // intellij stuff attack, enemy, uncommon, 11, 4, , , , 

    public AdventurersSword() {
        super(ID, 1, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        baseDamage = 11;
        MultiCardPreview.add(this, new GoodInstincts(), new SwiftStrike());
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.SLASH_HORIZONTAL);
    }

    @Override
    public void onObtainCard() {
        float fractical = Settings.WIDTH / 3;
        AbstractDungeon.effectsQueue.add(new ShowCardAndObtainEffect(new GoodInstincts(), fractical, Settings.HEIGHT / 2));
        AbstractDungeon.effectsQueue.add(new ShowCardAndObtainEffect(new SwiftStrike(), fractical * 2, Settings.HEIGHT / 2));
    }

    public void upp() {
        upgradeDamage(4);
    }
}