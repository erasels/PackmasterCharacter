package thePackmaster.patches.overwhelmingpack;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.megacrit.cardcrawl.actions.utility.NewQueueCardAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.cards.overwhelmingpack.MakeRoom;
import thePackmaster.util.Wiz;

import java.util.HashMap;
import java.util.Map;

@SpirePatch(
        clz = UseCardAction.class,
        method = SpirePatch.CONSTRUCTOR,
        paramtypez = { AbstractCard.class, AbstractCreature.class }
)
public class MakeRoomPatch {
    //I don't really wanna deal with properly attaching and removing card modifiers with correct timing.
    private static final Map<AbstractCard, MakeRoom.MakeRoomAction> makeRoomCards = new HashMap<>();
    public static void reset() {
        makeRoomCards.clear();
    }

    public static void makeRoom(AbstractCard toPlay, AbstractMonster target, MakeRoom.MakeRoomAction action) {
        makeRoomCards.put(toPlay, action);
        toPlay.flash();
        Wiz.att(new NewQueueCardAction(toPlay, target, true, true));
    }

    @SpirePostfixPatch
    public static void onUse(UseCardAction __instance, AbstractCard card, AbstractCreature target) {
        if (makeRoomCards.isEmpty())
            return;
        MakeRoom.MakeRoomAction action = makeRoomCards.remove(card);
        if (action != null) {
            Wiz.atb(action);
        }
    }
}
