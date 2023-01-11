package thePackmaster.cards.pixiepack;

import basemod.cardmods.EtherealMod;
import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.ThePackmaster;
import thePackmaster.cards.AbstractPackmasterCard;
import thePackmaster.packs.PixiePack;

import javax.swing.text.html.HTMLDocument;

import java.util.Locale;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class PortalPunch extends AbstractPackmasterCard {
    public final static String ID = makeID("PortalPunch");

    private static final int baseAtk = 9;
    private static final int upgradeAtk = 12;
    private static final int baseMgk = 1;
    private static final int upgradeMgk = 2;

    public PortalPunch() {
        super(ID, 1, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY, ThePackmaster.Enums.PACKMASTER_RAINBOW);
        this.baseDamage = this.damage = baseAtk;
        this.baseMagicNumber = this.magicNumber = baseMgk;

        setBackgroundTexture("anniv5Resources/images/512/pixie/" + type.name().toLowerCase(Locale.ROOT)+".png",
                "anniv5Resources/images/1024/pixie/" + type.name().toLowerCase(Locale.ROOT)+".png");
    }

    @Override
    public void upp() {
        this.upgradeDamage(upgradeAtk-baseAtk);
        this.upgradeMagicNumber(upgradeMgk-baseMgk);
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        dmg(abstractMonster, AbstractGameAction.AttackEffect.BLUNT_HEAVY);
        for(int i = 0; i < this.magicNumber; i++)
        {
            AbstractCard toAdd = PixiePack.pixieGenerate(null,null,null);
            CardModifierManager.addModifier(toAdd,new EtherealMod());
            addToBot(new MakeTempCardInHandAction(toAdd));
        }
    }
}
