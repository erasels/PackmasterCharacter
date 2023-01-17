package thePackmaster.patches.contentcreatorpack;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePrefixPatch;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.vfx.cardManip.PurgeCardEffect;
import thePackmaster.cards.contentcreatorpack.MegaEtherealCard;
import thePackmaster.util.Wiz;

import java.util.Arrays;

public class EndOfTurnPatch {
    @SpirePatch(clz = AbstractRoom.class, method = "endTurn")
    public static class EndTurn {
        @SpirePrefixPatch
        public static void Prefix(AbstractRoom __instance) {
            if (Wiz.isInCombat()) {
                AbstractPlayer p = AbstractDungeon.player;
                for (CardGroup cardGroup : Arrays.asList(new CardGroup[]{p.hand, p.drawPile, p.discardPile})) {
                    for (AbstractCard q : cardGroup.group) {
                        if (q instanceof MegaEtherealCard) {
                            AbstractDungeon.topLevelEffects.add(new PurgeCardEffect(q, Settings.WIDTH / 2.0F, Settings.HEIGHT / 2.0F));
                            cardGroup.moveToExhaustPile(q);
                        }
                    }
                }
            }
        }
    }
}
